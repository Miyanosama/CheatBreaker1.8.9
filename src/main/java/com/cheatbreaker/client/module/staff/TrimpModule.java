package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;

public class TrimpModule extends StaffModule {
   public static Setting recoveredField2796;
   public static Setting recoveredField2797;
   public static Setting recoveredField2798;
   public static Setting recoveredField2799;
   public static Setting recoveredField2800;
   public static Setting recoveredField2801;
   public static Setting recoveredField2802;
   public static Setting recoveredField2803;
   public static Setting recoveredField2804;
   public static Setting recoveredField2805;
   public static Setting recoveredField2806;
   public static Setting recoveredField2807;
   public static Setting recoveredField2808;

   public TrimpModule() {
      super("bunnyhop");
      this.method_28828(true);
      recoveredField2799 = new Setting(this, "Trimp").setValue(true);
      recoveredField2804 = new Setting(this, "Trimp Multiplier").setValue(1.4F).setMinMax(1.0F, 4.0F);
      recoveredField2806 = new Setting(this, "Hard cap").setValue(2.0F).setMinMax(1.0F, 4.0F);
      recoveredField2800 = new Setting(this, "Soft cap").setValue(1.4F).setMinMax(1.0F, 4.0F);
      recoveredField2796 = new Setting(this, "Soft cap degen").setValue(0.65F).setMinMax(0.1F, 4.0F);
      recoveredField2797 = new Setting(this, "Sharking").setValue(true);
      recoveredField2808 = new Setting(this, "Sharking surface tension").setValue(0.2).setMinMax(0.0, 1.0);
      recoveredField2801 = new Setting(this, "Sharking water friction").setValue(0.1).setMinMax(0.0, 1.0);
      recoveredField2802 = new Setting(this, "Accelerate").setValue(20.0).setMinMax(5.0, 100.0);
      recoveredField2803 = new Setting(this, "Air accelerate").setValue(28.0).setMinMax(5.0, 150.0);
      recoveredField2805 = new Setting(this, "Max air accel per tick").setValue(0.095).setMinMax(0.0, 1.0);
      recoveredField2798 = new Setting(this, "Uncapped BunnyHop").setValue(true);
      recoveredField2807 = new Setting(this, "Increased FallDistance").setValue(0.0).setMinMax(0.0, 10.0);
   }

   public static boolean method_21399() {
      return CheatBreaker.getInstance().getModuleManager().recoveredField1711.isStaffEnabledModule()
         && CheatBreaker.getInstance().getModuleManager().recoveredField1711.isEnabled();
   }
}
