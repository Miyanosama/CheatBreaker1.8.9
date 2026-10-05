package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class ContainerBrewingStand extends Container {
   public Slot theSlot;
   public int brewTime;
   public IInventory tileBrewingStand;

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int var1 = 0; var1 < this.e.size(); var1++) {
         ICrafting var2 = this.e.get(var1);
         if (this.brewTime != this.tileBrewingStand.getField(0)) {
            var2.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getField(0));
         }
      }

      this.brewTime = this.tileBrewingStand.getField(0);
   }

   public ContainerBrewingStand(InventoryPlayer var1, IInventory var2) {
      this.tileBrewingStand = var2;
      this.a(new ContainerBrewingStand.Potion(var1.player, var2, 0, 56, 46));
      this.a(new ContainerBrewingStand.Potion(var1.player, var2, 1, 79, 53));
      this.a(new ContainerBrewingStand.Potion(var1.player, var2, 2, 102, 46));
      this.theSlot = this.a(new ContainerBrewingStand.Ingredient(var2, 3, 79, 17));

      for (int var3 = 0; var3 < 3; var3++) {
         for (int var4 = 0; var4 < 9; var4++) {
            this.a(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
         }
      }

      for (int var5 = 0; var5 < 9; var5++) {
         this.a(new Slot(var1, var5, 8 + var5 * 18, 142));
      }
   }

   @Override
   public void updateProgressBar(int var1, int var2) {
      this.tileBrewingStand.setField(var1, var2);
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileBrewingStand.isUseableByPlayer(var1);
   }

   @Override
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
      var1.sendAllWindowProperties(this, this.tileBrewingStand);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if ((var2 < 0 || var2 > 2) && var2 != 3) {
            if (!this.theSlot.getHasStack() && this.theSlot.isItemValid(var5)) {
               if (!this.mergeItemStack(var5, 3, 4, false)) {
                  return null;
               }
            } else if (ContainerBrewingStand.Potion.canHoldPotion(var3)) {
               if (!this.mergeItemStack(var5, 0, 3, false)) {
                  return null;
               }
            } else if (var2 >= 4 && var2 < 31) {
               if (!this.mergeItemStack(var5, 31, 40, false)) {
                  return null;
               }
            } else if (var2 >= 31 && var2 < 40) {
               if (!this.mergeItemStack(var5, 4, 31, false)) {
                  return null;
               }
            } else if (!this.mergeItemStack(var5, 4, 40, false)) {
               return null;
            }
         } else {
            if (!this.mergeItemStack(var5, 4, 40, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
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

   public class Ingredient extends Slot {
      public Ingredient(IInventory var2, int var3, int var4, int var5) {
         super(var2, var3, var4, var5);
      }

      @Override
      public boolean isItemValid(ItemStack var1) {
         return var1 != null ? var1.getItem().isPotionIngredient(var1) : false;
      }

      @Override
      public int getSlotStackLimit() {
         return 64;
      }
   }

   public static class Potion extends Slot {
      public EntityPlayer player;

      @Override
      public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
         if (var2.getItem() == Items.potionitem && var2.getMetadata() > 0) {
            this.player.triggerAchievement(AchievementList.potion);
         }

         super.onPickupFromSlot(var1, var2);
      }

      public static boolean canHoldPotion(ItemStack var0) {
         return var0 != null && (var0.getItem() == Items.potionitem || var0.getItem() == Items.glass_bottle);
      }

      @Override
      public boolean isItemValid(ItemStack var1) {
         return canHoldPotion(var1);
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }

      public Potion(EntityPlayer var1, IInventory var2, int var3, int var4, int var5) {
         super(var2, var3, var4, var5);
         this.player = var1;
      }
   }
}
