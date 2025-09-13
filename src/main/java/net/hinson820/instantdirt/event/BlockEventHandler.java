package net.hinson820.instantdirt.event;

import net.hinson820.instantdirt.InstantDirt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
     * Fired when an entity places a block. This method contains the logic for both
     * "covering" and "placing under" scenarios for grass and mycelium.
     *
     * @param event The block placement event.
     */
    @SubscribeEvent
    public void onBlockPlace(final BlockEvent.EntityPlaceEvent event) {
        // Guard clause: Ensure we are on the logical server before proceeding.
        if (!(event.getLevel() instanceof Level level) || level.isClientSide()) {
            return;
        }

        BlockPos pos = event.getPos();
        BlockState placedState = level.getBlockState(pos);

        // --- User Story 1: Covering Grass/Mycelium ---
        // Check if the placed block smothers grass based on our refined logic.
        if (this.blockSmothersGrass(placedState, level, pos)) {
            BlockPos posBelow = pos.below();
            BlockState stateBelow = level.getBlockState(posBelow);

            if (stateBelow.is(Blocks.GRASS_BLOCK) || stateBelow.is(Blocks.MYCELIUM)) {
                level.setBlock(posBelow, Blocks.DIRT.defaultBlockState(), 3);
                // Action was taken, no need to perform the second check.
                return;
            }
        }

        // --- User Story 2: Placing Grass/Mycelium Under Cover ---
        // Check if the block being placed is grass or mycelium.
        if (placedState.is(Blocks.GRASS_BLOCK) || placedState.is(Blocks.MYCELIUM)) {
            BlockPos posAbove = pos.above();
            BlockState stateAbove = level.getBlockState(posAbove);

            // Check if the block above smothers grass.
            if (this.blockSmothersGrass(stateAbove, level, posAbove)) {
                level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
            }
        }
    }

    /**
     * Determines if a block should convert grass/mycelium to dirt.
     * This is true only if the block has a full, solid base AND it is made of a
     * light-blocking material.
     *
     * @param state The BlockState to check.
     * @param level The level the block is in.
     * @param pos   The position of the block.
     * @return True if the block should smother grass, false otherwise.
     */
    private boolean blockSmothersGrass(BlockState state, Level level, BlockPos pos) {
        // Condition 1: The block must have a sturdy, full bottom face.
        // This correctly handles the SHAPE of stairs, slabs, chests, etc.
        boolean hasSturdyBase = state.isFaceSturdy(level, pos, Direction.DOWN);

        // Condition 2: The block's material must be able to occlude light.
        // This handles the MATERIAL property, distinguishing stone (true) from glass (false).
        boolean canOccludeLight = state.canOcclude();

        return hasSturdyBase && canOccludeLight;
    }
}