package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import java.util.regex.Pattern;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.command.CommandXP;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

public class PackDisplayModule extends IconTextHudModule {
   public Setting field_0010;
   public CommandXP field_0005;
   public Pattern field_0001 = Pattern.compile("(?i)§[0-9A-F]");
   public Setting field_0007;
   public Setting field_0009;
   public Setting field_0008;
   public Setting field_0004;
   public int field_0003;
   public Pattern field_0002 = Pattern.compile("(?i)§[1-69A-E]");
   public Setting field_0006;
   public int field_0000;

   public void method_01863(TickEvent var1) {
      this.field_0003++;
      if (this.field_0006.getValue().equals("Rotate") && this.field_0003 > this.field_0007.method_08905() * 20.0F) {
         int var2 = Config.getResourcePackNames().split(",").length - 1;
         String var3 = this.field_0009.method_08874();
         switch (var3) {
            case "Top-Bottom":
               this.field_0000--;
               if (this.field_0000 < 0) {
                  this.field_0000 = var2;
               }
               break;
            case "Bottom-Top":
               this.field_0000++;
               if (this.field_0000 > var2) {
                  this.field_0000 = 0;
               }
               break;
            case "Randomized":
               this.field_0000 = (int)(Math.random() * (var2 + 1));
         }

         SimpleReloadableResourceManager.method_01482();
         this.field_0003 = 0;
      }
   }

   public String method_01866(String var1) {
      return var1 == null ? null : (this.field_0008.getValue().equals("ALL") ? this.field_0001 : this.field_0002).matcher(var1).replaceAll("§r");
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0006 = new Setting(this, "Pack Order")
         .setValue("Bottom")
         .acceptedValues("Bottom", "Top", "Rotate")
         .method_08894(() -> Config.getResourcePackNames().split(",").length != 1)
         .onChange(var0 -> SimpleReloadableResourceManager.method_01482());
      this.field_0009 = new Setting(this, "Rotation Order")
         .setValue("Top-Bottom")
         .acceptedValues("Top-Bottom", "Bottom-Top", "Randomized")
         .method_08894(() -> this.field_0006.getValue().equals("Rotate") && Config.getResourcePackNames().split(",").length != 1);
      this.field_0007 = new Setting(this, "Rotation Interval")
         .setValue(10.0F)
         .setMinMax(1.0F, 60.0F)
         .method_08892("s")
         .method_08894(() -> this.field_0006.getValue().equals("Rotate") && Config.getResourcePackNames().split(",").length != 1);
   }

   @Override
   public void method_01862() {
      this.field_0010 = new Setting(this, "Remove zip string", "Removes the \".zip\" part of a pack name if the file is in a zip.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0004 = new Setting(this, "Remove prefixes", "Removes ! and # prefixes from a pack.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0008 = new Setting(this, "Strip Color Formatting", "Remove all color formatting so you can set your own color.")
         .setValue("OFF")
         .acceptedValues("OFF", "Not Neutral", "ALL")
         .method_08914(SettingsDetailLevel.field_0003);
   }

   public PackDisplayModule() {
      super("Pack Display", "Default");
      this.field_0003 = 0;
      this.field_0000 = 0;
      this.method_28821("Displays your active Resource Pack(s).");
      this.method_28829("canelex");
      this.method_28820(TickEvent.class, this::method_01863);
   }

   @Override
   public String method_00166() {
      return "Pack";
   }

   @Override
   public ResourceLocation method_01868() {
      return CheatBreaker.field_0058;
   }

   @Override
   public String method_00167() {
      String[] var1 = Config.getResourcePackNames().split(",");
      String var2 = var1[var1.length - 1];
      if (this.field_0006.getValue().equals("Bottom")) {
         var2 = var1[0];
      } else if (this.field_0006.getValue().equals("Rotate")) {
         var2 = var1[this.field_0000];
      }

      if (this.field_0010.method_08908()) {
         var2 = var2.replace(".zip", "");
      }

      if (this.field_0004.method_08908()) {
         var2 = var2.substring(var2.indexOf("!") + 1).substring(var2.indexOf("#") + 1).trim();
         if (var2.substring(0, 1).contains("§") && var2.substring(2, 3).contains(" ")) {
            var2 = var2.replaceFirst(" ", "");
         }
      }

      return !this.field_0008.getValue().equals("OFF") ? this.method_01866(var2) : var2;
   }
}
