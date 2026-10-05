package net.minecraft.command;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class PlayerSelector$3 implements Predicate<Entity> {
   public AxisAlignedBB recoveredField275;

   public boolean apply(Entity var1) {
      return var1.s >= this.recoveredField275.a && var1.t >= this.recoveredField275.b && var1.u >= this.recoveredField275.c
         ? var1.s < this.recoveredField275.d && var1.t < this.recoveredField275.e && var1.u < this.recoveredField275.f
         : false;
   }

   public PlayerSelector$3(AxisAlignedBB var1) {
      this.recoveredField275 = var1;
   }
}
