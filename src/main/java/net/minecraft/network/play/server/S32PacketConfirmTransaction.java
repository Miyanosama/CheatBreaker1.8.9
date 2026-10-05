package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S32PacketConfirmTransaction implements Packet<INetHandlerPlayClient> {
   public boolean field_148893_c;
   public int windowId;
   public short actionNumber;

   public S32PacketConfirmTransaction(int var1, short var2, boolean var3) {
      this.windowId = var1;
      this.actionNumber = var2;
      this.field_148893_c = var3;
   }

   public S32PacketConfirmTransaction() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readUnsignedByte();
      this.actionNumber = var1.readShort();
      this.field_148893_c = var1.readBoolean();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
      var1.writeShort(this.actionNumber);
      var1.writeBoolean(this.field_148893_c);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleConfirmTransaction(this);
   }

   public boolean func_148888_e() {
      return this.field_148893_c;
   }
}
