package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnderChest extends BlockContainer {
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.obsidian);
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityEnderChest();
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      var1.a(var2, var3.withProperty(FACING, var4.getHorizontalFacing().getOpposite()), 2);
   }

   @Override
   public int getRenderType() {
      return 2;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      InventoryEnderChest var9 = var4.getInventoryEnderChest();
      TileEntity var10 = var1.getTileEntity(var2);
      if (var9 == null || !(var10 instanceof TileEntityEnderChest)) {
         return true;
      } else if (var1.getBlockState(var2.up()).getBlock().isNormalCube()) {
         return true;
      } else if (var1.D) {
         return true;
      } else {
         var9.setChestTileEntity((TileEntityEnderChest)var10);
         var4.displayGUIChest(var9);
         var4.triggerAchievement(StatList.field_181738_V);
         return true;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState().withProperty(FACING, var8.getHorizontalFacing().getOpposite());
   }

   public BlockEnderChest() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(FACING).getIndex();
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      for (int var5 = 0; var5 < 3; var5++) {
         int var6 = var4.nextInt(2) * 2 - 1;
         int var7 = var4.nextInt(2) * 2 - 1;
         double var8 = var2.getX() + 0.5 + 0.25 * var6;
         double var10 = var2.getY() + var4.nextFloat();
         double var12 = var2.getZ() + 0.5 + 0.25 * var7;
         double var14 = var4.nextFloat() * var6;
         double var16 = (var4.nextFloat() - 0.5) * 0.125;
         double var18 = var4.nextFloat() * var7;
         var1.spawnParticle(EnumParticleTypes.PORTAL, var8, var10, var12, var14, var16, var18);
      }
   }

   @Override
   public int quantityDropped(Random var1) {
      return 8;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      EnumFacing var2 = EnumFacing.getFront(var1);
      if (var2.getAxis() == EnumFacing.Axis.Y) {
         var2 = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, var2);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }
}
