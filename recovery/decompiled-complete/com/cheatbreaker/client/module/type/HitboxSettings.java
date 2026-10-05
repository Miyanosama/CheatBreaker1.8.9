package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchMappingsTask;
import net.minecraft.item.crafting.CraftingManager;

public class HitboxSettings {
   public Setting field_0006;
   public Setting field_0011;
   public Setting field_0005;
   public CraftingManager field_0010;
   public Setting field_0001;
   public Setting field_0002;
   public Setting field_0012;
   public Setting field_0009;
   public Setting field_0003;
   public Setting field_0013;
   public Setting field_0000;
   public Setting field_0007;
   public Setting field_0008;
   public ConcurrentHashMapV8$SearchMappingsTask field_0004;

   public HitboxSettings(AbstractModule var1, String var2) {
      new Setting(var1, "label").setValue(var2 + " Hitbox Options");
      boolean var3 = var2.equals("Player") || var2.equals("Mob");
      this.field_0002 = new Setting(var1, "Show " + var2 + " Hitbox").setValue(var3);
      this.field_0008 = new Setting(var1, "Show " + var2 + " Hitbox Outline")
         .setValue(true)
         .method_08894(() -> this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0009 = new Setting(var1, "Show " + var2 + " Eye Height")
         .setValue(var3)
         .method_08894(() -> this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0001 = new Setting(var1, "Show " + var2 + " Look Vector")
         .setValue(var3)
         .method_08894(() -> this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      if (var2.equals("Player")) {
         this.field_0013 = new Setting(var1, "Show Own Hitbox")
            .setValue(false)
            .method_08894(() -> this.field_0002.method_08908())
            .method_08914(SettingsDetailLevel.field_0003);
      }

      this.field_0006 = new Setting(var1, "Show " + var2 + " Dashed Line")
         .setValue(false)
         .method_08894(() -> this.field_0002.method_08908() && this.field_0008.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0000 = new Setting(var1, var2 + " Line Distance")
         .setValue(10)
         .setMinMax(0, 20)
         .method_08892("px")
         .method_08894(() -> this.field_0002.method_08908() && this.field_0008.method_08908() && this.field_0006.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(var1, var2 + " Line Thickness")
         .setValue(2)
         .setMinMax(1, 5)
         .method_08892("px")
         .method_08894(() -> this.field_0002.method_08908() && this.field_0008.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      if (var2.equals("Player")) {
         this.field_0007 = new Setting(var1, "Own Hitbox Outline Color")
            .setValue(-1)
            .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .method_08894(() -> this.field_0008.method_08908() && this.field_0013.method_08908() && this.field_0002.method_08908())
            .method_08914(SettingsDetailLevel.field_0003);
      }

      this.field_0011 = new Setting(var1, var2 + " Outline Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.field_0008.method_08908() && this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0005 = new Setting(var1, var2 + " Eye Height Color")
         .setValue(-65536)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.field_0009.method_08908() && this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0012 = new Setting(var1, var2 + " Look Vector Color")
         .setValue(-16776961)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.field_0001.method_08908() && this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
   }
}
