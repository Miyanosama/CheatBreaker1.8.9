package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindEntityNearest extends EntityAIBase {
   public EntityLiving mob;
   public Class<? extends EntityLivingBase> field_179439_f;
   public EntityLivingBase target;
   public static Logger LOGGER = LogManager.getLogger();
   public EntityAINearestAttackableTarget.Sorter field_179440_d;
   public Predicate<EntityLivingBase> field_179443_c;

   public double getFollowRange() {
      IAttributeInstance var1 = this.mob.getEntityAttribute(SharedMonsterAttributes.followRange);
      return var1 == null ? 16.0 : var1.getAttributeValue();
   }

   @Override
   public boolean shouldExecute() {
      double var1 = this.getFollowRange();
      List var3 = this.mob.o.getEntitiesWithinAABB(this.field_179439_f, this.mob.getEntityBoundingBox().expand(var1, 4.0, var1), this.field_179443_c);
      Collections.sort(var3, this.field_179440_d);
      if (var3.isEmpty()) {
         return false;
      } else {
         this.target = (EntityLivingBase)var3.get(0);
         return true;
      }
   }

   @Override
   public void resetTask() {
      this.mob.setAttackTarget((EntityLivingBase)null);
      super.startExecuting();
   }

   @Override
   public void startExecuting() {
      this.mob.setAttackTarget(this.target);
      super.startExecuting();
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase var1 = this.mob.getAttackTarget();
      if (var1 == null) {
         return false;
      } else if (!var1.isEntityAlive()) {
         return false;
      } else {
         double var2 = this.getFollowRange();
         return this.mob.h(var1) > var2 * var2 ? false : !(var1 instanceof EntityPlayerMP) || !((EntityPlayerMP)var1).theItemInWorldManager.isCreative();
      }
   }

   public EntityAIFindEntityNearest(EntityLiving var1, Class<? extends EntityLivingBase> var2) {
      this.mob = var1;
      this.field_179439_f = var2;
      if (var1 instanceof EntityCreature) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.field_179443_c = new Predicate<EntityLivingBase>() {
         public boolean apply(EntityLivingBase var1) {
            double var2x = EntityAIFindEntityNearest.this.getFollowRange();
            if (var1.isSneaking()) {
               var2x *= 0.8F;
            }

            return var1.isInvisible()
               ? false
               : (
                  var1.g(EntityAIFindEntityNearest.this.mob) > var2x
                     ? false
                     : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearest.this.mob, var1, false, true)
               );
         }
      };
      this.field_179440_d = new EntityAINearestAttackableTarget.Sorter(var1);
   }
}
