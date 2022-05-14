package net.kronk2.railstone.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kronk2.railstone.Railstone;
import net.kronk2.railstone.block.custom.StationBlock;
import net.kronk2.railstone.item.ModItemGroup;
import net.kronk2.railstone.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.kronk2.railstone.Railstone.MOD_ID;


public class ModBlocks {

    public static final Block STATION_BLOCK = registerBlock("station_block",
            new StationBlock(FabricBlockSettings
                    .of(Material.METAL)
                    .nonOpaque()
                    .strength(4f)
                    .requiresTool()),
//                    .sounds((ModSounds.STATION_SOUNDS))),
            ModItemGroup.RAILSTONE,
            "tooltip.railstone.station_block");

    private static Block registerBlock(String name, Block block, ItemGroup group)
    {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup group, String tooltipKey)
    {
        registerBlockItem(name, block, group, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group)
    {
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey)
    {
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)){
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(new TranslatableText(tooltipKey));
                    }
                });
    }


    public static void registerModBlocks() {
        Railstone.LOGGER.info("Registering Modblocks for " + MOD_ID);
    }
}
