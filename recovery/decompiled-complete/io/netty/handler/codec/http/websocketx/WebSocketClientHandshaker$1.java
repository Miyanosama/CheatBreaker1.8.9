package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.local.LocalServerChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpRequestEncoder;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.realms.RealmsClickableScrolledSelectionList;

public class WebSocketClientHandshaker$1 implements ChannelFutureListener {
   public RealmsClickableScrolledSelectionList __junk5650124642844452162;
   public BaseAttribute __junk4827162847831026835;
   public LocalServerChannel __junk7789669330120195101;

   public void operationComplete(ChannelFuture var1) {
      if (var1.isSuccess()) {
         ChannelPipeline var2 = var1.channel().pipeline();
         ChannelHandlerContext var3 = var2.context(HttpRequestEncoder.class);
         if (var3 == null) {
            var3 = var2.context(HttpClientCodec.class);
         }

         if (var3 == null) {
            this.val$promise.setFailure(new IllegalStateException("ChannelPipeline does not contain a HttpRequestEncoder or HttpClientCodec"));
            return;
         }

         var2.addAfter(var3.name(), "ws-encoder", this.this$0.newWebSocketEncoder());
         this.val$promise.setSuccess();
      } else {
         this.val$promise.setFailure(var1.cause());
      }
   }

   public WebSocketClientHandshaker$1(WebSocketClientHandshaker var1, ChannelPromise var2) {
      this.this$0 = var1;
      this.val$promise = var2;
      super();
   }
}
