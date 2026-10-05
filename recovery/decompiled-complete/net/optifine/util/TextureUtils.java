package net.optifine.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterGrass;
import net.optifine.BetterSnow;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomItems;
import net.optifine.CustomLoadingScreens;
import net.optifine.CustomPanorama;
import net.optifine.CustomSky;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.SmartLeaves;
import net.optifine.TextureAnimations;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TextureUtils {
   public static String field_0041;
   public static TextureAtlasSprite field_0073;
   public static String field_0037;
   public static String field_0069;
   public static TextureAtlasSprite field_0012;
   public static String field_0018;
   public static String field_0074;
   public static TextureAtlasSprite field_0057;
   public static String field_0019;
   public static String field_0081;
   public static String field_0011;
   public static String field_0042;
   public static String field_0051;
   public static String field_0028;
   public static String field_0062;
   public static String field_0072;
   public static String field_0009;
   public static String field_0026;
   public static String field_0046;
   public static TextureAtlasSprite field_0071;
   public static String field_0008;
   public static TextureAtlasSprite field_0006;
   public static String field_0010;
   public static String field_0004;
   public static String field_0067;
   public static String field_0047;
   public static String field_0061;
   public static String field_0035;
   public static String field_0079;
   public static String field_0052;
   public static String field_0025;
   public static String field_0013;
   public static String field_0033;
   public static TextureAtlasSprite field_0024;
   public static TextureAtlasSprite field_0023;
   public static TextureAtlasSprite field_0022;
   public static TextureAtlasSprite field_0003;
   public static String field_0085;
   public static String field_0043;
   public static String field_0055;
   public static TextureAtlasSprite field_0039;
   public static IntBuffer staticBuffer = GLAllocation.createDirectIntBuffer(256);
   public static String field_0038;
   public static String field_0002;
   public static String field_0017;
   public static String field_0020;
   public static String field_0049;
   public static TextureAtlasSprite field_0053;
   public static TextureAtlasSprite field_0014;
   public static String field_0021;
   public static TextureAtlasSprite field_0068;
   public static String field_0056;
   public static String field_0084;
   public static String field_0066;
   public static String field_0001;
   public static String field_0036;
   public static String field_0050;
   public static TextureAtlasSprite field_0058;
   public static TextureAtlasSprite field_0005;
   public static String field_0045;
   public static String field_0034;
   public static String field_0080;
   public static String field_0032;
   public static String field_0054;
   public static String field_0000;
   public static TextureAtlasSprite field_0040;
   public static String field_0070;
   public static String field_0059;
   public static String field_0083;
   public static String field_0015;
   public static TextureAtlasSprite field_0063;
   public static String field_0076;
   public static String field_0027;
   public static String field_0064;
   public static String field_0082;
   public static String field_0060;
   public static String field_0029;
   public static String field_0075;
   public static String field_0078;
   public static String field_0077;
   public static TextureAtlasSprite field_0048;
   public static String field_0044;
   public static String field_0030;
   public static String field_0065;
   public static String field_0007;
   public static String field_0016;

   public static BufferedImage scaleImage(BufferedImage var0, int var1) {
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();
      int var4 = var3 * var1 / var2;
      BufferedImage var5 = new BufferedImage(var1, var4, 2);
      Graphics2D var6 = var5.createGraphics();
      Object var7 = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
      if (var1 < var2 || var1 % var2 != 0) {
         var7 = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
      }

      var6.setRenderingHint(RenderingHints.KEY_INTERPOLATION, var7);
      var6.drawImage(var0, 0, 0, var1, var4, (ImageObserver)null);
      return var5;
   }

   public static TextureMap getTextureMapBlocks() {
      return Minecraft.getMinecraft().getTextureMapBlocks();
   }

   public static boolean isPowerOfTwo(int var0) {
      int var1 = MathHelper.roundUpToPowerOfTwo(var0);
      return var1 == var0;
   }

   public static void registerResourceListener() {
      IResourceManager var0 = Config.getResourceManager();
      if (var0 instanceof IReloadableResourceManager) {
         IReloadableResourceManager var1 = (IReloadableResourceManager)var0;
         TextureUtils$1 var2 = new TextureUtils$1();
         var1.registerReloadListener(var2);
      }

      TextureUtils$2 var3 = new TextureUtils$2();
      ResourceLocation var4 = new ResourceLocation("optifine/TickableTextures");
      Config.getTextureManager().loadTickableTexture(var4, var3);
   }

   public static void saveGlTexture(String var0, int var1, int var2, int var3, int var4) {
      bindTexture(var1);
      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3317, 1);
      File var5 = new File(var0);
      File var6 = var5.getParentFile();
      if (var6 != null) {
         var6.mkdirs();
      }

      for (int var7 = 0; var7 < 16; var7++) {
         File var8 = new File(var0 + "_" + var7 + ".png");
         var8.delete();
      }

      for (int var17 = 0; var17 <= var2; var17++) {
         File var18 = new File(var0 + "_" + var17 + ".png");
         int var9 = var3 >> var17;
         int var10 = var4 >> var17;
         int var11 = var9 * var10;
         IntBuffer var12 = BufferUtils.createIntBuffer(var11);
         int[] var13 = new int[var11];
         GL11.glGetTexImage(3553, var17, 32993, 33639, var12);
         var12.get(var13);
         BufferedImage var14 = new BufferedImage(var9, var10, 2);
         var14.setRGB(0, 0, var9, var10, var13, 0, var9);

         try {
            ImageIO.write(var14, "png", var18);
            Config.dbg("Exported: " + var18);
         } catch (Exception var16) {
            Config.warn("Error writing: " + var18);
            Config.warn("" + var16.getClass().getName() + ": " + var16.getMessage());
         }
      }
   }

   public static String getBasePath(String var0) {
      int var1 = var0.lastIndexOf(47);
      return var1 < 0 ? "" : var0.substring(0, var1);
   }

   public static ResourceLocation fixResourceLocation(ResourceLocation var0, String var1) {
      if (!var0.getResourceDomain().equals("minecraft")) {
         return var0;
      } else {
         String var2 = var0.getResourcePath();
         String var3 = fixResourcePath(var2, var1);
         if (var3 != var2) {
            var0 = new ResourceLocation(var0.getResourceDomain(), var3);
         }

         return var0;
      }
   }

   public static void update() {
      TextureMap var0 = getTextureMapBlocks();
      if (var0 != null) {
         String var1 = "minecraft:blocks/";
         field_0040 = var0.getSpriteSafe(var1 + "grass_top");
         field_0006 = var0.getSpriteSafe(var1 + "grass_side");
         field_0073 = var0.getSpriteSafe(var1 + "grass_side_overlay");
         field_0063 = var0.getSpriteSafe(var1 + "snow");
         field_0023 = var0.getSpriteSafe(var1 + "grass_side_snowed");
         field_0058 = var0.getSpriteSafe(var1 + "mycelium_side");
         field_0022 = var0.getSpriteSafe(var1 + "mycelium_top");
         field_0024 = var0.getSpriteSafe(var1 + "water_still");
         field_0057 = var0.getSpriteSafe(var1 + "water_flow");
         field_0068 = var0.getSpriteSafe(var1 + "lava_still");
         field_0005 = var0.getSpriteSafe(var1 + "lava_flow");
         field_0053 = var0.getSpriteSafe(var1 + "fire_layer_0");
         field_0039 = var0.getSpriteSafe(var1 + "fire_layer_1");
         field_0071 = var0.getSpriteSafe(var1 + "portal");
         field_0048 = var0.getSpriteSafe(var1 + "glass");
         field_0014 = var0.getSpriteSafe(var1 + "glass_pane_top");
         String var2 = "minecraft:items/";
         field_0012 = var0.getSpriteSafe(var2 + "compass");
         field_0003 = var0.getSpriteSafe(var2 + "clock");
      }
   }

   public static ITextureObject getTexture(ResourceLocation var0) {
      ITextureObject var1 = Config.getTextureManager().getTexture(var0);
      if (var1 != null) {
         return var1;
      } else if (!Config.hasResource(var0)) {
         return null;
      } else {
         SimpleTexture var2 = new SimpleTexture(var0);
         Config.getTextureManager().loadTexture(var0, var2);
         return var2;
      }
   }

   public static int getPowerOfTwo(int var0) {
      byte var1 = 1;

      int var2;
      for (var2 = 0; var1 < var0; var2++) {
         var1 *= 2;
      }

      return var2;
   }

   public static int scaleToMin(int var0, int var1) {
      if (var0 >= var1) {
         return var0;
      } else {
         int var2 = var1 / var0 * var0;

         while (var2 < var1) {
            var2 += var0;
         }

         return var2;
      }
   }

   public static void generateCustomMipmaps(TextureAtlasSprite var0, int var1) {
      int var2 = var0.getIconWidth();
      int var3 = var0.getIconHeight();
      if (var0.getFrameCount() < 1) {
         ArrayList var4 = new ArrayList();
         int[][] var5 = new int[var1 + 1][];
         int[] var6 = new int[var2 * var3];
         var5[0] = var6;
         var4.add(var5);
         var0.setFramesTextureData(var4);
      }

      ArrayList var12 = new ArrayList();
      int var13 = var0.getFrameCount();

      for (int var14 = 0; var14 < var13; var14++) {
         int[] var7 = getFrameData(var0, var14, 0);
         if (var7 == null || var7.length < 1) {
            var7 = new int[var2 * var3];
         }

         if (var7.length != var2 * var3) {
            int var8 = (int)Math.round(Math.sqrt(var7.length));
            if (var8 * var8 != var7.length) {
               var7 = new int[1];
               var8 = 1;
            }

            BufferedImage var9 = new BufferedImage(var8, var8, 2);
            var9.setRGB(0, 0, var8, var8, var7, 0, var8);
            BufferedImage var10 = scaleImage(var9, var2);
            int[] var11 = new int[var2 * var3];
            var10.getRGB(0, 0, var2, var3, var11, 0, var2);
            var7 = var11;
         }

         int[][] var15 = new int[var1 + 1][];
         var15[0] = var7;
         var12.add(var15);
      }

      var0.setFramesTextureData(var12);
      var0.generateMipmaps(var1);
   }

   public static void dbgMipmaps(TextureAtlasSprite var0) {
      int[][] var1 = var0.getFrameTextureData(0);

      for (int var2 = 0; var2 < var1.length; var2++) {
         int[] var3 = var1[var2];
         if (var3 == null) {
            Config.dbg("" + var2 + ": " + var3);
         } else {
            Config.dbg("" + var2 + ": " + var3.length);
         }
      }
   }

   public static String fixResourcePath(String var0, String var1) {
      String var2 = "assets/minecraft/";
      if (var0.startsWith(var2)) {
         return var0.substring(var2.length());
      } else if (var0.startsWith("./")) {
         var0 = var0.substring(2);
         if (!var1.endsWith("/")) {
            var1 = var1 + "/";
         }

         return var1 + var0;
      } else {
         if (var0.startsWith("/~")) {
            var0 = var0.substring(1);
         }

         String var3 = "mcpatcher/";
         if (var0.startsWith("~/")) {
            var0 = var0.substring(2);
            return var3 + var0;
         } else {
            return var0.startsWith("/") ? var3 + var0.substring(1) : var0;
         }
      }
   }

   public static Dimension getImageSize(InputStream var0, String var1) {
      Iterator var2 = ImageIO.getImageReadersBySuffix(var1);

      while (var2.hasNext()) {
         ImageReader var3 = (ImageReader)var2.next();

         Dimension var4;
         try {
            ImageInputStream var5 = ImageIO.createImageInputStream(var0);
            var3.setInput(var5);
            int var6 = var3.getWidth(var3.getMinIndex());
            int var7 = var3.getHeight(var3.getMinIndex());
            var4 = new Dimension(var6, var7);
         } catch (IOException var11) {
            continue;
         } finally {
            var3.dispose();
         }

         return var4;
      }

      return null;
   }

   public static BufferedImage fixTextureDimensions(String var0, BufferedImage var1) {
      if (var0.startsWith("/mob/zombie") || var0.startsWith("/mob/pigzombie")) {
         int var2 = var1.getWidth();
         int var3 = var1.getHeight();
         if (var2 == var3 * 2) {
            BufferedImage var4 = new BufferedImage(var2, var3 * 2, 2);
            Graphics2D var5 = var4.createGraphics();
            var5.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            var5.drawImage(var1, 0, 0, var2, var3, (ImageObserver)null);
            return var4;
         }
      }

      return var1;
   }

   public static void resourcesReloaded(IResourceManager var0) {
      if (getTextureMapBlocks() != null) {
         Config.dbg("*** Reloading custom textures ***");
         CustomSky.method_23576();
         TextureAnimations.reset();
         update();
         NaturalTextures.update();
         BetterGrass.update();
         BetterSnow.update();
         TextureAnimations.update();
         CustomColors.update();
         CustomSky.update();
         RandomEntities.update();
         CustomItems.method_05227();
         CustomEntityModels.update();
         Shaders.resourcesReloaded();
         Lang.resourcesReloaded();
         Config.method_04005();
         SmartLeaves.updateLeavesModels();
         CustomPanorama.update();
         CustomGuis.update();
         LayerMooshroomMushroom.update();
         CustomLoadingScreens.update();
         CustomBlockLayers.update();
         Config.getTextureManager().tick();
      }
   }

   public static int getGLMaximumTextureSize() {
      for (int var0 = 65536; var0 > 0; var0 >>= 1) {
         GlStateManager.glTexImage2D(32868, 0, 6408, var0, var0, 0, 6408, 5121, (IntBuffer)null);
         int var1 = GL11.glGetError();
         int var2 = GlStateManager.glGetTexLevelParameteri(32868, 0, 4096);
         if (var2 != 0) {
            return var0;
         }
      }

      return -1;
   }

   public static void applyAnisotropicLevel() {
      if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
         float var0 = GL11.glGetFloat(34047);
         float var1 = Config.getAnisotropicFilterLevel();
         var1 = Math.min(var1, var0);
         GL11.glTexParameterf(3553, 34046, var1);
      }
   }

   public static int twoToPower(int var0) {
      byte var1 = 1;

      for (int var2 = 0; var2 < var0; var2++) {
         var1 *= 2;
      }

      return var1;
   }

   public static int ceilPowerOfTwo(int var0) {
      byte var1 = 1;

      while (var1 < var0) {
         var1 *= 2;
      }

      return var1;
   }

   public static int scaleToGrid(int var0, int var1) {
      if (var0 == var1) {
         return var0;
      } else {
         int var2 = var0 / var1 * var1;

         while (var2 < var0) {
            var2 += var1;
         }

         return var2;
      }
   }

   public static void bindTexture(int var0) {
      GlStateManager.bindTexture(var0);
   }

   public static int[] getFrameData(TextureAtlasSprite var0, int var1, int var2) {
      List var3 = var0.getFramesTextureData();
      if (var3.size() <= var1) {
         return null;
      } else {
         int[][] var4 = (int[][])var3.get(var1);
         return var4 != null && var4.length > var2 ? var4[var2] : null;
      }
   }
}
