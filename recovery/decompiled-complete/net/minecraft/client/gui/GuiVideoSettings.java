package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.src.Config;
import net.minecraft.world.WorldServer$1;
import net.optifine.Lang;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.gui.GuiShaders;

public class GuiVideoSettings extends GuiScreenOF {
   public TooltipManager tooltipManager;
   public GuiScreen parentGuiScreen;
   public GameSettings guiGameSettings;
   public WorldServer$1 field_0004;
   public String screenTitle = "Video Settings";
   public static GameSettings$Options[] videoOptions = new GameSettings$Options[]{
      GameSettings$Options.GRAPHICS,
      GameSettings$Options.RENDER_DISTANCE,
      GameSettings$Options.AMBIENT_OCCLUSION,
      GameSettings$Options.FRAMERATE_LIMIT,
      GameSettings$Options.AO_LEVEL,
      GameSettings$Options.VIEW_BOBBING,
      GameSettings$Options.GUI_SCALE,
      GameSettings$Options.USE_VBO,
      GameSettings$Options.GAMMA,
      GameSettings$Options.BLOCK_ALTERNATIVES,
      GameSettings$Options.DYNAMIC_LIGHTS,
      GameSettings$Options.DYNAMIC_FOV
   };

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("options.videoTitle");
      this.n.clear();

      for (int var1 = 0; var1 < videoOptions.length; var1++) {
         GameSettings$Options var2 = videoOptions[var1];
         if (var2 != null) {
            int var3 = this.l / 2 - 155 + var1 % 2 * 160;
            int var4 = this.m / 6 + 21 * (var1 / 2) - 12;
            if (var2.getEnumFloat()) {
               this.n.add(new GuiOptionSliderOF(var2.returnEnumOrdinal(), var3, var4, var2));
            } else {
               this.n.add(new GuiOptionButtonOF(var2.returnEnumOrdinal(), var3, var4, var2, this.guiGameSettings.getKeyBinding(var2)));
            }
         }
      }

      int var5 = this.m / 6 + 21 * (videoOptions.length / 2) - 12;
      int var9 = 0;
      var9 = this.l / 2 - 155 + 0;
      this.n.add(new GuiOptionButton(231, var9, var5, Lang.get("of.options.shaders")));
      var9 = this.l / 2 - 155 + 160;
      this.n.add(new GuiOptionButton(202, var9, var5, Lang.get("of.options.quality")));
      var5 += 21;
      var9 = this.l / 2 - 155 + 0;
      this.n.add(new GuiOptionButton(201, var9, var5, Lang.get("of.options.details")));
      var9 = this.l / 2 - 155 + 160;
      this.n.add(new GuiOptionButton(212, var9, var5, Lang.get("of.options.performance")));
      var5 += 21;
      var9 = this.l / 2 - 155 + 0;
      this.n.add(new GuiOptionButton(211, var9, var5, Lang.get("of.options.animations")));
      var9 = this.l / 2 - 155 + 160;
      this.n.add(new GuiOptionButton(222, var9, var5, Lang.get("of.options.other")));
      var5 += 21;
      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 168 + 11, I18n.format("gui.done")));
   }

   @Override
   public void actionPerformedRightClick(GuiButton var1) {
      if (var1.k == GameSettings$Options.GUI_SCALE.ordinal()) {
         this.actionPerformed(var1, -1);
      }
   }

   public static int method_07297(GuiButton var0) {
      return var0.f;
   }

   public static String getGuiChatText(GuiChat var0) {
      return var0.inputField.getText();
   }

   public static void drawGradientRect(GuiScreen var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      var0.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   public GuiVideoSettings(GuiScreen var1, GameSettings var2) {
      this.tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
      this.parentGuiScreen = var1;
      this.guiGameSettings = var2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.screenTitle, this.l / 2, 15, 16777215);
      String var4 = Config.getVersion();
      String var5 = "HD_U";
      if (var5.equals("HD")) {
         var4 = "OptiFine HD M6_pre2";
      }

      if (var5.equals("HD_U")) {
         var4 = "OptiFine HD M6_pre2 Ultra";
      }

      if (var5.equals("L")) {
         var4 = "OptiFine M6_pre2 Light";
      }

      this.drawString(this.q, var4, 2, this.m - 10, 8421504);
      String var6 = "Minecraft 1.8.9";
      int var7 = this.q.getStringWidth(var6);
      this.drawString(this.q, var6, this.l - var7 - 2, this.m - 10, 8421504);
      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   public static int getButtonHeight(GuiButton var0) {
      return var0.height;
   }

   public void actionPerformed(GuiButton var1, int var2) {
      if (var1.l) {
         int var3 = this.guiGameSettings.guiScale;
         if (var1.k < 200 && var1 instanceof GuiOptionButton) {
            this.guiGameSettings.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), var2);
            var1.j = this.guiGameSettings.getKeyBinding(GameSettings$Options.getEnumOptions(var1.k));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.parentGuiScreen);
         }

         if (this.guiGameSettings.guiScale != var3) {
            ScaledResolution var4 = new ScaledResolution(this.j);
            int var5 = var4.getScaledWidth();
            int var6 = var4.getScaledHeight();
            this.setWorldAndResolution(this.j, var5, var6);
         }

         if (var1.k == 201) {
            this.j.gameSettings.saveOptions();
            GuiDetailSettingsOF var7 = new GuiDetailSettingsOF(this, this.guiGameSettings);
            this.j.displayGuiScreen(var7);
         }

         if (var1.k == 202) {
            this.j.gameSettings.saveOptions();
            GuiQualitySettingsOF var8 = new GuiQualitySettingsOF(this, this.guiGameSettings);
            this.j.displayGuiScreen(var8);
         }

         if (var1.k == 211) {
            this.j.gameSettings.saveOptions();
            GuiAnimationSettingsOF var9 = new GuiAnimationSettingsOF(this, this.guiGameSettings);
            this.j.displayGuiScreen(var9);
         }

         if (var1.k == 212) {
            this.j.gameSettings.saveOptions();
            GuiPerformanceSettingsOF var10 = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
            this.j.displayGuiScreen(var10);
         }

         if (var1.k == 222) {
            this.j.gameSettings.saveOptions();
            GuiOtherSettingsOF var11 = new GuiOtherSettingsOF(this, this.guiGameSettings);
            this.j.displayGuiScreen(var11);
         }

         if (var1.k == 231) {
            if (Config.isAntialiasing() || Config.isAntialiasingConfigured()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
               return;
            }

            if (Config.isAnisotropicFiltering()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
               return;
            }

            if (Config.isFastRender()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
               return;
            }

            if (Config.getGameSettings().anaglyph) {
               Config.showGuiMessage(Lang.get("of.message.shaders.an1"), Lang.get("of.message.shaders.an2"));
               return;
            }

            this.j.gameSettings.saveOptions();
            GuiShaders var12 = new GuiShaders(this, this.guiGameSettings);
            this.j.displayGuiScreen(var12);
         }
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      this.actionPerformed(var1, 1);
   }
}
