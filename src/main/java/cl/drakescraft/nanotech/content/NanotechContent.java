package cl.drakescraft.nanotech.content;

import cl.drakescraft.nanotech.DrakesNanotechPlugin;
import com.github.drakescraft_labs.slimefun4.api.items.ItemGroup;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItem;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItemStack;
import com.github.drakescraft_labs.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Registers the declarative catalog into the real Drakes Slimefun registry. */
public final class NanotechContent {
    private final DrakesNanotechPlugin plugin;
    private final Map<String, SlimefunItemStack> items = new HashMap<>();
    private final ItemGroup group;

    public NanotechContent(DrakesNanotechPlugin plugin) {
        this.plugin = plugin;
        ItemStack icon = new ItemStack(Material.HEART_OF_THE_SEA);
        var meta = icon.getItemMeta();
        meta.setDisplayName("§b§lDrakes Nanotech");
        meta.setLore(java.util.List.of("§7Programmable matter, ARC engineering,", "§7gamma science and cosmic containment."));
        icon.setItemMeta(meta);
        group = new ItemGroup(new NamespacedKey(plugin, "nanotech"), icon, 6);
    }

    /** Registers components first so later recipes can safely reference them. */
    public void registerAll() {
        for (ContentDefinition definition : NanotechCatalog.items()) registerItem(definition);
        for (MachineDefinition definition : NanotechCatalog.machines()) registerMachine(definition);
    }

    private void registerItem(ContentDefinition definition) {
        SlimefunItemStack stack = new SlimefunItemStack(definition.id(), definition.material(), definition.name(),
                "", "&8Branch: &f" + title(definition.branch()), "&8Technology Tier: &e" + definition.tier(),
                "&7" + definition.description(), "", "&4Ultra-endgame component");
        items.put(definition.id(), stack);
        ItemStack[] recipe = expensiveRecipe(definition);
        if (definition.id().matches("STARK_(STORAGE|NANO|ARMORY)_VAULT")) {
            new StarkStorageBlock(group, stack, recipe).register(plugin);
        } else {
            new SlimefunItem(group, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(plugin);
        }
    }

    private void registerMachine(MachineDefinition definition) {
        SlimefunItemStack stack = new SlimefunItemStack(definition.id(), definition.textureValue(), "&b" + definition.name(),
                "", "&8Machine Tier: &e" + definition.tier() + " &8| Branch: &f" + title(definition.branch()),
                "&7Power: &b" + definition.energyPerTick() + " J/t &8| Buffer: &b" + formatEnergy(definition.buffer()),
                "&7Process: &f" + definition.seconds() + "s &8| Batch: &f1", "&7Input: &f" + definition.input(),
                "&7Output: &f" + definition.output(), "&6Hazard: &f" + definition.hazard(),
                "&6Containment: &f" + definition.containment(), "&cShutdown: &f" + definition.shutdown(),
                "", "&aSingle-block machine &8· &7Owner protections apply");
        items.put(definition.id(), stack);
        if (definition.id().equals("UNIVERSAL_AUTOMATION_AI")) {
            new UniversalAutomationAI(plugin, group, stack, machineRecipe(definition)).register(plugin);
            return;
        }
        String[] process = machineProcess(definition.id());
        ItemStack output = items.get(process[1]).clone();
        if (process.length > 2) output.setAmount(Integer.parseInt(process[2]));
        new NanotechPoweredMachine(group, stack, machineRecipe(definition), definition, process[0], output).register(plugin);
    }

    private ItemStack[] expensiveRecipe(ContentDefinition definition) {
        ItemStack center = progressionCore(definition);
        Material edge = switch (definition.branch()) {
            case ARC -> Material.COPPER_INGOT;
            case NANOTECH -> Material.ECHO_SHARD;
            case GAMMA -> Material.LIME_DYE;
            case WAKANDAN -> Material.SCULK;
            case LATVERIAN -> Material.EMERALD;
            case COSMIC -> Material.AMETHYST_SHARD;
            default -> Material.IRON_INGOT;
        };
        return new ItemStack[]{new ItemStack(edge), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(edge),
                new ItemStack(Material.QUARTZ), center, new ItemStack(Material.QUARTZ),
                new ItemStack(edge), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(edge)};
    }

    /** Enforces branch prerequisites so tier labels represent progression, not decorative rarity. */
    private ItemStack progressionCore(ContentDefinition definition) {
        String prerequisite = switch (definition.branch()) {
            case ARC -> definition.id().equals("PALLADIUM_COIL") ? null
                    : items.containsKey("ARC_REACTOR_CORE") ? "ARC_REACTOR_CORE" : "PALLADIUM_COIL";
            case NANOTECH -> items.containsKey("NANOCARBON_MATRIX") ? "NANOCARBON_MATRIX" : "ARC_REACTOR_CORE";
            case GAMMA -> items.containsKey("GAMMA_ISOTOPE") ? "GAMMA_ISOTOPE" : "PORTABLE_ARC_REACTOR";
            case WAKANDAN -> "PORTABLE_ARC_REACTOR";
            case LATVERIAN -> "ARC_REACTOR_CORE";
            case COSMIC -> items.containsKey("COSMIC_FRAGMENT") ? "COSMIC_FRAGMENT" : "LATVERIAN_CORE";
            case SALVAGED -> null;
        };
        SlimefunItemStack registered = prerequisite == null ? null : items.get(prerequisite);
        if (registered != null) return registered.clone();
        return new ItemStack(definition.tier() >= 5 ? Material.NETHER_STAR
                : definition.tier() >= 3 ? Material.NETHERITE_INGOT : Material.DIAMOND);
    }

    private ItemStack[] machineRecipe(MachineDefinition definition) {
        ItemStack core = definition.tier() >= 5 && items.containsKey("COSMIC_CIRCUIT")
                ? items.get("COSMIC_CIRCUIT").clone()
                : items.get("ARC_REACTOR_CORE").clone();
        return new ItemStack[]{new ItemStack(Material.NETHERITE_INGOT), new ItemStack(Material.HEAVY_CORE), new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.COMPARATOR), core, new ItemStack(Material.COMPARATOR),
                new ItemStack(Material.OBSIDIAN), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.OBSIDIAN)};
    }

    /** Connects every machine to one registered input and output; startup tests audit these IDs. */
    static String[] machineProcess(String machineId) {
        return switch (machineId) {
            case "CARBYNE_PULVERIZER" -> new String[]{"SALVAGED_SERVO", "NANOCARBON_MATRIX"};
            case "MOLECULAR_SYNTHESIZER" -> new String[]{"NANOCARBON_MATRIX", "PROGRAMMABLE_NANOCELL"};
            case "NANOCELL_PROGRAMMER" -> new String[]{"NANOCARBON_MATRIX", "PROGRAMMABLE_NANOCELL", "2"};
            case "NANOFORGE" -> new String[]{"PROGRAMMABLE_NANOCELL", "NANO_SERVO_CLUSTER"};
            case "ARMOR_ASSEMBLY_BENCH" -> new String[]{"NANO_SERVO_CLUSTER", "MARK_L_NANOCORE"};
            case "PALLADIUM_WINDER" -> new String[]{"SALVAGED_SERVO", "PALLADIUM_COIL"};
            case "NEW_ELEMENT_ACCELERATOR" -> new String[]{"ARC_REACTOR_CORE", "NEW_ELEMENT_INGOT"};
            case "GAMMA_CYCLOTRON" -> new String[]{"GAMMA_ISOTOPE", "ROSS_ABSORBER"};
            case "BANNER_STABILIZATION_CHAMBER" -> new String[]{"GAMMA_ISOTOPE", "BANNER_STABILIZER"};
            case "TECHNO_ARCANE_FORGE" -> new String[]{"ARC_REACTOR_CORE", "LATVERIAN_CORE"};
            case "COSMIC_SPECTROMETER" -> new String[]{"COSMIC_FRAGMENT", "COSMIC_CIRCUIT"};
            case "SINGULARITY_GROWTH_CHAMBER" -> new String[]{"COSMIC_CIRCUIT", "POWER_STONE"};
            case "STELLAR_SWARM_FABRICATOR" -> new String[]{"PROGRAMMABLE_NANOCELL", "DYSON_SWARM_SEGMENT"};
            case "GRAVITON_FIELD_REGULATOR" -> new String[]{"GRAVITON_CONTAINMENT_COIL", "GOD_PRISON_FIELD_CORE"};
            default -> throw new IllegalArgumentException("Missing machine process: " + machineId);
        };
    }

    public boolean is(ItemStack stack, String id) {
        SlimefunItem item = SlimefunItem.getByItem(stack);
        return item != null && item.getId().equals(id);
    }

    public boolean isBareStone(ItemStack stack) {
        SlimefunItem item = SlimefunItem.getByItem(stack);
        return item != null && item.getId().matches("(POWER|SPACE|REALITY|MIND|TIME|SOUL)_STONE");
    }

    public String idOf(ItemStack stack) {
        SlimefunItem item = SlimefunItem.getByItem(stack);
        return item == null ? "" : item.getId();
    }

    public int itemCount() { return NanotechCatalog.items().size() + NanotechCatalog.machines().size(); }
    public int machineCount() { return NanotechCatalog.machines().size(); }
    public int multiblockCount() { return NanotechCatalog.multiblocks().size(); }
    private static String title(TechnologyBranch branch) { return branch.name().toLowerCase(Locale.ROOT).replace('_', ' '); }
    private static String formatEnergy(int energy) { return energy >= 1_000_000_000 ? energy / 1_000_000_000 + " GJ" : energy / 1_000_000 + " MJ"; }
}
