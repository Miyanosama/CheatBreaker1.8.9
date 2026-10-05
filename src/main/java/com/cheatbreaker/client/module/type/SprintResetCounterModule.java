package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class SprintResetCounterModule extends CombatCounterModule {
   public static long recoveredField680;
   public static List<Long> recoveredField682 = new ArrayList<>();
   public static List<Long> recoveredField690 = new ArrayList<>();
   public boolean recoveredField683;
   public Setting recoveredField684;
   public boolean recoveredField685;
   public int recoveredField686;
   public int recoveredField687;
   public static int recoveredField688;
   public static List<Long> recoveredField681 = new ArrayList<>();
   public static int recoveredField689 = -1;
   public static long recoveredField691;

   @Override
   public String method_00167() {
      if (recoveredField1835 == 0L && (Boolean)this.recoveredField1838.getValue()) {
         return null;
      } else {
         this.recoveredField687 = this.recoveredField684.getValue().equals("Forward") ? recoveredField690.size() : recoveredField681.size();
         return this.recoveredField684.getValue().equals("Higher")
            ? (this.recoveredField687 = Math.max(recoveredField690.size(), recoveredField681.size())) + ""
            : this.recoveredField687 + "";
      }
   }

   @Override
   public String method_00164() {
      return "4";
   }

   @Override
   public String method_00166() {
      int var1 = this.recoveredField686;
      if ((Boolean)this.recoveredField684.getValue().equals("Higher")) {
         if (recoveredField681.size() > recoveredField690.size()) {
            var1 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
         } else {
            var1 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
         }
      }

      return "" + Keyboard.getKeyName(var1) + "Tap" + (this.recoveredField687 != 1 ? "s" : "");
   }

   public void method_12432(TickEvent var1) {
      int var2 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
      int var3 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
      this.recoveredField686 = this.recoveredField684.getValue().equals("Forward") ? var2 : var3;
      int var4 = var2 != -99 && var2 != -100 ? -1 : (var2 == -99 ? 0 : 1);
      int var5 = var3 != -99 && var3 != -100 ? -1 : (var3 == -99 ? 0 : 1);
      boolean var6 = (
            this.minecraft.currentScreen == null
               || this.minecraft.currentScreen instanceof GuiContainer
               || this.minecraft.currentScreen instanceof CBModulesGui
         )
         && (var4 != -1 ? Mouse.isButtonDown(var4) : Keyboard.isKeyDown(var2));
      boolean var7 = (
            this.minecraft.currentScreen == null
               || this.minecraft.currentScreen instanceof GuiContainer
               || this.minecraft.currentScreen instanceof CBModulesGui
         )
         && (var5 != -1 ? Mouse.isButtonDown(var5) : Keyboard.isKeyDown(var3));
      if (this.minecraft.gameSettings.keyBindForward.isPressed() && this.minecraft.thePlayer.isSprinting() && recoveredField682.size() > 0) {
         if (var7) {
            recoveredField681.add(System.currentTimeMillis());
            recoveredField682.clear();
            return;
         }

         if (var6 && !this.recoveredField683) {
            this.recoveredField683 = true;
            if (this.recoveredField685) {
               recoveredField690.add(System.currentTimeMillis());
            }

            this.recoveredField685 = false;
            recoveredField680 = System.currentTimeMillis();
            recoveredField682.clear();
         } else if (this.recoveredField683 && !var6) {
            this.recoveredField683 = false;
            this.recoveredField685 = true;
         }
      }

      if (System.currentTimeMillis() - recoveredField1835 > 2000L) {
         recoveredField1835 = 0L;
         this.method_09625();
      }
   }

   @Override
   public void method_09625() {
      recoveredField680 = 0L;
      recoveredField690.clear();
      recoveredField681.clear();
   }

   @Override
   public void method_00165() {
      this.recoveredField684 = new Setting(this, "Counter").setValue("Forward").acceptedValues("Forward", "Backwards", "Higher");
      this.recoveredField1838 = new Setting(this, "Hide When Not Attacking").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
   }

   public SprintResetCounterModule() {
      super("Sprint Reset Counter", "[4 WTaps]");
      this.method_28821("Displays the amount of times you sprint reset.");
      this.method_28829("AgentManny");
      this.method_28820(TickEvent.class, this::method_12432);
   }
}
