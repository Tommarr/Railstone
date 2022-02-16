package net.kronk2.railstone.screen;

import net.kronk2.railstone.Railstone;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

import static net.kronk2.railstone.block.ModBlocks.STATION_SCREEN_HANDLER;

public class StationScreenHandler extends Generic3x3ContainerScreenHandler implements PositionedScreenHandler{
    private final BlockPos pos;

    public StationScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        super(syncId, playerInventory);
        this.pos = buf.readBlockPos();
    }

    public StationScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(syncId, playerInventory, inventory);
        this.pos = BlockPos.ORIGIN;
    }

    @Override
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return STATION_SCREEN_HANDLER;
    }
}
