package net.minecraft.inventory;

import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.handler.codec.http.cors.CorsHandler;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager$2;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import org.apache.log4j.helpers.MDCKeySetExtractor;

public class InventoryCraftResult implements IInventory {
   public Base64Encoder field_0003;
   public MDCKeySetExtractor field_0005;
   public ItemStack[] stackResult = new ItemStack[1];
   public EntityAIMate field_0004;
   public NetworkManager$2 field_0000;
   public CorsHandler field_0001;

   @Override
   public int getSizeInventory() {
      return 1;
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.stackResult[0] = var2;
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.stackResult[0] != null) {
         ItemStack var2 = this.stackResult[0];
         this.stackResult[0] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.stackResult.length; var1++) {
         this.stackResult[var1] = null;
      }
   }

   @Override
   public void markDirty() {
   }

   @Override
   public boolean u_() {
      return false;
   }

   @Override
   public String z_() {
      return "Result";
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.stackResult[0] != null) {
         ItemStack var3 = this.stackResult[0];
         this.stackResult[0] = null;
         return var3;
      } else {
         return null;
      }
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return this.stackResult[0];
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }
}
