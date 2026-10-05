package net.minecraft.network.play.client;

import net.minecraft.crash.CrashReport$3;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.MouseFilter;
import net.optifine.util.IteratorCache;

public class C0EPacketClickWindow implements Packet<INetHandlerPlayServer> {
   public int usedButton;
   public short actionNumber;
   public int slotId;
   public MouseFilter field_0006;
   public int windowId;
   public CrashReport$3 field_0001;
   public int mode;
   public IteratorCache field_0005;
   public ItemStack clickedItem;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.windowId);
      var1.writeShort(this.slotId);
      var1.writeByte(this.usedButton);
      var1.writeShort(this.actionNumber);
      var1.writeByte(this.mode);
      var1.writeItemStackToBuffer(this.clickedItem);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClickWindow(this);
   }

   public C0EPacketClickWindow(int var1, int var2, int var3, int var4, ItemStack var5, short var6) {
      this.windowId = var1;
      this.slotId = var2;
      this.usedButton = var3;
      this.clickedItem = var5 != null ? var5.copy() : null;
      this.actionNumber = var6;
      this.mode = var4;
   }

   public C0EPacketClickWindow() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.windowId = var1.readByte();
      this.slotId = var1.readShort();
      this.usedButton = var1.readByte();
      this.actionNumber = var1.readShort();
      this.mode = var1.readByte();
      this.clickedItem = var1.readItemStackFromBuffer();
   }

   public int getMode() {
      return this.mode;
   }

   public int getSlotId() {
      return this.slotId;
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getUsedButton() {
      return this.usedButton;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public ItemStack getClickedItem() {
      return this.clickedItem;
   }
}
