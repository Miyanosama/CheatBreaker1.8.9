package net.minecraft.world.gen;

import com.cheatbreaker.client.ui.mainmenu.AccountLoginButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.world.ColorizerGrass;

public class ChunkProviderSettings$Factory {
   public int coalMaxHeight;
   public static Gson JSON_ADAPTER = new GsonBuilder()
      .registerTypeAdapter(ChunkProviderSettings$Factory.class, new ChunkProviderSettings$Serializer())
      .create();
   public float baseSize;
   public int riverSize;
   public ColorizerGrass field_0011;
   public float upperLimitScale;
   public float biomeScaleWeight;
   public int andesiteMaxHeight;
   public int waterLakeChance;
   public int diamondMinHeight;
   public int redstoneMinHeight;
   public int gravelCount;
   public int gravelSize;
   public int seaLevel;
   public int redstoneCount;
   public int coalCount;
   public ModelRotation field_0008;
   public int diamondCount;
   public int diamondSize;
   public int dirtMinHeight;
   public float mainNoiseScaleY;
   public int dioriteCount;
   public float depthNoiseScaleZ;
   public int ironCount;
   public boolean useLavaOceans;
   public int coalMinHeight;
   public float mainNoiseScaleX;
   public int dioriteSize;
   public BlockTallGrass$EnumType field_0076;
   public boolean useLavaLakes;
   public int gravelMinHeight;
   public int dioriteMaxHeight;
   public int andesiteSize;
   public int graniteCount;
   public int goldCount;
   public boolean useStrongholds;
   public int ironMinHeight;
   public int graniteMaxHeight;
   public int goldMaxHeight;
   public int ironMaxHeight;
   public int ironSize;
   public float coordinateScale = 684.412F;
   public int lapisSpread;
   public float biomeDepthWeight;
   public boolean useVillages;
   public boolean useCaves;
   public int goldMinHeight;
   public int fixedBiome;
   public int gravelMaxHeight;
   public float depthNoiseScaleExponent;
   public int dioriteMinHeight;
   public boolean useRavines;
   public int lapisCount;
   public int lapisSize;
   public int lapisCenterHeight;
   public int goldSize;
   public int dirtSize;
   public int biomeSize;
   public float mainNoiseScaleZ;
   public boolean useWaterLakes;
   public int coalSize;
   public boolean useDungeons;
   public int andesiteCount;
   public boolean useMineShafts;
   public float depthNoiseScaleX;
   public int lavaLakeChance;
   public int dungeonChance;
   public float heightScale = 684.412F;
   public int redstoneMaxHeight;
   public float biomeDepthOffset;
   public int diamondMaxHeight;
   public int graniteSize;
   public int dirtMaxHeight;
   public boolean useTemples;
   public float stretchY;
   public int andesiteMinHeight;
   public float biomeScaleOffset;
   public boolean useMonuments;
   public int dirtCount;
   public AccountLoginButton field_0074;
   public int graniteMinHeight;
   public int redstoneSize;
   public float lowerLimitScale;

   public ChunkProviderSettings func_177864_b() {
      return new ChunkProviderSettings(this, null);
   }

   @Override
   public String toString() {
      return JSON_ADAPTER.toJson(this);
   }

   public ChunkProviderSettings$Factory() {
      this.upperLimitScale = 512.0F;
      this.lowerLimitScale = 512.0F;
      this.depthNoiseScaleX = 200.0F;
      this.depthNoiseScaleZ = 200.0F;
      this.depthNoiseScaleExponent = 0.5F;
      this.mainNoiseScaleX = 80.0F;
      this.mainNoiseScaleY = 160.0F;
      this.mainNoiseScaleZ = 80.0F;
      this.baseSize = 8.5F;
      this.stretchY = 12.0F;
      this.biomeDepthWeight = 1.0F;
      this.biomeDepthOffset = 0.0F;
      this.biomeScaleWeight = 1.0F;
      this.biomeScaleOffset = 0.0F;
      this.seaLevel = 63;
      this.useCaves = true;
      this.useDungeons = true;
      this.dungeonChance = 8;
      this.useStrongholds = true;
      this.useVillages = true;
      this.useMineShafts = true;
      this.useTemples = true;
      this.useMonuments = true;
      this.useRavines = true;
      this.useWaterLakes = true;
      this.waterLakeChance = 4;
      this.useLavaLakes = true;
      this.lavaLakeChance = 80;
      this.useLavaOceans = false;
      this.fixedBiome = -1;
      this.biomeSize = 4;
      this.riverSize = 4;
      this.dirtSize = 33;
      this.dirtCount = 10;
      this.dirtMinHeight = 0;
      this.dirtMaxHeight = 256;
      this.gravelSize = 33;
      this.gravelCount = 8;
      this.gravelMinHeight = 0;
      this.gravelMaxHeight = 256;
      this.graniteSize = 33;
      this.graniteCount = 10;
      this.graniteMinHeight = 0;
      this.graniteMaxHeight = 80;
      this.dioriteSize = 33;
      this.dioriteCount = 10;
      this.dioriteMinHeight = 0;
      this.dioriteMaxHeight = 80;
      this.andesiteSize = 33;
      this.andesiteCount = 10;
      this.andesiteMinHeight = 0;
      this.andesiteMaxHeight = 80;
      this.coalSize = 17;
      this.coalCount = 20;
      this.coalMinHeight = 0;
      this.coalMaxHeight = 128;
      this.ironSize = 9;
      this.ironCount = 20;
      this.ironMinHeight = 0;
      this.ironMaxHeight = 64;
      this.goldSize = 9;
      this.goldCount = 2;
      this.goldMinHeight = 0;
      this.goldMaxHeight = 32;
      this.redstoneSize = 8;
      this.redstoneCount = 8;
      this.redstoneMinHeight = 0;
      this.redstoneMaxHeight = 16;
      this.diamondSize = 8;
      this.diamondCount = 1;
      this.diamondMinHeight = 0;
      this.diamondMaxHeight = 16;
      this.lapisSize = 7;
      this.lapisCount = 1;
      this.lapisCenterHeight = 16;
      this.lapisSpread = 16;
      this.func_177863_a();
   }

   public void func_177863_a() {
      this.coordinateScale = 684.412F;
      this.heightScale = 684.412F;
      this.upperLimitScale = 512.0F;
      this.lowerLimitScale = 512.0F;
      this.depthNoiseScaleX = 200.0F;
      this.depthNoiseScaleZ = 200.0F;
      this.depthNoiseScaleExponent = 0.5F;
      this.mainNoiseScaleX = 80.0F;
      this.mainNoiseScaleY = 160.0F;
      this.mainNoiseScaleZ = 80.0F;
      this.baseSize = 8.5F;
      this.stretchY = 12.0F;
      this.biomeDepthWeight = 1.0F;
      this.biomeDepthOffset = 0.0F;
      this.biomeScaleWeight = 1.0F;
      this.biomeScaleOffset = 0.0F;
      this.seaLevel = 63;
      this.useCaves = true;
      this.useDungeons = true;
      this.dungeonChance = 8;
      this.useStrongholds = true;
      this.useVillages = true;
      this.useMineShafts = true;
      this.useTemples = true;
      this.useMonuments = true;
      this.useRavines = true;
      this.useWaterLakes = true;
      this.waterLakeChance = 4;
      this.useLavaLakes = true;
      this.lavaLakeChance = 80;
      this.useLavaOceans = false;
      this.fixedBiome = -1;
      this.biomeSize = 4;
      this.riverSize = 4;
      this.dirtSize = 33;
      this.dirtCount = 10;
      this.dirtMinHeight = 0;
      this.dirtMaxHeight = 256;
      this.gravelSize = 33;
      this.gravelCount = 8;
      this.gravelMinHeight = 0;
      this.gravelMaxHeight = 256;
      this.graniteSize = 33;
      this.graniteCount = 10;
      this.graniteMinHeight = 0;
      this.graniteMaxHeight = 80;
      this.dioriteSize = 33;
      this.dioriteCount = 10;
      this.dioriteMinHeight = 0;
      this.dioriteMaxHeight = 80;
      this.andesiteSize = 33;
      this.andesiteCount = 10;
      this.andesiteMinHeight = 0;
      this.andesiteMaxHeight = 80;
      this.coalSize = 17;
      this.coalCount = 20;
      this.coalMinHeight = 0;
      this.coalMaxHeight = 128;
      this.ironSize = 9;
      this.ironCount = 20;
      this.ironMinHeight = 0;
      this.ironMaxHeight = 64;
      this.goldSize = 9;
      this.goldCount = 2;
      this.goldMinHeight = 0;
      this.goldMaxHeight = 32;
      this.redstoneSize = 8;
      this.redstoneCount = 8;
      this.redstoneMinHeight = 0;
      this.redstoneMaxHeight = 16;
      this.diamondSize = 8;
      this.diamondCount = 1;
      this.diamondMinHeight = 0;
      this.diamondMaxHeight = 16;
      this.lapisSize = 7;
      this.lapisCount = 1;
      this.lapisCenterHeight = 16;
      this.lapisSpread = 16;
   }

   public static ChunkProviderSettings$Factory jsonToFactory(String var0) {
      if (var0.length() == 0) {
         return new ChunkProviderSettings$Factory();
      } else {
         try {
            return (ChunkProviderSettings$Factory)JSON_ADAPTER.fromJson(var0, ChunkProviderSettings$Factory.class);
         } catch (Exception var2) {
            return new ChunkProviderSettings$Factory();
         }
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ChunkProviderSettings$Factory var2 = (ChunkProviderSettings$Factory)var1;
         return this.andesiteCount != var2.andesiteCount
            ? false
            : (
               this.andesiteMaxHeight != var2.andesiteMaxHeight
                  ? false
                  : (
                     this.andesiteMinHeight != var2.andesiteMinHeight
                        ? false
                        : (
                           this.andesiteSize != var2.andesiteSize
                              ? false
                              : (
                                 Float.compare(var2.baseSize, this.baseSize) != 0
                                    ? false
                                    : (
                                       Float.compare(var2.biomeDepthOffset, this.biomeDepthOffset) != 0
                                          ? false
                                          : (
                                             Float.compare(var2.biomeDepthWeight, this.biomeDepthWeight) != 0
                                                ? false
                                                : (
                                                   Float.compare(var2.biomeScaleOffset, this.biomeScaleOffset) != 0
                                                      ? false
                                                      : (
                                                         Float.compare(var2.biomeScaleWeight, this.biomeScaleWeight) != 0
                                                            ? false
                                                            : (
                                                               this.biomeSize != var2.biomeSize
                                                                  ? false
                                                                  : (
                                                                     this.coalCount != var2.coalCount
                                                                        ? false
                                                                        : (
                                                                           this.coalMaxHeight != var2.coalMaxHeight
                                                                              ? false
                                                                              : (
                                                                                 this.coalMinHeight != var2.coalMinHeight
                                                                                    ? false
                                                                                    : (
                                                                                       this.coalSize != var2.coalSize
                                                                                          ? false
                                                                                          : (
                                                                                             Float.compare(var2.coordinateScale, this.coordinateScale) != 0
                                                                                                ? false
                                                                                                : (
                                                                                                   Float.compare(
                                                                                                            var2.depthNoiseScaleExponent,
                                                                                                            this.depthNoiseScaleExponent
                                                                                                         )
                                                                                                         != 0
                                                                                                      ? false
                                                                                                      : (
                                                                                                         Float.compare(
                                                                                                                  var2.depthNoiseScaleX, this.depthNoiseScaleX
                                                                                                               )
                                                                                                               != 0
                                                                                                            ? false
                                                                                                            : (
                                                                                                               Float.compare(
                                                                                                                        var2.depthNoiseScaleZ,
                                                                                                                        this.depthNoiseScaleZ
                                                                                                                     )
                                                                                                                     != 0
                                                                                                                  ? false
                                                                                                                  : (
                                                                                                                     this.diamondCount != var2.diamondCount
                                                                                                                        ? false
                                                                                                                        : (
                                                                                                                           this.diamondMaxHeight
                                                                                                                                 != var2.diamondMaxHeight
                                                                                                                              ? false
                                                                                                                              : (
                                                                                                                                 this.diamondMinHeight
                                                                                                                                       != var2.diamondMinHeight
                                                                                                                                    ? false
                                                                                                                                    : (
                                                                                                                                       this.diamondSize
                                                                                                                                             != var2.diamondSize
                                                                                                                                          ? false
                                                                                                                                          : (
                                                                                                                                             this.dioriteCount
                                                                                                                                                   != var2.dioriteCount
                                                                                                                                                ? false
                                                                                                                                                : (
                                                                                                                                                   this.dioriteMaxHeight
                                                                                                                                                         != var2.dioriteMaxHeight
                                                                                                                                                      ? false
                                                                                                                                                      : (
                                                                                                                                                         this.dioriteMinHeight
                                                                                                                                                               != var2.dioriteMinHeight
                                                                                                                                                            ? false
                                                                                                                                                            : (
                                                                                                                                                               this.dioriteSize
                                                                                                                                                                     != var2.dioriteSize
                                                                                                                                                                  ? false
                                                                                                                                                                  : (
                                                                                                                                                                     this.dirtCount
                                                                                                                                                                           != var2.dirtCount
                                                                                                                                                                        ? false
                                                                                                                                                                        : (
                                                                                                                                                                           this.dirtMaxHeight
                                                                                                                                                                                 != var2.dirtMaxHeight
                                                                                                                                                                              ? false
                                                                                                                                                                              : (
                                                                                                                                                                                 this.dirtMinHeight
                                                                                                                                                                                       != var2.dirtMinHeight
                                                                                                                                                                                    ? false
                                                                                                                                                                                    : (
                                                                                                                                                                                       this.dirtSize
                                                                                                                                                                                             != var2.dirtSize
                                                                                                                                                                                          ? false
                                                                                                                                                                                          : (
                                                                                                                                                                                             this.dungeonChance
                                                                                                                                                                                                   != var2.dungeonChance
                                                                                                                                                                                                ? false
                                                                                                                                                                                                : (
                                                                                                                                                                                                   this.fixedBiome
                                                                                                                                                                                                         != var2.fixedBiome
                                                                                                                                                                                                      ? false
                                                                                                                                                                                                      : (
                                                                                                                                                                                                         this.goldCount
                                                                                                                                                                                                               != var2.goldCount
                                                                                                                                                                                                            ? false
                                                                                                                                                                                                            : (
                                                                                                                                                                                                               this.goldMaxHeight
                                                                                                                                                                                                                     != var2.goldMaxHeight
                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                     this.goldMinHeight
                                                                                                                                                                                                                           != var2.goldMinHeight
                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                           this.goldSize
                                                                                                                                                                                                                                 != var2.goldSize
                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                 this.graniteCount
                                                                                                                                                                                                                                       != var2.graniteCount
                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                       this.graniteMaxHeight
                                                                                                                                                                                                                                             != var2.graniteMaxHeight
                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                             this.graniteMinHeight
                                                                                                                                                                                                                                                   != var2.graniteMinHeight
                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                   this.graniteSize
                                                                                                                                                                                                                                                         != var2.graniteSize
                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                         this.gravelCount
                                                                                                                                                                                                                                                               != var2.gravelCount
                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                               this.gravelMaxHeight
                                                                                                                                                                                                                                                                     != var2.gravelMaxHeight
                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                     this.gravelMinHeight
                                                                                                                                                                                                                                                                           != var2.gravelMinHeight
                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                           this.gravelSize
                                                                                                                                                                                                                                                                                 != var2.gravelSize
                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                 Float.compare(
                                                                                                                                                                                                                                                                                          var2.heightScale,
                                                                                                                                                                                                                                                                                          this.heightScale
                                                                                                                                                                                                                                                                                       )
                                                                                                                                                                                                                                                                                       != 0
                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                       this.ironCount
                                                                                                                                                                                                                                                                                             != var2.ironCount
                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                             this.ironMaxHeight
                                                                                                                                                                                                                                                                                                   != var2.ironMaxHeight
                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                   this.ironMinHeight
                                                                                                                                                                                                                                                                                                         != var2.ironMinHeight
                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                         this.ironSize
                                                                                                                                                                                                                                                                                                               != var2.ironSize
                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                               this.lapisCenterHeight
                                                                                                                                                                                                                                                                                                                     != var2.lapisCenterHeight
                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                     this.lapisCount
                                                                                                                                                                                                                                                                                                                           != var2.lapisCount
                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                           this.lapisSize
                                                                                                                                                                                                                                                                                                                                 != var2.lapisSize
                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                 this.lapisSpread
                                                                                                                                                                                                                                                                                                                                       != var2.lapisSpread
                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                       this.lavaLakeChance
                                                                                                                                                                                                                                                                                                                                             != var2.lavaLakeChance
                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                             Float.compare(
                                                                                                                                                                                                                                                                                                                                                      var2.lowerLimitScale,
                                                                                                                                                                                                                                                                                                                                                      this.lowerLimitScale
                                                                                                                                                                                                                                                                                                                                                   )
                                                                                                                                                                                                                                                                                                                                                   != 0
                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                   Float.compare(
                                                                                                                                                                                                                                                                                                                                                            var2.mainNoiseScaleX,
                                                                                                                                                                                                                                                                                                                                                            this.mainNoiseScaleX
                                                                                                                                                                                                                                                                                                                                                         )
                                                                                                                                                                                                                                                                                                                                                         != 0
                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                         Float.compare(
                                                                                                                                                                                                                                                                                                                                                                  var2.mainNoiseScaleY,
                                                                                                                                                                                                                                                                                                                                                                  this.mainNoiseScaleY
                                                                                                                                                                                                                                                                                                                                                               )
                                                                                                                                                                                                                                                                                                                                                               != 0
                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                               Float.compare(
                                                                                                                                                                                                                                                                                                                                                                        var2.mainNoiseScaleZ,
                                                                                                                                                                                                                                                                                                                                                                        this.mainNoiseScaleZ
                                                                                                                                                                                                                                                                                                                                                                     )
                                                                                                                                                                                                                                                                                                                                                                     != 0
                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                     this.redstoneCount
                                                                                                                                                                                                                                                                                                                                                                           != var2.redstoneCount
                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                           this.redstoneMaxHeight
                                                                                                                                                                                                                                                                                                                                                                                 != var2.redstoneMaxHeight
                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                 this.redstoneMinHeight
                                                                                                                                                                                                                                                                                                                                                                                       != var2.redstoneMinHeight
                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                                                                       this.redstoneSize
                                                                                                                                                                                                                                                                                                                                                                                             != var2.redstoneSize
                                                                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                                                                             this.riverSize
                                                                                                                                                                                                                                                                                                                                                                                                   != var2.riverSize
                                                                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                                                                   this.seaLevel
                                                                                                                                                                                                                                                                                                                                                                                                         != var2.seaLevel
                                                                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                                                                         Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                  var2.stretchY,
                                                                                                                                                                                                                                                                                                                                                                                                                  this.stretchY
                                                                                                                                                                                                                                                                                                                                                                                                               )
                                                                                                                                                                                                                                                                                                                                                                                                               != 0
                                                                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                                                                               Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                        var2.upperLimitScale,
                                                                                                                                                                                                                                                                                                                                                                                                                        this.upperLimitScale
                                                                                                                                                                                                                                                                                                                                                                                                                     )
                                                                                                                                                                                                                                                                                                                                                                                                                     != 0
                                                                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                                                                     this.useCaves
                                                                                                                                                                                                                                                                                                                                                                                                                           != var2.useCaves
                                                                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                                                                           this.useDungeons
                                                                                                                                                                                                                                                                                                                                                                                                                                 != var2.useDungeons
                                                                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                                                                 this.useLavaLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                       != var2.useLavaLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                    : (
                                                                                                                                                                                                                                                                                                                                                                                                                                       this.useLavaOceans
                                                                                                                                                                                                                                                                                                                                                                                                                                             != var2.useLavaOceans
                                                                                                                                                                                                                                                                                                                                                                                                                                          ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                          : (
                                                                                                                                                                                                                                                                                                                                                                                                                                             this.useMineShafts
                                                                                                                                                                                                                                                                                                                                                                                                                                                   != var2.useMineShafts
                                                                                                                                                                                                                                                                                                                                                                                                                                                ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.useRavines
                                                                                                                                                                                                                                                                                                                                                                                                                                                         != var2.useRavines
                                                                                                                                                                                                                                                                                                                                                                                                                                                      ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                      : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.useStrongholds
                                                                                                                                                                                                                                                                                                                                                                                                                                                               != var2.useStrongholds
                                                                                                                                                                                                                                                                                                                                                                                                                                                            ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                            : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.useTemples
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     != var2.useTemples
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.useMonuments
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           != var2.useMonuments
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.useVillages
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 != var2.useVillages
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.useWaterLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       != var2.useWaterLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    : this.waterLakeChance
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       == var2.waterLakeChance
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                                                              )
                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                )
                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                              )
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                  )
                                                                                                                                                                                                            )
                                                                                                                                                                                                      )
                                                                                                                                                                                                )
                                                                                                                                                                                          )
                                                                                                                                                                                    )
                                                                                                                                                                              )
                                                                                                                                                                        )
                                                                                                                                                                  )
                                                                                                                                                            )
                                                                                                                                                      )
                                                                                                                                                )
                                                                                                                                          )
                                                                                                                                    )
                                                                                                                              )
                                                                                                                        )
                                                                                                                  )
                                                                                                            )
                                                                                                      )
                                                                                                )
                                                                                          )
                                                                                    )
                                                                              )
                                                                        )
                                                                  )
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = this.coordinateScale != 0.0F ? Float.floatToIntBits(this.coordinateScale) : 0;
      var1 = 31 * var1 + (this.heightScale != 0.0F ? Float.floatToIntBits(this.heightScale) : 0);
      var1 = 31 * var1 + (this.upperLimitScale != 0.0F ? Float.floatToIntBits(this.upperLimitScale) : 0);
      var1 = 31 * var1 + (this.lowerLimitScale != 0.0F ? Float.floatToIntBits(this.lowerLimitScale) : 0);
      var1 = 31 * var1 + (this.depthNoiseScaleX != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleX) : 0);
      var1 = 31 * var1 + (this.depthNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleZ) : 0);
      var1 = 31 * var1 + (this.depthNoiseScaleExponent != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleExponent) : 0);
      var1 = 31 * var1 + (this.mainNoiseScaleX != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleX) : 0);
      var1 = 31 * var1 + (this.mainNoiseScaleY != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleY) : 0);
      var1 = 31 * var1 + (this.mainNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleZ) : 0);
      var1 = 31 * var1 + (this.baseSize != 0.0F ? Float.floatToIntBits(this.baseSize) : 0);
      var1 = 31 * var1 + (this.stretchY != 0.0F ? Float.floatToIntBits(this.stretchY) : 0);
      var1 = 31 * var1 + (this.biomeDepthWeight != 0.0F ? Float.floatToIntBits(this.biomeDepthWeight) : 0);
      var1 = 31 * var1 + (this.biomeDepthOffset != 0.0F ? Float.floatToIntBits(this.biomeDepthOffset) : 0);
      var1 = 31 * var1 + (this.biomeScaleWeight != 0.0F ? Float.floatToIntBits(this.biomeScaleWeight) : 0);
      var1 = 31 * var1 + (this.biomeScaleOffset != 0.0F ? Float.floatToIntBits(this.biomeScaleOffset) : 0);
      var1 = 31 * var1 + this.seaLevel;
      var1 = 31 * var1 + (this.useCaves ? 1 : 0);
      var1 = 31 * var1 + (this.useDungeons ? 1 : 0);
      var1 = 31 * var1 + this.dungeonChance;
      var1 = 31 * var1 + (this.useStrongholds ? 1 : 0);
      var1 = 31 * var1 + (this.useVillages ? 1 : 0);
      var1 = 31 * var1 + (this.useMineShafts ? 1 : 0);
      var1 = 31 * var1 + (this.useTemples ? 1 : 0);
      var1 = 31 * var1 + (this.useMonuments ? 1 : 0);
      var1 = 31 * var1 + (this.useRavines ? 1 : 0);
      var1 = 31 * var1 + (this.useWaterLakes ? 1 : 0);
      var1 = 31 * var1 + this.waterLakeChance;
      var1 = 31 * var1 + (this.useLavaLakes ? 1 : 0);
      var1 = 31 * var1 + this.lavaLakeChance;
      var1 = 31 * var1 + (this.useLavaOceans ? 1 : 0);
      var1 = 31 * var1 + this.fixedBiome;
      var1 = 31 * var1 + this.biomeSize;
      var1 = 31 * var1 + this.riverSize;
      var1 = 31 * var1 + this.dirtSize;
      var1 = 31 * var1 + this.dirtCount;
      var1 = 31 * var1 + this.dirtMinHeight;
      var1 = 31 * var1 + this.dirtMaxHeight;
      var1 = 31 * var1 + this.gravelSize;
      var1 = 31 * var1 + this.gravelCount;
      var1 = 31 * var1 + this.gravelMinHeight;
      var1 = 31 * var1 + this.gravelMaxHeight;
      var1 = 31 * var1 + this.graniteSize;
      var1 = 31 * var1 + this.graniteCount;
      var1 = 31 * var1 + this.graniteMinHeight;
      var1 = 31 * var1 + this.graniteMaxHeight;
      var1 = 31 * var1 + this.dioriteSize;
      var1 = 31 * var1 + this.dioriteCount;
      var1 = 31 * var1 + this.dioriteMinHeight;
      var1 = 31 * var1 + this.dioriteMaxHeight;
      var1 = 31 * var1 + this.andesiteSize;
      var1 = 31 * var1 + this.andesiteCount;
      var1 = 31 * var1 + this.andesiteMinHeight;
      var1 = 31 * var1 + this.andesiteMaxHeight;
      var1 = 31 * var1 + this.coalSize;
      var1 = 31 * var1 + this.coalCount;
      var1 = 31 * var1 + this.coalMinHeight;
      var1 = 31 * var1 + this.coalMaxHeight;
      var1 = 31 * var1 + this.ironSize;
      var1 = 31 * var1 + this.ironCount;
      var1 = 31 * var1 + this.ironMinHeight;
      var1 = 31 * var1 + this.ironMaxHeight;
      var1 = 31 * var1 + this.goldSize;
      var1 = 31 * var1 + this.goldCount;
      var1 = 31 * var1 + this.goldMinHeight;
      var1 = 31 * var1 + this.goldMaxHeight;
      var1 = 31 * var1 + this.redstoneSize;
      var1 = 31 * var1 + this.redstoneCount;
      var1 = 31 * var1 + this.redstoneMinHeight;
      var1 = 31 * var1 + this.redstoneMaxHeight;
      var1 = 31 * var1 + this.diamondSize;
      var1 = 31 * var1 + this.diamondCount;
      var1 = 31 * var1 + this.diamondMinHeight;
      var1 = 31 * var1 + this.diamondMaxHeight;
      var1 = 31 * var1 + this.lapisSize;
      var1 = 31 * var1 + this.lapisCount;
      var1 = 31 * var1 + this.lapisCenterHeight;
      return 31 * var1 + this.lapisSpread;
   }
}
