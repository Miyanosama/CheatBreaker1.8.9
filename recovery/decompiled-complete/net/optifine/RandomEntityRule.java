package net.optifine;

import java.util.Properties;
import net.minecraft.block.BlockDirt;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.config.Weather;
import net.optifine.reflect.Reflector;
import net.optifine.util.ArrayUtils;
import net.optifine.util.MathUtils;

public class RandomEntityRule {
   public RangeListInt dayTimes;
   public int[] field_0017;
   public RangeListInt healthRange;
   public boolean healthPercent;
   public BiomeGenBase[] biomes;
   public NbtTagValue nbtName;
   public RangeListInt moonPhases;
   public int[] field_0012;
   public Boolean baby;
   public VillagerProfession[] professions;
   public ResourceLocation baseResLoc;
   public ResourceLocation[] resourceLocations;
   public RangeListInt heights;
   public int field_0006;
   public BlockDirt field_0013;
   public String pathProps = null;
   public int field_0000;
   public Weather[] weatherList;
   public int[] field_0010;
   public EnumDyeColor[] collarColors;

   public boolean isValid(String var1) {
      if (this.field_0012 == null || this.field_0012.length == 0) {
         Config.warn("Invalid skins for rule: " + this.field_0000);
         return false;
      } else if (this.resourceLocations != null) {
         return true;
      } else {
         this.resourceLocations = new ResourceLocation[this.field_0012.length];
         boolean var2 = this.pathProps.startsWith("mcpatcher/mob/");
         ResourceLocation var3 = RandomEntities.getLocationRandom(this.baseResLoc, var2);
         if (var3 == null) {
            Config.warn("Invalid path: " + this.baseResLoc.getResourcePath());
            return false;
         } else {
            for (int var4 = 0; var4 < this.resourceLocations.length; var4++) {
               int var5 = this.field_0012[var4];
               if (var5 <= 1) {
                  this.resourceLocations[var4] = this.baseResLoc;
               } else {
                  ResourceLocation var6 = RandomEntities.getLocationIndexed(var3, var5);
                  if (var6 == null) {
                     Config.warn("Invalid path: " + this.baseResLoc.getResourcePath());
                     return false;
                  }

                  if (!Config.hasResource(var6)) {
                     Config.warn("Texture not found: " + var6.getResourcePath());
                     return false;
                  }

                  this.resourceLocations[var4] = var6;
               }
            }

            if (this.field_0017 != null) {
               if (this.field_0017.length > this.resourceLocations.length) {
                  Config.warn("More weights defined than skins, trimming weights: " + var1);
                  int[] var7 = new int[this.resourceLocations.length];
                  System.arraycopy(this.field_0017, 0, var7, 0, var7.length);
                  this.field_0017 = var7;
               }

               if (this.field_0017.length < this.resourceLocations.length) {
                  Config.warn("Less weights defined than skins, expanding weights: " + var1);
                  int[] var8 = new int[this.resourceLocations.length];
                  System.arraycopy(this.field_0017, 0, var8, 0, this.field_0017.length);
                  int var10 = MathUtils.getAverage(this.field_0017);

                  for (int var12 = this.field_0017.length; var12 < var8.length; var12++) {
                     var8[var12] = var10;
                  }

                  this.field_0017 = var8;
               }

               this.field_0010 = new int[this.field_0017.length];
               int var9 = 0;

               for (int var11 = 0; var11 < this.field_0017.length; var11++) {
                  if (this.field_0017[var11] < 0) {
                     Config.warn("Invalid weight: " + this.field_0017[var11]);
                     return false;
                  }

                  var9 += this.field_0017[var11];
                  this.field_0010[var11] = var9;
               }

               this.field_0006 = var9;
               if (this.field_0006 <= 0) {
                  Config.warn("Invalid sum of all weights: " + var9);
                  this.field_0006 = 1;
               }
            }

            if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
               Config.warn("Invalid professions or careers: " + var1);
               return false;
            } else if (this.collarColors == ConnectedParser.DYE_COLORS_INVALID) {
               Config.warn("Invalid collar colors: " + var1);
               return false;
            } else {
               return true;
            }
         }
      }
   }

   public boolean matches(IRandomEntity var1) {
      if (this.biomes != null && !Matches.biome(var1.getSpawnBiome(), this.biomes)) {
         return false;
      } else {
         if (this.heights != null) {
            BlockPos var2 = var1.getSpawnPosition();
            if (var2 != null && !this.heights.isInRange(var2.getY())) {
               return false;
            }
         }

         if (this.healthRange != null) {
            int var10 = var1.getHealth();
            if (this.healthPercent) {
               int var3 = var1.getMaxHealth();
               if (var3 > 0) {
                  var10 = (int)((double)(var10 * 100) / var3);
               }
            }

            if (!this.healthRange.isInRange(var10)) {
               return false;
            }
         }

         if (this.nbtName != null) {
            String var11 = var1.getName();
            if (!this.nbtName.matchesValue(var11)) {
               return false;
            }
         }

         if (this.professions != null && var1 instanceof RandomEntity) {
            RandomEntity var12 = (RandomEntity)var1;
            Entity var18 = var12.getEntity();
            if (var18 instanceof EntityVillager) {
               EntityVillager var4 = (EntityVillager)var18;
               int var5 = var4.getProfession();
               int var6 = Reflector.getFieldValueInt(var4, Reflector.EntityVillager_careerId, -1);
               if (var5 < 0 || var6 < 0) {
                  return false;
               }

               boolean var7 = false;

               for (int var8 = 0; var8 < this.professions.length; var8++) {
                  VillagerProfession var9 = this.professions[var8];
                  if (var9.matches(var5, var6)) {
                     var7 = true;
                     break;
                  }
               }

               if (!var7) {
                  return false;
               }
            }
         }

         if (this.collarColors != null && var1 instanceof RandomEntity) {
            RandomEntity var13 = (RandomEntity)var1;
            Entity var19 = var13.getEntity();
            if (var19 instanceof EntityWolf) {
               EntityWolf var24 = (EntityWolf)var19;
               if (!var24.isTamed()) {
                  return false;
               }

               EnumDyeColor var26 = var24.getCollarColor();
               if (!Config.equalsOne(var26, this.collarColors)) {
                  return false;
               }
            }
         }

         if (this.baby != null && var1 instanceof RandomEntity) {
            RandomEntity var14 = (RandomEntity)var1;
            Entity var20 = var14.getEntity();
            if (var20 instanceof EntityLiving) {
               EntityLiving var25 = (EntityLiving)var20;
               if (var25.o_() != this.baby) {
                  return false;
               }
            }
         }

         if (this.moonPhases != null) {
            WorldClient var15 = Config.getMinecraft().theWorld;
            if (var15 != null) {
               int var21 = var15.getMoonPhase();
               if (!this.moonPhases.isInRange(var21)) {
                  return false;
               }
            }
         }

         if (this.dayTimes != null) {
            WorldClient var16 = Config.getMinecraft().theWorld;
            if (var16 != null) {
               int var22 = (int)var16.P().getWorldTime();
               if (!this.dayTimes.isInRange(var22)) {
                  return false;
               }
            }
         }

         if (this.weatherList != null) {
            WorldClient var17 = Config.getMinecraft().theWorld;
            if (var17 != null) {
               Weather var23 = Weather.getWeather(var17, 0.0F);
               if (!ArrayUtils.contains(this.weatherList, var23)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public ResourceLocation getTextureLocation(ResourceLocation var1, int var2) {
      if (this.resourceLocations != null && this.resourceLocations.length != 0) {
         int var3 = 0;
         if (this.field_0017 == null) {
            var3 = var2 % this.resourceLocations.length;
         } else {
            int var4 = var2 % this.field_0006;

            for (int var5 = 0; var5 < this.field_0010.length; var5++) {
               if (this.field_0010[var5] > var4) {
                  var3 = var5;
                  break;
               }
            }
         }

         return this.resourceLocations[var3];
      } else {
         return var1;
      }
   }

   public RangeListInt parseMinMaxHeight(Properties var1, int var2) {
      String var3 = var1.getProperty("minHeight." + var2);
      String var4 = var1.getProperty("maxHeight." + var2);
      if (var3 == null && var4 == null) {
         return null;
      } else {
         int var5 = 0;
         if (var3 != null) {
            var5 = Config.parseInt(var3, -1);
            if (var5 < 0) {
               Config.warn("Invalid minHeight: " + var3);
               return null;
            }
         }

         int var6 = 256;
         if (var4 != null) {
            var6 = Config.parseInt(var4, -1);
            if (var6 < 0) {
               Config.warn("Invalid maxHeight: " + var4);
               return null;
            }
         }

         if (var6 < 0) {
            Config.warn("Invalid minHeight, maxHeight: " + var3 + ", " + var4);
            return null;
         } else {
            RangeListInt var7 = new RangeListInt();
            var7.addRange(new RangeInt(var5, var6));
            return var7;
         }
      }
   }

   public RandomEntityRule(Properties var1, String var2, ResourceLocation var3, int var4, String var5, ConnectedParser var6) {
      this.baseResLoc = null;
      this.field_0012 = null;
      this.resourceLocations = null;
      this.field_0017 = null;
      this.biomes = null;
      this.heights = null;
      this.healthRange = null;
      this.healthPercent = false;
      this.nbtName = null;
      this.field_0010 = null;
      this.field_0006 = 1;
      this.professions = null;
      this.collarColors = null;
      this.baby = null;
      this.moonPhases = null;
      this.dayTimes = null;
      this.weatherList = null;
      this.pathProps = var2;
      this.baseResLoc = var3;
      this.field_0000 = var4;
      this.field_0012 = var6.parseIntList(var5);
      this.field_0017 = var6.parseIntList(var1.getProperty("weights." + var4));
      this.biomes = var6.parseBiomes(var1.getProperty("biomes." + var4));
      this.heights = var6.parseRangeListInt(var1.getProperty("heights." + var4));
      if (this.heights == null) {
         this.heights = this.parseMinMaxHeight(var1, var4);
      }

      String var7 = var1.getProperty("health." + var4);
      if (var7 != null) {
         this.healthPercent = var7.contains("%");
         var7 = var7.replace("%", "");
         this.healthRange = var6.parseRangeListInt(var7);
      }

      this.nbtName = var6.parseNbtTagValue("name", var1.getProperty("name." + var4));
      this.professions = var6.parseProfessions(var1.getProperty("professions." + var4));
      this.collarColors = var6.parseDyeColors(var1.getProperty("collarColors." + var4), "collar color", ConnectedParser.DYE_COLORS_INVALID);
      this.baby = var6.parseBooleanObject(var1.getProperty("baby." + var4));
      this.moonPhases = var6.parseRangeListInt(var1.getProperty("moonPhase." + var4));
      this.dayTimes = var6.parseRangeListInt(var1.getProperty("dayTime." + var4));
      this.weatherList = var6.parseWeather(var1.getProperty("weather." + var4), "weather." + var4, (Weather[])null);
   }
}
