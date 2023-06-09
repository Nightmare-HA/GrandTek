package com.night.tek.block.custom;

import javax.annotation.Nullable;

import com.night.tek.container.CombineContainer;
import com.night.tek.tileentity.CombineTile;
import com.night.tek.tileentity.ModTileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class combine extends Block {
    public combine(net.minecraft.block.AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit) {

        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (!player.isCrouching()) {
                if (tileEntity instanceof CombineTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our Container Provider Is Missing");
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {

            @Override
            @Nullable
            public Container createMenu(int i, PlayerInventory playerInventory,
                    PlayerEntity playerEntity) {
                return new CombineContainer(i, worldIn, pos, playerInventory, playerEntity);
            }

            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("block.tek.combine");
            }

        };
    }

    @Override
    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.COMBINE_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public static class Properties {
        public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 2);

    }
}
