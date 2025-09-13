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
     * This is an instance method, called on the registered instance of this class.
     * It checks if an opaque block was placed on top of grass or mycelium
     * and, if so, converts the block below to dirt.
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

        // Check if the placed block is opaque and renders as a full cube.
        if (placedState.isSolidRender(level, pos)) {
            BlockPos posBelow = pos.below();
            BlockState stateBelow = level.getBlockState(posBelow);

            // Check if the block below is either grass or mycelium.
            if (stateBelow.is(Blocks.GRASS_BLOCK) || stateBelow.is(Blocks.MYCELIUM)) {
                // Replace the grass/mycelium block with dirt.
                // The '3' flag ensures the change is sent to clients and updates neighbors.
                level.setBlock(posBelow, Blocks.DIRT.defaultBlockState(), 3);
            }
        }
    }
}