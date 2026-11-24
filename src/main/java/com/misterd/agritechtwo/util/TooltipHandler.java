package com.misterd.agritechtwo.util;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.datamap.ATDataMaps;
import com.misterd.agritechtwo.datamap.FertilizerData;
import com.misterd.agritechtwo.datamap.SoilModifierData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AgritechTwo.MODID, value = Dist.CLIENT)
public class TooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        SoilModifierData soilData = ATDataMaps.getSoilModifier(event.getItemStack().getItem());
        FertilizerData fertData = ATDataMaps.getFertilizer(event.getItemStack().getItem());

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