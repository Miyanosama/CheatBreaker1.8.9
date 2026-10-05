package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import net.optifine.entity.model.ModelAdapterZombie;

public class EntityAIAvoidEntity$1 implements Predicate<Entity> {
   public GLAllocation field_0001;
   public ModelAdapterZombie field_0002;

   public EntityAIAvoidEntity$1(EntityAIAvoidEntity var1) {
      this.field_98219_c = var1;
      super();
   }

   public boolean apply(Entity var1) {
      return var1.isEntityAlive() && this.field_98219_c.theEntity.getEntitySenses().canSee(var1);
   }
}
