package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class C02PacketUseEntity implements Packet<INetHandlerPlayServer> {
   public Vec3 hitVec;
   public C02PacketUseEntity.Action action;
   public int entityId;

   public Vec3 getHitVec() {
      return this.hitVec;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
      this.action = var1.readEnumValue(C02PacketUseEntity.Action.class);
      if (this.action == C02PacketUseEntity.Action.INTERACT_AT) {
         this.hitVec = new Vec3(var1.readFloat(), var1.readFloat(), var1.readFloat());
      }
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processUseEntity(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeEnumValue(this.action);
      if (this.action == C02PacketUseEntity.Action.INTERACT_AT) {
         var1.writeFloat((float)this.hitVec.xCoord);
         var1.writeFloat((float)this.hitVec.yCoord);
         var1.writeFloat((float)this.hitVec.zCoord);
      }
   }

   public C02PacketUseEntity() {
   }

   public C02PacketUseEntity(Entity var1, Vec3 var2) {
      this(var1, C02PacketUseEntity.Action.INTERACT_AT);
      this.hitVec = var2;
   }

   public C02PacketUseEntity.Action getAction() {
      return this.action;
   }

   public C02PacketUseEntity(Entity var1, C02PacketUseEntity.Action var2) {
      this.entityId = var1.F();
      this.action = var2;
   }

   public Entity getEntityFromWorld(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   public static enum Action {
      INTERACT,
      ATTACK,
      INTERACT_AT;
   }
}
