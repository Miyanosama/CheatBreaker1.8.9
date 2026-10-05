package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S40PacketDisconnect implements Packet<INetHandlerPlayClient> {
   public IChatComponent reason;

   public S40PacketDisconnect(IChatComponent var1) {
      this.reason = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDisconnect(this);
   }

   public IChatComponent getReason() {
      return this.reason;
   }

   public S40PacketDisconnect() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.reason = var1.readChatComponent();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeChatComponent(this.reason);
   }
}
