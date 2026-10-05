package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageDeserializer extends ByteToMessageDecoder {
   public static Logger logger = LogManager.getLogger();
   public EnumPacketDirection recoveredField2428;
   public static Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.logMarkerPackets);

   public MessageDeserializer(EnumPacketDirection var1) {
      this.recoveredField2428 = var1;
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (var2.readableBytes() != 0) {
         PacketBuffer var4 = new PacketBuffer(var2);
         int var5 = var4.readVarIntFromBuffer();
         Packet var6 = var1.channel().attr(NetworkManager.recoveredField2251).get().getPacket(this.recoveredField2428, var5);
         if (var6 == null) {
            throw new IOException("Bad packet id " + var5);
         }

         var6.readPacketData(var4);
         if (var4.readableBytes() > 0) {
            throw new IOException(
               "Packet "
                  + var1.channel().attr(NetworkManager.recoveredField2251).get().getId()
                  + "/"
                  + var5
                  + " ("
                  + var6.getClass().getSimpleName()
                  + ") was larger than I expected, found "
                  + var4.readableBytes()
                  + " bytes extra whilst reading packet "
                  + var5
            );
         }

         var3.add(var6);
         if (logger.isDebugEnabled()) {
            logger.debug(
               RECEIVED_PACKET_MARKER, " IN: [{}:{}] {}", var1.channel().attr(NetworkManager.recoveredField2251).get(), var5, var6.getClass().getName()
            );
         }
      }
   }
}
