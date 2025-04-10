package net._void.civilizations.item.custom;

import net._void.civilizations.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MercuryBoots extends ArmorItem {
    private int timer = 0;

    public MercuryBoots(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player){
                if(hasCorrectBootsOn(ModArmorMaterials.MERCURY, player)){
                    player.getAbilities().allowFlying = true;
                    player.getAbilities().setFlySpeed(0.03F);
                    player.sendAbilitiesUpdate();
                }else{
                    player.getAbilities().setFlySpeed(0.05F);
                    if (!player.isCreative() && !player.isSpectator()) {
                        player.getAbilities().allowFlying = false;
                        player.getAbilities().flying = false;
                    }
                    player.sendAbilitiesUpdate();
                }
                if(player.getAbilities().flying){
                    timer++;
                }
                if(timer == 360){
                    timer = 0;
                    (player.getInventory().getArmorStack(0)).damage(1, player,
                            playerEntity -> playerEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasCorrectBootsOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        return !player.getInventory().getArmorStack(0).isEmpty() &&
                ((ArmorItem)player.getInventory().getArmorStack(0).getItem()).getMaterial() == material;
    }
}
