package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructure$3;
import net.optifine.Mipmaps;
import net.optifine.reflect.Reflector;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class TextureUtil {
   public static int[] mipmapBuffer;
   public MapGenStructure$3 field_0005;
   public static int[] dataArray = new int[4194304];
   public static IntBuffer dataBuffer = GLAllocation.createDirectIntBuffer(4194304);
   public static Logger logger = LogManager.getLogger();
   public static DynamicTexture missingTexture = new DynamicTexture(16, 16);
   public static int[] missingTextureData = missingTexture.getTextureData();

   public static int blendColors(int var0, int var1, int var2, int var3, boolean var4) {
      return Mipmaps.alphaBlend(var0, var1, var2, var3);
   }

   public static void uploadTextureImageSubImpl(BufferedImage var0, int var1, int var2, boolean var3, boolean var4) {
      int var5 = var0.getWidth();
      int var6 = var0.getHeight();
      int var7 = 4194304 / var5;
      int[] var8 = dataArray;
      setTextureBlurred(var3);
      setTextureClamped(var4);

      for (int var9 = 0; var9 < var5 * var6; var9 += var5 * var7) {
         int var10 = var9 / var5;
         int var11 = Math.min(var7, var6 - var10);
         int var12 = var5 * var11;
         var0.getRGB(0, var10, var5, var11, var8, 0, var5);
         copyToBuffer(var8, var12);
         GL11.glTexSubImage2D(3553, 0, var1, var2 + var10, var5, var11, 32993, 33639, dataBuffer);
      }
   }

   public static int blendColorComponent(int var0, int var1, int var2, int var3, int var4) {
      float var5 = (float)Math.pow((var0 >> var4 & 0xFF) / 255.0F, 2.2);
      float var6 = (float)Math.pow((var1 >> var4 & 0xFF) / 255.0F, 2.2);
      float var7 = (float)Math.pow((var2 >> var4 & 0xFF) / 255.0F, 2.2);
      float var8 = (float)Math.pow((var3 >> var4 & 0xFF) / 255.0F, 2.2);
      float var9 = (float)Math.pow((var5 + var6 + var7 + var8) * 0.25, 0.45454545454545453);
      return (int)(var9 * 255.0);
   }

   public static int anaglyphColor(int var0) {
      int var1 = var0 >> 24 & 0xFF;
      int var2 = var0 >> 16 & 0xFF;
      int var3 = var0 >> 8 & 0xFF;
      int var4 = var0 & 0xFF;
      int var5 = (var2 * 30 + var3 * 59 + var4 * 11) / 100;
      int var6 = (var2 * 30 + var3 * 70) / 100;
      int var7 = (var2 * 30 + var4 * 70) / 100;
      return var1 << 24 | var5 << 16 | var6 << 8 | var7;
   }

   public static void bindTexture(int var0) {
      GlStateManager.bindTexture(var0);
   }

   public static void setTextureBlurMipmap(boolean var0, boolean var1) {
      if (var0) {
         GL11.glTexParameteri(3553, 10241, var1 ? 9987 : 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
      } else {
         int var2 = Config.getMipmapType();
         GL11.glTexParameteri(3553, 10241, var1 ? var2 : 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
      }
   }

   public static int uploadTextureImageSub(int var0, BufferedImage var1, int var2, int var3, boolean var4, boolean var5) {
      bindTexture(var0);
      uploadTextureImageSubImpl(var1, var2, var3, var4, var5);
      return var0;
   }

   public static void copyToBuffer(int[] var0, int var1) {
      copyToBufferPos(var0, 0, var1);
   }

   public static void allocateTexture(int var0, int var1, int var2) {
      allocateTextureImpl(var0, 0, var1, var2);
   }

   public static void deleteTexture(int var0) {
      GlStateManager.deleteTexture(var0);
   }

   public static void setTextureBlurred(boolean var0) {
      setTextureBlurMipmap(var0, false);
   }

   public static void uploadTextureSub(int var0, int[] var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7, boolean var8) {
      int var9 = 4194304 / var2;
      setTextureBlurMipmap(var6, var8);
      setTextureClamped(var7);
      int var11 = 0;

      while (var11 < var2 * var3) {
         int var12 = var11 / var2;
         int var10 = Math.min(var9, var3 - var12);
         int var13 = var2 * var10;
         copyToBufferPos(var1, var11, var13);
         GL11.glTexSubImage2D(3553, var0, var4, var5 + var12, var2, var10, 32993, 33639, dataBuffer);
         var11 += var2 * var10;
      }
   }

   public static int[] readImageData(IResourceManager var0, ResourceLocation var1) {
      BufferedImage var2 = readBufferedImage(var0.getResource(var1).getInputStream());
      if (var2 == null) {
         return null;
      } else {
         int var3 = var2.getWidth();
         int var4 = var2.getHeight();
         int[] var5 = new int[var3 * var4];
         var2.getRGB(0, 0, var3, var4, var5, 0, var3);
         return var5;
      }
   }

   static {
      int var0 = -16777216;
      int var1 = -524040;
      int[] var2 = new int[]{-524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040};
      int[] var3 = new int[]{-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216};
      int var4 = var2.length;

      for (int var5 = 0; var5 < 16; var5++) {
         System.arraycopy(var5 < var4 ? var2 : var3, 0, missingTextureData, 16 * var5, var4);
         System.arraycopy(var5 < var4 ? var3 : var2, 0, missingTextureData, 16 * var5 + var4, var4);
      }

      missingTexture.updateDynamicTexture();
      mipmapBuffer = new int[4];
   }

   public static void uploadTextureMipmap(int[][] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      for (int var7 = 0; var7 < var0.length; var7++) {
         int[] var8 = var0[var7];
         uploadTextureSub(var7, var8, var1 >> var7, var2 >> var7, var3 >> var7, var4 >> var7, var5, var6, var0.length > 1);
      }
   }

   public static void allocateTextureImpl(int var0, int var1, int var2, int var3) {
      Class<TextureUtil> var4 = TextureUtil.class;
      if (Reflector.SplashScreen.exists()) {
         var4 = Reflector.SplashScreen.getTargetClass();
      }

      synchronized (var4) {
         deleteTexture(var0);
         bindTexture(var0);
      }

      if (var1 >= 0) {
         GL11.glTexParameteri(3553, 33085, var1);
         GL11.glTexParameterf(3553, 33082, 0.0F);
         GL11.glTexParameterf(3553, 33083, var1);
         GL11.glTexParameterf(3553, 34049, 0.0F);
      }

      for (int var5 = 0; var5 <= var1; var5++) {
         GL11.glTexImage2D(3553, var5, 6408, var2 >> var5, var3 >> var5, 0, 32993, 33639, (IntBuffer)null);
      }
   }

   public static void setTextureClamped(boolean var0) {
      if (var0) {
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
      } else {
         GL11.glTexParameteri(3553, 10242, 10497);
         GL11.glTexParameteri(3553, 10243, 10497);
      }
   }

   public static void uploadTexture(int var0, int[] var1, int var2, int var3) {
      bindTexture(var0);
      uploadTextureSub(0, var1, var2, var3, 0, 0, false, false, false);
   }

   public static int glGenTextures() {
      return GlStateManager.generateTexture();
   }

   public static void processPixelValues(int[] var0, int var1, int var2) {
      int[] var3 = new int[var1];
      int var4 = var2 / 2;

      for (int var5 = 0; var5 < var4; var5++) {
         System.arraycopy(var0, var5 * var1, var3, 0, var1);
         System.arraycopy(var0, (var2 - 1 - var5) * var1, var0, var5 * var1, var1);
         System.arraycopy(var3, 0, var0, (var2 - 1 - var5) * var1, var1);
      }
   }

   public static void copyToBufferPos(int[] var0, int var1, int var2) {
      int[] var3 = var0;
      if (Minecraft.getMinecraft().gameSettings.anaglyph) {
         var3 = updateAnaglyph(var0);
      }

      ((Buffer)dataBuffer).clear();
      dataBuffer.put(var3, var1, var2);
      ((Buffer)dataBuffer).position(0).limit(var2);
   }

   public static int uploadTextureImageAllocate(int var0, BufferedImage var1, boolean var2, boolean var3) {
      allocateTexture(var0, var1.getWidth(), var1.getHeight());
      return uploadTextureImageSub(var0, var1, 0, 0, var2, var3);
   }

   public static BufferedImage readBufferedImage(InputStream var0) {
      if (var0 == null) {
         return null;
      } else {
         BufferedImage var1;
         try {
            var1 = ImageIO.read(var0);
         } finally {
            IOUtils.closeQuietly(var0);
         }

         return var1;
      }
   }

   public static int[][] generateMipmapData(int var0, int var1, int[][] var2) {
      int[][] var3 = new int[var0 + 1][];
      var3[0] = var2[0];
      if (var0 > 0) {
         boolean var4 = false;

         for (int var5 = 0; var5 < var2[0].length; var5++) {
            if (var2[0][var5] >> 24 == 0) {
               var4 = true;
               break;
            }
         }

         for (int var14 = 1; var14 <= var0; var14++) {
            if (var2[var14] != null) {
               var3[var14] = var2[var14];
            } else {
               int[] var6 = var3[var14 - 1];
               int[] var7 = new int[var6.length >> 2];
               int var8 = var1 >> var14;
               int var9 = var7.length / var8;
               int var10 = var8 << 1;

               for (int var11 = 0; var11 < var8; var11++) {
                  for (int var12 = 0; var12 < var9; var12++) {
                     int var13 = 2 * (var11 + var12 * var10);
                     var7[var11 + var12 * var8] = blendColors(var6[var13 + 0], var6[var13 + 1], var6[var13 + 0 + var10], var6[var13 + 1 + var10], var4);
                  }
               }

               var3[var14] = var7;
            }
         }
      }

      return var3;
   }

   public static int[] updateAnaglyph(int[] var0) {
      int[] var1 = new int[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = anaglyphColor(var0[var2]);
      }

      return var1;
   }

   public static int uploadTextureImage(int var0, BufferedImage var1) {
      return uploadTextureImageAllocate(var0, var1, false, false);
   }
}
