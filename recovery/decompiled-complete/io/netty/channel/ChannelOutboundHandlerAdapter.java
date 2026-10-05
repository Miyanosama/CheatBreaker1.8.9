package io.netty.channel;

import io.netty.handler.codec.compression.SnappyFramedDecoder$1;
import java.net.SocketAddress;
import net.minecraft.client.gui.stream.GuiIngestServers$ServerList;
import net.minecraft.network.play.client.C02PacketUseEntity$Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;

public class ChannelOutboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelOutboundHandler {
   public GuiIngestServers$ServerList __junk3230578733552747290;
   public ChatComponentText __junk3508113522391144764;
   public SnappyFramedDecoder$1 __junk6564517881443778030;
   public C02PacketUseEntity$Action __junk9082661137950487529;
   public GenLayerDeepOcean __junk8851149043280539020;

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.disconnect(var2);
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.deregister(var2);
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) {
      var1.connect(var2, var3, var4);
   }

   @Override
   public void read(ChannelHandlerContext var1) {
      var1.read();
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.close(var2);
   }

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) {
      var1.bind(var2, var3);
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      var1.flush();
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      var1.write(var2, var3);
   }
}
