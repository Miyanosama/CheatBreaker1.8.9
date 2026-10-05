package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;

public class BlockDaylightDetector extends BlockContainer {
   public GenLayerBiomeEdge field_0002;
   public boolean inverted;
   public SyntaxErrorException field_0000;
   public static PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
   public WorldGenBigMushroom field_0004;

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(POWER);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, POWER);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var4.cn()) {
         if (var1.D) {
            return true;
         } else {
            if (this.inverted) {
               var1.a(var2, Blocks.daylight_detector.getDefaultState().withProperty(POWER, var3.getValue(POWER)), 4);
               Blocks.daylight_detector.updatePower(var1, var2);
            } else {
               var1.a(var2, Blocks.daylight_detector_inverted.getDefaultState().withProperty(POWER, var3.getValue(POWER)), 4);
               Blocks.daylight_detector_inverted.updatePower(var1, var2);
            }

            return true;
         }
      } else {
         return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8);
      }
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   public void updatePower(World var1, BlockPos var2) {
      if (!var1.t.getHasNoSky()) {
         IBlockState var3 = var1.getBlockState(var2);
         int var4 = var1.getLightFor(EnumSkyBlock.SKY, var2) - var1.getSkylightSubtracted();
         float var5 = var1.getCelestialAngleRadians(1.0F);
         float var6 = var5 < (float) Math.PI ? 0.0F : (float) (Math.PI * 2);
         var5 += (var6 - var5) * 0.2F;
         var4 = Math.round(var4 * MathHelper.cos(var5));
         var4 = MathHelper.clamp_int(var4, 0, 15);
         if (this.inverted) {
            var4 = 15 - var4;
         }

         if (var3.getValue(POWER) != var4) {
            var1.a(var2, var3.withProperty(POWER, var4), 3);
         }
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.daylight_detector);
   }

   public BlockDaylightDetector(boolean var1) {
      super(Material.wood);
      this.inverted = var1;
      this.setDefaultState(this.M.getBaseState().withProperty(POWER, 0));
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setHardness(0.2F);
      this.setStepSound(f);
      this.setUnlocalizedName("daylightDetector");
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.daylight_detector);
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityDaylightDetector();
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      if (!this.inverted) {
         super.getSubBlocks(var1, var2, var3);
      }
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var3.getValue(POWER);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(POWER, var1);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }
}
