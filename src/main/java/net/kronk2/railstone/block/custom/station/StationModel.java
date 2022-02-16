package net.kronk2.railstone.block.custom.station;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StationModel {
    private World world;
    private BlockPos pos;
    private BlockState state;
    private LivingEntity placer;
    private ItemStack itemStack;

    public StationModel(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.placer = placer;
        this.itemStack = itemStack;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public LivingEntity getPlacer() {
        return placer;
    }

    public void setPlacer(LivingEntity placer) {
        this.placer = placer;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
