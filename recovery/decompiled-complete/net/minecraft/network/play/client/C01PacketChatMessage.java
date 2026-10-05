package net.minecraft.network.play.client;

import io.netty.handler.codec.socks.SocksAuthRequest;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.java_websocket.AbstractWebSocket;

public class C01PacketChatMessage implements Packet<INetHandlerPlayServer> {
   public SocksAuthRequest field_0001;
   public AbstractWebSocket field_0002;
   public String message;

   public String getMessage() {
      return this.message;
   }

   public C01PacketChatMessage() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.message = var1.readStringFromBuffer(100);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.message);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processChatMessage(this);
   }

   public C01PacketChatMessage(String var1) {
      if (var1.length() > 100) {
         var1 = var1.substring(0, 100);
      }

      this.message = var1;
   }
}
