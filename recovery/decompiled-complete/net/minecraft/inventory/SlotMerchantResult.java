package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.village.MerchantRecipe;
import recovered.unidentified.UnidentifiedClass1221;
import recovered.unidentified.UnidentifiedClass3482;

public class SlotMerchantResult extends Slot {
   public InventoryMerchant theMerchantInventory;
   public int field_75231_g;
   public UnidentifiedClass3482 field_0002;
   public EntityPlayer thePlayer;
   public UnidentifiedClass1221 field_0000;
   public IMerchant theMerchant;

   @Override
   public void onCrafting(ItemStack var1, int var2) {
      this.field_75231_g += var2;
      this.onCrafting(var1);
   }

   @Override
   public ItemStack decrStackSize(int var1) {
      if (this.getHasStack()) {
         this.field_75231_g = this.field_75231_g + Math.min(var1, this.getStack().stackSize);
      }

      return super.decrStackSize(var1);
   }

   public boolean doTrade(MerchantRecipe var1, ItemStack var2, ItemStack var3) {
      ItemStack var4 = var1.getItemToBuy();
      ItemStack var5 = var1.getSecondItemToBuy();
      if (var2 != null && var2.getItem() == var4.getItem()) {
         if (var5 != null && var3 != null && var5.getItem() == var3.getItem()) {
            var2.stackSize = var2.stackSize - var4.stackSize;
            var3.stackSize = var3.stackSize - var5.stackSize;
            return true;
         }

         if (var5 == null && var3 == null) {
            var2.stackSize = var2.stackSize - var4.stackSize;
            return true;
         }
      }

      return false;
   }

   @Override
   public void onCrafting(ItemStack var1) {
      var1.onCrafting(this.thePlayer.o, this.thePlayer, this.field_75231_g);
      this.field_75231_g = 0;
   }

   @Override
   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.onCrafting(var2);
      MerchantRecipe var3 = this.theMerchantInventory.getCurrentRecipe();
      if (var3 != null) {
         ItemStack var4 = this.theMerchantInventory.getStackInSlot(0);
         ItemStack var5 = this.theMerchantInventory.getStackInSlot(1);
         if (this.doTrade(var3, var4, var5) || this.doTrade(var3, var5, var4)) {
            this.theMerchant.useRecipe(var3);
            var1.triggerAchievement(StatList.timesTradedWithVillagerStat);
            if (var4 != null && var4.stackSize <= 0) {
               var4 = null;
            }

            if (var5 != null && var5.stackSize <= 0) {
               var5 = null;
            }

            this.theMerchantInventory.setInventorySlotContents(0, var4);
            this.theMerchantInventory.setInventorySlotContents(1, var5);
         }
      }
   }

   public SlotMerchantResult(EntityPlayer var1, IMerchant var2, InventoryMerchant var3, int var4, int var5, int var6) {
      super(var3, var4, var5, var6);
      this.thePlayer = var1;
      this.theMerchant = var2;
      this.theMerchantInventory = var3;
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return false;
   }
}
