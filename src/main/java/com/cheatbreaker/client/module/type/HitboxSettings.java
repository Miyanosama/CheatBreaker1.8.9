package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;

public class HitboxSettings {
   public Setting recoveredField3268;
   public Setting recoveredField3269;
   public Setting recoveredField3270;
   public Setting recoveredField3271;
   public Setting recoveredField3272;
   public Setting recoveredField3273;
   public Setting recoveredField3274;
   public Setting recoveredField3275;
   public Setting recoveredField3276;
   public Setting recoveredField3277;
   public Setting recoveredField3278;
   public Setting recoveredField3279;

   public HitboxSettings(AbstractModule var1, String var2) {
      new Setting(var1, "label").setValue(var2 + " Hitbox Options");
      boolean var3 = var2.equals("Player") || var2.equals("Mob");
      this.recoveredField3272 = new Setting(var1, "Show " + var2 + " Hitbox").setValue(var3);
      this.recoveredField3279 = new Setting(var1, "Show " + var2 + " Hitbox Outline")
         .setValue(true)
         .method_08894(() -> this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3274 = new Setting(var1, "Show " + var2 + " Eye Height")
         .setValue(var3)
         .method_08894(() -> this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3271 = new Setting(var1, "Show " + var2 + " Look Vector")
         .setValue(var3)
         .method_08894(() -> this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      if (var2.equals("Player")) {
         this.recoveredField3276 = new Setting(var1, "Show Own Hitbox")
            .setValue(false)
            .method_08894(() -> this.recoveredField3272.method_08908())
            .method_08914(SettingsDetailLevel.MEDIUM);
      }

      this.recoveredField3268 = new Setting(var1, "Show " + var2 + " Dashed Line")
         .setValue(false)
         .method_08894(() -> this.recoveredField3272.method_08908() && this.recoveredField3279.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3277 = new Setting(var1, var2 + " Line Distance")
         .setValue(10)
         .setMinMax(0, 20)
         .method_08892("px")
         .method_08894(() -> this.recoveredField3272.method_08908() && this.recoveredField3279.method_08908() && this.recoveredField3268.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3275 = new Setting(var1, var2 + " Line Thickness")
         .setValue(2)
         .setMinMax(1, 5)
         .method_08892("px")
         .method_08894(() -> this.recoveredField3272.method_08908() && this.recoveredField3279.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      if (var2.equals("Player")) {
         this.recoveredField3278 = new Setting(var1, "Own Hitbox Outline Color")
            .setValue(-1)
            .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .method_08894(() -> this.recoveredField3279.method_08908() && this.recoveredField3276.method_08908() && this.recoveredField3272.method_08908())
            .method_08914(SettingsDetailLevel.MEDIUM);
      }

      this.recoveredField3269 = new Setting(var1, var2 + " Outline Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.recoveredField3279.method_08908() && this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3270 = new Setting(var1, var2 + " Eye Height Color")
         .setValue(-65536)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.recoveredField3274.method_08908() && this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField3273 = new Setting(var1, var2 + " Look Vector Color")
         .setValue(-16776961)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.recoveredField3271.method_08908() && this.recoveredField3272.method_08908())
         .method_08914(SettingsDetailLevel.MEDIUM);
   }
}
