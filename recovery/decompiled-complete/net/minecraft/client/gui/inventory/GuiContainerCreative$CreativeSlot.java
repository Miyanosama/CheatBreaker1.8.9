package net.minecraft.client.gui.inventory;

import net.minecraft.block.BlockPistonExtension$1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.UserListWhitelistEntry;
import recovered.unidentified.UnidentifiedClass3909;

public class GuiContainerCreative$CreativeSlot extends Slot {
   public UserListWhitelistEntry field_0004;
   public Slot slot;
   public UnidentifiedClass3909 field_0003;
   public BlockPistonExtension$1 field_0000;

   @Override
   public ItemStack getStack() {
      return this.slot.getStack();
   }

   @Override
   public void onSlotChanged() {
      this.slot.onSlotChanged();
   }

   public GuiContainerCreative$CreativeSlot(GuiContainerCreative var1, Slot var2, int var3) {
      this.field_148333_a = var1;
      super(var2.inventory, var3, 0, 0);
      this.slot = var2;
   }

   @Override
   public void putStack(ItemStack var1) {
      this.slot.putStack(var1);
   }

   @Override
   public int getItemStackLimit(ItemStack var1) {
      return this.slot.getItemStackLimit(var1);
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return this.slot.isItemValid(var1);
   }

   @Override
   public ItemStack decrStackSize(int var1) {
      return this.slot.decrStackSize(var1);
   }

   @Override
   public String getSlotTexture() {
      return this.slot.getSlotTexture();
   }

   @Override
   public boolean isHere(IInventory var1, int var2) {
      return this.slot.isHere(var1, var2);
   }

   @Override
   public int getSlotStackLimit() {
      return this.slot.getSlotStackLimit();
   }

   @Override
   public boolean getHasStack() {
      return this.slot.getHasStack();
   }

   @Override
   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.slot.onPickupFromSlot(var1, var2);
   }
}
