package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.config.IntegerRangeDefaults;

public abstract class NumberHudModule extends TextHudModule {
   public Setting recoveredField2752;
   public Setting recoveredField2753;

   public IntegerRangeDefaults method_00168() {
      return new IntegerRangeDefaults(0, 0, 30);
   }

   public NumberHudModule(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public String method_21178() {
      return "%VALUE% %LABEL%";
   }

   @Override
   public void method_00165() {
      this.recoveredField2752 = new Setting(this, "Hide when value is")
         .setValue("OFF")
         .acceptedValues(this.recoveredField3897)
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2753 = new Setting(this, "Hidden value(s)")
         .setValue(this.method_00168().method_26745())
         .setMinMax(this.method_00168().method_26746(), this.method_00168().method_26744())
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> !this.recoveredField2752.getValue().equals("OFF"));
   }

   public NumberHudModule(String var1, String var2, float var3, boolean var4, boolean var5) {
      super(var1, var2, var3, var4, var5);
   }
}
