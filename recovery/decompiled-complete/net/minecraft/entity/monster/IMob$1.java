package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.client.particle.EntityBreakingFX$SnowballFactory;
import net.minecraft.entity.Entity;

public class IMob$1 implements Predicate<Entity> {
   public EntityBreakingFX$SnowballFactory field_0000;

   public boolean apply(Entity var1) {
      return var1 instanceof IMob;
   }
}
