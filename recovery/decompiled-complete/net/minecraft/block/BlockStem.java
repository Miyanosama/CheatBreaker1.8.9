package net.minecraft.block;

import com.cheatbreaker.client.module.type.AnimationsModule;
import io.netty.handler.codec.socks.SocksCmdRequest$1;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.slf4j.helpers.MarkerIgnoringBase;

public class BlockStem extends BlockBush implements IGrowable {
   public static PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
   public MarkerIgnoringBase field_0000;
   public EntitySheep field_0001;
   public SocksCmdRequest$1 field_0005;
   public AnimationsModule field_0003;
   public Block crop;
   public static PropertyDirection FACING = PropertyDirection.create("facing", new BlockStem$1());

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      super.updateTick(var1, var2, var3, var4);
      if (var1.getLightFromNeighbors(var2.up()) >= 9) {
         float var5 = BlockCrops.getGrowthChance(this, var1, var2);
         if (var4.nextInt((int)(25.0F / var5) + 1) == 0) {
            int var6 = var3.getValue(AGE);
            if (var6 < 7) {
               var3 = var3.withProperty(AGE, var6 + 1);
               var1.a(var2, var3, 2);
            } else {
               for (EnumFacing var8 : EnumFacing$Plane.HORIZONTAL) {
                  if (var1.getBlockState(var2.a(var8)).getBlock() == this.crop) {
                     return;
                  }
               }

               var2 = var2.a(EnumFacing$Plane.HORIZONTAL.random(var4));
               Block var11 = var1.getBlockState(var2.down()).getBlock();
               if (var1.getBlockState(var2).getBlock().J == Material.air && (var11 == Blocks.farmland || var11 == Blocks.dirt || var11 == Blocks.grass)) {
                  var1.setBlockState(var2, this.crop.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return true;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float var1 = 0.125F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, AGE, FACING);
   }

   @Override
   public boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.farmland;
   }

   @Override
   public int getRenderColor(IBlockState var1) {
      if (var1.getBlock() != this) {
         return super.getRenderColor(var1);
      } else {
         int var2 = var1.getValue(AGE);
         int var3 = var2 * 32;
         int var4 = 255 - var2 * 8;
         int var5 = var2 * 4;
         return var3 << 16 | var4 << 8 | var5;
      }
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5);
      if (!var1.D) {
         Item var6 = this.getSeedItem();
         if (var6 != null) {
            int var7 = var3.getValue(AGE);

            for (int var8 = 0; var8 < 3; var8++) {
               if (var1.s.nextInt(15) <= var7) {
                  a(var1, var2, new ItemStack(var6));
               }
            }
         }
      }
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      Item var3 = this.getSeedItem();
      return var3 != null ? var3 : null;
   }

   public void growStem(World var1, BlockPos var2, IBlockState var3) {
      int var4 = var3.getValue(AGE) + MathHelper.getRandomIntegerInRange(var1.s, 2, 5);
      var1.a(var2, var3.withProperty(AGE, Math.min(7, var4)), 2);
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      return this.getRenderColor(var1.getBlockState(var2));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(AGE);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return null;
   }

   public BlockStem(Block var1) {
      this.setDefaultState(this.M.getBaseState().withProperty(AGE, 0).withProperty(FACING, EnumFacing.UP));
      this.crop = var1;
      this.setTickRandomly(true);
      float var2 = 0.125F;
      this.a(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
      this.setCreativeTab((CreativeTabs)null);
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return var3.getValue(AGE) != 7;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.F = (var1.getBlockState(var2).getValue(AGE) * 2 + 2) / 16.0F;
      float var3 = 0.125F;
      this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, (float)this.F, 0.5F + var3);
   }

   public Item getSeedItem() {
      return this.crop == Blocks.pumpkin ? Items.pumpkin_seeds : (this.crop == Blocks.melon_block ? Items.melon_seeds : null);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      var1 = var1.withProperty(FACING, EnumFacing.UP);

      for (EnumFacing var5 : EnumFacing$Plane.HORIZONTAL) {
         if (var2.getBlockState(var3.a(var5)).getBlock() == this.crop) {
            var1 = var1.withProperty(FACING, var5);
            break;
         }
      }

      return var1;
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      this.growStem(var1, var3, var4);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(AGE, var1);
   }
}
