package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C16PacketClientStatus implements Packet<INetHandlerPlayServer> {
   public C16PacketClientStatus.EnumState status;

   public C16PacketClientStatus() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.status = var1.readEnumValue(C16PacketClientStatus.EnumState.class);
   }

   public C16PacketClientStatus.EnumState getStatus() {
      return this.status;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClientStatus(this);
   }

   public C16PacketClientStatus(C16PacketClientStatus.EnumState var1) {
      this.status = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeEnumValue(this.status);
   }

   public static enum EnumState {
      PERFORM_RESPAWN,
      REQUEST_STATS,
      OPEN_INVENTORY_ACHIEVEMENT;
      // $VF: synthetic field
      public static C16PacketClientStatus.EnumState[] $VALUES = new C16PacketClientStatus.EnumState[]{
         PERFORM_RESPAWN, C16PacketClientStatus.EnumState.REQUEST_STATS, C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT
      };
   }
}
