package net.minecraft.network.play.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.MouseFilter;
import net.minecraft.world.biome.BiomeEndDecorator;

public class C10PacketCreativeInventoryAction implements Packet<INetHandlerPlayServer> {
   public int slotId;
   public MouseFilter field_0003;
   public ItemStack stack;
   public BiomeEndDecorator field_0002;

   public C10PacketCreativeInventoryAction(int var1, ItemStack var2) {
      this.slotId = var1;
      this.stack = var2 != null ? var2.copy() : null;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.slotId = var1.readShort();
      this.stack = var1.readItemStackFromBuffer();
   }

   public C10PacketCreativeInventoryAction() {
   }

   public ItemStack getStack() {
      return this.stack;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeShort(this.slotId);
      var1.writeItemStackToBuffer(this.stack);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processCreativeInventoryAction(this);
   }

   public int getSlotId() {
      return this.slotId;
   }
}
