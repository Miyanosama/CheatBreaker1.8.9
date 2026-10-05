package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.EntityRenderer$1;
import net.optifine.util.MathUtilsTest$OPER;
import recovered.unidentified.UnidentifiedClass0189;

public class ClockModule extends TextHudModule {
   public Setting field_0002;
   public UnidentifiedClass0189 field_0006;
   public EntityOtherPlayerMP field_0000;
   public Setting field_0003;
   public MathUtilsTest$OPER field_0005;
   public EntityRenderer$1 field_0001;
   public Setting field_0004;

   @Override
   public void method_01862() {
      this.field_0002 = new Setting(this, "Military (24 hour) time", "Show the clock time in military time format.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(this, "Show Meridiem (AM/PM) Marker", "Show the AM/PM marker.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0004 = new Setting(this, "Show Seconds", "Displays the seconds.").setValue(false).method_08914(SettingsDetailLevel.field_0003);
   }

   public ClockModule() {
      super("Clock", "[11:11 AM]");
      this.method_28821("Displays the current time.");
   }

   @Override
   public String method_00166() {
      return "Time";
   }

   @Override
   public String method_00167() {
      String var1 = !this.field_0002.method_08908() ? "h" : "HH";
      String var2 = ":mm";
      String var3 = this.field_0004.method_08908() ? ":ss" : "";
      String var4 = this.field_0003.method_08908() ? (!this.field_0002.method_08908() ? " a" : "") : "";
      return new SimpleDateFormat(var1 + var2 + var3 + var4).format(new Date());
   }
}
