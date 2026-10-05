package io.netty.channel;

import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import io.netty.channel.sctp.SctpChannelOption;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.util.internal.OneTimeTask;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$8;

public class AbstractChannelHandlerContext$4 extends OneTimeTask {
   public LogBrokerMonitor$8 __junk1235290429634880558;
   public SctpChannelOption __junk1768720021205927025;
   public ChannelInputShutdownEvent __junk558743976249733019;
   public AbstractModulesGuiElement __junk7384703904877036433;

   public AbstractChannelHandlerContext$4(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$300(this.val$next);
   }
}
