package io.netty.handler.codec;

import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.DefaultProgressivePromise;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;
import net.minecraft.crash.CrashReportCategory;
import net.optifine.entity.model.ModelAdapterSign;

public abstract class MessageToMessageCodec<INBOUND_IN, OUTBOUND_IN> extends ChannelDuplexHandler {
   public TypeParameterMatcher inboundMsgMatcher;
   public MessageToMessageEncoder<Object> encoder = new MessageToMessageEncoder<Object>() {
      @Override
      public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
         return MessageToMessageCodec.this.acceptOutboundMessage(var1);
      }

      @Override
      public void encode(ChannelHandlerContext var1, Object var2, List<Object> var3) throws java.lang.Exception {
         MessageToMessageCodec.this.encode(var1, (OUTBOUND_IN)var2, var3);
      }
   };
   public MessageToMessageDecoder<Object> decoder = new MessageToMessageDecoder<Object>() {

      @Override
      public void decode(ChannelHandlerContext var1, Object var2, List<Object> var3) throws java.lang.Exception {
         MessageToMessageCodec.this.decode(var1, (INBOUND_IN)var2, var3);
      }

      @Override
      public boolean acceptInboundMessage(Object var1) throws java.lang.Exception {
         return MessageToMessageCodec.this.acceptInboundMessage(var1);
      }
   };
   public TypeParameterMatcher outboundMsgMatcher;

   public abstract void encode(ChannelHandlerContext var1, OUTBOUND_IN var2, List<Object> var3) throws java.lang.Exception ;

   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return this.outboundMsgMatcher.match(var1);
   }

   public MessageToMessageCodec() {
      this.inboundMsgMatcher = TypeParameterMatcher.find(this, MessageToMessageCodec.class, "INBOUND_IN");
      this.outboundMsgMatcher = TypeParameterMatcher.find(this, MessageToMessageCodec.class, "OUTBOUND_IN");
   }

   public abstract void decode(ChannelHandlerContext var1, INBOUND_IN var2, List<Object> var3) throws java.lang.Exception ;

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      this.encoder.write(var1, var2, var3);
   }

   public MessageToMessageCodec(Class<? extends INBOUND_IN> var1, Class<? extends OUTBOUND_IN> var2) {
      this.inboundMsgMatcher = TypeParameterMatcher.get(var1);
      this.outboundMsgMatcher = TypeParameterMatcher.get(var2);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      this.decoder.channelRead(var1, var2);
   }

   public boolean acceptInboundMessage(Object var1) throws java.lang.Exception {
      return this.inboundMsgMatcher.match(var1);
   }
}
