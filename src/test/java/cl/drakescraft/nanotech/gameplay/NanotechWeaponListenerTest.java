package cl.drakescraft.nanotech.gameplay;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NanotechWeaponListenerTest {

    @Test
    void recognizesOnlyOwnedAbilityItems() {
        assertTrue(NanotechWeaponListener.isAbilityItem("REPULSOR_EMITTER"));
        assertTrue(NanotechWeaponListener.isAbilityItem("INFINITY_GAUNTLET"));
        assertTrue(NanotechWeaponListener.isAbilityItem("SINGULARITY_WARHEAD"));

        assertFalse(NanotechWeaponListener.isAbilityItem("NTW_GRABBER"));
        assertFalse(NanotechWeaponListener.isAbilityItem("URANIUM_EXTRACTOR"));
        assertFalse(NanotechWeaponListener.isAbilityItem("SUPREME_BASIC_VENTUS_GENERATOR"));
        assertFalse(NanotechWeaponListener.isAbilityItem("NANOFORGE"));
        assertFalse(NanotechWeaponListener.isAbilityItem(""));
    }

    @Test
    void vaultRejectionNamesTheItemAndKeepsIt() {
        String message = NanotechWeaponListener.vaultRejectionMessage("UNIVERSAL_FORGE_KEY");

        assertTrue(message.contains("UNIVERSAL_FORGE_KEY"));
        assertTrue(message.contains("not a trial key"));
        assertTrue(message.contains("stays in your inventory"));
        assertTrue(message.startsWith("§6DrakesNanotech"));
    }
}
