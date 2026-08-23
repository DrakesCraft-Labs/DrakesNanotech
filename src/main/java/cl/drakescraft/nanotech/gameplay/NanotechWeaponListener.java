package cl.drakescraft.nanotech.gameplay;

import cl.drakescraft.nanotech.DrakesNanotechPlugin;
import cl.drakescraft.nanotech.content.NanotechContent;
import cl.drakescraft.nanotech.effects.CataclysmEffectService;
import cl.drakescraft.nanotech.protection.ProtectionGate;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** Particle-first ARC weapons; never damages players and never creates block explosions. */
public final class NanotechWeaponListener implements Listener {
    private final DrakesNanotechPlugin plugin;
    private final NanotechContent content;
    private final ProtectionGate protectionGate;
    private final CataclysmEffectService cataclysms;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public NanotechWeaponListener(DrakesNanotechPlugin plugin, NanotechContent content, ProtectionGate protectionGate) {
        this.plugin = plugin;
        this.content = content;
        this.protectionGate = protectionGate;
        this.cataclysms = new CataclysmEffectService(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRepulsor(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        Player player = event.getPlayer();
        String id = content.idOf(event.getItem());
        if (id.isBlank()) return;
        long now = System.currentTimeMillis();
        if (cooldowns.getOrDefault(player.getUniqueId(), 0L) > now) {
            player.sendActionBar("§cRepulsor cooling down");
            return;
        }
        cooldowns.put(player.getUniqueId(), now + 1800L);
        event.setCancelled(true);
        try {
            switch (id) {
                case "REPULSOR_EMITTER" -> fireRepulsor(player);
                case "WEB_SHOOTER" -> fireWeb(player);
                case "HAWKEYE_COMPOUND_BOW", "SONIC_ARROW" -> sonicArrow(player);
                case "WIDOW_BITE_GAUNTLET" -> widowBite(player);
                case "ORBITAL_SKY_LANCE", "ULTRON_INFINITY_BEAM", "CELESTIAL_NULLIFIER" -> fireSkyWeapon(player, id);
                case "REALITY_FRACTURE_DEVICE", "DIMENSIONAL_BREACH_CHARGE", "SINGULARITY_WARHEAD" -> fractureReality(player);
                default -> cooldowns.remove(player.getUniqueId());
            }
        } catch (RuntimeException error) {
            plugin.getLogger().log(java.util.logging.Level.WARNING, "Ability execution failed safely for " + id, error);
            player.sendMessage("§6DrakesNanotech §8· §cThe ability aborted safely.");
        }
    }

    private void fireWeb(Player player) {
        Vector direction = player.getEyeLocation().getDirection().normalize();
        var start = player.getEyeLocation().add(direction.clone().multiply(0.6D));
        for (double step = 0; step < 22D; step += 0.35D) {
            player.getWorld().spawnParticle(Particle.END_ROD, start.clone().add(direction.clone().multiply(step)), 1, 0, 0, 0, 0);
        }
        RayTraceResult hit = player.getWorld().rayTraceEntities(start, direction, 22D, 0.5D,
                entity -> eligibleTarget(player, entity));
        if (hit != null && hit.getHitEntity() instanceof LivingEntity target) {
            target.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOWNESS, 100, 5, false, true));
            target.getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation().add(0, 1, 0), 35, 0.45, 0.8, 0.45, 0.01);
        }
    }

    private void widowBite(Player player) {
        LivingEntity previous = player;
        int affected = 0;
        for (LivingEntity target : player.getWorld().getNearbyLivingEntities(player.getLocation(), 12D,
                entity -> !(entity instanceof Player))) {
            if (affected++ >= 6) break;
            drawArc(previous.getLocation().add(0, 1, 0), target.getLocation().add(0, 1, 0));
            target.damage(7D, player);
            target.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOWNESS, 50, 3, false, true));
            previous = target;
        }
        player.getWorld().playSound(player, Sound.BLOCK_BEACON_POWER_SELECT, 1F, 1.8F);
    }

    private void sonicArrow(Player player) {
        org.bukkit.Location target = aimedLocation(player, 34D);
        Particle.DustOptions purple = new Particle.DustOptions(Color.fromRGB(185, 90, 255), 1.3F);
        for (double radius = 1D; radius <= 7D; radius += 1D) {
            for (int point = 0; point < 28; point++) {
                double angle = Math.PI * 2D * point / 28D;
                target.getWorld().spawnParticle(Particle.DUST,
                        target.clone().add(Math.cos(angle) * radius, 0.25, Math.sin(angle) * radius), 1, 0, 0, 0, purple);
            }
        }
        target.getWorld().playSound(target, Sound.ENTITY_WARDEN_SONIC_BOOM, 0.9F, 1.7F);
        target.getWorld().getNearbyLivingEntities(target, 7D, entity -> !(entity instanceof Player)).stream()
                .limit(12)
                .forEach(entity -> {
                    entity.damage(8D, player);
                    entity.setVelocity(entity.getVelocity().add(entity.getLocation().toVector()
                            .subtract(target.toVector()).normalize().multiply(0.65D).setY(0.25D)));
                });
    }

    private void drawArc(org.bukkit.Location from, org.bukkit.Location to) {
        Vector delta = to.toVector().subtract(from.toVector());
        for (double step = 0; step <= 1D; step += 0.08D) {
            org.bukkit.Location point = from.clone().add(delta.clone().multiply(step));
            point.add((Math.random() - 0.5D) * 0.2D, (Math.random() - 0.5D) * 0.2D, (Math.random() - 0.5D) * 0.2D);
            from.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, point, 2, 0.02, 0.02, 0.02, 0);
        }
    }

    private void fireSkyWeapon(Player player, String id) {
        org.bukkit.Location target = aimedLocation(player, 52D);
        double radius = id.equals("CELESTIAL_NULLIFIER") ? 18D : 11D;
        if (!protectionGate.allowLargeAbility(player, target, radius)) return;
        Color color = id.equals("ULTRON_INFINITY_BEAM") ? Color.fromRGB(210, 70, 255) : Color.fromRGB(100, 225, 255);
        cataclysms.skyLance(player, target, color, radius, id.equals("CELESTIAL_NULLIFIER") ? 28D : 20D);
    }

    private void fractureReality(Player player) {
        org.bukkit.Location target = aimedLocation(player, 36D);
        if (!protectionGate.allowLargeAbility(player, target, 14D)) return;
        cataclysms.realityFracture(player, target, 14D);
    }

    private org.bukkit.Location aimedLocation(Player player, double range) {
        RayTraceResult result = player.rayTraceBlocks(range);
        return result != null && result.getHitPosition() != null
                ? result.getHitPosition().toLocation(player.getWorld())
                : player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(range));
    }

    /** Draws a bounded ray and applies one protected PvE impact. */
    private void fireRepulsor(Player player) {
        double range = plugin.getConfig().getDouble("effects.repulsor-range", 28D);
        Vector direction = player.getEyeLocation().getDirection().normalize();
        var start = player.getEyeLocation().add(direction.clone().multiply(0.7));
        Particle.DustOptions core = new Particle.DustOptions(Color.fromRGB(120, 235, 255), 1.15F);
        for (double distance = 0; distance <= range; distance += 0.45D) {
            var point = start.clone().add(direction.clone().multiply(distance));
            player.getWorld().spawnParticle(Particle.DUST, point, 1, 0, 0, 0, core);
            if (((int) (distance * 10)) % 18 == 0) player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, point, 2, 0.04, 0.04, 0.04, 0);
        }
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 0.8F, 1.8F);
        RayTraceResult hit = player.getWorld().rayTraceEntities(start, direction, range, 0.45,
                entity -> eligibleTarget(player, entity));
        if (hit == null || !(hit.getHitEntity() instanceof LivingEntity target)) return;
        target.damage(plugin.getConfig().getDouble("effects.repulsor-damage", 10D), player);
        target.setVelocity(target.getVelocity().add(direction.multiply(0.75)));
        target.getWorld().spawnParticle(Particle.FLASH, target.getLocation().add(0, 1, 0), 1);
        target.getWorld().spawnParticle(Particle.END_ROD, target.getLocation().add(0, 1, 0), 24, 0.35, 0.55, 0.35, 0.05);
    }

    private static boolean eligibleTarget(Player source, Entity entity) {
        return entity instanceof LivingEntity && !(entity instanceof Player) && !entity.getUniqueId().equals(source.getUniqueId());
    }
}
