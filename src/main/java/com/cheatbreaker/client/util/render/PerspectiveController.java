package com.cheatbreaker.client.util.render;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;

public class PerspectiveController {
   public Minecraft recoveredField1492 = Minecraft.getMinecraft();
   public float recoveredField1493;
   public float recoveredField1494;
   public int recoveredField1495;
   public float recoveredField1496;
   public boolean recoveredField1497;
   public int recoveredField1498;
   public CheatBreaker recoveredField1499 = CheatBreaker.getInstance();
   public float recoveredField1500;

   public void method_21020() {
      this.recoveredField1492.gameSettings.thirdPersonView = this.recoveredField1498;
      this.recoveredField1497 = false;
   }

   public PerspectiveController() {
      this.recoveredField1497 = false;
      this.recoveredField1499
         .method_19817()
         .method_21938(
            TickEvent.class,
            var1 -> {
               if (this.recoveredField1495 == 1 && this.recoveredField1499.getModuleManager().recoveredField1723.recoveredField3185.method_08908()
                  || this.recoveredField1495 == 2 && this.recoveredField1499.getModuleManager().recoveredField1723.recoveredField3170.method_08908()) {
                  if (this.recoveredField1497
                     && (
                        this.recoveredField1499.getGlobalSettings().recoveredField482.isPressed()
                           || this.recoveredField1499.getGlobalSettings().recoveredField479.isPressed()
                     )) {
                     this.method_21020();
                  }
               } else if (this.recoveredField1497
                  && !this.recoveredField1499.getGlobalSettings().recoveredField482.isKeyDown()
                  && !this.recoveredField1499.getGlobalSettings().recoveredField479.isKeyDown()) {
                  this.method_21020();
               }
            }
         );
   }

   public void method_21021(int var1) {
      if (this.recoveredField1499.getModuleManager().recoveredField1723.isEnabled()) {
         this.recoveredField1493 = this.recoveredField1492.thePlayer.y;
         this.recoveredField1500 = this.recoveredField1492.thePlayer.z;
         this.recoveredField1494 = this.recoveredField1492.thePlayer.A;
         this.recoveredField1496 = this.recoveredField1492.thePlayer.B;
         this.recoveredField1498 = this.recoveredField1492.gameSettings.thirdPersonView;
         if (var1 == 1) {
            this.recoveredField1492.gameSettings.thirdPersonView = 1;
         } else {
            this.recoveredField1492.gameSettings.thirdPersonView = 2;
         }

         this.recoveredField1497 = true;
         this.recoveredField1495 = var1;
      }
   }
}
