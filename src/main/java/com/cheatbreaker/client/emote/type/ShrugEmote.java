package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.ui.fading.CosineFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class ShrugEmote extends Emote {
   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      var2.h.offsetY = -0.2F * this.recoveredField897.method_21227();
      var2.i.offsetY = -0.2F * this.recoveredField897.method_21227();
      var2.bipedRightArmwear.offsetY = -0.2F * this.recoveredField897.method_21227();
      var2.bipedLeftArmwear.offsetY = -0.2F * this.recoveredField897.method_21227();
      var2.e.offsetY = 0.05F * this.recoveredField897.method_21227();
      var2.f.offsetY = 0.05F * this.recoveredField897.method_21227();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   public ShrugEmote() {
      super("Shrug", new CosineFade(500L));
   }
}
