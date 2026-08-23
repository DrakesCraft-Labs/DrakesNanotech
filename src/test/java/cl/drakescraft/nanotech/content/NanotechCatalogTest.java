package cl.drakescraft.nanotech.content;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NanotechCatalogTest {
    @Test
    void expandedCatalogHasNinetyThreeUniqueEntries() {
        var ids = new HashSet<String>();
        NanotechCatalog.items().forEach(item -> assertTrue(ids.add(item.id()), "duplicate id " + item.id()));
        NanotechCatalog.machines().forEach(machine -> assertTrue(ids.add(machine.id()), "duplicate id " + machine.id()));
        assertEquals(93, ids.size());
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
