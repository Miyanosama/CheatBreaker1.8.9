package io.netty.handler.codec.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.MessageLite.Builder;
import io.netty.buffer.Unpooled;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.optifine.Lagometer;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Dispatcher;
import org.apache.log4j.helpers.FileWatchdog;

public class ProtobufEncoder extends MessageToMessageEncoder<MessageLiteOrBuilder> {

   public void encode(ChannelHandlerContext var1, MessageLiteOrBuilder var2, List<Object> var3) throws java.lang.Exception {
      if (var2 instanceof MessageLite) {
         var3.add(Unpooled.wrappedBuffer(((MessageLite)var2).toByteArray()));
      } else {
         if (var2 instanceof Builder) {
            var3.add(Unpooled.wrappedBuffer(((Builder)var2).build().toByteArray()));
         }
      }
   }
}
