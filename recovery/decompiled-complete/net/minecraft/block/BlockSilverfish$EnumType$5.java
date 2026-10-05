package net.minecraft.block;

import io.netty.buffer.ByteBufProcessor$10;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.init.Blocks;

public enum BlockSilverfish$EnumType$5 {
   public ByteBufProcessor$10 field_0000;
   public JsonBlendingMode field_0001;

   public BlockSilverfish$EnumType$5(int var3, String var4, String var5) {
   }

   @Override
   public IBlockState getModelBlock() {
      return Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick$EnumType.CRACKED);
   }
}
