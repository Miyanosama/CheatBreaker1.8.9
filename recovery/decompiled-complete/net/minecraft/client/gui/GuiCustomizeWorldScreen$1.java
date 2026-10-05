package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import io.netty.channel.AbstractChannelHandlerContext$WriteTask;
import net.minecraft.util.BlockPos$MutableBlockPos;

public class GuiCustomizeWorldScreen$1 implements Predicate<String> {
   public BlockPos$MutableBlockPos field_0002;
   public AbstractChannelHandlerContext$WriteTask field_0000;

   public GuiCustomizeWorldScreen$1(GuiCustomizeWorldScreen var1) {
      this.field_178957_a = var1;
      super();
   }

   public boolean apply(String var1) {
      Float var2 = Floats.tryParse(var1);
      return var1.length() == 0 || var2 != null && Floats.isFinite(var2) && var2 >= 0.0F;
   }
}
