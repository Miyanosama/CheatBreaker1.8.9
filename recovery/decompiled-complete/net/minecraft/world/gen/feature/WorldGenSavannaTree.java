package net.minecraft.world.gen.feature;

import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$26;
import org.java_websocket.WebSocketAdapter;

public class WorldGenSavannaTree extends WorldGenAbstractTree {
   public PrivateMessageElement field_0004;
   public static IBlockState field_181644_b = Blocks.leaves2
      .getDefaultState()
      .withProperty(BlockNewLeaf.VARIANT, BlockPlanks$EnumType.ACACIA)
      .withProperty(BlockLeaves.b, false);
   public static IBlockState field_181643_a = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks$EnumType.ACACIA);
   public LogBrokerMonitor$26 field_0005;
   public EntityEgg field_0003;
   public WebSocketAdapter field_0002;

   public void func_175924_b(World var1, BlockPos var2) {
      Material var3 = var1.getBlockState(var2).getBlock().getMaterial();
      if (var3 == Material.air || var3 == Material.leaves) {
         this.setBlockAndNotifyAdequately(var1, var2, field_181644_b);
      }
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      int var4 = var2.nextInt(3) + var2.nextInt(3) + 5;
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
            Block var21 = var1.getBlockState(var3.down()).getBlock();
            if ((var21 == Blocks.grass || var21 == Blocks.dirt) && var3.getY() < 256 - var4 - 1) {
               this.func_175921_a(var1, var3.down());
               EnumFacing var22 = EnumFacing$Plane.HORIZONTAL.random(var2);
               int var23 = var4 - var2.nextInt(4) - 1;
               int var24 = 3 - var2.nextInt(3);
               int var25 = var3.getX();
               int var11 = var3.getZ();
               int var12 = 0;

               for (int var13 = 0; var13 < var4; var13++) {
                  int var14 = var3.getY() + var13;
                  if (var13 >= var23 && var24 > 0) {
                     var25 += var22.getFrontOffsetX();
                     var11 += var22.getFrontOffsetZ();
                     var24--;
                  }

                  BlockPos var15 = new BlockPos(var25, var14, var11);
                  Material var16 = var1.getBlockState(var15).getBlock().getMaterial();
                  if (var16 == Material.air || var16 == Material.leaves) {
                     this.func_181642_b(var1, var15);
                     var12 = var14;
                  }
               }

               BlockPos var29 = new BlockPos(var25, var12, var11);

               for (int var31 = -3; var31 <= 3; var31++) {
                  for (int var34 = -3; var34 <= 3; var34++) {
                     if (Math.abs(var31) != 3 || Math.abs(var34) != 3) {
                        this.func_175924_b(var1, var29.add(var31, 0, var34));
                     }
                  }
               }

               var29 = var29.up();

               for (int var32 = -1; var32 <= 1; var32++) {
                  for (int var35 = -1; var35 <= 1; var35++) {
                     this.func_175924_b(var1, var29.add(var32, 0, var35));
                  }
               }

               this.func_175924_b(var1, var29.east(2));
               this.func_175924_b(var1, var29.west(2));
               this.func_175924_b(var1, var29.south(2));
               this.func_175924_b(var1, var29.north(2));
               var25 = var3.getX();
               var11 = var3.getZ();
               EnumFacing var33 = EnumFacing$Plane.HORIZONTAL.random(var2);
               if (var33 != var22) {
                  int var36 = var23 - var2.nextInt(2) - 1;
                  int var37 = 1 + var2.nextInt(3);
                  var12 = 0;

                  for (int var17 = var36; var17 < var4 && var37 > 0; var37--) {
                     if (var17 >= 1) {
                        int var18 = var3.getY() + var17;
                        var25 += var33.getFrontOffsetX();
                        var11 += var33.getFrontOffsetZ();
                        BlockPos var19 = new BlockPos(var25, var18, var11);
                        Material var20 = var1.getBlockState(var19).getBlock().getMaterial();
                        if (var20 == Material.air || var20 == Material.leaves) {
                           this.func_181642_b(var1, var19);
                           var12 = var18;
                        }
                     }

                     var17++;
                  }

                  if (var12 > 0) {
                     BlockPos var38 = new BlockPos(var25, var12, var11);

                     for (int var40 = -2; var40 <= 2; var40++) {
                        for (int var42 = -2; var42 <= 2; var42++) {
                           if (Math.abs(var40) != 2 || Math.abs(var42) != 2) {
                              this.func_175924_b(var1, var38.add(var40, 0, var42));
                           }
                        }
                     }

                     var38 = var38.up();

                     for (int var41 = -1; var41 <= 1; var41++) {
                        for (int var43 = -1; var43 <= 1; var43++) {
                           this.func_175924_b(var1, var38.add(var41, 0, var43));
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

   public WorldGenSavannaTree(boolean var1) {
      super(var1);
   }

   public void func_181642_b(World var1, BlockPos var2) {
      this.setBlockAndNotifyAdequately(var1, var2, field_181643_a);
   }
}
