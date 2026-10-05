package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import javax.crypto.Cipher;

public class NettyEncryptingEncoder extends MessageToByteEncoder<ByteBuf> {
   public NettyEncryptionTranslator recoveredField2532;

   public void encode(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) throws java.lang.Exception {
      this.recoveredField2532.cipher(var2, var3);
   }

   public NettyEncryptingEncoder(Cipher var1) {
      this.recoveredField2532 = new NettyEncryptionTranslator(var1);
   }
}
