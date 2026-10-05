package net.minecraft.entity.ai;

import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAIFollowParent extends EntityAIBase {
   public double moveSpeed;
   public int delayCounter;
   public EntityAnimal parentAnimal;
   public EntityAnimal childAnimal;

   @Override
   public boolean shouldExecute() {
      if (this.childAnimal.l() >= 0) {
         return false;
      } else {
         List var1 = this.childAnimal.o.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal.getEntityBoundingBox().expand(8.0, 4.0, 8.0));
         EntityAnimal var2 = null;
         double var3 = Double.MAX_VALUE;

         for (EntityAnimal var6 : (Iterable<EntityAnimal>)(Iterable<?>)(var1)) {
            if (var6.l() >= 0) {
               double var7 = this.childAnimal.h(var6);
               if (var7 <= var3) {
                  var3 = var7;
                  var2 = var6;
               }
            }
         }

         if (var2 == null) {
            return false;
         } else if (var3 < 9.0) {
            return false;
         } else {
            this.parentAnimal = var2;
            return true;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.delayCounter = 0;
   }

   @Override
   public void resetTask() {
      this.parentAnimal = null;
   }

   @Override
   public void updateTask() {
      if (--this.delayCounter <= 0) {
         this.delayCounter = 10;
         this.childAnimal.s().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
      }
   }

   public EntityAIFollowParent(EntityAnimal var1, double var2) {
      this.childAnimal = var1;
      this.moveSpeed = var2;
   }

   @Override
   public boolean continueExecuting() {
      if (this.childAnimal.l() >= 0) {
         return false;
      } else if (!this.parentAnimal.isEntityAlive()) {
         return false;
      } else {
         double var1 = this.childAnimal.h(this.parentAnimal);
         return var1 >= 9.0 && var1 <= 256.0;
      }
   }
}
