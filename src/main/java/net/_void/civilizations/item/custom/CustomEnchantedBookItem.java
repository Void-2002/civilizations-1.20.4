package net._void.civilizations.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.item.EnchantedBookItem.forEnchantment;

public class CustomEnchantedBookItem extends Item {
    private String type = "";

    public CustomEnchantedBookItem(String type, Settings settings) {
        super(settings);
        this.type = type;
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ItemStack newStack = null;
        switch(type){
            case "sharpness" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 4));
            case "power" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.POWER, 3));
            case "fire_aspect" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.FIRE_ASPECT, 2));
            case "unbreaking" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3));
            case "protection" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.PROTECTION, 3));
            case "feather_falling" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.FEATHER_FALLING, 3));
            case "thorns" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.THORNS, 2));
            case "frost_walker" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.FROST_WALKER, 2));
            case "depth_strider" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.DEPTH_STRIDER, 3));
            case "efficiency" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 4));
            case "fortune" -> newStack = forEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, 2));
        }
        stack.decrement(1);
        if(entity instanceof PlayerEntity player){
            player.getInventory().setStack(slot, newStack);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        switch(type){
            case "sharpness" -> tooltip.add(Text.translatable("tooltip.civilizations.sharpness_book"));
            case "power" -> tooltip.add(Text.translatable("tooltip.civilizations.power_book"));
            case "fire_aspect" -> tooltip.add(Text.translatable("tooltip.civilizations.fire_aspect_book"));
            case "unbreaking" -> tooltip.add(Text.translatable("tooltip.civilizations.unbreaking_book"));
            case "protection" -> tooltip.add(Text.translatable("tooltip.civilizations.protection_book"));
            case "feather_falling" -> tooltip.add(Text.translatable("tooltip.civilizations.feather_falling_book"));
            case "thorns" -> tooltip.add(Text.translatable("tooltip.civilizations.thorns_book"));
            case "frost_walker" -> tooltip.add(Text.translatable("tooltip.civilizations.frost_walker_book"));
            case "depth_strider" -> tooltip.add(Text.translatable("tooltip.civilizations.depth_strider_book"));
            case "efficiency" -> tooltip.add(Text.translatable("tooltip.civilizations.efficiency_book"));
            case "fortune" -> tooltip.add(Text.translatable("tooltip.civilizations.fortune_book"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
