package com.cheatbreaker.client.ui.util.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/** CPU-only rasterization, safe to run on the font worker. */
final class FontAtlasImage {
   BufferedImage image;
   final Glyph[] glyphs;
   final int width, height;
   final double density;

   private FontAtlasImage(BufferedImage image, Glyph[] glyphs, double density) {
      this.image = image;
      this.glyphs = glyphs;
      this.width = image.getWidth();
      this.height = image.getHeight();
      this.density = density;
   }

   static FontAtlasImage generate(Font font, double density, boolean antiAlias, boolean fractionalMetrics) {
      if (!Double.isFinite(density) || density <= 0) throw new IllegalArgumentException("Invalid font density");
      Font rasterFont = font.deriveFont((float)(font.getSize2D() * density));
      BufferedImage measure = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
      Graphics2D graphics = measure.createGraphics();
      Glyph[] glyphs = new Glyph[256];
      int ascent, maxWidth = 0;
      long area = 0;
      int padding = Math.max(2, (int)Math.ceil(8 * density));
      int inset = Math.max(1, (int)Math.round(2 * density));
      try {
         configure(graphics, rasterFont, antiAlias, fractionalMetrics);
         FontMetrics metrics = graphics.getFontMetrics();
         ascent = metrics.getAscent();
         for (int index = 0; index < glyphs.length; index++) {
            String character = String.valueOf((char)index);
            java.awt.Rectangle ink = rasterFont.createGlyphVector(graphics.getFontRenderContext(), character)
               .getPixelBounds(graphics.getFontRenderContext(), 0, 0);
            int left = Math.max(0, -ink.x - inset);
            int top = Math.max(0, -ink.y - ascent);
            int width = Math.max((int)Math.ceil(metrics.getStringBounds(character, graphics).getWidth()) + padding,
               inset + ink.x + ink.width + 2) + left;
            int height = Math.max(metrics.getHeight(), ascent + ink.y + ink.height + 1) + top;
            glyphs[index] = new Glyph(width, height, left, top);
            maxWidth = Math.max(maxWidth, width + 2);
            area += (long)(width + 2) * (height + 2);
         }
      } finally {
         graphics.dispose();
         measure.flush();
      }
      int atlasWidth = powerOfTwo(Math.max(maxWidth, (int)Math.ceil(Math.sqrt(area))));
      int x = 0, y = 1, rowHeight = 0;
      for (Glyph glyph : glyphs) {
         if (x + glyph.width + 2 > atlasWidth) { x = 0; y += rowHeight + 2; rowHeight = 0; }
         glyph.x = x; glyph.y = y;
         x += glyph.width + 2;
         rowHeight = Math.max(rowHeight, glyph.height);
      }
      int atlasHeight = powerOfTwo(y + rowHeight + 1);
      if (atlasWidth > 4096 || atlasHeight > 4096) throw new IllegalArgumentException("Font atlas exceeds size limit");
      BufferedImage image = new BufferedImage(atlasWidth, atlasHeight, BufferedImage.TYPE_INT_ARGB);
      graphics = image.createGraphics();
      try {
         configure(graphics, rasterFont, antiAlias, fractionalMetrics);
         graphics.setColor(Color.WHITE);
         for (int index = 0; index < glyphs.length; index++) {
            Glyph glyph = glyphs[index];
            graphics.drawString(String.valueOf((char)index), glyph.x + inset + glyph.left, glyph.y + ascent + glyph.top);
         }
      } finally {
         graphics.dispose();
      }
      return new FontAtlasImage(image, glyphs, density);
   }

   private static void configure(Graphics2D graphics, Font font, boolean antiAlias, boolean fractionalMetrics) {
      graphics.setFont(font);
      graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
         antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
      graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
         fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
   }

   private static int powerOfTwo(int value) {
      int result = 128;
      while (result < value) result *= 2;
      return result;
   }

   static final class Glyph {
      final int width, height, left, top;
      int x, y;
      Glyph(int width, int height, int left, int top) { this.width = width; this.height = height; this.left = left; this.top = top; }
   }
}
