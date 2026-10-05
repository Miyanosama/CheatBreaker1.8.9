package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class ScreenChatOptions extends GuiScreen {
   public String field_146401_i;
   public GameSettings game_settings;
   public GuiScreen parentScreen;
   public static GameSettings.Options[] field_146399_a = new GameSettings.Options[]{
      GameSettings.Options.CHAT_VISIBILITY,
      GameSettings.Options.CHAT_COLOR,
      GameSettings.Options.CHAT_LINKS,
      GameSettings.Options.CHAT_OPACITY,
      GameSettings.Options.CHAT_LINKS_PROMPT,
      GameSettings.Options.CHAT_SCALE,
      GameSettings.Options.CHAT_HEIGHT_FOCUSED,
      GameSettings.Options.CHAT_HEIGHT_UNFOCUSED,
      GameSettings.Options.CHAT_WIDTH,
      GameSettings.Options.REDUCED_DEBUG_INFO
   };

   public ScreenChatOptions(GuiScreen var1, GameSettings var2) {
      this.parentScreen = var1;
      this.game_settings = var2;
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.field_146401_i = I18n.format("options.chat.title");

      for (GameSettings.Options var5 : field_146399_a) {
         if (var5.getEnumFloat()) {
            this.n.add(new GuiOptionSlider(var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), var5));
         } else {
            this.n
               .add(
                  new GuiOptionButton(
                     var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), var5, this.game_settings.getKeyBinding(var5)
                  )
               );
         }

         var1++;
      }

      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 120, I18n.format("gui.done")));
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.field_146401_i, this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k < 100 && var1 instanceof GuiOptionButton) {
            this.game_settings.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.j = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(var1.k));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.parentScreen);
         }
      }
   }
}
