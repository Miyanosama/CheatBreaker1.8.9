package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class DabEmote extends Emote {
   public MinMaxFade recoveredField85 = new MinMaxFade(250L);
   public MinMaxFade recoveredField86 = new MinMaxFade(250L);

   public DabEmote() {
      super("Dab", new MinMaxFade(1000L));
      this.recoveredField85.method_20200();
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      if (this.recoveredField85.method_21240() > this.recoveredField897.method_21207()) {
         var4 = this.recoveredField85.method_21227();
      } else if (this.recoveredField897.method_21229() <= this.recoveredField86.method_21240()) {
         if (!this.recoveredField86.method_21217()) {
            this.recoveredField86.method_20200();
         }

         var4 = 1.0F - this.recoveredField86.method_21227();
      }

      var2.h.rotateAngleX = (float)Math.toRadians(-90.0F * var4);
      var2.h.rotateAngleY = (float)Math.toRadians(-35.0F * var4);
      var2.i.rotateAngleX = (float)Math.toRadians(15.0F * var4);
      var2.i.rotateAngleY = (float)Math.toRadians(15.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(-110.0F * var4);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(-90.0F * var4);
      var2.bipedRightArmwear.rotateAngleY = (float)Math.toRadians(-35.0F * var4);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleY = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-110.0F * var4);
      float var5 = var1.z;
      float var6 = var1.aJ - var1.y;
      var2.e.rotateAngleX = (float)Math.toRadians(-var5 * var4) + (float)Math.toRadians(45.0F * var4 + var5);
      var2.e.rotateAngleY = (float)Math.toRadians(var6 * var4) + (float)Math.toRadians(35.0F * var4 - var6);
      var2.f.rotateAngleX = (float)Math.toRadians(-var5 * var4) + (float)Math.toRadians(45.0F * var4 + var5);
      var2.f.rotateAngleY = (float)Math.toRadians(var6 * var4) + (float)Math.toRadians(35.0F * var4 - var6);
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
