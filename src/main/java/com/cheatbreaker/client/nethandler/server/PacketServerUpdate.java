package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketServerUpdate extends Packet {
   public String recoveredField1392;

   public PacketServerUpdate(String var1) {
      this.recoveredField1392 = var1;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11443(this);
   }

   public PacketServerUpdate() {
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField1392 = var1.readString();
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.recoveredField1392);
   }

   public String method_05486() {
      return this.recoveredField1392;
   }
}
