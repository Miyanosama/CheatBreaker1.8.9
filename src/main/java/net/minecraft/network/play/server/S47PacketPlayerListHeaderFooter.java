package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S47PacketPlayerListHeaderFooter implements Packet<INetHandlerPlayClient> {
   public IChatComponent header;
   public IChatComponent footer;

   public IChatComponent getFooter() {
      return this.footer;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeChatComponent(this.header);
      var1.writeChatComponent(this.footer);
   }

   public IChatComponent getHeader() {
      return this.header;
   }

   public S47PacketPlayerListHeaderFooter() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handlePlayerListHeaderFooter(this);
   }

   public S47PacketPlayerListHeaderFooter(IChatComponent var1) {
      this.header = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.header = var1.readChatComponent();
      this.footer = var1.readChatComponent();
   }
}
