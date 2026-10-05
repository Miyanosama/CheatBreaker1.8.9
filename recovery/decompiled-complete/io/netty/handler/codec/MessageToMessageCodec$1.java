package io.netty.handler.codec;

import io.netty.channel.ChannelHandlerContext;
import java.util.List;

public class MessageToMessageCodec$1 extends MessageToMessageEncoder<Object> {
   public MessageToMessageCodec$1(MessageToMessageCodec var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return this.this$0.acceptOutboundMessage(var1);
   }

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, List<Object> var3) {
      this.this$0.encode(var1, var2, var3);
   }
}
