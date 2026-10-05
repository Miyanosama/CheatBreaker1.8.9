package net.minecraft.inventory;

import io.netty.channel.rxtx.DefaultRxtxChannelConfig;
import io.netty.channel.socket.oio.DefaultOioSocketChannelConfig;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor;

public class InventoryCrafting implements IInventory {
   public GuiScreenOptionsSounds field_0004;
   public int inventoryWidth;
   public Container eventHandler;
   public DefaultOioSocketChannelConfig field_0006;
   public DefaultRxtxChannelConfig field_0000;
   public int inventoryHeight;
   public RenderLeashKnot field_0008;
   public ItemStack[] stackList;
   public CategoryNodeEditor field_0002;

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }

   public int getWidth() {
      return this.inventoryWidth;
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   @Override
   public int getSizeInventory() {
      return this.stackList.length;
   }

   @Override
   public void markDirty() {
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.stackList[var1] = var2;
      this.eventHandler.onCraftMatrixChanged(this);
   }

   public ItemStack getStackInRowAndColumn(int var1, int var2) {
      return var1 >= 0 && var1 < this.inventoryWidth && var2 >= 0 && var2 <= this.inventoryHeight
         ? this.getStackInSlot(var1 + var2 * this.inventoryWidth)
         : null;
   }

   public InventoryCrafting(Container var1, int var2, int var3) {
      int var4 = var2 * var3;
      this.stackList = new ItemStack[var4];
      this.eventHandler = var1;
      this.inventoryWidth = var2;
      this.inventoryHeight = var3;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public boolean u_() {
      return false;
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.stackList.length; var1++) {
         this.stackList[var1] = null;
      }
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return var1 >= this.getSizeInventory() ? null : this.stackList[var1];
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.stackList[var1] != null) {
         ItemStack var2 = this.stackList[var1];
         this.stackList[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public String z_() {
      return "container.crafting";
   }

   public int getHeight() {
      return this.inventoryHeight;
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.stackList[var1] != null) {
         if (this.stackList[var1].stackSize <= var2) {
            ItemStack var4 = this.stackList[var1];
            this.stackList[var1] = null;
            this.eventHandler.onCraftMatrixChanged(this);
            return var4;
         } else {
            ItemStack var3 = this.stackList[var1].splitStack(var2);
            if (this.stackList[var1].stackSize == 0) {
               this.stackList[var1] = null;
            }

            this.eventHandler.onCraftMatrixChanged(this);
            return var3;
         }
      } else {
         return null;
      }
   }
}
