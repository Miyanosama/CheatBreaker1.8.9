package io.netty.handler.codec.bytes;

import com.cheatbreaker.client.util.LegacyClientCrashReporter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import com.cheatbreaker.client.nethandler.server.PacketRemoveHologram;

public class ByteArrayEncoder extends MessageToMessageEncoder<byte[]> {

   public void encode(ChannelHandlerContext var1, byte[] var2, List<Object> var3) throws java.lang.Exception {
      var3.add(Unpooled.wrappedBuffer(var2));
   }
}
