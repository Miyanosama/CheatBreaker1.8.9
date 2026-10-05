package net.minecraft.client.gui;

import javazoom.jl.player.advanced.jlap$InfoListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.item.ItemEmptyMap;

public class GuiLanguage extends GuiScreen {
   public GuiScreen parentScreen;
   public GuiLanguage$List list;
   public GuiOptionButton confirmSettingsBtn;
   public LanguageManager languageManager;
   public ItemEmptyMap field_0000;
   public GuiOptionButton forceUnicodeFontBtn;
   public GameSettings game_settings_3;
   public jlap$InfoListener field_0004;

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         switch (var1.k) {
            case 5:
               break;
            case 6:
               this.j.displayGuiScreen(this.parentScreen);
               break;
            case 100:
               if (var1 instanceof GuiOptionButton) {
                  this.game_settings_3.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
                  var1.j = this.game_settings_3.getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT);
                  ScaledResolution var2 = new ScaledResolution(this.j);
                  int var3 = var2.getScaledWidth();
                  int var4 = var2.getScaledHeight();
                  this.setWorldAndResolution(this.j, var3, var4);
               }
               break;
            default:
               this.list.actionPerformed(var1);
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.list.a(var1, var2, var3);
      this.drawCenteredString(this.q, I18n.format("options.language"), this.l / 2, 16, 16777215);
      this.drawCenteredString(this.q, "(" + I18n.format("options.languageWarning") + ")", this.l / 2, this.m - 56, 8421504);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      this.n
         .add(
            this.forceUnicodeFontBtn = new GuiOptionButton(
               100,
               this.l / 2 - 155,
               this.m - 38,
               GameSettings$Options.FORCE_UNICODE_FONT,
               this.game_settings_3.getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT)
            )
         );
      this.n.add(this.confirmSettingsBtn = new GuiOptionButton(6, this.l / 2 - 155 + 160, this.m - 38, I18n.format("gui.done")));
      this.list = new GuiLanguage$List(this, this.j);
      this.list.registerScrollButtons(7, 8);
   }

   public GuiLanguage(GuiScreen var1, GameSettings var2, LanguageManager var3) {
      this.parentScreen = var1;
      this.game_settings_3 = var2;
      this.languageManager = var3;
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }
}
