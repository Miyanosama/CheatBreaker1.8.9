package io.netty.channel;

import com.cheatbreaker.client.module.type.ReachDisplayModule;
import io.netty.handler.timeout.IdleStateHandler$1;
import io.netty.util.concurrent.EventExecutorGroup;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.scoreboard.Score$1;
import net.minecraft.world.gen.MapGenCavesHell;
import recovered.unidentified.UnidentifiedClass0806;

public class DefaultChannelHandlerContext extends AbstractChannelHandlerContext {
   public MapGenCavesHell __junk5922009645472905610;
   public ChannelHandler handler;
   public UnidentifiedClass0806 __junk521468534506207816;
   public ReachDisplayModule __junk59768617034929324;
   public Score$1 __junk2815365249817450463;
   public SoundPoolEntry __junk3596375146119522823;
   public IdleStateHandler$1 __junk914445535208292470;

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
