package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.CustomItems;
import net.optifine.EmissiveTextures;
import net.optifine.SmartAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.ShadersTex;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureMap extends AbstractTexture implements ITickableTextureObject {
   public TextureAtlasSprite missingImage;
   public Map<String, TextureAtlasSprite> mapRegisteredSprites;
   public int mipmapLevels;
   public String basePath;
   public List<TextureAtlasSprite> listAnimatedSprites;
   public Map<String, TextureAtlasSprite> mapUploadedSprites;
   public boolean skipFirst = false;
   public int recoveredField962;
   public static boolean ENABLE_SKIP = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
   public static Logger logger = LogManager.getLogger();
   public int recoveredField963;
   public IIconCreator iconCreator;
   public double iconGridSizeU;
   public int iconGridSize;
   public static ResourceLocation LOCATION_MISSING_TEXTURE = new ResourceLocation("missingno");
   public int iconGridCountY;
   public CounterInt counterIndexInMap;
   public int iconGridCountX;
   public int atlasWidth;
   public int atlasHeight;
   public TextureAtlasSprite[] iconGrid = null;
   public static ResourceLocation locationBlocksTexture = new ResourceLocation("textures/atlas/blocks.png");
   public double iconGridSizeV;

   public boolean setTextureEntry(String var1, TextureAtlasSprite var2) {
      if (!this.mapRegisteredSprites.containsKey(var1)) {
         this.mapRegisteredSprites.put(var1, var2);
         var2.updateIndexInMap(this.counterIndexInMap);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void tick() {
      this.updateAnimations();
   }

   public int getMipmapLevels() {
      return this.mipmapLevels;
   }

   public ResourceLocation completeResourceLocation(ResourceLocation var1, int var2) {
      return this.isAbsoluteLocation(var1)
         ? new ResourceLocation(var1.getResourceDomain(), var1.getResourcePath() + ".png")
         : (
            var2 == 0
               ? new ResourceLocation(var1.getResourceDomain(), String.format("%s/%s%s", this.basePath, var1.getResourcePath(), ".png"))
               : new ResourceLocation(var1.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", this.basePath, var1.getResourcePath(), var2, ".png"))
         );
   }

   public boolean isTextureBound() {
      int var1 = GlStateManager.getBoundTexture();
      int var2 = this.getGlTextureId();
      return var1 == var2;
   }

   public int getMinSpriteSize() {
      int var1 = 1 << this.mipmapLevels;
      if (var1 < 8) {
         var1 = 8;
      }

      return var1;
   }

   @Override
   public void loadTexture(IResourceManager var1) throws java.io.IOException {
      if (this.iconCreator != null) {
         this.loadSprites(var1, this.iconCreator);
      }
   }

   public boolean isAbsoluteLocation(ResourceLocation var1) {
      String var2 = var1.getResourcePath();
      return this.isAbsoluteLocationPath(var2);
   }

   public TextureAtlasSprite getRegisteredSprite(ResourceLocation var1) {
      return this.mapRegisteredSprites.get(var1.toString());
   }

   public boolean setTextureEntry(TextureAtlasSprite var1) {
      return this.setTextureEntry(var1.getIconName(), var1);
   }

   public TextureAtlasSprite registerSprite(ResourceLocation var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         TextureAtlasSprite var2 = this.mapRegisteredSprites.get(var1.toString());
         if (var2 == null) {
            var2 = TextureAtlasSprite.makeAtlasSprite(var1);
            this.mapRegisteredSprites.put(var1.toString(), var2);
            var2.updateIndexInMap(this.counterIndexInMap);
            if (Config.isEmissiveTextures()) {
               this.checkEmissive(var1, var2);
            }
         }

         return var2;
      }
   }

   public TextureAtlasSprite getSpriteSafe(String var1) {
      ResourceLocation var2 = new ResourceLocation(var1);
      return this.mapRegisteredSprites.get(var2.toString());
   }

   public int detectMinimumSpriteSize(Map var1, IResourceManager var2, int var3) {
      HashMap var4 = new HashMap();

      for (Object var6 : var1.entrySet()) {
         Entry var7 = (Entry)var6;
         TextureAtlasSprite var8 = (TextureAtlasSprite)var7.getValue();
         ResourceLocation var9 = new ResourceLocation(var8.getIconName());
         ResourceLocation var10 = this.completeResourceLocation(var9);
         if (!var8.hasCustomLoader(var2, var9)) {
            try {
               IResource var11 = var2.getResource(var10);
               if (var11 != null) {
                  InputStream var12 = var11.getInputStream();
                  if (var12 != null) {
                     Dimension var13 = TextureUtils.getImageSize(var12, "png");
                     var12.close();
                     if (var13 != null) {
                        int var14 = var13.width;
                        int var15 = MathHelper.roundUpToPowerOfTwo(var14);
                        if (!var4.containsKey(var15)) {
                           var4.put(var15, 1);
                        } else {
                           int var16 = (Integer)var4.get(var15);
                           var4.put(var15, var16 + 1);
                        }
                     }
                  }
               }
            } catch (Exception var17) {
            }
         }
      }

      int var18 = 0;
      Set var19 = var4.keySet();
      TreeSet var20 = new TreeSet(var19);

      for (int var25 : (Iterable<Integer>)(Iterable<?>)(var20)) {
         int var21 = (Integer)var4.get(var25);
         var18 += var21;
      }

      int var24 = 16;
      int var26 = 0;
      int var22 = var18 * var3 / 100;

      for (int var28 : (Iterable<Integer>)(Iterable<?>)(var20)) {
         int var29 = (Integer)var4.get(var28);
         var26 += var29;
         if (var28 > var24) {
            var24 = var28;
         }

         if (var26 > var22) {
            return var24;
         }
      }

      return var24;
   }

   public TextureAtlasSprite getIconByUV(double var1, double var3) {
      if (this.iconGrid == null) {
         return null;
      } else {
         int var5 = (int)(var1 / this.iconGridSizeU);
         int var6 = (int)(var3 / this.iconGridSizeV);
         int var7 = var6 * this.iconGridCountX + var5;
         return var7 >= 0 && var7 <= this.iconGrid.length ? this.iconGrid[var7] : null;
      }
   }

   public void loadSprites(IResourceManager var1, IIconCreator var2) {
      this.mapRegisteredSprites.clear();
      this.counterIndexInMap.reset();
      var2.registerSprites(this);
      if (this.mipmapLevels >= 4) {
         this.mipmapLevels = this.detectMaxMipmapLevel(this.mapRegisteredSprites, var1);
         Config.log("Mipmap levels: " + this.mipmapLevels);
      }

      this.initMissingImage();
      this.deleteGlTexture();
      this.loadTextureAtlas(var1);
   }

   public boolean isAbsoluteLocationPath(String var1) {
      String var2 = var1.toLowerCase();
      return var2.startsWith("mcpatcher/") || var2.startsWith("optifine/");
   }

   public TextureMap(String var1) {
      this(var1, (IIconCreator)null);
   }

   public int method_22996() {
      return this.recoveredField963;
   }

   public void updateIconGrid(int var1, int var2) {
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGrid = null;
      if (this.iconGridSize > 0) {
         this.iconGridCountX = var1 / this.iconGridSize;
         this.iconGridCountY = var2 / this.iconGridSize;
         this.iconGrid = new TextureAtlasSprite[this.iconGridCountX * this.iconGridCountY];
         this.iconGridSizeU = 1.0 / this.iconGridCountX;
         this.iconGridSizeV = 1.0 / this.iconGridCountY;

         for (TextureAtlasSprite var4 : this.mapUploadedSprites.values()) {
            double var5 = 0.5 / var1;
            double var7 = 0.5 / var2;
            double var9 = Math.min(var4.getMinU(), var4.getMaxU()) + var5;
            double var11 = Math.min(var4.getMinV(), var4.getMaxV()) + var7;
            double var13 = Math.max(var4.getMinU(), var4.getMaxU()) - var5;
            double var15 = Math.max(var4.getMinV(), var4.getMaxV()) - var7;
            int var17 = (int)(var9 / this.iconGridSizeU);
            int var18 = (int)(var11 / this.iconGridSizeV);
            int var19 = (int)(var13 / this.iconGridSizeU);
            int var20 = (int)(var15 / this.iconGridSizeV);

            for (int var21 = var17; var21 <= var19; var21++) {
               if (var21 >= 0 && var21 < this.iconGridCountX) {
                  for (int var22 = var18; var22 <= var20; var22++) {
                     if (var22 >= 0 && var22 < this.iconGridCountX) {
                        int var23 = var22 * this.iconGridCountX + var21;
                        this.iconGrid[var23] = var4;
                     } else {
                        Config.warn("Invalid grid V: " + var22 + ", icon: " + var4.getIconName());
                     }
                  }
               } else {
                  Config.warn("Invalid grid U: " + var21 + ", icon: " + var4.getIconName());
               }
            }
         }
      }
   }

   public TextureMap(String var1, boolean var2) {
      this(var1, (IIconCreator)null, var2);
   }

   public void updateAnimations() {
      boolean var1 = false;
      boolean var2 = false;
      TextureUtil.bindTexture(this.getGlTextureId());
      int var3 = 0;

      for (TextureAtlasSprite var5 : this.listAnimatedSprites) {
         if (this.isTerrainAnimationActive(var5)) {
            var5.updateAnimation();
            if (var5.isAnimationActive()) {
               var3++;
            }

            if (var5.spriteNormal != null) {
               var1 = true;
            }

            if (var5.spriteSpecular != null) {
               var2 = true;
            }
         }
      }

      if (Config.isMultiTexture()) {
         for (TextureAtlasSprite var11 : this.listAnimatedSprites) {
            if (this.isTerrainAnimationActive(var11)) {
               TextureAtlasSprite var6 = var11.spriteSingle;
               if (var6 != null) {
                  if (var11 == TextureUtils.recoveredField1084 || var11 == TextureUtils.recoveredField1052) {
                     var6.frameCounter = var11.frameCounter;
                  }

                  var11.bindSpriteTexture();
                  var6.updateAnimation();
                  if (var6.isAnimationActive()) {
                     var3++;
                  }
               }
            }
         }

         TextureUtil.bindTexture(this.getGlTextureId());
      }

      if (Config.isShaders()) {
         if (var1) {
            TextureUtil.bindTexture(this.getMultiTexID().norm);

            for (TextureAtlasSprite var12 : this.listAnimatedSprites) {
               if (var12.spriteNormal != null && this.isTerrainAnimationActive(var12)) {
                  if (var12 == TextureUtils.recoveredField1084 || var12 == TextureUtils.recoveredField1052) {
                     var12.spriteNormal.frameCounter = var12.frameCounter;
                  }

                  var12.spriteNormal.updateAnimation();
                  if (var12.spriteNormal.isAnimationActive()) {
                     var3++;
                  }
               }
            }
         }

         if (var2) {
            TextureUtil.bindTexture(this.getMultiTexID().spec);

            for (TextureAtlasSprite var13 : this.listAnimatedSprites) {
               if (var13.spriteSpecular != null && this.isTerrainAnimationActive(var13)) {
                  if (var13 == TextureUtils.recoveredField1084 || var13 == TextureUtils.recoveredField1052) {
                     var13.spriteNormal.frameCounter = var13.frameCounter;
                  }

                  var13.spriteSpecular.updateAnimation();
                  if (var13.spriteSpecular.isAnimationActive()) {
                     var3++;
                  }
               }
            }
         }

         if (var1 || var2) {
            TextureUtil.bindTexture(this.getGlTextureId());
         }
      }

      int var10 = Config.getMinecraft().entityRenderer.frameCount;
      if (var10 != this.recoveredField962) {
         this.recoveredField963 = var3;
         this.recoveredField962 = var10;
      }

      if (SmartAnimations.isActive()) {
         SmartAnimations.resetSpritesRendered();
      }
   }

   public TextureMap(String var1, IIconCreator var2, boolean var3) {
      this.iconGridSize = -1;
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGridSizeU = -1.0;
      this.iconGridSizeV = -1.0;
      this.counterIndexInMap = new CounterInt(0);
      this.atlasWidth = 0;
      this.atlasHeight = 0;
      this.listAnimatedSprites = Lists.newArrayList();
      this.mapRegisteredSprites = Maps.newHashMap();
      this.mapUploadedSprites = Maps.newHashMap();
      this.missingImage = new TextureAtlasSprite("missingno");
      this.basePath = var1;
      this.iconCreator = var2;
      this.skipFirst = var3 && ENABLE_SKIP;
   }

   public TextureAtlasSprite getAtlasSprite(String var1) {
      TextureAtlasSprite var2 = this.mapUploadedSprites.get(var1);
      if (var2 == null) {
         var2 = this.missingImage;
      }

      return var2;
   }

   public void initMissingImage() {
      int var1 = this.getMinSpriteSize();
      int[] var2 = this.getMissingImageData(var1);
      this.missingImage.setIconWidth(var1);
      this.missingImage.setIconHeight(var1);
      int[][] var3 = new int[this.mipmapLevels + 1][];
      var3[0] = var2;
      this.missingImage.setFramesTextureData(Lists.<int[][]>newArrayList(new int[][][]{var3}));
      this.missingImage.setIndexInMap(this.counterIndexInMap.nextValue());
   }

   public void loadTextureAtlas(IResourceManager var1) {
      Config.dbg("Multitexture: " + Config.isMultiTexture());
      if (Config.isMultiTexture()) {
         for (TextureAtlasSprite var3 : this.mapUploadedSprites.values()) {
            var3.method_21394();
         }
      }

      ConnectedTextures.updateIcons(this);
      CustomItems.updateIcons(this);
      BetterGrass.updateIcons(this);
      int var31 = TextureUtils.getGLMaximumTextureSize();
      Stitcher var32 = new Stitcher(var31, var31, true, 0, this.mipmapLevels);
      this.mapUploadedSprites.clear();
      this.listAnimatedSprites.clear();
      int var4 = Integer.MAX_VALUE;
      Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, this);
      int var5 = this.getMinSpriteSize();
      this.iconGridSize = var5;
      int var6 = 1 << this.mipmapLevels;
      int var7 = 0;
      int var8 = 0;

      for (Entry var10 : this.mapRegisteredSprites.entrySet()) {
         if (!this.skipFirst) {
            TextureAtlasSprite var34 = (TextureAtlasSprite)var10.getValue();
            ResourceLocation var36 = new ResourceLocation(var34.getIconName());
            ResourceLocation var40 = this.completeResourceLocation(var36, 0);
            var34.updateIndexInMap(this.counterIndexInMap);
            if (var34.hasCustomLoader(var1, var36)) {
               if (!var34.load(var1, var36)) {
                  var4 = Math.min(var4, Math.min(var34.getIconWidth(), var34.getIconHeight()));
                  var32.addSprite(var34);
                  Config.detail("Custom loader (skipped): " + var34);
                  var8++;
               }

               Config.detail("Custom loader: " + var34);
               var7++;
            } else {
               try {
                  IResource var43 = var1.getResource(var40);
                  BufferedImage[] var47 = new BufferedImage[1 + this.mipmapLevels];
                  var47[0] = TextureUtil.readBufferedImage(var43.getInputStream());
                  int var49 = var47[0].getWidth();
                  int var51 = var47[0].getHeight();
                  if (var49 < 1 || var51 < 1) {
                     Config.warn("Invalid sprite size: " + var34);
                     continue;
                  }

                  if (var49 < var5 || this.mipmapLevels > 0) {
                     int var54 = this.mipmapLevels > 0 ? TextureUtils.scaleToGrid(var49, var5) : TextureUtils.scaleToMin(var49, var5);
                     if (var54 != var49) {
                        if (!TextureUtils.isPowerOfTwo(var49)) {
                           Config.log("Scaled non power of 2: " + var34.getIconName() + ", " + var49 + " -> " + var54);
                        } else {
                           Config.log("Scaled too small texture: " + var34.getIconName() + ", " + var49 + " -> " + var54);
                        }

                        int var56 = var51 * var54 / var49;
                        var47[0] = TextureUtils.scaleImage(var47[0], var54);
                     }
                  }

                  TextureMetadataSection var55 = var43.getMetadata("texture");
                  if (var55 != null) {
                     List var57 = var55.getListMipmaps();
                     if (!var57.isEmpty()) {
                        int var20 = var47[0].getWidth();
                        int var21 = var47[0].getHeight();
                        if (MathHelper.roundUpToPowerOfTwo(var20) != var20 || MathHelper.roundUpToPowerOfTwo(var21) != var21) {
                           throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                     }

                     for (int var60 : (Iterable<Integer>)(Iterable<?>)(var57)) {
                        if (var60 > 0 && var60 < var47.length - 1 && var47[var60] == null) {
                           ResourceLocation var22 = this.completeResourceLocation(var36, var60);

                           try {
                              var47[var60] = TextureUtil.readBufferedImage(var1.getResource(var22).getInputStream());
                           } catch (IOException var28) {
                              logger.error("Unable to load miplevel {} from: {}", var60, var22, var28);
                           }
                        }
                     }
                  }

                  AnimationMetadataSection var58 = var43.getMetadata("animation");
                  var34.loadSprite(var47, var58);
               } catch (RuntimeException var29) {
                  logger.error("Unable to parse metadata from " + var40, var29);
                  ReflectorForge.FMLClientHandler_trackBrokenTexture(var40, var29.getMessage());
                  continue;
               } catch (IOException var30) {
                  logger.error("Using missing texture, unable to load " + var40 + ", " + var30.getClass().getName());
                  ReflectorForge.FMLClientHandler_trackMissingTexture(var40);
                  continue;
               }

               var4 = Math.min(var4, Math.min(var34.getIconWidth(), var34.getIconHeight()));
               int var44 = Math.min(Integer.lowestOneBit(var34.getIconWidth()), Integer.lowestOneBit(var34.getIconHeight()));
               if (var44 < var6) {
                  logger.warn(
                     "Texture {} with size {}x{} limits mip level from {} to {}",
                     var40,
                     var34.getIconWidth(),
                     var34.getIconHeight(),
                     MathHelper.calculateLogBaseTwo(var6),
                     MathHelper.calculateLogBaseTwo(var44)
                  );
                  var6 = var44;
               }

               var32.addSprite(var34);
            }
            continue;
         }
         break;
      }

      if (var7 > 0) {
         Config.dbg("Custom loader sprites: " + var7);
      }

      if (var8 > 0) {
         Config.dbg("Custom loader sprites (skipped): " + var8);
      }

      int var33 = Math.min(var4, var6);
      int var11 = MathHelper.calculateLogBaseTwo(var33);
      if (var11 < 0) {
         var11 = 0;
      }

      if (var11 < this.mipmapLevels) {
         logger.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", this.basePath, this.mipmapLevels, var11, var33);
         this.mipmapLevels = var11;
      }

      for (final TextureAtlasSprite var13 : this.mapRegisteredSprites.values()) {
         if (this.skipFirst) {
            break;
         }

         try {
            var13.generateMipmaps(this.mipmapLevels);
         } catch (Throwable var27) {
            CrashReport var15 = CrashReport.makeCrashReport(var27, "Applying mipmap");
            CrashReportCategory var16 = var15.makeCategory("Sprite being mipmapped");
            var16.addCrashSectionCallable("Sprite name", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return var13.getIconName();
               }
            });
            var16.addCrashSectionCallable("Sprite size", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return var13.getIconWidth() + " x " + var13.getIconHeight();
               }
            });
            var16.addCrashSectionCallable("Sprite frames", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return var13.getFrameCount() + " frames";
               }
            });
            var16.addCrashSection("Mipmap levels", this.mipmapLevels);
            throw new ReportedException(var15);
         }
      }

      this.missingImage.generateMipmaps(this.mipmapLevels);
      var32.addSprite(this.missingImage);
      this.skipFirst = false;

      try {
         var32.doStitch();
      } catch (StitcherException var26) {
         throw var26;
      }

      logger.info("Created: {}x{} {}-atlas", var32.getCurrentWidth(), var32.getCurrentHeight(), this.basePath);
      if (Config.isShaders()) {
         ShadersTex.allocateTextureMap(this.getGlTextureId(), this.mipmapLevels, var32.getCurrentWidth(), var32.getCurrentHeight(), var32, this);
      } else {
         TextureUtil.allocateTextureImpl(this.getGlTextureId(), this.mipmapLevels, var32.getCurrentWidth(), var32.getCurrentHeight());
      }

      HashMap var35 = Maps.newHashMap(this.mapRegisteredSprites);

      for (TextureAtlasSprite var14 : var32.getStichSlots()) {
         String var45 = var14.getIconName();
         var35.remove(var45);
         this.mapUploadedSprites.put(var45, var14);

         try {
            if (Config.isShaders()) {
               ShadersTex.uploadTexSubForLoadAtlas(
                  this,
                  var14.getIconName(),
                  var14.getFrameTextureData(0),
                  var14.getIconWidth(),
                  var14.getIconHeight(),
                  var14.getOriginX(),
                  var14.getOriginY(),
                  false,
                  false
               );
            } else {
               TextureUtil.uploadTextureMipmap(
                  var14.getFrameTextureData(0), var14.getIconWidth(), var14.getIconHeight(), var14.getOriginX(), var14.getOriginY(), false, false
               );
            }
         } catch (Throwable var25) {
            CrashReport var17 = CrashReport.makeCrashReport(var25, "Stitching texture atlas");
            CrashReportCategory var18 = var17.makeCategory("Texture being stitched together");
            var18.addCrashSection("Atlas path", this.basePath);
            var18.addCrashSection("Sprite", var14);
            throw new ReportedException(var17);
         }

         if (var14.hasAnimationMetadata()) {
            var14.setAnimationIndex(this.listAnimatedSprites.size());
            this.listAnimatedSprites.add(var14);
         }
      }

      for (TextureAtlasSprite var41 : (Iterable<TextureAtlasSprite>)(Iterable<?>)(var35.values())) {
         var41.copyFrom(this.missingImage);
      }

      Config.log("Animated sprites: " + this.listAnimatedSprites.size());
      if (Config.isMultiTexture()) {
         int var39 = var32.getCurrentWidth();
         int var42 = var32.getCurrentHeight();

         for (TextureAtlasSprite var48 : var32.getStichSlots()) {
            var48.sheetWidth = var39;
            var48.sheetHeight = var42;
            var48.mipmapLevels = this.mipmapLevels;
            TextureAtlasSprite var50 = var48.spriteSingle;
            if (var50 != null) {
               if (var50.getIconWidth() <= 0) {
                  var50.setIconWidth(var48.getIconWidth());
                  var50.setIconHeight(var48.getIconHeight());
                  var50.initSprite(var48.getIconWidth(), var48.getIconHeight(), 0, 0, false);
                  var50.clearFramesTextureData();
                  List var52 = var48.getFramesTextureData();
                  var50.setFramesTextureData(var52);
                  var50.setAnimationMetadata(var48.getAnimationMetadata());
               }

               var50.sheetWidth = var39;
               var50.sheetHeight = var42;
               var50.mipmapLevels = this.mipmapLevels;
               var50.setAnimationIndex(var48.getAnimationIndex());
               var48.bindSpriteTexture();
               boolean var53 = false;
               boolean var19 = true;

               try {
                  TextureUtil.uploadTextureMipmap(
                     var50.getFrameTextureData(0), var50.getIconWidth(), var50.getIconHeight(), var50.getOriginX(), var50.getOriginY(), var53, var19
                  );
               } catch (Exception var24) {
                  Config.dbg("Error uploading sprite single: " + var50 + ", parent: " + var48);
                  var24.printStackTrace();
               }
            }
         }

         Config.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
      }

      Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPost, this);
      this.updateIconGrid(var32.getCurrentWidth(), var32.getCurrentHeight());
      if (Config.equals(System.getProperty("saveTextureMap"), "true")) {
         Config.dbg("Exporting texture map: " + this.basePath);
         TextureUtils.saveGlTexture(
            "debug/" + this.basePath.replaceAll("/", "_"), this.getGlTextureId(), this.mipmapLevels, var32.getCurrentWidth(), var32.getCurrentHeight()
         );
      }
   }

   public TextureAtlasSprite getMissingSprite() {
      return this.missingImage;
   }

   public boolean isTerrainAnimationActive(TextureAtlasSprite var1) {
      return var1 == TextureUtils.recoveredField1081 || var1 == TextureUtils.recoveredField1055
         ? Config.isAnimatedWater()
         : (
            var1 == TextureUtils.recoveredField1097 || var1 == TextureUtils.recoveredField1105
               ? Config.isAnimatedLava()
               : (
                  var1 == TextureUtils.recoveredField1094 || var1 == TextureUtils.recoveredField1088
                     ? Config.isAnimatedFire()
                     : (
                        var1 == TextureUtils.recoveredField1067
                           ? Config.isAnimatedPortal()
                           : (var1 != TextureUtils.recoveredField1084 && var1 != TextureUtils.recoveredField1052 ? Config.isAnimatedTerrain() : true)
                     )
               )
         );
   }

   public TextureAtlasSprite getTextureExtry(String var1) {
      return this.mapRegisteredSprites.get(var1);
   }

   public int getCountRegisteredSprites() {
      return this.counterIndexInMap.getValue();
   }

   public String getBasePath() {
      return this.basePath;
   }

   public TextureMap(String var1, IIconCreator var2) {
      this(var1, var2, false);
   }

   public int detectMaxMipmapLevel(Map var1, IResourceManager var2) {
      int var3 = this.detectMinimumSpriteSize(var1, var2, 20);
      if (var3 < 16) {
         var3 = 16;
      }

      var3 = MathHelper.roundUpToPowerOfTwo(var3);
      if (var3 > 16) {
         Config.log("Sprite size: " + var3);
      }

      int var4 = MathHelper.calculateLogBaseTwo(var3);
      if (var4 < 4) {
         var4 = 4;
      }

      return var4;
   }

   public void checkEmissive(ResourceLocation var1, TextureAtlasSprite var2) {
      String var3 = EmissiveTextures.getSuffixEmissive();
      if (var3 != null && !var1.getResourcePath().endsWith(var3)) {
         ResourceLocation var4 = new ResourceLocation(var1.getResourceDomain(), var1.getResourcePath() + var3);
         ResourceLocation var5 = this.completeResourceLocation(var4);
         if (Config.hasResource(var5)) {
            TextureAtlasSprite var6 = this.registerSprite(var4);
            var6.isEmissive = true;
            var2.spriteEmissive = var6;
         }
      }
   }

   public ResourceLocation completeResourceLocation(ResourceLocation var1) {
      return this.completeResourceLocation(var1, 0);
   }

   public int getCountAnimations() {
      return this.listAnimatedSprites.size();
   }

   public void setMipmapLevels(int var1) {
      this.mipmapLevels = var1;
   }

   public int[] getMissingImageData(int var1) {
      BufferedImage var2 = new BufferedImage(16, 16, 2);
      var2.setRGB(0, 0, 16, 16, TextureUtil.missingTextureData, 0, 16);
      BufferedImage var3 = TextureUtils.scaleImage(var2, var1);
      int[] var4 = new int[var1 * var1];
      var3.getRGB(0, 0, var1, var1, var4, 0, var1);
      return var4;
   }
}
