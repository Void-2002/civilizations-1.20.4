package net._void.civilizations.block.entity;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<TradingStationBlockEntity> TRADING_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Civilizations.MOD_ID, "trading_station_be"),
                    FabricBlockEntityTypeBuilder.create(TradingStationBlockEntity::new,
                            ModBlocks.TRADING_STATION).build());
    public static final BlockEntityType<TombstoneBlockEntity> TOMBSTONE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Civilizations.MOD_ID, "tombstone_be"),
                    FabricBlockEntityTypeBuilder.create(TombstoneBlockEntity::new,
                            ModBlocks.TOMBSTONE).build());

    public static void registerBlockEntities() {
        Civilizations.LOGGER.info("Registering Block Entities for " + Civilizations.MOD_ID);
    }
}
