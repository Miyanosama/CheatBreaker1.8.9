package net.minecraft.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityWitherSkull extends EntityFireball {
   public boolean isInvulnerable() {
      return this.ac.getWatchableObjectByte(10) == 1;
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   public void setInvulnerable(boolean var1) {
      this.ac.updateObject(10, (byte)(var1 ? 1 : 0));
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   public EntityWitherSkull(World var1) {
      super(var1);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityWitherSkull(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public float getMotionFactor() {
      return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
   }

   public EntityWitherSkull(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   @Override
   public float getExplosionResistance(Explosion var1, World var2, BlockPos var3, IBlockState var4) {
      float var5 = super.getExplosionResistance(var1, var2, var3, var4);
      Block var6 = var4.getBlock();
      if (this.isInvulnerable() && EntityWither.canDestroyBlock(var6)) {
         var5 = Math.min(0.8F, var5);
      }

      return var5;
   }

   @Override
   public void k_() {
      this.ac.addObject(10, (byte)0);
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      if (!this.o.D) {
         if (var1.entityHit != null) {
            if (this.a != null) {
               if (var1.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.a), 8.0F)) {
                  if (!var1.entityHit.isEntityAlive()) {
                     this.a.heal(5.0F);
                  } else {
                     this.applyEnchantments(this.a, var1.entityHit);
                  }
               }
            } else {
               var1.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
            }

            if (var1.entityHit instanceof EntityLivingBase) {
               byte var2 = 0;
               if (this.o.getDifficulty() == EnumDifficulty.NORMAL) {
                  var2 = 10;
               } else if (this.o.getDifficulty() == EnumDifficulty.HARD) {
                  var2 = 40;
               }

               if (var2 > 0) {
                  ((EntityLivingBase)var1.entityHit).c(new PotionEffect(Potion.wither.id, 20 * var2, 1));
               }
            }
         }

         this.o.newExplosion(this, this.s, this.t, this.u, 1.0F, false, this.o.Q().getBoolean("mobGriefing"));
         this.setDead();
      }
   }
}
