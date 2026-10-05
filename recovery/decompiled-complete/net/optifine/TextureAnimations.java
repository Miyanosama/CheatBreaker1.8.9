package net.optifine;

import io.netty.handler.codec.http.DefaultHttpRequest;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.player.PlayerItemParser;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TextureUtils;

public class TextureAnimations {
   public PlayerItemParser field_0002;
   public static int countAnimationsActive = 0;
   public DefaultHttpRequest field_0001;
   public static int frameCountAnimations = 0;
   public static TextureAnimation[] textureAnimations = null;

   public static TextureAnimation[] getTextureAnimations(IResourcePack[] var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < var0.length; var2++) {
         IResourcePack var3 = var0[var2];
         TextureAnimation[] var4 = getTextureAnimations(var3);
         if (var4 != null) {
            var1.addAll(Arrays.asList(var4));
         }
      }

      return var1.toArray(new TextureAnimation[var1.size()]);
   }

   public static TextureAnimation makeTextureAnimation(Properties var0, ResourceLocation var1) {
      String var2 = var0.getProperty("from");
      String var3 = var0.getProperty("to");
      int var4 = Config.parseInt(var0.getProperty("x"), -1);
      int var5 = Config.parseInt(var0.getProperty("y"), -1);
      int var6 = Config.parseInt(var0.getProperty("w"), -1);
      int var7 = Config.parseInt(var0.getProperty("h"), -1);
      if (var2 == null || var3 == null) {
         Config.warn("TextureAnimation: Source or target texture not specified");
         return null;
      } else if (var4 >= 0 && var5 >= 0 && var6 >= 0 && var7 >= 0) {
         var2 = var2.trim();
         var3 = var3.trim();
         String var8 = TextureUtils.getBasePath(var1.getResourcePath());
         var2 = TextureUtils.fixResourcePath(var2, var8);
         var3 = TextureUtils.fixResourcePath(var3, var8);
         byte[] var9 = getCustomTextureData(var2, var6);
         if (var9 == null) {
            Config.warn("TextureAnimation: Source texture not found: " + var3);
            return null;
         } else {
            int var10 = var9.length / 4;
            int var11 = var10 / (var6 * var7);
            int var12 = var11 * var6 * var7;
            if (var10 != var12) {
               Config.warn("TextureAnimation: Source texture has invalid number of frames: " + var2 + ", frames: " + (float)var10 / (var6 * var7));
               return null;
            } else {
               ResourceLocation var13 = new ResourceLocation(var3);

               try {
                  InputStream var14 = Config.getResourceStream(var13);
                  if (var14 == null) {
                     Config.warn("TextureAnimation: Target texture not found: " + var3);
                     return null;
                  } else {
                     BufferedImage var15 = readTextureImage(var14);
                     if (var4 + var6 <= var15.getWidth() && var5 + var7 <= var15.getHeight()) {
                        return new TextureAnimation(var2, var9, var3, var13, var4, var5, var6, var7, var0);
                     } else {
                        Config.warn("TextureAnimation: Animation coordinates are outside the target texture: " + var3);
                        return null;
                     }
                  }
               } catch (IOException var17) {
                  Config.warn("TextureAnimation: Target texture not found: " + var3);
                  return null;
               }
            }
         }
      } else {
         Config.warn("TextureAnimation: Invalid coordinates");
         return null;
      }
   }

   public static void update() {
      textureAnimations = null;
      countAnimationsActive = 0;
      IResourcePack[] var0 = Config.getResourcePacks();
      textureAnimations = getTextureAnimations(var0);
      updateAnimations();
   }

   public static byte[] loadImage(String var0, int var1) {
      GameSettings var2 = Config.getGameSettings();

      try {
         ResourceLocation var3 = new ResourceLocation(var0);
         InputStream var4 = Config.getResourceStream(var3);
         if (var4 == null) {
            return null;
         } else {
            BufferedImage var5 = readTextureImage(var4);
            var4.close();
            if (var5 == null) {
               return null;
            } else {
               if (var1 > 0 && var5.getWidth() != var1) {
                  double var6 = var5.getHeight() / var5.getWidth();
                  int var8 = (int)(var1 * var6);
                  var5 = scaleBufferedImage(var5, var1, var8);
               }

               int var20 = var5.getWidth();
               int var7 = var5.getHeight();
               int[] var21 = new int[var20 * var7];
               byte[] var9 = new byte[var20 * var7 * 4];
               var5.getRGB(0, 0, var20, var7, var21, 0, var20);

               for (int var10 = 0; var10 < var21.length; var10++) {
                  int var11 = var21[var10] >> 24 & 0xFF;
                  int var12 = var21[var10] >> 16 & 0xFF;
                  int var13 = var21[var10] >> 8 & 0xFF;
                  int var14 = var21[var10] & 0xFF;
                  if (var2 != null && var2.anaglyph) {
                     int var15 = (var12 * 30 + var13 * 59 + var14 * 11) / 100;
                     int var16 = (var12 * 30 + var13 * 70) / 100;
                     int var17 = (var12 * 30 + var14 * 70) / 100;
                     var12 = var15;
                     var13 = var16;
                     var14 = var17;
                  }

                  var9[var10 * 4 + 0] = (byte)var12;
                  var9[var10 * 4 + 1] = (byte)var13;
                  var9[var10 * 4 + 2] = (byte)var14;
                  var9[var10 * 4 + 3] = (byte)var11;
               }

               return var9;
            }
         }
      } catch (FileNotFoundException var18) {
         return null;
      } catch (Exception var19) {
         var19.printStackTrace();
         return null;
      }
   }

   public static BufferedImage scaleBufferedImage(BufferedImage var0, int var1, int var2) {
      BufferedImage var3 = new BufferedImage(var1, var2, 2);
      Graphics2D var4 = var3.createGraphics();
      var4.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      var4.drawImage(var0, 0, 0, var1, var2, (ImageObserver)null);
      return var3;
   }

   public static int getCountAnimations() {
      return textureAnimations == null ? 0 : textureAnimations.length;
   }

   public static byte[] getCustomTextureData(String var0, int var1) {
      byte[] var2 = loadImage(var0, var1);
      if (var2 == null) {
         var2 = loadImage("/anim" + var0, var1);
      }

      return var2;
   }

   public static void reset() {
      textureAnimations = null;
   }

   public static int getCountAnimationsActive() {
      return countAnimationsActive;
   }

   public static BufferedImage readTextureImage(InputStream var0) {
      BufferedImage var1 = ImageIO.read(var0);
      var0.close();
      return var1;
   }

   public static void updateAnimations() {
      if (textureAnimations != null && Config.isAnimatedTextures()) {
         int var0 = 0;

         for (int var1 = 0; var1 < textureAnimations.length; var1++) {
            TextureAnimation var2 = textureAnimations[var1];
            var2.updateTexture();
            if (var2.isActive()) {
               var0++;
            }
         }

         int var3 = Config.getMinecraft().entityRenderer.frameCount;
         if (var3 != frameCountAnimations) {
            countAnimationsActive = var0;
            frameCountAnimations = var3;
         }

         if (SmartAnimations.isActive()) {
            SmartAnimations.resetTexturesRendered();
         }
      } else {
         countAnimationsActive = 0;
      }
   }

   public static TextureAnimation[] getTextureAnimations(IResourcePack var0) {
      String[] var1 = ResUtils.collectFiles(var0, "mcpatcher/anim/", ".properties", (String[])null);
      if (var1.length <= 0) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            Config.dbg("Texture animation: " + var4);

            try {
               ResourceLocation var5 = new ResourceLocation(var4);
               InputStream var6 = var0.getInputStream(var5);
               PropertiesOrdered var7 = new PropertiesOrdered();
               var7.load(var6);
               var6.close();
               TextureAnimation var8 = makeTextureAnimation(var7, var5);
               if (var8 != null) {
                  ResourceLocation var9 = new ResourceLocation(var8.getDstTex());
                  if (Config.getDefiningResourcePack(var9) != var0) {
                     Config.dbg("Skipped: " + var4 + ", target texture not loaded from same resource pack");
                  } else {
                     var2.add(var8);
                  }
               }
            } catch (FileNotFoundException var10) {
               Config.warn("File not found: " + var10.getMessage());
            } catch (IOException var11) {
               var11.printStackTrace();
            }
         }

         return var2.toArray(new TextureAnimation[var2.size()]);
      }
   }
}
