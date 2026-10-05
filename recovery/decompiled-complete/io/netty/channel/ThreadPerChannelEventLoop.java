package io.netty.channel;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import net.minecraft.util.HttpUtil;
import net.optifine.entity.model.ModelAdapterRabbit;
import org.apache.log4j.AsyncAppender$Dispatcher;

public class ThreadPerChannelEventLoop extends SingleThreadEventLoop {
   public AsyncAppender$Dispatcher __junk3127188957648168059;
   public WebSocketClientHandshaker08 __junk5597881853161786896;
   public ThreadPerChannelEventLoopGroup parent;
   public Channel ch;
   public ModelAdapterRabbit __junk1629318788701565932;
   public HttpUtil __junk545532994985317173;

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      return super.register(var1, var2).addListener(new ThreadPerChannelEventLoop$1(this));
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
