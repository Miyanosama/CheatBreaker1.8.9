package net.minecraft.network.play.client;

import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import recovered.unidentified.UnidentifiedClass4506;
import recovered.unidentified.UnidentifiedClass5012;

public class C16PacketClientStatus implements Packet<INetHandlerPlayServer> {
   public EntityList$EntityEggInfo field_0001;
   public UnidentifiedClass4506 field_0003;
   public UnidentifiedClass5012 field_0000;
   public C16PacketClientStatus$EnumState status;

   public C16PacketClientStatus() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.status = var1.readEnumValue(C16PacketClientStatus$EnumState.class);
   }

   public C16PacketClientStatus$EnumState getStatus() {
      return this.status;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClientStatus(this);
   }

   public C16PacketClientStatus(C16PacketClientStatus$EnumState var1) {
      this.status = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeEnumValue(this.status);
   }
}
