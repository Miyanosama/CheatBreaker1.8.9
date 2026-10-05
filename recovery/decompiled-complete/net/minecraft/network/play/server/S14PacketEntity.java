package net.minecraft.network.play.server;

import io.netty.handler.codec.spdy.SpdyHeaders;
import io.netty.handler.stream.ChunkedWriteHandler;
import net.minecraft.client.renderer.WorldRenderer$2;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S14PacketEntity implements Packet<INetHandlerPlayClient> {
   public ChunkedWriteHandler field_0005;
   public byte b;
   public byte f;
   public WorldRenderer$2 field_0007;
   public SpdyHeaders field_0001;
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
   public void readPacketData(PacketBuffer var1) {
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
   public void writePacketData(PacketBuffer var1) {
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
}
