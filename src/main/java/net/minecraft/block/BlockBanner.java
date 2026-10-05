package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBanner extends BlockContainer {
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

   @Override
   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      if (var5 instanceof TileEntityBanner) {
         TileEntityBanner var6 = (TileEntityBanner)var5;
         ItemStack var7 = new ItemStack(Items.banner, 1, ((TileEntityBanner)var5).getBaseColor());
         NBTTagCompound var8 = new NBTTagCompound();
         TileEntityBanner.setBaseColorAndPatterns(var8, var6.getBaseColor(), var6.getPatterns());
         var7.setTagInfo("BlockEntityTag", var8);
         a(var1, var3, var7);
      } else {
         super.harvestBlock(var1, var2, var3, var4, (TileEntity)null);
      }
   }

   @Override
   public boolean canSpawnInBlock() {
      return true;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getSelectedBoundingBox(var1, var2);
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.banner;
   }

   @Override
   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.banner.white.name");
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityBanner();
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.banner;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      TileEntity var6 = var1.getTileEntity(var2);
      if (var6 instanceof TileEntityBanner) {
         ItemStack var7 = new ItemStack(Items.banner, 1, ((TileEntityBanner)var6).getBaseColor());
         NBTTagCompound var8 = new NBTTagCompound();
         var6.writeToNBT(var8);
         var8.removeTag("x");
         var8.removeTag("y");
         var8.removeTag("z");
         var8.removeTag("id");
         var7.setTagInfo("BlockEntityTag", var8);
         a(var1, var2, var7);
      } else {
         super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return !this.hasInvalidNeighbor(var1, var2) && super.canPlaceBlockAt(var1, var2);
   }

   public BlockBanner() {
      super(Material.wood);
      float var1 = 0.25F;
      float var2 = 1.0F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
   }

   public static class BlockBannerHanging extends BlockBanner {
      @Override
      public BlockState createBlockState() {
         return new BlockState(this, FACING);
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
      public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
         EnumFacing var5 = var3.getValue(FACING);
         if (!var1.getBlockState(var2.a(var5.getOpposite())).getBlock().getMaterial().isSolid()) {
            this.dropBlockAsItem(var1, var2, var3, 0);
            var1.setBlockToAir(var2);
         }

         super.onNeighborBlockChange(var1, var2, var3, var4);
      }

      public BlockBannerHanging() {
         this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      }

      @Override
      public int getMetaFromState(IBlockState var1) {
         return var1.getValue(FACING).getIndex();
      }

      @Override
      public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
         EnumFacing var3 = var1.getBlockState(var2).getValue(FACING);
         float var4 = 0.0F;
         float var5 = 0.78125F;
         float var6 = 0.0F;
         float var7 = 1.0F;
         float var8 = 0.125F;
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         switch (var3) {
            case NORTH:
            default:
               this.a(var6, var4, 1.0F - var8, var7, var5, 1.0F);
               break;
            case SOUTH:
               this.a(var6, var4, 0.0F, var7, var5, var8);
               break;
            case WEST:
               this.a(1.0F - var8, var4, var6, 1.0F, var5, var7);
               break;
            case EAST:
               this.a(0.0F, var4, var6, var8, var5, var7);
         }
      }
   }

   public static class BlockBannerStanding extends BlockBanner {
      @Override
      public BlockState createBlockState() {
         return new BlockState(this, ROTATION);
      }

      @Override
      public int getMetaFromState(IBlockState var1) {
         return var1.getValue(ROTATION);
      }

      @Override
      public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
         if (!var1.getBlockState(var2.down()).getBlock().getMaterial().isSolid()) {
            this.dropBlockAsItem(var1, var2, var3, 0);
            var1.setBlockToAir(var2);
         }

         super.onNeighborBlockChange(var1, var2, var3, var4);
      }

      @Override
      public IBlockState getStateFromMeta(int var1) {
         return this.getDefaultState().withProperty(ROTATION, var1);
      }

      public BlockBannerStanding() {
         this.setDefaultState(this.M.getBaseState().withProperty(ROTATION, 0));
      }
   }
}
