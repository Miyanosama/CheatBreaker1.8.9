package net.minecraft.world.gen.feature;

import java.util.Random;
import junit.runner.StandardTestSuiteLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureMap$3;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYRoom;

public abstract class WorldGenHugeTrees extends WorldGenAbstractTree {
   public int extraRandomHeight;
   public StructureOceanMonumentPieces$DoubleYRoom field_0000;
   public IBlockState leavesMetadata;
   public int a;
   public IBlockState b;
   public TextureMap$3 field_0002;
   public StandardTestSuiteLoader field_0006;

   public boolean func_175927_a(BlockPos var1, World var2) {
      BlockPos var3 = var1.down();
      Block var4 = var2.getBlockState(var3).getBlock();
      if ((var4 == Blocks.grass || var4 == Blocks.dirt) && var1.getY() >= 2) {
         this.func_175921_a(var2, var3);
         this.func_175921_a(var2, var3.east());
         this.func_175921_a(var2, var3.south());
         this.func_175921_a(var2, var3.south().east());
         return true;
      } else {
         return false;
      }
   }

   public void method_13260(World var1, BlockPos var2, int var3) {
      int var4 = var3 * var3;

      for (int var5 = -var3; var5 <= var3; var5++) {
         for (int var6 = -var3; var6 <= var3; var6++) {
            if (var5 * var5 + var6 * var6 <= var4) {
               BlockPos var7 = var2.add(var5, 0, var6);
               Material var8 = var1.getBlockState(var7).getBlock().getMaterial();
               if (var8 == Material.air || var8 == Material.leaves) {
                  this.setBlockAndNotifyAdequately(var1, var7, this.leavesMetadata);
               }
            }
         }
      }
   }

   public boolean func_175929_a(World var1, Random var2, BlockPos var3, int var4) {
      return this.func_175926_c(var1, var3, var4) && this.func_175927_a(var3, var1);
   }

   public int a(Random var1) {
      int var2 = var1.nextInt(3) + this.a;
      if (this.extraRandomHeight > 1) {
         var2 += var1.nextInt(this.extraRandomHeight);
      }

      return var2;
   }

   public WorldGenHugeTrees(boolean var1, int var2, int var3, IBlockState var4, IBlockState var5) {
      super(var1);
      this.a = var2;
      this.extraRandomHeight = var3;
      this.b = var4;
      this.leavesMetadata = var5;
   }

   public boolean func_175926_c(World var1, BlockPos var2, int var3) {
      boolean var4 = true;
      if (var2.getY() >= 1 && var2.getY() + var3 + 1 <= 256) {
         for (int var5 = 0; var5 <= 1 + var3; var5++) {
            byte var6 = 2;
            if (var5 == 0) {
               var6 = 1;
            } else if (var5 >= 1 + var3 - 2) {
               var6 = 2;
            }

            for (int var7 = -var6; var7 <= var6 && var4; var7++) {
               for (int var8 = -var6; var8 <= var6 && var4; var8++) {
                  if (var2.getY() + var5 < 0 || var2.getY() + var5 >= 256 || !this.func_150523_a(var1.getBlockState(var2.add(var7, var5, var8)).getBlock())) {
                     var4 = false;
                  }
               }
            }
         }

         return var4;
      } else {
         return false;
      }
   }

   public void a(World var1, BlockPos var2, int var3) {
      int var4 = var3 * var3;

      for (int var5 = -var3; var5 <= var3 + 1; var5++) {
         for (int var6 = -var3; var6 <= var3 + 1; var6++) {
            int var7 = var5 - 1;
            int var8 = var6 - 1;
            if (var5 * var5 + var6 * var6 <= var4
               || var7 * var7 + var8 * var8 <= var4
               || var5 * var5 + var8 * var8 <= var4
               || var7 * var7 + var6 * var6 <= var4) {
               BlockPos var9 = var2.add(var5, 0, var6);
               Material var10 = var1.getBlockState(var9).getBlock().getMaterial();
               if (var10 == Material.air || var10 == Material.leaves) {
                  this.setBlockAndNotifyAdequately(var1, var9, this.leavesMetadata);
               }
            }
         }
      }
   }
}
