package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;

public class GuiAnimationSettingsOF extends GuiScreen {
   public String title;
   public static GameSettings.Options[] enumOptions = new GameSettings.Options[]{
      GameSettings.Options.ANIMATED_WATER,
      GameSettings.Options.ANIMATED_LAVA,
      GameSettings.Options.ANIMATED_FIRE,
      GameSettings.Options.ANIMATED_PORTAL,
      GameSettings.Options.ANIMATED_REDSTONE,
      GameSettings.Options.ANIMATED_EXPLOSION,
      GameSettings.Options.ANIMATED_FLAME,
      GameSettings.Options.ANIMATED_SMOKE,
      GameSettings.Options.VOID_PARTICLES,
      GameSettings.Options.WATER_PARTICLES,
      GameSettings.Options.RAIN_SPLASH,
      GameSettings.Options.PORTAL_PARTICLES,
      GameSettings.Options.POTION_PARTICLES,
      GameSettings.Options.DRIPPING_WATER_LAVA,
      GameSettings.Options.ANIMATED_TERRAIN,
      GameSettings.Options.ANIMATED_TEXTURES,
      GameSettings.Options.FIREWORK_PARTICLES,
      GameSettings.Options.PARTICLES
   };
   public GameSettings settings;
   public GuiScreen prevScreen;

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k < 200 && var1 instanceof GuiOptionButton) {
            this.settings.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.j = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(var1.k));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.prevScreen);
         }

         if (var1.k == 210) {
            this.j.gameSettings.setAllAnimations(true);
         }

         if (var1.k == 211) {
            this.j.gameSettings.setAllAnimations(false);
         }

         ScaledResolution var2 = new ScaledResolution(this.j);
         this.setWorldAndResolution(this.j, var2.getScaledWidth(), var2.getScaledHeight());
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.title, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public GuiAnimationSettingsOF(GuiScreen var1, GameSettings var2) {
      this.prevScreen = var1;
      this.settings = var2;
   }

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.animationsTitle");
      this.n.clear();

      for (int var1 = 0; var1 < enumOptions.length; var1++) {
         GameSettings.Options var2 = enumOptions[var1];
         int var3 = this.l / 2 - 155 + var1 % 2 * 160;
         int var4 = this.m / 6 + 21 * (var1 / 2) - 12;
         if (!var2.getEnumFloat()) {
            this.n.add(new GuiOptionButtonOF(var2.returnEnumOrdinal(), var3, var4, var2, this.settings.getKeyBinding(var2)));
         } else {
            this.n.add(new GuiOptionSliderOF(var2.returnEnumOrdinal(), var3, var4, var2));
         }
      }

      this.n.add(new GuiButton(210, this.l / 2 - 155, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOn")));
      this.n.add(new GuiButton(211, this.l / 2 - 155 + 80, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOff")));
      this.n.add(new GuiOptionButton(200, this.l / 2 + 5, this.m / 6 + 168 + 11, I18n.format("gui.done")));
   }
}
