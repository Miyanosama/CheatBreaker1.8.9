package net.minecraft.network.login.server;

import io.netty.channel.rxtx.RxtxChannelConfig$Databits;
import io.netty.util.collection.IntObjectHashMap$IteratorImpl;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.util.IChatComponent;

public class S00PacketDisconnect implements Packet<INetHandlerLoginClient> {
   public RxtxChannelConfig$Databits field_0001;
   public IntObjectHashMap$IteratorImpl field_0003;
   public S14PacketEntity field_0000;
   public IChatComponent reason;

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleDisconnect(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.reason = var1.readChatComponent();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
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
