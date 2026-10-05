package com.cheatbreaker.client.nethandler.shared;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;

public class PacketAddWaypoint extends Packet {
   public int y;
   public int z;
   public boolean visible;
   public int x;
   public String world;
   public int color;
   public boolean forced;
   public String name;

   public int getX() {
      return this.x;
   }

   public int getColor() {
      return this.color;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.name);
      var1.writeString(this.world);
      var1.buf().writeInt(this.color);
      var1.buf().writeInt(this.x);
      var1.buf().writeInt(this.y);
      var1.buf().writeInt(this.z);
      var1.buf().writeBoolean(this.forced);
      var1.buf().writeBoolean(this.visible);
   }

   public boolean isForced() {
      return this.forced;
   }

   public String getName() {
      return this.name;
   }

   public int getZ() {
      return this.z;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.name = var1.readString();
      this.world = var1.readString();
      this.color = var1.buf().readInt();
      this.x = var1.buf().readInt();
      this.y = var1.buf().readInt();
      this.z = var1.buf().readInt();
      this.forced = var1.buf().readBoolean();
      this.visible = var1.buf().readBoolean();
   }

   public PacketAddWaypoint() {
   }

   public boolean isVisible() {
      return this.visible;
   }

   public int getY() {
      return this.y;
   }

   public String getWorld() {
      return this.world;
   }

   public PacketAddWaypoint(String var1, String var2, int var3, int var4, int var5, int var6, boolean var7, boolean var8) {
      this.name = var1;
      this.world = var2;
      this.color = var3;
      this.x = var4;
      this.y = var5;
      this.z = var6;
      this.forced = var7;
      this.visible = var8;
   }

   @Override
   public void process(ICBNetHandler var1) {
      var1.handleAddWaypoint(this);
   }
}
