package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;
import com.cheatbreaker.client.config.IntegerRangeDefaults;

public class ReachDisplayModule extends CombatCounterModule {
   public Setting recoveredField3447;
   public Setting recoveredField3448;
   public static double recoveredField3449;

   @Override
   public void method_01862() {
      this.recoveredField3448 = new Setting(this, "Show trailing zeros", "Show all trailing zeros.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField3447.method_08912() != 0);
      this.recoveredField3447 = new Setting(this, "Decimals", "Change how many decimals the mod should display.")
         .setValue(1)
         .setMinMax(0, 16)
         .method_08914(SettingsDetailLevel.MEDIUM);
      super.method_01862();
   }

   @Override
   public String method_00164() {
      double var1 = 2.780034105782015;
      return (Boolean)this.recoveredField3448.getValue()
         ? String.format("%." + this.recoveredField3447.getValue() + "f", var1)
         : new DecimalFormat(
               "#." + String.join("", Collections.nCopies((Integer)this.recoveredField3447.getValue(), "#")), new DecimalFormatSymbols(Locale.ENGLISH)
            )
            .format(var1);
   }

   @Override
   public String method_00166() {
      return "blocks";
   }

   @Override
   public IntegerRangeDefaults method_00168() {
      return new IntegerRangeDefaults(0, 2, 5);
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.recoveredField2752, (int)recoveredField3449, this.recoveredField2753.method_08912())) {
         return null;
      } else if (recoveredField1835 == 0L && (Boolean)this.recoveredField1838.getValue()) {
         return null;
      } else {
         return (Boolean)this.recoveredField3448.getValue()
            ? String.format("%." + this.recoveredField3447.getValue() + "f", recoveredField3449)
            : new DecimalFormat(
                  "#." + String.join("", Collections.nCopies((Integer)this.recoveredField3447.getValue(), "#")), new DecimalFormatSymbols(Locale.ENGLISH)
               )
               .format(recoveredField3449);
      }
   }

   @Override
   public void method_09625() {
      recoveredField3449 = 0.0;
   }

   public ReachDisplayModule() {
      super("Reach Display", "[2.8 blocks]");
      this.method_28821("Displays how far you hit an entity.");
      this.method_28829("dewgs");
   }
}
