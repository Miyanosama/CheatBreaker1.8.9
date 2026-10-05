package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class SuperFacepalmEmote extends Emote {
   public float recoveredField2207;
   public CosineFade recoveredField2208;
   public float recoveredField2209;
   public float recoveredField2210;
   public ExponentialFade recoveredField2211 = new ExponentialFade(1000L);
   public ExponentialFade recoveredField2212 = new ExponentialFade(2000L);

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   public SuperFacepalmEmote() {
      super("Super Facepalm", new FloatFade(20000L));
      this.recoveredField2208 = new CosineFade(100L);
      this.recoveredField2210 = (float)Math.toRadians(45.0);
      this.recoveredField2209 = (float)Math.toRadians(-30.0);
      this.recoveredField2207 = (float)Math.toRadians(-100.0);
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = this.recoveredField2211.method_21227();
      if (!this.recoveredField2211.method_21217() && this.recoveredField897.method_21207() >= 150L) {
         this.recoveredField2211.method_20200();
      }

      if (this.recoveredField2211.method_21217()) {
         if (this.recoveredField2211.method_21210() && !this.recoveredField2208.method_21233() && !this.recoveredField2212.method_21217()) {
            this.recoveredField2208.method_20200();
         }

         float var5 = var2.e.rotateAngleX;
         float var6 = var2.e.rotateAngleY;
         var2.e.rotateAngleZ = -((float)Math.toRadians(10.0F * this.recoveredField2208.method_21227()));
         var2.f.rotateAngleZ = -((float)Math.toRadians(10.0F * this.recoveredField2208.method_21227()));
         var2.e.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.recoveredField2208.method_21227());
         var2.f.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.recoveredField2208.method_21227());
         var2.e.rotateAngleX = this.recoveredField2210 * var4;
         var2.f.rotateAngleX = this.recoveredField2210 * var4;
         var2.h.rotateAngleY = this.recoveredField2209 * var4
            - (this.recoveredField2212.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.recoveredField2208.method_21227()));
         var2.h.rotateAngleX = this.recoveredField2207 * var4;
         var2.bipedRightArmwear.rotateAngleY = this.recoveredField2209 * var4
            - (this.recoveredField2212.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.recoveredField2208.method_21227()));
         var2.bipedRightArmwear.rotateAngleX = this.recoveredField2207 * var4;
         if (!this.recoveredField2212.method_21217() && this.recoveredField897.method_21229() <= this.recoveredField2212.method_21240()) {
            this.recoveredField2212.method_20200();
         }

         if (this.recoveredField2212.method_21217()) {
            var4 = this.recoveredField2212.method_21227();
            var2.e.rotateAngleY = var6 * var4;
            var2.f.rotateAngleY = var6 * var4;
            var2.e.rotateAngleZ = 0.0F;
            var2.f.rotateAngleZ = 0.0F;
            var2.e.rotateAngleX = var2.e.rotateAngleX - (this.recoveredField2210 - var5) * var4;
            var2.f.rotateAngleX = var2.f.rotateAngleX - (this.recoveredField2210 - var5) * var4;
            var2.h.rotateAngleY = var2.h.rotateAngleY - this.recoveredField2209 * var4;
            var2.h.rotateAngleX = var2.h.rotateAngleX - this.recoveredField2207 * var4;
            var2.bipedRightArmwear.rotateAngleY = var2.bipedRightArmwear.rotateAngleY - this.recoveredField2209 * var4;
            var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX - this.recoveredField2207 * var4;
         }
      }
   }
}
