package net.minecraft.network.play.client;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.WorldServer;
import net.optifine.entity.model.ModelAdapterWither;

public class C18PacketSpectate implements Packet<INetHandlerPlayServer> {
   public ModelAdapterWither field_0000;
   public UUID id;

   public C18PacketSpectate() {
   }

   public Entity getEntity(WorldServer var1) {
      return var1.getEntityFromUuid(this.id);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeUuid(this.id);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.id = var1.readUuid();
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.handleSpectate(this);
   }

   public C18PacketSpectate(UUID var1) {
      this.id = var1;
   }
}
