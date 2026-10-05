package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenerator {
   public boolean doBlockNotify;

   public void func_175904_e() {
   }

   public abstract boolean generate(World var1, Random var2, BlockPos var3);

   public WorldGenerator(boolean var1) {
      this.doBlockNotify = var1;
   }

   public WorldGenerator() {
      this(false);
   }

   public void setBlockAndNotifyAdequately(World var1, BlockPos var2, IBlockState var3) {
      if (this.doBlockNotify) {
         var1.a(var2, var3, 3);
      } else {
         var1.a(var2, var3, 2);
      }
   }
}
