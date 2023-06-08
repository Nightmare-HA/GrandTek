package com.night.tek.block.custom;

import javax.annotation.Nullable;

import com.night.tek.container.FormerContainer;
import com.night.tek.tileentity.FormerTile;
import com.night.tek.tileentity.ModTileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
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

public class FormerBlock extends Block {

    public FormerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit) {

        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (!player.isCrouching()) {
                if (tileEntity instanceof FormerTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
                    ((FormerTile) tileEntity).generate();

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
                // TODO Auto-generated method stub
                return new FormerContainer(i, worldIn, pos, playerInventory, playerEntity);
            }

            @Override
            public ITextComponent getDisplayName() {
                // TODO Auto-generated method stub
                return new TranslationTextComponent("block.tek.former");
            }

        };
    }

    @Override
    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.FORMER_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
