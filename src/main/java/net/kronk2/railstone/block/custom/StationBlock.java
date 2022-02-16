package net.kronk2.railstone.block.custom;

import net.kronk2.railstone.Railstone;
import net.kronk2.railstone.block.custom.station.StationModel;
import net.kronk2.railstone.block.custom.station.Stations;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StationBlock extends BlockWithEntity {

    public static final Logger LOGGER = LoggerFactory.getLogger(Railstone.MOD_ID);
    Stations stations = Stations.getInstance();

    public StationBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

        if(!world.isClient())
        {
            StationModel newStation = new StationModel(world, pos, state, placer, itemStack);
            stations.addToStation(newStation);
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {

        if(!world.isClient()) {
            stations.removeStation(pos);
        }

    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        LOGGER.info("neighborupdate" + pos.toString() + " | " + block.toString());


        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        // called 4 times on right click:
        // 2 Times on server (for each hand)
        // 2 Times on client (for each hand)


        if(world.isClient())
        {
            if(hand == Hand.MAIN_HAND)
            {
//                player.sendMessage(new LiteralText("CLIENT: This is the client! MAIN HAND!" + pos.toString()), false);
                player.sendMessage(new LiteralText("Stations in world:"), false);

                int index = 0;
                for (StationModel s: stations.getPlacedStations()) {
                    index++;
                    player.sendMessage(new LiteralText(index + " " + s.getPos().toString()), false);
                    LOGGER.info(s.toString());
                }
            }
            else{
                player.sendMessage(new LiteralText("CLIENT: This is the client! OFF HAND!"), false);
            }
        }
        else{

            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
//            int index = 0;
//            for (StationModel s: stations.getPlacedStations()) {
//                index++;
//                player.teleport(s.getPos().getX(), s.getPos().getY(), s.getPos().getZ());
//            }
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
}
