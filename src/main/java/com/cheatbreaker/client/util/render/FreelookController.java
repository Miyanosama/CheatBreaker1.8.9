package com.cheatbreaker.client.util.render;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;

public class FreelookController {
   public int recoveredField3502;
   public boolean recoveredField3503;
   public CheatBreaker recoveredField3504;
   public float recoveredField3505;
   public float recoveredField3506;
   public Minecraft recoveredField3507 = Minecraft.getMinecraft();
   public float recoveredField3508;
   public float recoveredField3509;

   public void method_23796() {
      this.recoveredField3507.gameSettings.thirdPersonView = this.recoveredField3502;
      this.recoveredField3503 = false;
   }

   public void method_23797(float var1, float var2) {
      float var3 = this.recoveredField3509;
      float var4 = this.recoveredField3508;
      this.recoveredField3508 = (float)(
         this.recoveredField3508 + (this.recoveredField3504.getModuleManager().recoveredField1723.recoveredField3167.method_08908() ? -var1 : var1) * 0.15
      );
      this.recoveredField3509 = (float)(
         this.recoveredField3509 - (this.recoveredField3504.getModuleManager().recoveredField1723.recoveredField3165.method_08908() ? -var2 : var2) * 0.15
      );
      if (this.recoveredField3504.getModuleManager().recoveredField1723.recoveredField3177.method_08908()) {
         if (this.recoveredField3509 < -90.0F) {
            this.recoveredField3509 = -90.0F;
         }

         if (this.recoveredField3509 > 90.0F) {
            this.recoveredField3509 = 90.0F;
         }
      }

      this.recoveredField3506 = this.recoveredField3506 + (this.recoveredField3509 - var3);
      this.recoveredField3505 = this.recoveredField3505 + (this.recoveredField3508 - var4);
   }

   public FreelookController() {
      this.recoveredField3504 = CheatBreaker.getInstance();
      this.recoveredField3503 = false;
      this.recoveredField3504.method_19817().method_21938(TickEvent.class, var1 -> {
         if (this.recoveredField3504.getModuleManager().recoveredField1723.recoveredField3179.method_08908()) {
            if (this.recoveredField3503 && this.recoveredField3504.getGlobalSettings().recoveredField498.isPressed()) {
               this.method_23796();
            }
         } else if (this.recoveredField3503 && !this.recoveredField3504.getGlobalSettings().recoveredField498.isKeyDown()) {
            this.method_23796();
         }
      });
   }

   public void method_23799() {
      if (this.recoveredField3504.getModuleManager().recoveredField1723.isEnabled()) {
         this.recoveredField3508 = this.recoveredField3507.thePlayer.y;
         this.recoveredField3509 = this.recoveredField3507.thePlayer.z;
         this.recoveredField3505 = this.recoveredField3507.thePlayer.A;
         this.recoveredField3506 = this.recoveredField3507.thePlayer.B;
         this.recoveredField3502 = this.recoveredField3507.gameSettings.thirdPersonView;
         String var1 = this.recoveredField3504.getModuleManager().recoveredField1723.recoveredField3184.method_08874();
         switch (var1) {
            case "Third":
               this.recoveredField3507.gameSettings.thirdPersonView = 1;
               break;
            case "Reverse":
               this.recoveredField3507.gameSettings.thirdPersonView = 2;
               break;
            default:
               this.recoveredField3507.gameSettings.thirdPersonView = 0;
         }

         this.recoveredField3503 = true;
      }
   }
}
