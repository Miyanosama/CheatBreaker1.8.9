package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import net.minecraft.world.gen.structure.MapGenStronghold;

public class TrimpModule extends StaffModule {
   public static Setting field_0009;
   public static Setting field_0013;
   public static Setting field_0011;
   public static Setting field_0001;
   public static Setting field_0005;
   public static Setting field_0007;
   public static Setting field_0010;
   public MapGenStronghold field_0002;
   public static Setting field_0006;
   public static Setting field_0004;
   public static Setting field_0012;
   public static Setting field_0003;
   public static Setting field_0008;
   public static Setting field_0000;

   public TrimpModule() {
      super("bunnyhop");
      this.method_28828(true);
      field_0001 = new Setting(this, "Trimp").setValue(true);
      field_0004 = new Setting(this, "Trimp Multiplier").setValue(1.4F).setMinMax(1.0F, 4.0F);
      field_0003 = new Setting(this, "Hard cap").setValue(2.0F).setMinMax(1.0F, 4.0F);
      field_0005 = new Setting(this, "Soft cap").setValue(1.4F).setMinMax(1.0F, 4.0F);
      field_0009 = new Setting(this, "Soft cap degen").setValue(0.65F).setMinMax(0.1F, 4.0F);
      field_0013 = new Setting(this, "Sharking").setValue(true);
      field_0000 = new Setting(this, "Sharking surface tension").setValue(0.2).setMinMax(0.0, 1.0);
      field_0007 = new Setting(this, "Sharking water friction").setValue(0.1).setMinMax(0.0, 1.0);
      field_0010 = new Setting(this, "Accelerate").setValue(20.0).setMinMax(5.0, 100.0);
      field_0006 = new Setting(this, "Air accelerate").setValue(28.0).setMinMax(5.0, 150.0);
      field_0012 = new Setting(this, "Max air accel per tick").setValue(0.095).setMinMax(0.0, 1.0);
      field_0011 = new Setting(this, "Uncapped BunnyHop").setValue(true);
      field_0008 = new Setting(this, "Increased FallDistance").setValue(0.0).setMinMax(0.0, 10.0);
   }

   public static boolean method_21399() {
      return CheatBreaker.getInstance().getModuleManager().field_0011.isStaffEnabledModule()
         && CheatBreaker.getInstance().getModuleManager().field_0011.isEnabled();
   }
}
