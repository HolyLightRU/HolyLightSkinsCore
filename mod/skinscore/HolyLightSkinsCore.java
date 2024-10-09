package mod.skinscore;

import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.IFuelHandler;

@Mod(modid = HolyLightSkinsCore.MODID, version = HolyLightSkinsCore.VERSION)
public class HolyLightSkinsCore implements IFuelHandler, IWorldGenerator {

    public static final String MODID = "HolyLightSkinsCore";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "mod.mcreator.ClientProxyHolyLightSkinsCore", serverSide = "mod.mcreator.CommonProxyHolyLightSkinsCore")
    // @SidedProxy(clientSide="mod.mcreator.ClientProxyHolyLightSkinsCore",
    // serverSide="mod.mcreator.CommonProxyHolyLightSkinsCore")
    public static CommonProxyHolyLightSkinsCore proxy;
    // public static EnumMap<Side, FMLEmbeddedChannel> channels =
    // NetworkRegistry.INSTANCE.newChannel("MCRBUS", new
    // ChannelHandlerHolyLightSkinsCore());

    @Instance(MODID)
    public static HolyLightSkinsCore instance;

    mcreator_testHuia mcreator_0 = new mcreator_testHuia();
    mcreator_openHuesosky123 mcreator_1 = new mcreator_openHuesosky123();

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (this.mcreator_0.addFuel(fuel) != 0)
            return this.mcreator_0.addFuel(fuel);
        if (this.mcreator_1.addFuel(fuel) != 0)
            return this.mcreator_1.addFuel(fuel);
        return 0;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        chunkX = chunkX * 16;
        chunkZ = chunkZ * 16;
        if (world.provider.dimensionId == -1) {
            this.mcreator_0.generateNether(world, random, chunkX, chunkZ);
        }
        if (world.provider.dimensionId == 0) {
            this.mcreator_0.generateSurface(world, random, chunkX, chunkZ);
        }
        if (world.provider.dimensionId == -1) {
            this.mcreator_1.generateNether(world, random, chunkX, chunkZ);
        }
        if (world.provider.dimensionId == 0) {
            this.mcreator_1.generateSurface(world, random, chunkX, chunkZ);
        }

    }

    @EventHandler
    public void load(FMLInitializationEvent event) {

        GameRegistry.registerFuelHandler(this);
        GameRegistry.registerWorldGenerator(this, 1);
        MinecraftForge.EVENT_BUS.register(new mcreator_GlobalEventsTestEnvironmentMod());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        this.mcreator_0.load();
        this.mcreator_1.load();

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        this.mcreator_0.serverLoad(event);
        this.mcreator_1.serverLoad(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.mcreator_0.instance = this.instance;
        this.mcreator_1.instance = this.instance;
        this.mcreator_0.preInit(event);
        this.mcreator_1.preInit(event);
        proxy.registerRenderers(this);
    }

    public static class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == mcreator_testHuia.GUIID)
                return new mcreator_testHuia.GuiContainerMod(world, x, y, z, player);
            return null;
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == mcreator_testHuia.GUIID)
                return new mcreator_testHuia.GuiWindow(world, x, y, z, player);
            return null;
        }
    }

}
