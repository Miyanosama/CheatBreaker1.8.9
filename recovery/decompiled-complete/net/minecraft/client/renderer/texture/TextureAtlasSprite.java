package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.audio.SoundList$SoundEntry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.SmartAnimations;
import net.optifine.shaders.Shaders;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;

public class TextureAtlasSprite {
   public boolean isSpriteSingle;
   public boolean isShadersSprite;
   public List<int[][]> framesTextureData = Lists.newArrayList();
   public int originX;
   public boolean rotated;
   public int originY;
   public float baseU;
   public int frameCounter;
   public int animationIndex;
   public int sheetHeight;
   public int mipmapLevels;
   public TextureAtlasSprite spriteEmissive;
   public TextureAtlasSprite spriteSingle;
   public int glSpriteTextureId;
   public int height;
   public static String locationNameCompass = "builtin/compass";
   public AnimationMetadataSection animationMetadata;
   public int tickCounter;
   public boolean animationActive;
   public float maxV;
   public TextureAtlasSprite spriteSpecular;
   public float baseV;
   public int sheetWidth;
   public SoundList$SoundEntry field_0000;
   public TextureAtlasSprite spriteNormal;
   public int width;
   public float minU;
   public float maxU;
   public static String locationNameClock = "builtin/clock";
   public int[][] interpolatedFrameData;
   public String iconName;
   public int indexInMap = -1;
   public float minV;
   public boolean isEmissive;

   public void updateIndexInMap(CounterInt var1) {
      if (this.indexInMap < 0) {
         this.indexInMap = var1.nextValue();
      }
   }

   public boolean isAnimationActive() {
      return this.animationActive;
   }

   public float getInterpolatedV(double var1) {
      float var3 = this.maxV - this.minV;
      return this.minV + var3 * ((float)var1 / 16.0F);
   }

   public AnimationMetadataSection getAnimationMetadata() {
      return this.animationMetadata;
   }

   public boolean hasAnimationMetadata() {
      return this.animationMetadata != null;
   }

   public void bindSpriteTexture() {
      if (this.glSpriteTextureId < 0) {
         this.glSpriteTextureId = TextureUtil.glGenTextures();
         TextureUtil.allocateTextureImpl(this.glSpriteTextureId, this.mipmapLevels, this.width, this.height);
         TextureUtils.applyAnisotropicLevel();
      }

      TextureUtils.bindTexture(this.glSpriteTextureId);
   }

   public void setAnimationMetadata(AnimationMetadataSection var1) {
      this.animationMetadata = var1;
   }

   public float getMaxU() {
      return this.maxU;
   }

   public static void setLocationNameCompass(String var0) {
      locationNameCompass = var0;
   }

   public int getFrameCount() {
      return this.framesTextureData.size();
   }

   public static void setLocationNameClock(String var0) {
      locationNameClock = var0;
   }

   public List<int[][]> getFramesTextureData() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.framesTextureData);
      return var1;
   }

   public boolean load(IResourceManager var1, ResourceLocation var2) {
      return true;
   }

   public int getOriginX() {
      return this.originX;
   }

   public static TextureAtlasSprite makeAtlasSprite(ResourceLocation var0) {
      String var1 = var0.toString();
      return (TextureAtlasSprite)(locationNameClock.equals(var1)
         ? new TextureClock(var1)
         : (locationNameCompass.equals(var1) ? new TextureCompass(var1) : new TextureAtlasSprite(var1)));
   }

   public void copyFrom(TextureAtlasSprite var1) {
      this.originX = var1.originX;
      this.originY = var1.originY;
      this.width = var1.width;
      this.height = var1.height;
      this.rotated = var1.rotated;
      this.minU = var1.minU;
      this.maxU = var1.maxU;
      this.minV = var1.minV;
      this.maxV = var1.maxV;
      if (var1 != Config.getTextureMap().getMissingSprite()) {
         this.indexInMap = var1.indexInMap;
      }

      this.baseU = var1.baseU;
      this.baseV = var1.baseV;
      this.sheetWidth = var1.sheetWidth;
      this.sheetHeight = var1.sheetHeight;
      this.glSpriteTextureId = var1.glSpriteTextureId;
      this.mipmapLevels = var1.mipmapLevels;
      if (this.spriteSingle != null) {
         this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
      }

      this.animationIndex = var1.animationIndex;
   }

   public void resetSprite() {
      this.animationMetadata = null;
      this.setFramesTextureData(Lists.newArrayList());
      this.frameCounter = 0;
      this.tickCounter = 0;
      if (this.spriteSingle != null) {
         this.spriteSingle.resetSprite();
      }
   }

   public void initSprite(int var1, int var2, int var3, int var4, boolean var5) {
      this.originX = var3;
      this.originY = var4;
      this.rotated = var5;
      float var6 = (float)(0.01F / var1);
      float var7 = (float)(0.01F / var2);
      this.minU = var3 / (float)var1 + var6;
      this.maxU = (var3 + this.width) / (float)var1 - var6;
      this.minV = (float)var4 / var2 + var7;
      this.maxV = (float)(var4 + this.height) / var2 - var7;
      this.baseU = Math.min(this.minU, this.maxU);
      this.baseV = Math.min(this.minV, this.maxV);
      if (this.spriteSingle != null) {
         this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
      }

      if (this.spriteNormal != null) {
         this.spriteNormal.copyFrom(this);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.copyFrom(this);
      }
   }

   public boolean hasCustomLoader(IResourceManager var1, ResourceLocation var2) {
      return false;
   }

   public void method_21394() {
      if (this.glSpriteTextureId >= 0) {
         TextureUtil.deleteTexture(this.glSpriteTextureId);
         this.glSpriteTextureId = -1;
      }
   }

   public float getInterpolatedU(double var1) {
      float var3 = this.maxU - this.minU;
      return this.minU + var3 * (float)var1 / 16.0F;
   }

   public String getIconName() {
      return this.iconName;
   }

   public void clearFramesTextureData() {
      this.framesTextureData.clear();
      if (this.spriteSingle != null) {
         this.spriteSingle.clearFramesTextureData();
      }
   }

   public int getIndexInMap() {
      return this.indexInMap;
   }

   public void generateMipmaps(int var1) {
      ArrayList var2 = Lists.newArrayList();

      for (int var3 = 0; var3 < this.framesTextureData.size(); var3++) {
         int[][] var4 = this.framesTextureData.get(var3);
         if (var4 != null) {
            try {
               var2.add(TextureUtil.generateMipmapData(var1, this.width, var4));
            } catch (Throwable var8) {
               CrashReport var6 = CrashReport.makeCrashReport(var8, "Generating mipmaps for frame");
               CrashReportCategory var7 = var6.makeCategory("Frame being iterated");
               var7.addCrashSection("Frame index", var3);
               var7.addCrashSectionCallable("Frame sizes", new TextureAtlasSprite$1(this, var4));
               throw new ReportedException(var6);
            }
         }
      }

      this.setFramesTextureData(var2);
      if (this.spriteSingle != null) {
         this.spriteSingle.generateMipmaps(var1);
      }
   }

   public double getSpriteV16(float var1) {
      float var2 = this.maxV - this.minV;
      return (var1 - this.minV) / var2 * 16.0F;
   }

   public static int[][] getFrameTextureData(int[][] var0, int var1, int var2, int var3) {
      int[][] var4 = new int[var0.length][];

      for (int var5 = 0; var5 < var0.length; var5++) {
         int[] var6 = var0[var5];
         if (var6 != null) {
            var4[var5] = new int[(var1 >> var5) * (var2 >> var5)];
            System.arraycopy(var6, var3 * var4[var5].length, var4[var5], 0, var4[var5].length);
         }
      }

      return var4;
   }

   @Override
   public String toString() {
      return "TextureAtlasSprite{name='"
         + this.iconName
         + '\''
         + ", frameCount="
         + this.framesTextureData.size()
         + ", rotated="
         + this.rotated
         + ", x="
         + this.originX
         + ", y="
         + this.originY
         + ", height="
         + this.height
         + ", width="
         + this.width
         + ", u0="
         + this.minU
         + ", u1="
         + this.maxU
         + ", v0="
         + this.minV
         + ", v1="
         + this.maxV
         + '}';
   }

   public void setAnimationIndex(int var1) {
      this.animationIndex = var1;
      if (this.spriteNormal != null) {
         this.spriteNormal.setAnimationIndex(var1);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.setAnimationIndex(var1);
      }
   }

   public void setFramesTextureData(List<int[][]> var1) {
      this.framesTextureData = var1;
      if (this.spriteSingle != null) {
         this.spriteSingle.setFramesTextureData(var1);
      }
   }

   public void method_21357() {
      double var1 = 1.0 - (double)this.tickCounter / this.animationMetadata.getFrameTimeSingle(this.frameCounter);
      int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
      int var4 = this.animationMetadata.getFrameCount() == 0 ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
      int var5 = this.animationMetadata.getFrameIndex((this.frameCounter + 1) % var4);
      if (var3 != var5 && var5 >= 0 && var5 < this.framesTextureData.size()) {
         int[][] var6 = this.framesTextureData.get(var3);
         int[][] var7 = this.framesTextureData.get(var5);
         if (this.interpolatedFrameData == null || this.interpolatedFrameData.length != var6.length) {
            this.interpolatedFrameData = new int[var6.length][];
         }

         for (int var8 = 0; var8 < var6.length; var8++) {
            if (this.interpolatedFrameData[var8] == null) {
               this.interpolatedFrameData[var8] = new int[var6[var8].length];
            }

            if (var8 < var7.length && var7[var8].length == var6[var8].length) {
               for (int var9 = 0; var9 < var6[var8].length; var9++) {
                  int var10 = var6[var8][var9];
                  int var11 = var7[var8][var9];
                  int var12 = (int)(((var10 & 0xFF0000) >> 16) * var1 + ((var11 & 0xFF0000) >> 16) * (1.0 - var1));
                  int var13 = (int)(((var10 & 0xFF00) >> 8) * var1 + ((var11 & 0xFF00) >> 8) * (1.0 - var1));
                  int var14 = (int)((var10 & 0xFF) * var1 + (var11 & 0xFF) * (1.0 - var1));
                  this.interpolatedFrameData[var8][var9] = var10 & 0xFF000000 | var12 << 16 | var13 << 8 | var14;
               }
            }
         }

         TextureUtil.uploadTextureMipmap(this.interpolatedFrameData, this.width, this.height, this.originX, this.originY, false, false);
      }
   }

   public int getIconHeight() {
      return this.height;
   }

   public void method_21360(int var1) {
      if (this.framesTextureData.size() <= var1) {
         for (int var2 = this.framesTextureData.size(); var2 <= var1; var2++) {
            this.framesTextureData.add((int[][])null);
         }
      }

      if (this.spriteSingle != null) {
         this.spriteSingle.method_21360(var1);
      }
   }

   public int[][] getFrameTextureData(int var1) {
      return this.framesTextureData.get(var1);
   }

   public double getSpriteU16(float var1) {
      float var2 = this.maxU - this.minU;
      return (var1 - this.minU) / var2 * 16.0F;
   }

   public void setIndexInMap(int var1) {
      this.indexInMap = var1;
   }

   public float getMinV() {
      return this.minV;
   }

   public void updateAnimation() {
      if (this.animationMetadata != null) {
         this.animationActive = SmartAnimations.isActive() ? SmartAnimations.method_01906(this.animationIndex) : true;
         this.tickCounter++;
         if (this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
            int var1 = this.animationMetadata.getFrameIndex(this.frameCounter);
            int var2 = this.animationMetadata.getFrameCount() == 0 ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
            this.frameCounter = (this.frameCounter + 1) % var2;
            this.tickCounter = 0;
            int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
            boolean var4 = false;
            boolean var5 = this.isSpriteSingle;
            if (!this.animationActive) {
               return;
            }

            if (var1 != var3 && var3 >= 0 && var3 < this.framesTextureData.size()) {
               TextureUtil.uploadTextureMipmap(this.framesTextureData.get(var3), this.width, this.height, this.originX, this.originY, var4, var5);
            }
         } else if (this.animationMetadata.isInterpolate()) {
            if (!this.animationActive) {
               return;
            }

            this.method_21357();
         }
      }
   }

   public int getIconWidth() {
      return this.width;
   }

   public void setIconHeight(int var1) {
      this.height = var1;
      if (this.spriteSingle != null) {
         this.spriteSingle.setIconHeight(this.height);
      }
   }

   public int getOriginY() {
      return this.originY;
   }

   public float getMinU() {
      return this.minU;
   }

   public float toSingleU(float var1) {
      var1 -= this.baseU;
      float var2 = (float)this.sheetWidth / this.width;
      return var1 * var2;
   }

   public void loadSprite(BufferedImage[] var1, AnimationMetadataSection var2) {
      this.resetSprite();
      int var3 = var1[0].getWidth();
      int var4 = var1[0].getHeight();
      this.width = var3;
      this.height = var4;
      if (this.spriteSingle != null) {
         this.spriteSingle.width = this.width;
         this.spriteSingle.height = this.height;
      }

      int[][] var5 = new int[var1.length][];

      for (int var6 = 0; var6 < var1.length; var6++) {
         BufferedImage var7 = var1[var6];
         if (var7 != null) {
            if (this.width >> var6 != var7.getWidth()) {
               var7 = TextureUtils.scaleImage(var7, this.width >> var6);
            }

            if (var6 > 0 && (var7.getWidth() != var3 >> var6 || var7.getHeight() != var4 >> var6)) {
               throw new RuntimeException(
                  String.format(
                     "Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", var6, var7.getWidth(), var7.getHeight(), var3 >> var6, var4 >> var6
                  )
               );
            }

            var5[var6] = new int[var7.getWidth() * var7.getHeight()];
            var7.getRGB(0, 0, var7.getWidth(), var7.getHeight(), var5[var6], 0, var7.getWidth());
         }
      }

      if (var2 == null) {
         if (var4 != var3) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.framesTextureData.add(var5);
      } else {
         int var11 = var4 / var3;
         int var13 = var3;
         int var8 = var3;
         this.height = this.width;
         if (var2.getFrameCount() > 0) {
            for (int var10 : var2.getFrameIndexSet()) {
               if (var10 >= var11) {
                  throw new RuntimeException("invalid frameindex " + var10);
               }

               this.method_21360(var10);
               this.framesTextureData.set(var10, getFrameTextureData(var5, var13, var8, var10));
            }

            this.animationMetadata = var2;
         } else {
            ArrayList var16 = Lists.newArrayList();

            for (int var18 = 0; var18 < var11; var18++) {
               this.framesTextureData.add(getFrameTextureData(var5, var13, var8, var18));
               var16.add(new AnimationFrame(var18, -1));
            }

            this.animationMetadata = new AnimationMetadataSection(var16, this.width, this.height, var2.getFrameTime(), var2.isInterpolate());
         }
      }

      if (!this.isShadersSprite) {
         if (Config.isShaders()) {
            this.loadShadersSprites();
         }

         for (int var12 = 0; var12 < this.framesTextureData.size(); var12++) {
            int[][] var14 = this.framesTextureData.get(var12);
            if (var14 != null && !this.iconName.startsWith("minecraft:blocks/leaves_")) {
               for (int var15 = 0; var15 < var14.length; var15++) {
                  int[] var17 = var14[var15];
                  this.fixTransparentColor(var17);
               }
            }
         }

         if (this.spriteSingle != null) {
            this.spriteSingle.loadSprite(var1, var2);
         }
      }
   }

   public void fixTransparentColor(int[] var1) {
      if (var1 != null) {
         long var2 = 6574080L & -7812845016245599996L;
         long var4 = 639640451L & 1263760529877637124L;
         long var6 = -1321982216932145152L & 1321982215747535331L;
         long var8 = 77103116L & -6619902943178979824L;

         for (int var10 = 0; var10 < var1.length; var10++) {
            int var11 = var1[var10];
            int var12 = var11 >> 24 & 0xFF;
            if (var12 >= 16) {
               int var13 = var11 >> 16 & 0xFF;
               int var14 = var11 >> 8 & 0xFF;
               int var15 = var11 & 0xFF;
               var2 += var13;
               var4 += var14;
               var6 += var15;
               var8 += -5093376648714117111L & 5093376648482718755L;
            }
         }

         if (var8 > (1270942744L & 2666924682918236711L)) {
            int var17 = (int)(var2 / var8);
            int var18 = (int)(var4 / var8);
            int var19 = (int)(var6 / var8);
            int var20 = var17 << 16 | var18 << 8 | var19;

            for (int var21 = 0; var21 < var1.length; var21++) {
               int var22 = var1[var21];
               int var16 = var22 >> 24 & 0xFF;
               if (var16 <= 16) {
                  var1[var21] = var20;
               }
            }
         }
      }
   }

   public float toSingleV(float var1) {
      var1 -= this.baseV;
      float var2 = (float)this.sheetHeight / this.height;
      return var1 * var2;
   }

   public void loadShadersSprites() {
      if (Shaders.configNormalMap) {
         String var1 = this.iconName + "_n";
         ResourceLocation var2 = new ResourceLocation(var1);
         var2 = Config.getTextureMap().completeResourceLocation(var2);
         if (Config.hasResource(var2)) {
            this.spriteNormal = new TextureAtlasSprite(var1);
            this.spriteNormal.isShadersSprite = true;
            this.spriteNormal.copyFrom(this);
            this.spriteNormal.generateMipmaps(this.mipmapLevels);
         }
      }

      if (Shaders.configSpecularMap) {
         String var3 = this.iconName + "_s";
         ResourceLocation var5 = new ResourceLocation(var3);
         var5 = Config.getTextureMap().completeResourceLocation(var5);
         if (Config.hasResource(var5)) {
            this.spriteSpecular = new TextureAtlasSprite(var3);
            this.spriteSpecular.isShadersSprite = true;
            this.spriteSpecular.copyFrom(this);
            this.spriteSpecular.generateMipmaps(this.mipmapLevels);
         }
      }
   }

   public int getAnimationIndex() {
      return this.animationIndex;
   }

   public TextureAtlasSprite(String var1) {
      this.glSpriteTextureId = -1;
      this.spriteSingle = null;
      this.isSpriteSingle = false;
      this.mipmapLevels = 0;
      this.spriteNormal = null;
      this.spriteSpecular = null;
      this.isShadersSprite = false;
      this.isEmissive = false;
      this.spriteEmissive = null;
      this.animationIndex = -1;
      this.animationActive = false;
      this.iconName = var1;
      if (Config.isMultiTexture()) {
         this.spriteSingle = new TextureAtlasSprite(this.getIconName() + ".spriteSingle", true);
      }
   }

   public TextureAtlasSprite(String var1, boolean var2) {
      this.glSpriteTextureId = -1;
      this.spriteSingle = null;
      this.isSpriteSingle = false;
      this.mipmapLevels = 0;
      this.spriteNormal = null;
      this.spriteSpecular = null;
      this.isShadersSprite = false;
      this.isEmissive = false;
      this.spriteEmissive = null;
      this.animationIndex = -1;
      this.animationActive = false;
      this.iconName = var1;
      this.isSpriteSingle = var2;
   }

   public void setIconWidth(int var1) {
      this.width = var1;
      if (this.spriteSingle != null) {
         this.spriteSingle.setIconWidth(this.width);
      }
   }

   public float getMaxV() {
      return this.maxV;
   }
}
