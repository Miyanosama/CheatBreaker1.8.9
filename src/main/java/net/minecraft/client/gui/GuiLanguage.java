package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;

public class GuiLanguage extends GuiScreen {
   public GuiScreen parentScreen;
   public GuiLanguage.List list;
   public GuiOptionButton confirmSettingsBtn;
   public LanguageManager languageManager;
   public GuiOptionButton forceUnicodeFontBtn;
   public GameSettings game_settings_3;

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
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
                  var1.j = this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
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
               GameSettings.Options.FORCE_UNICODE_FONT,
               this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT)
            )
         );
      this.n.add(this.confirmSettingsBtn = new GuiOptionButton(6, this.l / 2 - 155 + 160, this.m - 38, I18n.format("gui.done")));
      this.list = new GuiLanguage.List(this.j);
      this.list.registerScrollButtons(7, 8);
   }

   public GuiLanguage(GuiScreen var1, GameSettings var2, LanguageManager var3) {
      this.parentScreen = var1;
      this.game_settings_3 = var2;
      this.languageManager = var3;
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   public class List extends GuiSlot {
      public Map<String, Language> languageMap;
      public java.util.List<String> langCodeList = Lists.newArrayList();

      @Override
      public boolean isSelected(int var1) {
         return this.langCodeList.get(var1).equals(GuiLanguage.this.languageManager.getCurrentLanguage().getLanguageCode());
      }

      @Override
      public void drawBackground() {
         GuiLanguage.this.drawDefaultBackground();
      }

      public List(Minecraft var2) {
         super(var2, GuiLanguage.this.l, GuiLanguage.this.m, 32, GuiLanguage.this.m - 65 + 4, 18);
         this.languageMap = Maps.newHashMap();

         for (Language var4 : GuiLanguage.this.languageManager.getLanguages()) {
            this.languageMap.put(var4.getLanguageCode(), var4);
            this.langCodeList.add(var4.getLanguageCode());
         }
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
         Language var5 = this.languageMap.get(this.langCodeList.get(var1));
         GuiLanguage.this.languageManager.setCurrentLanguage(var5);
         GuiLanguage.this.game_settings_3.language = var5.getLanguageCode();
         this.a.refreshResources();
         GuiLanguage.this.q.setUnicodeFlag(GuiLanguage.this.languageManager.isCurrentLocaleUnicode() || GuiLanguage.this.game_settings_3.forceUnicodeFont);
         GuiLanguage.this.q.setBidiFlag(GuiLanguage.this.languageManager.isCurrentLanguageBidirectional());
         GuiLanguage.this.confirmSettingsBtn.j = I18n.format("gui.done");
         GuiLanguage.this.forceUnicodeFontBtn.j = GuiLanguage.this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
         GuiLanguage.this.game_settings_3.saveOptions();
      }

      @Override
      public int getContentHeight() {
         return this.getSize() * 18;
      }

      @Override
      public int getSize() {
         return this.langCodeList.size();
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         GuiLanguage.this.q.setBidiFlag(true);
         GuiLanguage.this.drawCenteredString(GuiLanguage.this.q, this.languageMap.get(this.langCodeList.get(var1)).toString(), this.b / 2, var3 + 1, 16777215);
         GuiLanguage.this.q.setBidiFlag(GuiLanguage.this.languageManager.getCurrentLanguage().isBidirectional());
      }
   }
}
