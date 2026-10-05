package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import junit.extensions.ExceptionTestCase;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity$1;
import net.minecraft.network.PacketThreadUtil;
import net.optifine.render.VboRegion;
import recovered.unidentified.UnidentifiedClass1472;
import recovered.unidentified.UnidentifiedClass4439;

public abstract class NumberHudModule extends TextHudModule {
   public ExceptionTestCase field_0002;
   public WrongUsageException field_0007;
   public Entity$1 field_0000;
   public Setting field_0003;
   public UnidentifiedClass1472 field_0005;
   public VboRegion field_0001;
   public Setting field_0004;
   public PacketThreadUtil field_0006;

   public UnidentifiedClass4439 method_00168() {
      return new UnidentifiedClass4439(0, 0, 30);
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
      this.field_0003 = new Setting(this, "Hide when value is").setValue("OFF").acceptedValues(this.field_0031).method_08914(SettingsDetailLevel.field_0003);
      this.field_0004 = new Setting(this, "Hidden value(s)")
         .setValue(this.method_00168().method_26745())
         .setMinMax(this.method_00168().method_26746(), this.method_00168().method_26744())
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !this.field_0003.getValue().equals("OFF"));
   }

   public NumberHudModule(String var1, String var2, float var3, boolean var4, boolean var5) {
      super(var1, var2, var3, var4, var5);
   }
}
