package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketCooldown extends Packet {
   public int iconId;
   public String message;
   public long durationMs;

   public PacketCooldown(String var1, long var2, int var4) {
      this.message = var1;
      this.durationMs = var2;
      this.iconId = var4;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.message);
      var1.buf().writeLong(this.durationMs);
      var1.buf().writeInt(this.iconId);
   }

   public PacketCooldown() {
   }

   public long getDurationMs() {
      return this.durationMs;
   }

   public int getIconId() {
      return this.iconId;
   }

   public String getMessage() {
      return this.message;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.message = var1.readString();
      this.durationMs = var1.buf().readLong();
      this.iconId = var1.buf().readInt();
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleCooldown(this);
   }
}
