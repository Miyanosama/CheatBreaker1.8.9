package net.minecraft.util;

import com.google.common.base.Predicate;
import io.netty.buffer.PoolSubpage;
import net.minecraft.entity.Entity;

public class EntitySelectors$2 implements Predicate<Entity> {
   public PoolSubpage field_0000;

   public boolean apply(Entity var1) {
      return var1.isEntityAlive() && var1.l == null && var1.m == null;
   }
}
