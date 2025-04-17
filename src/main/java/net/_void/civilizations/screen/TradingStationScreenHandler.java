package net._void.civilizations.screen;

import net._void.civilizations.block.entity.TradingStationBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class TradingStationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final TradingStationBlockEntity blockEntity;

    public TradingStationScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(1));
    }

    public TradingStationScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.TRADING_STATION_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 4);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((TradingStationBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 10, 64));
        this.addSlot(new Slot(inventory, 1, 64,  64){
            public boolean canInsert(ItemStack stack) {
                return false;
            }
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
                transaction(0, "trade");
            }
        });
        this.addSlot(new Slot(inventory, 2, 97, 64));
        this.addSlot(new Slot(inventory, 3, 151, 64){
            public boolean canInsert(ItemStack stack) {
                return false;
            }
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
                transaction(2, "quest");
            }
        });


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public int getReputation(){
        return propertyDelegate.get(0);
    }

    public void setReputation(int value){
        propertyDelegate.set(0,value);
    }

    public void transaction(int paymentIndex, String type){
        if (type == "trade") getSlot(paymentIndex).getStack().setCount(getSlot(paymentIndex).getStack().getCount()-1);
        if (type == "quest"){
            int paymentAmount = 0;
            switch(getReputation()){
                case 0 -> paymentAmount = 10;
                case 10 -> paymentAmount = 64;
                case 20 -> paymentAmount = 32;
                case 30 -> paymentAmount = 1;
                case 40 -> paymentAmount = 32;
                case 50 -> paymentAmount = 32;
                case 60 -> paymentAmount = 40;
                case 70 -> paymentAmount = 16;
                case 80 -> paymentAmount = 4;
            }
            if(getReputation() >= 90) paymentAmount = 40;
            getSlot(paymentIndex).getStack().setCount(getSlot(paymentIndex).getStack().getCount() - paymentAmount);
            setReputation(getReputation()+10);
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if(invSlot == 1 && getSlot(1).hasStack()) transaction(0, "trade");
        if(invSlot == 3 && getSlot(3).hasStack()) transaction(2, "quest");

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
