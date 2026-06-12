package com.misterd.agritechtwo.client;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.datamap.ATDataMaps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = AgritechTwo.MODID, value = Dist.CLIENT)
public class SoilTooltipHandler {

    private static final boolean ATE_LOADED = ModList.get().isLoaded("agritechevolved");

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        var soilData = event.getItemStack().getItem().builtInRegistryHolder().getData(ATDataMaps.SOIL_MODIFIERS);
        var fertData = event.getItemStack().getItem().builtInRegistryHolder().getData(ATDataMaps.FERTILIZERS);

        if (soilData == null && fertData == null) return;

        if (soilData != null) {
            event.getToolTip().add(
                    Component.translatable("tooltip.agritechtwo.soil_type")
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
            event.getToolTip().add(
                    Component.translatable("tooltip.agritechtwo.soil_growth_modifier",
                                    String.format("%.2fx", soilData.growthModifier()))
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
        }

        if (fertData != null) {
            event.getToolTip().add(
                    Component.translatable("tooltip.agritechtwo.fertilizer_type")
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
            event.getToolTip().add(
                    Component.translatable("tooltip.agritechtwo.fertilizer_speed",
                                    String.format("%.2fx", fertData.speedMultiplier()))
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
            event.getToolTip().add(
                    Component.translatable("tooltip.agritechtwo.fertilizer_yield",
                                    String.format("%.2fx", fertData.yieldMultiplier()))
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
        }
    }
}