
package de.HolyLight.skinshifter.common.main;

import java.nio.file.Paths;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.HolyLight.skinshifter.Config;
import de.HolyLight.skinshifter.Consts;
import de.HolyLight.skinshifter.net.NetworkService;

public class CommonHandler {

    public void preInit(FMLPreInitializationEvent event) {

        // Retrieve dynamic "constants"
        Consts.MINECRAFT_DIR = Paths.get(".");

        // Initialize the configuration
        Config.initialize(event.getSuggestedConfigurationFile());

        // Initialize the network service
        NetworkService.initialize();
    }


    public void postInit(FMLPostInitializationEvent event) {

    }

}
