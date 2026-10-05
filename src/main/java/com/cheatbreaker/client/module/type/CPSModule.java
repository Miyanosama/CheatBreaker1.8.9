package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.ArrayDeque;
import java.util.Deque;
import org.apache.commons.lang3.StringUtils;
import com.cheatbreaker.client.event.type.MouseClickEvent;

public class CPSModule extends CombatCounterModule {
   public Setting recoveredField2534;
   public String recoveredField2535;
   public Setting recoveredField2536;
   public Deque<Long> recoveredField2537;
   public String recoveredField2538;
   public Deque<Long> recoveredField2539 = new ArrayDeque<>();

   public void method_04334(String var1, String var2) {
      float var3 = (float)this.minecraft.fontRendererObj.getStringWidth(StringUtils.substringBefore(var1, "%VALUE%"))
         + this.minecraft.fontRendererObj.getStringWidth(this.recoveredField2535)
         + (this.minecraft.fontRendererObj.getStringWidth(this.recoveredField2538) / 2.0F - 0.1F);
      float var4 = !this.recoveredField2245.method_08908() && !this.recoveredField2239.method_08908()
         ? -1.0F
         : this.recoveredField2247.method_08905() / 2.0F - 4.5F;
      boolean var5 = this.recoveredField2245.method_08908() ? this.recoveredField2240.method_08908() : this.recoveredField2246.method_08908();
      if (this.recoveredField2245.method_08908() || this.recoveredField2239.method_08908()) {
         float var6 = this.recoveredField3889 / 2.0F
            - this.minecraft.fontRendererObj.getStringWidth(var1.replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", var2)) / 2;
         var3 += var6;
      }

      if ((Boolean)this.recoveredField2536.getValue().equals("Both")) {
         RenderUtil.method_22058(var3, var4, var3 + 1.0F, var4 + 9.0F, this.recoveredField2534.method_08901(), var5);
      }
   }

   @Override
   public String method_00164() {
      return this.recoveredField2536.getValue().equals("Both") ? "9  2" : "9";
   }

   @Override
   public void method_04335() {
      this.recoveredField2534 = new Setting(this, "Line Color")
         .setValue(-14671840)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField2536.getValue().equals("Both"));
   }

   public void method_04337(MouseClickEvent var1) {
      if (var1.method_05882() == 0) {
         this.recoveredField2539.add(System.currentTimeMillis());
      }

      if (var1.method_05882() == 1) {
         this.recoveredField2537.add(System.currentTimeMillis());
      }
   }

   @Override
   public void method_04331(String var1) {
      super.method_04331(var1);
      this.method_04334((this.recoveredField2245.method_08908() ? this.recoveredField2233 : this.recoveredField2243).method_08874(), var1);
   }

   @Override
   public String method_00166() {
      return (
            !this.recoveredField2536.getValue().equals("Right Clicks")
                  && (!this.recoveredField2536.getValue().equals("Higher") || this.recoveredField2539.size() >= this.recoveredField2537.size())
               ? ""
               : "R"
         )
         + "CPS";
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.recoveredField2752.method_08894(() -> this.recoveredField2536.method_08874().endsWith(" Clicks"));
      this.recoveredField2753
         .method_08894(() -> !this.recoveredField2752.getValue().equals("OFF") && this.recoveredField2536.method_08874().endsWith(" Clicks"));
      this.recoveredField2536 = new Setting(
            this,
            "Counter",
            "Determines which and when clicks should be shown.\n\n§bLeft Clicks:§r Only show left clicks.\n§bRight Clicks:§r Only show right clicks.\n§bBoth:§r Show both left and right clicks.\n§bHigher:§r Shows either left or right clicks depending which has a higher amount."
         )
         .setValue("Left Clicks")
         .acceptedValues("Left Clicks", "Right Clicks", "Both", "Higher")
         .method_08914(SettingsDetailLevel.SIMPLE);
   }

   public void onTick(TickEvent var1) {
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.recoveredField2752, this.recoveredField2539.size(), this.recoveredField2753.method_08912())
         && (Boolean)this.recoveredField2536.getValue().equals("Left Clicks")) {
         return null;
      } else if (this.method_09815(this.recoveredField2752, this.recoveredField2537.size(), this.recoveredField2753.method_08912())
         && (Boolean)this.recoveredField2536.getValue().equals("Right Clicks")) {
         return null;
      } else if (recoveredField1835 == 0L && (Boolean)this.recoveredField1838.getValue()) {
         return null;
      } else {
         this.recoveredField2539.removeIf(var0 -> var0 < System.currentTimeMillis() - 1000L);
         this.recoveredField2537.removeIf(var0 -> var0 < System.currentTimeMillis() - 1000L);
         this.recoveredField2535 = !this.recoveredField2536.getValue().equals("Right Clicks") ? "" + this.recoveredField2539.size() : "";
         String var1 = !this.recoveredField2536.getValue().equals("Left Clicks") ? Integer.toString(this.recoveredField2537.size()) : "";
         this.recoveredField2538 = this.recoveredField2536.getValue().equals("Both") ? "  " : "";
         return this.recoveredField2536.getValue().equals("Higher")
            ? Math.max(this.recoveredField2539.size(), this.recoveredField2537.size()) + ""
            : this.recoveredField2535 + this.recoveredField2538 + var1;
      }
   }

   public CPSModule() {
      super("CPS", "[9 CPS]");
      this.recoveredField2537 = new ArrayDeque<>();
      this.method_28821("Displays your clicks per second.");
      this.method_28829("Fyu");
      this.method_28820(MouseClickEvent.class, this::method_04337);
      this.method_28820(TickEvent.class, this::onTick);
   }
}
