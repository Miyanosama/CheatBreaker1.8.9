package net.minecraft.entity.monster;

import com.cheatbreaker.client.util.ClientCrashReporter;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;
import net.optifine.shaders.CustomTextureRaw$1;

public class EntityGuardian$GuardianMoveHelper extends EntityMoveHelper {
   public EntityAITarget field_0000;
   public EntityGuardian entityGuardian;
   public ClientCrashReporter field_0003;
   public CustomTextureRaw$1 field_0001;
   public EntityGuardian$1 field_0004;

   public EntityGuardian$GuardianMoveHelper(EntityGuardian var1) {
      super(var1);
      this.entityGuardian = var1;
   }

   @Override
   public void onUpdateMoveHelper() {
      if (this.f && !this.entityGuardian.s().noPath()) {
         double var1 = this.posX - this.entityGuardian.s;
         double var3 = this.posY - this.entityGuardian.t;
         double var5 = this.posZ - this.entityGuardian.u;
         double var7 = var1 * var1 + var3 * var3 + var5 * var5;
         var7 = MathHelper.sqrt_double(var7);
         var3 /= var7;
         float var9 = (float)(MathHelper.atan2(var5, var1) * 180.0 / Math.PI) - 90.0F;
         this.entityGuardian.y = this.limitAngle(this.entityGuardian.y, var9, 30.0F);
         this.entityGuardian.aI = this.entityGuardian.y;
         float var10 = (float)(this.e * this.entityGuardian.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
         this.entityGuardian.setAIMoveSpeed(this.entityGuardian.bI() + (var10 - this.entityGuardian.bI()) * 0.125F);
         double var11 = Math.sin((this.entityGuardian.W + this.entityGuardian.F()) * 0.5) * 0.05;
         double var13 = Math.cos(this.entityGuardian.y * (float) Math.PI / 180.0F);
         double var15 = Math.sin(this.entityGuardian.y * (float) Math.PI / 180.0F);
         this.entityGuardian.v += var11 * var13;
         this.entityGuardian.x += var11 * var15;
         var11 = Math.sin((this.entityGuardian.W + this.entityGuardian.F()) * 0.75) * 0.05;
         this.entityGuardian.w += var11 * (var15 + var13) * 0.25;
         this.entityGuardian.w = this.entityGuardian.w + this.entityGuardian.bI() * var3 * 0.1;
         EntityLookHelper var17 = this.entityGuardian.getLookHelper();
         double var18 = this.entityGuardian.s + var1 / var7 * 2.0;
         double var20 = this.entityGuardian.getEyeHeight() + this.entityGuardian.t + var3 / var7 * 1.0;
         double var22 = this.entityGuardian.u + var5 / var7 * 2.0;
         double var24 = var17.getLookPosX();
         double var26 = var17.getLookPosY();
         double var28 = var17.getLookPosZ();
         if (!var17.getIsLooking()) {
            var24 = var18;
            var26 = var20;
            var28 = var22;
         }

         this.entityGuardian
            .getLookHelper()
            .setLookPosition(var24 + (var18 - var24) * 0.125, var26 + (var20 - var26) * 0.125, var28 + (var22 - var28) * 0.125, 10.0F, 40.0F);
         EntityGuardian.access$200(this.entityGuardian, true);
      } else {
         this.entityGuardian.setAIMoveSpeed(0.0F);
         EntityGuardian.access$200(this.entityGuardian, false);
      }
   }
}
