package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class InventoryLargeChest implements ILockableContainer {
   public ILockableContainer lowerChest;
   public String name;
   public ILockableContainer upperChest;

   @Override
   public void openInventory(EntityPlayer var1) {
      this.upperChest.openInventory(var1);
      this.lowerChest.openInventory(var1);
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }

   @Override
   public int getInventoryStackLimit() {
      return this.upperChest.getInventoryStackLimit();
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      return var1 >= this.upperChest.getSizeInventory()
         ? this.lowerChest.removeStackFromSlot(var1 - this.upperChest.getSizeInventory())
         : this.upperChest.removeStackFromSlot(var1);
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      return var1 >= this.upperChest.getSizeInventory()
         ? this.lowerChest.decrStackSize(var1 - this.upperChest.getSizeInventory(), var2)
         : this.upperChest.decrStackSize(var1, var2);
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public String z_() {
      return this.upperChest.u_() ? this.upperChest.z_() : (this.lowerChest.u_() ? this.lowerChest.z_() : this.name);
   }

   @Override
   public String getGuiID() {
      return this.upperChest.getGuiID();
   }

   @Override
   public boolean u_() {
      return this.upperChest.u_() || this.lowerChest.u_();
   }

   @Override
   public void markDirty() {
      this.upperChest.markDirty();
      this.lowerChest.markDirty();
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
      this.upperChest.closeInventory(var1);
      this.lowerChest.closeInventory(var1);
   }

   @Override
   public boolean B_() {
      return this.upperChest.B_() || this.lowerChest.B_();
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      if (var1 >= this.upperChest.getSizeInventory()) {
         this.lowerChest.setInventorySlotContents(var1 - this.upperChest.getSizeInventory(), var2);
      } else {
         this.upperChest.setInventorySlotContents(var1, var2);
      }
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void clear() {
      this.upperChest.clear();
      this.lowerChest.clear();
   }

   public InventoryLargeChest(String var1, ILockableContainer var2, ILockableContainer var3) {
      this.name = var1;
      if (var2 == null) {
         var2 = var3;
      }

      if (var3 == null) {
         var3 = var2;
      }

      this.upperChest = var2;
      this.lowerChest = var3;
      if (var2.B_()) {
         var3.setLockCode(var2.getLockCode());
      } else if (var3.B_()) {
         var2.setLockCode(var3.getLockCode());
      }
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public LockCode getLockCode() {
      return this.upperChest.getLockCode();
   }

   public boolean isPartOfLargeChest(IInventory var1) {
      return this.upperChest == var1 || this.lowerChest == var1;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.upperChest.isUseableByPlayer(var1) && this.lowerChest.isUseableByPlayer(var1);
   }

   @Override
   public int getSizeInventory() {
      return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerChest(var1, this, var2);
   }

   @Override
   public void setLockCode(LockCode var1) {
      this.upperChest.setLockCode(var1);
      this.lowerChest.setLockCode(var1);
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return var1 >= this.upperChest.getSizeInventory()
         ? this.lowerChest.getStackInSlot(var1 - this.upperChest.getSizeInventory())
         : this.upperChest.getStackInSlot(var1);
   }
}
