package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1Stereo;
import net.optifine.CustomGuiProperties;

public class NettyEncryptingDecoder extends MessageToMessageDecoder<ByteBuf> {
   public LayerIDecoder$SubbandLayer1Stereo field_0001;
   public NettyEncryptionTranslator field_0002;
   public CustomGuiProperties field_0000;

   public NettyEncryptingDecoder(Cipher var1) {
      this.field_0002 = new NettyEncryptionTranslator(var1);
   }

   public void method_01123(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      var3.add(this.field_0002.decipher(var1, var2));
   }
}
