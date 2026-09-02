package cl.drakescraft.nanotech.content;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NanotechCatalogTest {
    @Test
    void expandedCatalogHasUniqueEntries() {
        var ids = new HashSet<String>();
        NanotechCatalog.items().forEach(item -> assertTrue(ids.add(item.id()), "duplicate id " + item.id()));
        NanotechCatalog.machines().forEach(machine -> assertTrue(ids.add(machine.id()), "duplicate id " + machine.id()));
        assertTrue(ids.size() >= 158);
        assertTrue(ids.contains("ENCHANT_ARC_AEGIS"));
        assertTrue(ids.contains("ENCHANT_NANITE_REGENESIS"));
        assertTrue(ids.contains("ENCHANT_ZERO_POINT_ANCHOR"));
        assertTrue(ids.contains("STARK_STORAGE_VAULT"));
    }

    @Test
    void everyMultiblockHasACompleteFieldSpecification() {
        assertTrue(NanotechCatalog.multiblocks().size() >= 8);
        for (MultiblockDefinition multiblock : NanotechCatalog.multiblocks()) {
            assertTrue(multiblock.tier() >= 3);
            assertTrue(!multiblock.footprint().isBlank());
            assertTrue(!multiblock.controller().isBlank());
            assertTrue(!multiblock.structure().isBlank());
            assertTrue(!multiblock.power().isBlank());
            assertTrue(!multiblock.purpose().isBlank());
            assertTrue(!multiblock.safety().isBlank());
        }
    }

    @Test
    void everyMachineHasOperationalDocumentationAndTexture() {
        var itemIds = NanotechCatalog.items().stream().map(ContentDefinition::id).collect(java.util.stream.Collectors.toSet());
        for (MachineDefinition machine : NanotechCatalog.machines()) {
            assertTrue(machine.energyPerTick() > 0);
            assertTrue(machine.buffer() >= machine.energyPerTick());
            assertTrue(machine.seconds() > 0);
            assertTrue(machine.input() != null && !machine.input().isBlank());
            assertTrue(machine.output() != null && !machine.output().isBlank());
            assertTrue(machine.hazard() != null && !machine.hazard().isBlank());
            assertTrue(machine.containment() != null && !machine.containment().isBlank());
            assertTrue(machine.shutdown() != null && !machine.shutdown().isBlank());
            assertTrue(machine.textureValue() != null && machine.textureValue().length() > 100);
            if (!machine.id().equals("UNIVERSAL_AUTOMATION_AI")) {
                String[] process = NanotechContent.machineProcess(machine.id());
                assertTrue(itemIds.contains(process[0]), "missing machine input " + process[0]);
                assertTrue(itemIds.contains(process[1]), "missing machine output " + process[1]);
            }
        }
        assertTrue(NanotechCatalog.machines().stream().anyMatch(machine -> machine.id().equals("UNIVERSAL_AUTOMATION_AI")));
    }

    @Test
    void allSevenTechnologyBranchesShipContent() {
        for (TechnologyBranch branch : TechnologyBranch.values()) {
            assertTrue(NanotechCatalog.items().stream().anyMatch(item -> item.branch() == branch)
                    || NanotechCatalog.machines().stream().anyMatch(machine -> machine.branch() == branch));
        }
    }

    @Test
    void vanillaKeyLookalikesStayDocumented() {
        var keyMaterials = java.util.Set.of(org.bukkit.Material.TRIAL_KEY, org.bukkit.Material.OMINOUS_TRIAL_KEY);
        var lookalikes = NanotechCatalog.items().stream()
                .filter(item -> keyMaterials.contains(item.material()))
                .map(ContentDefinition::id)
                .collect(java.util.stream.Collectors.toSet());
        // NanotechWeaponListener.onVaultInsert exists because these items can be fed to a vanilla
        // vault; if the catalog stops using key materials the guard can go with them.
        assertEquals(java.util.Set.of("UNIVERSAL_FORGE_KEY", "ULTRON_AI_CORE"), lookalikes);
    }
}
