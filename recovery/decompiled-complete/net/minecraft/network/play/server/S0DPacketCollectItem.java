package net.minecraft.network.play.server;

import io.netty.buffer.ByteBufProcessor$5;
import io.netty.channel.local.LocalServerChannel$1;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem implements Packet<INetHandlerPlayClient> {
   public GuiYesNo field_0003;
   public int collectedItemEntityId;
   public NBTTagList field_0002;
   public int entityId;
   public ByteBufProcessor$5 field_0000;
   public LocalServerChannel$1 field_0001;

   public int getEntityID() {
      return this.entityId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.collectedItemEntityId);
      var1.writeVarIntToBuffer(this.entityId);
   }

   public int getCollectedItemEntityID() {
      return this.collectedItemEntityId;
   }

   public S0DPacketCollectItem(int var1, int var2) {
      this.collectedItemEntityId = var1;
      this.entityId = var2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.collectedItemEntityId = var1.readVarIntFromBuffer();
      this.entityId = var1.readVarIntFromBuffer();
   }

   public S0DPacketCollectItem() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCollectItem(this);
   }
}
