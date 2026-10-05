package net.minecraft.entity.item;

import net.minecraft.client.util.JsonException;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass0706;

public class EntityEnderEye extends Entity {
   public UnidentifiedClass0706 field_0003;
   public JsonException field_0005;
   public int despawnTimer;
   public double targetY;
   public double targetX;
   public boolean shatterOrDrop;
   public double targetZ;

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      double var3 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(var3)) {
         var3 = 4.0;
      }

      var3 *= 64.0;
      return var1 < var3 * var3;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   public void moveTowards(BlockPos var1) {
      double var2 = var1.getX();
      int var4 = var1.getY();
      double var5 = var1.getZ();
      double var7 = var2 - this.s;
      double var9 = var5 - this.u;
      float var11 = MathHelper.sqrt_double(var7 * var7 + var9 * var9);
      if (var11 > 12.0F) {
         this.targetX = this.s + var7 / var11 * 12.0;
         this.targetZ = this.u + var9 / var11 * 12.0;
         this.targetY = this.t + 8.0;
      } else {
         this.targetX = var2;
         this.targetY = var4;
         this.targetZ = var5;
      }

      this.despawnTimer = 0;
      this.shatterOrDrop = this.V.nextInt(5) > 0;
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
   }

   public EntityEnderEye(World var1, double var2, double var4, double var6) {
      super(var1);
      this.despawnTimer = 0;
      this.setSize(0.25F, 0.25F);
      this.b(var2, var4, var6);
   }

   @Override
   public void onUpdate() {
      this.P = this.s;
      this.Q = this.t;
      this.R = this.u;
      super.onUpdate();
      this.s = this.s + this.v;
      this.t = this.t + this.w;
      this.u = this.u + this.x;
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
      if (!this.o.D) {
         double var2 = this.targetX - this.s;
         double var4 = this.targetZ - this.u;
         float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
         float var7 = (float)MathHelper.atan2(var4, var2);
         double var8 = var1 + (var6 - var1) * 0.0025;
         if (var6 < 1.0F) {
            var8 *= 0.8;
            this.w *= 0.8;
         }

         this.v = Math.cos(var7) * var8;
         this.x = Math.sin(var7) * var8;
         if (this.t < this.targetY) {
            this.w = this.w + (1.0 - this.w) * 0.015F;
         } else {
            this.w = this.w + (-1.0 - this.w) * 0.015F;
         }
      }

      float var10 = 0.25F;
      if (this.V()) {
         for (int var3 = 0; var3 < 4; var3++) {
            this.o
               .spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.s - this.v * var10, this.t - this.w * var10, this.u - this.x * var10, this.v, this.w, this.x);
         }
      } else {
         this.o
            .spawnParticle(
               EnumParticleTypes.PORTAL,
               this.s - this.v * var10 + this.V.nextDouble() * 0.6 - 0.3,
               this.t - this.w * var10 - 0.5,
               this.u - this.x * var10 + this.V.nextDouble() * 0.6 - 0.3,
               this.v,
               this.w,
               this.x
            );
      }

      if (!this.o.D) {
         this.b(this.s, this.t, this.u);
         this.despawnTimer++;
         if (this.despawnTimer > 80 && !this.o.D) {
            this.setDead();
            if (this.shatterOrDrop) {
               this.o.spawnEntityInWorld(new EntityItem(this.o, this.s, this.t, this.u, new ItemStack(Items.ender_eye)));
            } else {
               this.o.b(2003, new BlockPos(this), 0);
            }
         }
      }
   }

   @Override
   public void k_() {
   }

   @Override
   public boolean r_() {
      return false;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
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

   public EntityEnderEye(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }
}
