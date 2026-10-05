package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;
import java.util.UUID;

public class PacketVoiceMute extends Packet {
   public UUID muting;

   public UUID getMuting() {
      return this.muting;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerServer)var1).handleVoiceMute(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.muting);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.muting = var1.readUUID();
   }

   public PacketVoiceMute(UUID var1) {
      this.muting = var1;
   }

   public PacketVoiceMute() {
   }
}
