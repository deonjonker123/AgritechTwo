package com.misterd.agritechtwo.util;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ATTags {
    public static class Items {
        public static final TagKey<Item> FARMLAND_SOILS = createTag("farmland_soils");
        public static final TagKey<Item> DIRT_SOILS = createTag("dirt_soils");
        public static final TagKey<Item> TREE_SOILS = createTag("tree_soils");
        public static final TagKey<Item> SAND_SOILS = createTag("sand_soils");
        public static final TagKey<Item> SOUL_SAND_SOILS = createTag("soul_sand_soils");
        public static final TagKey<Item> MOSS_SOILS = createTag("moss_soils");
        public static final TagKey<Item> WATER_SOILS = createTag("water_soils");
        public static final TagKey<Item> MUSHROOM_SOILS = createTag("mushroom_soils");
        public static final TagKey<Item> NETHER_SOILS = createTag("nether_soils");
        public static final TagKey<Item> STONE_SOILS = createTag("stone_soils");
        public static final TagKey<Item> END_SOILS = createTag("end_soils");
        public static final TagKey<Item> JUNGLE_SOILS = createTag("jungle_soils");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath("agritechtwo", name));
        }
    }
}
