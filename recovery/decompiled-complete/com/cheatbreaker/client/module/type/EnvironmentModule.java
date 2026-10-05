package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass0806;
import recovered.unidentified.UnidentifiedClass0877;

public class EnvironmentModule extends AbstractModule {
   public Setting field_0004;
   public Setting field_0006;
   public Setting field_0001;
   public Setting field_0002;
   public Setting field_0010;
   public Date field_0007;
   public Setting field_0011;
   public Setting field_0009;
   public Setting field_0000;
   public Setting field_0003;
   public Setting field_0005;
   public Setting field_0008;

   public void method_20151(UnidentifiedClass0877 var1) {
      if (this.field_0008.method_08874().equalsIgnoreCase("Static")) {
         if (var1.method_05882() == this.field_0001.method_08912()) {
            this.field_0002.setValue(this.field_0002.method_08912() + 100);
         }

         if (var1.method_05882() == this.field_0001.method_08912()) {
            this.field_0002.setValue(this.field_0002.method_08912() - 100);
         }
      }
   }

   public EnvironmentModule() {
      super("Environment Changer");
      this.method_28821("Allows you to change the environment around you to your liking.");
      this.method_28829("Fyu (Time Changer)", "Sk1er (Snow)");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/env-changer.png"), 32, 32);
      new Setting(this, "label").setValue("Time Settings");
      this.field_0008 = new Setting(this, "Time Type")
         .setValue("Server")
         .acceptedValues("Server", "Real Time", "Static")
         .onChange(var1x -> this.method_20137());
      this.field_0002 = new Setting(this, "World Time")
         .setValue(-14490)
         .setMinMax(-22880, -6100)
         .method_08896(false)
         .method_08894(() -> this.field_0008.getValue().equals("Static"))
         .onChange(var1x -> {
            if (this.minecraft.theWorld != null) {
               this.minecraft.theWorld.setWorldTime(Integer.parseInt(var1x.toString()));
            }
         });
      this.field_0009 = new Setting(this, "Time Multiplier Type")
         .setValue("Vanilla")
         .acceptedValues("Vanilla", "Accelerate", "Decelerate")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0008.getValue().equals("Static"));
      this.field_0005 = new Setting(this, "Time Multiplier Delay")
         .method_08892("ms")
         .setValue(300)
         .setMinMax(300, 5000)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> this.field_0008.getValue().equals("Static"));
      this.field_0006 = new Setting(this, "Time Multiplier Amount")
         .setValue(0)
         .setMinMax(0, 10)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0008.getValue().equals("Static"));
      this.field_0004 = new Setting(this, "Increase/Decrease Amount")
         .setValue(100)
         .setMinMax(1, 1000)
         .method_08894(() -> this.field_0008.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0001 = new Setting(this, "Increase Time Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08894(() -> this.field_0008.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0011 = new Setting(this, "Decrease Time Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08894(() -> this.field_0008.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.field_0003);
      new Setting(this, "label").setValue("Weather Settings");
      this.field_0000 = new Setting(this, "Allow Through Blocks").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(this, "Custom Weather").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0010 = new Setting(this, "Current Weather")
         .setValue("Clear")
         .acceptedValues("Clear", "Rain", "Snow")
         .method_08894(() -> this.field_0003.method_08908())
         .method_08914(SettingsDetailLevel.field_0000);
      this.method_28820(TickEvent.class, this::method_20141);
      this.method_28820(UnidentifiedClass0806.class, this::method_20150);
      this.method_28820(UnidentifiedClass0877.class, this::method_20151);
      Runnable var1 = () -> {
         if (this.field_0008.method_08874().equalsIgnoreCase("Static") && this.minecraft.theWorld != null) {
            String var1x = this.field_0009.method_08874();
            if (var1x.equalsIgnoreCase("Vanilla")) {
               return;
            }

            if (var1x.equalsIgnoreCase("Accelerate")) {
               this.minecraft.theWorld.setWorldTime(this.minecraft.theWorld.L() * this.field_0006.method_08912());
            }

            if (var1x.equalsIgnoreCase("Decelerate")) {
               this.minecraft.theWorld.setWorldTime(this.minecraft.theWorld.L() / this.field_0006.method_08912());
            }

            try {
               Thread.sleep(this.field_0005.method_08912());
            } catch (InterruptedException var3) {
               var3.printStackTrace();
            }
         }
      };
      ScheduledExecutorService var2 = Executors.newScheduledThreadPool(1);
      var2.scheduleAtFixedRate(var1, 6986404857444047241L & -6986404857829089279L, 272728225L & 554961671L, TimeUnit.MILLISECONDS);
   }

   public int method_20149() {
      if (this.field_0007 == null) {
         this.field_0007 = new Date();
      }

      this.field_0007.setTime(System.currentTimeMillis());
      int var1 = this.field_0007.getHours() - 6;
      if (var1 <= 0) {
         var1 += 24;
      }

      return (int)(var1 * 1000 + (this.field_0007.getSeconds() + this.field_0007.getMinutes() * 60) / 3.6);
   }

   public boolean method_20153() {
      return this.field_0010.method_08874().equals("Clear") || this.field_0010.method_08874().equals("Snow");
   }

   public void method_20137() {
      if (this.minecraft.theWorld != null) {
         if (this.field_0008.getValue().equals("Real Time")) {
            this.minecraft.theWorld.setWorldTime(this.method_20149());
         } else if (this.field_0008.getValue().equals("Static")) {
            this.minecraft.theWorld.setWorldTime(this.field_0002.method_08912());
         }
      }
   }

   public void method_20141(TickEvent var1) {
      if (this.minecraft.theWorld != null && this.field_0008.getValue().equals("Real Time")) {
         this.minecraft.theWorld.setWorldTime(this.method_20149());
      }
   }

   public void method_20150(UnidentifiedClass0806 var1) {
      long var2 = Integer.parseInt(this.field_0002.getValue().toString()) + this.field_0004.method_08912();
      long var4 = Integer.parseInt(this.field_0002.getValue().toString()) - this.field_0004.method_08912();
      if (this.field_0008.method_08874().equalsIgnoreCase("Static")) {
         if (var1.method_05523() == this.field_0001.method_08912() && this.minecraft.theWorld != null) {
            if (var2 > (-5011L & -5954L)) {
               return;
            }

            this.field_0002.setValue(Integer.parseInt(this.field_0002.getValue().toString()) + this.field_0004.method_08912());
            this.minecraft.theWorld.setWorldTime(var2);
         }

         if (var1.method_05523() == this.field_0011.method_08912() && this.minecraft.theWorld != null) {
            if (var4 < (-6474L & -22807L)) {
               return;
            }

            this.field_0002.setValue(Integer.parseInt(this.field_0002.getValue().toString()) - this.field_0004.method_08912());
            this.minecraft.theWorld.setWorldTime(var4);
         }
      }
   }
}
