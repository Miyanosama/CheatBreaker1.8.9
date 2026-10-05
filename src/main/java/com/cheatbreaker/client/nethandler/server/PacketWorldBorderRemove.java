package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketWorldBorderRemove extends Packet {
   public String recoveredField924;

   public String method_23476() {
      return this.recoveredField924;
   }

   public PacketWorldBorderRemove() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.recoveredField924);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField924 = var1.readString();
   }

   public PacketWorldBorderRemove(String var1) {
      this.recoveredField924 = var1;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11462(this);
   }
}
