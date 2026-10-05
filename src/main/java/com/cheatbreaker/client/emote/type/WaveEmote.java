package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class WaveEmote extends Emote {
   public FloatFade recoveredField2401;
   public CosineFade recoveredField2402;
   public FloatFade recoveredField2403 = new FloatFade(250L);

   public WaveEmote() {
      super("Wave", new FloatFade(2000L));
      this.recoveredField2401 = new FloatFade(250L);
      this.recoveredField2402 = new CosineFade(500L);
      this.recoveredField2403.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      float var5 = 0.5F;
      if (this.recoveredField2403.method_21240() > this.recoveredField897.method_21207()) {
         var4 = this.recoveredField2403.method_21227();
      } else if (this.recoveredField897.method_21229() <= this.recoveredField2401.method_21240()) {
         if (!this.recoveredField2401.method_21217()) {
            this.recoveredField2401.method_20200();
         }

         var4 = 1.0F - this.recoveredField2401.method_21227();
      } else {
         if (!this.recoveredField2402.method_21217()) {
            this.recoveredField2402.method_21221(125.0F);
            this.recoveredField2402.method_21234();
         }

         var5 = this.recoveredField2402.method_21227();
      }

      var2.i.rotateAngleX = (float)Math.toRadians(-150.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(40.0F * var5 - 20.0F);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(-150.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(40.0F * var5 - 20.0F);
   }
}
