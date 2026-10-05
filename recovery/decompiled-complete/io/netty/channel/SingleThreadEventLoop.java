package io.netty.channel;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import java.util.concurrent.ThreadFactory;
import net.minecraft.entity.ai.EntityAISwimming;

public abstract class SingleThreadEventLoop extends SingleThreadEventExecutor implements EventLoop {
   public HttpPostRequestEncoder __junk9054993251394039515;
   public EntityAISwimming __junk5680960774575704374;

   public SingleThreadEventLoop(EventLoopGroup var1, ThreadFactory var2, boolean var3) {
      super(var1, var2, var3);
   }

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else if (var2 == null) {
         throw new NullPointerException("promise");
      } else {
         var1.unsafe().register(this, var2);
         return var2;
      }
   }

   @Override
   public ChannelFuture register(Channel var1) {
      return this.register(var1, new DefaultChannelPromise(var1, this));
   }

   @Override
   public EventLoop next() {
      return (EventLoop)super.next();
   }

   @Override
   public EventLoopGroup parent() {
      return (EventLoopGroup)super.parent();
   }

   @Override
   public boolean wakesUpForTask(Runnable var1) {
      return !(var1 instanceof SingleThreadEventLoop$NonWakeupRunnable);
   }
}
