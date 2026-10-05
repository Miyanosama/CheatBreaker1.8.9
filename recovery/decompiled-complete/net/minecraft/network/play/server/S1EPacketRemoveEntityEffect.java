package net.minecraft.network.play.server;

import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.item.ItemFireball;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1EPacketRemoveEntityEffect implements Packet<INetHandlerPlayClient> {
   public RenderMinecart field_0001;
   public ItemFireball field_0003;
   public int effectId;
   public int entityId;

   public S1EPacketRemoveEntityEffect(int var1, PotionEffect var2) {
      this.entityId = var1;
      this.effectId = var2.getPotionID();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.effectId);
   }

   public int getEffectId() {
      return this.effectId;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.effectId = var1.readUnsignedByte();
   }

   public S1EPacketRemoveEntityEffect() {
   }

   public int getEntityId() {
      return this.entityId;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleRemoveEntityEffect(this);
   }
}
