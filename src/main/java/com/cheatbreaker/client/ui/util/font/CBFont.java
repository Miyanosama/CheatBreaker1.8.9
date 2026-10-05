package com.cheatbreaker.client.ui.util.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CBFont {
   public DynamicTexture tex;
   public CBFont.CharData[] charData;
   public boolean fractionalMetrics;
   public float imgSize = 1048.0F;
   public Font font;
   public int charOffset;
   public int fontHeight;
   public boolean antiAlias;

   public void drawChar(CBFont.CharData[] var1, char var2, float var3, float var4) {
      try {
         this.drawQuad(var3, var4, var1[var2].width, var1[var2].height, var1[var2].storedX, var1[var2].storedY, var1[var2].width, var1[var2].height);
      } catch (Exception var6) {
         var6.printStackTrace();
      }
   }

   public DynamicTexture setupTexture(Font var1, boolean var2, boolean var3, CBFont.CharData[] var4) {
      BufferedImage var5 = this.generateFontImage(var1, var2, var3, var4);

      try {
         return new DynamicTexture(var5);
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public int getHeight() {
      return (this.fontHeight - 8) / 2;
   }

   public void setAntiAlias(boolean var1) {
      if (this.antiAlias != var1) {
         this.antiAlias = var1;
         this.tex = this.setupTexture(this.font, var1, this.fractionalMetrics, this.charData);
      }
   }

   public void setFont(Font var1) {
      this.font = var1;
      this.tex = this.setupTexture(var1, this.antiAlias, this.fractionalMetrics, this.charData);
   }

   public Font getFont() {
      return this.font;
   }

   public int getStringWidth(String var1) {
      int var2 = 0;

      for (char var6 : var1.toCharArray()) {
         if (var6 < this.charData.length && var6 >= 0) {
            var2 += this.charData[var6].width - 8 + this.charOffset;
         }
      }

      return var2 / 2;
   }

   public CBFont(ResourceLocation var1, float var2) {
      this.charData = new CBFont.CharData[256];
      this.fontHeight = -1;
      this.charOffset = 0;

      Font var3;
      try {
         InputStream var4 = Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getResourceManager() != null
            ? Minecraft.getMinecraft().getResourceManager().getResource(var1).getInputStream()
            : Minecraft.getMinecraft().method_20413().method_20550(var1);
         var3 = Font.createFont(0, var4).deriveFont(var2);
      } catch (Exception var5) {
         var3 = new Font("Arial", 0, (int)var2);
      }

      this.font = var3;
      this.antiAlias = true;
      this.fractionalMetrics = true;
      this.tex = this.setupTexture(this.font, this.antiAlias, this.fractionalMetrics, this.charData);
   }

   public void drawQuad(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = var5 / 1048.0F;
      float var10 = var6 / 1048.0F;
      float var11 = var7 / 1048.0F;
      float var12 = var8 / 1048.0F;
      GL11.glTexCoord2f(var9 + var11, var10);
      GL11.glVertex2d(var1 + var3, var2);
      GL11.glTexCoord2f(var9, var10);
      GL11.glVertex2d(var1, var2);
      GL11.glTexCoord2f(var9, var10 + var12);
      GL11.glVertex2d(var1, var2 + var4);
      GL11.glTexCoord2f(var9, var10 + var12);
      GL11.glVertex2d(var1, var2 + var4);
      GL11.glTexCoord2f(var9 + var11, var10 + var12);
      GL11.glVertex2d(var1 + var3, var2 + var4);
      GL11.glTexCoord2f(var9 + var11, var10);
      GL11.glVertex2d(var1 + var3, var2);
   }

   public BufferedImage generateFontImage(Font var1, boolean var2, boolean var3, CBFont.CharData[] var4) {
      short var5 = 1048;
      BufferedImage var6 = new BufferedImage(var5, var5, 2);
      Graphics2D var7 = (Graphics2D)var6.getGraphics();
      var7.setFont(var1);
      var7.setColor(new Color(255, 255, 255, 0));
      var7.fillRect(0, 0, var5, var5);
      var7.setColor(Color.WHITE);
      var7.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, var3 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
      var7.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, var2 ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
      var7.setRenderingHint(RenderingHints.KEY_ANTIALIASING, var2 ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
      FontMetrics var8 = var7.getFontMetrics();
      int var9 = 0;
      int var10 = 0;
      int var11 = 1;

      for (int var12 = 0; var12 < var4.length; var12++) {
         char var13 = (char)var12;
         CBFont.CharData var14 = new CBFont.CharData(this);
         Rectangle2D var15 = var8.getStringBounds(String.valueOf(var13), var7);
         var14.width = var15.getBounds().width + 8;
         var14.height = var15.getBounds().height;
         if (var10 + var14.width >= var5) {
            var10 = 0;
            var11 += var9;
            var9 = 0;
         }

         if (var14.height > var9) {
            var9 = var14.height;
         }

         var14.storedX = var10;
         var14.storedY = var11;
         if (var14.height > this.fontHeight) {
            this.fontHeight = var14.height;
         }

         var4[var12] = var14;
         var7.drawString(String.valueOf(var13), var10 + 2, var11 + var8.getAscent());
         var10 += var14.width;
      }

      return var6;
   }

   public void setFractionalMetrics(boolean var1) {
      if (this.fractionalMetrics != var1) {
         this.fractionalMetrics = var1;
         this.tex = this.setupTexture(this.font, this.antiAlias, var1, this.charData);
      }
   }

   public class CharData {
      public int width;
      public int storedY;
      public int height;
      public CBFont recoveredField2809;
      public int storedX;

      public CharData(CBFont var1) {
         this.recoveredField2809 = var1;
      }
   }
}
