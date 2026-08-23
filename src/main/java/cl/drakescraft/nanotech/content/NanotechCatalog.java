package cl.drakescraft.nanotech.content;

import org.bukkit.Material;

import java.util.List;

/** Declarative launch catalog; adding future chapters does not expand registration logic. */
public final class NanotechCatalog {
    private NanotechCatalog() {}

    public static List<ContentDefinition> items() {
        return List.of(
            d("SALVAGED_SERVO", Material.IRON_NUGGET, "&7Salvaged Servo", TechnologyBranch.SALVAGED, 0, "Recovered precision actuator."),
            d("PALLADIUM_COIL", Material.COPPER_INGOT, "&fPalladium Induction Coil", TechnologyBranch.ARC, 1, "Early ARC magnetic winding."),
            d("ARC_REACTOR_CORE", Material.NETHER_STAR, "&bStabilized ARC Core", TechnologyBranch.ARC, 2, "Compact clean-energy heart."),
            d("PORTABLE_ARC_REACTOR", Material.HEART_OF_THE_SEA, "&bPortable ARC Reactor", TechnologyBranch.ARC, 3, "Wearable energy supply: 40 MJ."),
            d("NEW_ELEMENT_INGOT", Material.NETHERITE_INGOT, "&fNew Element Ingot", TechnologyBranch.ARC, 4, "Vacuum-forged exotic lattice."),
            d("REPULSOR_EMITTER", Material.DIAMOND, "&bRepulsor Emitter", TechnologyBranch.ARC, 2, "Right-click: charged PvE energy ray."),
            d("UNIBEAM_LENS", Material.BEACON, "&fUnibeam Focusing Lens", TechnologyBranch.ARC, 3, "Chest-mounted coherent beam optic."),
            d("MICRO_MISSILE_RACK", Material.FIREWORK_ROCKET, "&cMicro-Missile Rack", TechnologyBranch.ARC, 3, "Controlled creature-targeting payload rack."),
            d("SYNTHETIC_VIBRANIUM_DUST", Material.GUNPOWDER, "&9Synthetic Vibranium Dust", TechnologyBranch.WAKANDAN, 4, "Kinetic resonance precursor."),
            d("KINETIC_WEAVE", Material.BLACK_CARPET, "&5Kinetic Weave", TechnologyBranch.WAKANDAN, 4, "Stores a bounded fraction of impacts."),
            d("NANOCARBON_MATRIX", Material.ECHO_SHARD, "&8Nanocarbon Matrix", TechnologyBranch.NANOTECH, 2, "Programmable matter scaffold."),
            d("PROGRAMMABLE_NANOCELL", Material.PRISMARINE_CRYSTALS, "&cProgrammable Nanocell", TechnologyBranch.NANOTECH, 2, "One billion synchronized assemblers."),
            d("SELF_REPAIRING_WEAVE", Material.STRING, "&6Self-Repairing Weave", TechnologyBranch.NANOTECH, 3, "Consumes nanomass to restore integrity."),
            d("NANOMASS_CANISTER", Material.HONEY_BOTTLE, "&cNanomass Canister", TechnologyBranch.NANOTECH, 3, "Sealed reserve for adaptive equipment."),
            d("QUANTUM_PROCESSOR", Material.RECOVERY_COMPASS, "&dQuantum Processor", TechnologyBranch.NANOTECH, 4, "Coordinates remote suit segments."),
            d("ENERGY_WEAVE_EMITTER", Material.END_CRYSTAL, "&bEnergy Weave Emitter", TechnologyBranch.NANOTECH, 4, "Projects body-bound hexagonal armor."),
            d("MARK_I_HELMET", Material.IRON_HELMET, "&7Mark I Helmet", TechnologyBranch.ARC, 2, "Heavy prototype targeting shell."),
            d("MARK_I_CHESTPLATE", Material.IRON_CHESTPLATE, "&7Mark I Chestplate", TechnologyBranch.ARC, 2, "Armored prototype ARC housing."),
            d("MARK_III_HELMET", Material.GOLDEN_HELMET, "&6Mark III Helmet", TechnologyBranch.ARC, 3, "Flight telemetry and threat display."),
            d("MARK_III_CHESTPLATE", Material.GOLDEN_CHESTPLATE, "&cMark III Chestplate", TechnologyBranch.ARC, 3, "Repulsor flight distribution frame."),
            d("MARK_V_SUITCASE", Material.RED_SHULKER_BOX, "&cMark V Suitcase", TechnologyBranch.NANOTECH, 3, "Emergency low-endurance deployment rig."),
            d("MARK_XLII_CONTROL_NODE", Material.ENDER_EYE, "&cMark XLII Control Node", TechnologyBranch.NANOTECH, 4, "Calls owner-bound modular segments."),
            d("MARK_L_NANOCORE", Material.NETHERITE_CHESTPLATE, "&cMark L Nanocore", TechnologyBranch.NANOTECH, 5, "Forms armor and weapons from nanomass."),
            d("HULKBUSTER_FRAME", Material.NETHERITE_CHESTPLATE, "&4Hulkbuster Frame", TechnologyBranch.GAMMA, 5, "Anchored gamma-containment exoframe."),
            d("RESCUE_BARRIER_NODE", Material.AMETHYST_SHARD, "&dRescue Barrier Node", TechnologyBranch.NANOTECH, 4, "Projects cooperative energy barriers."),
            d("GAMMA_ISOTOPE", Material.LIME_DYE, "&aGamma Isotope", TechnologyBranch.GAMMA, 3, "Boss-derived irradiated precursor."),
            d("BANNER_STABILIZER", Material.TOTEM_OF_UNDYING, "&aBanner Stabilizer", TechnologyBranch.GAMMA, 4, "Controls adaptive gamma mutation."),
            d("ROSS_ABSORBER", Material.SCULK_CATALYST, "&2Ross Energy Absorber", TechnologyBranch.GAMMA, 4, "Captures bounded gamma discharge."),
            d("LATVERIAN_CORE", Material.EMERALD, "&2Latverian Arcane Core", TechnologyBranch.LATVERIAN, 4, "Runic ARC hybrid with unstable resonance."),
            d("DOOMBOT_NEURAL_MATRIX", Material.SKELETON_SKULL, "&2Doombot Neural Matrix", TechnologyBranch.LATVERIAN, 5, "Owner-bound synthetic control matrix."),
            d("COSMIC_FRAGMENT", Material.AMETHYST_CLUSTER, "&dUnresolved Cosmic Fragment", TechnologyBranch.COSMIC, 5, "A boss relic without a stable domain."),
            d("URU_CONTAINMENT_TONGS", Material.NETHERITE_HOE, "&6Uru Containment Tongs", TechnologyBranch.COSMIC, 5, "Required to handle exposed artifacts."),
            d("EMPTY_COSMIC_CAPSULE", Material.GLASS_BOTTLE, "&fEmpty Cosmic Capsule", TechnologyBranch.COSMIC, 5, "Level-V containment vessel."),
            d("POWER_STONE", Material.PURPLE_DYE, "&5Power Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: catastrophic carrier exposure."),
            d("SPACE_STONE", Material.BLUE_DYE, "&9Space Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: spatial displacement risk."),
            d("REALITY_STONE", Material.RED_DYE, "&cReality Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: perceptual collapse risk."),
            d("MIND_STONE", Material.YELLOW_DYE, "&eMind Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: neural overload risk."),
            d("TIME_STONE", Material.LIME_DYE, "&aTime Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: localized stasis risk."),
            d("SOUL_STONE", Material.ORANGE_DYE, "&6Soul Stone", TechnologyBranch.COSMIC, 6, "UNCONTAINED: vitality fracture risk."),
            d("EMPTY_NANOGAUNTLET", Material.NETHERITE_CHESTPLATE, "&8Empty Nanogauntlet", TechnologyBranch.COSMIC, 6, "Six persistent, duplicate-safe artifact sockets."),
            d("EMPTY_GOLDEN_GAUNTLET", Material.GOLDEN_CHESTPLATE, "&6Empty Uru Gauntlet", TechnologyBranch.COSMIC, 6, "Maximum output, severe cooldown platform."),
            d("COSMIC_CIRCUIT", Material.COMPARATOR, "&dCosmic Control Circuit", TechnologyBranch.COSMIC, 5, "Mediates six incompatible domains."),
            d("INFINITY_CONDUIT", Material.END_ROD, "&fInfinity Conduit", TechnologyBranch.COSMIC, 6, "Carries stabilized universal energy."),
            d("CHRONAL_ALLOY", Material.CLOCK, "&3Chronal Alloy", TechnologyBranch.COSMIC, 5, "Remembers bounded positional states."),
            d("TEMPORAL_ANCHOR", Material.LODESTONE, "&3Temporal Anchor", TechnologyBranch.COSMIC, 5, "Safe return point; never stores inventory."),
            d("ANTI_GRAVITON_PROJECTOR", Material.END_CRYSTAL, "&bAnti-Graviton Projector", TechnologyBranch.COSMIC, 5, "Lifts eligible creatures with a visible field."),
            d("SYNTHETHEZOID_MATRIX", Material.NETHER_STAR, "&eSynthezoid Matrix", TechnologyBranch.NANOTECH, 6, "A solar-powered programmable density core."),
            d("SPIDER_WEB_FLUID", Material.COBWEB, "&fSynthetic Web Fluid", TechnologyBranch.NANOTECH, 2, "High-tensile biodegradable restraint polymer."),
            d("WEB_SHOOTER", Material.TRIPWIRE_HOOK, "&fWeb Shooter", TechnologyBranch.NANOTECH, 3, "Restrains creatures with a visible polymer filament."),
            d("SPIDER_SENSE_NODE", Material.SPIDER_EYE, "&cSpider-Sense Node", TechnologyBranch.NANOTECH, 3, "Predictive proximity and projectile warning module."),
            d("CLASSIC_SPIDER_MASK", Material.LEATHER_HELMET, "&cClassic Spider Mask", TechnologyBranch.NANOTECH, 3, "Optical lenses, threat telemetry and toxin filter."),
            d("CLASSIC_SPIDER_SUIT", Material.LEATHER_CHESTPLATE, "&9Classic Spider Suit", TechnologyBranch.NANOTECH, 3, "Agile kinetic weave with wall-assist anchors."),
            d("STEALTH_SPIDER_SUIT", Material.LEATHER_CHESTPLATE, "&8Stealth Spider Suit", TechnologyBranch.NANOTECH, 4, "Low-emission infiltration weave and silent webbing."),
            d("IRON_SPIDER_NANOCORE", Material.NETHERITE_CHESTPLATE, "&cIron Spider Nanocore", TechnologyBranch.NANOTECH, 5, "Adaptive armor reservoir with four visual waldoes."),
            d("SPIDER_WALDO_MODULE", Material.END_ROD, "&6Spider Waldo Module", TechnologyBranch.NANOTECH, 5, "Articulated particle limb for defense and traversal."),
            d("HAWKEYE_COMPOUND_BOW", Material.BOW, "&5Hawkeye Compound Bow", TechnologyBranch.ARC, 3, "Smart bow with cartridge-driven tactical payloads."),
            d("TRICK_ARROW_CARTRIDGE", Material.ARROW, "&dTrick Arrow Cartridge", TechnologyBranch.ARC, 3, "Configurable EMP, cable, sonic and foam arrow base."),
            d("SONIC_ARROW", Material.SPECTRAL_ARROW, "&bSonic Disruption Arrow", TechnologyBranch.ARC, 4, "Visible concussive pulse against creatures."),
            d("EMP_ARROW", Material.TIPPED_ARROW, "&eEMP Arrow", TechnologyBranch.ARC, 4, "Temporarily disrupts authorized machine targets."),
            d("GRAPPLE_ARROW", Material.ARROW, "&fGrapple Arrow", TechnologyBranch.ARC, 3, "Traversal tether with protection-safe landing checks."),
            d("WIDOW_BITE_GAUNTLET", Material.LIGHTNING_ROD, "&eWidow's Bite Gauntlet", TechnologyBranch.ARC, 4, "Creature-only chained electroshock weapon."),
            d("WIDOW_BATON", Material.BLAZE_ROD, "&bElectroshock Baton", TechnologyBranch.ARC, 3, "Close-range stun conductor with visible arcs."),
            d("WIDOW_LINE_LAUNCHER", Material.FISHING_ROD, "&8Widow Line Launcher", TechnologyBranch.ARC, 3, "Silent tactical ascent and extraction line."),
            d("RED_ROOM_CLOAKING_MESH", Material.GRAY_CARPET, "&8Red Room Cloaking Mesh", TechnologyBranch.NANOTECH, 4, "Short-duration optical signature suppression."),
            d("ULTRON_AI_CORE", Material.OMINOUS_TRIAL_KEY, "&cUltron AI Core", TechnologyBranch.NANOTECH, 5, "Sandboxed synthetic intelligence with corruption fuse."),
            d("ULTRON_PRIMARY_SHELL", Material.NETHERITE_CHESTPLATE, "&8Ultron Primary Shell", TechnologyBranch.NANOTECH, 5, "Secondary-adamantium autonomous combat body."),
            d("ULTRON_SENTINEL_POD", Material.HEAVY_CORE, "&cUltron Sentinel Pod", TechnologyBranch.NANOTECH, 5, "Owner-limited temporary drone deployment capsule."),
            d("ULTRON_INFINITY_CORE", Material.END_CRYSTAL, "&5Ultron Infinity Core", TechnologyBranch.COSMIC, 6, "Artificial six-domain convergence intelligence."),
            d("ULTRON_INFINITY_BEAM", Material.END_ROD, "&dUltron Infinity Beam", TechnologyBranch.COSMIC, 6, "Sky-splitting cosmic ray with protected-region lockout."),
            d("SLING_RING", Material.GOLD_NUGGET, "&6Sling Ring", TechnologyBranch.LATVERIAN, 4, "Opens a short safe portal to an unprotected destination."),
            d("MYSTIC_CLOAK", Material.RED_BANNER, "&cMystic Levitation Cloak", TechnologyBranch.LATVERIAN, 4, "Owner-bound levitation and emergency rescue weave."),
            d("MIRROR_DIMENSION_PRISM", Material.PRISMARINE_SHARD, "&dMirror Dimension Prism", TechnologyBranch.LATVERIAN, 5, "Projects reversible geometry without changing blocks."),
            d("CRIMSON_BANDS_RELIC", Material.LEAD, "&cCrimson Bands Relic", TechnologyBranch.LATVERIAN, 5, "Binds creatures; never players or protected NPCs."),
            d("EYE_OF_AGAMOTTO_REPLICA", Material.ENDER_EYE, "&aChronal Eye Replica", TechnologyBranch.COSMIC, 6, "Bounded temporal field without world rollback."),
            d("REALITY_FRACTURE_DEVICE", Material.RESPAWN_ANCHOR, "&5Reality Fracture Device", TechnologyBranch.COSMIC, 6, "Cinematic dimensional rupture; zero block mutation."),
            d("ORBITAL_SKY_LANCE", Material.LIGHTNING_ROD, "&bOrbital Sky Lance", TechnologyBranch.COSMIC, 6, "Calls a column of energy from sky to ground against creatures."),
            d("CELESTIAL_NULLIFIER", Material.BEACON, "&fCelestial Nullifier", TechnologyBranch.COSMIC, 6, "Massive PvE suppression field with hard safety budgets."),
            d("SINGULARITY_WARHEAD", Material.END_CRYSTAL, "&8Singularity Warhead", TechnologyBranch.COSMIC, 6, "Controlled gravitational implosion; visual only for blocks."),
            d("NANITE_DISASSEMBLER", Material.NETHERITE_SWORD, "&cNanite Disassembler", TechnologyBranch.NANOTECH, 6, "Dissolves eligible creatures into bounded ash effects."),
            d("DIMENSIONAL_BREACH_CHARGE", Material.CRYING_OBSIDIAN, "&dDimensional Breach Charge", TechnologyBranch.COSMIC, 6, "Creates a temporary visual scar in unprotected wilderness."),
            d("UNIVERSAL_FORGE_KEY", Material.TRIAL_KEY, "&dUniversal Forge Key", TechnologyBranch.COSMIC, 6, "Proof of mastery across every branch.")
        );
    }

    private static ContentDefinition d(String id, Material material, String name, TechnologyBranch branch, int tier, String description) {
        return new ContentDefinition(id, material, name, branch, tier, description);
    }

    public static List<MachineDefinition> machines() {
        String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ1Y2Y5MmJjNzllYzE5ZjQxMDY0NDFhZmZmZjE0MDZhMTM2NzAxMGRjYWZiMTk3ZGQ5NGNmY2ExYTZkZTBmYyJ9fX0=";
        return List.of(
            m("CARBYNE_PULVERIZER", "Carbyne Pulverizer", TechnologyBranch.SALVAGED, 1, 256, 2_000_000, 16, "Carbon + reinforced alloy", "Carbyne precursor", "Carbon dust", "Industrial I", "Stops when output is full", texture),
            m("MOLECULAR_SYNTHESIZER", "Molecular Synthesizer", TechnologyBranch.NANOTECH, 2, 1024, 25_000_000, 30, "Carbyne precursor + graphene", "Nanocarbon matrix", "Nanite contamination", "Cleanroom II", "Seals chamber and purges batch", texture),
            m("NANOCELL_PROGRAMMER", "Nanocell Programmer", TechnologyBranch.NANOTECH, 2, 2048, 50_000_000, 45, "Nanocarbon + control circuit", "Programmable nanocell x2", "Logic instability", "Cleanroom II", "Quarantines invalid firmware", texture),
            m("NANOFORGE", "Nanoforge", TechnologyBranch.NANOTECH, 3, 4096, 100_000_000, 60, "Nanocells + exotic alloy", "Suit components", "Heat / nanite contamination", "Cleanroom III", "SCRAM; consumes no ingredients", texture),
            m("ARMOR_ASSEMBLY_BENCH", "Armor Assembly Bench", TechnologyBranch.ARC, 3, 2048, 80_000_000, 40, "Armor frame + ARC core + modules", "Owner-bound suit core", "Stored charge", "Ownership lock", "Rejects mixed-generation modules", texture),
            m("PALLADIUM_WINDER", "Palladium Coil Winder", TechnologyBranch.ARC, 1, 512, 5_000_000, 20, "Palladium + copper wire", "Palladium coil", "Metal fumes", "Ventilation I", "Safe idle; no destructive failure", texture),
            m("NEW_ELEMENT_ACCELERATOR", "New Element Accelerator", TechnologyBranch.ARC, 4, 8192, 250_000_000, 120, "Exotic lattice + singularity", "New element ingot", "Extreme heat", "Containment IV", "ARC SCRAM and visual EMP", texture),
            m("GAMMA_CYCLOTRON", "Gamma Cyclotron", TechnologyBranch.GAMMA, 3, 4096, 120_000_000, 75, "Boss isotope + shielding", "Charged gamma isotope", "Ionizing radiation", "Gamma III", "Drops field and seals isotope", texture),
            m("BANNER_STABILIZATION_CHAMBER", "Banner Stabilization Chamber", TechnologyBranch.GAMMA, 4, 6144, 180_000_000, 90, "Gamma isotope + genetic catalyst", "Controlled mutation serum", "Mutation / radiation", "Gamma IV", "Neutralizes batch into inert waste", texture),
            m("TECHNO_ARCANE_FORGE", "Techno-Arcane Forge", TechnologyBranch.LATVERIAN, 4, 8192, 300_000_000, 100, "ARC component + arcane essence", "Latverian component", "Runic feedback", "Ward IV", "Grounds resonance; never breaks blocks", texture),
            m("COSMIC_SPECTROMETER", "Cosmic Fragment Spectrometer", TechnologyBranch.COSMIC, 5, 16384, 500_000_000, 180, "Unresolved fragment", "Domain signature", "Cosmic exposure", "Containment V", "Ejects sealed capsule", texture),
            m("SINGULARITY_GROWTH_CHAMBER", "Singularity Growth Chamber", TechnologyBranch.COSMIC, 6, 32768, 2_000_000_000, 300, "Domain signature + boss core", "Contained cosmic replica", "Reality collapse", "Containment VI", "Emergency stasis; zero block damage", texture)
        );
    }

    private static MachineDefinition m(String id, String name, TechnologyBranch branch, int tier, int ept,
                                       int buffer, int seconds, String input, String output, String hazard,
                                       String containment, String shutdown, String texture) {
        return new MachineDefinition(id, name, branch, tier, ept, buffer, seconds, input, output, hazard, containment, shutdown, texture);
    }
}
