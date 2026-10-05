package io.netty.handler.codec.sctp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import org.apache.log4j.spi.LoggingEvent;

public class SctpOutboundByteStreamHandler extends MessageToMessageEncoder<ByteBuf> {
   public int protocolIdentifier;
   public int streamIdentifier;

   public SctpOutboundByteStreamHandler(int var1, int var2) {
      this.streamIdentifier = var1;
      this.protocolIdentifier = var2;
   }

   public void encode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      var3.add(new SctpMessage(this.streamIdentifier, this.protocolIdentifier, var2.retain()));
   }
}
