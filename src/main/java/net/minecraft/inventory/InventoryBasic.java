package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryBasic implements IInventory {
   public List<IInvBasic> changeListeners;
   public String inventoryTitle;
   public int slotsCount;
   public boolean hasCustomName;
   public ItemStack[] inventoryContents;

   public InventoryBasic(IChatComponent var1, int var2) {
      this(var1.getUnformattedText(), true, var2);
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   public void setCustomName(String var1) {
      this.hasCustomName = true;
      this.inventoryTitle = var1;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public String z_() {
      return this.inventoryTitle;
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.inventoryContents.length; var1++) {
         this.inventoryContents[var1] = null;
      }
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.inventoryContents[var1] = var2;
      if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return var1 >= 0 && var1 < this.inventoryContents.length ? this.inventoryContents[var1] : null;
   }

   public ItemStack func_174894_a(ItemStack var1) {
      ItemStack var2 = var1.copy();

      for (int var3 = 0; var3 < this.slotsCount; var3++) {
         ItemStack var4 = this.getStackInSlot(var3);
         if (var4 == null) {
            this.setInventorySlotContents(var3, var2);
            this.markDirty();
            return null;
         }

         if (ItemStack.areItemsEqual(var4, var2)) {
            int var5 = Math.min(this.getInventoryStackLimit(), var4.getMaxStackSize());
            int var6 = Math.min(var2.stackSize, var5 - var4.stackSize);
            if (var6 > 0) {
               var4.stackSize += var6;
               var2.stackSize -= var6;
               if (var2.stackSize <= 0) {
                  this.markDirty();
                  return null;
               }
            }
         }
      }

      if (var2.stackSize != var1.stackSize) {
         this.markDirty();
      }

      return var2;
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public void markDirty() {
      if (this.changeListeners != null) {
         for (int var1 = 0; var1 < this.changeListeners.size(); var1++) {
            this.changeListeners.get(var1).onInventoryChanged(this);
         }
      }
   }

   @Override
   public int getSizeInventory() {
      return this.slotsCount;
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   public void removeInventoryChangeListener(IInvBasic var1) {
      this.changeListeners.remove(var1);
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   public InventoryBasic(String var1, boolean var2, int var3) {
      this.inventoryTitle = var1;
      this.hasCustomName = var2;
      this.slotsCount = var3;
      this.inventoryContents = new ItemStack[var3];
   }

   public void addInventoryChangeListener(IInvBasic var1) {
      if (this.changeListeners == null) {
         this.changeListeners = Lists.newArrayList();
      }

      this.changeListeners.add(var1);
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.inventoryContents[var1] != null) {
         ItemStack var2 = this.inventoryContents[var1];
         this.inventoryContents[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.inventoryContents[var1] != null) {
         if (this.inventoryContents[var1].stackSize <= var2) {
            ItemStack var4 = this.inventoryContents[var1];
            this.inventoryContents[var1] = null;
            this.markDirty();
            return var4;
         } else {
            ItemStack var3 = this.inventoryContents[var1].splitStack(var2);
            if (this.inventoryContents[var1].stackSize == 0) {
               this.inventoryContents[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean u_() {
      return this.hasCustomName;
   }
}
