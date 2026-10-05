package net.minecraft.client.gui;

import io.netty.util.ReferenceCountUtil$ReleasingTask;
import javazoom.jl.decoder.Manager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.http.HttpRequest;

public class GuiButton extends Gui {
   public int h;
   public int height;
   public ReferenceCountUtil$ReleasingTask field_0002;
   public boolean m;
   public Manager field_0008;
   public HttpRequest field_0005;
   public int f = 200;
   public int k;
   public boolean l;
   public String j;
   public int i;
   public static ResourceLocation a = new ResourceLocation("textures/gui/widgets.png");
   public boolean hovered;

   public void mouseReleased(int var1, int var2) {
   }

   public GuiButton(int var1, int var2, int var3, String var4) {
      this(var1, var2, var3, 200, 20, var4);
   }

   public int method_11184() {
      return this.height;
   }

   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      return this.l && this.m && var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
   }

   public void drawButtonForegroundLayer(int var1, int var2) {
   }

   public int getButtonWidth() {
      return this.f;
   }

   public GuiButton(int var1, int var2, int var3, int var4, int var5, String var6) {
      this.height = 20;
      this.l = true;
      this.m = true;
      this.k = var1;
      this.h = var2;
      this.i = var3;
      this.f = var4;
      this.height = var5;
      this.j = var6;
   }

   public void mouseDragged(Minecraft var1, int var2, int var3) {
   }

   public boolean isMouseOver() {
      return this.hovered;
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         FontRenderer var4 = var1.fontRendererObj;
         var1.getTextureManager().bindTexture(a);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         int var5 = this.getHoverState(this.hovered);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         this.drawTexturedModalRect(this.h, this.i, 0, 46 + var5 * 20, this.f / 2, this.height);
         this.drawTexturedModalRect(this.h + this.f / 2, this.i, 200 - this.f / 2, 46 + var5 * 20, this.f / 2, this.height);
         this.mouseDragged(var1, var2, var3);
         int var6 = 14737632;
         if (!this.l) {
            var6 = 10526880;
         } else if (this.hovered) {
            var6 = 16777120;
         }

         this.drawCenteredString(var4, this.j, this.h + this.f / 2, this.i + (this.height - 8) / 2, var6);
      }
   }

   public int getHoverState(boolean var1) {
      byte var2 = 1;
      if (!this.l) {
         var2 = 0;
      } else if (var1) {
         var2 = 2;
      }

      return var2;
   }

   public int method_11180() {
      return this.f;
   }

   public void playPressSound(SoundHandler var1) {
      var1.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public void setWidth(int var1) {
      this.f = var1;
   }
}
