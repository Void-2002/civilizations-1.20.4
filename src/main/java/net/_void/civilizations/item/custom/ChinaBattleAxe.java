package net._void.civilizations.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterials;

public class ChinaBattleAxe extends AxeItem {
    public ChinaBattleAxe(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.IRON, attackDamage, attackSpeed, settings);
    }
}
