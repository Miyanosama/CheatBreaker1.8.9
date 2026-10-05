package net.minecraft.client;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.server.integrated.IntegratedServer$3;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.village.VillageCollection;

public class Minecraft$1 implements IStatStringFormat {
   public VillageCollection field_0003;
   public IntegratedServer$3 field_0000;
   public Minecraft$18 field_0002;

   public Minecraft$1(Minecraft var1) {
      this.field_74532_a = var1;
      super();
   }

   @Override
   public String formatString(String var1) {
      try {
         return String.format(var1, GameSettings.getKeyDisplayString(this.field_74532_a.gameSettings.keyBindInventory.getKeyCode()));
      } catch (Exception var3) {
         return "Error: " + var3.getLocalizedMessage();
      }
   }
}
