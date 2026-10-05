package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;

public class NettyEncryptingDecoder extends MessageToMessageDecoder<ByteBuf> {
   public NettyEncryptionTranslator recoveredField3644;

   public NettyEncryptingDecoder(Cipher var1) {
      this.recoveredField3644 = new NettyEncryptionTranslator(var1);
   }

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      var3.add(this.recoveredField3644.decipher(var1, var2));
   }
}
