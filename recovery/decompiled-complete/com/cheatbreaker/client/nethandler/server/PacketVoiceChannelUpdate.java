package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import io.netty.handler.codec.LengthFieldPrepender;
import java.util.UUID;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.network.NetworkSystem$6;

public class PacketVoiceChannelUpdate extends Packet {
   public EnumEnchantmentType field_0001;
   public int status;
   public LengthFieldPrepender field_0007;
   public String name;
   public EntityAIMoveToBlock field_0003;
   public UUID uuid;
   public NetworkSystem$6 field_0000;
   public UUID channelUuid;
   public LayerDeadmau5Head field_0005;

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
