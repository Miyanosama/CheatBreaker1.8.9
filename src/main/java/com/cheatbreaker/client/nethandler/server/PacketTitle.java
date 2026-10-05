package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketTitle extends Packet {
   public String type;
   public String message;
   public long fadeOutTimeMs;
   public long displayTimeMs;
   public float scale;
   public long fadeInTimeMs;

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.type);
      var1.writeString(this.message);
      var1.buf().writeFloat(this.scale);
      var1.buf().writeLong(this.displayTimeMs);
      var1.buf().writeLong(this.fadeInTimeMs);
      var1.buf().writeLong(this.fadeOutTimeMs);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.type = var1.readString();
      this.message = var1.readString();
      this.scale = var1.buf().readFloat();
      this.displayTimeMs = var1.buf().readLong();
      this.fadeInTimeMs = var1.buf().readLong();
      this.fadeOutTimeMs = var1.buf().readLong();
   }

   public long getDisplayTimeMs() {
      return this.displayTimeMs;
   }

   public long getFadeOutTimeMs() {
      return this.fadeOutTimeMs;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleTitle(this);
   }

   public PacketTitle(String var1, String var2, float var3, long var4, long var6, long var8) {
      this.type = var1;
      this.message = var2;
      this.scale = var3;
      this.displayTimeMs = var4;
      this.fadeInTimeMs = var6;
      this.fadeOutTimeMs = var8;
   }

   public PacketTitle() {
   }

   public String getMessage() {
      return this.message;
   }

   public String getType() {
      return this.type;
   }

   public long getFadeInTimeMs() {
      return this.fadeInTimeMs;
   }

   public float getScale() {
      return this.scale;
   }

   public PacketTitle(String var1, String var2, long var3, long var5, long var7, float var9) {
      this.type = var1;
      this.message = var2;
      this.displayTimeMs = var3;
      this.fadeInTimeMs = var5;
      this.fadeOutTimeMs = var7;
      this.scale = var9;
   }

   public PacketTitle(String var1, String var2, long var3, long var5, long var7) {
      this(var1, var2, 1.0F, var3, var5, var7);
   }
}
