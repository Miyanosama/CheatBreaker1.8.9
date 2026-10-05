package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.UUID;

public class PacketDeleteVoiceChannel extends Packet {
   public UUID recoveredField3648;

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField3648 = var1.readUUID();
   }

   public PacketDeleteVoiceChannel(UUID var1) {
      this.recoveredField3648 = var1;
   }

   public UUID method_03211() {
      return this.recoveredField3648;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11438(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.recoveredField3648);
   }

   public PacketDeleteVoiceChannel() {
   }
}
