package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S14PacketEntity implements Packet<INetHandlerPlayClient> {
   public byte b;
   public byte f;
   public byte e;
   public int entityId;
   public boolean h;
   public byte c;
   public byte d;
   public boolean g;

   public byte method_05393() {
      return this.f;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
   }

   public S14PacketEntity() {
   }

   public byte method_05397() {
      return this.d;
   }

   public byte method_05392() {
      return this.b;
   }

   public S14PacketEntity(int var1) {
      this.entityId = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityMovement(this);
   }

   public byte method_05396() {
      return this.e;
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   public boolean method_05390() {
      return this.h;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
   }

   public boolean method_05391() {
      return this.g;
   }

   public byte method_05398() {
      return this.c;
   }

   @Override
   public String toString() {
      return "Entity_" + super.toString();
   }

   public static class S15PacketEntityRelMove extends S14PacketEntity {
      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         super.readPacketData(var1);
         this.b = var1.readByte();
         this.c = var1.readByte();
         this.d = var1.readByte();
         this.g = var1.readBoolean();
      }

      public S15PacketEntityRelMove(int var1, byte var2, byte var3, byte var4, boolean var5) {
         super(var1);
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.g = var5;
      }

      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         super.writePacketData(var1);
         var1.writeByte(this.b);
         var1.writeByte(this.c);
         var1.writeByte(this.d);
         var1.writeBoolean(this.g);
      }

      public S15PacketEntityRelMove() {
      }
   }

   public static class S16PacketEntityLook extends S14PacketEntity {
      public S16PacketEntityLook() {
         this.h = true;
      }

      public S16PacketEntityLook(int var1, byte var2, byte var3, boolean var4) {
         super(var1);
         this.e = var2;
         this.f = var3;
         this.h = true;
         this.g = var4;
      }

      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         super.readPacketData(var1);
         this.e = var1.readByte();
         this.f = var1.readByte();
         this.g = var1.readBoolean();
      }

      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         super.writePacketData(var1);
         var1.writeByte(this.e);
         var1.writeByte(this.f);
         var1.writeBoolean(this.g);
      }
   }

   public static class S17PacketEntityLookMove extends S14PacketEntity {
      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         super.writePacketData(var1);
         var1.writeByte(this.b);
         var1.writeByte(this.c);
         var1.writeByte(this.d);
         var1.writeByte(this.e);
         var1.writeByte(this.f);
         var1.writeBoolean(this.g);
      }

      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         super.readPacketData(var1);
         this.b = var1.readByte();
         this.c = var1.readByte();
         this.d = var1.readByte();
         this.e = var1.readByte();
         this.f = var1.readByte();
         this.g = var1.readBoolean();
      }

      public S17PacketEntityLookMove(int var1, byte var2, byte var3, byte var4, byte var5, byte var6, boolean var7) {
         super(var1);
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.e = var5;
         this.f = var6;
         this.g = var7;
         this.h = true;
      }

      public S17PacketEntityLookMove() {
         this.h = true;
      }
   }
}
