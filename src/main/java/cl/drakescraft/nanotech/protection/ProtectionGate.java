package cl.drakescraft.nanotech.protection;

import cl.drakescraft.nanotech.DrakesNanotechPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/** Fail-closed WorldGuard/ProtectionStones gate for every large-area ability. */
public final class ProtectionGate {
    private final DrakesNanotechPlugin plugin;
    private final Method protectionStoneLookup;
    private boolean warned;

    public ProtectionGate(DrakesNanotechPlugin plugin) {
        this.plugin = plugin;
        this.protectionStoneLookup = findProtectionStoneLookup();
    }

    /** Blocks the ability when any sampled point intersects any region, including the actor's own. */
    public boolean allowLargeAbility(Player actor, Location center, double effectRadius) {
        int buffer = Math.max(0, plugin.getConfig().getInt("safety.protected-region-buffer", 24));
        double radius = effectRadius + buffer;
        for (Location sample : samples(center, radius)) {
            if (isProtected(sample)) {
                String raw = plugin.getConfig().getString("safety.protected-message",
                        "&6DrakesNanotech &8· &cYou are inside or near a protected region. &7This ability cannot be used here, even inside your own protection.");
                actor.sendMessage(ChatColor.translateAlternateColorCodes('&', raw));
                return false;
            }
        }
        return true;
    }

    private List<Location> samples(Location center, double radius) {
        List<Location> result = new ArrayList<>();
        result.add(center);
        for (int index = 0; index < 16; index++) {
            double angle = Math.PI * 2D * index / 16D;
            result.add(center.clone().add(Math.cos(angle) * radius, 0, Math.sin(angle) * radius));
            result.add(center.clone().add(Math.cos(angle) * radius * 0.5D, 0, Math.sin(angle) * radius * 0.5D));
        }
        return result;
    }

    /** Queries ProtectionStones first and WorldGuard second. Any integration failure denies use. */
    private boolean isProtected(Location location) {
        try {
            if (protectionStoneLookup != null && protectionStoneLookup.invoke(null, location) != null) return true;
            if (plugin.getServer().getPluginManager().getPlugin("WorldGuard") == null) return protectionStoneLookup == null;
            Class<?> worldGuardType = Class.forName("com.sk89q.worldguard.WorldGuard");
            Object worldGuard = worldGuardType.getMethod("getInstance").invoke(null);
            Object platform = worldGuard.getClass().getMethod("getPlatform").invoke(worldGuard);
            Object container = platform.getClass().getMethod("getRegionContainer").invoke(platform);
            Object query = container.getClass().getMethod("createQuery").invoke(container);
            Class<?> adapter = Class.forName("com.sk89q.worldedit.bukkit.BukkitAdapter");
            Object adapted = adapter.getMethod("adapt", Location.class).invoke(null, location);
            Class<?> worldEditLocation = Class.forName("com.sk89q.worldedit.util.Location");
            Object regions = query.getClass().getMethod("getApplicableRegions", worldEditLocation).invoke(query, adapted);
            return ((Number) regions.getClass().getMethod("size").invoke(regions)).intValue() > 0;
        } catch (ReflectiveOperationException | RuntimeException | LinkageError error) {
            if (!warned) {
                warned = true;
                plugin.getLogger().log(Level.WARNING, "Protection lookup failed; large abilities will fail closed.", error);
            }
            return true;
        }
    }

    private Method findProtectionStoneLookup() {
        try {
            return Class.forName("dev.espi.protectionstones.PSRegion")
                    .getMethod("fromLocation", Location.class);
        } catch (ReflectiveOperationException | LinkageError ignored) {
            return null;
        }
    }
}
