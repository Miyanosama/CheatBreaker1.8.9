package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAgeable extends EntityCreature {
   public int growingAge;
   public int field_175503_c;
   public float ageHeight;
   public int field_175502_b;
   public float ageWidth = -1.0F;

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.o.D) {
         if (this.field_175503_c > 0) {
            if (this.field_175503_c % 4 == 0) {
               this.o
                  .spawnParticle(
                     EnumParticleTypes.VILLAGER_HAPPY,
                     this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
                     this.t + 0.5 + this.V.nextFloat() * this.K,
                     this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
                     0.0,
                     0.0,
                     0.0
                  );
            }

            this.field_175503_c--;
         }

         this.setScaleForAge(this.o_());
      } else {
         int var1 = this.l();
         if (var1 < 0) {
            this.setGrowingAge(++var1);
            if (var1 == 0) {
               this.onGrowingAdult();
            }
         } else if (var1 > 0) {
            this.setGrowingAge(--var1);
         }
      }
   }

   public void addGrowth(int var1) {
      this.func_175501_a(var1, false);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Age", this.l());
      var1.setInteger("ForcedAge", this.field_175502_b);
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.spawn_egg) {
         if (!this.o.D) {
            Class var3 = EntityList.getClassFromID(var2.getMetadata());
            if (var3 != null && this.getClass() == var3) {
               EntityAgeable var4 = this.createChild(this);
               if (var4 != null) {
                  var4.setGrowingAge(-24000);
                  var4.a_(this.s, this.t, this.u, 0.0F, 0.0F);
                  this.o.spawnEntityInWorld(var4);
                  if (var2.hasDisplayName()) {
                     var4.a(var2.getDisplayName());
                  }

                  if (!var1.bA.isCreativeMode) {
                     var2.stackSize--;
                     if (var2.stackSize <= 0) {
                        var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void setScaleForAge(boolean var1) {
      this.setScale(var1 ? 0.5F : 1.0F);
   }

   public void setScale(float var1) {
      super.setSize(this.ageWidth * var1, this.ageHeight * var1);
   }

   public void onGrowingAdult() {
   }

   public void func_175501_a(int var1, boolean var2) {
      int var3 = this.l();
      var3 += var1 * 20;
      if (var3 > 0) {
         var3 = 0;
         if (var3 < 0) {
            this.onGrowingAdult();
         }
      }

      int var5 = var3 - var3;
      this.setGrowingAge(var3);
      if (var2) {
         this.field_175502_b += var5;
         if (this.field_175503_c == 0) {
            this.field_175503_c = 40;
         }
      }

      if (this.l() == 0) {
         this.setGrowingAge(this.field_175502_b);
      }
   }

   public void setGrowingAge(int var1) {
      this.ac.updateObject(12, (byte)MathHelper.clamp_int(var1, -1, 1));
      this.growingAge = var1;
      this.setScaleForAge(this.o_());
   }

   public EntityAgeable(World var1) {
      super(var1);
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(12, (byte)0);
   }

   public abstract EntityAgeable createChild(EntityAgeable var1);

   @Override
   public void setSize(float var1, float var2) {
      boolean var3 = this.ageWidth > 0.0F;
      this.ageWidth = var1;
      this.ageHeight = var2;
      if (!var3) {
         this.setScale(1.0F);
      }
   }

   @Override
   public boolean o_() {
      return this.l() < 0;
   }

   public int l() {
      return this.o.D ? this.ac.getWatchableObjectByte(12) : this.growingAge;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setGrowingAge(var1.getInteger("Age"));
      this.field_175502_b = var1.getInteger("ForcedAge");
   }
}
