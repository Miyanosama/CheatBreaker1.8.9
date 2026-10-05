package net.minecraft.client.gui;

import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import org.java_websocket.extensions.permessage_deflate.PerMessageDeflateExtension;

public class GuiScreenOptionsSounds extends GuiScreen {
   public PerMessageDeflateExtension field_0002;
   public GameSettings game_settings_4;
   public String field_146508_h;
   public String field_146507_a = "Options";
   public GuiScreen field_146505_f;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.field_146507_a, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l && var1.k == 200) {
         this.j.gameSettings.saveOptions();
         this.j.displayGuiScreen(this.field_146505_f);
      }
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.field_146507_a = I18n.format("options.sounds.title");
      this.field_146508_h = I18n.format("options.off");
      this.n
         .add(
            new GuiScreenOptionsSounds$Button(
               this, SoundCategory.MASTER.getCategoryId(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), SoundCategory.MASTER, true
            )
         );
      var1 += 2;

      for (SoundCategory var5 : SoundCategory.values()) {
         if (var5 != SoundCategory.MASTER) {
            this.n
               .add(
                  new GuiScreenOptionsSounds$Button(
                     this, var5.getCategoryId(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), var5, false
                  )
               );
            var1++;
         }
      }

      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 168, I18n.format("gui.done")));
   }

   public String getSoundVolume(SoundCategory var1) {
      float var2 = this.game_settings_4.getSoundLevel(var1);
      return var2 == 0.0F ? this.field_146508_h : (int)(var2 * 100.0F) + "%";
   }

   public GuiScreenOptionsSounds(GuiScreen var1, GameSettings var2) {
      this.field_146505_f = var1;
      this.game_settings_4 = var2;
   }
}
