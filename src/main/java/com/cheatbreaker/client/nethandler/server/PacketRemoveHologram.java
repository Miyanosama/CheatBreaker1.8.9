package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.UUID;

public class PacketRemoveHologram extends Packet {
   public UUID recoveredField641;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11448(this);
   }

   public UUID method_12150() {
      return this.recoveredField641;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField641 = var1.readUUID();
   }

   public PacketRemoveHologram(UUID var1) {
      this.recoveredField641 = var1;
   }

   public PacketRemoveHologram() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.recoveredField641);
   }
}
