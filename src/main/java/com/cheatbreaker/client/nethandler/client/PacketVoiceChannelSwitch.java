package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;
import java.util.UUID;

public class PacketVoiceChannelSwitch extends Packet {
   public UUID switchingTo;

   @Override
   public void read(ByteBufWrapper var1) {
      this.switchingTo = var1.readUUID();
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerServer)var1).handleVoiceChannelSwitch(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.switchingTo);
   }

   public PacketVoiceChannelSwitch() {
   }

   public UUID getSwitchingTo() {
      return this.switchingTo;
   }

   public PacketVoiceChannelSwitch(UUID var1) {
      this.switchingTo = var1;
   }
}
