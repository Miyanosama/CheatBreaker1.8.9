package net.minecraft.client.gui;

import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocket07FrameDecoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.util.MathHelper;

public class GuiOptionSlider extends GuiButton {
   public boolean dragging;
   public float sliderValue = 1.0F;
   public float field_146131_s;
   public float field_146132_r;
   public WebSocket07FrameDecoder field_0000;
   public GameSettings$Options options;
   public EpollServerSocketChannel field_0006;

   @Override
   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if (super.mousePressed(var1, var2, var3)) {
         this.sliderValue = (float)(var2 - (this.h + 4)) / (this.f - 8);
         this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
         var1.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
         this.j = var1.gameSettings.getKeyBinding(this.options);
         this.dragging = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void mouseDragged(Minecraft var1, int var2, int var3) {
      if (this.m) {
         if (this.dragging) {
            this.sliderValue = (float)(var2 - (this.h + 4)) / (this.f - 8);
            this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
            float var4 = this.options.denormalizeValue(this.sliderValue);
            var1.gameSettings.setOptionFloatValue(this.options, var4);
            this.sliderValue = this.options.normalizeValue(var4);
            this.j = var1.gameSettings.getKeyBinding(this.options);
         }

         var1.getTextureManager().bindTexture(a);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.h + (int)(this.sliderValue * (this.f - 8)), this.i, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.h + (int)(this.sliderValue * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
      }
   }

   @Override
   public void mouseReleased(int var1, int var2) {
      this.dragging = false;
   }

   public GuiOptionSlider(int var1, int var2, int var3, GameSettings$Options var4) {
      this(var1, var2, var3, var4, 0.0F, 1.0F);
   }

   public GuiOptionSlider(int var1, int var2, int var3, GameSettings$Options var4, float var5, float var6) {
      super(var1, var2, var3, 150, 20, "");
      this.options = var4;
      this.field_146132_r = var5;
      this.field_146131_s = var6;
      Minecraft var7 = Minecraft.getMinecraft();
      this.sliderValue = var4.normalizeValue(var7.gameSettings.getOptionFloatValue(var4));
      this.j = var7.gameSettings.getKeyBinding(var4);
   }

   @Override
   public int getHoverState(boolean var1) {
      return 0;
   }
}
