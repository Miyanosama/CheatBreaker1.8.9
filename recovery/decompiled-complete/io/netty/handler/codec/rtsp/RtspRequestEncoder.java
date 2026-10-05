package io.netty.handler.codec.rtsp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe;
import io.netty.handler.codec.compression.SnappyFramedEncoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;
import net.minecraft.block.BlockRedstoneTorch;

public class RtspRequestEncoder extends RtspObjectEncoder<HttpRequest> {
   public BlockRedstoneTorch __junk4360226467598046329;
   public static byte[] CRLF = new byte[]{13, 10};
   public AbstractNioChannel$AbstractNioUnsafe __junk7945530106396362332;
   public SnappyFramedEncoder __junk42977299178144648;

   public void encodeInitialLine(ByteBuf var1, HttpRequest var2) {
      HttpHeaders.encodeAscii(var2.getMethod().toString(), var1);
      var1.writeByte(32);
      var1.writeBytes(var2.getUri().getBytes(CharsetUtil.UTF_8));
      var1.writeByte(32);
      encodeAscii(var2.getProtocolVersion().toString(), var1);
      var1.writeBytes(CRLF);
   }

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return var1 instanceof FullHttpRequest;
   }
}
