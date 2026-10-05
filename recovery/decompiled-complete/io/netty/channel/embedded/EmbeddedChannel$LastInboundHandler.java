package io.netty.channel.embedded;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SslHandler$4;
import net.minecraft.tileentity.TileEntityCommandBlock$1;
import net.optifine.shaders.config.ShaderOptionVariableConst;

public class EmbeddedChannel$LastInboundHandler extends ChannelInboundHandlerAdapter {
   public SslHandler$4 __junk4786321844306555140;
   public TileEntityCommandBlock$1 __junk8743807450383588507;
   public ShaderOptionVariableConst __junk3861087460015339063;

   public EmbeddedChannel$LastInboundHandler(EmbeddedChannel var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      EmbeddedChannel.access$200(this.this$0).add(var2);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      EmbeddedChannel.access$300(this.this$0, var2);
   }
}
