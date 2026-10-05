package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketStaffModState extends Packet {
   public String mod;
   public boolean state;

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.mod);
      var1.buf().writeBoolean(this.state);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.mod = var1.readString();
      this.state = var1.buf().readBoolean();
   }

   public String getMod() {
      return this.mod;
   }

   public boolean isState() {
      return this.state;
   }

   public PacketStaffModState(String var1, boolean var2) {
      this.mod = var1;
      this.state = var2;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleStaffModState(this);
   }

   public PacketStaffModState() {
   }
}
