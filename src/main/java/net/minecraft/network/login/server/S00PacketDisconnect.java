package net.minecraft.network.login.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.IChatComponent;

public class S00PacketDisconnect implements Packet<INetHandlerLoginClient> {
   public IChatComponent reason;

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleDisconnect(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.reason = var1.readChatComponent();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeChatComponent(this.reason);
   }

   public IChatComponent func_149603_c() {
      return this.reason;
   }

   public S00PacketDisconnect() {
   }

   public S00PacketDisconnect(IChatComponent var1) {
      this.reason = var1;
   }
}
