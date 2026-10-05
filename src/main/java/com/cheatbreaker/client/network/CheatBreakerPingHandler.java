package com.cheatbreaker.client.network;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import com.cheatbreaker.client.network.CBInboundChannel;

public class CheatBreakerPingHandler extends ChannelInboundHandlerAdapter {
   public CBInboundChannel recoveredField2480;
   public long recoveredField2481;
   public long recoveredField2482 = System.nanoTime() - 30000000000L;
   public Minecraft recoveredField2483 = Minecraft.getMinecraft();

   public CheatBreakerPingHandler(CBInboundChannel var1) {
      this.recoveredField2481 = 0L;
      this.recoveredField2480 = var1;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.io.IOException, java.lang.Exception {
      S3FPacketCustomPayload var3;
      if (var2 instanceof S3FPacketCustomPayload && (var3 = (S3FPacketCustomPayload)var2).getChannelName().equals("CB|PING")) {
         this.recoveredField2482 = System.nanoTime();
         long var4 = new DataInputStream(new ByteArrayInputStream(var3.getBufferData().readByteArray())).readInt() & 4294967295L;
         if (var4 != this.recoveredField2480.method_24511()) {
            throw new IOException("CheatBreaker Protocol Error -2a\n(Try updating your client)");
         }

         ByteArrayOutputStream var6 = new ByteArrayOutputStream();
         DataOutputStream var7 = new DataOutputStream(var6);

         try {
            var7.write(var3.getBufferData().readByteArray());
            if ((this.recoveredField2481++ & 7L) == 0L) {
               var7.writeLong(this.recoveredField2481);
               var7.writeUTF(this.recoveredField2483.currentServerData.serverIP);
               var7.writeUTF(this.recoveredField2483.getSession().getPlayerID());
               var7.writeUTF(this.recoveredField2483.entityRenderer.getClass().getName());
            }
         } catch (IOException var9) {
         }

         PacketBuffer var8 = new PacketBuffer(Unpooled.buffer());
         var8.writeBytes(var6.toByteArray());
         var1.channel().eventLoop().execute(() -> var1.channel().writeAndFlush(new CustomPayloadSender("CB|PONG", var8)));
      }

      if (System.nanoTime() - this.recoveredField2482 > 45000000000L) {
         throw new IOException("CheatBreaker Protocol Error -2b\n(Try updating your client)");
      } else {
         super.channelRead(var1, var2);
      }
   }
}
