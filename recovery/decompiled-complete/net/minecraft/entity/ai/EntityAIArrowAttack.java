package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass4535;

public class EntityAIArrowAttack extends EntityAIBase {
   public UnidentifiedClass4535 field_0005;
   public double entityMoveSpeed;
   public float maxAttackDistance;
   public EntityLivingBase attackTarget;
   public int maxRangedAttackTime;
   public EntityLiving entityHost;
   public int field_96561_g;
   public IRangedAttackMob rangedAttackEntityHost;
   public float field_96562_i;
   public int rangedAttackTime = -1;
   public int field_75318_f;

   @Override
   public boolean continueExecuting() {
      return this.shouldExecute() || !this.entityHost.s().noPath();
   }

   @Override
   public void updateTask() {
      double var1 = this.entityHost.e(this.attackTarget.s, this.attackTarget.getEntityBoundingBox().b, this.attackTarget.u);
      boolean var3 = this.entityHost.getEntitySenses().canSee(this.attackTarget);
      if (var3) {
         this.field_75318_f++;
      } else {
         this.field_75318_f = 0;
      }

      if (var1 <= this.maxAttackDistance && this.field_75318_f >= 20) {
         this.entityHost.s().clearPathEntity();
      } else {
         this.entityHost.s().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
      }

      this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
      if (--this.rangedAttackTime == 0) {
         if (var1 > this.maxAttackDistance || !var3) {
            return;
         }

         float var4 = MathHelper.sqrt_double(var1) / this.field_96562_i;
         float var5 = MathHelper.clamp_float(var4, 0.1F, 1.0F);
         this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, var5);
         this.rangedAttackTime = MathHelper.floor_float(var4 * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
      } else if (this.rangedAttackTime < 0) {
         float var6 = MathHelper.sqrt_double(var1) / this.field_96562_i;
         this.rangedAttackTime = MathHelper.floor_float(var6 * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
      }
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.entityHost.getAttackTarget();
      if (var1 == null) {
         return false;
      } else {
         this.attackTarget = var1;
         return true;
      }
   }

   public EntityAIArrowAttack(IRangedAttackMob var1, double var2, int var4, float var5) {
      this(var1, var2, var4, var4, var5);
   }

   public EntityAIArrowAttack(IRangedAttackMob var1, double var2, int var4, int var5, float var6) {
      if (!(var1 instanceof EntityLivingBase)) {
         throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
      } else {
         this.rangedAttackEntityHost = var1;
         this.entityHost = (EntityLiving)var1;
         this.entityMoveSpeed = var2;
         this.field_96561_g = var4;
         this.maxRangedAttackTime = var5;
         this.field_96562_i = var6;
         this.maxAttackDistance = var6 * var6;
         this.setMutexBits(3);
      }
   }

   @Override
   public void resetTask() {
      this.attackTarget = null;
      this.field_75318_f = 0;
      this.rangedAttackTime = -1;
   }
}
