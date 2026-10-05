package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.util.MessageSerializer2;
import net.minecraft.util.ResourceLocation;
import net.optifine.expr.ParametersVariable;

public class EnchantmentGlintModule extends AbstractModule {
   public Setting field_0008;
   public Setting field_0010;
   public MessageSerializer2 field_0002;
   public Setting field_0003;
   public Setting field_0014;
   public Setting field_0011;
   public Setting field_0016;
   public Setting field_0013;
   public Setting field_0000;
   public Setting field_0006;
   public Setting field_0009;
   public Setting field_0012;
   public Setting field_0001;
   public Setting field_0007;
   public Setting field_0005;
   public Setting field_0015;
   public ParametersVariable field_0004;

   public EnchantmentGlintModule() {
      super("Enchantment Glint");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("Glint Options");
      this.field_0003 = new Setting(this, "Inventory Glint").setValue(true);
      this.field_0005 = new Setting(this, "Dropped Item Glint").setValue(true);
      this.field_0001 = new Setting(this, "Thrown Item Glint").setValue(true);
      this.field_0010 = new Setting(this, "Held Item Glint").setValue(true);
      this.field_0008 = new Setting(this, "Armor Glint").setValue(true);
      new Setting(this, "label").setValue("Potion Options").method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0009 = new Setting(this, "Show Potion Color (Inventory)").setValue(false).method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0012 = new Setting(this, "Only Render Potion Glint (Inventory)")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0016 = new Setting(this, "Exclude Potion Glint (Inventory)")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0007 = new Setting(this, "Shiny Pots", "Show a glint box around potions.")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0006 = new Setting(this, "Shiny Pots Boundary")
         .setValue("Entire Slot")
         .acceptedValues("Entire Slot", "To Border")
         .method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()) && Boolean.valueOf(this.field_0007.method_08908()));
      new Setting(this, "label")
         .setValue("Color Options")
         .method_08894(
            () -> Boolean.valueOf(this.field_0003.method_08908())
               || Boolean.valueOf(this.field_0005.method_08908())
               || this.field_0001.method_08908()
               || Boolean.valueOf(this.field_0010.method_08908())
               || Boolean.valueOf(this.field_0008.method_08908())
         );
      this.field_0014 = new Setting(this, "Inventory Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.field_0003.method_08908()));
      this.field_0015 = new Setting(this, "Dropped Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.field_0005.method_08908()));
      this.field_0011 = new Setting(this, "Thrown Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.field_0001.method_08908()));
      this.field_0013 = new Setting(this, "Held Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.field_0010.method_08908()));
      this.field_0000 = new Setting(this, "Armor Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.field_0008.method_08908()));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/ench_sword.png"), 32, 32);
      this.method_28821("Customize the enchantment glint and/or enable shiny pots overlay.");
   }
}
