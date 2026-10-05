package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketWorldBorderUpdate extends Packet {
   public double minX;
   public double maxX;
   public String id;
   public int durationTicks;
   public double minZ;
   public double maxZ;

   public double getMaxZ() {
      return this.maxZ;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.id = var1.readString();
      this.minX = var1.buf().readDouble();
      this.minZ = var1.buf().readDouble();
      this.maxX = var1.buf().readDouble();
      this.maxZ = var1.buf().readDouble();
      this.durationTicks = var1.buf().readInt();
   }

   public PacketWorldBorderUpdate() {
   }

   public double getMaxX() {
      return this.maxX;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.id);
      var1.buf().writeDouble(this.minX);
      var1.buf().writeDouble(this.minZ);
      var1.buf().writeDouble(this.maxX);
      var1.buf().writeDouble(this.maxZ);
      var1.buf().writeInt(this.durationTicks);
   }

   public int getDurationTicks() {
      return this.durationTicks;
   }

   public PacketWorldBorderUpdate(String var1, double var2, double var4, double var6, double var8, int var10) {
      this.id = var1;
      this.minX = var2;
      this.minZ = var4;
      this.maxX = var6;
      this.maxZ = var8;
      this.durationTicks = var10;
   }

   public String getId() {
      return this.id;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleWorldBorderUpdate(this);
   }

   public double getMinX() {
      return this.minX;
   }

   public double getMinZ() {
      return this.minZ;
   }
}
