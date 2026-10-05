package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0BPacketEntityAction implements Packet<INetHandlerPlayServer> {
   public int entityID;
   public C0BPacketEntityAction.Action action;
   public int auxData;

   public int getAuxData() {
      return this.auxData;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityID = var1.readVarIntFromBuffer();
      this.action = var1.readEnumValue(C0BPacketEntityAction.Action.class);
      this.auxData = var1.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processEntityAction(this);
   }

   public C0BPacketEntityAction() {
   }

   public C0BPacketEntityAction(Entity var1, C0BPacketEntityAction.Action var2) {
      this(var1, var2, 0);
   }

   public C0BPacketEntityAction(Entity var1, C0BPacketEntityAction.Action var2, int var3) {
      this.entityID = var1.F();
      this.action = var2;
      this.auxData = var3;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeEnumValue(this.action);
      var1.writeVarIntToBuffer(this.auxData);
   }

   public C0BPacketEntityAction.Action getAction() {
      return this.action;
   }

   public static enum Action {
      START_SNEAKING,
      STOP_SNEAKING,
      STOP_SLEEPING,
      START_SPRINTING,
      STOP_SPRINTING,
      RIDING_JUMP,
      OPEN_INVENTORY;
      // $VF: synthetic field
      public static C0BPacketEntityAction.Action[] $VALUES = new C0BPacketEntityAction.Action[]{
         C0BPacketEntityAction.Action.START_SNEAKING,
         STOP_SNEAKING,
         C0BPacketEntityAction.Action.STOP_SLEEPING,
         C0BPacketEntityAction.Action.START_SPRINTING,
         C0BPacketEntityAction.Action.STOP_SPRINTING,
         C0BPacketEntityAction.Action.RIDING_JUMP,
         OPEN_INVENTORY
      };
   }
}
