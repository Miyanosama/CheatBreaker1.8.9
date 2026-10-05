package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.optifine.player.CapeImageBuffer;

public class C0BPacketEntityAction implements Packet<INetHandlerPlayServer> {
   public CapeImageBuffer field_0002;
   public int entityID;
   public EntityZombie$1 field_0001;
   public C0BPacketEntityAction$Action action;
   public int auxData;

   public int getAuxData() {
      return this.auxData;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityID = var1.readVarIntFromBuffer();
      this.action = var1.readEnumValue(C0BPacketEntityAction$Action.class);
      this.auxData = var1.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processEntityAction(this);
   }

   public C0BPacketEntityAction() {
   }

   public C0BPacketEntityAction(Entity var1, C0BPacketEntityAction$Action var2) {
      this(var1, var2, 0);
   }

   public C0BPacketEntityAction(Entity var1, C0BPacketEntityAction$Action var2, int var3) {
      this.entityID = var1.F();
      this.action = var2;
      this.auxData = var3;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeEnumValue(this.action);
      var1.writeVarIntToBuffer(this.auxData);
   }

   public C0BPacketEntityAction$Action getAction() {
      return this.action;
   }
}
