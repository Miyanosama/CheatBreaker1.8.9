package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.util.input.ToggleSprintMovementInput;
import com.cheatbreaker.client.ui.module.HudSizeDefaults;

public class ToggleSprintModule extends TextHudModule {
   public static Setting recoveredField3616;
   public Setting recoveredField3617;
   public Setting recoveredField3618;
   public static Setting recoveredField3619;
   public static Setting recoveredField3620;
   public Setting recoveredField3621;
   public Setting recoveredField3622;
   public static Setting recoveredField3623;
   public Setting recoveredField3624;
   public Setting recoveredField3625;
   public Setting recoveredField3626;
   public static boolean recoveredField3627 = false;
   public Setting recoveredField3628;
   public static Setting recoveredField3629;
   public static Setting recoveredField3630;
   public Setting recoveredField3631;
   public Setting recoveredField3632;
   public Setting recoveredField3633;

   @Override
   public void method_00165() {
      new Setting(this, "label").setValue("Toggle Sprint/Sneak Options").method_08914(SettingsDetailLevel.MEDIUM);
      recoveredField3620 = new Setting(this, "Toggle Sprint", "Makes sprinting toggleable instead of held down.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField3629 = new Setting(this, "Toggle Sneak", "Makes sneaking toggleable instead of held down.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField3616 = new Setting(this, "Sneak In Container", "Allows sneaking in containers.")
         .setValue(false)
         .method_08894(() -> recoveredField3629.method_08908())
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField3619 = new Setting(this, "Double Tap", "Determine if double tapping the moving forward key should make the player start sprinting.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3617 = new Setting(this, "label").setValue("Fly Boost");
      recoveredField3630 = new Setting(this, "Fly Boost", "Determine if pressing the sprint key should boost the fly speed.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField3623 = new Setting(this, "Fly Boost Amount", "Change the boost amount.")
         .setValue(4.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("x")
         .method_08894(() -> (Boolean)recoveredField3630.getValue())
         .method_08914(SettingsDetailLevel.SIMPLE);
   }

   @Override
   public void method_01862() {
      this.recoveredField3632 = new Setting(this, "Fly Boost String").setValue("Flying (%BOOST%x boost)").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3626 = new Setting(this, "Fly String").setValue("Flying").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3633 = new Setting(this, "Riding String").setValue("Riding").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3621 = new Setting(this, "Descend String").setValue("Descending").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3622 = new Setting(this, "Dismount String").setValue("Dismounting").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3628 = new Setting(this, "Sneaking String").setValue("Sneaking (Key Held)").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3631 = new Setting(this, "Sprinting Held String")
         .setValue("Sprinting (Key Held)")
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3618 = new Setting(this, "Sprinting Vanilla String")
         .setValue("Sprinting (Vanilla)")
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3625 = new Setting(this, "Sprinting Toggle String")
         .setValue("Sprinting (Toggled)")
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3624 = new Setting(this, "Sneaking Toggle String").setValue("Sneaking (Toggled)").method_08914(SettingsDetailLevel.ADVANCED);
   }

   @Override
   public String method_00166() {
      return "Sprint Status";
   }

   @Override
   public HudSizeDefaults method_08395() {
      return new HudSizeDefaults(10.0F, 13.0F, 24.0F, 40.0F, 56.0F, 120.0F);
   }

   @Override
   public String method_00164() {
      return this.recoveredField3625.getValue().toString();
   }

   @Override
   public String method_00167() {
      return !ToggleSprintMovementInput.recoveredField3291.isEmpty() ? ToggleSprintMovementInput.recoveredField3291 : null;
   }

   public ToggleSprintModule() {
      super("ToggleSprint", "[Sprinting (Toggled)]", 1.0F, false, false);
      this.setDefaultState(false);
      this.recoveredField3912 = false;
      this.method_28821("Makes your sprint and sneak keys toggleable.");
      this.method_28829("deeznueces");
      this.method_28823("Sneak in containers is disabled.", "hypixel");
   }
}
