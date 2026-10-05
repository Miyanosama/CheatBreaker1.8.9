package com.cheatbreaker.client.util.render;

import org.lwjgl.opengl.GL11;

public class LegacyGlBooleanState {
   public boolean recoveredField3379 = false;
   public int recoveredField3380;

   public void method_03840(boolean var1) {
      if (var1 != this.recoveredField3379) {
         this.recoveredField3379 = var1;
         if (var1) {
            GL11.glEnable(this.recoveredField3380);
         } else {
            GL11.glDisable(this.recoveredField3380);
         }
      }
   }

   public LegacyGlBooleanState(int var1) {
      this.recoveredField3380 = var1;
   }

   public void method_03839() {
      this.method_03840(false);
   }

   public void method_03841() {
      this.method_03840(true);
   }
}
