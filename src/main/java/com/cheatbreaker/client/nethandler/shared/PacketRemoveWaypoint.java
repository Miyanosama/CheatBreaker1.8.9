package com.cheatbreaker.client.nethandler.shared;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;

public class PacketRemoveWaypoint extends Packet {
   public String recoveredField3645;
   public String recoveredField3646;

   public PacketRemoveWaypoint() {
   }

   public String method_27970() {
      return this.recoveredField3646;
   }

   public PacketRemoveWaypoint(String var1, String var2) {
      this.recoveredField3645 = var1;
      this.recoveredField3646 = var2;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.recoveredField3645);
      var1.writeString(this.recoveredField3646);
   }

   public String method_27969() {
      return this.recoveredField3645;
   }

   @Override
   public void process(ICBNetHandler var1) {
      var1.handleRemoveWaypoint(this);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField3645 = var1.readString();
      this.recoveredField3646 = var1.readString();
   }
}
