package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.play.client.C0BPacketEntityAction$Action;
import net.minecraft.potion.PotionAbsorption;
import net.optifine.shaders.uniform.ShaderUniforms;

public class SpdyFrameCodec$1 implements ChannelFutureListener {
   public PotionAbsorption __junk3775084360050876739;
   public ShaderUniforms __junk1741396920568623343;
   public C0BPacketEntityAction$Action __junk8294292098659877829;

   public SpdyFrameCodec$1(SpdyFrameCodec var1) {
      this.this$0 = var1;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      SpdyFrameCodec.access$000(this.this$0).end();
      SpdyFrameCodec.access$100(this.this$0).end();
   }
}
