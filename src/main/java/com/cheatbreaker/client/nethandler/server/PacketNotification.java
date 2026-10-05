package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketNotification extends Packet {
   public String level;
   public long durationMs;
   public String message;

   @Override
   public void read(ByteBufWrapper var1) {
      this.message = var1.readString();
      this.durationMs = var1.buf().readLong();
      this.level = var1.readString();
   }

   public String getLevel() {
      return this.level;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.message);
      var1.buf().writeLong(this.durationMs);
      var1.writeString(this.level);
   }

   public String getMessage() {
      return this.message;
   }

   public long getDurationMs() {
      return this.durationMs;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleNotification(this);
   }

   public PacketNotification() {
   }

   public PacketNotification(String var1, long var2, String var4) {
      this.message = var1;
      this.durationMs = var2;
      this.level = var4;
   }
}
