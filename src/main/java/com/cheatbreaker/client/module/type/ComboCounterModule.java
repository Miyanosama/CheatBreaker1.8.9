package com.cheatbreaker.client.module.type;

import java.util.ArrayList;
import java.util.List;

public class ComboCounterModule extends CombatCounterModule {
   public static long recoveredField642;
   public static int recoveredField643;
   public static List<Long> recoveredField645 = new ArrayList<>();
   public static int recoveredField644 = -1;

   @Override
   public String method_00164() {
      return "6";
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.recoveredField2752, recoveredField645.size(), this.recoveredField2753.method_08912())) {
         return null;
      } else {
         return recoveredField1835 == 0L && (Boolean)this.recoveredField1838.getValue() ? null : recoveredField645.size() + "";
      }
   }

   @Override
   public void method_09625() {
      recoveredField645.clear();
   }

   @Override
   public String method_00166() {
      return "Combo";
   }

   public ComboCounterModule() {
      super("Combo Counter", "[6 Combo]");
      this.method_28821("Displays how many times you hit someone in a row within 2 seconds.");
      this.method_28829("Erouax");
   }

   @Override
   public String method_09624() {
      return "No %LABEL%";
   }
}
