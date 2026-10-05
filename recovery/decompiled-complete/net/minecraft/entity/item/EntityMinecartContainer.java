package net.minecraft.entity.item;

import io.netty.util.CharsetUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public abstract class EntityMinecartContainer extends EntityMinecart implements ILockableContainer {
   public CharsetUtil field_0002;
   public boolean dropContentsWhenDead;
   public ItemStack[] minecartContainerItems = new ItemStack[36];

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.minecartContainerItems[var1] != null) {
         if (this.minecartContainerItems[var1].stackSize <= var2) {
            ItemStack var4 = this.minecartContainerItems[var1];
            this.minecartContainerItems[var1] = null;
            return var4;
         } else {
            ItemStack var3 = this.minecartContainerItems[var1].splitStack(var2);
            if (this.minecartContainerItems[var1].stackSize == 0) {
               this.minecartContainerItems[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public EntityMinecartContainer(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
      this.dropContentsWhenDead = true;
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public void markDirty() {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.minecartContainerItems.length; var3++) {
         if (this.minecartContainerItems[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.minecartContainerItems[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.I ? false : var1.h(this) <= 64.0;
   }

   @Override
   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      if (this.o.Q().getBoolean("doEntityDrops")) {
         InventoryHelper.dropInventoryItems(this.o, this, this);
      }
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return this.minecartContainerItems[var1];
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void travelToDimension(int var1) {
      this.dropContentsWhenDead = false;
      super.travelToDimension(var1);
   }

   @Override
   public void setLockCode(LockCode var1) {
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public String z_() {
      return this.u_() ? this.aM() : "container.minecart";
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      if (!this.o.D) {
         var1.displayGUIChest(this);
      }

      return true;
   }

   public EntityMinecartContainer(World var1) {
      super(var1);
      this.dropContentsWhenDead = true;
   }

   @Override
   public LockCode getLockCode() {
      return LockCode.EMPTY_CODE;
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.minecartContainerItems[var1] != null) {
         ItemStack var2 = this.minecartContainerItems[var1];
         this.minecartContainerItems[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.minecartContainerItems = new ItemStack[this.getSizeInventory()];

      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         int var5 = var4.getByte("Slot") & 255;
         if (var5 >= 0 && var5 < this.minecartContainerItems.length) {
            this.minecartContainerItems[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }
   }

   @Override
   public boolean B_() {
      return false;
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.minecartContainerItems[var1] = var2;
      if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.minecartContainerItems.length; var1++) {
         this.minecartContainerItems[var1] = null;
      }
   }

   @Override
   public void applyDrag() {
      int var1 = 15 - Container.calcRedstoneFromInventory(this);
      float var2 = 0.98F + var1 * 0.001F;
      this.v *= var2;
      this.w *= 0.0;
      this.x *= var2;
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public void setDead() {
      if (this.dropContentsWhenDead) {
         InventoryHelper.dropInventoryItems(this.o, this, this);
      }

      super.setDead();
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }
}
