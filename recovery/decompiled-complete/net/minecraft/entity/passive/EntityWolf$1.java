package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.command.PlayerSelector$10;
import net.minecraft.entity.Entity;
import recovered.unidentified.UnidentifiedClass4584;

public class EntityWolf$1 implements Predicate<Entity> {
   public PlayerSelector$10 field_0001;
   public UnidentifiedClass4584 field_0000;

   public boolean apply(Entity var1) {
      return var1 instanceof EntitySheep || var1 instanceof EntityRabbit;
   }

   public EntityWolf$1(EntityWolf var1) {
      this.field_180095_a = var1;
      super();
   }
}
