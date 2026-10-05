package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindEntityNearestPlayer extends EntityAIBase {
   public EntityLivingBase entityTarget;
   public Predicate<Entity> predicate;
   public static Logger LOGGER = LogManager.getLogger();
   public EntityLiving entityLiving;
   public EntityAINearestAttackableTarget.Sorter sorter;

   public EntityAIFindEntityNearestPlayer(EntityLiving var1) {
      this.entityLiving = var1;
      if (var1 instanceof EntityCreature) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new Predicate<Entity>() {
         public boolean apply(Entity var1) {
            if (!(var1 instanceof EntityPlayer)) {
               return false;
            } else if (((EntityPlayer)var1).bA.disableDamage) {
               return false;
            } else {
               double var2 = EntityAIFindEntityNearestPlayer.this.maxTargetRange();
               if (var1.isSneaking()) {
                  var2 *= 0.8F;
               }

               if (var1.isInvisible()) {
                  float var4 = ((EntityPlayer)var1).getArmorVisibility();
                  if (var4 < 0.1F) {
                     var4 = 0.1F;
                  }

                  var2 *= 0.7F * var4;
               }

               return var1.g(EntityAIFindEntityNearestPlayer.this.entityLiving) > var2
                  ? false
                  : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearestPlayer.this.entityLiving, (EntityLivingBase)var1, false, true);
            }
         }
      };
      this.sorter = new EntityAINearestAttackableTarget.Sorter(var1);
   }

   @Override
   public void resetTask() {
      this.entityLiving.setAttackTarget((EntityLivingBase)null);
      super.startExecuting();
   }

   public double maxTargetRange() {
      IAttributeInstance var1 = this.entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange);
      return var1 == null ? 16.0 : var1.getAttributeValue();
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase var1 = this.entityLiving.getAttackTarget();
      if (var1 == null) {
         return false;
      } else if (!var1.isEntityAlive()) {
         return false;
      } else if (var1 instanceof EntityPlayer && ((EntityPlayer)var1).bA.disableDamage) {
         return false;
      } else {
         Team var2 = this.entityLiving.getTeam();
         Team var3 = var1.getTeam();
         if (var2 != null && var3 == var2) {
            return false;
         } else {
            double var4 = this.maxTargetRange();
            return this.entityLiving.h(var1) > var4 * var4
               ? false
               : !(var1 instanceof EntityPlayerMP) || !((EntityPlayerMP)var1).theItemInWorldManager.isCreative();
         }
      }
   }

   @Override
   public boolean shouldExecute() {
      double var1 = this.maxTargetRange();
      List var3 = this.entityLiving
         .o
         .getEntitiesWithinAABB(EntityPlayer.class, this.entityLiving.getEntityBoundingBox().expand(var1, 4.0, var1), this.predicate);
      Collections.sort(var3, this.sorter);
      if (var3.isEmpty()) {
         return false;
      } else {
         this.entityTarget = (EntityLivingBase)var3.get(0);
         return true;
      }
   }

   @Override
   public void startExecuting() {
      this.entityLiving.setAttackTarget(this.entityTarget);
      super.startExecuting();
   }
}
