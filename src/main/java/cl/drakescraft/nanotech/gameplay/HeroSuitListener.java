package cl.drakescraft.nanotech.gameplay;

import cl.drakescraft.nanotech.DrakesNanotechPlugin;
import cl.drakescraft.nanotech.content.NanotechContent;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

/** Lightweight hero-suit abilities with no persistent entities or block modification. */
public final class HeroSuitListener implements Listener {
    private final DrakesNanotechPlugin plugin;
    private final NanotechContent content;
    private final Map<UUID, Long> wallLeapCooldown = new HashMap<>();

    public HeroSuitListener(DrakesNanotechPlugin plugin, NanotechContent content) {
        this.plugin = plugin;
        this.content = content;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWallLeap(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking() || !wearsSpiderSuit(player) || !touchingWall(player)) return;
        long now = System.currentTimeMillis();
        if (wallLeapCooldown.getOrDefault(player.getUniqueId(), 0L) > now) return;
        wallLeapCooldown.put(player.getUniqueId(), now + 1200L);
        try {
            Vector launch = player.getLocation().getDirection().multiply(-0.45D).setY(0.82D);
            player.setVelocity(player.getVelocity().add(launch));
            player.getWorld().spawnParticle(Particle.SNOWFLAKE, player.getLocation().add(0, 0.7, 0), 18, 0.3, 0.45, 0.3, 0.02);
            player.getWorld().playSound(player, Sound.ENTITY_SPIDER_STEP, 0.8F, 1.6F);
        } catch (RuntimeException error) {
            plugin.getLogger().log(Level.WARNING, "Spider wall leap aborted safely", error);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onHeroFall(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player) || event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (wearsSpiderSuit(player) || content.is(player.getInventory().getChestplate(), "MYSTIC_CLOAK")) {
            event.setDamage(event.getDamage() * 0.15D);
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 14, 0.3, 0.05, 0.3, 0.02);
        }
    }

    private boolean wearsSpiderSuit(Player player) {
        String id = content.idOf(player.getInventory().getChestplate());
        return id.equals("CLASSIC_SPIDER_SUIT") || id.equals("STEALTH_SPIDER_SUIT") || id.equals("IRON_SPIDER_NANOCORE");
    }

    private boolean touchingWall(Player player) {
        var block = player.getLocation().getBlock();
        return !block.getRelative(BlockFace.NORTH).isPassable() || !block.getRelative(BlockFace.SOUTH).isPassable()
                || !block.getRelative(BlockFace.EAST).isPassable() || !block.getRelative(BlockFace.WEST).isPassable();
    }
}
