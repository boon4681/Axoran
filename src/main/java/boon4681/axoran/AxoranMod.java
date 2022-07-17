package boon4681.axoran;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AxoranMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Axoran");

    @Override
    public void onInitialize() {
        LOGGER.info("Let's have fun with Axolotl!");
    }
}
