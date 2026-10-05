package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob {
   public int explosionStrength = 1;

   @Override
   public boolean getCanSpawnHere() {
      return this.V.nextInt(20) == 0 && super.getCanSpawnHere() && this.o.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   public int getFireballStrength() {
      return this.explosionStrength;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("ExplosionPower", this.explosionStrength);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(2) + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItem(Items.ghast_tear, 1);
      }

      var3 = this.V.nextInt(3) + this.V.nextInt(1 + var2);

      for (int var6 = 0; var6 < var3; var6++) {
         this.dropItem(Items.gunpowder, 1);
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
   }

   @Override
   public float getEyeHeight() {
      return 2.6F;
   }

   @Override
   public String getLivingSound() {
      return "mob.ghast.moan";
   }

   public EntityGhast(World var1) {
      super(var1);
      this.setSize(4.0F, 4.0F);
      this.ab = true;
      this.experienceValue = 5;
      this.f = new EntityGhast.GhastMoveHelper(this);
      this.i.addTask(5, new EntityGhast.AIRandomFly(this));
      this.i.addTask(7, new EntityGhast.AILookAround(this));
      this.i.addTask(7, new EntityGhast.AIFireballAttack(this));
      this.bi.addTask(1, new EntityAIFindEntityNearestPlayer(this));
   }

   public void setAttacking(boolean var1) {
      this.ac.updateObject(16, (byte)(var1 ? 1 : 0));
   }

   @Override
   public Item getDropItem() {
      return Items.gunpowder;
   }

   @Override
   public String getHurtSound() {
      return "mob.ghast.scream";
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if ("fireball".equals(var1.getDamageType()) && var1.getEntity() instanceof EntityPlayer) {
         super.attackEntityFrom(var1, 1000.0F);
         ((EntityPlayer)var1.getEntity()).triggerAchievement(AchievementList.ghast);
         return true;
      } else {
         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public float getSoundVolume() {
      return 10.0F;
   }

   @Override
   public String getDeathSound() {
      return "mob.ghast.death";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0);
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 1;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D && this.o.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("ExplosionPower", 99)) {
         this.explosionStrength = var1.getInteger("ExplosionPower");
      }
   }

   public boolean isAttacking() {
      return this.ac.getWatchableObjectByte(16) != 0;
   }

   public static class AIFireballAttack extends EntityAIBase {
      public int attackTimer;
      public EntityGhast parentEntity;

      @Override
      public void updateTask() {
         EntityLivingBase var1 = this.parentEntity.getAttackTarget();
         double var2 = 64.0;
         if (var1.h(this.parentEntity) < var2 * var2 && this.parentEntity.t(var1)) {
            World var4 = this.parentEntity.o;
            this.attackTimer++;
            if (this.attackTimer == 10) {
               var4.playAuxSFXAtEntity((EntityPlayer)null, 1007, new BlockPos(this.parentEntity), 0);
            }

            if (this.attackTimer == 20) {
               double var5 = 4.0;
               Vec3 var7 = this.parentEntity.getLook(1.0F);
               double var8 = var1.s - (this.parentEntity.s + var7.xCoord * var5);
               double var10 = var1.getEntityBoundingBox().b + var1.K / 2.0F - (0.5 + this.parentEntity.t + this.parentEntity.K / 2.0F);
               double var12 = var1.u - (this.parentEntity.u + var7.zCoord * var5);
               var4.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.parentEntity), 0);
               EntityLargeFireball var14 = new EntityLargeFireball(var4, this.parentEntity, var8, var10, var12);
               var14.explosionPower = this.parentEntity.getFireballStrength();
               var14.s = this.parentEntity.s + var7.xCoord * var5;
               var14.t = this.parentEntity.t + this.parentEntity.K / 2.0F + 0.5;
               var14.u = this.parentEntity.u + var7.zCoord * var5;
               var4.spawnEntityInWorld(var14);
               this.attackTimer = -40;
            }
         } else if (this.attackTimer > 0) {
            this.attackTimer--;
         }

         this.parentEntity.setAttacking(this.attackTimer > 10);
      }

      public AIFireballAttack(EntityGhast var1) {
         this.parentEntity = var1;
      }

      @Override
      public void startExecuting() {
         this.attackTimer = 0;
      }

      @Override
      public boolean shouldExecute() {
         return this.parentEntity.getAttackTarget() != null;
      }

      @Override
      public void resetTask() {
         this.parentEntity.setAttacking(false);
      }
   }

   public static class AILookAround extends EntityAIBase {
      public EntityGhast parentEntity;

      @Override
      public boolean shouldExecute() {
         return true;
      }

      public AILookAround(EntityGhast var1) {
         this.parentEntity = var1;
         this.setMutexBits(2);
      }

      @Override
      public void updateTask() {
         if (this.parentEntity.getAttackTarget() == null) {
            this.parentEntity.aI = this.parentEntity.y = -((float)MathHelper.atan2(this.parentEntity.v, this.parentEntity.x)) * 180.0F / (float) Math.PI;
         } else {
            EntityLivingBase var1 = this.parentEntity.getAttackTarget();
            double var2 = 64.0;
            if (var1.h(this.parentEntity) < var2 * var2) {
               double var4 = var1.s - this.parentEntity.s;
               double var6 = var1.u - this.parentEntity.u;
               this.parentEntity.aI = this.parentEntity.y = -((float)MathHelper.atan2(var4, var6)) * 180.0F / (float) Math.PI;
            }
         }
      }
   }

   public static class AIRandomFly extends EntityAIBase {
      public EntityGhast parentEntity;

      public AIRandomFly(EntityGhast var1) {
         this.parentEntity = var1;
         this.setMutexBits(1);
      }

      @Override
      public void startExecuting() {
         Random var1 = this.parentEntity.getRNG();
         double var2 = this.parentEntity.s + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
         double var4 = this.parentEntity.t + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
         double var6 = this.parentEntity.u + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
         this.parentEntity.q().setMoveTo(var2, var4, var6, 1.0);
      }

      @Override
      public boolean shouldExecute() {
         EntityMoveHelper var1 = this.parentEntity.q();
         if (!var1.isUpdating()) {
            return true;
         } else {
            double var2 = var1.getX() - this.parentEntity.s;
            double var4 = var1.getY() - this.parentEntity.t;
            double var6 = var1.getZ() - this.parentEntity.u;
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;
            return var8 < 1.0 || var8 > 3600.0;
         }
      }

      @Override
      public boolean continueExecuting() {
         return false;
      }
   }

   public static class GhastMoveHelper extends EntityMoveHelper {
      public int courseChangeCooldown;
      public EntityGhast parentEntity;

      @Override
      public void onUpdateMoveHelper() {
         if (this.f) {
            double var1 = this.posX - this.parentEntity.s;
            double var3 = this.posY - this.parentEntity.t;
            double var5 = this.posZ - this.parentEntity.u;
            double var7 = var1 * var1 + var3 * var3 + var5 * var5;
            if (this.courseChangeCooldown-- <= 0) {
               this.courseChangeCooldown = this.courseChangeCooldown + this.parentEntity.getRNG().nextInt(5) + 2;
               var7 = MathHelper.sqrt_double(var7);
               if (this.isNotColliding(this.posX, this.posY, this.posZ, var7)) {
                  this.parentEntity.v += var1 / var7 * 0.1;
                  this.parentEntity.w += var3 / var7 * 0.1;
                  this.parentEntity.x += var5 / var7 * 0.1;
               } else {
                  this.f = false;
               }
            }
         }
      }

      public boolean isNotColliding(double var1, double var3, double var5, double var7) {
         double var9 = (var1 - this.parentEntity.s) / var7;
         double var11 = (var3 - this.parentEntity.t) / var7;
         double var13 = (var5 - this.parentEntity.u) / var7;
         AxisAlignedBB var15 = this.parentEntity.getEntityBoundingBox();

         for (int var16 = 1; var16 < var7; var16++) {
            var15 = var15.offset(var9, var11, var13);
            if (!this.parentEntity.o.a(this.parentEntity, var15).isEmpty()) {
               return false;
            }
         }

         return true;
      }

      public GhastMoveHelper(EntityGhast var1) {
         super(var1);
         this.parentEntity = var1;
      }
   }
}
