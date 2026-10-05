package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityJumpHelper {
   public boolean a;
   public EntityLiving entity;

   public EntityJumpHelper(EntityLiving var1) {
      this.entity = var1;
   }

   public void doJump() {
      this.entity.i(this.a);
      this.a = false;
   }

   public void setJumping() {
      this.a = true;
   }
}
