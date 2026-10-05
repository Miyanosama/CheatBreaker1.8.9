package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.item.ItemMinecart;
import org.newsclub.net.unix.AFUNIXSocketImpl$Lenient;

public class AbstractChannelHandlerContext$7 extends OneTimeTask {
   public ItemMinecart __junk4272644402226148038;
   public AFUNIXSocketImpl$Lenient __junk5924679562895105503;

   public AbstractChannelHandlerContext$7(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, Object var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$event = var3;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$600(this.val$next, this.val$event);
   }
}
