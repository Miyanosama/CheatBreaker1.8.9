package net.minecraft.entity.monster;

import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.optifine.shaders.config.ShaderOptionResolver;

public class EntityGhast$GhastMoveHelper extends EntityMoveHelper {
   public int courseChangeCooldown;
   public EntityGhast parentEntity;
   public ShaderOptionResolver field_0002;

   @Override
   public void onUpdateMoveHelper() {
      if (this.f) {
         double var1 = this.posX - this.parentEntity.s;
         double var3 = this.posY - this.parentEntity.t;
         double var5 = this.posZ - this.parentEntity.u;
         double var7 = var1 * var1 + var3 * var3 + var5 * var5;
         if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown = this.courseChangeCooldown + this.parentEntity.getRNG().nextInt(5) + 2;
            var7 = MathHelper.sqrt_double(var7);
            if (this.isNotColliding(this.posX, this.posY, this.posZ, var7)) {
               this.parentEntity.v += var1 / var7 * 0.1;
               this.parentEntity.w += var3 / var7 * 0.1;
               this.parentEntity.x += var5 / var7 * 0.1;
            } else {
               this.f = false;
            }
         }
      }
   }

   public boolean isNotColliding(double var1, double var3, double var5, double var7) {
      double var9 = (var1 - this.parentEntity.s) / var7;
      double var11 = (var3 - this.parentEntity.t) / var7;
      double var13 = (var5 - this.parentEntity.u) / var7;
      AxisAlignedBB var15 = this.parentEntity.getEntityBoundingBox();

      for (int var16 = 1; var16 < var7; var16++) {
         var15 = var15.offset(var9, var11, var13);
         if (!this.parentEntity.o.a(this.parentEntity, var15).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public EntityGhast$GhastMoveHelper(EntityGhast var1) {
      super(var1);
      this.parentEntity = var1;
   }
}
