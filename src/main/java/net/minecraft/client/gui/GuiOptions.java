package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
   public GuiButton field_175357_i;
   public GuiScreen field_146441_g;
   public String field_146442_a = "Options";
   public GuiLockIconButton field_175356_r;
   public GameSettings game_settings_1;
   public static GameSettings.Options[] field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV};

   public GuiOptions(GuiScreen var1, GameSettings var2) {
      this.field_146441_g = var1;
      this.game_settings_1 = var2;
   }

   public String func_175355_a(EnumDifficulty var1) {
      ChatComponentText var2 = new ChatComponentText("");
      var2.appendSibling(new ChatComponentTranslation("options.difficulty"));
      var2.appendText(": ");
      var2.appendSibling(new ChatComponentTranslation(var1.getDifficultyResourceKey()));
      return var2.getFormattedText();
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      this.j.displayGuiScreen(this);
      if (var2 == 109 && var1 && this.j.theWorld != null) {
         this.j.theWorld.P().setDifficultyLocked(true);
         this.field_175356_r.func_175229_b(true);
         this.field_175356_r.l = false;
         this.field_175357_i.l = false;
      }
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.field_146442_a = I18n.format("options.title");

      for (GameSettings.Options var5 : field_146440_f) {
         if (var5.getEnumFloat()) {
            this.n.add(new GuiOptionSlider(var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), var5));
         } else {
            GuiOptionButton var6 = new GuiOptionButton(
               var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), var5, this.game_settings_1.getKeyBinding(var5)
            );
            this.n.add(var6);
         }

         var1++;
      }

      if (this.j.theWorld != null) {
         EnumDifficulty var7 = this.j.theWorld.getDifficulty();
         this.field_175357_i = new GuiButton(108, this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), 150, 20, this.func_175355_a(var7));
         this.n.add(this.field_175357_i);
         if (this.j.isSingleplayer() && !this.j.theWorld.P().isHardcoreModeEnabled()) {
            this.field_175357_i.setWidth(this.field_175357_i.getButtonWidth() - 20);
            this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.h + this.field_175357_i.getButtonWidth(), this.field_175357_i.i);
            this.n.add(this.field_175356_r);
            this.field_175356_r.func_175229_b(this.j.theWorld.P().isDifficultyLocked());
            this.field_175356_r.l = !this.field_175356_r.func_175230_c();
            this.field_175357_i.l = !this.field_175356_r.func_175230_c();
         } else {
            this.field_175357_i.l = false;
         }
      } else {
         GuiOptionButton var8 = new GuiOptionButton(
            GameSettings.Options.REALMS_NOTIFICATIONS.returnEnumOrdinal(),
            this.l / 2 - 155 + var1 % 2 * 160,
            this.m / 6 - 12 + 24 * (var1 >> 1),
            GameSettings.Options.REALMS_NOTIFICATIONS,
            this.game_settings_1.getKeyBinding(GameSettings.Options.REALMS_NOTIFICATIONS)
         );
         this.n.add(var8);
      }

      this.n.add(new GuiButton(110, this.l / 2 - 155, this.m / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation")));
      this.n
         .add(
            new GuiButton(8675309, this.l / 2 + 5, this.m / 6 + 48 - 6, 150, 20, "Super Secret Settings...") {
               @Override
               public void playPressSound(SoundHandler var1) {
                  SoundEventAccessorComposite var2 = var1.getRandomSoundFromCategories(
                     SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER
                  );
                  if (var2 != null) {
                     var1.playSound(PositionedSoundRecord.create(var2.getSoundEventLocation(), 0.5F));
                  }
               }
            }
         );
      this.n.add(new GuiButton(106, this.l / 2 - 155, this.m / 6 + 72 - 6, 150, 20, I18n.format("options.sounds")));
      this.n.add(new GuiButton(107, this.l / 2 + 5, this.m / 6 + 72 - 6, 150, 20, I18n.format("options.stream")));
      this.n.add(new GuiButton(101, this.l / 2 - 155, this.m / 6 + 96 - 6, 150, 20, I18n.format("options.video")));
      this.n.add(new GuiButton(100, this.l / 2 + 5, this.m / 6 + 96 - 6, 150, 20, I18n.format("options.controls")));
      this.n.add(new GuiButton(102, this.l / 2 - 155, this.m / 6 + 120 - 6, 150, 20, I18n.format("options.language")));
      this.n.add(new GuiButton(103, this.l / 2 + 5, this.m / 6 + 120 - 6, 150, 20, I18n.format("options.chat.title")));
      this.n.add(new GuiButton(105, this.l / 2 - 155, this.m / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack")));
      this.n.add(new GuiButton(104, this.l / 2 + 5, this.m / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view")));
      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 168, I18n.format("gui.done")));
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k < 100 && var1 instanceof GuiOptionButton) {
            GameSettings.Options var2 = ((GuiOptionButton)var1).returnEnumOptions();
            this.game_settings_1.setOptionValue(var2, 1);
            var1.j = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(var1.k));
         }

         if (var1.k == 108) {
            this.j.theWorld.P().setDifficulty(EnumDifficulty.getDifficultyEnum(this.j.theWorld.getDifficulty().getDifficultyId() + 1));
            this.field_175357_i.j = this.func_175355_a(this.j.theWorld.getDifficulty());
         }

         if (var1.k == 109) {
            this.j
               .displayGuiScreen(
                  new GuiYesNo(
                     this,
                     new ChatComponentTranslation("difficulty.lock.title").getFormattedText(),
                     new ChatComponentTranslation(
                           "difficulty.lock.question", new ChatComponentTranslation(this.j.theWorld.P().getDifficulty().getDifficultyResourceKey())
                        )
                        .getFormattedText(),
                     109
                  )
               );
         }

         if (var1.k == 110) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiCustomizeSkin(this));
         }

         if (var1.k == 8675309) {
            this.j.entityRenderer.activateNextShader();
         }

         if (var1.k == 101) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
         }

         if (var1.k == 100) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiControls(this, this.game_settings_1));
         }

         if (var1.k == 102) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.j.getLanguageManager()));
         }

         if (var1.k == 103) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
         }

         if (var1.k == 104) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.field_146441_g);
         }

         if (var1.k == 105) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new ResourcePackGui(this));
         }

         if (var1.k == 106) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
         }

         if (var1.k == 107) {
            this.j.gameSettings.saveOptions();
            IStream var3 = this.j.getTwitchStream();
            if (var3.method_02383() && var3.func_152928_D()) {
               this.j.displayGuiScreen(new GuiStreamOptions(this, this.game_settings_1));
            } else {
               GuiStreamUnavailable.func_152321_a(this);
            }
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.field_146442_a, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
   }
}
