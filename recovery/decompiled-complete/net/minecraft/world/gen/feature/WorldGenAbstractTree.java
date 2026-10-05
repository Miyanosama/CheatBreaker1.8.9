package net.minecraft.world.gen.feature;

import io.netty.buffer.PooledUnsafeDirectByteBuf;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass0968;

public abstract class WorldGenAbstractTree extends WorldGenerator {
   public PooledUnsafeDirectByteBuf field_0001;
   public UnidentifiedClass0968 field_0002;
   public GuiOptionButton field_0000;

   public WorldGenAbstractTree(boolean var1) {
      super(var1);
   }

   public boolean func_150523_a(Block var1) {
      Material var2 = var1.getMaterial();
      return var2 == Material.air
         || var2 == Material.leaves
         || var1 == Blocks.grass
         || var1 == Blocks.dirt
         || var1 == Blocks.log
         || var1 == Blocks.log2
         || var1 == Blocks.sapling
         || var1 == Blocks.vine;
   }

   public void func_180711_a(World var1, Random var2, BlockPos var3) {
   }

   public void func_175921_a(World var1, BlockPos var2) {
      if (var1.getBlockState(var2).getBlock() != Blocks.dirt) {
         this.setBlockAndNotifyAdequately(var1, var2, Blocks.dirt.getDefaultState());
      }
   }
}
