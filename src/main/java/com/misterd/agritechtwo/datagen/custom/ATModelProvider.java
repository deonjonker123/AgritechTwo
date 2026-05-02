package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ATModelProvider extends ModelProvider {
    public ATModelProvider(PackOutput output) {
        super(output, AgritechTwo.MODID);
    }

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING =
            PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                    .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                    .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                    .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                    .select(Direction.NORTH, BlockModelGenerators.NOP);



    @Override
    protected void registerModels(BlockModelGenerators g, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ATItems.CLOCHE.get(), ModelTemplates.FLAT_ITEM);
        g.createNonTemplateModelBlock(ATBlocks.ACACIA_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.BAMBOO_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.BIRCH_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.CHERRY_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.CRIMSON_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.DARK_OAK_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.JUNGLE_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.MANGROVE_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.OAK_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.PALE_OAK_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.SPRUCE_PLANTER.get());
        g.createNonTemplateModelBlock(ATBlocks.WARPED_PLANTER.get());

        g.createNonTemplateModelBlock(ATBlocks.ACACIA_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.BAMBOO_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.BIRCH_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.CHERRY_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.CRIMSON_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.DARK_OAK_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.JUNGLE_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.MANGROVE_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.OAK_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.PALE_OAK_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.SPRUCE_RAISED_BED.get());
        g.createNonTemplateModelBlock(ATBlocks.WARPED_RAISED_BED.get());

        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.ACACIA_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/acacia_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.BAMBOO_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/bamboo_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.BIRCH_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/birch_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.CHERRY_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/cherry_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.CRIMSON_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/crimson_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.DARK_OAK_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/dark_oak_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.JUNGLE_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/jungle_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.MANGROVE_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/mangrove_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.OAK_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/oak_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.PALE_OAK_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/pale_oak_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.SPRUCE_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/spruce_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
        g.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ATBlocks.WARPED_CRATE.get(),
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/warped_crate")))
                .with(ROTATION_HORIZONTAL_FACING));
    }
}
