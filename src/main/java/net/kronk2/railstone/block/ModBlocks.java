package net.kronk2.railstone.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.kronk2.railstone.Railstone;
import net.kronk2.railstone.block.custom.StationBlock;
import net.kronk2.railstone.block.custom.StationBlockEntity;
import net.kronk2.railstone.item.ModItemGroup;
import net.kronk2.railstone.screen.StationScreenHandler;
import net.kronk2.railstone.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.kronk2.railstone.Railstone.MOD_ID;


public class ModBlocks {

    public static final Block STATION_BLOCK = registerBlock("station_block",
            new StationBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(4f).requiresTool().sounds((ModSounds.STATION_SOUNDS))), ModItemGroup.RAILSTONE);

    public static final ScreenHandlerType<StationScreenHandler> STATION_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(new Identifier(MOD_ID, "station"), StationScreenHandler::new);


    public static final BlockEntityType<StationBlockEntity> STATION_ENTITY = registerEntity(FabricBlockEntityTypeBuilder.create(StationBlockEntity::new, STATION_BLOCK).build(), "station");
//    public static final BlockEntityType<StationBlockEntity> STATION_ENTITY = FabricBlockEntityTypeBuilder.create(StationBlockEntity::new, STATION_BLOCK).build();



    private static Block registerBlock(String name, Block block, ItemGroup group)
    {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static BlockEntityType registerEntity(BlockEntityType blockEntity, String name)
    {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, name), blockEntity);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group)
    {
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }


    public static void registerModBlocks() {
        Railstone.LOGGER.info("Registering Modblocks for " + MOD_ID);
    }
}
