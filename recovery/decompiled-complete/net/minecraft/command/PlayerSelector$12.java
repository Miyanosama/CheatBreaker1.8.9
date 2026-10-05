package net.minecraft.command;

import com.google.common.base.Predicate;
import io.netty.util.internal.ThreadLocalRandom$2;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;

public class PlayerSelector$12 implements Predicate<Entity> {
   public EntityFishHook field_0004;
   public ModelEnderman field_0003;
   public ThreadLocalRandom$2 field_0000;

   public boolean apply(Entity var1) {
      int var2 = PlayerSelector.func_179650_a((int)Math.floor(var1.y));
      return this.field_179593_a > this.field_179592_b
         ? var2 >= this.field_179593_a || var2 <= this.field_179592_b
         : var2 >= this.field_179593_a && var2 <= this.field_179592_b;
   }

   public PlayerSelector$12(int var1, int var2) {
      this.field_179593_a = var1;
      this.field_179592_b = var2;
      super();
   }
}
