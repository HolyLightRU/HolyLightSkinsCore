package mod.skinscore;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IIcon;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import de.HolyLight.skinshifter.net.NetworkService;
import de.HolyLight.skinshifter.net.messages.AvailableSkinsRequestServerMessage;
import net.minecraft.entity.EntityLivingBase;
import java.util.Random;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class mcreator_openHuesosky123 {

    public mcreator_openHuesosky123() {
    }

    public static BlockOpenHuesosky123 block;

    public static Object instance;

    public int addFuel(ItemStack fuel) {
        return 0;
    }

    public void serverLoad(FMLServerStartingEvent event) {
    }

    public void preInit(FMLPreInitializationEvent event) {

        GameRegistry.registerBlock(block, "OpenHuesosky123");
    }

    public void registerRenderers() {
    }

    public void load() {
    }

    static {

        block = (BlockOpenHuesosky123) (new BlockOpenHuesosky123().setHardness(2.0F).setResistance(10.0F).setLightLevel(0.0F)
                .setBlockName("OpenHuesosky123").setBlockTextureName("BlyaNuIHueta").setLightOpacity(0).setStepSound(Block.soundTypeStone)
                .setCreativeTab(CreativeTabs.tabBlock));
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        Block.blockRegistry.addObject(183, "OpenHuesosky123", block);
        block.setHarvestLevel("pickaxe", 0);
    }

    public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
    }

    public void generateNether(World world, Random random, int chunkX, int chunkZ) {
    }

    static class BlockOpenHuesosky123 extends Block {

        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0;

        Random field_149942_b = new Random();

        IIcon gor = null, dol = null, st1 = null, st2 = null, st3 = null, st4 = null;

        boolean red = false;

        protected BlockOpenHuesosky123() {
            super(Material.ground);

        }

        @Override
        public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
        {
            int metadata = world.getBlockMetadata(x, y, z);

            int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            int change = 0;

            switch (angle)
            {
                case 0:
                    change = 3;
                    break;
                case 1:
                    change = 1;
                    break;
                case 2:
                    change = 2;
                    break;
                case 3:
                    change = 0;
                    break;
            }

            world.setBlockMetadataWithNotify(x, y, z, (metadata & 12) + change, 3);
        }

        @Override
        public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
            return this.red ? 1 : 0;
        }

        @Override
        public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entity, int l, float m, float n, float o) {

            if (true) {
                if (entity instanceof EntityPlayer) {
                    NetworkService.DISPATCHER.sendToServer(new AvailableSkinsRequestServerMessage());
                }
            }

            return true;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int par2) {

            if (i == 0)
                return this.gor;

            else if (i == 1)
                return this.dol;

            else if (i == 2)
                return this.st1;

            else if (i == 3)
                return this.st2;

            else if (i == 4)
                return this.st4;

            else if (i == 5)
                return this.st3;

            else
                return this.gor;

        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerBlockIcons(IIconRegister reg) {
            this.gor = reg.registerIcon("BlyaNuIHueta");
            this.dol = reg.registerIcon("BlyaNuIHueta");
            this.st1 = reg.registerIcon("BlyaNuIHueta");
            this.st2 = reg.registerIcon("BlyaNuIHueta");
            this.st3 = reg.registerIcon("BlyaNuIHueta");
            this.st4 = reg.registerIcon("BlyaNuIHueta");
        }

        @Override
        public int getRenderType() {
            return 0;
        }

        @Override
        public int tickRate(World world) {
            return 10;
        }

        @Override
        public int quantityDropped(Random par1Random) {
            return 1;
        }

    }
}
