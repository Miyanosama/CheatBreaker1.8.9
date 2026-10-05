package io.netty.handler.codec.base64;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import net.minecraft.client.particle.EntityHugeExplodeFX$Factory;
import net.minecraft.network.play.server.S31PacketWindowProperty;

public class Base64Decoder extends MessageToMessageDecoder<ByteBuf> {
   public Base64Dialect dialect;
   public EntityHugeExplodeFX$Factory __junk5682843787461677155;
   public S31PacketWindowProperty __junk6028445470818453308;

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      var3.add(Base64.decode(var2, var2.readerIndex(), var2.readableBytes(), this.dialect));
   }

   public Base64Decoder(Base64Dialect var1) {
      if (var1 == null) {
         throw new NullPointerException("dialect");
      } else {
         this.dialect = var1;
      }
   }

   public Base64Decoder() {
      this(Base64Dialect.STANDARD);
   }
}
