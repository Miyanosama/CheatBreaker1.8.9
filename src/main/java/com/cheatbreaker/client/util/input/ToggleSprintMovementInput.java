package com.cheatbreaker.client.util.input;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.ToggleSprintModule;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;

public class ToggleSprintMovementInput extends MovementInputFromOptions {
   public static boolean recoveredField3284 = true;
   public static boolean recoveredField3281 = false;
   public static long recoveredField3282;
   public static boolean recoveredField3283;
   public static boolean recoveredField3280 = false;
   public static boolean recoveredField3285;
   public static long recoveredField3286;
   public static String recoveredField3291 = "";
   public static boolean recoveredField3288;
   public static boolean recoveredField3289;
   public static boolean recoveredField3290;
   public static boolean recoveredField3287 = false;
   public static boolean recoveredField3292;
   public static boolean recoveredField3293;

   public void method_09086(boolean var1, boolean var2) {
      recoveredField3284 = var1;
      recoveredField3280 = var2;
   }

   public static void method_09084(MovementInputFromOptions var0, EntityPlayerSP var1, GameSettings var2) {
      String var3 = "";
      boolean var4 = var1.bA.isFlying;
      boolean var5 = var1.au();
      boolean var6 = var2.keyBindSneak.isKeyDown();
      boolean var7 = var2.recoveredField2692.isKeyDown();
      if (var4) {
         DecimalFormat var8 = new DecimalFormat("#.00");
         var3 = (Boolean)ToggleSprintModule.recoveredField3630.getValue() && var7 && var1.bA.isCreativeMode
            ? var3
               + ((String)CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3632.getValue())
                  .replaceAll("%BOOST%", var8.format(ToggleSprintModule.recoveredField3623.getValue()))
            : var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3626.getValue();
      }

      if (var5) {
         var3 = var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3633.getValue();
      }

      if (var0.sneak) {
         var3 = var4
            ? CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3621.getValue().toString()
            : (
               var5
                  ? CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3622.getValue().toString()
                  : (
                     var6
                        ? var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3628.getValue()
                        : var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3624.getValue()
                  )
            );
      } else if (recoveredField3284 && !var4 && !var5) {
         boolean var9 = recoveredField3281 || recoveredField3283 || recoveredField3280;
         var3 = var7
            ? var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3631.getValue()
            : (
               var9
                  ? var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3618.getValue()
                  : var3 + CheatBreaker.getInstance().getModuleManager().recoveredField1716.recoveredField3625.getValue()
            );
      }

      recoveredField3291 = (String)var3;
   }

   public ToggleSprintMovementInput(GameSettings var1) {
      super(var1);
   }

   public static boolean method_09083() {
      return !CheatBreaker.getInstance().getModuleManager().recoveredField1716.method_28796() && ToggleSprintModule.recoveredField3616.method_08908();
   }

   public static void method_09085(Minecraft var0, MovementInputFromOptions var1, EntityPlayerSP var2) {
      var1.recoveredField3365 = 0.0F;
      var1.recoveredField3366 = 0.0F;
      GameSettings var3 = var0.gameSettings;
      if (var3.keyBindForward.isKeyDown()) {
         var1.recoveredField3366++;
      }

      if (var3.keyBindBack.isKeyDown()) {
         var1.recoveredField3366--;
      }

      if (var3.keyBindLeft.isKeyDown()) {
         var1.recoveredField3365++;
      }

      if (var3.keyBindRight.isKeyDown()) {
         var1.recoveredField3365--;
      }

      if (var2.au() && !recoveredField3288) {
         recoveredField3288 = true;
         recoveredField3289 = recoveredField3284;
      } else if (recoveredField3288 && !var2.au()) {
         recoveredField3288 = false;
         if (recoveredField3289 && !recoveredField3284) {
            recoveredField3284 = true;
            recoveredField3282 = System.currentTimeMillis();
            recoveredField3290 = true;
            recoveredField3281 = false;
         }
      }

      var1.jump = var3.keyBindJump.isKeyDown();
      if ((Boolean)ToggleSprintModule.recoveredField3629.getValue() && CheatBreaker.getInstance().getModuleManager().recoveredField1716.isEnabled()) {
         if (var3.keyBindSneak.isKeyDown() && !recoveredField3285) {
            if (!var2.au() && !var2.bA.isFlying) {
               var1.sneak = !var1.sneak;
            } else {
               var1.sneak = true;
               recoveredField3293 = var2.au();
            }

            recoveredField3286 = System.currentTimeMillis();
            recoveredField3285 = true;
         }

         if (!var3.keyBindSneak.isKeyDown() && recoveredField3285) {
            if (!var2.bA.isFlying && !recoveredField3293) {
               if (System.currentTimeMillis() - recoveredField3286 > 300L) {
                  var1.sneak = false;
               }
            } else {
               var1.sneak = false;
            }

            recoveredField3285 = false;
         }

         if (!method_09083()) {
            if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer && var1.sneak) {
               recoveredField3287 = true;
               var1.sneak = false;
            } else if (recoveredField3287 && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainer)) {
               recoveredField3287 = false;
               var1.sneak = true;
            }
         }
      } else {
         var1.sneak = var3.keyBindSneak.isKeyDown();
      }

      if (var1.sneak) {
         var1.recoveredField3365 = (float)(var1.recoveredField3365 * 0.3);
         var1.recoveredField3366 = (float)(var1.recoveredField3366 * 0.3);
      }

      boolean var4 = var2.getFoodStats().getFoodLevel() > 6.0F || var2.bA.isFlying;
      boolean var5 = !var1.sneak && !var2.bA.isFlying && var4;
      recoveredField3283 = !(Boolean)ToggleSprintModule.recoveredField3620.getValue();
      recoveredField3292 = (Boolean)ToggleSprintModule.recoveredField3619.getValue();
      if ((var5 || recoveredField3283) && var3.recoveredField2692.isKeyDown() && !recoveredField3290 && !var2.bA.isFlying && !recoveredField3283) {
         recoveredField3284 = !recoveredField3284;
         recoveredField3282 = System.currentTimeMillis();
         recoveredField3290 = true;
         recoveredField3281 = false;
      }

      if ((var5 || recoveredField3283) && !var3.recoveredField2692.isKeyDown() && recoveredField3290) {
         if (System.currentTimeMillis() - recoveredField3282 > 300L) {
            recoveredField3281 = true;
         }

         recoveredField3290 = false;
      }

      method_09084(var1, var2, var3);
   }
}
