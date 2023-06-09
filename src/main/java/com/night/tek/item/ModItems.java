package com.night.tek.item;

import com.night.tek.core;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, core.MOD_ID);

        public static final RegistryObject<Item> BLACKDIAMOND = ITEMS.register("blackdiamond",
                        () -> new Item(new Item.Properties().group(modItemGroup.CORE_GROUP)));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
