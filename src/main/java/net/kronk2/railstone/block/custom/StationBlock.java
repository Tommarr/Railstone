package net.kronk2.railstone.block.custom;

import net.kronk2.railstone.Railstone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationBlock extends BlockWithEntity {

    public static final Logger LOGGER = LoggerFactory.getLogger(Railstone.MOD_ID);

    public StationBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockPos destinationBlockPos = checkNeighborBlocks(pos, null, world);
        if(!world.isClient())
        {

        }
        else{
            if(destinationBlockPos != null)
            {
                playParticle(world, pos, ParticleTypes.HAPPY_VILLAGER);
            }
            else{
                playParticle(world, pos, ParticleTypes.SMOKE);
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        // called 4 times on right click:
        // 2 Times on server (for each hand)
        // 2 Times on client (for each hand)


//        LOGGER.info(destinationBlockPos.toString());
        if(world.isClient())
        {
            if(hand == Hand.MAIN_HAND)
            {
                BlockPos destinationBlockPos = checkNeighborBlocks(pos, null, world);
//                destinationBlockPos = findDestination(pos, world);
                // CLIENT: MAIN HAND
                if(destinationBlockPos != null){
                    playParticle(world, pos, ParticleTypes.HAPPY_VILLAGER);
                }
                else{
                    playParticle(world, pos, ParticleTypes.SMOKE);
                }
//                player.playSound(SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 1f, 1f);
//                player.playSound(SoundEvents.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 1f, 1f);
//                player.sendMessage(new LiteralText("Stations in world:"), false);
//                int index = 0;
//                for (StationModel s: stations.getPlacedStations()) {
//                    index++;
//                    player.sendMessage(new LiteralText(index + " " + s.getPos().toString()), false);
//                    LOGGER.info(s.toString());
//                }

            }
            else{
                // CLIENT: OFF HAND
            }
        }
        else{
            // SERVER
            BlockPos destinationBlockPos = findDestination(pos, world);
            if(destinationBlockPos != null)
            {
//                Position destinationTeleportPos = setTeleportPos(destinationBlockPos);
                player.teleport(destinationBlockPos.getX() + 0.5, destinationBlockPos.getY() + 0.3, destinationBlockPos.getZ() + 0.5);
                world.playSound(null, destinationBlockPos, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.BLOCKS, 1f, 1f);
            }
            else{
                world.playSound(null, pos, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.BLOCKS, 1f, 1f);
                player.sendMessage(new LiteralText("No destination found"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StationBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);

            if (be instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory) be);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    public void playParticle(World world, BlockPos pos, DefaultParticleType particle){
        for (int i = 0; i < 360; i++)
        {
            if(i % 20 == 0){

                world.addParticle(particle,
                        pos.getX() + 0.5d, pos.getY() + 1, pos.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

    public BlockPos findDestination(BlockPos stationPos, World world){
        BlockPos rail = checkNeighborBlocks(stationPos, null, world);
        if(rail == null)
            return null;

        BlockPos newRail;
        BlockPos previousPos = stationPos;
        List<BlockPos> foundRails = new ArrayList<>();
        foundRails.add(rail);

        do {
            newRail = checkNeighborBlocks(rail, previousPos, world);
            if(newRail == null)
                return rail;

            for (BlockPos foundRail : foundRails) {
                if(newRail.equals(foundRail))
                    return  rail;
            }
            previousPos = rail;
            rail = newRail;
            foundRails.add(rail);
        } while (true);
    }

    public BlockPos checkNeighborBlocks(BlockPos pos, BlockPos previousPos, World world){
        BlockPos[] neighborBlocks = {
                new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),
                new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),
                new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1),
                new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1),

                new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()),
                new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()),
                new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1),
                new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1),

                new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ()),
                new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ()),
                new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1),
                new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1)
        };
        if(previousPos != null)
        {
            for (BlockPos neighborBlock: neighborBlocks) {
                Block block = world.getBlockState(new BlockPos(neighborBlock)).getBlock();
                if(Objects.equals(block.getLootTableId().toString(), "minecraft:blocks/rail")){
                    if(neighborBlock.getX() == previousPos.getX()){
                        if(neighborBlock.getZ() != previousPos.getZ()){
                            return neighborBlock;
                        }
                    }
                    else if(neighborBlock.getZ() == previousPos.getZ()){
                        if(neighborBlock.getX() != previousPos.getX()){
                            return neighborBlock;
                        }
                    }
                    else{
                        return neighborBlock;
                    }
                }
            }
        }
        else{
            for (BlockPos neighborBlock: neighborBlocks) {
                Block block = world.getBlockState(new BlockPos(neighborBlock)).getBlock();
                if(Objects.equals(block.getLootTableId().toString(), "minecraft:blocks/rail"))
                    return neighborBlock;
            }
        }
        return null;
    }


}
