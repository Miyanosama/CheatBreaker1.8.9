package net.minecraft.world.gen.feature;

import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.world.World;
import org.apache.log4j.HTMLLayout;

public class WorldGenTaiga1 extends WorldGenAbstractTree {
   public static IBlockState field_181637_b = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks$EnumType.SPRUCE)
      .withProperty(BlockLeaves.b, false);
   public HTMLLayout field_0000;
   public static IBlockState field_181636_a = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks$EnumType.SPRUCE);
   public CosmeticType field_0003;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      int var4 = var2.nextInt(5) + 7;
      int var5 = var4 - var2.nextInt(2) - 3;
      int var6 = var4 - var5;
      int var7 = 1 + var2.nextInt(var6 + 1);
      boolean var8 = true;
      if (var3.getY() >= 1 && var3.getY() + var4 + 1 <= 256) {
         for (int var9 = var3.getY(); var9 <= var3.getY() + 1 + var4 && var8; var9++) {
            int var10 = 1;
            if (var9 - var3.getY() < var5) {
               var10 = 0;
            } else {
               var10 = var7;
            }

            BlockPos$MutableBlockPos var11 = new BlockPos$MutableBlockPos();

            for (int var12 = var3.getX() - var10; var12 <= var3.getX() + var10 && var8; var12++) {
               for (int var13 = var3.getZ() - var10; var13 <= var3.getZ() + var10 && var8; var13++) {
                  if (var9 < 0 || var9 >= 256) {
                     var8 = false;
                  } else if (!this.func_150523_a(var1.getBlockState(var11.set(var12, var9, var13)).getBlock())) {
                     var8 = false;
                  }
               }
            }
         }

         if (!var8) {
            return false;
         } else {
            Block var17 = var1.getBlockState(var3.down()).getBlock();
            if ((var17 == Blocks.grass || var17 == Blocks.dirt) && var3.getY() < 256 - var4 - 1) {
               this.func_175921_a(var1, var3.down());
               int var19 = 0;

               for (int var20 = var3.getY() + var4; var20 >= var3.getY() + var5; var20--) {
                  for (int var22 = var3.getX() - var19; var22 <= var3.getX() + var19; var22++) {
                     int var24 = var22 - var3.getX();

                     for (int var14 = var3.getZ() - var19; var14 <= var3.getZ() + var19; var14++) {
                        int var15 = var14 - var3.getZ();
                        if (Math.abs(var24) != var19 || Math.abs(var15) != var19 || var19 <= 0) {
                           BlockPos var16 = new BlockPos(var22, var20, var14);
                           if (!var1.getBlockState(var16).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(var1, var16, field_181637_b);
                           }
                        }
                     }
                  }

                  if (var19 >= 1 && var20 == var3.getY() + var5 + 1) {
                     var19--;
                  } else if (var19 < var7) {
                     var19++;
                  }
               }

               for (int var21 = 0; var21 < var4 - 1; var21++) {
                  Block var23 = var1.getBlockState(var3.up(var21)).getBlock();
                  if (var23.getMaterial() == Material.air || var23.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3.up(var21), field_181636_a);
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public WorldGenTaiga1() {
      super(false);
   }
}
