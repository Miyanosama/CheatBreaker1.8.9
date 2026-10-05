package net.minecraft.entity;

import com.google.common.base.Predicate;

public class EntityLivingBase$1 implements Predicate<Entity> {
   public EntityLivingBase$1(EntityLivingBase var1) {
      this.field_181170_a = var1;
      super();
   }

   public boolean apply(Entity var1) {
      return var1.m_();
   }
}
