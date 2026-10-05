package net.minecraft.inventory;

import net.minecraft.block.BlockOldLog$1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.optifine.entity.model.ModelAdapterWolf;

public class Slot {
   public int xDisplayPosition;
   public BlockOldLog$1 field_0001;
   public int slotNumber;
   public IInventory inventory;
   public ModelAdapterWolf field_0003;
   public int yDisplayPosition;
   public int slotIndex;

   public ItemStack getStack() {
      return this.inventory.getStackInSlot(this.slotIndex);
   }

   public String getSlotTexture() {
      return null;
   }

   public boolean isItemValid(ItemStack var1) {
      return true;
   }

   public void onSlotChanged() {
      this.inventory.markDirty();
   }

   public void putStack(ItemStack var1) {
      this.inventory.setInventorySlotContents(this.slotIndex, var1);
      this.onSlotChanged();
   }

   public boolean canTakeStack(EntityPlayer var1) {
      return true;
   }

   public void onCrafting(ItemStack var1, int var2) {
   }

   public void onCrafting(ItemStack var1) {
   }

   public boolean isHere(IInventory var1, int var2) {
      return var1 == this.inventory && var2 == this.slotIndex;
   }

   public void onSlotChange(ItemStack var1, ItemStack var2) {
      if (var1 != null && var2 != null && var1.getItem() == var2.getItem()) {
         int var3 = var2.stackSize - var1.stackSize;
         if (var3 > 0) {
            this.onCrafting(var1, var3);
         }
      }
   }

   public ItemStack decrStackSize(int var1) {
      return this.inventory.decrStackSize(this.slotIndex, var1);
   }

   public boolean getHasStack() {
      return this.getStack() != null;
   }

   public boolean canBeHovered() {
      return true;
   }

   public int getItemStackLimit(ItemStack var1) {
      return this.getSlotStackLimit();
   }

   public int getSlotStackLimit() {
      return this.inventory.getInventoryStackLimit();
   }

   public Slot(IInventory var1, int var2, int var3, int var4) {
      this.inventory = var1;
      this.slotIndex = var2;
      this.xDisplayPosition = var3;
      this.yDisplayPosition = var4;
   }

   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.onSlotChanged();
   }
}
