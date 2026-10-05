package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureMap$1;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import recovered.unidentified.UnidentifiedClass1216;

public class MessageSerializer extends MessageToByteEncoder<Packet> {
   public EnumPacketDirection field_0003;
   public static Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_SENT", NetworkManager.logMarkerPackets);
   public TextureMap$1 field_0002;
   public UnidentifiedClass1216 field_0004;
   public VertexBufferUploader field_0000;
   public static Logger logger = LogManager.getLogger();

   public MessageSerializer(EnumPacketDirection var1) {
      this.field_0003 = var1;
   }

   public void encode(ChannelHandlerContext var1, Packet var2, ByteBuf var3) {
      Integer var4 = var1.channel().attr(NetworkManager.field_0001).get().getPacketId(this.field_0003, var2);
      if (logger.isDebugEnabled()) {
         logger.debug(
            RECEIVED_PACKET_MARKER, "OUT: [{}:{}] {}", new Object[]{var1.channel().attr(NetworkManager.field_0001).get(), var4, var2.getClass().getName()}
         );
      }

      if (var4 == null) {
         throw new IOException("Can't serialize unregistered packet");
      } else {
         PacketBuffer var5 = new PacketBuffer(var3);
         var5.writeVarIntToBuffer(var4);

         try {
            var2.writePacketData(var5);
         } catch (Throwable var7) {
            logger.error(var7);
         }
      }
   }
}
