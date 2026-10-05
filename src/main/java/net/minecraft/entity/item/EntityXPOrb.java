package net.minecraft.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityXPOrb extends Entity {
   public int xpOrbAge;
   public int xpTargetColor;
   public int xpValue;
   public EntityPlayer closestPlayer;
   public int delayBeforeCanPickup;
   public int xpColor;
   public int xpOrbHealth = 5;

   public int getTextureByXP() {
      return this.xpValue >= 2477
         ? 10
         : (
            this.xpValue >= 1237
               ? 9
               : (
                  this.xpValue >= 617
                     ? 8
                     : (
                        this.xpValue >= 307
                           ? 7
                           : (
                              this.xpValue >= 149
                                 ? 6
                                 : (
                                    this.xpValue >= 73
                                       ? 5
                                       : (this.xpValue >= 37 ? 4 : (this.xpValue >= 17 ? 3 : (this.xpValue >= 7 ? 2 : (this.xpValue >= 3 ? 1 : 0))))
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.delayBeforeCanPickup > 0) {
         this.delayBeforeCanPickup--;
      }

      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.w -= 0.03F;
      if (this.o.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
         this.w = 0.2F;
         this.v = (this.V.nextFloat() - this.V.nextFloat()) * 0.2F;
         this.x = (this.V.nextFloat() - this.V.nextFloat()) * 0.2F;
         this.playSound("random.fizz", 0.4F, 2.0F + this.V.nextFloat() * 0.4F);
      }

      this.j(this.s, (this.getEntityBoundingBox().b + this.getEntityBoundingBox().e) / 2.0, this.u);
      double var1 = 8.0;
      if (this.xpTargetColor < this.xpColor - 20 + this.F() % 100) {
         if (this.closestPlayer == null || this.closestPlayer.h(this) > var1 * var1) {
            this.closestPlayer = this.o.getClosestPlayerToEntity(this, var1);
         }

         this.xpTargetColor = this.xpColor;
      }

      if (this.closestPlayer != null && this.closestPlayer.isSpectator()) {
         this.closestPlayer = null;
      }

      if (this.closestPlayer != null) {
         double var3 = (this.closestPlayer.s - this.s) / var1;
         double var5 = (this.closestPlayer.t + this.closestPlayer.getEyeHeight() - this.t) / var1;
         double var7 = (this.closestPlayer.u - this.u) / var1;
         double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
         double var11 = 1.0 - var9;
         if (var11 > 0.0) {
            var11 *= var11;
            this.v += var3 / var9 * var11 * 0.1;
            this.w += var5 / var9 * var11 * 0.1;
            this.x += var7 / var9 * var11 * 0.1;
         }
      }

      this.d(this.v, this.w, this.x);
      float var13 = 0.98F;
      if (this.C) {
         var13 = this.o
               .getBlockState(
                  new BlockPos(MathHelper.floor_double(this.s), MathHelper.floor_double(this.getEntityBoundingBox().b) - 1, MathHelper.floor_double(this.u))
               )
               .getBlock()
               .L
            * 0.98F;
      }

      this.v *= var13;
      this.w *= 0.98F;
      this.x *= var13;
      if (this.C) {
         this.w *= -0.9F;
      }

      this.xpColor++;
      this.xpOrbAge++;
      if (this.xpOrbAge >= 6000) {
         this.setDead();
      }
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public boolean handleWaterMovement() {
      return this.o.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this);
   }

   @Override
   public void dealFireDamage(int var1) {
      this.attackEntityFrom(DamageSource.inFire, var1);
   }

   @Override
   public boolean r_() {
      return false;
   }

   @Override
   public void k_() {
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.setBeenAttacked();
         this.xpOrbHealth = (int)(this.xpOrbHealth - var2);
         if (this.xpOrbHealth <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   @Override
   public void b_(EntityPlayer var1) {
      if (!this.o.D && this.delayBeforeCanPickup == 0 && var1.xpCooldown == 0) {
         var1.xpCooldown = 2;
         this.o.a(var1, "random.orb", 0.1F, 0.5F * ((this.V.nextFloat() - this.V.nextFloat()) * 0.7F + 1.8F));
         var1.a(this, 1);
         var1.addExperience(this.xpValue);
         this.setDead();
      }
   }

   public static int getXPSplit(int var0) {
      return var0 >= 2477
         ? 2477
         : (
            var0 >= 1237
               ? 1237
               : (
                  var0 >= 617
                     ? 617
                     : (
                        var0 >= 307
                           ? 307
                           : (var0 >= 149 ? 149 : (var0 >= 73 ? 73 : (var0 >= 37 ? 37 : (var0 >= 17 ? 17 : (var0 >= 7 ? 7 : (var0 >= 3 ? 3 : 1))))))
                     )
               )
         );
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("Health", (byte)this.xpOrbHealth);
      var1.setShort("Age", (short)this.xpOrbAge);
      var1.setShort("Value", (short)this.xpValue);
   }

   public int getXpValue() {
      return this.xpValue;
   }

   @Override
   public int b_(float var1) {
      float var2 = 0.5F;
      var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
      int var3 = super.b_(var1);
      int var4 = var3 & 0xFF;
      int var5 = var3 >> 16 & 0xFF;
      var4 += (int)(var2 * 15.0F * 16.0F);
      if (var4 > 240) {
         var4 = 240;
      }

      return var4 | var5 << 16;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.xpOrbHealth = var1.getShort("Health") & 255;
      this.xpOrbAge = var1.getShort("Age");
      this.xpValue = var1.getShort("Value");
   }

   public EntityXPOrb(World var1, double var2, double var4, double var6, int var8) {
      super(var1);
      this.setSize(0.5F, 0.5F);
      this.b(var2, var4, var6);
      this.y = (float)(Math.random() * 360.0);
      this.v = (float)(Math.random() * 0.2F - 0.1F) * 2.0F;
      this.w = (float)(Math.random() * 0.2) * 2.0F;
      this.x = (float)(Math.random() * 0.2F - 0.1F) * 2.0F;
      this.xpValue = var8;
   }

   public EntityXPOrb(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }
}
