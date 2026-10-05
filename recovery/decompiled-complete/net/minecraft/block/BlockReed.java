package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient$1;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.optifine.shaders.config.ShaderOptionScreen;

public class BlockReed extends Block {
   public static PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
   public WorldClient$1 field_0002;
   public ShaderOptionScreen field_0000;

   public boolean canBlockStay(World var1, BlockPos var2) {
      return this.canPlaceBlockAt(var1, var2);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      Block var3 = var1.getBlockState(var2.down()).getBlock();
      if (var3 == this) {
         return true;
      } else if (var3 != Blocks.grass && var3 != Blocks.dirt && var3 != Blocks.sand) {
         return false;
      } else {
         for (EnumFacing var5 : EnumFacing$Plane.HORIZONTAL) {
            if (var1.getBlockState(var2.a(var5).down()).getBlock().getMaterial() == Material.water) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      return var1.getBiomeGenForCoords(var2).getGrassColorAtPos(var2);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.reeds;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, AGE);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockReed() {
      super(Material.plants);
      this.setDefaultState(this.M.getBaseState().withProperty(AGE, 0));
      float var1 = 0.375F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 1.0F, 0.5F + var1);
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      this.checkForDrop(var1, var2, var3);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if ((var1.getBlockState(var2.down()).getBlock() == Blocks.reeds || this.checkForDrop(var1, var2, var3)) && var1.isAirBlock(var2.up())) {
         int var5 = 1;

         while (var1.getBlockState(var2.down(var5)).getBlock() == this) {
            var5++;
         }

         if (var5 < 3) {
            int var6 = var3.getValue(AGE);
            if (var6 == 15) {
               var1.setBlockState(var2.up(), this.getDefaultState());
               var1.a(var2, var3.withProperty(AGE, 0), 4);
            } else {
               var1.a(var2, var3.withProperty(AGE, var6 + 1), 4);
            }
         }
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public boolean checkForDrop(World var1, BlockPos var2, IBlockState var3) {
      if (this.canBlockStay(var1, var2)) {
         return true;
      } else {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
         return false;
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(AGE);
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.reeds;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(AGE, var1);
   }
}
