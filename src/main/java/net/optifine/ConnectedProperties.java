package net.optifine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.util.MathUtils;
import net.optifine.util.TextureUtils;

public class ConnectedProperties {
   public static final int recoveredField3037 = 0;
   public int[] ctmTileIndexes;
   public static final int recoveredField3038 = 1;
   public static final int recoveredField3039 = 16;
   public static final int recoveredField3040 = 63;
   public static final int recoveredField3041 = 128;
   public String[] tiles;
   public static final int recoveredField3042 = 6;
   public RangeListInt heights;
   public int sumAllWeights;
   public static final String recoveredField3043 = "<default>.png";
   public TextureAtlasSprite[] connectTileIcons;
   public MatchBlock[] matchBlocks;
   public int faces;
   public static final int recoveredField3044 = 2;
   public static final int recoveredField3045 = 3;
   public static final int recoveredField3046 = 2;
   public static final int recoveredField3047 = 128;
   public static final int recoveredField3048 = 9;
   public String[] matchTiles;
   public static final int recoveredField3049 = 4;
   public TextureAtlasSprite[] tileIcons;
   public IBlockState tintBlockState;
   public static final int recoveredField3050 = 12;
   public static final int recoveredField3051 = 8;
   public static final int recoveredField3052 = 5;
   public static final int recoveredField3053 = 128;
   public TextureAtlasSprite[] matchTileIcons;
   public static final int recoveredField3054 = 11;
   public int[] weights;
   public static final int recoveredField3055 = 3;
   public int[] metadatas;
   public static final int recoveredField3056 = 4;
   public static final String recoveredField3057 = "<skip>.png";
   public static final int recoveredField3058 = 2;
   public static final int recoveredField3059 = 0;
   public int method;
   public static final int recoveredField3060 = 32;
   public static final int recoveredField3061 = 8;
   public EnumWorldBlockLayer layer;
   public static final int recoveredField3062 = 1;
   public static final int recoveredField3063 = 10;
   public static final int recoveredField3064 = 2;
   public int randomLoops;
   public boolean innerSeams;
   public static final int recoveredField3065 = 6;
   public String basePath;
   public String name = null;
   public static final int recoveredField3066 = 60;
   public BiomeGenBase[] biomes;
   public static final int recoveredField3067 = 7;
   public int width;
   public String[] connectTiles;
   public static final int recoveredField3068 = 1;
   public int symmetry;
   public int height;
   public int tintIndex;
   public int[] sumWeights;
   public static final int recoveredField3069 = 13;
   public static final int recoveredField3070 = 1;
   public int renderPass;
   public MatchBlock[] connectBlocks;
   public static final int recoveredField3071 = 14;
   public NbtTagValue nbtName;
   public boolean linked;
   public static final int recoveredField3072 = 15;
   public int connect;

   public String[] parseTileNames(String var1) {
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = Config.tokenize(var1, " ,");

         for (int var4 = 0; var4 < var3.length; var4++) {
            String var5 = var3[var4];
            if (var5.contains("-")) {
               String[] var6 = Config.tokenize(var5, "-");
               if (var6.length == 2) {
                  int var7 = Config.parseInt(var6[0], -1);
                  int var8 = Config.parseInt(var6[1], -1);
                  if (var7 >= 0 && var8 >= 0) {
                     if (var7 > var8) {
                        Config.warn("Invalid interval: " + var5 + ", when parsing: " + var1);
                        continue;
                     }

                     for (int var9 = var7; var9 <= var8; var9++) {
                        var2.add(String.valueOf(var9));
                     }
                     continue;
                  }
               }
            }

            var2.add(var5);
         }

         String[] var10 = (java.lang.String[])var2.toArray(new String[var2.size()]);

         for (int var11 = 0; var11 < var10.length; var11++) {
            String var12 = var10[var11];
            var12 = TextureUtils.fixResourcePath(var12, this.basePath);
            if (!var12.startsWith(this.basePath) && !var12.startsWith("textures/") && !var12.startsWith("mcpatcher/")) {
               var12 = this.basePath + "/" + var12;
            }

            if (var12.endsWith(".png")) {
               var12 = var12.substring(0, var12.length() - 4);
            }

            if (var12.startsWith("/")) {
               var12 = var12.substring(1);
            }

            var10[var11] = var12;
         }

         return var10;
      }
   }

   public static int parseMethod(String var0) {
      if (var0 == null) {
         return 1;
      } else {
         var0 = var0.trim();
         if (var0.equals("ctm") || var0.equals("glass")) {
            return 1;
         } else if (var0.equals("ctm_compact")) {
            return 10;
         } else if (var0.equals("horizontal") || var0.equals("bookshelf")) {
            return 2;
         } else if (var0.equals("vertical")) {
            return 6;
         } else if (var0.equals("top")) {
            return 3;
         } else if (var0.equals("random")) {
            return 4;
         } else if (var0.equals("repeat")) {
            return 5;
         } else if (var0.equals("fixed")) {
            return 7;
         } else if (var0.equals("horizontal+vertical") || var0.equals("h+v")) {
            return 8;
         } else if (var0.equals("vertical+horizontal") || var0.equals("v+h")) {
            return 9;
         } else if (var0.equals("overlay")) {
            return 11;
         } else if (var0.equals("overlay_fixed")) {
            return 12;
         } else if (var0.equals("overlay_random")) {
            return 13;
         } else if (var0.equals("overlay_repeat")) {
            return 14;
         } else if (var0.equals("overlay_ctm")) {
            return 15;
         } else {
            Config.warn("Unknown method: " + var0);
            return 0;
         }
      }
   }

   public static int parseFaces(String var0) {
      if (var0 == null) {
         return 63;
      } else {
         String[] var1 = Config.tokenize(var0, " ,");
         int var2 = 0;

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            int var5 = parseFace(var4);
            var2 |= var5;
         }

         return var2;
      }
   }

   public boolean matchesBiome(BiomeGenBase var1) {
      return Matches.biome(var1, this.biomes);
   }

   public static IProperty getProperty(String var0, Collection var1) {
      for (Object var3 : var1) {
         IProperty var4 = (IProperty)var3;
         if (var0.equals(var4.getName())) {
            return var4;
         }
      }

      return null;
   }

   public static int parseFace(String var0) {
      var0 = var0.toLowerCase();
      if (var0.equals("bottom") || var0.equals("down")) {
         return 1;
      } else if (var0.equals("top") || var0.equals("up")) {
         return 2;
      } else if (var0.equals("north")) {
         return 4;
      } else if (var0.equals("south")) {
         return 8;
      } else if (var0.equals("east")) {
         return 32;
      } else if (var0.equals("west")) {
         return 16;
      } else if (var0.equals("sides")) {
         return 60;
      } else if (var0.equals("all")) {
         return 63;
      } else {
         Config.warn("Unknown face: " + var0);
         return 128;
      }
   }

   public int detectConnect() {
      return this.matchBlocks != null ? 1 : (this.matchTiles != null ? 2 : 128);
   }

   public boolean method_21601(String var1) {
      if (!this.isValidRepeat(var1)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public boolean isValidCtm(String var1) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-11 16-27 32-43 48-58");
      }

      if (this.tiles.length < 47) {
         Config.warn("Invalid tiles, must be at least 47: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public MatchBlock[] detectMatchBlocks() {
      int[] var1 = this.detectMatchBlockIds();
      if (var1 == null) {
         return null;
      } else {
         MatchBlock[] var2 = new MatchBlock[var1.length];

         for (int var3 = 0; var3 < var2.length; var3++) {
            var2[var3] = new MatchBlock(var1[var3]);
         }

         return var2;
      }
   }

   @Override
   public String toString() {
      return "CTM name: "
         + this.name
         + ", basePath: "
         + this.basePath
         + ", matchBlocks: "
         + Config.arrayToString(this.matchBlocks)
         + ", matchTiles: "
         + Config.arrayToString(this.matchTiles);
   }

   public void updateIcons(TextureMap var1) {
      if (this.matchTiles != null) {
         this.matchTileIcons = registerIcons(this.matchTiles, var1, false, false);
      }

      if (this.connectTiles != null) {
         this.connectTileIcons = registerIcons(this.connectTiles, var1, false, false);
      }

      if (this.tiles != null) {
         this.tileIcons = registerIcons(this.tiles, var1, true, !isMethodOverlay(this.method));
      }
   }

   public boolean isValidCtmCompact(String var1) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-4");
      }

      if (this.tiles.length < 5) {
         Config.warn("Invalid tiles, must be at least 5: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public int getMax(int[] var1, int var2) {
      if (var1 == null) {
         return var2;
      } else {
         for (int var3 = 0; var3 < var1.length; var3++) {
            int var4 = var1[var3];
            if (var4 > var2) {
               var2 = var4;
            }
         }

         return var2;
      }
   }

   public ConnectedProperties(Properties var1, String var2) {
      this.basePath = null;
      this.matchBlocks = null;
      this.metadatas = null;
      this.matchTiles = null;
      this.method = 0;
      this.tiles = null;
      this.connect = 0;
      this.faces = 63;
      this.biomes = null;
      this.heights = null;
      this.renderPass = 0;
      this.innerSeams = false;
      this.ctmTileIndexes = null;
      this.width = 0;
      this.height = 0;
      this.weights = null;
      this.randomLoops = 0;
      this.symmetry = 1;
      this.linked = false;
      this.nbtName = null;
      this.sumWeights = null;
      this.sumAllWeights = 1;
      this.matchTileIcons = null;
      this.tileIcons = null;
      this.connectBlocks = null;
      this.connectTiles = null;
      this.connectTileIcons = null;
      this.tintIndex = -1;
      this.tintBlockState = Blocks.air.getDefaultState();
      this.layer = null;
      ConnectedParser var3 = new ConnectedParser("ConnectedTextures");
      this.name = var3.parseName(var2);
      this.basePath = var3.parseBasePath(var2);
      this.matchBlocks = var3.parseMatchBlocks(var1.getProperty("matchBlocks"));
      this.metadatas = var3.parseIntList(var1.getProperty("metadata"));
      this.matchTiles = this.parseMatchTiles(var1.getProperty("matchTiles"));
      this.method = parseMethod(var1.getProperty("method"));
      this.tiles = this.parseTileNames(var1.getProperty("tiles"));
      this.connect = parseConnect(var1.getProperty("connect"));
      this.faces = parseFaces(var1.getProperty("faces"));
      this.biomes = var3.parseBiomes(var1.getProperty("biomes"));
      this.heights = var3.parseRangeListInt(var1.getProperty("heights"));
      if (this.heights == null) {
         int var4 = var3.parseInt(var1.getProperty("minHeight"), -1);
         int var5 = var3.parseInt(var1.getProperty("maxHeight"), 1024);
         if (var4 != -1 || var5 != 1024) {
            this.heights = new RangeListInt(new RangeInt(var4, var5));
         }
      }

      this.renderPass = var3.parseInt(var1.getProperty("renderPass"), -1);
      this.innerSeams = var3.parseBoolean(var1.getProperty("innerSeams"), false);
      this.ctmTileIndexes = this.parseCtmTileIndexes(var1);
      this.width = var3.parseInt(var1.getProperty("width"), -1);
      this.height = var3.parseInt(var1.getProperty("height"), -1);
      this.weights = var3.parseIntList(var1.getProperty("weights"));
      this.randomLoops = var3.parseInt(var1.getProperty("randomLoops"), 0);
      this.symmetry = parseSymmetry(var1.getProperty("symmetry"));
      this.linked = var3.parseBoolean(var1.getProperty("linked"), false);
      this.nbtName = var3.parseNbtTagValue("name", var1.getProperty("name"));
      this.connectBlocks = var3.parseMatchBlocks(var1.getProperty("connectBlocks"));
      this.connectTiles = this.parseMatchTiles(var1.getProperty("connectTiles"));
      this.tintIndex = var3.parseInt(var1.getProperty("tintIndex"), -1);
      this.tintBlockState = var3.parseBlockState(var1.getProperty("tintBlock"), Blocks.air.getDefaultState());
      this.layer = var3.parseBlockRenderLayer(var1.getProperty("layer"), EnumWorldBlockLayer.CUTOUT_MIPPED);
   }

   public boolean matchesBlockId(int var1) {
      return Matches.blockId(var1, this.matchBlocks);
   }

   public boolean isValidVerticalHorizontal(String var1) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for vertical+horizontal: " + var1);
         return false;
      } else if (this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean isValidFixed(String var1) {
      if (this.tiles == null) {
         Config.warn("Tiles not defined: " + var1);
         return false;
      } else if (this.tiles.length != 1) {
         Config.warn("Number of tiles should be 1 for method: fixed.");
         return false;
      } else {
         return true;
      }
   }

   public boolean method_21634(String var1) {
      if (!this.isValidCtm(var1)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public boolean method_21599(String var1) {
      if (this.name == null || this.name.length() <= 0) {
         Config.warn("No name found: " + var1);
         return false;
      } else if (this.basePath == null) {
         Config.warn("No base path found: " + var1);
         return false;
      } else {
         if (this.matchBlocks == null) {
            this.matchBlocks = this.detectMatchBlocks();
         }

         if (this.matchTiles == null && this.matchBlocks == null) {
            this.matchTiles = this.detectMatchTiles();
         }

         if (this.matchBlocks == null && this.matchTiles == null) {
            Config.warn("No matchBlocks or matchTiles specified: " + var1);
            return false;
         } else if (this.method == 0) {
            Config.warn("No method: " + var1);
            return false;
         } else if (this.tiles != null && this.tiles.length > 0) {
            if (this.connect == 0) {
               this.connect = this.detectConnect();
            }

            if (this.connect == 128) {
               Config.warn("Invalid connect in: " + var1);
               return false;
            } else if (this.renderPass > 0) {
               Config.warn("Render pass not supported: " + this.renderPass);
               return false;
            } else if ((this.faces & 128) != 0) {
               Config.warn("Invalid faces in: " + var1);
               return false;
            } else if ((this.symmetry & 128) != 0) {
               Config.warn("Invalid symmetry in: " + var1);
               return false;
            } else {
               switch (this.method) {
                  case 1:
                     return this.isValidCtm(var1);
                  case 2:
                     return this.isValidHorizontal(var1);
                  case 3:
                     return this.isValidTop(var1);
                  case 4:
                     return this.method_21620(var1);
                  case 5:
                     return this.isValidRepeat(var1);
                  case 6:
                     return this.isValidVertical(var1);
                  case 7:
                     return this.isValidFixed(var1);
                  case 8:
                     return this.isValidHorizontalVertical(var1);
                  case 9:
                     return this.isValidVerticalHorizontal(var1);
                  case 10:
                     return this.isValidCtmCompact(var1);
                  case 11:
                     return this.method_21629(var1);
                  case 12:
                     return this.method_21605(var1);
                  case 13:
                     return this.method_21622(var1);
                  case 14:
                     return this.method_21601(var1);
                  case 15:
                     return this.method_21634(var1);
                  default:
                     Config.warn("Unknown method: " + var1);
                     return false;
               }
            }
         } else {
            Config.warn("No tiles specified: " + var1);
            return false;
         }
      }
   }

   public String[] parseMatchTiles(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String[] var2 = Config.tokenize(var1, " ");

         for (int var3 = 0; var3 < var2.length; var3++) {
            String var4 = var2[var3];
            if (var4.endsWith(".png")) {
               var4 = var4.substring(0, var4.length() - 4);
            }

            var4 = TextureUtils.fixResourcePath(var4, this.basePath);
            var2[var3] = var4;
         }

         return var2;
      }
   }

   public boolean method_21622(String var1) {
      if (!this.method_21620(var1)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public int getMetadataMax() {
      int var1 = -1;
      var1 = this.getMax(this.metadatas, var1);
      if (this.matchBlocks != null) {
         for (int var2 = 0; var2 < this.matchBlocks.length; var2++) {
            MatchBlock var3 = this.matchBlocks[var2];
            var1 = this.getMax(var3.getMetadatas(), var1);
         }
      }

      return var1;
   }

   public boolean method_21605(String var1) {
      if (!this.isValidFixed(var1)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public boolean matchesIcon(TextureAtlasSprite var1) {
      return Matches.sprite(var1, this.matchTileIcons);
   }

   public static boolean isMethodOverlay(int var0) {
      switch (var0) {
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
            return true;
         default:
            return false;
      }
   }

   public boolean isValidTop(String var1) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("66");
      }

      if (this.tiles.length != 1) {
         Config.warn("Invalid tiles, must be exactly 1: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean method_21629(String var1) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-16");
      }

      if (this.tiles.length < 17) {
         Config.warn("Invalid tiles, must be at least 17: " + var1);
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public static int parseSymmetry(String var0) {
      if (var0 == null) {
         return 1;
      } else {
         var0 = var0.trim();
         if (var0.equals("opposite")) {
            return 2;
         } else if (var0.equals("all")) {
            return 6;
         } else {
            Config.warn("Unknown symmetry: " + var0);
            return 1;
         }
      }
   }

   public boolean isValidHorizontal(String var1) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("12-15");
      }

      if (this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean method_21620(String var1) {
      if (this.tiles != null && this.tiles.length > 0) {
         if (this.weights != null) {
            if (this.weights.length > this.tiles.length) {
               Config.warn("More weights defined than tiles, trimming weights: " + var1);
               int[] var2 = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, var2, 0, var2.length);
               this.weights = var2;
            }

            if (this.weights.length < this.tiles.length) {
               Config.warn("Less weights defined than tiles, expanding weights: " + var1);
               int[] var5 = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, var5, 0, this.weights.length);
               int var3 = MathUtils.getAverage(this.weights);

               for (int var4 = this.weights.length; var4 < var5.length; var4++) {
                  var5[var4] = var3;
               }

               this.weights = var5;
            }

            this.sumWeights = new int[this.weights.length];
            int var6 = 0;

            for (int var7 = 0; var7 < this.weights.length; var7++) {
               var6 += this.weights[var7];
               this.sumWeights[var7] = var6;
            }

            this.sumAllWeights = var6;
            if (this.sumAllWeights <= 0) {
               Config.warn("Invalid sum of all weights: " + var6);
               this.sumAllWeights = 1;
            }
         }

         if (this.randomLoops >= 0 && this.randomLoops <= 9) {
            return true;
         } else {
            Config.warn("Invalid randomLoops: " + this.randomLoops);
            return false;
         }
      } else {
         Config.warn("Tiles not defined: " + var1);
         return false;
      }
   }

   public boolean isValidRepeat(String var1) {
      if (this.tiles == null) {
         Config.warn("Tiles not defined: " + var1);
         return false;
      } else if (this.width <= 0) {
         Config.warn("Invalid width: " + var1);
         return false;
      } else if (this.height <= 0) {
         Config.warn("Invalid height: " + var1);
         return false;
      } else if (this.tiles.length != this.width * this.height) {
         Config.warn("Number of tiles does not equal width x height: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean isValidHorizontalVertical(String var1) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for horizontal+vertical: " + var1);
         return false;
      } else if (this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean matchesBlock(int var1, int var2) {
      return !Matches.block(var1, var2, this.matchBlocks) ? false : Matches.metadata(var2, this.metadatas);
   }

   public static String method_21595(String var0) {
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

   public static String method_21598(String var0) {
      int var1 = var0.lastIndexOf(47);
      return var1 < 0 ? "" : var0.substring(0, var1);
   }

   public static TextureAtlasSprite[] registerIcons(String[] var0, TextureMap var1, boolean var2, boolean var3) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();

         for (int var5 = 0; var5 < var0.length; var5++) {
            String var6 = var0[var5];
            ResourceLocation var7 = new ResourceLocation(var6);
            String var8 = var7.getResourceDomain();
            String var9 = var7.getResourcePath();
            if (!var9.contains("/")) {
               var9 = "textures/blocks/" + var9;
            }

            String var10 = var9 + ".png";
            if (var2 && var10.endsWith("<skip>.png")) {
               var4.add(null);
            } else if (var3 && var10.endsWith("<default>.png")) {
               var4.add(ConnectedTextures.SPRITE_DEFAULT);
            } else {
               ResourceLocation var11 = new ResourceLocation(var8, var10);
               boolean var12 = Config.hasResource(var11);
               if (!var12) {
                  Config.warn("File not found: " + var10);
               }

               String var13 = "textures/";
               String var14 = var9;
               if (var9.startsWith(var13)) {
                  var14 = var9.substring(var13.length());
               }

               ResourceLocation var15 = new ResourceLocation(var8, var14);
               TextureAtlasSprite var16 = var1.registerSprite(var15);
               var4.add(var16);
            }
         }

         return (net.minecraft.client.renderer.texture.TextureAtlasSprite[])var4.toArray(new TextureAtlasSprite[var4.size()]);
      }
   }

   public static int parseConnect(String var0) {
      if (var0 == null) {
         return 0;
      } else {
         var0 = var0.trim();
         if (var0.equals("block")) {
            return 1;
         } else if (var0.equals("tile")) {
            return 2;
         } else if (var0.equals("material")) {
            return 3;
         } else {
            Config.warn("Unknown connect: " + var0);
            return 128;
         }
      }
   }

   public String[] detectMatchTiles() {
      TextureAtlasSprite var1 = getIcon(this.name);
      return var1 == null ? null : new String[]{this.name};
   }

   public int[] parseCtmTileIndexes(Properties var1) {
      if (this.tiles == null) {
         return null;
      } else {
         HashMap var2 = new HashMap();

         for (Object var4 : var1.keySet()) {
            if (var4 instanceof String) {
               String var5 = (String)var4;
               String var6 = "ctm.";
               if (var5.startsWith(var6)) {
                  String var7 = var5.substring(var6.length());
                  String var8 = var1.getProperty(var5);
                  if (var8 != null) {
                     var8 = var8.trim();
                     int var9 = Config.parseInt(var7, -1);
                     if (var9 >= 0 && var9 <= 46) {
                        int var10 = Config.parseInt(var8, -1);
                        if (var10 >= 0 && var10 < this.tiles.length) {
                           var2.put(var9, var10);
                        } else {
                           Config.warn("Invalid CTM tile index: " + var8);
                        }
                     } else {
                        Config.warn("Invalid CTM index: " + var7);
                     }
                  }
               }
            }
         }

         if (var2.isEmpty()) {
            return null;
         } else {
            int[] var11 = new int[47];

            for (int var12 = 0; var12 < var11.length; var12++) {
               var11[var12] = -1;
               if (var2.containsKey(var12)) {
                  var11[var12] = (Integer)var2.get(var12);
               }
            }

            return var11;
         }
      }
   }

   public static TextureAtlasSprite getIcon(String var0) {
      TextureMap var1 = Minecraft.getMinecraft().getTextureMapBlocks();
      TextureAtlasSprite var2 = var1.getSpriteSafe(var0);
      return var2 != null ? var2 : var1.getSpriteSafe("blocks/" + var0);
   }

   public int[] detectMatchBlockIds() {
      if (!this.name.startsWith("block")) {
         return null;
      } else {
         int var1 = "block".length();

         int var2;
         for (var2 = var1; var2 < this.name.length(); var2++) {
            char var3 = this.name.charAt(var2);
            if (var3 < '0' || var3 > '9') {
               break;
            }
         }

         if (var2 == var1) {
            return null;
         } else {
            String var5 = this.name.substring(var1, var2);
            int var4 = Config.parseInt(var5, -1);
            return var4 < 0 ? null : new int[]{var4};
         }
      }
   }

   public boolean isValidVertical(String var1) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for vertical: " + var1);
         return false;
      } else if (this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + var1);
         return false;
      } else {
         return true;
      }
   }
}
