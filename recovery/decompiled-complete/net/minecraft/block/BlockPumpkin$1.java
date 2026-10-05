package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.buffer.AbstractDerivedByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;

public class BlockPumpkin$1 implements Predicate<IBlockState> {
   public AbstractDerivedByteBuf field_0000;
   public EntitySpider field_0001;

   public boolean apply(IBlockState var1) {
      return var1 != null && (var1.getBlock() == Blocks.pumpkin || var1.getBlock() == Blocks.lit_pumpkin);
   }
}
