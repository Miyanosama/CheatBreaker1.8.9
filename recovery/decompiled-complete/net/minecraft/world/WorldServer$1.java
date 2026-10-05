package net.minecraft.world;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;

public class WorldServer$1 implements Predicate<EntityLivingBase> {
   public WorldServer$1(WorldServer var1) {
      this.field_180243_a = var1;
      super();
   }

   public boolean apply(EntityLivingBase var1) {
      return var1 != null && var1.isEntityAlive() && this.field_180243_a.canSeeSky(var1.getPosition());
   }
}
