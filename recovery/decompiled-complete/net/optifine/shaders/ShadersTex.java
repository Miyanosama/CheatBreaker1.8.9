package net.optifine.shaders;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToDoubleTask;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ShadersTex {
   public static int field_0005;
   public static ByteBuffer byteBuffer = BufferUtils.createByteBuffer(4194304);
   public static Map<Integer, MultiTexID> multiTexMap = new HashMap<>();
   public SimpleShaderTexture field_0008;
   public static int field_0001;
   public static int field_0002;
   public NumberInvalidException field_0010;
   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask field_0007;
   public static int field_0003;
   public static int[] intArray = new int[1048576];
   public static IntBuffer intBuffer = byteBuffer.asIntBuffer();
   public JsonBlendingMode field_0006;

   public static int log2(int var0) {
      int var1 = 0;
      if ((var0 & -65536) != 0) {
         var1 += 16;
         var0 >>= 16;
      }

      if ((var0 & 0xFF00) != 0) {
         var1 += 8;
         var0 >>= 8;
      }

      if ((var0 & 240) != 0) {
         var1 += 4;
         var0 >>= 4;
      }

      if ((var0 & 6) != 0) {
         var1 += 2;
         var0 >>= 2;
      }

      if ((var0 & 2) != 0) {
         var1++;
      }

      return var1;
   }

   public static void bindTextures(int var0, int var1, int var2) {
      if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
         GlStateManager.setActiveTexture(33986);
         GlStateManager.bindTexture(var1);
         GlStateManager.setActiveTexture(33987);
         GlStateManager.bindTexture(var2);
         GlStateManager.setActiveTexture(33984);
      }

      GlStateManager.bindTexture(var0);
   }

   public static void bindNSTextures(int var0, int var1) {
      if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
         GlStateManager.setActiveTexture(33986);
         GlStateManager.bindTexture(var0);
         GlStateManager.setActiveTexture(33987);
         GlStateManager.bindTexture(var1);
         GlStateManager.setActiveTexture(33984);
      }
   }

   public static void bindTexture(ITextureObject var0) {
      int var1 = var0.getGlTextureId();
      bindTextures(var0.getMultiTexID());
      if (GlStateManager.getActiveTextureUnit() == 33984) {
         int var2 = Shaders.atlasSizeX;
         int var3 = Shaders.atlasSizeY;
         if (var0 instanceof TextureMap) {
            Shaders.atlasSizeX = ((TextureMap)var0).atlasWidth;
            Shaders.atlasSizeY = ((TextureMap)var0).atlasHeight;
         } else {
            Shaders.atlasSizeX = 0;
            Shaders.atlasSizeY = 0;
         }

         if (Shaders.atlasSizeX != var2 || Shaders.atlasSizeY != var3) {
            Shaders.uniform_atlasSize.setValue(Shaders.atlasSizeX, Shaders.atlasSizeY);
         }
      }
   }

   public static void uploadTexSubForLoadAtlas(TextureMap var0, String var1, int[][] var2, int var3, int var4, int var5, int var6, boolean var7, boolean var8) {
      MultiTexID var9 = var0.multiTex;
      TextureUtil.uploadTextureMipmap(var2, var3, var4, var5, var6, var7, var8);
      boolean var10 = false;
      if (Shaders.configNormalMap) {
         int[][] var11 = readImageAndMipmaps(var0, var1 + "_n", var3, var4, var2.length, var10, -8421377);
         GlStateManager.bindTexture(var9.norm);
         TextureUtil.uploadTextureMipmap(var11, var3, var4, var5, var6, var7, var8);
      }

      if (Shaders.configSpecularMap) {
         int[][] var12 = readImageAndMipmaps(var0, var1 + "_s", var3, var4, var2.length, var10, 0);
         GlStateManager.bindTexture(var9.spec);
         TextureUtil.uploadTextureMipmap(var12, var3, var4, var5, var6, var7, var8);
      }

      GlStateManager.bindTexture(var9.base);
   }

   public static ResourceLocation getNSMapLocation(ResourceLocation var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         String var2 = var0.getResourcePath();
         String[] var3 = var2.split(".png");
         String var4 = var3[0];
         return new ResourceLocation(var0.getResourceDomain(), var4 + "_" + var1 + ".png");
      }
   }

   public static void mergeImage(int[] var0, int var1, int var2, int var3) {
   }

   public static MultiTexID getMultiTexID(AbstractTexture var0) {
      MultiTexID var1 = var0.multiTex;
      if (var1 == null) {
         int var2 = var0.getGlTextureId();
         var1 = multiTexMap.get(var2);
         if (var1 == null) {
            var1 = new MultiTexID(var2, GL11.glGenTextures(), GL11.glGenTextures());
            multiTexMap.put(var2, var1);
         }

         var0.multiTex = var1;
      }

      return var1;
   }

   public static int[][] readImageAndMipmaps(TextureMap var0, String var1, int var2, int var3, int var4, boolean var5, int var6) {
      MultiTexID var7 = var0.multiTex;
      int[][] var8 = new int[var4][];
      int[] var9;
      var8[0] = var9 = new int[var2 * var3];
      boolean var10 = false;
      BufferedImage var11 = readImage(var0.completeResourceLocation(new ResourceLocation(var1)));
      if (var11 != null) {
         int var12 = var11.getWidth();
         int var13 = var11.getHeight();
         if (var12 + (var5 ? 16 : 0) == var2) {
            var10 = true;
            var11.getRGB(0, 0, var12, var12, var9, 0, var12);
         }
      }

      if (!var10) {
         Arrays.fill(var9, var6);
      }

      GlStateManager.bindTexture(var7.spec);
      return genMipmapsSimple(var8.length - 1, var2, var8);
   }

   public static void updateTextureMinMagFilter() {
      TextureManager var0 = Minecraft.getMinecraft().getTextureManager();
      ITextureObject var1 = var0.getTexture(TextureMap.locationBlocksTexture);
      if (var1 != null) {
         MultiTexID var2 = var1.getMultiTexID();
         GlStateManager.bindTexture(var2.base);
         GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilB]);
         GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilB]);
         GlStateManager.bindTexture(var2.norm);
         GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilN]);
         GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilN]);
         GlStateManager.bindTexture(var2.spec);
         GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilS]);
         GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilS]);
         GlStateManager.bindTexture(0);
      }
   }

   public static void updateDynTexSubImage1(int[] var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = var1 * var2;
      IntBuffer var7 = getIntBuffer(var6);
      ((Buffer)var7).clear();
      int var8 = var5 * var6;
      if (var0.length >= var8 + var6) {
         ((Buffer)var7.put(var0, var8, var6)).position(0).limit(var6);
         GL11.glTexSubImage2D(3553, 0, var3, var4, var1, var2, 32993, 33639, var7);
         ((Buffer)var7).clear();
      }
   }

   public static void fixTransparentColor(TextureAtlasSprite var0, int[] var1) {
   }

   public static void updateDynamicTexture(int var0, int[] var1, int var2, int var3, DynamicTexture var4) {
      MultiTexID var5 = var4.getMultiTexID();
      GlStateManager.bindTexture(var5.base);
      updateDynTexSubImage1(var1, var2, var3, 0, 0, 0);
      GlStateManager.bindTexture(var5.norm);
      updateDynTexSubImage1(var1, var2, var3, 0, 0, 1);
      GlStateManager.bindTexture(var5.spec);
      updateDynTexSubImage1(var1, var2, var3, 0, 0, 2);
      GlStateManager.bindTexture(var5.base);
   }

   public static int[] createAIntImage(int var0, int var1) {
      int[] var2 = new int[var0 * 3];
      Arrays.fill(var2, 0, var0, var1);
      Arrays.fill(var2, var0, var0 * 2, -8421377);
      Arrays.fill(var2, var0 * 2, var0 * 3, 0);
      return var2;
   }

   public static int[][] genMipmapsSimple(int var0, int var1, int[][] var2) {
      for (int var3 = 1; var3 <= var0; var3++) {
         if (var2[var3] == null) {
            int var4 = var1 >> var3;
            int var5 = var4 * 2;
            int[] var6 = var2[var3 - 1];
            int[] var7 = var2[var3] = new int[var4 * var4];

            for (int var8 = 0; var8 < var4; var8++) {
               for (int var9 = 0; var9 < var4; var9++) {
                  int var10 = var8 * 2 * var5 + var9 * 2;
                  var7[var8 * var4 + var9] = blend4Simple(var6[var10], var6[var10 + 1], var6[var10 + var5], var6[var10 + var5 + 1]);
               }
            }
         }
      }

      return var2;
   }

   public static void updateSubTex1(int[] var0, int var1, int var2, int var3, int var4) {
      int var5 = 0;
      int var6 = var1;
      int var7 = var2;
      int var8 = var3;

      for (int var9 = var4; var6 > 0 && var7 > 0; var9 /= 2) {
         GL11.glCopyTexSubImage2D(3553, var5, var8, var9, 0, 0, var6, var7);
         var5++;
         var6 /= 2;
         var7 /= 2;
         var8 /= 2;
      }
   }

   public static void uploadTexSub1(int[][] var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = var1 * var2;
      IntBuffer var7 = getIntBuffer(var6);
      int var8 = var0.length;
      int var9 = 0;
      int var10 = var1;
      int var11 = var2;
      int var12 = var3;

      for (int var13 = var4; var10 > 0 && var11 > 0 && var9 < var8; var9++) {
         int var14 = var10 * var11;
         int[] var15 = var0[var9];
         ((Buffer)var7).clear();
         if (var15.length >= var14 * (var5 + 1)) {
            ((Buffer)var7.put(var15, var14 * var5, var14)).position(0).limit(var14);
            GL11.glTexSubImage2D(3553, var9, var12, var13, var10, var11, 32993, 33639, var7);
         }

         var10 >>= 1;
         var11 >>= 1;
         var12 >>= 1;
         var13 >>= 1;
      }

      ((Buffer)var7).clear();
   }

   public static void bindTextures(int var0) {
      MultiTexID var1 = multiTexMap.get(var0);
      bindTextures(var1);
   }

   public static void loadLayeredTexture(LayeredTexture var0, IResourceManager var1, List var2) {
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int[] var6 = null;

      for (Object var8 : var2) {
         if (var8 != null) {
            try {
               ResourceLocation var9 = new ResourceLocation((String)var8);
               InputStream var10 = var1.getResource(var9).getInputStream();
               BufferedImage var11 = ImageIO.read(var10);
               if (var5 == 0) {
                  var3 = var11.getWidth();
                  var4 = var11.getHeight();
                  var5 = var3 * var4;
                  var6 = createAIntImage(var5, 0);
               }

               int[] var12 = getIntArray(var5 * 3);
               var11.getRGB(0, 0, var3, var4, var12, 0, var3);
               loadNSMap(var1, var9, var3, var4, var12);

               for (int var13 = 0; var13 < var5; var13++) {
                  int var14 = var12[var13] >>> 24 & 0xFF;
                  var6[var5 * 0 + var13] = blendColor(var12[var5 * 0 + var13], var6[var5 * 0 + var13], var14);
                  var6[var5 * 1 + var13] = blendColor(var12[var5 * 1 + var13], var6[var5 * 1 + var13], var14);
                  var6[var5 * 2 + var13] = blendColor(var12[var5 * 2 + var13], var6[var5 * 2 + var13], var14);
               }
            } catch (IOException var15) {
               var15.printStackTrace();
            }
         }
      }

      setupTexture(var0.getMultiTexID(), var6, var3, var4, false, false);
   }

   public static void updateSubImage(MultiTexID var0, int[] var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7) {
      int var8 = var2 * var3;
      IntBuffer var9 = getIntBuffer(var8);
      ((Buffer)var9).clear();
      var9.put(var1, 0, var8);
      ((Buffer)var9).position(0).limit(var8);
      GlStateManager.bindTexture(var0.base);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, var4, var5, var2, var3, 32993, 33639, var9);
      if (var1.length == var8 * 3) {
         ((Buffer)var9).clear();
         ((Buffer)var9.put(var1, var8, var8)).position(0);
         ((Buffer)var9).position(0).limit(var8);
      }

      GlStateManager.bindTexture(var0.norm);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, var4, var5, var2, var3, 32993, 33639, var9);
      if (var1.length == var8 * 3) {
         ((Buffer)var9).clear();
         var9.put(var1, var8 * 2, var8);
         ((Buffer)var9).position(0).limit(var8);
      }

      GlStateManager.bindTexture(var0.spec);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, var4, var5, var2, var3, 32993, 33639, var9);
      GlStateManager.setActiveTexture(33984);
   }

   public static int[][] prepareAF(TextureAtlasSprite var0, int[][] var1, int var2, int var3) {
      boolean var4 = true;
      return var1;
   }

   public static void setupTexture(MultiTexID var0, int[] var1, int var2, int var3, boolean var4, boolean var5) {
      int var6 = var4 ? 9729 : 9728;
      int var7 = var5 ? '脯' : 10497;
      int var8 = var2 * var3;
      IntBuffer var9 = getIntBuffer(var8);
      ((Buffer)var9).clear();
      ((Buffer)var9.put(var1, 0, var8)).position(0).limit(var8);
      GlStateManager.bindTexture(var0.base);
      GL11.glTexImage2D(3553, 0, 6408, var2, var3, 0, 32993, 33639, var9);
      GL11.glTexParameteri(3553, 10241, var6);
      GL11.glTexParameteri(3553, 10240, var6);
      GL11.glTexParameteri(3553, 10242, var7);
      GL11.glTexParameteri(3553, 10243, var7);
      ((Buffer)var9.put(var1, var8, var8)).position(0).limit(var8);
      GlStateManager.bindTexture(var0.norm);
      GL11.glTexImage2D(3553, 0, 6408, var2, var3, 0, 32993, 33639, var9);
      GL11.glTexParameteri(3553, 10241, var6);
      GL11.glTexParameteri(3553, 10240, var6);
      GL11.glTexParameteri(3553, 10242, var7);
      GL11.glTexParameteri(3553, 10243, var7);
      ((Buffer)var9.put(var1, var8 * 2, var8)).position(0).limit(var8);
      GlStateManager.bindTexture(var0.spec);
      GL11.glTexImage2D(3553, 0, 6408, var2, var3, 0, 32993, 33639, var9);
      GL11.glTexParameteri(3553, 10241, var6);
      GL11.glTexParameteri(3553, 10240, var6);
      GL11.glTexParameteri(3553, 10242, var7);
      GL11.glTexParameteri(3553, 10243, var7);
      GlStateManager.bindTexture(var0.base);
   }

   public static void bindTextures(MultiTexID var0) {
      if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
         if (Shaders.configNormalMap) {
            GlStateManager.setActiveTexture(33986);
            GlStateManager.bindTexture(var0.norm);
         }

         if (Shaders.configSpecularMap) {
            GlStateManager.setActiveTexture(33987);
            GlStateManager.bindTexture(var0.spec);
         }

         GlStateManager.setActiveTexture(33984);
      }

      GlStateManager.bindTexture(var0.base);
   }

   public static int[] getIntArray(int var0) {
      if (intArray == null) {
         intArray = new int[1048576];
      }

      if (intArray.length < var0) {
         intArray = new int[roundUpPOT(var0)];
      }

      return intArray;
   }

   public static int blend4Simple(int var0, int var1, int var2, int var3) {
      return ((var0 >>> 24 & 0xFF) + (var1 >>> 24 & 0xFF) + (var2 >>> 24 & 0xFF) + (var3 >>> 24 & 0xFF) + 2) / 4 << 24
         | ((var0 >>> 16 & 0xFF) + (var1 >>> 16 & 0xFF) + (var2 >>> 16 & 0xFF) + (var3 >>> 16 & 0xFF) + 2) / 4 << 16
         | ((var0 >>> 8 & 0xFF) + (var1 >>> 8 & 0xFF) + (var2 >>> 8 & 0xFF) + (var3 >>> 8 & 0xFF) + 2) / 4 << 8
         | ((var0 >>> 0 & 0xFF) + (var1 >>> 0 & 0xFF) + (var2 >>> 0 & 0xFF) + (var3 >>> 0 & 0xFF) + 2) / 4 << 0;
   }

   public static boolean isSemiTransparent(int[] var0, int var1, int var2) {
      int var3 = var1 * var2;
      if (var0[0] >>> 24 == 255 && var0[var3 - 1] == 0) {
         return true;
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = var0[var4] >>> 24;
            if (var5 != 0 && var5 != 255) {
               return true;
            }
         }

         return false;
      }
   }

   public static int[][] getFrameTexData(int[][] var0, int var1, int var2, int var3) {
      int var4 = var0.length;
      int[][] var5 = new int[var4][];

      for (int var6 = 0; var6 < var4; var6++) {
         int[] var7 = var0[var6];
         if (var7 != null) {
            int var8 = (var1 >> var6) * (var2 >> var6);
            int[] var9 = new int[var8 * 3];
            var5[var6] = var9;
            int var10 = var7.length / 3;
            int var11 = var8 * var3;
            int var12 = 0;
            System.arraycopy(var7, var11, var9, var12, var8);
            var11 += var10;
            var12 += var8;
            System.arraycopy(var7, var11, var9, var12, var8);
            var11 += var10;
            var12 += var8;
            System.arraycopy(var7, var11, var9, var12, var8);
         }
      }

      return var5;
   }

   public static int roundUpPOT(int var0) {
      int var1 = var0 - 1;
      var1 |= var1 >> 1;
      var1 |= var1 >> 2;
      var1 |= var1 >> 4;
      var1 |= var1 >> 8;
      var1 |= var1 >> 16;
      return var1 + 1;
   }

   public static void method_03509(int[] var0, int var1, int var2, int var3) {
      Math.min(var2, var3);
      int var4 = var1;
      int var5 = var2;
      int var6 = var3;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;

      int var10;
      for (var10 = 0; var5 > 1 && var6 > 1; var4 = var7) {
         var7 = var4 + var5 * var6;
         var8 = var5 / 2;
         var9 = var6 / 2;

         for (int var11 = 0; var11 < var9; var11++) {
            int var12 = var7 + var11 * var8;
            int var13 = var4 + var11 * 2 * var5;

            for (int var14 = 0; var14 < var8; var14++) {
               var0[var12 + var14] = blend4Alpha(
                  var0[var13 + var14 * 2], var0[var13 + var14 * 2 + 1], var0[var13 + var5 + var14 * 2], var0[var13 + var5 + var14 * 2 + 1]
               );
            }
         }

         var10++;
         var5 = var8;
         var6 = var9;
      }

      while (var10 > 0) {
         var5 = var2 >> --var10;
         var6 = var3 >> var10;
         var4 = var7 - var5 * var6;
         int var19 = var4;

         for (int var20 = 0; var20 < var6; var20++) {
            for (int var21 = 0; var21 < var5; var21++) {
               if (var0[var19] == 0) {
                  var0[var19] = var0[var7 + var20 / 2 * var8 + var21 / 2] & 16777215;
               }

               var19++;
            }
         }

         var7 = var4;
         var8 = var5;
      }
   }

   public static void method_03469(int[] var0, int var1, int var2, int var3) {
      Math.min(var2, var3);
      int var4 = var1;
      int var5 = var2;
      int var6 = var3;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;

      int var10;
      for (var10 = 0; var5 > 1 && var6 > 1; var4 = var7) {
         var7 = var4 + var5 * var6;
         var8 = var5 / 2;
         var9 = var6 / 2;

         for (int var11 = 0; var11 < var9; var11++) {
            int var12 = var7 + var11 * var8;
            int var13 = var4 + var11 * 2 * var5;

            for (int var14 = 0; var14 < var8; var14++) {
               var0[var12 + var14] = blend4Simple(
                  var0[var13 + var14 * 2], var0[var13 + var14 * 2 + 1], var0[var13 + var5 + var14 * 2], var0[var13 + var5 + var14 * 2 + 1]
               );
            }
         }

         var10++;
         var5 = var8;
         var6 = var9;
      }

      while (var10 > 0) {
         var5 = var2 >> --var10;
         var6 = var3 >> var10;
         var4 = var7 - var5 * var6;
         int var19 = var4;

         for (int var20 = 0; var20 < var6; var20++) {
            for (int var21 = 0; var21 < var5; var21++) {
               if (var0[var19] == 0) {
                  var0[var19] = var0[var7 + var20 / 2 * var8 + var21 / 2] & 16777215;
               }

               var19++;
            }
         }

         var7 = var4;
         var8 = var5;
      }
   }

   public static IntBuffer fillIntBuffer(int var0, int var1) {
      int[] var2 = getIntArray(var0);
      IntBuffer var3 = getIntBuffer(var0);
      Arrays.fill(intArray, 0, var0, var1);
      intBuffer.put(intArray, 0, var0);
      return intBuffer;
   }

   public static ITextureObject createDefaultTexture() {
      DynamicTexture var0 = new DynamicTexture(1, 1);
      var0.getTextureData()[0] = -1;
      var0.updateDynamicTexture();
      return var0;
   }

   public static void allocateTextureMap(int var0, int var1, int var2, int var3, Stitcher var4, TextureMap var5) {
      SMCLog.info("allocateTextureMap " + var1 + " " + var2 + " " + var3 + " ");
      var5.atlasWidth = var2;
      var5.atlasHeight = var3;
      MultiTexID var6 = getMultiTexID(var5);
      TextureUtil.allocateTextureImpl(var6.base, var1, var2, var3);
      if (Shaders.configNormalMap) {
         TextureUtil.allocateTextureImpl(var6.norm, var1, var2, var3);
      }

      if (Shaders.configSpecularMap) {
         TextureUtil.allocateTextureImpl(var6.spec, var1, var2, var3);
      }

      GlStateManager.bindTexture(var0);
   }

   public static void loadNSMap(IResourceManager var0, ResourceLocation var1, int var2, int var3, int[] var4) {
      if (Shaders.configNormalMap) {
         loadNSMap1(var0, getNSMapLocation(var1, "n"), var2, var3, var4, var2 * var3, -8421377);
      }

      if (Shaders.configSpecularMap) {
         loadNSMap1(var0, getNSMapLocation(var1, "s"), var2, var3, var4, var2 * var3 * 2, 0);
      }
   }

   public static int[] createAIntImage(int var0) {
      int[] var1 = new int[var0 * 3];
      Arrays.fill(var1, 0, var0, 0);
      Arrays.fill(var1, var0, var0 * 2, -8421377);
      Arrays.fill(var1, var0 * 2, var0 * 3, 0);
      return var1;
   }

   public static boolean loadNSMapFile(IResourceManager var0, ResourceLocation var1, int var2, int var3, int[] var4, int var5) {
      if (var1 == null) {
         return false;
      } else {
         try {
            IResource var6 = var0.getResource(var1);
            BufferedImage var7 = ImageIO.read(var6.getInputStream());
            if (var7 == null) {
               return false;
            } else if (var7.getWidth() == var2 && var7.getHeight() == var3) {
               var7.getRGB(0, 0, var2, var3, var4, var5, var2);
               return true;
            } else {
               return false;
            }
         } catch (IOException var8) {
            return false;
         }
      }
   }

   public static void loadNSMap1(IResourceManager var0, ResourceLocation var1, int var2, int var3, int[] var4, int var5, int var6) {
      if (!loadNSMapFile(var0, var1, var2, var3, var4, var5)) {
         Arrays.fill(var4, var5, var5 + var2 * var3, var6);
      }
   }

   public static void initDynamicTexture(int var0, int var1, int var2, DynamicTexture var3) {
      MultiTexID var4 = var3.getMultiTexID();
      int[] var5 = var3.getTextureData();
      int var6 = var1 * var2;
      Arrays.fill(var5, var6, var6 * 2, -8421377);
      Arrays.fill(var5, var6 * 2, var6 * 3, 0);
      TextureUtil.allocateTexture(var4.base, var1, var2);
      TextureUtil.setTextureBlurMipmap(false, false);
      TextureUtil.setTextureClamped(false);
      TextureUtil.allocateTexture(var4.norm, var1, var2);
      TextureUtil.setTextureBlurMipmap(false, false);
      TextureUtil.setTextureClamped(false);
      TextureUtil.allocateTexture(var4.spec, var1, var2);
      TextureUtil.setTextureBlurMipmap(false, false);
      TextureUtil.setTextureClamped(false);
      GlStateManager.bindTexture(var4.base);
   }

   public static int blendColor(int var0, int var1, int var2) {
      int var3 = 255 - var2;
      return ((var0 >>> 24 & 0xFF) * var2 + (var1 >>> 24 & 0xFF) * var3) / 255 << 24
         | ((var0 >>> 16 & 0xFF) * var2 + (var1 >>> 16 & 0xFF) * var3) / 255 << 16
         | ((var0 >>> 8 & 0xFF) * var2 + (var1 >>> 8 & 0xFF) * var3) / 255 << 8
         | ((var0 >>> 0 & 0xFF) * var2 + (var1 >>> 0 & 0xFF) * var3) / 255 << 0;
   }

   public static int loadSimpleTexture(int var0, BufferedImage var1, boolean var2, boolean var3, IResourceManager var4, ResourceLocation var5, MultiTexID var6) {
      int var7 = var1.getWidth();
      int var8 = var1.getHeight();
      int var9 = var7 * var8;
      int[] var10 = getIntArray(var9 * 3);
      var1.getRGB(0, 0, var7, var8, var10, 0, var7);
      loadNSMap(var4, var5, var7, var8, var10);
      setupTexture(var6, var10, var7, var8, var2, var3);
      return var0;
   }

   public static int blend4Alpha(int var0, int var1, int var2, int var3) {
      int var4 = var0 >>> 24 & 0xFF;
      int var5 = var1 >>> 24 & 0xFF;
      int var6 = var2 >>> 24 & 0xFF;
      int var7 = var3 >>> 24 & 0xFF;
      int var8 = var4 + var5 + var6 + var7;
      int var9 = (var8 + 2) / 4;
      int var10;
      if (var8 != 0) {
         var10 = var8;
      } else {
         var10 = 4;
         var4 = 1;
         var5 = 1;
         var6 = 1;
         var7 = 1;
      }

      int var11 = (var10 + 1) / 2;
      return var9 << 24
         | ((var0 >>> 16 & 0xFF) * var4 + (var1 >>> 16 & 0xFF) * var5 + (var2 >>> 16 & 0xFF) * var6 + (var3 >>> 16 & 0xFF) * var7 + var11) / var10 << 16
         | ((var0 >>> 8 & 0xFF) * var4 + (var1 >>> 8 & 0xFF) * var5 + (var2 >>> 8 & 0xFF) * var6 + (var3 >>> 8 & 0xFF) * var7 + var11) / var10 << 8
         | ((var0 >>> 0 & 0xFF) * var4 + (var1 >>> 0 & 0xFF) * var5 + (var2 >>> 0 & 0xFF) * var6 + (var3 >>> 0 & 0xFF) * var7 + var11) / var10 << 0;
   }

   public static IntBuffer getIntBuffer(int var0) {
      if (intBuffer.capacity() < var0) {
         int var1 = roundUpPOT(var0);
         byteBuffer = BufferUtils.createByteBuffer(var1 * 4);
         intBuffer = byteBuffer.asIntBuffer();
      }

      return intBuffer;
   }

   public static BufferedImage readImage(ResourceLocation var0) {
      try {
         if (!Config.hasResource(var0)) {
            return null;
         } else {
            InputStream var1 = Config.getResourceStream(var0);
            if (var1 == null) {
               return null;
            } else {
               BufferedImage var2 = ImageIO.read(var1);
               var1.close();
               return var2;
            }
         }
      } catch (IOException var3) {
         return null;
      }
   }

   public static void deleteTextures(AbstractTexture var0, int var1) {
      MultiTexID var2 = var0.multiTex;
      if (var2 != null) {
         var0.multiTex = null;
         multiTexMap.remove(var2.base);
         GlStateManager.deleteTexture(var2.norm);
         GlStateManager.deleteTexture(var2.spec);
         if (var2.base != var1) {
            SMCLog.warning("Error : MultiTexID.base mismatch: " + var2.base + ", texid: " + var1);
            GlStateManager.deleteTexture(var2.base);
         }
      }
   }

   public static void bindNSTextures(MultiTexID var0) {
      bindNSTextures(var0.norm, var0.spec);
   }
}
