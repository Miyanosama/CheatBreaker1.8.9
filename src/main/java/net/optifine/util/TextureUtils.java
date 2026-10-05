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
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
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
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TextureUtils {
   public static final String recoveredField1048 = "dirt";
   public static TextureAtlasSprite recoveredField1049;
   public static final String recoveredField1050 = "farmland_dry";
   public static final String recoveredField1051 = "leaves_birch";
   public static TextureAtlasSprite recoveredField1052;
   public static final String recoveredField1053 = "soul_sand";
   public static final String recoveredField1054 = "leaves_spruce";
   public static TextureAtlasSprite recoveredField1055;
   public static final String recoveredField1056 = "sandstone_bottom";
   public static final String recoveredField1057 = "log_birch";
   public static final String recoveredField1058 = "iron_ore";
   public static final String recoveredField1059 = "leaves_jungle";
   public static final String recoveredField1060 = "coal_ore";
   public static final String recoveredField1061 = "gravel";
   public static final String recoveredField1062 = "coarse_dirt";
   public static final String recoveredField1063 = "lapis_ore";
   public static final String recoveredField1064 = "stone";
   public static final String recoveredField1065 = "leaves_spruce";
   public static final String recoveredField1066 = "grass_side_snowed";
   public static TextureAtlasSprite recoveredField1067;
   public static final String recoveredField1068 = "leaves_spruce_opaque";
   public static TextureAtlasSprite recoveredField1069;
   public static final String recoveredField1070 = "clay";
   public static final String recoveredField1071 = "glass";
   public static final String recoveredField1072 = "water_flow";
   public static final String recoveredField1073 = "bedrock";
   public static final String recoveredField1074 = "mycelium_top";
   public static final String recoveredField1075 = "log_jungle_top";
   public static final String recoveredField1076 = "leaves_acacia";
   public static final String recoveredField1077 = "compass";
   public static final String recoveredField1078 = "log_big_oak_top";
   public static final String recoveredField1079 = "redstone_ore";
   public static final String recoveredField1080 = "log_spruce";
   public static TextureAtlasSprite recoveredField1081;
   public static TextureAtlasSprite recoveredField1082;
   public static TextureAtlasSprite recoveredField1083;
   public static TextureAtlasSprite recoveredField1084;
   public static final String recoveredField1085 = "fire_layer_0";
   public static final String recoveredField1086 = "cactus_side";
   public static final String recoveredField1087 = "obsidian";
   public static TextureAtlasSprite recoveredField1088;
   public static final String recoveredField1089 = "leaves_oak";
   public static final String recoveredField1090 = "stone_slab_top";
   public static final String recoveredField1091 = "gold_ore";
   public static final String recoveredField1092 = "lava_flow";
   public static final String recoveredField1093 = "water_still";
   public static final String recoveredField1096 = "minecraft:items/";
   public static TextureAtlasSprite recoveredField1094;
   public static TextureAtlasSprite recoveredField1095;
   public static final String recoveredField1098 = "log_birch_top";
   public static TextureAtlasSprite recoveredField1097;
   public static final String recoveredField1099 = "redstone_lamp_off";
   public static final String recoveredField1100 = "log_big_oak";
   public static final String recoveredField1101 = "log_oak_top";
   public static final String recoveredField1102 = "glowstone";
   public static final String recoveredField1103 = "lava_still";
   public static final String recoveredField1106 = "redstone_lamp_on";
   public static TextureAtlasSprite recoveredField1104;
   public static TextureAtlasSprite recoveredField1105;
   public static final String recoveredField1107 = "portal";
   public static final String recoveredField1108 = "log_spruce_top";
   public static final String recoveredField1109 = "fire_layer_1";
   public static final String recoveredField1110 = "netherrack";
   public static final String recoveredField1111 = "clock";
   public static final String recoveredField1113 = "leaves_big_oak";
   public static TextureAtlasSprite recoveredField1112;
   public static final String recoveredField1114 = "minecraft:blocks/";
   public static final String recoveredField1115 = "diamond_ore";
   public static final String recoveredField1116 = "grass_side";
   public static final String recoveredField1118 = "glass_pane_top";
   public static TextureAtlasSprite recoveredField1117;
   public static final String recoveredField1119 = "grass_top";
   public static final String recoveredField1120 = "log_acacia";
   public static final String recoveredField1121 = "sandstone_top";
   public static final String recoveredField1122 = "grass_side_overlay";
   public static final String recoveredField1123 = "log_jungle";
   public static final String recoveredField1124 = "log_oak";
   public static final String recoveredField1125 = "log_acacia_top";
   public static final String recoveredField1126 = "end_stone";
   public static final String recoveredField1128 = "mycelium_side";
   public static TextureAtlasSprite recoveredField1127;
   public static final String recoveredField1129 = "sand";
   public static final String recoveredField1130 = "snow";
   public static final String recoveredField1131 = "farmland_wet";
   public static final String recoveredField1132 = "stone_slab_side";
   public static IntBuffer staticBuffer = GLAllocation.createDirectIntBuffer(256);

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
         IResourceManagerReloadListener var2 = new IResourceManagerReloadListener() {
            @Override
            public void onResourceManagerReload(IResourceManager var1) {
               TextureUtils.resourcesReloaded(var1);
            }
         };
         var1.registerReloadListener(var2);
      }

      ITickableTextureObject var3 = new ITickableTextureObject() {
         @Override
         public void setBlurMipmap(boolean var1, boolean var2) {
         }

         @Override
         public MultiTexID getMultiTexID() {
            return null;
         }

         @Override
         public void tick() {
            TextureAnimations.updateAnimations();
         }

         @Override
         public void restoreLastBlurMipmap() {
         }

         @Override
         public int getGlTextureId() {
            return 0;
         }

         @Override
         public void loadTexture(IResourceManager var1) throws java.io.IOException {
         }
      };
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
         recoveredField1112 = var0.getSpriteSafe(var1 + "grass_top");
         recoveredField1069 = var0.getSpriteSafe(var1 + "grass_side");
         recoveredField1049 = var0.getSpriteSafe(var1 + "grass_side_overlay");
         recoveredField1117 = var0.getSpriteSafe(var1 + "snow");
         recoveredField1082 = var0.getSpriteSafe(var1 + "grass_side_snowed");
         recoveredField1104 = var0.getSpriteSafe(var1 + "mycelium_side");
         recoveredField1083 = var0.getSpriteSafe(var1 + "mycelium_top");
         recoveredField1081 = var0.getSpriteSafe(var1 + "water_still");
         recoveredField1055 = var0.getSpriteSafe(var1 + "water_flow");
         recoveredField1097 = var0.getSpriteSafe(var1 + "lava_still");
         recoveredField1105 = var0.getSpriteSafe(var1 + "lava_flow");
         recoveredField1094 = var0.getSpriteSafe(var1 + "fire_layer_0");
         recoveredField1088 = var0.getSpriteSafe(var1 + "fire_layer_1");
         recoveredField1067 = var0.getSpriteSafe(var1 + "portal");
         recoveredField1127 = var0.getSpriteSafe(var1 + "glass");
         recoveredField1095 = var0.getSpriteSafe(var1 + "glass_pane_top");
         String var2 = "minecraft:items/";
         recoveredField1052 = var0.getSpriteSafe(var2 + "compass");
         recoveredField1084 = var0.getSpriteSafe(var2 + "clock");
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
      int var1 = 1;

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
      int var1 = 1;

      for (int var2 = 0; var2 < var0; var2++) {
         var1 *= 2;
      }

      return var1;
   }

   public static int ceilPowerOfTwo(int var0) {
      int var1 = 1;

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
