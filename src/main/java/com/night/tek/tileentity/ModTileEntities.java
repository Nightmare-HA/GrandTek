package com.night.tek.tileentity;

import com.night.tek.core;
import com.night.tek.block.modblocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, core.MOD_ID);

    public static RegistryObject<TileEntityType<FormerTile>> FORMER_TILE = TILE_ENTITIES.register("formertile",
            () -> TileEntityType.Builder.create(
                    FormerTile::new, modblocks.FORMER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);

    }
}