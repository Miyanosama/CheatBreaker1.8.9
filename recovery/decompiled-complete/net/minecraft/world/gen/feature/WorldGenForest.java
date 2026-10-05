package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterLeadKnot;

public class WorldGenForest extends WorldGenAbstractTree {
   public static IBlockState field_181629_a = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks$EnumType.BIRCH);
   public static IBlockState field_181630_b = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks$EnumType.BIRCH)
      .withProperty(BlockOldLeaf.b, false);
   public boolean useExtraRandomHeight;
   public GuiOptionSlider field_0004;
   public ModelAdapterLeadKnot field_0002;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      int var4 = var2.nextInt(3) + 5;
      if (this.useExtraRandomHeight) {
         var4 += var2.nextInt(7);
      }

      boolean var5 = true;
      if (var3.getY() >= 1 && var3.getY() + var4 + 1 <= 256) {
         for (int var6 = var3.getY(); var6 <= var3.getY() + 1 + var4; var6++) {
            byte var7 = 1;
            if (var6 == var3.getY()) {
               var7 = 0;
            }

            if (var6 >= var3.getY() + 1 + var4 - 2) {
               var7 = 2;
            }

            BlockPos$MutableBlockPos var8 = new BlockPos$MutableBlockPos();

            for (int var9 = var3.getX() - var7; var9 <= var3.getX() + var7 && var5; var9++) {
               for (int var10 = var3.getZ() - var7; var10 <= var3.getZ() + var7 && var5; var10++) {
                  if (var6 < 0 || var6 >= 256) {
                     var5 = false;
                  } else if (!this.func_150523_a(var1.getBlockState(var8.set(var9, var6, var10)).getBlock())) {
                     var5 = false;
                  }
               }
            }
         }

         if (!var5) {
            return false;
         } else {
            Block var16 = var1.getBlockState(var3.down()).getBlock();
            if ((var16 == Blocks.grass || var16 == Blocks.dirt || var16 == Blocks.farmland) && var3.getY() < 256 - var4 - 1) {
               this.func_175921_a(var1, var3.down());

               for (int var17 = var3.getY() - 3 + var4; var17 <= var3.getY() + var4; var17++) {
                  int var19 = var17 - (var3.getY() + var4);
                  int var21 = 1 - var19 / 2;

                  for (int var22 = var3.getX() - var21; var22 <= var3.getX() + var21; var22++) {
                     int var11 = var22 - var3.getX();

                     for (int var12 = var3.getZ() - var21; var12 <= var3.getZ() + var21; var12++) {
                        int var13 = var12 - var3.getZ();
                        if (Math.abs(var11) != var21 || Math.abs(var13) != var21 || var2.nextInt(2) != 0 && var19 != 0) {
                           BlockPos var14 = new BlockPos(var22, var17, var12);
                           Block var15 = var1.getBlockState(var14).getBlock();
                           if (var15.getMaterial() == Material.air || var15.getMaterial() == Material.leaves) {
                              this.setBlockAndNotifyAdequately(var1, var14, field_181630_b);
                           }
                        }
                     }
                  }
               }

               for (int var18 = 0; var18 < var4; var18++) {
                  Block var20 = var1.getBlockState(var3.up(var18)).getBlock();
                  if (var20.getMaterial() == Material.air || var20.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3.up(var18), field_181629_a);
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

   public WorldGenForest(boolean var1, boolean var2) {
      super(var1);
      this.useExtraRandomHeight = var2;
   }
}
