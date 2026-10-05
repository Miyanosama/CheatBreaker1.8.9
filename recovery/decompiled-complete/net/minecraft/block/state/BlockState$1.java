package net.minecraft.block.state;

import com.google.common.base.Function;
import net.minecraft.block.properties.IProperty;

public class BlockState$1 implements Function<IProperty, String> {
   public String apply(IProperty var1) {
      return var1 == null ? "<NULL>" : var1.getName();
   }
}
