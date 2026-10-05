package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.mainmenu.LogoVanillaMainMenu;
import com.cheatbreaker.client.ui.mainmenu.MainMenuMode$EnumSwitch;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import net.minecraft.client.gui.GuiScreen;

public enum MainMenuMode {
      OLD,
      MAIN,
      VANILLA;

   public static MainMenuMode[] recoveredField2820 = new MainMenuMode[]{OLD, MAIN, VANILLA};

   public static GuiScreen method_22258(MainMenuMode var0) {
      switch (MainMenuMode$EnumSwitch.recoveredField3945[var0.ordinal()]) {
         case 1:
            return new LogoVanillaMainMenu();
         case 2:
            return new LegacyMainMenu();
         default:
            return new MainMenu();
      }
   }

   public static MainMenuMode method_22257(String var0) {
      return Enum.valueOf(MainMenuMode.class, var0);
   }
}
