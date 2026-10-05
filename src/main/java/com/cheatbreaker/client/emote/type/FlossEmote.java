package com.cheatbreaker.client.emote.type;

import com.cheatbreaker.client.emote.type.FlossEmote$EnumSwitch;
import com.cheatbreaker.client.emote.type.FlossPose;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class FlossEmote extends Emote {
   public FlossPose recoveredField3220;
   public ExponentialFade recoveredField3221 = new ExponentialFade(375L);
   public CosineFade recoveredField3222;
   public boolean recoveredField3223;
   public CosineFade recoveredField3224 = new CosineFade(375L);

   public FlossEmote() {
      super("Floss", new FloatFade(7500L));
      this.recoveredField3222 = new CosineFade(250L);
      this.recoveredField3223 = false;
      this.recoveredField3222.method_20200();
      this.recoveredField3220 = FlossPose.LEFT_TO_RIGHT;
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      CheatBreaker.getInstance().method_19783().method_01383().put(var1.aK(), var2);
      if (!this.recoveredField3221.method_21217()) {
         if (this.recoveredField3222.method_21227() >= 0.5) {
            this.recoveredField3221.method_20200();
            this.recoveredField3224.method_20200();
         }
      } else if (this.recoveredField3221.method_21210()) {
         this.recoveredField3221.method_20200();
         this.recoveredField3224.method_20200();
         this.recoveredField3220 = this.method_28680();
      }

      if (this.recoveredField3222.method_21210()) {
         this.recoveredField3223 = !this.recoveredField3223;
         this.recoveredField3222.method_20200();
      }

      float var4 = this.recoveredField3221.method_21227();
      float var5 = this.recoveredField3224.method_21227();
      var2.h.rotateAngleX = (float)Math.toRadians((this.recoveredField3220 == FlossPose.RIGHT_TO_BACK ? 45 : -45) * var5);
      var2.i.rotateAngleX = (float)Math.toRadians((this.recoveredField3220 == FlossPose.LEFT_TO_BACK ? 45 : -45) * var5);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians((this.recoveredField3220 == FlossPose.RIGHT_TO_BACK ? 45 : -45) * var5);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians((this.recoveredField3220 == FlossPose.LEFT_TO_BACK ? 45 : -45) * var5);
      float var6 = 150.0F;
      float var7 = var6 / 2.0F;
      switch (FlossEmote$EnumSwitch.recoveredField3356[this.recoveredField3220.ordinal()]) {
         case 1:
            var2.h.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            break;
         case 2:
            var2.h.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.i.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            break;
         case 3:
            var2.h.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            break;
         case 4:
            var2.h.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
      }

      var5 = this.recoveredField3222.method_21227();
      if (this.recoveredField3223) {
         var2.g.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedCape.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.j.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.k.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.k.offsetX = 0.2F * var5;
         var2.j.offsetX = 0.2F * var5;
         var2.bipedBodyWear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedRightLegwear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedLeftLegwear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedLeftLegwear.offsetX = 0.2F * var5;
         var2.bipedRightLegwear.offsetX = 0.2F * var5;
      } else {
         var2.g.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedCape.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.j.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.k.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.k.offsetX = -0.2F * var5;
         var2.j.offsetX = -0.2F * var5;
         var2.bipedBodyWear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedRightLegwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedLeftLegwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedLeftLegwear.offsetX = -0.2F * var5;
         var2.bipedRightLegwear.offsetX = -0.2F * var5;
      }
   }

   public FlossPose method_28680() {
      switch (FlossEmote$EnumSwitch.recoveredField3356[this.recoveredField3220.ordinal()]) {
         case 2:
            return FlossPose.RIGHT_TO_LEFT;
         case 3:
            return FlossPose.LEFT_TO_BACK;
         case 4:
            return FlossPose.LEFT_TO_RIGHT;
         default:
            return FlossPose.RIGHT_TO_BACK;
      }
   }
}
