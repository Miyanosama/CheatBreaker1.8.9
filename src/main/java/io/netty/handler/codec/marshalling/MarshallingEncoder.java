package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.world.biome.BiomeGenBase;
import org.java_websocket.server.DefaultWebSocketServerFactory;
import org.jboss.marshalling.Marshaller;

public class MarshallingEncoder extends MessageToByteEncoder<Object> {
   public MarshallerProvider provider;
   public static byte[] LENGTH_PLACEHOLDER = new byte[4];

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, ByteBuf var3) throws java.lang.Exception {
      Marshaller var4 = this.provider.getMarshaller(var1);
      int var5 = var3.writerIndex();
      var3.writeBytes(LENGTH_PLACEHOLDER);
      ChannelBufferByteOutput var6 = new ChannelBufferByteOutput(var3);
      var4.start(var6);
      var4.writeObject(var2);
      var4.finish();
      var4.close();
      var3.setInt(var5, var3.writerIndex() - var5 - 4);
   }

   public MarshallingEncoder(MarshallerProvider var1) {
      this.provider = var1;
   }
}
