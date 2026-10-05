package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging {
   public float itemDropChance = 1.0F;

   public EntityItemFrame(World var1) {
      super(var1);
   }

   public void setDisplayedItem(ItemStack var1) {
      this.setDisplayedItemWithUpdate(var1, true);
   }

   public void dropItemOrSelf(Entity var1, boolean var2) {
      if (this.o.Q().getBoolean("doEntityDrops")) {
         ItemStack var3 = this.getDisplayedItem();
         if (var1 instanceof EntityPlayer) {
            EntityPlayer var4 = (EntityPlayer)var1;
            if (var4.bA.isCreativeMode) {
               this.removeFrameFromMap(var3);
               return;
            }
         }

         if (var2) {
            this.a(new ItemStack(Items.item_frame), 0.0F);
         }

         if (var3 != null && this.V.nextFloat() < this.itemDropChance) {
            var3 = var3.copy();
            this.removeFrameFromMap(var3);
            this.a(var3, 0.0F);
         }
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      NBTTagCompound var2 = var1.getCompoundTag("Item");
      if (var2 != null && !var2.hasNoTags()) {
         this.setDisplayedItemWithUpdate(ItemStack.loadItemStackFromNBT(var2), false);
         this.func_174865_a(var1.getByte("ItemRotation"), false);
         if (var1.hasKey("ItemDropChance", 99)) {
            this.itemDropChance = var1.getFloat("ItemDropChance");
         }

         if (var1.hasKey("Direction")) {
            this.func_174865_a(this.getRotation() * 2, false);
         }
      }

      super.readEntityFromNBT(var1);
   }

   @Override
   public int getHeightPixels() {
      return 12;
   }

   public ItemStack getDisplayedItem() {
      return this.H().getWatchableObjectItemStack(8);
   }

   @Override
   public int getWidthPixels() {
      return 12;
   }

   public void func_174865_a(int var1, boolean var2) {
      this.H().updateObject(9, (byte)(var1 % 8));
      if (var2 && this.a != null) {
         this.o.updateComparatorOutputLevel(this.a, Blocks.air);
      }
   }

   public void removeFrameFromMap(ItemStack var1) {
      if (var1 != null) {
         if (var1.getItem() == Items.filled_map) {
            MapData var2 = ((ItemMap)var1.getItem()).getMapData(var1, this.o);
            var2.mapDecorations.remove("frame-" + this.F());
         }

         var1.setItemFrame((EntityItemFrame)null);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (!var1.isExplosion() && this.getDisplayedItem() != null) {
         if (!this.o.D) {
            this.dropItemOrSelf(var1.getEntity(), false);
            this.setDisplayedItem((ItemStack)null);
         }

         return true;
      } else {
         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public void k_() {
      this.H().addObjectByDataType(8, 5);
      this.H().addObject(9, (byte)0);
   }

   public void setDisplayedItemWithUpdate(ItemStack var1, boolean var2) {
      if (var1 != null) {
         var1 = var1.copy();
         var1.stackSize = 1;
         var1.setItemFrame(this);
      }

      this.H().updateObject(8, var1);
      this.H().setObjectWatched(8);
      if (var2 && this.a != null) {
         this.o.updateComparatorOutputLevel(this.a, Blocks.air);
      }
   }

   public void setItemRotation(int var1) {
      this.func_174865_a(var1, true);
   }

   @Override
   public float getCollisionBorderSize() {
      return 0.0F;
   }

   public int getRotation() {
      return this.H().getWatchableObjectByte(9);
   }

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      double var3 = 16.0;
      var3 = var3 * 64.0 * this.j;
      return var1 < var3 * var3;
   }

   public int func_174866_q() {
      return this.getDisplayedItem() == null ? 0 : this.getRotation() % 8 + 1;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      if (this.getDisplayedItem() != null) {
         var1.setTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
         var1.setByte("ItemRotation", (byte)this.getRotation());
         var1.setFloat("ItemDropChance", this.itemDropChance);
      }

      super.writeEntityToNBT(var1);
   }

   public EntityItemFrame(World var1, BlockPos var2, EnumFacing var3) {
      super(var1, var2);
      this.a(var3);
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      if (this.getDisplayedItem() == null) {
         ItemStack var2 = var1.getHeldItem();
         if (var2 != null && !this.o.D) {
            this.setDisplayedItem(var2);
            if (!var1.bA.isCreativeMode && --var2.stackSize <= 0) {
               var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
            }
         }
      } else if (!this.o.D) {
         this.setItemRotation(this.getRotation() + 1);
      }

      return true;
   }

   @Override
   public void onBroken(Entity var1) {
      this.dropItemOrSelf(var1, true);
   }
}
