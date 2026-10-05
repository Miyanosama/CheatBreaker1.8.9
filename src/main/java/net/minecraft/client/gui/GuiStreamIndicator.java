package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiStreamIndicator {
   public float streamAlpha = 1.0F;
   public int streamAlphaDelta = 1;
   public Minecraft mc;
   public static ResourceLocation locationStreamIndicator = new ResourceLocation("textures/gui/stream_indicator.png");

   public void render(int var1, int var2, int var3, int var4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.streamAlpha);
      this.mc.getTextureManager().bindTexture(locationStreamIndicator);
      float var5 = 150.0F;
      float var6 = 0.0F;
      float var7 = var3 * 0.015625F;
      float var8 = 1.0F;
      float var9 = (var3 + 16) * 0.015625F;
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      var11.begin(7, DefaultVertexFormats.POSITION_TEX);
      var11.pos(var1 - 16 - var4, var2 + 16, var5).tex(var6, var9).endVertex();
      var11.pos(var1 - var4, var2 + 16, var5).tex(var8, var9).endVertex();
      var11.pos(var1 - var4, var2 + 0, var5).tex(var8, var7).endVertex();
      var11.pos(var1 - 16 - var4, var2 + 0, var5).tex(var6, var7).endVertex();
      var10.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public int func_152440_b() {
      return this.mc.getTwitchStream().isPaused() ? 16 : 0;
   }

   public void render(int var1, int var2) {
      if (this.mc.getTwitchStream().isBroadcasting()) {
         GlStateManager.enableBlend();
         int var3 = this.mc.getTwitchStream().func_152920_A();
         if (var3 > 0) {
            String var4 = "" + var3;
            int var5 = this.mc.fontRendererObj.getStringWidth(var4);
            byte var6 = 20;
            int var7 = var1 - var5 - 1;
            int var8 = var2 + 20 - 1;
            int var9 = var2 + 20 + this.mc.fontRendererObj.FONT_HEIGHT - 1;
            GlStateManager.disableTexture2D();
            Tessellator var10 = Tessellator.getInstance();
            WorldRenderer var11 = var10.getWorldRenderer();
            GlStateManager.color(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.streamAlpha) / 2.0F);
            var11.begin(7, DefaultVertexFormats.POSITION);
            var11.pos(var7, var9, 0.0).endVertex();
            var11.pos(var1, var9, 0.0).endVertex();
            var11.pos(var1, var8, 0.0).endVertex();
            var11.pos(var7, var8, 0.0).endVertex();
            var10.draw();
            GlStateManager.enableTexture2D();
            this.mc.fontRendererObj.drawString(var4, var1 - var5, var2 + 20, 16777215);
         }

         this.render(var1, var2, this.func_152440_b(), 0);
         this.render(var1, var2, this.func_152438_c(), 17);
      }
   }

   public GuiStreamIndicator(Minecraft var1) {
      this.mc = var1;
   }

   public void updateStreamAlpha() {
      if (this.mc.getTwitchStream().isBroadcasting()) {
         this.streamAlpha = this.streamAlpha + 0.025F * this.streamAlphaDelta;
         if (this.streamAlpha < 0.0F) {
            this.streamAlphaDelta *= -1;
            this.streamAlpha = 0.0F;
         } else if (this.streamAlpha > 1.0F) {
            this.streamAlphaDelta *= -1;
            this.streamAlpha = 1.0F;
         }
      } else {
         this.streamAlpha = 1.0F;
         this.streamAlphaDelta = 1;
      }
   }

   public int func_152438_c() {
      return this.mc.getTwitchStream().func_152929_G() ? 48 : 32;
   }
}
