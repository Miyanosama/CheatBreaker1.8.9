package io.netty.channel;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import net.minecraft.util.HttpUtil;
import net.optifine.entity.model.ModelAdapterRabbit;
import org.apache.log4j.AsyncAppender;

public class ThreadPerChannelEventLoop extends SingleThreadEventLoop {
   public ThreadPerChannelEventLoopGroup parent;
   public Channel ch;

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      return super.register(var1, var2).addListener(new ChannelFutureListener() {
         public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
            if (var1.isSuccess()) {
               ThreadPerChannelEventLoop.this.ch = var1.channel();
            } else {
               ThreadPerChannelEventLoop.this.deregister();
            }
         }
      });
   }

   public ThreadPerChannelEventLoop(ThreadPerChannelEventLoopGroup var1) {
      super(var1, var1.threadFactory, true);
      this.parent = var1;
   }

   public void deregister() {
      this.ch = null;
      this.parent.activeChildren.remove(this);
      this.parent.idleChildren.add(this);
   }

   @Override
   public void run() {
      while (true) {
         Runnable var1 = this.takeTask();
         if (var1 != null) {
            var1.run();
            this.updateLastExecutionTime();
         }

         Channel var2 = this.ch;
         if (this.isShuttingDown()) {
            if (var2 != null) {
               var2.unsafe().close(var2.unsafe().voidPromise());
            }

            if (this.confirmShutdown()) {
               return;
            }
         } else if (var2 != null && !var2.isRegistered()) {
            this.runAllTasks();
            this.deregister();
         }
      }
   }
}
