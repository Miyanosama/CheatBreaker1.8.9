package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockDirt$DirtType;
import org.apache.log4j.pattern.ThreadPatternConverter;

public class Item$10 implements Function<ItemStack, String> {
   public ThreadPatternConverter field_0000;

   public String apply(ItemStack var1) {
      return BlockDirt$DirtType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
