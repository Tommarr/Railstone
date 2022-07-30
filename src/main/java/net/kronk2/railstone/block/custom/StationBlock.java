package net.kronk2.railstone.block.custom;

import net.kronk2.railstone.Railstone;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StationBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public StationBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 0, 5, 3, 16),
            Block.createCuboidShape(11, 0, 0, 16, 3, 16),
            Block.createCuboidShape(1, 3, 12, 4, 9, 15),
            Block.createCuboidShape(12, 3, 12, 15, 9, 15),
            Block.createCuboidShape(1, 3, 1, 4, 11, 4),
            Block.createCuboidShape(12, 3, 1, 15, 11, 4),
            Block.createCuboidShape(-1, 11, 0, 17, 16, 4),
            Block.createCuboidShape(1, 12, -1, 4, 15, 0),
            Block.createCuboidShape(12, 12, -1, 15, 15, 0),
            Block.createCuboidShape(1, 7, 8, 4, 10, 14),
            Block.createCuboidShape(1, 8, 5, 4, 11, 11),
            Block.createCuboidShape(1, 9, 3, 4, 12, 8),
            Block.createCuboidShape(12, 8, 5, 15, 11, 11),
            Block.createCuboidShape(12, 7, 8, 15, 10, 14),
            Block.createCuboidShape(12, 9, 3, 15, 12, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 3, 5),
            Block.createCuboidShape(0, 0, 11, 16, 3, 16),
            Block.createCuboidShape(1, 3, 1, 4, 9, 4),
            Block.createCuboidShape(1, 3, 12, 4, 9, 15),
            Block.createCuboidShape(12, 3, 1, 15, 11, 4),
            Block.createCuboidShape(12, 3, 12, 15, 11, 15),
            Block.createCuboidShape(12, 11, -1, 16, 16, 17),
            Block.createCuboidShape(16, 12, 1, 17, 15, 4),
            Block.createCuboidShape(16, 12, 12, 17, 15, 15),
            Block.createCuboidShape(2, 7, 1, 8, 10, 4),
            Block.createCuboidShape(5, 8, 1, 11, 11, 4),
            Block.createCuboidShape(8, 9, 1, 13, 12, 4),
            Block.createCuboidShape(5, 8, 12, 11, 11, 15),
            Block.createCuboidShape(2, 7, 12, 8, 10, 15),
            Block.createCuboidShape(8, 9, 12, 13, 12, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(11, 0, 0, 16, 3, 16),
            Block.createCuboidShape(0, 0, 0, 5, 3, 16),
            Block.createCuboidShape(12, 3, 1, 15, 9, 4),
            Block.createCuboidShape(1, 3, 1, 4, 9, 4),
            Block.createCuboidShape(12, 3, 12, 15, 11, 15),
            Block.createCuboidShape(1, 3, 12, 4, 11, 15),
            Block.createCuboidShape(-1, 11, 12, 17, 16, 16),
            Block.createCuboidShape(12, 12, 16, 15, 15, 17),
            Block.createCuboidShape(1, 12, 16, 4, 15, 17),
            Block.createCuboidShape(12, 7, 2, 15, 10, 8),
            Block.createCuboidShape(12, 8, 5, 15, 11, 11),
            Block.createCuboidShape(12, 9, 8, 15, 12, 13),
            Block.createCuboidShape(1, 8, 5, 4, 11, 11),
            Block.createCuboidShape(1, 7, 2, 4, 10, 8),
            Block.createCuboidShape(1, 9, 8, 4, 12, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 11, 16, 3, 16),
            Block.createCuboidShape(0, 0, 0, 16, 3, 5),
            Block.createCuboidShape(12, 3, 12, 15, 9, 15),
            Block.createCuboidShape(12, 3, 1, 15, 9, 4),
            Block.createCuboidShape(1, 3, 12, 4, 11, 15),
            Block.createCuboidShape(1, 3, 1, 4, 11, 4),
            Block.createCuboidShape(0, 11, -1, 4, 16, 17),
            Block.createCuboidShape(-1, 12, 12, 0, 15, 15),
            Block.createCuboidShape(-1, 12, 1, 0, 15, 4),
            Block.createCuboidShape(8, 7, 12, 14, 10, 15),
            Block.createCuboidShape(5, 8, 12, 11, 11, 15),
            Block.createCuboidShape(3, 9, 12, 8, 12, 15),
            Block.createCuboidShape(5, 8, 1, 11, 11, 4),
            Block.createCuboidShape(8, 7, 1, 14, 10, 4),
            Block.createCuboidShape(3, 9, 1, 8, 12, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> SHAPE_N;
            case EAST -> SHAPE_E;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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

                if(AbstractRailBlock.isRail(block.getDefaultState())){

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
                if(AbstractRailBlock.isRail(block.getDefaultState()))
                    return neighborBlock;
            }
        }
        return null;
    }


}
