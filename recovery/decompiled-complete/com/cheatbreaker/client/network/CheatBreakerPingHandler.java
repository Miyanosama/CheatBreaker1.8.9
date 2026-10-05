package com.cheatbreaker.client.network;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.multipart.InternalAttribute;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import recovered.unidentified.UnidentifiedClass4078;

public class CheatBreakerPingHandler extends ChannelInboundHandlerAdapter {
   public InternalAttribute field_0002;
   public UnidentifiedClass4078 field_0004;
   public long field_0001;
   public long field_0003 = System.nanoTime() - (4508873568001702930L & -4508873538035798104L);
   public Minecraft field_0000 = Minecraft.getMinecraft();

   public CheatBreakerPingHandler(UnidentifiedClass4078 var1) {
      this.field_0001 = 1787824144L & 270985124L;
      this.field_0004 = var1;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      S3FPacketCustomPayload var3;
      if (var2 instanceof S3FPacketCustomPayload && (var3 = (S3FPacketCustomPayload)var2).getChannelName().equals("CB|PING")) {
         this.field_0003 = System.nanoTime();
         long var4 = new DataInputStream(new ByteArrayInputStream(var3.getBufferData().readByteArray())).readInt() & -7385904892126167041L & 4294967295L;
         if (var4 != this.field_0004.method_24511()) {
            throw new IOException("CheatBreaker Protocol Error -2a\n(Try updating your client)");
         }

         ByteArrayOutputStream var6 = new ByteArrayOutputStream();
         DataOutputStream var7 = new DataOutputStream(var6);

         try {
            var7.write(var3.getBufferData().readByteArray());
            long var10000 = this.field_0001;
            this.field_0001 += 4841060828991981197L & 67153923L;
            if ((var10000 & 8430123401112272911L & 740295047L) == (-8901107000875613268L & 276843523L)) {
               var7.writeLong(this.field_0001);
               var7.writeUTF(this.field_0000.currentServerData.serverIP);
               var7.writeUTF(this.field_0000.getSession().getPlayerID());
               var7.writeUTF(this.field_0000.entityRenderer.getClass().getName());
            }
         } catch (IOException var9) {
         }

         PacketBuffer var8 = new PacketBuffer(Unpooled.buffer());
         var8.writeBytes(var6.toByteArray());
         var1.channel().eventLoop().execute(() -> var1.channel().writeAndFlush(new CustomPayloadSender("CB|PONG", var8)));
      }

      if (System.nanoTime() - this.field_0003 > (45000006160L & -291880067591077272L)) {
         throw new IOException("CheatBreaker Protocol Error -2b\n(Try updating your client)");
      } else {
         super.channelRead(var1, var2);
      }
   }
}
