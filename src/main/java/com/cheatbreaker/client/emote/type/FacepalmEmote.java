package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class FacepalmEmote extends Emote {
   public float recoveredField617;
   public ExponentialFade recoveredField618;
   public ExponentialFade recoveredField619 = new ExponentialFade(150L);
   public CosineFade recoveredField620;
   public float recoveredField621;
   public float recoveredField622;

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = this.recoveredField619.method_21227();
      if (!this.recoveredField619.method_21217() && this.recoveredField897.method_21207() >= 150L) {
         this.recoveredField619.method_20200();
      }

      if (this.recoveredField619.method_21217()) {
         if (this.recoveredField619.method_21210() && !this.recoveredField620.method_21233() && !this.recoveredField618.method_21217()) {
            this.recoveredField620.method_20200();
         }

         float var5 = var2.e.rotateAngleX;
         float var6 = var2.e.rotateAngleY;
         var2.e.rotateAngleZ = -((float)Math.toRadians(10.0F * this.recoveredField620.method_21227()));
         var2.f.rotateAngleZ = -((float)Math.toRadians(10.0F * this.recoveredField620.method_21227()));
         var2.e.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.recoveredField620.method_21227());
         var2.f.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.recoveredField620.method_21227());
         var2.e.rotateAngleX = this.recoveredField617 * var4;
         var2.f.rotateAngleX = this.recoveredField617 * var4;
         var2.h.rotateAngleY = this.recoveredField622 * var4
            - (this.recoveredField618.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.recoveredField620.method_21227()));
         var2.h.rotateAngleX = this.recoveredField621 * var4;
         var2.bipedRightArmwear.rotateAngleY = this.recoveredField622 * var4
            - (this.recoveredField618.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.recoveredField620.method_21227()));
         var2.bipedRightArmwear.rotateAngleX = this.recoveredField621 * var4;
         if (!this.recoveredField618.method_21217() && this.recoveredField897.method_21229() <= this.recoveredField618.method_21240()) {
            this.recoveredField618.method_20200();
         }

         if (this.recoveredField618.method_21217()) {
            var4 = this.recoveredField618.method_21227();
            var2.e.rotateAngleY = var6 * var4;
            var2.f.rotateAngleY = var6 * var4;
            var2.e.rotateAngleZ = 0.0F;
            var2.f.rotateAngleZ = 0.0F;
            var2.e.rotateAngleX = var2.e.rotateAngleX - (this.recoveredField617 - var5) * var4;
            var2.f.rotateAngleX = var2.f.rotateAngleX - (this.recoveredField617 - var5) * var4;
            var2.h.rotateAngleY = var2.h.rotateAngleY - this.recoveredField622 * var4;
            var2.h.rotateAngleX = var2.h.rotateAngleX - this.recoveredField621 * var4;
            var2.bipedRightArmwear.rotateAngleY = var2.bipedRightArmwear.rotateAngleY - this.recoveredField622 * var4;
            var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX - this.recoveredField621 * var4;
         }
      }
   }

   public FacepalmEmote() {
      super("Facepalm", new FloatFade(2000L));
      this.recoveredField618 = new ExponentialFade(200L);
      this.recoveredField620 = new CosineFade(300L);
      this.recoveredField617 = (float)Math.toRadians(45.0);
      this.recoveredField622 = (float)Math.toRadians(-30.0);
      this.recoveredField621 = (float)Math.toRadians(-100.0);
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
