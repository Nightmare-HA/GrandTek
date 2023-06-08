package com.night.tek.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class modItemGroup {
    public static final ItemGroup CORE_GROUP = new ItemGroup("grandcore") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CRYSTAL.get());
        }
    };
}
