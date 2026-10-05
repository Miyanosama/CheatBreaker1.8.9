package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.util.ResourceLocation;

public class EnchantmentGlintModule extends AbstractModule {
   public Setting recoveredField3402;
   public Setting recoveredField3403;
   public Setting recoveredField3404;
   public Setting recoveredField3405;
   public Setting recoveredField3406;
   public Setting recoveredField3407;
   public Setting recoveredField3408;
   public Setting recoveredField3409;
   public Setting recoveredField3410;
   public Setting recoveredField3411;
   public Setting recoveredField3412;
   public Setting recoveredField3413;
   public Setting recoveredField3414;
   public Setting recoveredField3415;
   public Setting recoveredField3416;

   public EnchantmentGlintModule() {
      super("Enchantment Glint");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("Glint Options");
      this.recoveredField3404 = new Setting(this, "Inventory Glint").setValue(true);
      this.recoveredField3415 = new Setting(this, "Dropped Item Glint").setValue(true);
      this.recoveredField3413 = new Setting(this, "Thrown Item Glint").setValue(true);
      this.recoveredField3403 = new Setting(this, "Held Item Glint").setValue(true);
      this.recoveredField3402 = new Setting(this, "Armor Glint").setValue(true);
      new Setting(this, "label").setValue("Potion Options").method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3411 = new Setting(this, "Show Potion Color (Inventory)")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3412 = new Setting(this, "Only Render Potion Glint (Inventory)")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3407 = new Setting(this, "Exclude Potion Glint (Inventory)")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3414 = new Setting(this, "Shiny Pots", "Show a glint box around potions.")
         .setValue(false)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3410 = new Setting(this, "Shiny Pots Boundary")
         .setValue("Entire Slot")
         .acceptedValues("Entire Slot", "To Border")
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()) && Boolean.valueOf(this.recoveredField3414.method_08908()));
      new Setting(this, "label")
         .setValue("Color Options")
         .method_08894(
            () -> Boolean.valueOf(this.recoveredField3404.method_08908())
               || Boolean.valueOf(this.recoveredField3415.method_08908())
               || this.recoveredField3413.method_08908()
               || Boolean.valueOf(this.recoveredField3403.method_08908())
               || Boolean.valueOf(this.recoveredField3402.method_08908())
         );
      this.recoveredField3405 = new Setting(this, "Inventory Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3404.method_08908()));
      this.recoveredField3416 = new Setting(this, "Dropped Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3415.method_08908()));
      this.recoveredField3406 = new Setting(this, "Thrown Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3413.method_08908()));
      this.recoveredField3408 = new Setting(this, "Held Item Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3403.method_08908()));
      this.recoveredField3409 = new Setting(this, "Armor Glint Color")
         .setValue(-8372020)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> Boolean.valueOf(this.recoveredField3402.method_08908()));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/ench_sword.png"), 32, 32);
      this.method_28821("Customize the enchantment glint and/or enable shiny pots overlay.");
   }
}
