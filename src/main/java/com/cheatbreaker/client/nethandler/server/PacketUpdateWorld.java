package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketUpdateWorld extends Packet {
   public String recoveredField2427;

   public String method_20224() {
      return this.recoveredField2427;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField2427 = var1.readString();
   }

   public PacketUpdateWorld(String var1) {
      this.recoveredField2427 = var1;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.recoveredField2427);
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11456(this);
   }

   public PacketUpdateWorld() {
   }
}
