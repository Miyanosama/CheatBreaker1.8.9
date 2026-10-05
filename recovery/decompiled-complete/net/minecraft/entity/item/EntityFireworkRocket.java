package net.minecraft.entity.item;

import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatLayerInfo;
import net.optifine.texture.PixelType;

public class EntityFireworkRocket extends Entity {
   public int lifetime;
   public int fireworkAge;
   public GuiFurnace field_0001;
   public FlatLayerInfo field_0003;
   public PixelType field_0000;

   @Override
   public float a_(float var1) {
      return super.a_(var1);
   }

   @Override
   public void setVelocity(double var1, double var3, double var5) {
      this.v = var1;
      this.w = var3;
      this.x = var5;
      if (this.B == 0.0F && this.A == 0.0F) {
         float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         this.A = this.y = (float)(MathHelper.atan2(var1, var5) * 180.0 / Math.PI);
         this.B = this.z = (float)(MathHelper.atan2(var3, var7) * 180.0 / Math.PI);
      }
   }

   @Override
   public void k_() {
      this.ac.addObjectByDataType(8, 5);
   }

   @Override
   public boolean r_() {
      return false;
   }

   public EntityFireworkRocket(World var1, double var2, double var4, double var6, ItemStack var8) {
      super(var1);
      this.fireworkAge = 0;
      this.setSize(0.25F, 0.25F);
      this.b(var2, var4, var6);
      byte var9 = 1;
      if (var8 != null && var8.hasTagCompound()) {
         this.ac.updateObject(8, var8);
         NBTTagCompound var10 = var8.getTagCompound();
         NBTTagCompound var11 = var10.getCompoundTag("Fireworks");
         if (var11 != null) {
            var9 += var11.getByte("Flight");
         }
      }

      this.v = this.V.nextGaussian() * 0.001;
      this.x = this.V.nextGaussian() * 0.001;
      this.w = 0.05;
      this.lifetime = 10 * var9 + this.V.nextInt(6) + this.V.nextInt(7);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setInteger("Life", this.fireworkAge);
      var1.setInteger("LifeTime", this.lifetime);
      ItemStack var2 = this.ac.getWatchableObjectItemStack(8);
      if (var2 != null) {
         NBTTagCompound var3 = new NBTTagCompound();
         var2.writeToNBT(var3);
         var1.setTag("FireworksItem", var3);
      }
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 17 && this.o.D) {
         ItemStack var2 = this.ac.getWatchableObjectItemStack(8);
         NBTTagCompound var3 = null;
         if (var2 != null && var2.hasTagCompound()) {
            var3 = var2.getTagCompound().getCompoundTag("Fireworks");
         }

         this.o.makeFireworks(this.s, this.t, this.u, this.v, this.w, this.x, var3);
      }

      super.handleStatusUpdate(var1);
   }

   @Override
   public int b_(float var1) {
      return super.b_(var1);
   }

   @Override
   public void onUpdate() {
      this.P = this.s;
      this.Q = this.t;
      this.R = this.u;
      super.onUpdate();
      this.v *= 1.15;
      this.x *= 1.15;
      this.w += 0.04;
      this.d(this.v, this.w, this.x);
      float var1 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
      this.y = (float)(MathHelper.atan2(this.v, this.x) * 180.0 / Math.PI);
      this.z = (float)(MathHelper.atan2(this.w, var1) * 180.0 / Math.PI);

      while (this.z - this.B < -180.0F) {
         this.B -= 360.0F;
      }

      while (this.z - this.B >= 180.0F) {
         this.B += 360.0F;
      }

      while (this.y - this.A < -180.0F) {
         this.A -= 360.0F;
      }

      while (this.y - this.A >= 180.0F) {
         this.A += 360.0F;
      }

      this.z = this.B + (this.z - this.B) * 0.2F;
      this.y = this.A + (this.y - this.A) * 0.2F;
      if (this.fireworkAge == 0 && !this.R()) {
         this.o.a(this, "fireworks.launch", 3.0F, 1.0F);
      }

      this.fireworkAge++;
      if (this.o.D && this.fireworkAge % 2 < 2) {
         this.o
            .spawnParticle(
               EnumParticleTypes.FIREWORKS_SPARK, this.s, this.t - 0.3, this.u, this.V.nextGaussian() * 0.05, -this.w * 0.5, this.V.nextGaussian() * 0.05
            );
      }

      if (!this.o.D && this.fireworkAge > this.lifetime) {
         this.o.setEntityState(this, (byte)17);
         this.setDead();
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.fireworkAge = var1.getInteger("Life");
      this.lifetime = var1.getInteger("LifeTime");
      NBTTagCompound var2 = var1.getCompoundTag("FireworksItem");
      if (var2 != null) {
         ItemStack var3 = ItemStack.loadItemStackFromNBT(var2);
         if (var3 != null) {
            this.ac.updateObject(8, var3);
         }
      }
   }

   public EntityFireworkRocket(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      return var1 < 4096.0;
   }
}
