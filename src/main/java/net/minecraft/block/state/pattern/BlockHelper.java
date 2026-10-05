package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockHelper implements Predicate<IBlockState> {
   public Block block;

   public boolean apply(IBlockState var1) {
      return var1 != null && var1.getBlock() == this.block;
   }

   public BlockHelper(Block var1) {
      this.block = var1;
   }

   public static BlockHelper forBlock(Block var0) {
      return new BlockHelper(var0);
   }
}
