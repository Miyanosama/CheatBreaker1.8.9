package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;

public class PacketClientVoice extends Packet {
   public byte[] data;

   public byte[] getData() {
      return this.data;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.data = this.readBlob(var1);
   }

   public PacketClientVoice(byte[] var1) {
      this.data = var1;
   }

   public PacketClientVoice() {
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerServer)var1).handleVoice(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      this.writeBlob(var1, this.data);
   }
}
