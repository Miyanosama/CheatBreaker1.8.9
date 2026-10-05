package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;
import net.optifine.expr.FunctionBool;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption;
import recovered.unidentified.UnidentifiedClass4439;

public class ReachDisplayModule extends CombatCounterModule {
   public FunctionBool field_0002;
   public GuiButtonEnumShaderOption field_0003;
   public Setting field_0000;
   public Setting field_0001;
   public static double field_0004;

   @Override
   public void method_01862() {
      this.field_0001 = new Setting(this, "Show trailing zeros", "Show all trailing zeros.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0000.method_08912() != 0);
      this.field_0000 = new Setting(this, "Decimals", "Change how many decimals the mod should display.")
         .setValue(1)
         .setMinMax(0, 16)
         .method_08914(SettingsDetailLevel.field_0003);
      super.method_01862();
   }

   @Override
   public String method_00164() {
      double var1 = 2.780034105782015;
      return this.field_0001.getValue()
         ? String.format("%." + this.field_0000.getValue() + "f", var1)
         : new DecimalFormat("#." + String.join("", Collections.nCopies((Integer)this.field_0000.getValue(), "#")), new DecimalFormatSymbols(Locale.ENGLISH))
            .format(var1);
   }

   @Override
   public String method_00166() {
      return "blocks";
   }

   @Override
   public UnidentifiedClass4439 method_00168() {
      return new UnidentifiedClass4439(0, 2, 5);
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.field_0003, (int)field_0004, this.field_0004.method_08912())) {
         return null;
      } else if (field_0000 == (6134596092936980572L & 134234112L) && (Boolean)this.field_0002.getValue()) {
         return null;
      } else {
         return this.field_0001.getValue()
            ? String.format("%." + this.field_0000.getValue() + "f", field_0004)
            : new DecimalFormat("#." + String.join("", Collections.nCopies((Integer)this.field_0000.getValue(), "#")), new DecimalFormatSymbols(Locale.ENGLISH))
               .format(field_0004);
      }
   }

   @Override
   public void method_09625() {
      field_0004 = 0.0;
   }

   public ReachDisplayModule() {
      super("Reach Display", "[2.8 blocks]");
      this.method_28821("Displays how far you hit an entity.");
      this.method_28829("dewgs");
   }
}
