package io.netty.handler.codec.string;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.Charset;
import java.util.List;
import net.minecraft.server.management.PlayerManager;
import com.cheatbreaker.client.util.server.HypixelAutoTipTask;

public class StringDecoder extends MessageToMessageDecoder<ByteBuf> {
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

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      var3.add(var2.toString(this.charset));
   }
}
