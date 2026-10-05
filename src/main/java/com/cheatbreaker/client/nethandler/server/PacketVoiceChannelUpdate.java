package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.UUID;

public class PacketVoiceChannelUpdate extends Packet {
   public int status;
   public String name;
   public UUID uuid;
   public UUID channelUuid;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleVoiceChannelUpdate(this);
   }

   public String getName() {
      return this.name;
   }

   public UUID getChannelUuid() {
      return this.channelUuid;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.status = var1.readVarInt();
      this.channelUuid = var1.readUUID();
      this.uuid = var1.readUUID();
      this.name = var1.readString();
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public int getStatus() {
      return this.status;
   }

   public PacketVoiceChannelUpdate(int var1, UUID var2, UUID var3, String var4) {
      this.status = var1;
      this.channelUuid = var2;
      this.uuid = var3;
      this.name = var4;
   }

   public PacketVoiceChannelUpdate() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeVarInt(this.status);
      var1.writeUUID(this.channelUuid);
      var1.writeUUID(this.uuid);
      var1.writeString(this.name);
   }
}
