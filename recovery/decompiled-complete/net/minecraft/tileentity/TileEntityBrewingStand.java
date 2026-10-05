package net.minecraft.tileentity;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory$1;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.optifine.config.NbtTagValue;

public class TileEntityBrewingStand extends TileEntityLockable implements ISidedInventory, ITickable {
   public WorldGenTrees field_0006;
   public int brewTime;
   public Item ingredientID;
   public NbtTagValue field_0009;
   public boolean[] filledSlots;
   public static int[] inputSlots = new int[]{3};
   public PotionHealthBoost field_0010;
   public static int[] outputSlots = new int[]{0, 1, 2};
   public String customName;
   public ItemStack[] brewingItemStacks = new ItemStack[4];
   public RenderBlaze field_0000;
   public InsecureTrustManagerFactory$1 field_0008;

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public int getFieldCount() {
      return 1;
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.brewingItemStacks.length; var1++) {
         this.brewingItemStacks[var1] = null;
      }
   }

   @Override
   public String z_() {
      return this.u_() ? this.customName : "container.brewing";
   }

   public void setName(String var1) {
      this.customName = var1;
   }

   public boolean[] func_174902_m() {
      boolean[] var1 = new boolean[3];

      for (int var2 = 0; var2 < 3; var2++) {
         if (this.brewingItemStacks[var2] != null) {
            var1[var2] = true;
         }
      }

      return var1;
   }

   @Override
   public boolean canInsertItem(int var1, ItemStack var2, EnumFacing var3) {
      return this.isItemValidForSlot(var1, var2);
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerBrewingStand(var1, this);
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (var1 >= 0 && var1 < this.brewingItemStacks.length) {
         ItemStack var2 = this.brewingItemStacks[var1];
         this.brewingItemStacks[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void brewPotions() {
      if (this.canBrew()) {
         ItemStack var1 = this.brewingItemStacks[3];

         for (int var2 = 0; var2 < 3; var2++) {
            if (this.brewingItemStacks[var2] != null && this.brewingItemStacks[var2].getItem() == Items.potionitem) {
               int var3 = this.brewingItemStacks[var2].getMetadata();
               int var4 = this.getPotionResult(var3, var1);
               List var5 = Items.potionitem.getEffects(var3);
               List var6 = Items.potionitem.getEffects(var4);
               if (var3 > 0 && var5 == var6 || var5 != null && (var5.equals(var6) || var6 == null)) {
                  if (!ItemPotion.isSplash(var3) && ItemPotion.isSplash(var4)) {
                     this.brewingItemStacks[var2].setItemDamage(var4);
                  }
               } else if (var3 != var4) {
                  this.brewingItemStacks[var2].setItemDamage(var4);
               }
            }
         }

         if (var1.getItem().hasContainerItem()) {
            this.brewingItemStacks[3] = new ItemStack(var1.getItem().getContainerItem());
         } else {
            this.brewingItemStacks[3].stackSize--;
            if (this.brewingItemStacks[3].stackSize <= 0) {
               this.brewingItemStacks[3] = null;
            }
         }
      }
   }

   @Override
   public int[] getSlotsForFace(EnumFacing var1) {
      return var1 == EnumFacing.UP ? inputSlots : outputSlots;
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   public boolean canBrew() {
      if (this.brewingItemStacks[3] != null && this.brewingItemStacks[3].stackSize > 0) {
         ItemStack var1 = this.brewingItemStacks[3];
         if (!var1.getItem().isPotionIngredient(var1)) {
            return false;
         } else {
            boolean var2 = false;

            for (int var3 = 0; var3 < 3; var3++) {
               if (this.brewingItemStacks[var3] != null && this.brewingItemStacks[var3].getItem() == Items.potionitem) {
                  int var4 = this.brewingItemStacks[var3].getMetadata();
                  int var5 = this.getPotionResult(var4, var1);
                  if (!ItemPotion.isSplash(var4) && ItemPotion.isSplash(var5)) {
                     var2 = true;
                     break;
                  }

                  List var6 = Items.potionitem.getEffects(var4);
                  List var7 = Items.potionitem.getEffects(var5);
                  if ((var4 <= 0 || var6 != var7) && (var6 == null || !var6.equals(var7) && var7 != null) && var4 != var5) {
                     var2 = true;
                     break;
                  }
               }
            }

            return var2;
         }
      } else {
         return false;
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setShort("BrewTime", (short)this.brewTime);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.brewingItemStacks.length; var3++) {
         if (this.brewingItemStacks[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.brewingItemStacks[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if (this.u_()) {
         var1.setString("CustomName", this.customName);
      }
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void setField(int var1, int var2) {
      switch (var1) {
         case 0:
            this.brewTime = var2;
      }
   }

   public int getPotionResult(int var1, ItemStack var2) {
      return var2 == null ? var1 : (var2.getItem().isPotionIngredient(var2) ? PotionHelper.applyIngredient(var1, var2.getItem().getPotionEffect(var2)) : var1);
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.b.getTileEntity(this.c) != this ? false : var1.e(this.c.getX() + 0.5, this.c.getY() + 0.5, this.c.getZ() + 0.5) <= 64.0;
   }

   @Override
   public boolean u_() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (var1 >= 0 && var1 < this.brewingItemStacks.length) {
         ItemStack var3 = this.brewingItemStacks[var1];
         this.brewingItemStacks[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return var1 >= 0 && var1 < this.brewingItemStacks.length ? this.brewingItemStacks[var1] : null;
   }

   @Override
   public String getGuiID() {
      return "minecraft:brewing_stand";
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      if (var1 >= 0 && var1 < this.brewingItemStacks.length) {
         this.brewingItemStacks[var1] = var2;
      }
   }

   @Override
   public boolean canExtractItem(int var1, ItemStack var2, EnumFacing var3) {
      return true;
   }

   @Override
   public void update() {
      if (this.brewTime > 0) {
         this.brewTime--;
         if (this.brewTime == 0) {
            this.brewPotions();
            this.markDirty();
         } else if (!this.canBrew()) {
            this.brewTime = 0;
            this.markDirty();
         } else if (this.ingredientID != this.brewingItemStacks[3].getItem()) {
            this.brewTime = 0;
            this.markDirty();
         }
      } else if (this.canBrew()) {
         this.brewTime = 400;
         this.ingredientID = this.brewingItemStacks[3].getItem();
      }

      if (!this.b.D) {
         boolean[] var1 = this.func_174902_m();
         if (!Arrays.equals(var1, this.filledSlots)) {
            this.filledSlots = var1;
            IBlockState var2 = this.b.getBlockState(this.v());
            if (!(var2.getBlock() instanceof BlockBrewingStand)) {
               return;
            }

            for (int var3 = 0; var3 < BlockBrewingStand.HAS_BOTTLE.length; var3++) {
               var2 = var2.withProperty(BlockBrewingStand.HAS_BOTTLE[var3], var1[var3]);
            }

            this.b.a(this.c, var2, 2);
         }
      }
   }

   @Override
   public int getSizeInventory() {
      return this.brewingItemStacks.length;
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.brewingItemStacks = new ItemStack[this.getSizeInventory()];

      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         byte var5 = var4.getByte("Slot");
         if (var5 >= 0 && var5 < this.brewingItemStacks.length) {
            this.brewingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      this.brewTime = var1.getShort("BrewTime");
      if (var1.hasKey("CustomName", 8)) {
         this.customName = var1.getString("CustomName");
      }
   }

   @Override
   public int getField(int var1) {
      switch (var1) {
         case 0:
            return this.brewTime;
         default:
            return 0;
      }
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return var1 == 3 ? var2.getItem().isPotionIngredient(var2) : var2.getItem() == Items.potionitem || var2.getItem() == Items.glass_bottle;
   }
}
