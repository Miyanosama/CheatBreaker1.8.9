package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContainerBeacon extends Container {
   public ContainerBeacon.BeaconSlot beaconSlot;
   public IInventory tileBeacon;

   @Override
   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      if (var1 != null && !var1.o.D) {
         ItemStack var2 = this.beaconSlot.decrStackSize(this.beaconSlot.getSlotStackLimit());
         if (var2 != null) {
            var1.dropPlayerItemWithRandomChoice(var2, false);
         }
      }
   }

   public IInventory func_180611_e() {
      return this.tileBeacon;
   }

   @Override
   public void updateProgressBar(int var1, int var2) {
      this.tileBeacon.setField(var1, var2);
   }

   @Override
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
      var1.sendAllWindowProperties(this, this.tileBeacon);
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileBeacon.isUseableByPlayer(var1);
   }

   public ContainerBeacon(IInventory var1, IInventory var2) {
      this.tileBeacon = var2;
      this.a(this.beaconSlot = new ContainerBeacon.BeaconSlot(var2, 0, 136, 110));
      byte var3 = 36;
      short var4 = 137;

      for (int var5 = 0; var5 < 3; var5++) {
         for (int var6 = 0; var6 < 9; var6++) {
            this.a(new Slot(var1, var6 + var5 * 9 + 9, var3 + var6 * 18, var4 + var5 * 18));
         }
      }

      for (int var7 = 0; var7 < 9; var7++) {
         this.a(new Slot(var1, var7, var3 + var7 * 18, 58 + var4));
      }
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if (var2 == 0) {
            if (!this.mergeItemStack(var5, 1, 37, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(var5) && var5.stackSize == 1) {
            if (!this.mergeItemStack(var5, 0, 1, false)) {
               return null;
            }
         } else if (var2 >= 1 && var2 < 28) {
            if (!this.mergeItemStack(var5, 28, 37, false)) {
               return null;
            }
         } else if (var2 >= 28 && var2 < 37) {
            if (!this.mergeItemStack(var5, 1, 28, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(var5, 1, 37, false)) {
            return null;
         }

         if (var5.stackSize == 0) {
            var4.putStack((ItemStack)null);
         } else {
            var4.onSlotChanged();
         }

         if (var5.stackSize == var3.stackSize) {
            return null;
         }

         var4.onPickupFromSlot(var1, var5);
      }

      return var3;
   }

   public class BeaconSlot extends Slot {
      public BeaconSlot(IInventory var2, int var3, int var4, int var5) {
         super(var2, var3, var4, var5);
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }

      @Override
      public boolean isItemValid(ItemStack var1) {
         return var1 == null
            ? false
            : var1.getItem() == Items.emerald || var1.getItem() == Items.diamond || var1.getItem() == Items.gold_ingot || var1.getItem() == Items.iron_ingot;
      }
   }
}
