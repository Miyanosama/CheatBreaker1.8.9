package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import junit.swingui.TestSelector$DoubleClickListener;
import net.optifine.RandomEntityProperties;
import org.java_websocket.enums.Opcode;

public class AbstractChannelHandlerContext$15 extends OneTimeTask {
   public TestSelector$DoubleClickListener __junk1900702733507239397;
   public RandomEntityProperties __junk2328237888721669896;
   public Opcode __junk8960621169241230412;

   public AbstractChannelHandlerContext$15(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1400(this.val$next, this.val$promise);
   }
}
