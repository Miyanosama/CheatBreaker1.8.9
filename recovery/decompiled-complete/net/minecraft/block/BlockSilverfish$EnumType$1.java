package net.minecraft.block;

import io.netty.handler.codec.CodecException;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.Blocks;
import net.optifine.entity.model.anim.RenderEntityParameterBool;

public enum BlockSilverfish$EnumType$1 {
   public RenderEntityParameterBool field_0001;
   public PositionedSoundRecord field_0002;
   public CodecException field_0000;

   public BlockSilverfish$EnumType$1(int var3, String var4) {
   }

   @Override
   public IBlockState getModelBlock() {
      return Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone$EnumType.STONE);
   }
}
