package com.night.tek.item;

import com.night.tek.core;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, core.MOD_ID);

        public static final RegistryObject<Item> CRYSTAL = ITEMS.register("mysticalcrystal",
                        () -> new Item(new Item.Properties().group(modItemGroup.CORE_GROUP)));

        public static final RegistryObject<Item> LENS = ITEMS.register("lens",
                        () -> new Item(new Item.Properties().group(modItemGroup.CORE_GROUP)));

        public static final RegistryObject<Item> SCEPTOR = ITEMS.register("sceptor",
                        () -> new Item(new Item.Properties().group(modItemGroup.CORE_GROUP)));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
