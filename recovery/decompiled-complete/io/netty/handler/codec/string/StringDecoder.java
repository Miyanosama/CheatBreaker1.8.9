package io.netty.handler.codec.string;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.Charset;
import java.util.List;
import net.minecraft.server.management.PlayerManager$PlayerInstance;
import recovered.unidentified.UnidentifiedClass3624;

public class StringDecoder extends MessageToMessageDecoder<ByteBuf> {
   public PlayerManager$PlayerInstance __junk8839291397543805568;
   public UnidentifiedClass3624 __junk4113842236155264483;
   public Charset charset;

   public StringDecoder(Charset var1) {
      if (var1 == null) {
         throw new NullPointerException("charset");
      } else {
         this.charset = var1;
      }
   }

   public StringDecoder() {
      this(Charset.defaultCharset());
   }

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      var3.add(var2.toString(this.charset));
   }
}
