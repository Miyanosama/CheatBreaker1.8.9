package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import io.netty.bootstrap.Bootstrap$2;
import net.minecraft.block.BlockNote;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;
import net.minecraft.server.management.ServerConfigurationManager;
import recovered.unidentified.UnidentifiedClass1316;
import recovered.unidentified.UnidentifiedClass1369;
import recovered.unidentified.UnidentifiedClass3786;

public class ToggleSprintModule extends TextHudModule {
   public static Setting field_0010;
   public Setting field_0022;
   public Setting field_0001;
   public static Setting field_0013;
   public UnidentifiedClass3786 field_0018;
   public Bootstrap$2 field_0004;
   public static Setting field_0014;
   public Setting field_0021;
   public Setting field_0012;
   public BlockNote field_0005;
   public ServerConfigurationManager field_0017;
   public static Setting field_0020;
   public Setting field_0019;
   public Setting field_0009;
   public Setting field_0007;
   public static boolean field_0006 = false;
   public Setting field_0015;
   public static Setting field_0000;
   public static Setting field_0002;
   public Setting field_0016;
   public VertexFormatElement$EnumType field_0008;
   public Setting field_0003;
   public Setting field_0011;

   @Override
   public void method_00165() {
      new Setting(this, "label").setValue("Toggle Sprint/Sneak Options").method_08914(SettingsDetailLevel.field_0003);
      field_0014 = new Setting(this, "Toggle Sprint", "Makes sprinting toggleable instead of held down.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0000);
      field_0000 = new Setting(this, "Toggle Sneak", "Makes sneaking toggleable instead of held down.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0000);
      field_0010 = new Setting(this, "Sneak In Container", "Allows sneaking in containers.")
         .setValue(false)
         .method_08894(() -> field_0000.method_08908())
         .method_08914(SettingsDetailLevel.field_0000);
      field_0013 = new Setting(this, "Double Tap", "Determine if double tapping the moving forward key should make the player start sprinting.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0022 = new Setting(this, "label").setValue("Fly Boost");
      field_0002 = new Setting(this, "Fly Boost", "Determine if pressing the sprint key should boost the fly speed.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0000);
      field_0020 = new Setting(this, "Fly Boost Amount", "Change the boost amount.")
         .setValue(4.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("x")
         .method_08894(() -> (Boolean)field_0002.getValue())
         .method_08914(SettingsDetailLevel.field_0000);
   }

   @Override
   public void method_01862() {
      this.field_0003 = new Setting(this, "Fly Boost String").setValue("Flying (%BOOST%x boost)").method_08914(SettingsDetailLevel.field_0001);
      this.field_0007 = new Setting(this, "Fly String").setValue("Flying").method_08914(SettingsDetailLevel.field_0001);
      this.field_0011 = new Setting(this, "Riding String").setValue("Riding").method_08914(SettingsDetailLevel.field_0001);
      this.field_0021 = new Setting(this, "Descend String").setValue("Descending").method_08914(SettingsDetailLevel.field_0001);
      this.field_0012 = new Setting(this, "Dismount String").setValue("Dismounting").method_08914(SettingsDetailLevel.field_0001);
      this.field_0015 = new Setting(this, "Sneaking String").setValue("Sneaking (Key Held)").method_08914(SettingsDetailLevel.field_0001);
      this.field_0016 = new Setting(this, "Sprinting Held String").setValue("Sprinting (Key Held)").method_08914(SettingsDetailLevel.field_0001);
      this.field_0001 = new Setting(this, "Sprinting Vanilla String").setValue("Sprinting (Vanilla)").method_08914(SettingsDetailLevel.field_0001);
      this.field_0009 = new Setting(this, "Sprinting Toggle String").setValue("Sprinting (Toggled)").method_08914(SettingsDetailLevel.field_0001);
      this.field_0019 = new Setting(this, "Sneaking Toggle String").setValue("Sneaking (Toggled)").method_08914(SettingsDetailLevel.field_0001);
   }

   @Override
   public String method_00166() {
      return "Sprint Status";
   }

   @Override
   public UnidentifiedClass1369 method_08395() {
      return new UnidentifiedClass1369(10.0F, 13.0F, 24.0F, 40.0F, 56.0F, 120.0F);
   }

   @Override
   public String method_00164() {
      return this.field_0009.getValue().toString();
   }

   @Override
   public String method_00167() {
      return !UnidentifiedClass1316.field_0008.isEmpty() ? UnidentifiedClass1316.field_0008 : null;
   }

   public ToggleSprintModule() {
      super("ToggleSprint", "[Sprinting (Toggled)]", 1.0F, false, false);
      this.setDefaultState(false);
      this.field_0020 = false;
      this.method_28821("Makes your sprint and sneak keys toggleable.");
      this.method_28829("deeznueces");
      this.method_28823("Sneak in containers is disabled.", "hypixel");
   }
}
