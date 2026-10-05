package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;
import io.netty.channel.AbstractChannelHandlerContext;
import io.netty.util.concurrent.DefaultPromise$2;
import java.util.UUID;

public class PacketVoiceMute extends Packet {
   public DefaultPromise$2 field_0000;
   public UUID muting;
   public AbstractChannelHandlerContext field_0002;

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
