package com.misterd.agritechtwo.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class ClocheItem extends Item {
    public ClocheItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                TooltipDisplay display,
                                java.util.function.Consumer<Component> consumer,
                                TooltipFlag flag) {

        consumer.accept(Component.translatable("item.agritechtwo.cloche.tooltip.line1"));
        consumer.accept(Component.translatable("item.agritechtwo.cloche.tooltip.line2"));
        consumer.accept(Component.translatable("item.agritechtwo.cloche.tooltip.line3"));

        super.appendHoverText(stack, context, display, consumer, flag);
    }
}