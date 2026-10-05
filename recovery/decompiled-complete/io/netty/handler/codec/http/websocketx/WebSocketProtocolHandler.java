package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.enchantment.EnchantmentArrowDamage;

public abstract class WebSocketProtocolHandler extends MessageToMessageDecoder<WebSocketFrame> {
   public ModelSpider __junk6375883585311523367;
   public EnchantmentArrowDamage __junk4003831688706473692;

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      var1.close();
   }

   public void decode(ChannelHandlerContext var1, WebSocketFrame var2, List<Object> var3) {
      if (var2 instanceof PingWebSocketFrame) {
         var2.content().retain();
         var1.channel().writeAndFlush(new PongWebSocketFrame(var2.content()));
      } else if (!(var2 instanceof PongWebSocketFrame)) {
         var3.add(var2.retain());
      }
   }
}
