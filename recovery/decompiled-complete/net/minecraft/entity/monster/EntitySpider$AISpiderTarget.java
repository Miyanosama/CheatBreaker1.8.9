package net.minecraft.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class EntitySpider$AISpiderTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
   @Override
   public boolean shouldExecute() {
      float var1 = this.e.a_(1.0F);
      return var1 >= 0.5F ? false : super.shouldExecute();
   }

   public EntitySpider$AISpiderTarget(EntitySpider var1, Class<T> var2) {
      super(var1, var2, true);
   }
}
