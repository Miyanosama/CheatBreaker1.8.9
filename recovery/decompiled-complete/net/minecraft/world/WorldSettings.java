package net.minecraft.world;

import io.netty.channel.ChannelHandlerAdapter;
import net.minecraft.world.storage.WorldInfo;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser$NamedPatternConverter;
import org.apache.log4j.lf5.util.LogFileParser;

public class WorldSettings {
   public boolean mapFeaturesEnabled;
   public ChannelHandlerAdapter field_0009;
   public PatternParser$NamedPatternConverter field_0004;
   public String worldName = "";
   public boolean field_0001;
   public PatternLayout field_0002;
   public long seed;
   public WorldSettings$GameType theGameType;
   public boolean field_0003;
   public LogFileParser field_0011;
   public boolean hardcoreEnabled;
   public WorldType terrainType;

   public WorldSettings setWorldName(String var1) {
      this.worldName = var1;
      return this;
   }

   public long getSeed() {
      return this.seed;
   }

   public WorldSettings$GameType getGameType() {
      return this.theGameType;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public boolean areCommandsAllowed() {
      return this.field_0001;
   }

   public static WorldSettings$GameType getGameTypeById(int var0) {
      return WorldSettings$GameType.getByID(var0);
   }

   public WorldSettings enableBonusChest() {
      this.field_0003 = true;
      return this;
   }

   public boolean getHardcoreEnabled() {
      return this.hardcoreEnabled;
   }

   public boolean method_26031() {
      return this.field_0003;
   }

   public WorldSettings(WorldInfo var1) {
      this(var1.getSeed(), var1.getGameType(), var1.isMapFeaturesEnabled(), var1.isHardcoreModeEnabled(), var1.getTerrainType());
   }

   public String getWorldName() {
      return this.worldName;
   }

   public WorldType getTerrainType() {
      return this.terrainType;
   }

   public WorldSettings method_26032() {
      this.field_0001 = true;
      return this;
   }

   public WorldSettings(long var1, WorldSettings$GameType var3, boolean var4, boolean var5, WorldType var6) {
      this.seed = var1;
      this.theGameType = var3;
      this.mapFeaturesEnabled = var4;
      this.hardcoreEnabled = var5;
      this.terrainType = var6;
   }
}
