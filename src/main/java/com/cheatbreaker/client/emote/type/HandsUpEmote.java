package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class HandsUpEmote extends Emote {
   public MinMaxFade recoveredField3561 = new MinMaxFade(250L);
   public MinMaxFade recoveredField3562 = new MinMaxFade(250L);

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      if (this.recoveredField3561.method_21240() > this.recoveredField897.method_21207()) {
         var4 = this.recoveredField3561.method_21227();
      } else if (this.recoveredField897.method_21229() <= this.recoveredField3562.method_21240()) {
         if (!this.recoveredField3562.method_21217()) {
            this.recoveredField3562.method_20200();
         }

         var4 = 1.0F - this.recoveredField3562.method_21227();
      }

      var2.i.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.h.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.h.rotateAngleZ = (float)Math.toRadians(-15.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(15.0F * var4);
   }

   public HandsUpEmote() {
      super("Hands Up", new FloatFade(2000L));
      this.recoveredField3561.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
