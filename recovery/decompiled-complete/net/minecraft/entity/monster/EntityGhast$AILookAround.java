package net.minecraft.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$9;

public class EntityGhast$AILookAround extends EntityAIBase {
   public EntityGhast parentEntity;
   public LogBrokerMonitor$9 field_0001;

   @Override
   public boolean shouldExecute() {
      return true;
   }

   public EntityGhast$AILookAround(EntityGhast var1) {
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
