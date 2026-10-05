package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockModule extends TextHudModule {
   public Setting recoveredField694;
   public Setting recoveredField695;
   public Setting recoveredField696;

   @Override
   public void method_01862() {
      this.recoveredField694 = new Setting(this, "Military (24 hour) time", "Show the clock time in military time format.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField695 = new Setting(this, "Show Meridiem (AM/PM) Marker", "Show the AM/PM marker.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> !(Boolean)this.recoveredField694.getValue());
      this.recoveredField696 = new Setting(this, "Show Seconds", "Displays the seconds.").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
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
      String var1 = !this.recoveredField694.method_08908() ? "h" : "HH";
      String var2 = ":mm";
      String var3 = this.recoveredField696.method_08908() ? ":ss" : "";
      String var4 = this.recoveredField695.method_08908() ? (!this.recoveredField694.method_08908() ? " a" : "") : "";
      return new SimpleDateFormat(var1 + var2 + var3 + var4).format(new Date());
   }
}
