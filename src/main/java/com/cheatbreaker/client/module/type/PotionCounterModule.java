package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSoup;

public class PotionCounterModule extends NumberHudModule {
   public Setting recoveredField1250;
   public Setting recoveredField1251;

   @Override
   public void method_00165() {
      super.method_00165();
      this.recoveredField1250 = new Setting(this, "Hide When Empty", "Hide the mod when there are none of the selected counter item in your inventory.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField1251 = new Setting(this, "Counter", "Count Healing potions or Soup.")
         .setValue("Pots")
         .acceptedValues("Pots", "Soup")
         .method_08914(SettingsDetailLevel.SIMPLE);
   }

   public PotionCounterModule() {
      super("Potion Counter", "[10 Pots]");
      this.method_28821("Displays the amount of healing potions or bowls of soup in your inventory.");
      this.method_28829("Maxwell");
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.recoveredField2752, this.method_28962(), this.recoveredField2753.method_08912())) {
         return null;
      } else {
         return this.method_28962() == 0 && this.recoveredField1250.method_08908() ? null : "" + this.method_28962();
      }
   }

   @Override
   public String method_00166() {
      return ""
         + (this.method_28962() == 1 ? this.recoveredField1251.getValue().toString().replaceAll("s", "") : this.recoveredField1251.getValue().toString());
   }

   public int method_28962() {
      EntityPlayerSP var1 = this.minecraft.thePlayer;
      return this.recoveredField1251.getValue().equals("Pots")
         ? (int)Arrays.stream(var1.bi.mainInventory)
            .filter(Objects::nonNull)
            .filter(var0 -> var0.getItem() instanceof ItemPotion)
            .filter(var0 -> var0.getItemDamage() == 16421 | var0.getItemDamage() == 16453)
            .count()
         : (int)Arrays.stream(var1.bi.mainInventory).filter(Objects::nonNull).filter(var0 -> var0.getItem() instanceof ItemSoup).count();
   }

   @Override
   public String method_00164() {
      return "10";
   }
}
