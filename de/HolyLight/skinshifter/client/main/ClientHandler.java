
package de.HolyLight.skinshifter.client.main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.HolyLight.skinshifter.Consts;
import de.HolyLight.skinshifter.client.event.PollSkinsEventHandler;
import de.HolyLight.skinshifter.common.main.CommonHandler;

public class ClientHandler extends CommonHandler {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);

        // The minecraft dir might be different on the client
        Consts.MINECRAFT_DIR = Minecraft.getMinecraft().mcDataDir.toPath();
    }
    {

        // Initialize the skin polling mechanism
        MinecraftForge.EVENT_BUS.register(new PollSkinsEventHandler());
    }
    @Override
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);
    }

}
