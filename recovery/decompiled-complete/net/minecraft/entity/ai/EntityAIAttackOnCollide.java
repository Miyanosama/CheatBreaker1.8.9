package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.world.World;

public class EntityAIAttackOnCollide extends EntityAIBase {
   public double targetZ;
   public boolean longMemory;
   public World worldObj;
   public BlockPos$MutableBlockPos field_0008;
   public EntityCreature attacker;
   public double targetX;
   public Class<? extends Entity> classTarget;
   public double targetY;
   public double speedTowardsTarget;
   public PathEntity entityPathEntity;
   public int delayCounter;
   public int attackTick;

   @Override
   public boolean continueExecuting() {
      EntityLivingBase var1 = this.attacker.getAttackTarget();
      return var1 == null
         ? false
         : (
            !var1.isEntityAlive()
               ? false
               : (!this.longMemory ? !this.attacker.s().noPath() : this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(var1)))
         );
   }

   public double func_179512_a(EntityLivingBase var1) {
      return this.attacker.J * 2.0F * this.attacker.J * 2.0F + var1.J;
   }

   @Override
   public void startExecuting() {
      this.attacker.s().setPath(this.entityPathEntity, this.speedTowardsTarget);
      this.delayCounter = 0;
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.attacker.getAttackTarget();
      if (var1 == null) {
         return false;
      } else if (!var1.isEntityAlive()) {
         return false;
      } else if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass())) {
         return false;
      } else {
         this.entityPathEntity = this.attacker.s().getPathToEntityLiving(var1);
         return this.entityPathEntity != null;
      }
   }

   public EntityAIAttackOnCollide(EntityCreature var1, Class<? extends Entity> var2, double var3, boolean var5) {
      this(var1, var3, var5);
      this.classTarget = var2;
   }

   @Override
   public void resetTask() {
      this.attacker.s().clearPathEntity();
   }

   @Override
   public void updateTask() {
      EntityLivingBase var1 = this.attacker.getAttackTarget();
      this.attacker.getLookHelper().setLookPositionWithEntity(var1, 30.0F, 30.0F);
      double var2 = this.attacker.e(var1.s, var1.getEntityBoundingBox().b, var1.u);
      double var4 = this.func_179512_a(var1);
      this.delayCounter--;
      if ((this.longMemory || this.attacker.getEntitySenses().canSee(var1))
         && this.delayCounter <= 0
         && (
            this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0
               || var1.e(this.targetX, this.targetY, this.targetZ) >= 1.0
               || this.attacker.getRNG().nextFloat() < 0.05F
         )) {
         this.targetX = var1.s;
         this.targetY = var1.getEntityBoundingBox().b;
         this.targetZ = var1.u;
         this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
         if (var2 > 1024.0) {
            this.delayCounter += 10;
         } else if (var2 > 256.0) {
            this.delayCounter += 5;
         }

         if (!this.attacker.s().tryMoveToEntityLiving(var1, this.speedTowardsTarget)) {
            this.delayCounter += 15;
         }
      }

      this.attackTick = Math.max(this.attackTick - 1, 0);
      if (var2 <= var4 && this.attackTick <= 0) {
         this.attackTick = 20;
         if (this.attacker.getHeldItem() != null) {
            this.attacker.swingItem();
         }

         this.attacker.attackEntityAsMob(var1);
      }
   }

   public EntityAIAttackOnCollide(EntityCreature var1, double var2, boolean var4) {
      this.attacker = var1;
      this.worldObj = var1.o;
      this.speedTowardsTarget = var2;
      this.longMemory = var4;
      this.setMutexBits(3);
   }
}
