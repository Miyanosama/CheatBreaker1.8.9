package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldNameable;

public interface IInventory extends IWorldNameable {
   ItemStack getStackInSlot(int var1);

   void setField(int var1, int var2);

   void setInventorySlotContents(int var1, ItemStack var2);

   void closeInventory(EntityPlayer var1);

   void clear();

   ItemStack decrStackSize(int var1, int var2);

   int getInventoryStackLimit();

   int getField(int var1);

   void openInventory(EntityPlayer var1);

   ItemStack removeStackFromSlot(int var1);

   int getFieldCount();

   int getSizeInventory();

   void markDirty();

   boolean isUseableByPlayer(EntityPlayer var1);

   boolean isItemValidForSlot(int var1, ItemStack var2);
}
