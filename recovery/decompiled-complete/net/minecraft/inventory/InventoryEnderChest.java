package net.minecraft.inventory;

import net.minecraft.block.BlockRailBase$Rail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.storage.DerivedWorldInfo;
import recovered.unidentified.UnidentifiedClass5074;

public class InventoryEnderChest extends InventoryBasic {
   public UnidentifiedClass5074 field_0001;
   public BlockRailBase$Rail field_0003;
   public TileEntityEnderChest associatedChest;
   public DerivedWorldInfo field_0002;

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.associatedChest != null && !this.associatedChest.canBeUsed(var1) ? false : super.isUseableByPlayer(var1);
   }

   public NBTTagList saveInventoryToNBT() {
      NBTTagList var1 = new NBTTagList();

      for (int var2 = 0; var2 < this.getSizeInventory(); var2++) {
         ItemStack var3 = this.getStackInSlot(var2);
         if (var3 != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var2);
            var3.writeToNBT(var4);
            var1.appendTag(var4);
         }
      }

      return var1;
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
      if (this.associatedChest != null) {
         this.associatedChest.closeChest();
      }

      super.closeInventory(var1);
      this.associatedChest = null;
   }

   @Override
   public void openInventory(EntityPlayer var1) {
      if (this.associatedChest != null) {
         this.associatedChest.openChest();
      }

      super.openInventory(var1);
   }

   public InventoryEnderChest() {
      super("container.enderchest", false, 27);
   }

   public void loadInventoryFromNBT(NBTTagList var1) {
      for (int var2 = 0; var2 < this.getSizeInventory(); var2++) {
         this.setInventorySlotContents(var2, (ItemStack)null);
      }

      for (int var5 = 0; var5 < var1.tagCount(); var5++) {
         NBTTagCompound var3 = var1.getCompoundTagAt(var5);
         int var4 = var3.getByte("Slot") & 255;
         if (var4 >= 0 && var4 < this.getSizeInventory()) {
            this.setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
         }
      }
   }

   public void setChestTileEntity(TileEntityEnderChest var1) {
      this.associatedChest = var1;
   }
}
