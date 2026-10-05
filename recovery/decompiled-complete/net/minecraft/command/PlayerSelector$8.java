package net.minecraft.command;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.scoreboard.Team;

public class PlayerSelector$8 implements Predicate<Entity> {
   public boolean apply(Entity var1) {
      if (!(var1 instanceof EntityLivingBase)) {
         return false;
      } else {
         EntityLivingBase var2 = (EntityLivingBase)var1;
         Team var3 = var2.getTeam();
         String var4 = var3 == null ? "" : var3.getRegisteredName();
         return var4.equals(this.field_179623_a) != this.field_179622_b;
      }
   }

   public PlayerSelector$8(String var1, boolean var2) {
      this.field_179623_a = var1;
      this.field_179622_b = var2;
      super();
   }
}
