package com.cheatbreaker.client.util.render;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class EyeHeightAnimationController {
   public static final float recoveredField3263 = 1.62F;
   public float recoveredField3264;
   public float recoveredField3265;
   public static final float recoveredField3266 = 1.54F;
   public static EyeHeightAnimationController recoveredField3267 = new EyeHeightAnimationController();

   public void method_20197(TickEvent var1) {
      this.recoveredField3264 = this.recoveredField3265;
      EntityPlayerSP var2 = Minecraft.getMinecraft().thePlayer;
      if (var2 == null) {
         this.recoveredField3265 = 1.62F;
      } else {
         if (var2.isSneaking()) {
            this.recoveredField3265 = 1.54F;
         } else if (!CheatBreaker.getInstance().getModuleManager().recoveredField1717.recoveredField3721.method_08908()) {
            this.recoveredField3265 = 1.62F;
         } else if (this.recoveredField3265 < 1.62F) {
            float var3 = 1.62F - this.recoveredField3265;
            var3 = (float)(var3 * 0.4);
            this.recoveredField3265 = 1.62F - var3;
         }
      }
   }

   public float method_20196(float var1) {
      return !CheatBreaker.getInstance().getModuleManager().recoveredField1717.recoveredField3727.method_08908()
         ? this.recoveredField3265
         : this.recoveredField3264 + (this.recoveredField3265 - this.recoveredField3264) * var1;
   }

   public static EyeHeightAnimationController method_20195() {
      return recoveredField3267;
   }

   public EyeHeightAnimationController() {
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_20197);
   }
}
