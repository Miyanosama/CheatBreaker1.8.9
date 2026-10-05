package io.netty.channel;

import com.cheatbreaker.client.module.type.ReachDisplayModule;
import io.netty.util.concurrent.EventExecutorGroup;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.world.gen.MapGenCavesHell;
import com.cheatbreaker.client.event.type.KeyPressEvent;

public class DefaultChannelHandlerContext extends AbstractChannelHandlerContext {
   public ChannelHandler handler;

   public DefaultChannelHandlerContext(DefaultChannelPipeline var1, EventExecutorGroup var2, String var3, ChannelHandler var4) {
      super(var1, var2, var3, isInbound(var4), isOutbound(var4));
      if (var4 == null) {
         throw new NullPointerException("handler");
      } else {
         this.handler = var4;
      }
   }

   public static boolean isOutbound(ChannelHandler var0) {
      return var0 instanceof ChannelOutboundHandler;
   }

   public static boolean isInbound(ChannelHandler var0) {
      return var0 instanceof ChannelInboundHandler;
   }

   @Override
   public ChannelHandler handler() {
      return this.handler;
   }
}
