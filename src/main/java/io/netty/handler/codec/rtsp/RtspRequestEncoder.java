package io.netty.handler.codec.rtsp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.handler.codec.compression.SnappyFramedEncoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;
import net.minecraft.block.BlockRedstoneTorch;

public class RtspRequestEncoder extends RtspObjectEncoder<HttpRequest> {
   public static byte[] CRLF = new byte[]{13, 10};

   public void encodeInitialLine(ByteBuf var1, HttpRequest var2) throws java.lang.Exception {
      HttpHeaders.encodeAscii(var2.getMethod().toString(), var1);
      var1.writeByte(32);
      var1.writeBytes(var2.getUri().getBytes(CharsetUtil.UTF_8));
      var1.writeByte(32);
      encodeAscii(var2.getProtocolVersion().toString(), var1);
      var1.writeBytes(CRLF);
   }

   @Override
   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return var1 instanceof FullHttpRequest;
   }
}
