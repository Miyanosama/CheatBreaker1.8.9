package com.cheatbreaker.client.ui.util.font;

import com.cheatbreaker.client.CheatBreaker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CBFontRenderer extends CBFont {
   private static final FontAtlasCache<DynamicTexture> ADAPTIVE_FONTS = new FontAtlasCache<>(
      Executors.newSingleThreadExecutor(task -> {
         Thread thread = new Thread(task, "CheatBreaker font rasterizer");
         thread.setDaemon(true);
         return thread;
      }), new FontAtlasCache.Textures<DynamicTexture>() {
         @Override public DynamicTexture upload(FontAtlasImage image) { return new DynamicTexture(image.image); }
         @Override public void delete(DynamicTexture texture) { texture.deleteGlTexture(); }
      }, System::nanoTime, 64L * 1024 * 1024, 32);
   public DynamicTexture texItalicBold;
   public char COLOR_CODE_START = 167;
   public DynamicTexture texItalic;
   public String recoveredField1977;
   public CBFont.CharData[] boldChars = new CBFont.CharData[256];
   public DynamicTexture texBold;
   public CBFont.CharData[] italicChars = new CBFont.CharData[256];
   public int[] colorCode;
   public CBFont.CharData[] boldItalicChars = new CBFont.CharData[256];

   public float drawString(String var1, float var2, float var3, int var4) {
      return this.drawString(var1, var2, var3, var4, false);
   }

   public String method_03181(String var1, double var2) {
      StringBuilder var4 = new StringBuilder();
      StringBuilder var5 = new StringBuilder();
      boolean var6 = false;
      if (var1 == null) {
         return null;
      } else {
         for (char var10 : var1.toCharArray()) {
            if (var6) {
               var4.append(var10);
               var6 = false;
            } else if (var10 == 167) {
               var4.append(var10);
               var6 = true;
            } else {
               var4.append(var10);
               int var13 = this.getStringWidth(var4.toString());
               if (var13 >= var2) {
                  String var14 = var4.toString();
                  String var11;
                  String var12;
                  if (var14.contains(" ")) {
                     var12 = var14.substring(0, var14.lastIndexOf(" "));
                     var11 = var14.substring(var14.lastIndexOf(" "));
                     if (var11.startsWith(" ")) {
                        var11 = var11.replaceFirst(" ", "");
                     }
                  } else {
                     var12 = var14.substring(0, var14.length() - 1);
                     var11 = var14.substring(var14.length() - 1);
                  }

                  var5.append(var12).append("\n");
                  String var15 = EnumChatFormatting.method_09748(var4.toString());
                  var4.setLength(0);
                  var4.append(var11).append(var15);
               }
            }
         }

         var5.append((CharSequence)var4);
         return var5.length() == 0 ? var1 : var5.toString();
      }
   }

   public List<String> method_03190(String var1, double var2) {
      ArrayList var4 = new ArrayList();
      if (this.getStringWidth(var1) > var2) {
         String[] var5 = var1.split(" ");
         String var6 = "";
         char var7 = '\uffff';

         for (int var8 = 0; var8 < var5.length; var8++) {
            String var9 = var5[var8];

            for (int var10 = 0; var10 < var9.toCharArray().length; var10++) {
               char var11 = var9.toCharArray()[var10];
               if (var11 == 167 && var10 < var9.toCharArray().length - 1) {
                  var7 = var9.toCharArray()[var10 + 1];
               }
            }

            StringBuilder var15 = new StringBuilder();
            if (this.getStringWidth(var15.append(var6).append(var9).append(" ").toString()) < var2) {
               var6 = var6 + var9 + " ";
            } else {
               var4.add(var6);
               var6 = 167 + var7 + var9 + " ";
            }
         }

         if (var6.length() > 0) {
            if (this.getStringWidth(var6) < var2) {
               var4.add(167 + var7 + var6 + " ");
               var6 = "";
            } else {
               for (String var14 : this.method_03189(var6, var2)) {
                  var4.add(var14);
               }
            }
         }
      } else {
         var4.add(var1);
      }

      return var4;
   }

   public String method_03177(String var1, double var2) {
      return this.method_03185(var1, var2, false);
   }

   public float drawString(String var1, double var2, double var4, int var6, boolean var7) {
      var2--;
      if (var1 == null) {
         return 0.0F;
      } else {
         if (var6 == 553648127) {
            var6 = 16777215;
         }

         if ((var6 & -67108864) == 0) {
            var6 |= -16777216;
         }

         if (var7) {
            var6 = (var6 & 16579836) >> 2 | var6 & 0xFF000000;
         }

         if (CheatBreaker.recoveredField1597) {
            var6 = -12058369;
         }

         CBFont.CharData[] var8 = this.charData;
         float var9 = (var6 >> 24 & 0xFF) / 255.0F;
         boolean var10 = false;
         boolean var11 = false;
         boolean var12 = false;
         boolean var13 = false;
         boolean var14 = false;
         boolean var15 = true;
         FontScreenGeometry screen = FontScreenGeometry.capture();
         FontAtlasCache.Atlas<DynamicTexture> atlas = null;
         boolean selectAtlas = true;
         var2 *= 2.0;
         var4 = (var4 - 3.0) * 2.0;
         if (var15) {
            GL11.glPushMatrix();
            GL11.glScaled(0.5, 0.5, 0.5);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f((var6 >> 16 & 0xFF) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, var9);
            int var16 = var1.length();
            GL11.glEnable(3553);
            int var17 = GL11.glGetInteger(32873);
            GL11.glBindTexture(3553, this.tex.getGlTextureId());

            for (int var18 = 0; var18 < var16; var18++) {
               char var19 = var1.charAt(var18);
               if (var19 == 167 && var18 < var16) {
                  int var20 = 21;

                  try {
                     var20 = "0123456789abcdefklmnor".indexOf(var1.charAt(var18 + 1));
                  } catch (Exception var22) {
                     var22.printStackTrace();
                  }

                  if (var20 >= 16) {
                     if (var20 == 16) {
                        var10 = true;
                     } else if (var20 == 17) {
                        var11 = true;
                        if (var12) {
                           GL11.glBindTexture(3553, this.texItalicBold.getGlTextureId());
                           var8 = this.boldItalicChars;
                        } else {
                           GL11.glBindTexture(3553, this.texBold.getGlTextureId());
                           var8 = this.boldChars;
                        }
                     } else if (var20 == 18) {
                        var13 = true;
                     } else if (var20 == 19) {
                        var14 = true;
                     } else if (var20 == 20) {
                        var12 = true;
                        if (var11) {
                           GL11.glBindTexture(3553, this.texItalicBold.getGlTextureId());
                           var8 = this.boldItalicChars;
                        } else {
                           GL11.glBindTexture(3553, this.texItalic.getGlTextureId());
                           var8 = this.italicChars;
                        }
                     } else if (var20 == 21) {
                        var11 = false;
                        var12 = false;
                        var10 = false;
                        var14 = false;
                        var13 = false;
                        GL11.glColor4f((var6 >> 16 & 0xFF) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, var9);
                        GL11.glBindTexture(3553, this.tex.getGlTextureId());
                        var8 = this.charData;
                     }
                  } else {
                     var11 = false;
                     var12 = false;
                     var10 = false;
                     var14 = false;
                     var13 = false;
                     GL11.glBindTexture(3553, this.tex.getGlTextureId());
                     var8 = this.charData;
                     if (var20 < 0 || var20 > 15) {
                        var20 = 15;
                     }

                     if (var7) {
                        var20 += 16;
                     }

                     int var21 = this.colorCode[var20];
                     GL11.glColor4f((var21 >> 16 & 0xFF) / 255.0F, (var21 >> 8 & 0xFF) / 255.0F, (var21 & 0xFF) / 255.0F, var9);
                  }

                  var18++;
                  selectAtlas = true;
               } else if (var19 < var8.length && var19 >= 0) {
                  if (selectAtlas) {
                     int style = (var11 ? java.awt.Font.BOLD : 0) | (var12 ? java.awt.Font.ITALIC : 0);
                     atlas = ADAPTIVE_FONTS.get(this.font.deriveFont(style), screen.rasterScale(), this.antiAlias,
                        this.fractionalMetrics, texture -> texture.getGlTextureId() != var17);
                     DynamicTexture base = var11 ? (var12 ? this.texItalicBold : this.texBold) : (var12 ? this.texItalic : this.tex);
                     GL11.glBindTexture(3553, atlas == null ? base.getGlTextureId() : atlas.texture.getGlTextureId());
                     selectAtlas = false;
                  }
                  float glyphX = (float)(screen.snapX(var2 / 2) * 2);
                  float glyphY = (float)(screen.snapY((var4 + 6) / 2) * 2);
                  GL11.glBegin(4);
                  if (atlas == null) {
                     this.drawChar(var8, var19, glyphX, glyphY);
                  } else {
                     drawAdaptiveChar(atlas, var19, glyphX, glyphY);
                  }
                  GL11.glEnd();
                  if (var13) {
                     this.method_03179(var2, var4 + var8[var19].height / 2, var2 + var8[var19].width - 8.0, var4 + var8[var19].height / 2, 1.0F);
                  }

                  if (var14) {
                     this.method_03179(var2, var4 + var8[var19].height - 2.0, var2 + var8[var19].width - 8.0, var4 + var8[var19].height - 2.0, 1.0F);
                  }

                  var2 += var8[var19].width - 8 + this.charOffset;
               }
            }

            GL11.glDisable(3042);
            GL11.glBindTexture(3553, var17);
            GL11.glHint(3155, 4352);
            GL11.glPopMatrix();
         }

         return (float)var2 / 2.0F;
      }
   }

   public List<String> method_03189(String var1, double var2) {
      ArrayList var4 = new ArrayList();
      String var5 = "";
      char var6 = '\uffff';
      char[] var7 = var1.toCharArray();

      for (int var8 = 0; var8 < var7.length; var8++) {
         char var9 = var7[var8];
         if (var9 == 167 && var8 < var7.length - 1) {
            var6 = var7[var8 + 1];
         }

         StringBuilder var10 = new StringBuilder();
         if (this.getStringWidth(var10.append(var5).append(var9).toString()) < var2) {
            var5 = var5 + var9;
         } else {
            var4.add(var5);
            var5 = 167 + var6 + String.valueOf(var9);
         }
      }

      if (var5.length() > 0) {
         var4.add(var5);
      }

      return var4;
   }

   public void method_03179(double var1, double var3, double var5, double var7, float var9) {
      GL11.glDisable(3553);
      GL11.glLineWidth(var9);
      GL11.glBegin(1);
      GL11.glVertex2d(var1, var3);
      GL11.glVertex2d(var5, var7);
      GL11.glEnd();
      GL11.glEnable(3553);
   }

   public float method_03191(String var1, float var2, float var3, int var4) {
      this.drawString(var1, var2 - this.getStringWidth(var1) / 2 + 1.0, var3 + 1.0, var4, true);
      return this.drawString(var1, var2 - this.getStringWidth(var1) / 2, var3, var4);
   }

   @Override
   public void setFractionalMetrics(boolean var1) {
      super.setFractionalMetrics(var1);
      this.setupBoldItalicIDs();
   }

   public float drawStringWithShadow(String var1, double var2, double var4, int var6, int var7) {
      float var8 = this.drawString(var1, var2 + 1.0, var4 + 1.0, var7, false);
      return Math.max(var8, this.drawString(var1, var2, var4, var6, false));
   }

   public void setupMinecraftColorCodes() {
      for (int var1 = 0; var1 < 32; var1++) {
         int var2 = (var1 >> 3 & 1) * 85;
         int var3 = (var1 >> 2 & 1) * 170 + var2;
         int var4 = (var1 >> 1 & 1) * 170 + var2;
         int var5 = (var1 & 1) * 170 + var2;
         if (var1 == 6) {
            var3 += 85;
         }

         if (var1 >= 16) {
            var3 /= 4;
            var4 /= 4;
            var5 /= 4;
         }

         this.colorCode[var1] = (var3 & 0xFF) << 16 | (var4 & 0xFF) << 8 | var5 & 0xFF;
      }
   }

   public float drawCenteredString(String var1, float var2, float var3, int var4) {
      return this.drawString(var1, var2 - this.getStringWidth(var1) / 2, var3, var4);
   }

   public CBFontRenderer(ResourceLocation var1, float var2) {
      super(var1, var2);
      this.colorCode = new int[32];
      this.recoveredField1977 = "0123456789abcdefklmnor";

      try {
         Minecraft.getMinecraft().recoveredField3820.method_25825();
      } catch (NullPointerException var4) {
      }

      this.setupMinecraftColorCodes();
      this.setupBoldItalicIDs();
   }

   public float drawStringWithShadow(String var1, double var2, double var4, int var6) {
      float var7 = this.drawString(var1, var2 + 1.0, var4 + 1.0, var6, true);
      return Math.max(var7, this.drawString(var1, var2, var4, var6, false));
   }

   public String method_03185(String var1, double var2, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      float var6 = 0.0F;
      int var7 = var4 ? var1.length() - 1 : 0;
      int var8 = var4 ? -1 : 1;
      boolean var9 = false;
      boolean var10 = false;

      for (int var11 = var7; var11 >= 0 && var11 < var1.length() && var6 < (float)var2; var11 += var8) {
         char var12 = var1.charAt(var11);
         double var13 = this.getStringWidth(String.valueOf(var12));
         if (var9) {
            var9 = false;
            if (var12 == 'l' || var12 == 'L') {
               var10 = true;
            } else if (var12 == 'r' || var12 == 'R') {
               var10 = false;
            }
         } else if (var13 < 0.0) {
            var9 = true;
         } else {
            var6 = (float)(var6 + var13);
            if (var10) {
               var6++;
            }
         }

         if (var6 > (float)var2) {
            break;
         }

         if (var4) {
            var5.insert(0, var12);
         } else {
            var5.append(var12);
         }
      }

      return var5.toString();
   }

   @Override
   public int getStringWidth(String var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = 0;
         CBFont.CharData[] var3 = this.charData;
         boolean var4 = false;
         boolean var5 = false;
         int var6 = var1.length();

         for (int var7 = 0; var7 < var6; var7++) {
            char var8 = var1.charAt(var7);
            if (var8 == 167 && var7 < var6) {
               int var9 = "0123456789abcdefklmnor".indexOf(var8);
               if (var9 < 16) {
                  var4 = false;
                  var5 = false;
               } else if (var9 == 17) {
                  var4 = true;
                  var3 = var5 ? this.boldItalicChars : this.boldChars;
               } else if (var9 == 20) {
                  var5 = true;
                  var3 = var4 ? this.boldItalicChars : this.italicChars;
               } else if (var9 == 21) {
                  var4 = false;
                  var5 = false;
                  var3 = this.charData;
               }

               var7++;
            } else if (var8 < var3.length && var8 >= 0) {
               var2 += var3[var8].width - 8 + this.charOffset;
            }
         }

         return var2 / 2;
      }
   }

   @Override
   public void setAntiAlias(boolean var1) {
      super.setAntiAlias(var1);
      this.setupBoldItalicIDs();
   }

   public void setupBoldItalicIDs() {
      this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
      this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
      this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
   }

   private static void drawAdaptiveChar(FontAtlasCache.Atlas<DynamicTexture> atlas, char character, float x, float y) {
      FontAtlasImage.Glyph glyph = atlas.glyphs[character];
      x -= glyph.left / atlas.density;
      y -= glyph.top / atlas.density;
      float width = (float)(glyph.width / atlas.density);
      float height = (float)(glyph.height / atlas.density);
      float u0 = (float)glyph.x / atlas.width, v0 = (float)glyph.y / atlas.height;
      float u1 = (float)(glyph.x + glyph.width) / atlas.width, v1 = (float)(glyph.y + glyph.height) / atlas.height;
      GL11.glTexCoord2f(u1, v0); GL11.glVertex2f(x + width, y);
      GL11.glTexCoord2f(u0, v0); GL11.glVertex2f(x, y);
      GL11.glTexCoord2f(u0, v1); GL11.glVertex2f(x, y + height);
      GL11.glTexCoord2f(u0, v1); GL11.glVertex2f(x, y + height);
      GL11.glTexCoord2f(u1, v1); GL11.glVertex2f(x + width, y + height);
      GL11.glTexCoord2f(u1, v0); GL11.glVertex2f(x + width, y);
   }
}
