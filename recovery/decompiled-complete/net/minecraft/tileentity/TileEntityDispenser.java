package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDispenser extends TileEntityLockable implements IInventory {
   public ItemStack[] stacks = new ItemStack[9];
   public EntityFootStepFX field_0003;
   public String a;
   public static Random RNG = new Random();

   @Override
   public String getGuiID() {
      return "minecraft:dispenser";
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.stacks.length; var3++) {
         if (this.stacks[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.stacks[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if (this.u_()) {
         var1.setString("CustomName", this.a);
      }
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return this.stacks[var1];
   }

   public int getDispenseSlot() {
      int var1 = -1;
      int var2 = 1;

      for (int var3 = 0; var3 < this.stacks.length; var3++) {
         if (this.stacks[var3] != null && RNG.nextInt(var2++) == 0) {
            var1 = var3;
         }
      }

      return var1;
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.stacks[var1] = var2;
      if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   public void setCustomName(String var1) {
      this.a = var1;
   }

   public int addItemStack(ItemStack var1) {
      for (int var2 = 0; var2 < this.stacks.length; var2++) {
         if (this.stacks[var2] == null || this.stacks[var2].getItem() == null) {
            this.setInventorySlotContents(var2, var1);
            return var2;
         }
      }

      return -1;
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.b.getTileEntity(this.c) != this ? false : var1.e(this.c.getX() + 0.5, this.c.getY() + 0.5, this.c.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.stacks = new ItemStack[this.getSizeInventory()];

      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         int var5 = var4.getByte("Slot") & 255;
         if (var5 >= 0 && var5 < this.stacks.length) {
            this.stacks[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      if (var1.hasKey("CustomName", 8)) {
         this.a = var1.getString("CustomName");
      }
   }

   @Override
   public String z_() {
      return this.u_() ? this.a : "container.dispenser";
   }

   @Override
   public boolean u_() {
      return this.a != null;
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerDispenser(var1, this);
   }

   @Override
   public int getSizeInventory() {
      return 9;
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.stacks[var1] != null) {
         if (this.stacks[var1].stackSize <= var2) {
            ItemStack var4 = this.stacks[var1];
            this.stacks[var1] = null;
            this.markDirty();
            return var4;
         } else {
            ItemStack var3 = this.stacks[var1].splitStack(var2);
            if (this.stacks[var1].stackSize == 0) {
               this.stacks[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.stacks.length; var1++) {
         this.stacks[var1] = null;
      }
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.stacks[var1] != null) {
         ItemStack var2 = this.stacks[var1];
         this.stacks[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public int getFieldCount() {
      return 0;
   }
}
