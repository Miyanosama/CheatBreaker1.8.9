package io.netty.handler.codec.string;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class StringEncoder extends MessageToMessageEncoder<CharSequence> {
   public Charset charset;

   public StringEncoder() {
      this(Charset.defaultCharset());
   }

   public StringEncoder(Charset var1) {
      if (var1 == null) {
         throw new NullPointerException("charset");
      } else {
         this.charset = var1;
      }
   }

   public void encode(ChannelHandlerContext var1, CharSequence var2, List<Object> var3) throws java.lang.Exception {
      if (var2.length() != 0) {
         var3.add(ByteBufUtil.encodeString(var1.alloc(), CharBuffer.wrap(var2), this.charset));
      }
   }
}
