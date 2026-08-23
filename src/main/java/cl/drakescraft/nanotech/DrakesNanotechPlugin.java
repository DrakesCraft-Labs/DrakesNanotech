package cl.drakescraft.nanotech;

import cl.drakescraft.nanotech.content.NanotechContent;
import cl.drakescraft.nanotech.gameplay.CosmicExposureListener;
import cl.drakescraft.nanotech.gameplay.NanotechWeaponListener;
import cl.drakescraft.nanotech.gameplay.HeroSuitListener;
import cl.drakescraft.nanotech.gameplay.GodPrisonFieldService;
import cl.drakescraft.nanotech.protection.ProtectionGate;
import com.github.drakescraft_labs.slimefun4.api.SlimefunAddon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.logging.Level;

/** Boots the addon and owns every scheduled task and registered Slimefun item. */
public final class DrakesNanotechPlugin extends JavaPlugin implements SlimefunAddon {
    private NanotechContent content;
    private GodPrisonFieldService godPrisonFields;

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
            content = new NanotechContent(this);
            content.registerAll();
            ProtectionGate protectionGate = new ProtectionGate(this);
            godPrisonFields = new GodPrisonFieldService(this, content, protectionGate);
            godPrisonFields.runTaskTimer(this, 1L, 1L);
            getServer().getPluginManager().registerEvents(godPrisonFields, this);
            getServer().getPluginManager().registerEvents(new NanotechWeaponListener(this, content, protectionGate, godPrisonFields), this);
            HeroSuitListener heroSuits = new HeroSuitListener(this, content);
            getServer().getPluginManager().registerEvents(heroSuits, this);
            getServer().getScheduler().runTaskTimer(this, heroSuits::renderAdvancedArmor, 1L, 2L);
            getServer().getPluginManager().registerEvents(new CosmicExposureListener(this, content), this);
            getLogger().info("DrakesNanotech loaded " + content.itemCount() + " items, " + content.machineCount()
                    + " machines and " + content.multiblockCount() + " documented multiblocks.");
        } catch (RuntimeException | LinkageError error) {
            getLogger().log(Level.SEVERE, "DrakesNanotech failed its safe startup; disabling addon.", error);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override public void onDisable() {
        if (godPrisonFields != null) godPrisonFields.shutdown();
        getServer().getScheduler().cancelTasks(this);
    }
    @Override public @Nonnull JavaPlugin getJavaPlugin() { return this; }
    @Override public String getBugTrackerURL() { return "https://github.com/DrakesCraft-Labs/DrakesNanotech/issues"; }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        sender.sendMessage("§6DrakesNanotech §8· §f" + content.itemCount() + " registered items, §b"
                + content.machineCount() + " machines§f and §d" + content.multiblockCount()
                + " multiblocks§f, ARC weapons, nanotech armor and cosmic containment.");
        sender.sendMessage("§7Open the Slimefun Guide and search §eDrakes Nanotech§7.");
        return true;
    }
}
