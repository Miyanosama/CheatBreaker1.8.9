package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0FPacketConfirmTransaction implements Packet<INetHandlerPlayServer> {
   public int windowId;
   public short uid;
   public boolean accepted;

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processConfirmTransaction(this);
   }

   public C0FPacketConfirmTransaction() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readByte();
      this.uid = var1.readShort();
      this.accepted = var1.readByte() != 0;
   }

   public C0FPacketConfirmTransaction(int var1, short var2, boolean var3) {
      this.windowId = var1;
      this.uid = var2;
      this.accepted = var3;
   }

   public int getWindowId() {
      return this.windowId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
      var1.writeShort(this.uid);
      var1.writeByte(this.accepted ? 1 : 0);
   }

   public short getUid() {
      return this.uid;
   }
}
