package cl.drakescraft.nanotech.gameplay;

import cl.drakescraft.nanotech.DrakesNanotechPlugin;
import cl.drakescraft.nanotech.content.NanotechContent;
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
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public NanotechWeaponListener(DrakesNanotechPlugin plugin, NanotechContent content) {
        this.plugin = plugin;
        this.content = content;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRepulsor(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick() || !content.is(event.getItem(), "REPULSOR_EMITTER")) return;
        Player player = event.getPlayer();
        long now = System.currentTimeMillis();
        if (cooldowns.getOrDefault(player.getUniqueId(), 0L) > now) {
            player.sendActionBar("§cRepulsor cooling down");
            return;
        }
        cooldowns.put(player.getUniqueId(), now + 1800L);
        event.setCancelled(true);
        fireRepulsor(player);
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
