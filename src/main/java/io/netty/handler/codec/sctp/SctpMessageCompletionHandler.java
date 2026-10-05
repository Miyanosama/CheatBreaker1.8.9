package io.netty.handler.codec.sctp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.vecmath.Point2d;
import net.optifine.player.PlayerConfigurations;

public class SctpMessageCompletionHandler extends MessageToMessageDecoder<SctpMessage> {
   public Map<Integer, ByteBuf> fragments = new HashMap<>();

   public void decode(ChannelHandlerContext var1, SctpMessage var2, List<Object> var3) throws java.lang.Exception {
      ByteBuf var4 = var2.content();
      int var5 = var2.protocolIdentifier();
      int var6 = var2.streamIdentifier();
      boolean var7 = var2.isComplete();
      ByteBuf var8;
      if (this.fragments.containsKey(var6)) {
         var8 = this.fragments.remove(var6);
      } else {
         var8 = Unpooled.EMPTY_BUFFER;
      }

      if (var7 && !var8.isReadable()) {
         var3.add(var2);
      } else if (!var7 && var8.isReadable()) {
         this.fragments.put(var6, Unpooled.wrappedBuffer(var8, var4));
      } else if (var7 && var8.isReadable()) {
         this.fragments.remove(var6);
         SctpMessage var9 = new SctpMessage(var5, var6, Unpooled.wrappedBuffer(var8, var4));
         var3.add(var9);
      } else {
         this.fragments.put(var6, var4);
      }

      var4.retain();
   }
}
