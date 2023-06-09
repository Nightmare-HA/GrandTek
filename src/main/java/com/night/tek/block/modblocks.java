package com.night.tek.block;

import com.google.common.base.Supplier;
import com.night.tek.core;
import com.night.tek.block.custom.combine;
import com.night.tek.item.ModItems;
import com.night.tek.item.modItemGroup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modblocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        core.MOD_ID);

        public static final RegistryObject<Block> BLACKDIAMONDORE = registerBlock("blackdiamondore",
                        () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(1)
                                        .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));

        public static final RegistryObject<Block> COMBINE = registerBlock("combine",
                        () -> new combine(AbstractBlock.Properties.create(Material.IRON)));

        public static final RegistryObject<Block> BLACKDIAMONDBLOCK = registerBlock("blackdiamondblock",
                        () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(0)
                                        .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(7f)));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);

                return toReturn;
        }

        private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
                ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                                new Item.Properties().group(modItemGroup.CORE_GROUP)));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
