package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITarget extends EntityAIBase {
   public int targetSearchStatus;
   public int targetUnseenTicks;
   public boolean shouldCheckSight;
   public int targetSearchDelay;
   public boolean nearbyOnly;
   public EntityCreature e;

   @Override
   public void resetTask() {
      this.e.setAttackTarget((EntityLivingBase)null);
   }

   public boolean a(EntityLivingBase var1, boolean var2) {
      if (!isSuitableTarget(this.e, var1, var2, this.shouldCheckSight)) {
         return false;
      } else if (!this.e.isWithinHomeDistanceFromPosition(new BlockPos(var1))) {
         return false;
      } else {
         if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
               this.targetSearchStatus = 0;
            }

            if (this.targetSearchStatus == 0) {
               this.targetSearchStatus = this.canEasilyReach(var1) ? 1 : 2;
            }

            if (this.targetSearchStatus == 2) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean canEasilyReach(EntityLivingBase var1) {
      this.targetSearchDelay = 10 + this.e.getRNG().nextInt(5);
      PathEntity var2 = this.e.s().getPathToEntityLiving(var1);
      if (var2 == null) {
         return false;
      } else {
         PathPoint var3 = var2.getFinalPathPoint();
         if (var3 == null) {
            return false;
         } else {
            int var4 = var3.xCoord - MathHelper.floor_double(var1.s);
            int var5 = var3.zCoord - MathHelper.floor_double(var1.u);
            return var4 * var4 + var5 * var5 <= 2.25;
         }
      }
   }

   public double f() {
      IAttributeInstance var1 = this.e.getEntityAttribute(SharedMonsterAttributes.followRange);
      return var1 == null ? 16.0 : var1.getAttributeValue();
   }

   public static boolean isSuitableTarget(EntityLiving var0, EntityLivingBase var1, boolean var2, boolean var3) {
      if (var1 == null) {
         return false;
      } else if (var1 == var0) {
         return false;
      } else if (!var1.isEntityAlive()) {
         return false;
      } else if (!var0.canAttackClass((Class<? extends EntityLivingBase>)var1.getClass())) {
         return false;
      } else {
         Team var4 = var0.getTeam();
         Team var5 = var1.getTeam();
         if (var4 != null && var5 == var4) {
            return false;
         } else {
            if (var0 instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)var0).getOwnerId())) {
               if (var1 instanceof IEntityOwnable && ((IEntityOwnable)var0).getOwnerId().equals(((IEntityOwnable)var1).getOwnerId())) {
                  return false;
               }

               if (var1 == ((IEntityOwnable)var0).getOwner()) {
                  return false;
               }
            } else if (var1 instanceof EntityPlayer && !var2 && ((EntityPlayer)var1).bA.disableDamage) {
               return false;
            }

            return !var3 || var0.getEntitySenses().canSee(var1);
         }
      }
   }

   @Override
   public void startExecuting() {
      this.targetSearchStatus = 0;
      this.targetSearchDelay = 0;
      this.targetUnseenTicks = 0;
   }

   public EntityAITarget(EntityCreature var1, boolean var2, boolean var3) {
      this.e = var1;
      this.shouldCheckSight = var2;
      this.nearbyOnly = var3;
   }

   public EntityAITarget(EntityCreature var1, boolean var2) {
      this(var1, var2, false);
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase var1 = this.e.getAttackTarget();
      if (var1 == null) {
         return false;
      } else if (!var1.isEntityAlive()) {
         return false;
      } else {
         Team var2 = this.e.getTeam();
         Team var3 = var1.getTeam();
         if (var2 != null && var3 == var2) {
            return false;
         } else {
            double var4 = this.f();
            if (this.e.h(var1) > var4 * var4) {
               return false;
            } else {
               if (this.shouldCheckSight) {
                  if (this.e.getEntitySenses().canSee(var1)) {
                     this.targetUnseenTicks = 0;
                  } else if (++this.targetUnseenTicks > 60) {
                     return false;
                  }
               }

               return !(var1 instanceof EntityPlayer) || !((EntityPlayer)var1).bA.disableDamage;
            }
         }
      }
   }
}
