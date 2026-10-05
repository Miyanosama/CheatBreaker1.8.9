package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.xml.DOMConfigurator$5;
import org.java_websocket.enums.CloseHandshakeType;

public class GuiScreenOptionsSounds$Button extends GuiButton {
   public boolean field_146155_p;
   public DOMConfigurator$5 field_0005;
   public float field_146156_o;
   public String field_146152_s;
   public CloseHandshakeType field_0001;
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
         this.j = this.field_146152_s + ": " + this.field_146154_q.getSoundVolume(this.field_146153_r);
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
            this.j = this.field_146152_s + ": " + this.field_146154_q.getSoundVolume(this.field_146153_r);
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
            GuiScreenOptionsSounds.access$000(this.field_146154_q).getSoundLevel(this.field_146153_r);
         }

         this.field_146154_q.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
      }

      this.field_146155_p = false;
   }

   public GuiScreenOptionsSounds$Button(GuiScreenOptionsSounds var1, int var2, int var3, int var4, SoundCategory var5, boolean var6) {
      this.field_146154_q = var1;
      super(var2, var3, var4, var6 ? 310 : 150, 20, "");
      this.field_146156_o = 1.0F;
      this.field_146153_r = var5;
      this.field_146152_s = I18n.format("soundCategory." + var5.getCategoryName());
      this.j = this.field_146152_s + ": " + var1.getSoundVolume(var5);
      this.field_146156_o = GuiScreenOptionsSounds.access$000(var1).getSoundLevel(var5);
   }

   @Override
   public void playPressSound(SoundHandler var1) {
   }
}
