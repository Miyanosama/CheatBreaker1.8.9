package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import io.netty.util.ThreadDeathWatcher;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSoup;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class PotionCounterModule extends NumberHudModule {
   public Setting field_0001;
   public Setting field_0000;
   public ThreadDeathWatcher field_0002;
   public S2DPacketOpenWindow field_0003;

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0001 = new Setting(this, "Hide When Empty", "Hide the mod when there are none of the selected counter item in your inventory.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0000 = new Setting(this, "Counter", "Count Healing potions or Soup.")
         .setValue("Pots")
         .acceptedValues("Pots", "Soup")
         .method_08914(SettingsDetailLevel.field_0000);
   }

   public PotionCounterModule() {
      super("Potion Counter", "[10 Pots]");
      this.method_28821("Displays the amount of healing potions or bowls of soup in your inventory.");
      this.method_28829("Maxwell");
   }

   @Override
   public String method_00167() {
      if (this.method_09815(this.field_0003, this.method_28962(), this.field_0004.method_08912())) {
         return null;
      } else {
         return this.method_28962() == 0 && this.field_0001.method_08908() ? null : "" + this.method_28962();
      }
   }

   @Override
   public String method_00166() {
      return "" + (this.method_28962() == 1 ? this.field_0000.getValue().toString().replaceAll("s", "") : this.field_0000.getValue().toString());
   }

   public int method_28962() {
      EntityPlayerSP var1 = this.minecraft.thePlayer;
      return this.field_0000.getValue().equals("Pots")
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
