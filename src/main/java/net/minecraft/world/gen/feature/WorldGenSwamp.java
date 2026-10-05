package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSwamp extends WorldGenAbstractTree {
   public static IBlockState field_181648_a = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
   public static IBlockState field_181649_b = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockOldLeaf.b, false);

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      int var4 = var2.nextInt(4) + 5;

      while (var1.getBlockState(var3.down()).getBlock().getMaterial() == Material.water) {
         var3 = var3.down();
      }

      boolean var5 = true;
      if (var3.getY() >= 1 && var3.getY() + var4 + 1 <= 256) {
         for (int var6 = var3.getY(); var6 <= var3.getY() + 1 + var4; var6++) {
            byte var7 = 1;
            if (var6 == var3.getY()) {
               var7 = 0;
            }

            if (var6 >= var3.getY() + 1 + var4 - 2) {
               var7 = 3;
            }

            BlockPos.MutableBlockPos var8 = new BlockPos.MutableBlockPos();

            for (int var9 = var3.getX() - var7; var9 <= var3.getX() + var7 && var5; var9++) {
               for (int var10 = var3.getZ() - var7; var10 <= var3.getZ() + var7 && var5; var10++) {
                  if (var6 >= 0 && var6 < 256) {
                     Block var11 = var1.getBlockState(var8.set(var9, var6, var10)).getBlock();
                     if (var11.getMaterial() != Material.air && var11.getMaterial() != Material.leaves) {
                        if (var11 != Blocks.water && var11 != Blocks.flowing_water) {
                           var5 = false;
                        } else if (var6 > var3.getY()) {
                           var5 = false;
                        }
                     }
                  } else {
                     var5 = false;
                  }
               }
            }
         }

         if (!var5) {
            return false;
         } else {
            Block var17 = var1.getBlockState(var3.down()).getBlock();
            if ((var17 == Blocks.grass || var17 == Blocks.dirt) && var3.getY() < 256 - var4 - 1) {
               this.func_175921_a(var1, var3.down());

               for (int var18 = var3.getY() - 3 + var4; var18 <= var3.getY() + var4; var18++) {
                  int var21 = var18 - (var3.getY() + var4);
                  int var24 = 2 - var21 / 2;

                  for (int var26 = var3.getX() - var24; var26 <= var3.getX() + var24; var26++) {
                     int var28 = var26 - var3.getX();

                     for (int var12 = var3.getZ() - var24; var12 <= var3.getZ() + var24; var12++) {
                        int var13 = var12 - var3.getZ();
                        if (Math.abs(var28) != var24 || Math.abs(var13) != var24 || var2.nextInt(2) != 0 && var21 != 0) {
                           BlockPos var14 = new BlockPos(var26, var18, var12);
                           if (!var1.getBlockState(var14).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(var1, var14, field_181649_b);
                           }
                        }
                     }
                  }
               }

               for (int var19 = 0; var19 < var4; var19++) {
                  Block var22 = var1.getBlockState(var3.up(var19)).getBlock();
                  if (var22.getMaterial() == Material.air || var22.getMaterial() == Material.leaves || var22 == Blocks.flowing_water || var22 == Blocks.water) {
                     this.setBlockAndNotifyAdequately(var1, var3.up(var19), field_181648_a);
                  }
               }

               for (int var20 = var3.getY() - 3 + var4; var20 <= var3.getY() + var4; var20++) {
                  int var23 = var20 - (var3.getY() + var4);
                  int var25 = 2 - var23 / 2;
                  BlockPos.MutableBlockPos var27 = new BlockPos.MutableBlockPos();

                  for (int var29 = var3.getX() - var25; var29 <= var3.getX() + var25; var29++) {
                     for (int var30 = var3.getZ() - var25; var30 <= var3.getZ() + var25; var30++) {
                        var27.set(var29, var20, var30);
                        if (var1.getBlockState(var27).getBlock().getMaterial() == Material.leaves) {
                           BlockPos var31 = var27.west();
                           BlockPos var32 = var27.east();
                           BlockPos var15 = var27.north();
                           BlockPos var16 = var27.south();
                           if (var2.nextInt(4) == 0 && var1.getBlockState(var31).getBlock().getMaterial() == Material.air) {
                              this.func_181647_a(var1, var31, BlockVine.EAST);
                           }

                           if (var2.nextInt(4) == 0 && var1.getBlockState(var32).getBlock().getMaterial() == Material.air) {
                              this.func_181647_a(var1, var32, BlockVine.WEST);
                           }

                           if (var2.nextInt(4) == 0 && var1.getBlockState(var15).getBlock().getMaterial() == Material.air) {
                              this.func_181647_a(var1, var15, BlockVine.SOUTH);
                           }

                           if (var2.nextInt(4) == 0 && var1.getBlockState(var16).getBlock().getMaterial() == Material.air) {
                              this.func_181647_a(var1, var16, BlockVine.NORTH);
                           }
                        }
                     }
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

   public WorldGenSwamp() {
      super(false);
   }

   public void func_181647_a(World var1, BlockPos var2, PropertyBool var3) {
      IBlockState var4 = Blocks.vine.getDefaultState().withProperty(var3, true);
      this.setBlockAndNotifyAdequately(var1, var2, var4);
      int var5 = 4;

      for (BlockPos var6 = var2.down(); var1.getBlockState(var6).getBlock().getMaterial() == Material.air && var5 > 0; var5--) {
         this.setBlockAndNotifyAdequately(var1, var6, var4);
         var6 = var6.down();
      }
   }
}
