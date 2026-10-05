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
import com.cheatbreaker.client.event.type.KeyPressEvent;
import com.cheatbreaker.client.event.type.MouseClickEvent;

public class EnvironmentModule extends AbstractModule {
   public Setting recoveredField2027;
   public Setting recoveredField2028;
   public Setting recoveredField2029;
   public Setting recoveredField2030;
   public Setting recoveredField2031;
   public Date recoveredField2032;
   public Setting recoveredField2033;
   public Setting recoveredField2034;
   public Setting recoveredField2035;
   public Setting recoveredField2036;
   public Setting recoveredField2037;
   public Setting recoveredField2038;

   public void method_20151(MouseClickEvent var1) {
      if (this.recoveredField2038.method_08874().equalsIgnoreCase("Static")) {
         if (var1.method_05882() == this.recoveredField2029.method_08912()) {
            this.recoveredField2030.setValue(this.recoveredField2030.method_08912() + 100);
         }

         if (var1.method_05882() == this.recoveredField2029.method_08912()) {
            this.recoveredField2030.setValue(this.recoveredField2030.method_08912() - 100);
         }
      }
   }

   public EnvironmentModule() {
      super("Environment Changer");
      this.method_28821("Allows you to change the environment around you to your liking.");
      this.method_28829("Fyu (Time Changer)", "Sk1er (Snow)");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/env-changer.png"), 32, 32);
      new Setting(this, "label").setValue("Time Settings");
      this.recoveredField2038 = new Setting(this, "Time Type")
         .setValue("Server")
         .acceptedValues("Server", "Real Time", "Static")
         .onChange(var1x -> this.method_20137());
      this.recoveredField2030 = new Setting(this, "World Time")
         .setValue(-14490)
         .setMinMax(-22880, -6100)
         .method_08896(false)
         .method_08894(() -> this.recoveredField2038.getValue().equals("Static"))
         .onChange(var1x -> {
            if (this.minecraft.theWorld != null) {
               this.minecraft.theWorld.setWorldTime(Integer.parseInt(var1x.toString()));
            }
         });
      this.recoveredField2034 = new Setting(this, "Time Multiplier Type")
         .setValue("Vanilla")
         .acceptedValues("Vanilla", "Accelerate", "Decelerate")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2038.getValue().equals("Static"));
      this.recoveredField2037 = new Setting(this, "Time Multiplier Delay")
         .method_08892("ms")
         .setValue(300)
         .setMinMax(300, 5000)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> this.recoveredField2038.getValue().equals("Static"));
      this.recoveredField2028 = new Setting(this, "Time Multiplier Amount")
         .setValue(0)
         .setMinMax(0, 10)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2038.getValue().equals("Static"));
      this.recoveredField2027 = new Setting(this, "Increase/Decrease Amount")
         .setValue(100)
         .setMinMax(1, 1000)
         .method_08894(() -> this.recoveredField2038.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2029 = new Setting(this, "Increase Time Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08894(() -> this.recoveredField2038.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2033 = new Setting(this, "Decrease Time Keybind")
         .setValue(0)
         .method_08909(false)
         .method_08894(() -> this.recoveredField2038.method_08874().equalsIgnoreCase("Static"))
         .method_08914(SettingsDetailLevel.MEDIUM);
      new Setting(this, "label").setValue("Weather Settings");
      this.recoveredField2035 = new Setting(this, "Allow Through Blocks").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2036 = new Setting(this, "Custom Weather").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2031 = new Setting(this, "Current Weather")
         .setValue("Clear")
         .acceptedValues("Clear", "Rain", "Snow")
         .method_08894(() -> this.recoveredField2036.method_08908())
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.method_28820(TickEvent.class, this::method_20141);
      this.method_28820(KeyPressEvent.class, this::method_20150);
      this.method_28820(MouseClickEvent.class, this::method_20151);
      Runnable var1 = () -> {
         if (this.recoveredField2038.method_08874().equalsIgnoreCase("Static") && this.minecraft.theWorld != null) {
            String var1x = this.recoveredField2034.method_08874();
            if (var1x.equalsIgnoreCase("Vanilla")) {
               return;
            }

            if (var1x.equalsIgnoreCase("Accelerate")) {
               this.minecraft.theWorld.setWorldTime(this.minecraft.theWorld.L() * this.recoveredField2028.method_08912());
            }

            if (var1x.equalsIgnoreCase("Decelerate")) {
               this.minecraft.theWorld.setWorldTime(this.minecraft.theWorld.L() / this.recoveredField2028.method_08912());
            }

            try {
               Thread.sleep(this.recoveredField2037.method_08912());
            } catch (InterruptedException var3) {
               var3.printStackTrace();
            }
         }
      };
      ScheduledExecutorService var2 = Executors.newScheduledThreadPool(1);
      var2.scheduleAtFixedRate(var1, 1L, 1L, TimeUnit.MILLISECONDS);
   }

   public int method_20149() {
      if (this.recoveredField2032 == null) {
         this.recoveredField2032 = new Date();
      }

      this.recoveredField2032.setTime(System.currentTimeMillis());
      int var1 = this.recoveredField2032.getHours() - 6;
      if (var1 <= 0) {
         var1 += 24;
      }

      return (int)(var1 * 1000 + (this.recoveredField2032.getSeconds() + this.recoveredField2032.getMinutes() * 60) / 3.6);
   }

   public boolean method_20153() {
      return this.recoveredField2031.method_08874().equals("Clear") || this.recoveredField2031.method_08874().equals("Snow");
   }

   public void method_20137() {
      if (this.minecraft.theWorld != null) {
         if ((Boolean)this.recoveredField2038.getValue().equals("Real Time")) {
            this.minecraft.theWorld.setWorldTime(this.method_20149());
         } else if ((Boolean)this.recoveredField2038.getValue().equals("Static")) {
            this.minecraft.theWorld.setWorldTime(this.recoveredField2030.method_08912());
         }
      }
   }

   public void method_20141(TickEvent var1) {
      if (this.minecraft.theWorld != null && (Boolean)this.recoveredField2038.getValue().equals("Real Time")) {
         this.minecraft.theWorld.setWorldTime(this.method_20149());
      }
   }

   public void method_20150(KeyPressEvent var1) {
      long var2 = Integer.parseInt(this.recoveredField2030.getValue().toString()) + this.recoveredField2027.method_08912();
      long var4 = Integer.parseInt(this.recoveredField2030.getValue().toString()) - this.recoveredField2027.method_08912();
      if (this.recoveredField2038.method_08874().equalsIgnoreCase("Static")) {
         if (var1.method_05523() == this.recoveredField2029.method_08912() && this.minecraft.theWorld != null) {
            if (var2 > -6100L) {
               return;
            }

            this.recoveredField2030.setValue(Integer.parseInt(this.recoveredField2030.getValue().toString()) + this.recoveredField2027.method_08912());
            this.minecraft.theWorld.setWorldTime(var2);
         }

         if (var1.method_05523() == this.recoveredField2033.method_08912() && this.minecraft.theWorld != null) {
            if (var4 < -22880L) {
               return;
            }

            this.recoveredField2030.setValue(Integer.parseInt(this.recoveredField2030.getValue().toString()) - this.recoveredField2027.method_08912());
            this.minecraft.theWorld.setWorldTime(var4);
         }
      }
   }
}
