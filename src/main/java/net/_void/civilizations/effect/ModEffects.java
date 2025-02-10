package net._void.civilizations.effect;

import net._void.civilizations.Civilizations;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> CURSE_OF_RA = registerStatusEffect("curse_of_ra",
            new CurseOfRaEffect(StatusEffectCategory.NEUTRAL, 0xFFD04F));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Civilizations.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Civilizations.LOGGER.info("Registering Mod Effects for " + Civilizations.MOD_ID);
    }
}
