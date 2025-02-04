package net._void.civilizations.block.entity;

import net._void.civilizations.item.ModItems;
import net._void.civilizations.screen.TradingStationScreenHandler;
import net._void.civilizations.screen.TradingStationScreen;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net._void.civilizations.screen.TradingStationScreen.TRADE;

public class TradingStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int TRADE_INPUT = 0;
    private static final int TRADE_OUTPUT = 1;
    private static final int QUEST_INPUT = 2;
    private static final int QUEST_OUTPUT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int reputation = 0;

    public TradingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRADING_STATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TradingStationBlockEntity.this.reputation;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TradingStationBlockEntity.this.reputation = value;
                }
            }

            @Override
            public int size() {
                return 1;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Trading Station");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("trading_station.reputation", reputation);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        reputation = nbt.getInt("trading_station.reputation");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new TradingStationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        // Place trade offers

        if(TRADE==1 && isRightItemForTrade(TRADE)) tradeResult(TRADE, world, pos, state);
        else if(TRADE==2 && isRightItemForTrade(TRADE)) tradeResult(TRADE, world, pos, state);
        else if(TRADE==3 && isRightItemForTrade(TRADE)) tradeResult(TRADE, world, pos, state);
        else if(TRADE==4 && isRightItemForTrade(TRADE)) tradeResult(TRADE, world, pos, state);
        else tradeResult(-1, world, pos, state);

        // Place quest offers

        if(reputation==0 && isRightItemForQuest(0)) questResult(0, world, pos, state);
        else if(reputation==10 && isRightItemForQuest(10)) questResult(10, world, pos, state);
        else if(reputation==20 && isRightItemForQuest(20)) questResult(20, world, pos, state);
        else if(reputation==30 && isRightItemForQuest(30)) questResult(30, world, pos, state);
        else if(reputation==40 && isRightItemForQuest(40)) questResult(40, world, pos, state);
        else if(reputation==50 && isRightItemForQuest(50)) questResult(50, world, pos, state);
        else if(reputation==60 && isRightItemForQuest(60)) questResult(60, world, pos, state);
        else if(reputation==70 && isRightItemForQuest(70)) questResult(70, world, pos, state);
        else if(reputation==80 && isRightItemForQuest(80)) questResult(80, world, pos, state);
        else if(reputation>=90 && isRightItemForQuest(90)) questResult(90, world, pos, state);
        else questResult(-1, world, pos, state);

    }

    private boolean isRightItemForTrade(int trade){
        boolean bl = false;
        switch(trade){
            case 1 -> bl = getStack(TRADE_INPUT).getItem() == Items.HAY_BLOCK;
            case 2 -> bl = getStack(TRADE_INPUT).getItem() == Items.CLAY;
            case 3 -> bl = getStack(TRADE_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 4 -> bl = getStack(TRADE_INPUT).getItem() == ModItems.EGYPT_COIN;
        }
        return bl;
    }

    private void tradeResult(int trade, World world, BlockPos pos, BlockState state){
        switch(trade){
            case 1 -> this.setStack(TRADE_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,1));
            case 2 -> this.setStack(TRADE_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,1));
            case 3 -> this.setStack(TRADE_OUTPUT, new ItemStack(ModItems.BLANK_PAPYRUS,1));
            case 4 -> this.setStack(TRADE_OUTPUT, new ItemStack(Items.GOLD_NUGGET,3));
            case -1 -> this.setStack(TRADE_OUTPUT, ItemStack.EMPTY);
        }
        markDirty(world, pos, state);
    }

    private boolean isRightItemForQuest(int reputation){
        boolean bl = false;
        switch(reputation){
            case 0 -> bl = getStack(QUEST_INPUT).getItem() == Items.HAY_BLOCK && getStack(QUEST_INPUT).getCount() >= 10;
            case 10 -> bl = getStack(QUEST_INPUT).getItem() == Items.HAY_BLOCK;
            case 20 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 30 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 40 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 50 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 60 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 70 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN;
            case 80 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN && getStack(QUEST_INPUT).getCount() >= 64;
            case 90 -> bl = getStack(QUEST_INPUT).getItem() == ModItems.EGYPT_COIN && getStack(QUEST_INPUT).getCount() >= 40;
        }
        return bl;
    }

    private void questResult(int reputation, World world, BlockPos pos, BlockState state){
        switch(reputation){
            case 0 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,10));
            case 10 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,15));
            case 20 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,20));
            case 30 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,25));
            case 40 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,30));
            case 50 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,35));
            case 60 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,40));
            case 70 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_COIN,45));
            case 80 -> this.setStack(QUEST_OUTPUT, new ItemStack(Items.TOTEM_OF_UNDYING,1));
            case 90 -> this.setStack(QUEST_OUTPUT, new ItemStack(ModItems.EGYPT_KEY,1));
            case -1 -> this.setStack(QUEST_OUTPUT, ItemStack.EMPTY);
        }
        markDirty(world, pos, state);
    }
}
