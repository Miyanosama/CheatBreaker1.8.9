package io.netty.handler.codec;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;
import net.optifine.entity.model.ModelAdapterSign;

public abstract class MessageToMessageCodec<INBOUND_IN, OUTBOUND_IN> extends ChannelDuplexHandler {
   public TypeParameterMatcher inboundMsgMatcher;
   public MessageToMessageEncoder<Object> encoder = new MessageToMessageCodec$1(this);
   public MessageToMessageDecoder<Object> decoder = new MessageToMessageCodec$2(this);
   public ModelAdapterSign __junk3371784881100728643;
   public TypeParameterMatcher outboundMsgMatcher;

   public abstract void encode(ChannelHandlerContext var1, OUTBOUND_IN var2, List<Object> var3);

   public boolean acceptOutboundMessage(Object var1) {
      return this.outboundMsgMatcher.match(var1);
   }

   public MessageToMessageCodec() {
      this.inboundMsgMatcher = TypeParameterMatcher.find(this, MessageToMessageCodec.class, "INBOUND_IN");
      this.outboundMsgMatcher = TypeParameterMatcher.find(this, MessageToMessageCodec.class, "OUTBOUND_IN");
   }

   public abstract void decode(ChannelHandlerContext var1, INBOUND_IN var2, List<Object> var3);

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.encoder.write(var1, var2, var3);
   }

   public MessageToMessageCodec(Class<? extends INBOUND_IN> var1, Class<? extends OUTBOUND_IN> var2) {
      this.inboundMsgMatcher = TypeParameterMatcher.get(var1);
      this.outboundMsgMatcher = TypeParameterMatcher.get(var2);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.decoder.channelRead(var1, var2);
   }

   public boolean acceptInboundMessage(Object var1) {
      return this.inboundMsgMatcher.match(var1);
   }
}
