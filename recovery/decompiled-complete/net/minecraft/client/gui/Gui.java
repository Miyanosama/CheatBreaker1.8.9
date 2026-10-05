package net.minecraft.client.gui;

import io.netty.channel.sctp.oio.OioSctpChannel$1;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

public class Gui {
   public static ResourceLocation b = new ResourceLocation("textures/gui/options_background.png");
   public static ResourceLocation statIcons = new ResourceLocation("textures/gui/container/stats_icons.png");
   public static float field_0003;
   public ModelBanner field_0005;
   public S22PacketMultiBlockChange field_0006;
   public static ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
   public TileEntityEnderChest field_0004;
   public OioSctpChannel$1 field_0007;

   public static void method_00887(float var0, float var1, float var2, float var3, float var4, int var5, int var6) {
      drawRect(var0 + var4, var1 + var4, var2 - var4, var3 - var4, var6);
      drawRect(var0, var1 + var4, var0 + var4, var3 - var4, var5);
      drawRect(var2 - var4, var1 + var4, var2, var3 - var4, var5);
      drawRect(var0, var1, var2, var1 + var4, var5);
      drawRect(var0, var3 - var4, var2, var3, var5);
   }

   public void method_00889(float var1, float var2, float var3, float var4, int var5, int var6) {
      float var7 = (var5 >> 24 & 0xFF) / 255.0F;
      float var8 = (var5 >> 16 & 0xFF) / 255.0F;
      float var9 = (var5 >> 8 & 0xFF) / 255.0F;
      float var10 = (var5 & 0xFF) / 255.0F;
      float var11 = (var6 >> 24 & 0xFF) / 255.0F;
      float var12 = (var6 >> 16 & 0xFF) / 255.0F;
      float var13 = (var6 >> 8 & 0xFF) / 255.0F;
      float var14 = (var6 & 0xFF) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      Tessellator var15 = Tessellator.getInstance();
      WorldRenderer var16 = var15.getWorldRenderer();
      var16.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var16.pos(var3, var2, field_0003).color(var8, var9, var10, var7).endVertex();
      var16.pos(var1, var2, field_0003).color(var8, var9, var10, var7).endVertex();
      var16.pos(var1, var4, field_0003).color(var12, var13, var14, var11).endVertex();
      var16.pos(var3, var4, field_0003).color(var12, var13, var14, var11).endVertex();
      var15.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public void drawTexturedModalRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0, var2 + var6, field_0003).tex((var3 + 0) * var7, (var4 + var6) * var8).endVertex();
      var10.pos(var1 + var5, var2 + var6, field_0003).tex((var3 + var5) * var7, (var4 + var6) * var8).endVertex();
      var10.pos(var1 + var5, var2 + 0, field_0003).tex((var3 + var5) * var7, (var4 + 0) * var8).endVertex();
      var10.pos(var1 + 0, var2 + 0, field_0003).tex((var3 + 0) * var7, (var4 + 0) * var8).endVertex();
      var9.draw();
   }

   public void drawHorizontalLine(int var1, int var2, int var3, int var4) {
      if (var2 < var1) {
         int var5 = var1;
         var1 = var2;
         var2 = var5;
      }

      a(var1, var3, var2 + 1, var3 + 1, var4);
   }

   public void drawGradientRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = (var5 >> 24 & 0xFF) / 255.0F;
      float var8 = (var5 >> 16 & 0xFF) / 255.0F;
      float var9 = (var5 >> 8 & 0xFF) / 255.0F;
      float var10 = (var5 & 0xFF) / 255.0F;
      float var11 = (var6 >> 24 & 0xFF) / 255.0F;
      float var12 = (var6 >> 16 & 0xFF) / 255.0F;
      float var13 = (var6 >> 8 & 0xFF) / 255.0F;
      float var14 = (var6 & 0xFF) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      Tessellator var15 = Tessellator.getInstance();
      WorldRenderer var16 = var15.getWorldRenderer();
      var16.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var16.pos(var3, var2, field_0003).color(var8, var9, var10, var7).endVertex();
      var16.pos(var1, var2, field_0003).color(var8, var9, var10, var7).endVertex();
      var16.pos(var1, var4, field_0003).color(var12, var13, var14, var11).endVertex();
      var16.pos(var3, var4, field_0003).color(var12, var13, var14, var11).endVertex();
      var15.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public void drawString(FontRenderer var1, String var2, int var3, int var4, int var5) {
      var1.drawStringWithShadow(var2, var3, var4, var5);
   }

   public void drawTexturedModalRect(int var1, int var2, TextureAtlasSprite var3, int var4, int var5) {
      Tessellator var6 = Tessellator.getInstance();
      WorldRenderer var7 = var6.getWorldRenderer();
      var7.begin(7, DefaultVertexFormats.POSITION_TEX);
      var7.pos(var1 + 0, var2 + var5, field_0003).tex(var3.getMinU(), var3.getMaxV()).endVertex();
      var7.pos(var1 + var4, var2 + var5, field_0003).tex(var3.getMaxU(), var3.getMaxV()).endVertex();
      var7.pos(var1 + var4, var2 + 0, field_0003).tex(var3.getMaxU(), var3.getMinV()).endVertex();
      var7.pos(var1 + 0, var2 + 0, field_0003).tex(var3.getMinU(), var3.getMinV()).endVertex();
      var6.draw();
   }

   public void drawVerticalLine(int var1, int var2, int var3, int var4) {
      if (var3 < var2) {
         int var5 = var2;
         var2 = var3;
         var3 = var5;
      }

      a(var1, var2 + 1, var1 + 1, var3, var4);
   }

   public static void drawRect(float var0, float var1, float var2, float var3, int var4) {
      if (var0 < var2) {
         float var5 = var0;
         var0 = var2;
         var2 = var5;
      }

      if (var1 < var3) {
         float var12 = var1;
         var1 = var3;
         var3 = var12;
      }

      float var6 = (var4 >> 24 & 0xFF) / 255.0F;
      float var7 = (var4 >> 16 & 0xFF) / 255.0F;
      float var8 = (var4 >> 8 & 0xFF) / 255.0F;
      float var9 = (var4 & 0xFF) / 255.0F;
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      GlStateManager.disableCull();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0, var3, 0.0).color(var7, var8, var9, var6).endVertex();
      var11.pos(var2, var3, 0.0).color(var7, var8, var9, var6).endVertex();
      var11.pos(var2, var1, 0.0).color(var7, var8, var9, var6).endVertex();
      var11.pos(var0, var1, 0.0).color(var7, var8, var9, var6).endVertex();
      var10.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void drawScaledCustomSizeModalRect(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9) {
      float var10 = 1.0F / var8;
      float var11 = 1.0F / var9;
      Tessellator var12 = Tessellator.getInstance();
      WorldRenderer var13 = var12.getWorldRenderer();
      var13.begin(7, DefaultVertexFormats.POSITION_TEX);
      var13.pos(var0, var1 + var7, 0.0).tex(var2 * var10, (var3 + var5) * var11).endVertex();
      var13.pos(var0 + var6, var1 + var7, 0.0).tex((var2 + var4) * var10, (var3 + var5) * var11).endVertex();
      var13.pos(var0 + var6, var1, 0.0).tex((var2 + var4) * var10, var3 * var11).endVertex();
      var13.pos(var0, var1, 0.0).tex(var2 * var10, var3 * var11).endVertex();
      var12.draw();
   }

   public static void a(int var0, int var1, int var2, int var3, int var4) {
      if (var0 < var2) {
         int var5 = var0;
         var0 = var2;
         var2 = var5;
      }

      if (var1 < var3) {
         int var11 = var1;
         var1 = var3;
         var3 = var11;
      }

      float var12 = (var4 >> 24 & 0xFF) / 255.0F;
      float var6 = (var4 >> 16 & 0xFF) / 255.0F;
      float var7 = (var4 >> 8 & 0xFF) / 255.0F;
      float var8 = (var4 & 0xFF) / 255.0F;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var6, var7, var8, var12);
      var10.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var10.pos(var0, var3, 0.0).color(var6, var7, var8, var12).endVertex();
      var10.pos(var2, var3, 0.0).color(var6, var7, var8, var12).endVertex();
      var10.pos(var2, var1, 0.0).color(var6, var7, var8, var12).endVertex();
      var10.pos(var0, var1, 0.0).color(var6, var7, var8, var12).endVertex();
      var9.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawModalRectWithCustomSizedTexture(int var0, int var1, float var2, float var3, int var4, int var5, float var6, float var7) {
      float var8 = 1.0F / var6;
      float var9 = 1.0F / var7;
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      var11.begin(7, DefaultVertexFormats.POSITION_TEX);
      var11.pos(var0, var1 + var5, 0.0).tex(var2 * var8, (var3 + var5) * var9).endVertex();
      var11.pos(var0 + var4, var1 + var5, 0.0).tex((var2 + var4) * var8, (var3 + var5) * var9).endVertex();
      var11.pos(var0 + var4, var1, 0.0).tex((var2 + var4) * var8, var3 * var9).endVertex();
      var11.pos(var0, var1, 0.0).tex(var2 * var8, var3 * var9).endVertex();
      var10.draw();
   }

   public void drawTexturedModalRect(float var1, float var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0.0F, var2 + var6, field_0003).tex((var3 + 0) * var7, (var4 + var6) * var8).endVertex();
      var10.pos(var1 + var5, var2 + var6, field_0003).tex((var3 + var5) * var7, (var4 + var6) * var8).endVertex();
      var10.pos(var1 + var5, var2 + 0.0F, field_0003).tex((var3 + var5) * var7, (var4 + 0) * var8).endVertex();
      var10.pos(var1 + 0.0F, var2 + 0.0F, field_0003).tex((var3 + 0) * var7, (var4 + 0) * var8).endVertex();
      var9.draw();
   }

   public void drawCenteredString(FontRenderer var1, String var2, int var3, int var4, int var5) {
      var1.drawStringWithShadow(var2, var3 - var1.getStringWidth(var2) / 2, var4, var5);
   }

   public static void drawBoxWithOutLine(float var0, float var1, float var2, float var3, float var4, int var5, int var6) {
      drawRect(var0, var1, var2, var3, var6);
      drawRect(var0 - var4, var1 - var4, var0, var3 + var4, var5);
      drawRect(var2, var1 - var4, var2 + var4, var3 + var4, var5);
      drawRect(var0, var1 - var4, var2, var1, var5);
      drawRect(var0, var3, var2, var3 + var4, var5);
   }

   public static void method_00886(float var0, float var1, float var2, float var3, float var4, int var5) {
      drawRect(var0, var1 + var4, var0 + var4, var3 - var4, var5);
      drawRect(var2 - var4, var1 + var4, var2, var3 - var4, var5);
      drawRect(var0, var1, var2, var1 + var4, var5);
      drawRect(var0, var3 - var4, var2, var3, var5);
   }
}
