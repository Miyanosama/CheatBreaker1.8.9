package net.optifine;

import io.netty.handler.codec.compression.JdkZlibDecoder$GzipState;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToLongTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.client.resources.model.SimpleBakedModel$Builder;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.IParserInt;
import net.optifine.config.NbtTagValue;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.reflect.Reflector;
import net.optifine.render.Blender;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties {
   public Map mapSprites;
   public JdkZlibDecoder$GzipState field_0038;
   public Map mapTextureLocations;
   public static int field_0035;
   public float duration;
   public Map<String, String> mapModels;
   public static int field_0039;
   public NbtTagValue[] nbtTagValues;
   public int[] items;
   public float rotation;
   public int textureWidth;
   public static int field_0024;
   public int textureHeight;
   public EntityFishHook field_0016;
   public RangeListInt enchantmentIds;
   public int hand;
   public RangeListInt stackSize;
   public static String field_0015;
   public ResourceLocation textureLocation;
   public String name = null;
   public int damageMask;
   public IBakedModel bakedModelFull;
   public boolean damagePercent;
   public String basePath = null;
   public RangeListInt enchantmentLevels;
   public int type = 1;
   public String model;
   public Map<String, IBakedModel> mapBakedModelsFull;
   public float speed;
   public Map<String, IBakedModel> mapBakedModelsTexture;
   public RangeListInt damage;
   public static int field_0008;
   public String texture;
   public int weight;
   public static int field_0012;
   public int layer;
   public IBakedModel bakedModelTexture;
   public static int field_0042;
   public Map<String, String> mapTextures;
   public ConcurrentHashMapV8$MapReduceKeysToLongTask field_0030;
   public TextureAtlasSprite sprite;
   public static int field_0017;
   public int blend;

   public RangeListInt parseRangeListInt(String var1) {
      return this.parseRangeListInt(var1, (IParserInt)null);
   }

   public float getTextureHeight(TextureManager var1) {
      if (this.textureHeight <= 0) {
         if (this.textureLocation != null) {
            ITextureObject var2 = var1.getTexture(this.textureLocation);
            int var3 = var2.getGlTextureId();
            int var4 = GlStateManager.getBoundTexture();
            GlStateManager.bindTexture(var3);
            this.textureHeight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            GlStateManager.bindTexture(var4);
         }

         if (this.textureHeight <= 0) {
            this.textureHeight = 16;
         }
      }

      return this.textureHeight;
   }

   public IBakedModel getBakedModel(ResourceLocation var1, boolean var2) {
      IBakedModel var3;
      Map var4;
      if (var2) {
         var3 = this.bakedModelFull;
         var4 = this.mapBakedModelsFull;
      } else {
         var3 = this.bakedModelTexture;
         var4 = this.mapBakedModelsTexture;
      }

      if (var1 != null && var4 != null) {
         String var5 = var1.getResourcePath();
         IBakedModel var6 = (IBakedModel)var4.get(var5);
         if (var6 != null) {
            return var6;
         }
      }

      return var3;
   }

   public boolean isUseTint() {
      return true;
   }

   public static Map parseTextures(Properties var0, String var1) {
      String var2 = "texture.";
      Map var3 = method_09686(var0, var2);
      if (var3.size() <= 0) {
         return null;
      } else {
         Set var4 = var3.keySet();
         LinkedHashMap var5 = new LinkedHashMap();

         for (Object var7 : var4) {
            String var8 = (String)var7;
            String var9 = (String)var3.get(var8);
            var9 = fixTextureName(var9, var1);
            var5.put(var8, var9);
         }

         return var5;
      }
   }

   public int parseHand(String var1) {
      if (var1 == null) {
         return 0;
      } else {
         var1 = var1.toLowerCase();
         if (var1.equals("any")) {
            return 0;
         } else if (var1.equals("main")) {
            return 1;
         } else if (var1.equals("off")) {
            return 2;
         } else {
            Config.warn("Invalid hand: " + var1);
            return 0;
         }
      }
   }

   public static String fixTextureName(String var0, String var1) {
      var0 = TextureUtils.fixResourcePath(var0, var1);
      if (!var0.startsWith(var1) && !var0.startsWith("textures/") && !var0.startsWith("mcpatcher/")) {
         var0 = var1 + "/" + var0;
      }

      if (var0.endsWith(".png")) {
         var0 = var0.substring(0, var0.length() - 4);
      }

      if (var0.startsWith("/")) {
         var0 = var0.substring(1);
      }

      return var0;
   }

   public RangeInt parseRangeInt(String var1) {
      if (var1 == null) {
         return null;
      } else {
         var1 = var1.trim();
         int var2 = var1.length() - var1.replace("-", "").length();
         if (var2 > 1) {
            Config.warn("Invalid range: " + var1);
            return null;
         } else {
            String[] var3 = Config.tokenize(var1, "- ");
            int[] var4 = new int[var3.length];

            for (int var5 = 0; var5 < var3.length; var5++) {
               String var6 = var3[var5];
               int var7 = Config.parseInt(var6, -1);
               if (var7 < 0) {
                  Config.warn("Invalid range: " + var1);
                  return null;
               }

               var4[var5] = var7;
            }

            if (var4.length == 1) {
               int var10 = var4[0];
               if (var1.startsWith("-")) {
                  return new RangeInt(0, var10);
               } else {
                  return var1.endsWith("-") ? new RangeInt(var10, 65535) : new RangeInt(var10, var10);
               }
            } else if (var4.length == 2) {
               int var9 = Math.min(var4[0], var4[1]);
               int var11 = Math.max(var4[0], var4[1]);
               return new RangeInt(var9, var11);
            } else {
               Config.warn("Invalid range: " + var1);
               return null;
            }
         }
      }
   }

   public float getTextureWidth(TextureManager var1) {
      if (this.textureWidth <= 0) {
         if (this.textureLocation != null) {
            ITextureObject var2 = var1.getTexture(this.textureLocation);
            int var3 = var2.getGlTextureId();
            int var4 = GlStateManager.getBoundTexture();
            GlStateManager.bindTexture(var3);
            this.textureWidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            GlStateManager.bindTexture(var4);
         }

         if (this.textureWidth <= 0) {
            this.textureWidth = 16;
         }
      }

      return this.textureWidth;
   }

   public static String parseModel(String var0, String var1, String var2, int var3, Map<String, String> var4) {
      if (var0 != null) {
         String var6 = ".json";
         if (var0.endsWith(var6)) {
            var0 = var0.substring(0, var0.length() - var6.length());
         }

         return fixModelName(var0, var2);
      } else if (var3 == 3) {
         return null;
      } else {
         if (var4 != null) {
            String var5 = (String)var4.get("model.bow_standby");
            if (var5 != null) {
               return var5;
            }
         }

         return var0;
      }
   }

   public static ModelBlock makeModelBlock(String[] var0) {
      StringBuffer var1 = new StringBuffer();
      var1.append("{\"parent\": \"builtin/generated\",\"textures\": {");

      for (int var2 = 0; var2 < var0.length; var2++) {
         String var3 = var0[var2];
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append("\"layer" + var2 + "\": \"" + var3 + "\"");
      }

      var1.append("}}");
      String var4 = var1.toString();
      return ModelBlock.deserialize(var4);
   }

   public CustomItemProperties(Properties var1, String var2) {
      this.items = null;
      this.texture = null;
      this.mapTextures = null;
      this.model = null;
      this.mapModels = null;
      this.damage = null;
      this.damagePercent = false;
      this.damageMask = 0;
      this.stackSize = null;
      this.enchantmentIds = null;
      this.enchantmentLevels = null;
      this.nbtTagValues = null;
      this.hand = 0;
      this.blend = 1;
      this.speed = 0.0F;
      this.rotation = 0.0F;
      this.layer = 0;
      this.duration = 1.0F;
      this.weight = 0;
      this.textureLocation = null;
      this.mapTextureLocations = null;
      this.sprite = null;
      this.mapSprites = null;
      this.bakedModelTexture = null;
      this.mapBakedModelsTexture = null;
      this.bakedModelFull = null;
      this.mapBakedModelsFull = null;
      this.textureWidth = 0;
      this.textureHeight = 0;
      this.name = parseName(var2);
      this.basePath = parseBasePath(var2);
      this.type = this.parseType(var1.getProperty("type"));
      this.items = this.parseItems(var1.getProperty("items"), var1.getProperty("matchItems"));
      this.mapModels = parseModels(var1, this.basePath);
      this.model = parseModel(var1.getProperty("model"), var2, this.basePath, this.type, this.mapModels);
      this.mapTextures = parseTextures(var1, this.basePath);
      boolean var3 = this.mapModels == null && this.model == null;
      this.texture = parseTexture(
         var1.getProperty("texture"), var1.getProperty("tile"), var1.getProperty("source"), var2, this.basePath, this.type, this.mapTextures, var3
      );
      String var4 = var1.getProperty("damage");
      if (var4 != null) {
         this.damagePercent = var4.contains("%");
         var4 = var4.replace("%", "");
         this.damage = this.parseRangeListInt(var4);
         this.damageMask = this.parseInt(var1.getProperty("damageMask"), 0);
      }

      this.stackSize = this.parseRangeListInt(var1.getProperty("stackSize"));
      this.enchantmentIds = this.parseRangeListInt(var1.getProperty("enchantmentIDs"), new ParserEnchantmentId());
      this.enchantmentLevels = this.parseRangeListInt(var1.getProperty("enchantmentLevels"));
      this.nbtTagValues = this.parseNbtTagValues(var1);
      this.hand = this.parseHand(var1.getProperty("hand"));
      this.blend = Blender.parseBlend(var1.getProperty("blend"));
      this.speed = this.parseFloat(var1.getProperty("speed"), 0.0F);
      this.rotation = this.parseFloat(var1.getProperty("rotation"), 0.0F);
      this.layer = this.parseInt(var1.getProperty("layer"), 0);
      this.weight = this.parseInt(var1.getProperty("weight"), 0);
      this.duration = this.parseFloat(var1.getProperty("duration"), 1.0F);
   }

   public void updateModelTexture(TextureMap var1, ItemModelGenerator var2) {
      if (this.texture != null || this.mapTextures != null) {
         String[] var3 = this.getModelTextures();
         boolean var4 = this.isUseTint();
         this.bakedModelTexture = makeBakedModel(var1, var2, var3, var4);
         if (this.type == 1 && this.mapTextures != null) {
            for (String var6 : this.mapTextures.keySet()) {
               String var7 = this.mapTextures.get(var6);
               String var8 = StrUtils.removePrefix(var6, "texture.");
               if (var8.startsWith("bow") || var8.startsWith("fishing_rod") || var8.startsWith("shield")) {
                  String[] var9 = new String[]{var7};
                  IBakedModel var10 = makeBakedModel(var1, var2, var9, var4);
                  if (this.mapBakedModelsTexture == null) {
                     this.mapBakedModelsTexture = new HashMap<>();
                  }

                  this.mapBakedModelsTexture.put(var8, var10);
               }
            }
         }
      }
   }

   public static IBakedModel bakeModel(TextureMap var0, ModelBlock var1, boolean var2) {
      ModelRotation var3 = ModelRotation.X0_Y0;
      boolean var4 = false;
      String var5 = var1.resolveTextureName("particle");
      TextureAtlasSprite var6 = var0.getAtlasSprite(new ResourceLocation(var5).toString());
      SimpleBakedModel$Builder var7 = new SimpleBakedModel$Builder(var1).setTexture(var6);

      for (BlockPart var9 : var1.getElements()) {
         for (EnumFacing var11 : var9.mapFaces.keySet()) {
            BlockPartFace var12 = var9.mapFaces.get(var11);
            if (!var2) {
               var12 = new BlockPartFace(var12.cullFace, -1, var12.texture, var12.blockFaceUV);
            }

            String var13 = var1.resolveTextureName(var12.texture);
            TextureAtlasSprite var14 = var0.getAtlasSprite(new ResourceLocation(var13).toString());
            BakedQuad var15 = makeBakedQuad(var9, var12, var14, var11, var3, var4);
            if (var12.cullFace == null) {
               var7.addGeneralQuad(var15);
            } else {
               var7.addFaceQuad(var3.rotateFace(var12.cullFace), var15);
            }
         }
      }

      return var7.makeBakedModel();
   }

   public static void checkNull(Object var0, String var1) {
      if (var0 == null) {
         throw new NullPointerException(var1);
      }
   }

   public void updateModelsFull() {
      ModelManager var1 = Config.getModelManager();
      IBakedModel var2 = var1.getMissingModel();
      if (this.model != null) {
         ResourceLocation var3 = getModelLocation(this.model);
         ModelResourceLocation var4 = new ModelResourceLocation(var3, "inventory");
         this.bakedModelFull = var1.getModel(var4);
         if (this.bakedModelFull == var2) {
            Config.warn("Custom Items: Model not found " + var4.getResourcePath());
            this.bakedModelFull = null;
         }
      }

      if (this.type == 1 && this.mapModels != null) {
         for (String var11 : this.mapModels.keySet()) {
            String var5 = this.mapModels.get(var11);
            String var6 = StrUtils.removePrefix(var11, "model.");
            if (var6.startsWith("bow") || var6.startsWith("fishing_rod") || var6.startsWith("shield")) {
               ResourceLocation var7 = getModelLocation(var5);
               ModelResourceLocation var8 = new ModelResourceLocation(var7, "inventory");
               IBakedModel var9 = var1.getModel(var8);
               if (var9 == var2) {
                  Config.warn("Custom Items: Model not found " + var8.getResourcePath());
               } else {
                  if (this.mapBakedModelsFull == null) {
                     this.mapBakedModelsFull = new HashMap<>();
                  }

                  this.mapBakedModelsFull.put(var6, var9);
               }
            }
         }
      }
   }

   public int parseInt(String var1, int var2) {
      if (var1 == null) {
         return var2;
      } else {
         var1 = var1.trim();
         int var3 = Config.parseInt(var1, Integer.MIN_VALUE);
         if (var3 == Integer.MIN_VALUE) {
            Config.warn("Invalid integer: " + var1);
            return var2;
         } else {
            return var3;
         }
      }
   }

   public static String fixModelName(String var0, String var1) {
      var0 = TextureUtils.fixResourcePath(var0, var1);
      boolean var2 = var0.startsWith("block/") || var0.startsWith("item/");
      if (!var0.startsWith(var1) && !var2 && !var0.startsWith("mcpatcher/")) {
         var0 = var1 + "/" + var0;
      }

      String var3 = ".json";
      if (var0.endsWith(var3)) {
         var0 = var0.substring(0, var0.length() - var3.length());
      }

      if (var0.startsWith("/")) {
         var0 = var0.substring(1);
      }

      return var0;
   }

   public static String parseName(String var0) {
      String var1 = var0;
      int var2 = var0.lastIndexOf(47);
      if (var2 >= 0) {
         var1 = var0.substring(var2 + 1);
      }

      int var3 = var1.lastIndexOf(46);
      if (var3 >= 0) {
         var1 = var1.substring(0, var3);
      }

      return var1;
   }

   public static ResourceLocation getModelLocation(String var0) {
      return Reflector.ModelLoader.exists() && !var0.startsWith("mcpatcher/") && !var0.startsWith("optifine/")
         ? new ResourceLocation("models/" + var0)
         : new ResourceLocation(var0);
   }

   public int[] detectItems() {
      Item var1 = Item.getByNameOrId(this.name);
      if (var1 == null) {
         return null;
      } else {
         int var2 = Item.getIdFromItem(var1);
         return var2 <= 0 ? null : new int[]{var2};
      }
   }

   public static Map method_09686(Properties var0, String var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (Object var4 : var0.keySet()) {
         String var5 = (String)var4;
         String var6 = var0.getProperty(var5);
         if (var5.startsWith(var1)) {
            var2.put(var5, var6);
         }
      }

      return var2;
   }

   public void loadModels(ModelBakery var1) {
      if (this.model != null) {
         loadItemModel(var1, this.model);
      }

      if (this.type == 1 && this.mapModels != null) {
         for (String var3 : this.mapModels.keySet()) {
            String var4 = this.mapModels.get(var3);
            String var5 = StrUtils.removePrefix(var3, "model.");
            if (var5.startsWith("bow") || var5.startsWith("fishing_rod") || var5.startsWith("shield")) {
               loadItemModel(var1, var4);
            }
         }
      }
   }

   public static Map parseModels(Properties var0, String var1) {
      String var2 = "model.";
      Map var3 = method_09686(var0, var2);
      if (var3.size() <= 0) {
         return null;
      } else {
         Set var4 = var3.keySet();
         LinkedHashMap var5 = new LinkedHashMap();

         for (Object var7 : var4) {
            String var8 = (String)var7;
            String var9 = (String)var3.get(var8);
            var9 = fixModelName(var9, var1);
            var5.put(var8, var9);
         }

         return var5;
      }
   }

   public NbtTagValue[] parseNbtTagValues(Properties var1) {
      String var2 = "nbt.";
      Map var3 = method_09686(var1, var2);
      if (var3.size() <= 0) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();

         for (Object var6 : var3.keySet()) {
            String var7 = (String)var6;
            String var8 = (String)var3.get(var7);
            String var9 = var7.substring(var2.length());
            NbtTagValue var10 = new NbtTagValue(var9, var8);
            var4.add(var10);
         }

         return var4.toArray(new NbtTagValue[var4.size()]);
      }
   }

   public String getMapTexture(Map<String, String> var1, String var2, String var3) {
      if (var1 == null) {
         return var3;
      } else {
         String var4 = (String)var1.get(var2);
         return var4 == null ? var3 : var4;
      }
   }

   @Override
   public String toString() {
      return "" + this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
   }

   public static BakedQuad makeBakedQuad(BlockPart var0, BlockPartFace var1, TextureAtlasSprite var2, EnumFacing var3, ModelRotation var4, boolean var5) {
      FaceBakery var6 = new FaceBakery();
      return var6.makeBakedQuad(var0.positionFrom, var0.positionTo, var1, var2, var3, var4, var0.partRotation, var5, var0.shade);
   }

   public int parseType(String var1) {
      if (var1 == null) {
         return 1;
      } else if (var1.equals("item")) {
         return 1;
      } else if (var1.equals("enchantment")) {
         return 2;
      } else if (var1.equals("armor")) {
         return 3;
      } else {
         Config.warn("Unknown method: " + var1);
         return 0;
      }
   }

   public void updateIcons(TextureMap var1) {
      if (this.texture != null) {
         this.textureLocation = this.getTextureLocation(this.texture);
         if (this.type == 1) {
            ResourceLocation var2 = this.getSpriteLocation(this.textureLocation);
            this.sprite = var1.registerSprite(var2);
         }
      }

      if (this.mapTextures != null) {
         this.mapTextureLocations = new HashMap();
         this.mapSprites = new HashMap();

         for (String var3 : this.mapTextures.keySet()) {
            String var4 = this.mapTextures.get(var3);
            ResourceLocation var5 = this.getTextureLocation(var4);
            this.mapTextureLocations.put(var3, var5);
            if (this.type == 1) {
               ResourceLocation var6 = this.getSpriteLocation(var5);
               TextureAtlasSprite var7 = var1.registerSprite(var6);
               this.mapSprites.put(var3, var7);
            }
         }
      }
   }

   public static String parseTexture(String var0, String var1, String var2, String var3, String var4, int var5, Map<String, String> var6, boolean var7) {
      if (var0 == null) {
         var0 = var1;
      }

      if (var0 == null) {
         var0 = var2;
      }

      if (var0 != null) {
         String var12 = ".png";
         if (var0.endsWith(var12)) {
            var0 = var0.substring(0, var0.length() - var12.length());
         }

         return fixTextureName(var0, var4);
      } else if (var5 == 3) {
         return null;
      } else {
         if (var6 != null) {
            String var8 = (String)var6.get("texture.bow_standby");
            if (var8 != null) {
               return var8;
            }
         }

         if (!var7) {
            return null;
         } else {
            String var11 = var3;
            int var9 = var3.lastIndexOf(47);
            if (var9 >= 0) {
               var11 = var3.substring(var9 + 1);
            }

            int var10 = var11.lastIndexOf(46);
            if (var10 >= 0) {
               var11 = var11.substring(0, var10);
            }

            return fixTextureName(var11, var4);
         }
      }
   }

   public int[] parseItems(String var1, String var2) {
      if (var1 == null) {
         var1 = var2;
      }

      if (var1 == null) {
         return null;
      } else {
         var1 = var1.trim();
         TreeSet var3 = new TreeSet();
         String[] var4 = Config.tokenize(var1, " ");

         for (int var5 = 0; var5 < var4.length; var5++) {
            String var6 = var4[var5];
            int var7 = Config.parseInt(var6, -1);
            if (var7 >= 0) {
               var3.add(new Integer(var7));
            } else {
               if (var6.contains("-")) {
                  String[] var8 = Config.tokenize(var6, "-");
                  if (var8.length == 2) {
                     int var9 = Config.parseInt(var8[0], -1);
                     int var10 = Config.parseInt(var8[1], -1);
                     if (var9 >= 0 && var10 >= 0) {
                        int var11 = Math.min(var9, var10);
                        int var12 = Math.max(var9, var10);

                        for (int var13 = var11; var13 <= var12; var13++) {
                           var3.add(new Integer(var13));
                        }
                        continue;
                     }
                  }
               }

               Item var18 = Item.getByNameOrId(var6);
               if (var18 == null) {
                  Config.warn("Item not found: " + var6);
               } else {
                  int var19 = Item.getIdFromItem(var18);
                  if (var19 <= 0) {
                     Config.warn("Item not found: " + var6);
                  } else {
                     var3.add(new Integer(var19));
                  }
               }
            }
         }

         Integer[] var15 = var3.toArray(new Integer[var3.size()]);
         int[] var16 = new int[var15.length];

         for (int var17 = 0; var17 < var16.length; var17++) {
            var16[var17] = var15[var17];
         }

         return var16;
      }
   }

   public static String parseBasePath(String var0) {
      int var1 = var0.lastIndexOf(47);
      return var1 < 0 ? "" : var0.substring(0, var1);
   }

   public ResourceLocation getSpriteLocation(ResourceLocation var1) {
      String var2 = var1.getResourcePath();
      var2 = StrUtils.removePrefix(var2, "textures/");
      var2 = StrUtils.removeSuffix(var2, ".png");
      return new ResourceLocation(var1.getResourceDomain(), var2);
   }

   public float parseFloat(String var1, float var2) {
      if (var1 == null) {
         return var2;
      } else {
         var1 = var1.trim();
         float var3 = Config.parseFloat(var1, Float.MIN_VALUE);
         if (var3 == Float.MIN_VALUE) {
            Config.warn("Invalid float: " + var1);
            return var2;
         } else {
            return var3;
         }
      }
   }

   public ResourceLocation getTextureLocation(String var1) {
      if (var1 == null) {
         return null;
      } else {
         ResourceLocation var2 = new ResourceLocation(var1);
         String var3 = var2.getResourceDomain();
         String var4 = var2.getResourcePath();
         if (!var4.contains("/")) {
            var4 = "textures/items/" + var4;
         }

         String var5 = var4 + ".png";
         ResourceLocation var6 = new ResourceLocation(var3, var5);
         boolean var7 = Config.hasResource(var6);
         if (!var7) {
            Config.warn("File not found: " + var5);
         }

         return var6;
      }
   }

   public static IBakedModel makeBakedModel(TextureMap var0, ItemModelGenerator var1, String[] var2, boolean var3) {
      String[] var4 = new String[var2.length];

      for (int var5 = 0; var5 < var4.length; var5++) {
         String var6 = var2[var5];
         var4[var5] = StrUtils.removePrefix(var6, "textures/");
      }

      ModelBlock var8 = makeModelBlock(var4);
      ModelBlock var9 = var1.makeItemModel(var0, var8);
      return bakeModel(var0, var9, var3);
   }

   public static void loadItemModel(ModelBakery var0, String var1) {
      ResourceLocation var2 = getModelLocation(var1);
      ModelResourceLocation var3 = new ModelResourceLocation(var2, "inventory");
      if (Reflector.ModelLoader.exists()) {
         try {
            Object var4 = Reflector.ModelLoader_VanillaLoader_INSTANCE.getValue();
            checkNull(var4, "vanillaLoader is null");
            Object var5 = Reflector.call(var4, Reflector.ModelLoader_VanillaLoader_loadModel, var3);
            checkNull(var5, "iModel is null");
            Map var6 = (Map)Reflector.getFieldValue(var0, Reflector.ModelLoader_stateModels);
            checkNull(var6, "stateModels is null");
            var6.put(var3, var5);
            Set var7 = (Set)Reflector.getFieldValue(var0, Reflector.ModelLoader_textures);
            checkNull(var7, "registryTextures is null");
            Collection var8 = (Collection)Reflector.call(var5, Reflector.IModel_getTextures);
            checkNull(var8, "modelTextures is null");
            var7.addAll(var8);
         } catch (Exception var9) {
            Config.warn("Error registering model with ModelLoader: " + var3 + ", " + var9.getClass().getName() + ": " + var9.getMessage());
         }
      } else {
         var0.loadItemModel(var2.toString(), var3, var2);
      }
   }

   public boolean isValid(String var1) {
      if (this.name == null || this.name.length() <= 0) {
         Config.warn("No name found: " + var1);
         return false;
      } else if (this.basePath == null) {
         Config.warn("No base path found: " + var1);
         return false;
      } else if (this.type == 0) {
         Config.warn("No type defined: " + var1);
         return false;
      } else {
         if (this.type == 1 || this.type == 3) {
            if (this.items == null) {
               this.items = this.detectItems();
            }

            if (this.items == null) {
               Config.warn("No items defined: " + var1);
               return false;
            }
         }

         if (this.texture == null && this.mapTextures == null && this.model == null && this.mapModels == null) {
            Config.warn("No texture or model specified: " + var1);
            return false;
         } else if (this.type == 2 && this.enchantmentIds == null) {
            Config.warn("No enchantmentIDs specified: " + var1);
            return false;
         } else {
            return true;
         }
      }
   }

   public RangeListInt parseRangeListInt(String var1, IParserInt var2) {
      if (var1 == null) {
         return null;
      } else {
         String[] var3 = Config.tokenize(var1, " ");
         RangeListInt var4 = new RangeListInt();

         for (int var5 = 0; var5 < var3.length; var5++) {
            String var6 = var3[var5];
            if (var2 != null) {
               int var7 = var2.parse(var6, Integer.MIN_VALUE);
               if (var7 != Integer.MIN_VALUE) {
                  var4.addRange(new RangeInt(var7, var7));
                  continue;
               }
            }

            RangeInt var8 = this.parseRangeInt(var6);
            if (var8 == null) {
               Config.warn("Invalid range list: " + var1);
               return null;
            }

            var4.addRange(var8);
         }

         return var4;
      }
   }

   public String[] getModelTextures() {
      if (this.type == 1 && this.items.length == 1) {
         Item var1 = Item.getItemById(this.items[0]);
         if (var1 == Items.potionitem && this.damage != null && this.damage.getCountRanges() > 0) {
            RangeInt var8 = this.damage.getRange(0);
            int var9 = var8.getMin();
            boolean var10 = (var9 & 16384) != 0;
            String var11 = this.getMapTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
            Object var12 = null;
            if (var10) {
               var12 = this.getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash");
            } else {
               var12 = this.getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
            }

            return new String[]{var11, (String)var12};
         }

         if (var1 instanceof ItemArmor) {
            ItemArmor var2 = (ItemArmor)var1;
            if (var2.getArmorMaterial() == ItemArmor$ArmorMaterial.LEATHER) {
               String var3 = "leather";
               String var4 = "helmet";
               if (var2.armorType == 0) {
                  var4 = "helmet";
               }

               if (var2.armorType == 1) {
                  var4 = "chestplate";
               }

               if (var2.armorType == 2) {
                  var4 = "leggings";
               }

               if (var2.armorType == 3) {
                  var4 = "boots";
               }

               String var5 = var3 + "_" + var4;
               String var6 = this.getMapTexture(this.mapTextures, "texture." + var5, "items/" + var5);
               String var7 = this.getMapTexture(this.mapTextures, "texture." + var5 + "_overlay", "items/" + var5 + "_overlay");
               return new String[]{var6, var7};
            }
         }
      }

      return new String[]{this.texture};
   }
}
