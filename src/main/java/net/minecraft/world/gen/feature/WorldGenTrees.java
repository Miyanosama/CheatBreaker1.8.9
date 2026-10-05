package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenTrees extends WorldGenAbstractTree {
   public boolean vinesGrow;
   public int minTreeHeight;
   public static IBlockState field_181653_a = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
   public static IBlockState field_181654_b = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockLeaves.b, false);
   public IBlockState metaWood;
   public IBlockState metaLeaves;

   public void func_181651_a(World var1, BlockPos var2, PropertyBool var3) {
      this.setBlockAndNotifyAdequately(var1, var2, Blocks.vine.getDefaultState().withProperty(var3, true));
   }

   public WorldGenTrees(boolean var1) {
      this(var1, 4, field_181653_a, field_181654_b, false);
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      int var4 = var2.nextInt(3) + this.minTreeHeight;
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

            BlockPos.MutableBlockPos var8 = new BlockPos.MutableBlockPos();

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
            Block var19 = var1.getBlockState(var3.down()).getBlock();
            if ((var19 == Blocks.grass || var19 == Blocks.dirt || var19 == Blocks.farmland) && var3.getY() < 256 - var4 - 1) {
               this.func_175921_a(var1, var3.down());
               byte var20 = 3;
               byte var21 = 0;

               for (int var22 = var3.getY() - var20 + var4; var22 <= var3.getY() + var4; var22++) {
                  int var26 = var22 - (var3.getY() + var4);
                  int var11 = var21 + 1 - var26 / 2;

                  for (int var12 = var3.getX() - var11; var12 <= var3.getX() + var11; var12++) {
                     int var13 = var12 - var3.getX();

                     for (int var14 = var3.getZ() - var11; var14 <= var3.getZ() + var11; var14++) {
                        int var15 = var14 - var3.getZ();
                        if (Math.abs(var13) != var11 || Math.abs(var15) != var11 || var2.nextInt(2) != 0 && var26 != 0) {
                           BlockPos var16 = new BlockPos(var12, var22, var14);
                           Block var17 = var1.getBlockState(var16).getBlock();
                           if (var17.getMaterial() == Material.air || var17.getMaterial() == Material.leaves || var17.getMaterial() == Material.vine) {
                              this.setBlockAndNotifyAdequately(var1, var16, this.metaLeaves);
                           }
                        }
                     }
                  }
               }

               for (int var23 = 0; var23 < var4; var23++) {
                  Block var27 = var1.getBlockState(var3.up(var23)).getBlock();
                  if (var27.getMaterial() == Material.air || var27.getMaterial() == Material.leaves || var27.getMaterial() == Material.vine) {
                     this.setBlockAndNotifyAdequately(var1, var3.up(var23), this.metaWood);
                     if (this.vinesGrow && var23 > 0) {
                        if (var2.nextInt(3) > 0 && var1.isAirBlock(var3.add(-1, var23, 0))) {
                           this.func_181651_a(var1, var3.add(-1, var23, 0), BlockVine.EAST);
                        }

                        if (var2.nextInt(3) > 0 && var1.isAirBlock(var3.add(1, var23, 0))) {
                           this.func_181651_a(var1, var3.add(1, var23, 0), BlockVine.WEST);
                        }

                        if (var2.nextInt(3) > 0 && var1.isAirBlock(var3.add(0, var23, -1))) {
                           this.func_181651_a(var1, var3.add(0, var23, -1), BlockVine.SOUTH);
                        }

                        if (var2.nextInt(3) > 0 && var1.isAirBlock(var3.add(0, var23, 1))) {
                           this.func_181651_a(var1, var3.add(0, var23, 1), BlockVine.NORTH);
                        }
                     }
                  }
               }

               if (this.vinesGrow) {
                  for (int var24 = var3.getY() - 3 + var4; var24 <= var3.getY() + var4; var24++) {
                     int var28 = var24 - (var3.getY() + var4);
                     int var30 = 2 - var28 / 2;
                     BlockPos.MutableBlockPos var32 = new BlockPos.MutableBlockPos();

                     for (int var34 = var3.getX() - var30; var34 <= var3.getX() + var30; var34++) {
                        for (int var35 = var3.getZ() - var30; var35 <= var3.getZ() + var30; var35++) {
                           var32.set(var34, var24, var35);
                           if (var1.getBlockState(var32).getBlock().getMaterial() == Material.leaves) {
                              BlockPos var36 = var32.west();
                              BlockPos var37 = var32.east();
                              BlockPos var38 = var32.north();
                              BlockPos var18 = var32.south();
                              if (var2.nextInt(4) == 0 && var1.getBlockState(var36).getBlock().getMaterial() == Material.air) {
                                 this.func_181650_b(var1, var36, BlockVine.EAST);
                              }

                              if (var2.nextInt(4) == 0 && var1.getBlockState(var37).getBlock().getMaterial() == Material.air) {
                                 this.func_181650_b(var1, var37, BlockVine.WEST);
                              }

                              if (var2.nextInt(4) == 0 && var1.getBlockState(var38).getBlock().getMaterial() == Material.air) {
                                 this.func_181650_b(var1, var38, BlockVine.SOUTH);
                              }

                              if (var2.nextInt(4) == 0 && var1.getBlockState(var18).getBlock().getMaterial() == Material.air) {
                                 this.func_181650_b(var1, var18, BlockVine.NORTH);
                              }
                           }
                        }
                     }
                  }

                  if (var2.nextInt(5) == 0 && var4 > 5) {
                     for (int var25 = 0; var25 < 2; var25++) {
                        for (EnumFacing var31 : EnumFacing.Plane.HORIZONTAL) {
                           if (var2.nextInt(4 - var25) == 0) {
                              EnumFacing var33 = var31.getOpposite();
                              this.func_181652_a(var1, var2.nextInt(3), var3.add(var33.getFrontOffsetX(), var4 - 5 + var25, var33.getFrontOffsetZ()), var31);
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

   public void func_181650_b(World var1, BlockPos var2, PropertyBool var3) {
      this.func_181651_a(var1, var2, var3);
      int var4 = 4;

      for (BlockPos var5 = var2.down(); var1.getBlockState(var5).getBlock().getMaterial() == Material.air && var4 > 0; var4--) {
         this.func_181651_a(var1, var5, var3);
         var5 = var5.down();
      }
   }

   public WorldGenTrees(boolean var1, int var2, IBlockState var3, IBlockState var4, boolean var5) {
      super(var1);
      this.minTreeHeight = var2;
      this.metaWood = var3;
      this.metaLeaves = var4;
      this.vinesGrow = var5;
   }

   public void func_181652_a(World var1, int var2, BlockPos var3, EnumFacing var4) {
      this.setBlockAndNotifyAdequately(var1, var3, Blocks.cocoa.getDefaultState().withProperty(BlockCocoa.AGE, var2).withProperty(BlockCocoa.O, var4));
   }
}
