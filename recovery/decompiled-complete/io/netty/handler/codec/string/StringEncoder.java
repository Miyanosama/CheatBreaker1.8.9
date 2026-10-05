package io.netty.handler.codec.string;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.AbstractChannelHandlerContext$17;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1;
import io.netty.util.concurrent.DefaultPromise$5;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight;

public class StringEncoder extends MessageToMessageEncoder<CharSequence> {
   public DefaultPromise$5 __junk1640762077838964704;
   public WebSocket08FrameDecoder$1 __junk3366607688948506409;
   public StructureVillagePieces$PieceWeight __junk5125603249495494609;
   public Charset charset;
   public AbstractChannelHandlerContext$17 __junk8161917022068960641;

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

   public void encode(ChannelHandlerContext var1, CharSequence var2, List<Object> var3) {
      if (var2.length() != 0) {
         var3.add(ByteBufUtil.encodeString(var1.alloc(), CharBuffer.wrap(var2), this.charset));
      }
   }
}
