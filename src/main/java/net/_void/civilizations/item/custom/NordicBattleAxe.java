package net._void.civilizations.item.custom;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterials;

public class NordicBattleAxe extends AxeItem {
    public NordicBattleAxe(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.NETHERITE, attackDamage, attackSpeed, settings);
    }
}
