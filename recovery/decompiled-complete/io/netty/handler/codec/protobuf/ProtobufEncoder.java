package io.netty.handler.codec.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.MessageLite.Builder;
import io.netty.buffer.Unpooled;
import io.netty.channel.AdaptiveRecvByteBufAllocator$HandleImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.optifine.Lagometer;
import org.apache.log4j.ConsoleAppender$SystemErrStream;
import org.apache.log4j.Dispatcher;
import org.apache.log4j.helpers.FileWatchdog;

public class ProtobufEncoder extends MessageToMessageEncoder<MessageLiteOrBuilder> {
   public Lagometer __junk2072320088144875136;
   public DefaultVertexFormats __junk641422909396051015;
   public FileWatchdog __junk5494711583505252350;
   public ConsoleAppender$SystemErrStream __junk6915394255637144104;
   public AdaptiveRecvByteBufAllocator$HandleImpl __junk2596363677129905259;
   public Dispatcher __junk5502810916319542012;

   public void encode(ChannelHandlerContext var1, MessageLiteOrBuilder var2, List<Object> var3) {
      if (var2 instanceof MessageLite) {
         var3.add(Unpooled.wrappedBuffer(((MessageLite)var2).toByteArray()));
      } else {
         if (var2 instanceof Builder) {
            var3.add(Unpooled.wrappedBuffer(((Builder)var2).build().toByteArray()));
         }
      }
   }
}
