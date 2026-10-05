package io.netty.handler.codec.bytes;

import com.cheatbreaker.client.util.LegacyClientCrashReporter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import recovered.unidentified.UnidentifiedClass1748;

public class ByteArrayEncoder extends MessageToMessageEncoder<byte[]> {
   public LegacyClientCrashReporter __junk1347327536502791653;
   public UnidentifiedClass1748 __junk280012751387695996;

   public void encode(ChannelHandlerContext var1, byte[] var2, List<Object> var3) {
      var3.add(Unpooled.wrappedBuffer(var2));
   }
}
