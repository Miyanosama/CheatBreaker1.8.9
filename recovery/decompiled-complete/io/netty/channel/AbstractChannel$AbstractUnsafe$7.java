package io.netty.channel;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State;
import io.netty.util.internal.OneTimeTask;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$3;

public class AbstractChannel$AbstractUnsafe$7 extends OneTimeTask {
   public WebSocket08FrameDecoder$State __junk1644637181132965848;
   public LogBrokerMonitor$3 __junk987908563432104205;

   public AbstractChannel$AbstractUnsafe$7(AbstractChannel$AbstractUnsafe var1, Exception var2) {
      this.this$1 = var1;
      this.val$e = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannel.access$500(this.this$1.this$0).fireExceptionCaught(this.val$e);
   }
}
