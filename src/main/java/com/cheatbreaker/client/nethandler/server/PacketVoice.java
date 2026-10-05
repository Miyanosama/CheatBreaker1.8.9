package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.UUID;

public class PacketVoice extends Packet {
   public UUID uuid;
   public byte[] data;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleVoice(this);
   }

   public PacketVoice(UUID var1, byte[] var2) {
      this.uuid = var1;
      this.data = var2;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public byte[] getData() {
      return this.data;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.uuid = var1.readUUID();
      this.data = this.readBlob(var1);
   }

   public PacketVoice() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.uuid);
      this.writeBlob(var1, this.data);
   }
}
