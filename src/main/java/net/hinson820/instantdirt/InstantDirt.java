package net.hinson820.instantdirt;

import com.mojang.logging.LogUtils;
import net.hinson820.instantdirt.event.BlockEventHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;


/**
 * The main mod class for InstantDirt.
 * This class is the entry point for the mod and is responsible for
 * registering event listeners.
 */
@Mod(InstantDirt.MODID)
public class InstantDirt {

    /**
     * The unique identifier for this mod.
     */
    public static final String MODID = "instantdirt";

    /**
     * A logger instance for logging messages from this mod.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    /**
     * The constructor for the mod. It's called by NeoForge during the mod loading process.
     * @param modEventBus The event bus for this mod, provided by NeoForge.
     */
    public InstantDirt(IEventBus modEventBus) {
        // Register our event handler to the main NeoForge event bus.
        // This allows our handler to listen for game-wide events.
        NeoForge.EVENT_BUS.register(new BlockEventHandler());

        LOGGER.info("InstantDirt loaded and ready to smother some grass!");
    }
}