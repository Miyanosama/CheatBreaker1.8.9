package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class TPoseEmote extends Emote {
   public ExponentialFade recoveredField2940 = new ExponentialFade(600L);
   public MinMaxFade recoveredField2941 = new MinMaxFade(600L);

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      var2.h.rotateAngleX = var2.h.rotateAngleX * this.recoveredField2941.method_21227();
      var2.i.rotateAngleX = var2.i.rotateAngleX * this.recoveredField2941.method_21227();
      var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX * this.recoveredField2941.method_21227();
      var2.bipedLeftArmwear.rotateAngleX = var2.bipedLeftArmwear.rotateAngleX * this.recoveredField2941.method_21227();
      if (this.recoveredField2940.method_21233()) {
         var2.h.rotateAngleZ = (float)Math.toRadians(90.0F * this.recoveredField2940.method_21227());
         var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(90.0F * this.recoveredField2940.method_21227());
         var2.i.rotateAngleZ = (float)Math.toRadians(-90.0F * this.recoveredField2940.method_21227());
         var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-90.0F * this.recoveredField2940.method_21227());
      } else if (this.recoveredField897.method_21229() <= this.recoveredField2941.method_21240()) {
         if (!this.recoveredField2941.method_21217()) {
            this.recoveredField2941.method_20200();
         }

         var2.i.rotateAngleZ = Math.min((float)Math.toRadians(-90.0F + 90.0F * this.recoveredField2941.method_21227()), var2.i.rotateAngleZ);
         var2.bipedLeftArmwear.rotateAngleZ = Math.min(
            (float)Math.toRadians(-90.0F + 90.0F * this.recoveredField2941.method_21227()), var2.bipedLeftArmwear.rotateAngleZ
         );
         var2.h.rotateAngleZ = Math.max((float)Math.toRadians(90.0F - 90.0F * this.recoveredField2941.method_21227()), var2.h.rotateAngleZ);
         var2.bipedRightArmwear.rotateAngleZ = Math.max(
            (float)Math.toRadians(90.0F - 90.0F * this.recoveredField2941.method_21227()), var2.bipedRightArmwear.rotateAngleZ
         );
      } else {
         var2.h.rotateAngleZ = (float)Math.toRadians(90.0);
         var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(90.0);
         var2.i.rotateAngleZ = (float)Math.toRadians(-90.0);
         var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-90.0);
      }
   }

   public TPoseEmote() {
      super("T-Pose", new MinMaxFade(5000L));
      this.recoveredField2940.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
