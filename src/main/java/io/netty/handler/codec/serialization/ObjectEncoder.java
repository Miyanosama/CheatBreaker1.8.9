package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.socks.SocksMessage;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import java.io.Serializable;

public class ObjectEncoder extends MessageToByteEncoder<Serializable> {
   public static byte[] LENGTH_PLACEHOLDER = new byte[4];

   public void encode(ChannelHandlerContext var1, Serializable var2, ByteBuf var3) throws java.lang.Exception {
      int var4 = var3.writerIndex();
      ByteBufOutputStream var5 = new ByteBufOutputStream(var3);
      var5.write(LENGTH_PLACEHOLDER);
      CompactObjectOutputStream var6 = new CompactObjectOutputStream(var5);
      var6.writeObject(var2);
      var6.flush();
      var6.close();
      int var7 = var3.writerIndex();
      var3.setInt(var4, var7 - var4 - 4);
   }
}
