package io.netty.channel.nio;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.SingleThreadEventExecutor$PurgeTask;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$16;
import recovered.unidentified.UnidentifiedClass4999;

public class AbstractNioChannel$AbstractNioUnsafe$2 implements ChannelFutureListener {
   public SslHandler __junk2654284018221827926;
   public SingleThreadEventExecutor$PurgeTask __junk2395635669530738173;
   public UnidentifiedClass4999 __junk4662587710358634439;
   public LogBrokerMonitor$16 __junk2546588000126624373;

   public AbstractNioChannel$AbstractNioUnsafe$2(AbstractNioChannel$AbstractNioUnsafe var1) {
      this.this$1 = var1;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (var1.isCancelled()) {
         if (AbstractNioChannel.access$200(this.this$1.this$0) != null) {
            AbstractNioChannel.access$200(this.this$1.this$0).cancel(false);
         }

         AbstractNioChannel.access$002(this.this$1.this$0, null);
         this.this$1.close(this.this$1.voidPromise());
      }
   }
}
