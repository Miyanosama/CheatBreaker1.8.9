package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.optifine.shaders.uniform.ShaderParameterFloat$1;

public class ContainerFurnace extends Container {
   public int totalCookTime;
   public ShaderParameterFloat$1 field_0005;
   public int furnaceBurnTime;
   public int currentItemBurnTime;
   public IInventory tileFurnace;
   public int cookTime;

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if (var2 == 2) {
            if (!this.mergeItemStack(var5, 3, 39, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if (var2 != 1 && var2 != 0) {
            if (FurnaceRecipes.instance().getSmeltingResult(var5) != null) {
               if (!this.mergeItemStack(var5, 0, 1, false)) {
                  return null;
               }
            } else if (TileEntityFurnace.isItemFuel(var5)) {
               if (!this.mergeItemStack(var5, 1, 2, false)) {
                  return null;
               }
            } else if (var2 >= 3 && var2 < 30) {
               if (!this.mergeItemStack(var5, 30, 39, false)) {
                  return null;
               }
            } else if (var2 >= 30 && var2 < 39 && !this.mergeItemStack(var5, 3, 30, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(var5, 3, 39, false)) {
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
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int var1 = 0; var1 < this.e.size(); var1++) {
         ICrafting var2 = this.e.get(var1);
         if (this.cookTime != this.tileFurnace.getField(2)) {
            var2.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));
         }

         if (this.furnaceBurnTime != this.tileFurnace.getField(0)) {
            var2.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));
         }

         if (this.currentItemBurnTime != this.tileFurnace.getField(1)) {
            var2.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));
         }

         if (this.totalCookTime != this.tileFurnace.getField(3)) {
            var2.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
         }
      }

      this.cookTime = this.tileFurnace.getField(2);
      this.furnaceBurnTime = this.tileFurnace.getField(0);
      this.currentItemBurnTime = this.tileFurnace.getField(1);
      this.totalCookTime = this.tileFurnace.getField(3);
   }

   @Override
   public void updateProgressBar(int var1, int var2) {
      this.tileFurnace.setField(var1, var2);
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileFurnace.isUseableByPlayer(var1);
   }

   public ContainerFurnace(InventoryPlayer var1, IInventory var2) {
      this.tileFurnace = var2;
      this.a(new Slot(var2, 0, 56, 17));
      this.a(new SlotFurnaceFuel(var2, 1, 56, 53));
      this.a(new SlotFurnaceOutput(var1.player, var2, 2, 116, 35));

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
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
      var1.sendAllWindowProperties(this, this.tileFurnace);
   }
}
