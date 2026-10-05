package net.minecraft.entity.item;

import io.netty.util.concurrent.AbstractEventExecutor$EventExecutorIterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import org.json.JSONPointer;

public class EntityTNTPrimed extends Entity {
   public int fuse;
   public AbstractEventExecutor$EventExecutorIterator field_0004;
   public EntityLivingBase tntPlacedBy;
   public EntityAILookAtTradePlayer field_0003;
   public JSONPointer field_0000;

   public void explode() {
      float var1 = 4.0F;
      this.o.createExplosion(this, this.s, this.t + this.K / 16.0F, this.u, var1, true);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.fuse = var1.getByte("Fuse");
   }

   @Override
   public void k_() {
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.w -= 0.04F;
      this.d(this.v, this.w, this.x);
      this.v *= 0.98F;
      this.w *= 0.98F;
      this.x *= 0.98F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
         this.w *= -0.5;
      }

      if (this.fuse-- <= 0) {
         this.setDead();
         if (!this.o.D) {
            this.explode();
         }
      } else {
         this.handleWaterMovement();
         this.o.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.s, this.t + 0.5, this.u, 0.0, 0.0, 0.0);
      }
   }

   @Override
   public float getEyeHeight() {
      return 0.0F;
   }

   public EntityLivingBase getTntPlacedBy() {
      return this.tntPlacedBy;
   }

   public EntityTNTPrimed(World var1) {
      super(var1);
      this.k = true;
      this.setSize(0.98F, 0.98F);
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.I;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setByte("Fuse", (byte)this.fuse);
   }

   public EntityTNTPrimed(World var1, double var2, double var4, double var6, EntityLivingBase var8) {
      this(var1);
      this.b(var2, var4, var6);
      float var9 = (float)(Math.random() * Math.PI * 2.0);
      this.v = -((float)Math.sin(var9)) * 0.02F;
      this.w = 0.2F;
      this.x = -((float)Math.cos(var9)) * 0.02F;
      this.fuse = 80;
      this.p = var2;
      this.q = var4;
      this.r = var6;
      this.tntPlacedBy = var8;
   }

   @Override
   public boolean l_() {
      return false;
   }
}
