package io.netty.handler.codec.sctp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesTask;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.vecmath.Point2d;
import net.minecraft.init.Bootstrap$6;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$1;
import net.optifine.player.PlayerConfigurations;

public class SctpMessageCompletionHandler extends MessageToMessageDecoder<SctpMessage> {
   public PlayerConfigurations __junk3548323118869846798;
   public Point2d __junk729549744196181939;
   public Map<Integer, ByteBuf> fragments = new HashMap<>();
   public Bootstrap$6 __junk8509539826829290384;
   public ConcurrentHashMapV8$MapReduceValuesTask __junk819179929787463214;
   public StructureMineshaftPieces$1 __junk294490390779016791;

   public void decode(ChannelHandlerContext var1, SctpMessage var2, List<Object> var3) {
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
