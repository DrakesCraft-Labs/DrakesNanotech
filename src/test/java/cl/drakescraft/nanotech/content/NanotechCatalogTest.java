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
        assertTrue(ids.size() >= 151);
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
        }
    }

    @Test
    void allSevenTechnologyBranchesShipContent() {
        for (TechnologyBranch branch : TechnologyBranch.values()) {
            assertTrue(NanotechCatalog.items().stream().anyMatch(item -> item.branch() == branch)
                    || NanotechCatalog.machines().stream().anyMatch(machine -> machine.branch() == branch));
        }
    }
}
