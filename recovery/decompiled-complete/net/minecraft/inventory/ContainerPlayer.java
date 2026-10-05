package net.minecraft.inventory;

import io.netty.handler.codec.socks.SocksCmdType;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerPlayer extends Container {
   public boolean isLocalWorld;
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
   public IInventory craftResult = new InventoryCraftResult();
   public SpdyHeaderBlockRawDecoder$1 field_0000;
   public SocksCmdType field_0001;
   public EntityPlayer thePlayer;

   @Override
   public void onCraftMatrixChanged(IInventory var1) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.o));
   }

   public ContainerPlayer(InventoryPlayer var1, boolean var2, EntityPlayer var3) {
      this.isLocalWorld = var2;
      this.thePlayer = var3;
      this.a(new SlotCrafting(var1.player, this.craftMatrix, this.craftResult, 0, 144, 36));

      for (int var4 = 0; var4 < 2; var4++) {
         for (int var5 = 0; var5 < 2; var5++) {
            this.a(new Slot(this.craftMatrix, var5 + var4 * 2, 88 + var5 * 18, 26 + var4 * 18));
         }
      }

      for (int var6 = 0; var6 < 4; var6++) {
         this.a(new ContainerPlayer$1(this, var1, var1.getSizeInventory() - 1 - var6, 8, 8 + var6 * 18, var6));
      }

      for (int var7 = 0; var7 < 3; var7++) {
         for (int var9 = 0; var9 < 9; var9++) {
            this.a(new Slot(var1, var9 + (var7 + 1) * 9, 8 + var9 * 18, 84 + var7 * 18));
         }
      }

      for (int var8 = 0; var8 < 9; var8++) {
         this.a(new Slot(var1, var8, 8 + var8 * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }

   @Override
   public boolean canMergeSlot(ItemStack var1, Slot var2) {
      return var2.inventory != this.craftResult && super.canMergeSlot(var1, var2);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if (var2 == 0) {
            if (!this.mergeItemStack(var5, 9, 45, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if (var2 >= 1 && var2 < 5) {
            if (!this.mergeItemStack(var5, 9, 45, false)) {
               return null;
            }
         } else if (var2 >= 5 && var2 < 9) {
            if (!this.mergeItemStack(var5, 9, 45, false)) {
               return null;
            }
         } else if (var3.getItem() instanceof ItemArmor && !this.c.get(5 + ((ItemArmor)var3.getItem()).armorType).getHasStack()) {
            int var6 = 5 + ((ItemArmor)var3.getItem()).armorType;
            if (!this.mergeItemStack(var5, var6, var6 + 1, false)) {
               return null;
            }
         } else if (var2 >= 9 && var2 < 36) {
            if (!this.mergeItemStack(var5, 36, 45, false)) {
               return null;
            }
         } else if (var2 >= 36 && var2 < 45) {
            if (!this.mergeItemStack(var5, 9, 36, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(var5, 9, 45, false)) {
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

   @Override
   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);

      for (int var2 = 0; var2 < 4; var2++) {
         ItemStack var3 = this.craftMatrix.removeStackFromSlot(var2);
         if (var3 != null) {
            var1.dropPlayerItemWithRandomChoice(var3, false);
         }
      }

      this.craftResult.setInventorySlotContents(0, (ItemStack)null);
   }
}
