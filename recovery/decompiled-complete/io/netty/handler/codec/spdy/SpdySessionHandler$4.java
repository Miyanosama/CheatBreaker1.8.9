package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.module.type.NumberHudModule;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.particle.EntityAuraFX$Factory;
import org.apache.log4j.lf5.viewer.LF5SwingUtils;

public class SpdySessionHandler$4 implements ChannelFutureListener {
   public LF5SwingUtils __junk5137071843657047390;
   public NumberHudModule __junk58492172069682538;
   public EntityAuraFX$Factory __junk1391688158383105548;

   public SpdySessionHandler$4(SpdySessionHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         SpdySessionHandler.access$000(this.this$0, this.val$ctx, SpdySessionStatus.INTERNAL_ERROR);
      }
   }
}
