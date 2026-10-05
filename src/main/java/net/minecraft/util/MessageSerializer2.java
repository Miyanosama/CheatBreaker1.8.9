package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.network.PacketBuffer;

public class MessageSerializer2 extends MessageToByteEncoder<ByteBuf> {
   public void encode(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) throws java.lang.Exception {
      int var4 = var2.readableBytes();
      int var5 = PacketBuffer.getVarIntSize(var4);
      if (var5 > 3) {
         throw new IllegalArgumentException("unable to fit " + var4 + " into " + 3);
      } else {
         PacketBuffer var6 = new PacketBuffer(var3);
         var6.ensureWritable(var5 + var4);
         var6.writeVarIntToBuffer(var4);
         var6.writeBytes(var2, var2.readerIndex(), var4);
      }
   }
}
