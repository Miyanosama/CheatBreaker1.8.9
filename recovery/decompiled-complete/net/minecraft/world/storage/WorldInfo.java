package net.minecraft.world.storage;

import junit.swingui.TestSuitePanel$TestTreeCellRenderer;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;

public class WorldInfo {
   public int spawnZ;
   public double borderCenterX;
   public long randomSeed;
   public int spawnY;
   public int dimension;
   public boolean thundering;
   public EnumDifficulty difficulty;
   public boolean initialized;
   public GameRules theGameRules;
   public int borderWarningDistance;
   public double borderSafeZone;
   public int spawnX;
   public long borderSizeLerpTime;
   public long worldTime;
   public long sizeOnDisk;
   public double borderDamagePerBlock;
   public long lastTimePlayed;
   public int saveVersion;
   public boolean allowCommands;
   public int borderWarningTime;
   public double borderSizeLerpTarget;
   public boolean raining;
   public long totalTime;
   public boolean hardcore;
   public double borderSize;
   public double borderCenterZ;
   public TestSuitePanel$TestTreeCellRenderer field_0018;
   public int rainTime;
   public WorldType terrainType = WorldType.DEFAULT;
   public WorldSettings$GameType theGameType;
   public int thunderTime;
   public boolean difficultyLocked;
   public NBTTagCompound playerTag;
   public String generatorOptions = "";
   public static EnumDifficulty DEFAULT_DIFFICULTY = EnumDifficulty.NORMAL;
   public String levelName;
   public int cleanWeatherTime;
   public boolean mapFeaturesEnabled;

   public double getBorderCenterX() {
      return this.borderCenterX;
   }

   public void setBorderLerpTime(long var1) {
      this.borderSizeLerpTime = var1;
   }

   public long getBorderLerpTime() {
      return this.borderSizeLerpTime;
   }

   public void setAllowCommands(boolean var1) {
      this.allowCommands = var1;
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public void setSpawn(BlockPos var1) {
      this.spawnX = var1.getX();
      this.spawnY = var1.getY();
      this.spawnZ = var1.getZ();
   }

   public void setWorldTotalTime(long var1) {
      this.totalTime = var1;
   }

   public void setRaining(boolean var1) {
      this.raining = var1;
   }

   public boolean isRaining() {
      return this.raining;
   }

   public int getBorderWarningDistance() {
      return this.borderWarningDistance;
   }

   public void populateFromWorldSettings(WorldSettings var1) {
      this.randomSeed = var1.getSeed();
      this.theGameType = var1.getGameType();
      this.mapFeaturesEnabled = var1.isMapFeaturesEnabled();
      this.hardcore = var1.getHardcoreEnabled();
      this.terrainType = var1.getTerrainType();
      this.generatorOptions = var1.getWorldName();
      this.allowCommands = var1.areCommandsAllowed();
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public String getGeneratorOptions() {
      return this.generatorOptions;
   }

   public NBTTagCompound getPlayerNBTTagCompound() {
      return this.playerTag;
   }

   public int getBorderWarningTime() {
      return this.borderWarningTime;
   }

   public void setWorldName(String var1) {
      this.levelName = var1;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setThundering(boolean var1) {
      this.thundering = var1;
   }

   public WorldInfo() {
      this.borderCenterX = 0.0;
      this.borderCenterZ = 0.0;
      this.borderSize = 6.0E7;
      this.borderSizeLerpTime = 838926404L & -7414681495130047984L;
      this.borderSizeLerpTarget = 0.0;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.theGameRules = new GameRules();
   }

   public WorldSettings$GameType getGameType() {
      return this.theGameType;
   }

   public void setHardcore(boolean var1) {
      this.hardcore = var1;
   }

   public void setBorderWarningDistance(int var1) {
      this.borderWarningDistance = var1;
   }

   public String getWorldName() {
      return this.levelName;
   }

   public void setWorldTime(long var1) {
      this.worldTime = var1;
   }

   public void setSaveVersion(int var1) {
      this.saveVersion = var1;
   }

   public void setThunderTime(int var1) {
      this.thunderTime = var1;
   }

   public double getBorderCenterZ() {
      return this.borderCenterZ;
   }

   public void setServerInitialized(boolean var1) {
      this.initialized = var1;
   }

   public WorldInfo(WorldInfo var1) {
      this.borderCenterX = 0.0;
      this.borderCenterZ = 0.0;
      this.borderSize = 6.0E7;
      this.borderSizeLerpTime = 33865796L & 406983712L;
      this.borderSizeLerpTarget = 0.0;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.theGameRules = new GameRules();
      this.randomSeed = var1.randomSeed;
      this.terrainType = var1.terrainType;
      this.generatorOptions = var1.generatorOptions;
      this.theGameType = var1.theGameType;
      this.mapFeaturesEnabled = var1.mapFeaturesEnabled;
      this.spawnX = var1.spawnX;
      this.spawnY = var1.spawnY;
      this.spawnZ = var1.spawnZ;
      this.totalTime = var1.totalTime;
      this.worldTime = var1.worldTime;
      this.lastTimePlayed = var1.lastTimePlayed;
      this.sizeOnDisk = var1.sizeOnDisk;
      this.playerTag = var1.playerTag;
      this.dimension = var1.dimension;
      this.levelName = var1.levelName;
      this.saveVersion = var1.saveVersion;
      this.rainTime = var1.rainTime;
      this.raining = var1.raining;
      this.thunderTime = var1.thunderTime;
      this.thundering = var1.thundering;
      this.hardcore = var1.hardcore;
      this.allowCommands = var1.allowCommands;
      this.initialized = var1.initialized;
      this.theGameRules = var1.theGameRules;
      this.difficulty = var1.difficulty;
      this.difficultyLocked = var1.difficultyLocked;
      this.borderCenterX = var1.borderCenterX;
      this.borderCenterZ = var1.borderCenterZ;
      this.borderSize = var1.borderSize;
      this.borderSizeLerpTime = var1.borderSizeLerpTime;
      this.borderSizeLerpTarget = var1.borderSizeLerpTarget;
      this.borderSafeZone = var1.borderSafeZone;
      this.borderDamagePerBlock = var1.borderDamagePerBlock;
      this.borderWarningTime = var1.borderWarningTime;
      this.borderWarningDistance = var1.borderWarningDistance;
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   public void updateTagCompound(NBTTagCompound var1, NBTTagCompound var2) {
      var1.setLong("RandomSeed", this.randomSeed);
      var1.setString("generatorName", this.terrainType.getWorldTypeName());
      var1.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
      var1.setString("generatorOptions", this.generatorOptions);
      var1.setInteger("GameType", this.theGameType.getID());
      var1.setBoolean("MapFeatures", this.mapFeaturesEnabled);
      var1.setInteger("SpawnX", this.spawnX);
      var1.setInteger("SpawnY", this.spawnY);
      var1.setInteger("SpawnZ", this.spawnZ);
      var1.setLong("Time", this.totalTime);
      var1.setLong("DayTime", this.worldTime);
      var1.setLong("SizeOnDisk", this.sizeOnDisk);
      var1.setLong("LastPlayed", MinecraftServer.getCurrentTimeMillis());
      var1.setString("LevelName", this.levelName);
      var1.setInteger("version", this.saveVersion);
      var1.setInteger("clearWeatherTime", this.cleanWeatherTime);
      var1.setInteger("rainTime", this.rainTime);
      var1.setBoolean("raining", this.raining);
      var1.setInteger("thunderTime", this.thunderTime);
      var1.setBoolean("thundering", this.thundering);
      var1.setBoolean("hardcore", this.hardcore);
      var1.setBoolean("allowCommands", this.allowCommands);
      var1.setBoolean("initialized", this.initialized);
      var1.setDouble("BorderCenterX", this.borderCenterX);
      var1.setDouble("BorderCenterZ", this.borderCenterZ);
      var1.setDouble("BorderSize", this.borderSize);
      var1.setLong("BorderSizeLerpTime", this.borderSizeLerpTime);
      var1.setDouble("BorderSafeZone", this.borderSafeZone);
      var1.setDouble("BorderDamagePerBlock", this.borderDamagePerBlock);
      var1.setDouble("BorderSizeLerpTarget", this.borderSizeLerpTarget);
      var1.setDouble("BorderWarningBlocks", this.borderWarningDistance);
      var1.setDouble("BorderWarningTime", this.borderWarningTime);
      if (this.difficulty != null) {
         var1.setByte("Difficulty", (byte)this.difficulty.getDifficultyId());
      }

      var1.setBoolean("DifficultyLocked", this.difficultyLocked);
      var1.setTag("GameRules", this.theGameRules.writeToNBT());
      if (var2 != null) {
         var1.setTag("Player", var2);
      }
   }

   public int getThunderTime() {
      return this.thunderTime;
   }

   public void getBorderCenterZ(double var1) {
      this.borderCenterZ = var1;
   }

   public int getSpawnZ() {
      return this.spawnZ;
   }

   public boolean areCommandsAllowed() {
      return this.allowCommands;
   }

   public double getBorderSize() {
      return this.borderSize;
   }

   public void setBorderDamagePerBlock(double var1) {
      this.borderDamagePerBlock = var1;
   }

   public void setBorderSize(double var1) {
      this.borderSize = var1;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public void setDifficulty(EnumDifficulty var1) {
      this.difficulty = var1;
   }

   public void setCleanWeatherTime(int var1) {
      this.cleanWeatherTime = var1;
   }

   public int getCleanWeatherTime() {
      return this.cleanWeatherTime;
   }

   public void setMapFeaturesEnabled(boolean var1) {
      this.mapFeaturesEnabled = var1;
   }

   public void addToCrashReport(CrashReportCategory var1) {
      var1.addCrashSectionCallable("Level seed", new WorldInfo$1(this));
      var1.addCrashSectionCallable("Level generator", new WorldInfo$2(this));
      var1.addCrashSectionCallable("Level generator options", new WorldInfo$3(this));
      var1.addCrashSectionCallable("Level spawn location", new WorldInfo$4(this));
      var1.addCrashSectionCallable("Level time", new WorldInfo$5(this));
      var1.addCrashSectionCallable("Level dimension", new WorldInfo$6(this));
      var1.addCrashSectionCallable("Level storage version", new WorldInfo$7(this));
      var1.addCrashSectionCallable("Level weather", new WorldInfo$8(this));
      var1.addCrashSectionCallable("Level game mode", new WorldInfo$9(this));
   }

   public int getSpawnX() {
      return this.spawnX;
   }

   public long getWorldTotalTime() {
      return this.totalTime;
   }

   public void setSpawnY(int var1) {
      this.spawnY = var1;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public WorldInfo(WorldSettings var1, String var2) {
      this.borderCenterX = 0.0;
      this.borderCenterZ = 0.0;
      this.borderSize = 6.0E7;
      this.borderSizeLerpTime = 6080645148224841768L & 235995143L;
      this.borderSizeLerpTarget = 0.0;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.theGameRules = new GameRules();
      this.populateFromWorldSettings(var1);
      this.levelName = var2;
      this.difficulty = DEFAULT_DIFFICULTY;
      this.initialized = false;
   }

   public void setSpawnZ(int var1) {
      this.spawnZ = var1;
   }

   public double getBorderLerpTarget() {
      return this.borderSizeLerpTarget;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.theGameType = var1;
   }

   public double getBorderSafeZone() {
      return this.borderSafeZone;
   }

   public WorldType getTerrainType() {
      return this.terrainType;
   }

   public int getSpawnY() {
      return this.spawnY;
   }

   public void setBorderSafeZone(double var1) {
      this.borderSafeZone = var1;
   }

   public long getSeed() {
      return this.randomSeed;
   }

   public void setBorderLerpTarget(double var1) {
      this.borderSizeLerpTarget = var1;
   }

   public void setDifficultyLocked(boolean var1) {
      this.difficultyLocked = var1;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   public void setTerrainType(WorldType var1) {
      this.terrainType = var1;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public int getRainTime() {
      return this.rainTime;
   }

   public GameRules getGameRulesInstance() {
      return this.theGameRules;
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.updateTagCompound(var1, this.playerTag);
      return var1;
   }

   public boolean isThundering() {
      return this.thundering;
   }

   public WorldInfo(NBTTagCompound var1) {
      this.borderCenterX = 0.0;
      this.borderCenterZ = 0.0;
      this.borderSize = 6.0E7;
      this.borderSizeLerpTime = -346033903401477888L & 2622466L;
      this.borderSizeLerpTarget = 0.0;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.theGameRules = new GameRules();
      this.randomSeed = var1.getLong("RandomSeed");
      if (var1.hasKey("generatorName", 8)) {
         String var2 = var1.getString("generatorName");
         this.terrainType = WorldType.parseWorldType(var2);
         if (this.terrainType == null) {
            this.terrainType = WorldType.DEFAULT;
         } else if (this.terrainType.method_29047()) {
            int var3 = 0;
            if (var1.hasKey("generatorVersion", 99)) {
               var3 = var1.getInteger("generatorVersion");
            }

            this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(var3);
         }

         if (var1.hasKey("generatorOptions", 8)) {
            this.generatorOptions = var1.getString("generatorOptions");
         }
      }

      this.theGameType = WorldSettings$GameType.getByID(var1.getInteger("GameType"));
      if (var1.hasKey("MapFeatures", 99)) {
         this.mapFeaturesEnabled = var1.getBoolean("MapFeatures");
      } else {
         this.mapFeaturesEnabled = true;
      }

      this.spawnX = var1.getInteger("SpawnX");
      this.spawnY = var1.getInteger("SpawnY");
      this.spawnZ = var1.getInteger("SpawnZ");
      this.totalTime = var1.getLong("Time");
      if (var1.hasKey("DayTime", 99)) {
         this.worldTime = var1.getLong("DayTime");
      } else {
         this.worldTime = this.totalTime;
      }

      this.lastTimePlayed = var1.getLong("LastPlayed");
      this.sizeOnDisk = var1.getLong("SizeOnDisk");
      this.levelName = var1.getString("LevelName");
      this.saveVersion = var1.getInteger("version");
      this.cleanWeatherTime = var1.getInteger("clearWeatherTime");
      this.rainTime = var1.getInteger("rainTime");
      this.raining = var1.getBoolean("raining");
      this.thunderTime = var1.getInteger("thunderTime");
      this.thundering = var1.getBoolean("thundering");
      this.hardcore = var1.getBoolean("hardcore");
      if (var1.hasKey("initialized", 99)) {
         this.initialized = var1.getBoolean("initialized");
      } else {
         this.initialized = true;
      }

      if (var1.hasKey("allowCommands", 99)) {
         this.allowCommands = var1.getBoolean("allowCommands");
      } else {
         this.allowCommands = this.theGameType == WorldSettings$GameType.CREATIVE;
      }

      if (var1.hasKey("Player", 10)) {
         this.playerTag = var1.getCompoundTag("Player");
         this.dimension = this.playerTag.getInteger("Dimension");
      }

      if (var1.hasKey("GameRules", 10)) {
         this.theGameRules.readFromNBT(var1.getCompoundTag("GameRules"));
      }

      if (var1.hasKey("Difficulty", 99)) {
         this.difficulty = EnumDifficulty.getDifficultyEnum(var1.getByte("Difficulty"));
      }

      if (var1.hasKey("DifficultyLocked", 1)) {
         this.difficultyLocked = var1.getBoolean("DifficultyLocked");
      }

      if (var1.hasKey("BorderCenterX", 99)) {
         this.borderCenterX = var1.getDouble("BorderCenterX");
      }

      if (var1.hasKey("BorderCenterZ", 99)) {
         this.borderCenterZ = var1.getDouble("BorderCenterZ");
      }

      if (var1.hasKey("BorderSize", 99)) {
         this.borderSize = var1.getDouble("BorderSize");
      }

      if (var1.hasKey("BorderSizeLerpTime", 99)) {
         this.borderSizeLerpTime = var1.getLong("BorderSizeLerpTime");
      }

      if (var1.hasKey("BorderSizeLerpTarget", 99)) {
         this.borderSizeLerpTarget = var1.getDouble("BorderSizeLerpTarget");
      }

      if (var1.hasKey("BorderSafeZone", 99)) {
         this.borderSafeZone = var1.getDouble("BorderSafeZone");
      }

      if (var1.hasKey("BorderDamagePerBlock", 99)) {
         this.borderDamagePerBlock = var1.getDouble("BorderDamagePerBlock");
      }

      if (var1.hasKey("BorderWarningBlocks", 99)) {
         this.borderWarningDistance = var1.getInteger("BorderWarningBlocks");
      }

      if (var1.hasKey("BorderWarningTime", 99)) {
         this.borderWarningTime = var1.getInteger("BorderWarningTime");
      }
   }

   public double getBorderDamagePerBlock() {
      return this.borderDamagePerBlock;
   }

   public void setRainTime(int var1) {
      this.rainTime = var1;
   }

   public void setBorderWarningTime(int var1) {
      this.borderWarningTime = var1;
   }

   public int getSaveVersion() {
      return this.saveVersion;
   }

   public NBTTagCompound cloneNBTCompound(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      this.updateTagCompound(var2, var1);
      return var2;
   }

   public void getBorderCenterX(double var1) {
      this.borderCenterX = var1;
   }

   public void setSpawnX(int var1) {
      this.spawnX = var1;
   }
}
