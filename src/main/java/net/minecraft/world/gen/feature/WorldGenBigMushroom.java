package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBigMushroom extends WorldGenerator {
   public Block mushroomType;

   public WorldGenBigMushroom() {
      super(false);
   }

   public WorldGenBigMushroom(Block var1) {
      super(true);
      this.mushroomType = var1;
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      if (this.mushroomType == null) {
         this.mushroomType = var2.nextBoolean() ? Blocks.brown_mushroom_block : Blocks.red_mushroom_block;
      }

      int var4 = var2.nextInt(3) + 4;
      boolean var5 = true;
      if (var3.getY() >= 1 && var3.getY() + var4 + 1 < 256) {
         for (int var6 = var3.getY(); var6 <= var3.getY() + 1 + var4; var6++) {
            byte var7 = 3;
            if (var6 <= var3.getY() + 3) {
               var7 = 0;
            }

            BlockPos.MutableBlockPos var8 = new BlockPos.MutableBlockPos();

            for (int var9 = var3.getX() - var7; var9 <= var3.getX() + var7 && var5; var9++) {
               for (int var10 = var3.getZ() - var7; var10 <= var3.getZ() + var7 && var5; var10++) {
                  if (var6 >= 0 && var6 < 256) {
                     Block var11 = var1.getBlockState(var8.set(var9, var6, var10)).getBlock();
                     if (var11.getMaterial() != Material.air && var11.getMaterial() != Material.leaves) {
                        var5 = false;
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
            Block var19 = var1.getBlockState(var3.down()).getBlock();
            if (var19 != Blocks.dirt && var19 != Blocks.grass && var19 != Blocks.mycelium) {
               return false;
            } else {
               int var20 = var3.getY() + var4;
               if (this.mushroomType == Blocks.red_mushroom_block) {
                  var20 = var3.getY() + var4 - 3;
               }

               for (int var21 = var20; var21 <= var3.getY() + var4; var21++) {
                  int var23 = 1;
                  if (var21 < var3.getY() + var4) {
                     var23++;
                  }

                  if (this.mushroomType == Blocks.brown_mushroom_block) {
                     var23 = 3;
                  }

                  int var25 = var3.getX() - var23;
                  int var26 = var3.getX() + var23;
                  int var12 = var3.getZ() - var23;
                  int var13 = var3.getZ() + var23;

                  for (int var14 = var25; var14 <= var26; var14++) {
                     for (int var15 = var12; var15 <= var13; var15++) {
                        int var16 = 5;
                        if (var14 == var25) {
                           var16--;
                        } else if (var14 == var26) {
                           var16++;
                        }

                        if (var15 == var12) {
                           var16 -= 3;
                        } else if (var15 == var13) {
                           var16 += 3;
                        }

                        BlockHugeMushroom.EnumType var17 = BlockHugeMushroom.EnumType.byMetadata(var16);
                        if (this.mushroomType == Blocks.brown_mushroom_block || var21 < var3.getY() + var4) {
                           if ((var14 == var25 || var14 == var26) && (var15 == var12 || var15 == var13)) {
                              continue;
                           }

                           if (var14 == var3.getX() - (var23 - 1) && var15 == var12) {
                              var17 = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (var14 == var25 && var15 == var3.getZ() - (var23 - 1)) {
                              var17 = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (var14 == var3.getX() + (var23 - 1) && var15 == var12) {
                              var17 = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (var14 == var26 && var15 == var3.getZ() - (var23 - 1)) {
                              var17 = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (var14 == var3.getX() - (var23 - 1) && var15 == var13) {
                              var17 = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (var14 == var25 && var15 == var3.getZ() + (var23 - 1)) {
                              var17 = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (var14 == var3.getX() + (var23 - 1) && var15 == var13) {
                              var17 = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }

                           if (var14 == var26 && var15 == var3.getZ() + (var23 - 1)) {
                              var17 = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }
                        }

                        if (var17 == BlockHugeMushroom.EnumType.CENTER && var21 < var3.getY() + var4) {
                           var17 = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        }

                        if (var3.getY() >= var3.getY() + var4 - 1 || var17 != BlockHugeMushroom.EnumType.ALL_INSIDE) {
                           BlockPos var18 = new BlockPos(var14, var21, var15);
                           if (!var1.getBlockState(var18).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(var1, var18, this.mushroomType.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, var17));
                           }
                        }
                     }
                  }
               }

               for (int var22 = 0; var22 < var4; var22++) {
                  Block var24 = var1.getBlockState(var3.up(var22)).getBlock();
                  if (!var24.isFullBlock()) {
                     this.setBlockAndNotifyAdequately(
                        var1, var3.up(var22), this.mushroomType.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.STEM)
                     );
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
