package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import recovered.unidentified.UnidentifiedClass4818;

public enum BlockSilverfish$EnumType$6 {
   public UnidentifiedClass4818 field_0000;

   @Override
   public IBlockState getModelBlock() {
      return Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick$EnumType.CHISELED);
   }

   public BlockSilverfish$EnumType$6(int var3, String var4, String var5) {
   }
}
