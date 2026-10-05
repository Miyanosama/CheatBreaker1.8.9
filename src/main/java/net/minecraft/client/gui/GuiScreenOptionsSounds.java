package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiScreenOptionsSounds extends GuiScreen {
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
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
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
            new GuiScreenOptionsSounds.Button(
               SoundCategory.MASTER.getCategoryId(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), SoundCategory.MASTER, true
            )
         );
      var1 += 2;

      for (SoundCategory var5 : SoundCategory.values()) {
         if (var5 != SoundCategory.MASTER) {
            this.n
               .add(new GuiScreenOptionsSounds.Button(var5.getCategoryId(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 - 12 + 24 * (var1 >> 1), var5, false));
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

   public class Button extends GuiButton {
      public boolean field_146155_p;
      public float field_146156_o = 1.0F;
      public String field_146152_s;
      public SoundCategory field_146153_r;

      @Override
      public int getHoverState(boolean var1) {
         return 0;
      }

      @Override
      public boolean mousePressed(Minecraft var1, int var2, int var3) {
         if (super.mousePressed(var1, var2, var3)) {
            this.field_146156_o = (float)(var2 - (this.h + 4)) / (this.f - 8);
            this.field_146156_o = MathHelper.clamp_float(this.field_146156_o, 0.0F, 1.0F);
            var1.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
            var1.gameSettings.saveOptions();
            this.j = this.field_146152_s + ": " + GuiScreenOptionsSounds.this.getSoundVolume(this.field_146153_r);
            this.field_146155_p = true;
            return true;
         } else {
            return false;
         }
      }

      @Override
      public void mouseDragged(Minecraft var1, int var2, int var3) {
         if (this.m) {
            if (this.field_146155_p) {
               this.field_146156_o = (float)(var2 - (this.h + 4)) / (this.f - 8);
               this.field_146156_o = MathHelper.clamp_float(this.field_146156_o, 0.0F, 1.0F);
               var1.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
               var1.gameSettings.saveOptions();
               this.j = this.field_146152_s + ": " + GuiScreenOptionsSounds.this.getSoundVolume(this.field_146153_r);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.h + (int)(this.field_146156_o * (this.f - 8)), this.i, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.h + (int)(this.field_146156_o * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
         }
      }

      @Override
      public void mouseReleased(int var1, int var2) {
         if (this.field_146155_p) {
            if (this.field_146153_r == SoundCategory.MASTER) {
               float var3 = 1.0F;
            } else {
               GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(this.field_146153_r);
            }

            GuiScreenOptionsSounds.this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }

         this.field_146155_p = false;
      }

      public Button(int var2, int var3, int var4, SoundCategory var5, boolean var6) {
         super(var2, var3, var4, var6 ? 310 : 150, 20, "");
         this.field_146153_r = var5;
         this.field_146152_s = I18n.format("soundCategory." + var5.getCategoryName());
         this.j = this.field_146152_s + ": " + GuiScreenOptionsSounds.this.getSoundVolume(var5);
         this.field_146156_o = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(var5);
      }

      @Override
      public void playPressSound(SoundHandler var1) {
      }
   }
}
