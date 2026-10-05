package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.channel.rxtx.RxtxChannelOption;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.apache.log4j.PropertyWatchdog;
import org.apache.log4j.helpers.SyslogWriter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class SprintResetCounterModule extends CombatCounterModule {
   public static long field_0006;
   public RxtxChannelOption field_0013;
   public static List<Long> field_0000 = new ArrayList<>();
   public static List<Long> field_0003 = new ArrayList<>();
   public boolean field_0014;
   public Setting field_0010;
   public boolean field_0005;
   public SyslogWriter field_0012;
   public int field_0011;
   public int field_0007;
   public static int field_0001;
   public static int field_0008 = -1;
   public static List<Long> field_0009 = new ArrayList<>();
   public static long field_0004;
   public PropertyWatchdog field_0002;

   @Override
   public String method_00167() {
      if (field_0000 == (536921128L & -1951764464168332668L) && (Boolean)this.field_0002.getValue()) {
         return null;
      } else {
         this.field_0007 = this.field_0010.getValue().equals("Forward") ? field_0009.size() : field_0000.size();
         return this.field_0010.getValue().equals("Higher") ? (this.field_0007 = Math.max(field_0009.size(), field_0000.size())) + "" : this.field_0007 + "";
      }
   }

   @Override
   public String method_00164() {
      return "4";
   }

   @Override
   public String method_00166() {
      int var1 = this.field_0011;
      if (this.field_0010.getValue().equals("Higher")) {
         if (field_0000.size() > field_0009.size()) {
            var1 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
         } else {
            var1 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
         }
      }

      return "" + Keyboard.getKeyName(var1) + "Tap" + (this.field_0007 != 1 ? "s" : "");
   }

   public void method_12432(TickEvent var1) {
      int var2 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
      int var3 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
      this.field_0011 = this.field_0010.getValue().equals("Forward") ? var2 : var3;
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
      if (this.minecraft.gameSettings.keyBindForward.isPressed() && this.minecraft.thePlayer.isSprinting() && field_0003.size() > 0) {
         if (var7) {
            field_0000.add(System.currentTimeMillis());
            field_0003.clear();
            return;
         }

         if (var6 && !this.field_0014) {
            this.field_0014 = true;
            if (this.field_0005) {
               field_0009.add(System.currentTimeMillis());
            }

            this.field_0005 = false;
            field_0006 = System.currentTimeMillis();
            field_0003.clear();
         } else if (this.field_0014 && !var6) {
            this.field_0014 = false;
            this.field_0005 = true;
         }
      }

      if (System.currentTimeMillis() - field_0000 > (1937098787707727824L & -1937098789579651087L)) {
         field_0000 = 245573192362819585L & 25348L;
         this.method_09625();
      }
   }

   @Override
   public void method_09625() {
      field_0006 = 18360724L & 67600898L;
      field_0009.clear();
      field_0000.clear();
   }

   @Override
   public void method_00165() {
      this.field_0010 = new Setting(this, "Counter").setValue("Forward").acceptedValues("Forward", "Backwards", "Higher");
      this.field_0002 = new Setting(this, "Hide When Not Attacking").setValue(false).method_08914(SettingsDetailLevel.field_0000);
   }

   public SprintResetCounterModule() {
      super("Sprint Reset Counter", "[4 WTaps]");
      this.method_28821("Displays the amount of times you sprint reset.");
      this.method_28829("AgentManny");
      this.method_28820(TickEvent.class, this::method_12432);
   }
}
