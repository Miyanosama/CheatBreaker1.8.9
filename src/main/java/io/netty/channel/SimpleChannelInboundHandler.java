package io.netty.channel;

import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.TypeParameterMatcher;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.world.WorldProviderSurface;

public abstract class SimpleChannelInboundHandler<I> extends ChannelInboundHandlerAdapter {
   public boolean autoRelease;
   public TypeParameterMatcher matcher;

   public boolean acceptInboundMessage(Object var1) throws java.lang.Exception {
      return this.matcher.match(var1);
   }

   public abstract void channelRead0(ChannelHandlerContext var1, I var2) throws java.lang.Exception ;

   public SimpleChannelInboundHandler() {
      this(true);
   }

   public SimpleChannelInboundHandler(Class<? extends I> var1, boolean var2) {
      this.matcher = TypeParameterMatcher.get(var1);
      this.autoRelease = var2;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      boolean var3 = true;

      try {
         if (this.acceptInboundMessage(var2)) {
            this.channelRead0(var1, (I)var2);
         } else {
            var3 = false;
            var1.fireChannelRead(var2);
         }
      } finally {
         if (this.autoRelease && var3) {
            ReferenceCountUtil.release(var2);
         }
      }
   }

   public SimpleChannelInboundHandler(boolean var1) {
      this.matcher = TypeParameterMatcher.find(this, SimpleChannelInboundHandler.class, "I");
      this.autoRelease = var1;
   }

   public SimpleChannelInboundHandler(Class<? extends I> var1) {
      this(var1, true);
   }
}
