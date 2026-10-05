package io.netty.handler.codec.sctp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import net.minecraft.realms.RealmsBufferBuilder;

public class SctpInboundByteStreamHandler extends MessageToMessageDecoder<SctpMessage> {
   public int protocolIdentifier;
   public RealmsBufferBuilder __junk7286948346752117988;
   public int streamIdentifier;

   public SctpInboundByteStreamHandler(int var1, int var2) {
      this.protocolIdentifier = var1;
      this.streamIdentifier = var2;
   }

   public void decode(ChannelHandlerContext var1, SctpMessage var2, List<Object> var3) {
      if (!var2.isComplete()) {
         throw new CodecException(
            String.format(
               "Received SctpMessage is not complete, please add %s in the pipeline before this handler", SctpMessageCompletionHandler.class.getSimpleName()
            )
         );
      } else {
         var3.add(var2.content().retain());
      }
   }

   public boolean acceptInboundMessage(SctpMessage var1) {
      return var1.protocolIdentifier() == this.protocolIdentifier && var1.streamIdentifier() == this.streamIdentifier;
   }

   @Override
   public boolean acceptInboundMessage(Object var1) {
      return super.acceptInboundMessage(var1) ? this.acceptInboundMessage((SctpMessage)var1) : false;
   }
}
