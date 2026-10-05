package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.Entity;

public class EntitySelectors$1 implements Predicate<Entity> {
   public Vec3 field_0000;
   public BlockJukebox field_0001;

   public boolean apply(Entity var1) {
      return var1.isEntityAlive();
   }
}
