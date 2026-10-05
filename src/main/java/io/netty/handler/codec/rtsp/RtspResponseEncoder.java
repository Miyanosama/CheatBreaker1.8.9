package io.netty.handler.codec.rtsp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.world.gen.layer.GenLayerSmooth;

public class RtspResponseEncoder extends RtspObjectEncoder<HttpResponse> {
   public static byte[] CRLF = new byte[]{13, 10};

   @Override
   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return var1 instanceof FullHttpResponse;
   }

   public void encodeInitialLine(ByteBuf var1, HttpResponse var2) throws java.lang.Exception {
      HttpHeaders.encodeAscii(var2.getProtocolVersion().toString(), var1);
      var1.writeByte(32);
      var1.writeBytes(String.valueOf(var2.getStatus().code()).getBytes(CharsetUtil.US_ASCII));
      var1.writeByte(32);
      encodeAscii(String.valueOf(var2.getStatus().reasonPhrase()), var1);
      var1.writeBytes(CRLF);
   }
}
