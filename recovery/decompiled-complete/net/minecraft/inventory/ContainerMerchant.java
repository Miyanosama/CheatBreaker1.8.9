package net.minecraft.inventory;

import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler;
import net.minecraft.block.BlockTrapDoor$DoorHalf;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;

public class ContainerMerchant extends Container {
   public InventoryMerchant merchantInventory;
   public Achievement field_0006;
   public WebSocketServerProtocolHandshakeHandler field_0002;
   public BlockTrapDoor$DoorHalf field_0000;
   public World theWorld;
   public ModuleListElement field_0004;
   public IMerchant theMerchant;

   public void setCurrentRecipeIndex(int var1) {
      this.merchantInventory.setCurrentRecipeIndex(var1);
   }

   @Override
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();
   }

   @Override
   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      this.theMerchant.setCustomer((EntityPlayer)null);
      super.onContainerClosed(var1);
      if (!this.theWorld.D) {
         ItemStack var2 = this.merchantInventory.removeStackFromSlot(0);
         if (var2 != null) {
            var1.dropPlayerItemWithRandomChoice(var2, false);
         }

         var2 = this.merchantInventory.removeStackFromSlot(1);
         if (var2 != null) {
            var1.dropPlayerItemWithRandomChoice(var2, false);
         }
      }
   }

   public ContainerMerchant(InventoryPlayer var1, IMerchant var2, World var3) {
      this.theMerchant = var2;
      this.theWorld = var3;
      this.merchantInventory = new InventoryMerchant(var1.player, var2);
      this.a(new Slot(this.merchantInventory, 0, 36, 53));
      this.a(new Slot(this.merchantInventory, 1, 62, 53));
      this.a(new SlotMerchantResult(var1.player, var2, this.merchantInventory, 2, 120, 53));

      for (int var4 = 0; var4 < 3; var4++) {
         for (int var5 = 0; var5 < 9; var5++) {
            this.a(new Slot(var1, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
         }
      }

      for (int var6 = 0; var6 < 9; var6++) {
         this.a(new Slot(var1, var6, 8 + var6 * 18, 142));
      }
   }

   @Override
   public void onCraftMatrixChanged(IInventory var1) {
      this.merchantInventory.resetRecipeAndSlots();
      super.onCraftMatrixChanged(var1);
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.theMerchant.getCustomer() == var1;
   }

   public InventoryMerchant getMerchantInventory() {
      return this.merchantInventory;
   }

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
         } else if (var2 != 0 && var2 != 1) {
            if (var2 >= 3 && var2 < 30) {
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
   public void updateProgressBar(int var1, int var2) {
   }
}
