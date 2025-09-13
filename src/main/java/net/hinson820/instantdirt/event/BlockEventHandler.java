package net.hinson820.instantdirt.event;

import net.hinson820.instantdirt.InstantDirt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

/**
 * Handles game events related to block interactions.
 * An instance of this class is manually registered to the event bus.
 */
public class BlockEventHandler {

    /**
     * Fired when an entity places a block.
     * This method now contains two checks:
     * 1. If an opaque block is placed on grass/mycelium, the block below turns to dirt.
     * 2. If grass/mycelium is placed under an opaque block, the newly placed block turns to dirt.
     *
     * @param event The block placement event.
     */
    @SubscribeEvent
    public void onBlockPlace(final BlockEvent.EntityPlaceEvent event) {
        LevelAccessor levelAccessor = event.getLevel();
        BlockPos pos = event.getPos();

        // Guard clause: Ensure we are on the logical server before proceeding.
        if (!(levelAccessor instanceof Level level) || level.isClientSide()) {
            return;
        }

        BlockState placedState = level.getBlockState(pos);

        // --- Check 1: Top-Down Logic (Opaque block placed on top) ---
        // This handles the case where a player builds ON TOP of grass.
        if (placedState.isSolidRender(level, pos)) {
            BlockPos posBelow = pos.below();
            BlockState stateBelow = level.getBlockState(posBelow);

            // Check if the block below is either grass or mycelium.
            if (stateBelow.is(Blocks.GRASS_BLOCK) || stateBelow.is(Blocks.MYCELIUM)) {
                // Replace the grass/mycelium block with dirt.
                level.setBlock(posBelow, Blocks.DIRT.defaultBlockState(), 3);
            }
        }

        // --- Check 2: Bottom-Up Logic (Grass/Mycelium placed below) ---
        // This handles the case where a player places grass UNDER an existing structure.
        if (placedState.is(Blocks.GRASS_BLOCK) || placedState.is(Blocks.MYCELIUM)) {
            BlockPos posAbove = pos.above();
            BlockState stateAbove = level.getBlockState(posAbove);

            // Check if the block above is opaque.
            if (stateAbove.isSolidRender(level, posAbove)) {
                // Replace the newly placed grass/mycelium block with dirt.
                level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
            }
        }
    }
}
