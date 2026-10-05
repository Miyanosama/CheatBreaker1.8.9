package net.minecraft.network.play.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C10PacketCreativeInventoryAction implements Packet<INetHandlerPlayServer> {
   public int slotId;
   public ItemStack stack;

   public C10PacketCreativeInventoryAction(int var1, ItemStack var2) {
      this.slotId = var1;
      this.stack = var2 != null ? var2.copy() : null;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.slotId = var1.readShort();
      this.stack = var1.readItemStackFromBuffer();
   }

   public C10PacketCreativeInventoryAction() {
   }

   public ItemStack getStack() {
      return this.stack;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
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
