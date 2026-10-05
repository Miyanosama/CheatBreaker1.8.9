package net.minecraft.world;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.storage.WorldInfo;

public class WorldSettings {
   public boolean mapFeaturesEnabled;
   public String worldName = "";
   public boolean recoveredField1;
   public long seed;
   public WorldSettings.GameType theGameType;
   public boolean recoveredField2;
   public boolean hardcoreEnabled;
   public WorldType terrainType;

   public WorldSettings setWorldName(String var1) {
      this.worldName = var1;
      return this;
   }

   public long getSeed() {
      return this.seed;
   }

   public WorldSettings.GameType getGameType() {
      return this.theGameType;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public boolean areCommandsAllowed() {
      return this.recoveredField1;
   }

   public static WorldSettings.GameType getGameTypeById(int var0) {
      return WorldSettings.GameType.getByID(var0);
   }

   public WorldSettings enableBonusChest() {
      this.recoveredField2 = true;
      return this;
   }

   public boolean getHardcoreEnabled() {
      return this.hardcoreEnabled;
   }

   public boolean method_26031() {
      return this.recoveredField2;
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
      this.recoveredField1 = true;
      return this;
   }

   public WorldSettings(long var1, WorldSettings.GameType var3, boolean var4, boolean var5, WorldType var6) {
      this.seed = var1;
      this.theGameType = var3;
      this.mapFeaturesEnabled = var4;
      this.hardcoreEnabled = var5;
      this.terrainType = var6;
   }

   public static enum GameType {
      NOT_SET(-1, ""),
      SURVIVAL(0, "survival"),
      CREATIVE(1, "creative"),
      ADVENTURE(2, "adventure"),
      SPECTATOR(3, "spectator");
      public String name;
      // $VF: synthetic field
      public static WorldSettings.GameType[] $VALUES = new WorldSettings.GameType[]{NOT_SET, SURVIVAL, CREATIVE, ADVENTURE, WorldSettings.GameType.SPECTATOR};
      public int id;

      public boolean isCreative() {
         return this == CREATIVE;
      }

      public boolean isSurvivalOrAdventure() {
         return this == SURVIVAL || this == ADVENTURE;
      }

      public String getName() {
         return this.name;
      }

      public static WorldSettings.GameType getByID(int var0) {
         for (WorldSettings.GameType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return SURVIVAL;
      }

      public void configurePlayerCapabilities(PlayerCapabilities var1) {
         if (this == CREATIVE) {
            var1.allowFlying = true;
            var1.isCreativeMode = true;
            var1.disableDamage = true;
         } else if (this == SPECTATOR) {
            var1.allowFlying = true;
            var1.isCreativeMode = false;
            var1.disableDamage = true;
            var1.isFlying = true;
         } else {
            var1.allowFlying = false;
            var1.isCreativeMode = false;
            var1.disableDamage = false;
            var1.isFlying = false;
         }

         var1.allowEdit = !this.isAdventure();
      }

      public static WorldSettings.GameType getByName(String var0) {
         for (WorldSettings.GameType var4 : values()) {
            if (var4.name.equals(var0)) {
               return var4;
            }
         }

         return SURVIVAL;
      }

      GameType(int var3, String var4) {
         this.id = var3;
         this.name = var4;
      }

      public int getID() {
         return this.id;
      }

      public boolean isAdventure() {
         return this == ADVENTURE || this == SPECTATOR;
      }
   }
}
