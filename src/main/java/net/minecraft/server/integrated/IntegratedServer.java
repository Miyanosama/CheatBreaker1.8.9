package net.minecraft.server.integrated;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.ClearWater;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends MinecraftServer {
   public Minecraft mc;
   public DifficultyInstance difficultyLast;
   public boolean isPublic;
   public BlockPos difficultyUpdatePos;
   public WorldSettings theWorldSettings;
   public long ticksSaveLast = 0L;
   public boolean isGamePaused;
   public static Logger logger = LogManager.getLogger();
   public World difficultyUpdateWorld = null;
   public ThreadLanServerPing lanServerPing;

   @Override
   public boolean isSnooperEnabled() {
      return Minecraft.getMinecraft().isSnooperEnabled();
   }

   @Override
   public boolean startServer() throws java.io.IOException {
      logger.info("Starting integrated minecraft server version 1.9");
      this.d(true);
      this.setCanSpawnAnimals(true);
      this.f(true);
      this.g(true);
      this.h(true);
      logger.info("Generating keypair");
      this.setKeyPair(CryptManager.generateKeyPair());
      if (Reflector.FMLCommonHandler_handleServerAboutToStart.exists()) {
         Object var1 = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (!Reflector.callBoolean(var1, Reflector.FMLCommonHandler_handleServerAboutToStart, this)) {
            return false;
         }
      }

      this.loadAllWorlds(this.U(), this.V(), this.theWorldSettings.getSeed(), this.theWorldSettings.getTerrainType(), this.theWorldSettings.getWorldName());
      this.l(this.getServerOwner() + " - " + this.worldServers[0].P().getWorldName());
      if (Reflector.FMLCommonHandler_handleServerStarting.exists()) {
         Object var2 = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (Reflector.FMLCommonHandler_handleServerStarting.getReturnType() == boolean.class) {
            return Reflector.callBoolean(var2, Reflector.FMLCommonHandler_handleServerStarting, this);
         }

         Reflector.callVoid(var2, Reflector.FMLCommonHandler_handleServerStarting, this);
      }

      return true;
   }

   @Override
   public boolean isCommandBlockEnabled() {
      return true;
   }

   public boolean getPublic() {
      return this.isPublic;
   }

   @Override
   public ServerCommandManager createNewCommandManager() {
      return new IntegratedServerCommandManager();
   }

   public IntegratedServer(Minecraft var1) {
      super(var1.getProxy(), new File(var1.mcDataDir, USER_CACHE_FILE.getName()));
      this.difficultyUpdatePos = null;
      this.difficultyLast = null;
      this.mc = var1;
      this.theWorldSettings = null;
   }

   @Override
   public void finalTick(CrashReport var1) {
      this.mc.crashed(var1);
   }

   @Override
   public int getOpPermissionLevel() {
      return 4;
   }

   @Override
   public CrashReport addServerInfoToCrashReport(CrashReport var1) {
      var1 = super.addServerInfoToCrashReport(var1);
      var1.getCategory().addCrashSectionCallable("Type", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return "Integrated Server (map_client.txt)";
         }
      });
      var1.getCategory()
         .addCrashSectionCallable(
            "Is Modded",
            new Callable<String>() {
               public String call() throws java.lang.Exception {
                  String var1 = ClientBrandRetriever.getClientModName();
                  if (!var1.equals("vanilla")) {
                     return "Definitely; Client brand changed to '" + var1 + "'";
                  } else {
                     var1 = IntegratedServer.this.getServerModName();
                     return !var1.equals("vanilla")
                        ? "Definitely; Server brand changed to '" + var1 + "'"
                        : (
                           Minecraft.class.getSigners() == null
                              ? "Very likely; Jar signature invalidated"
                              : "Probably not. Jar signature remains and both client + server brands are untouched."
                        );
                  }
               }
            }
         );
      return var1;
   }

   public void onTick(WorldServer var1) {
      if (!Config.isTimeDefault()) {
         this.fixWorldTime(var1);
      }

      if (!Config.isWeatherEnabled()) {
         this.fixWorldWeather(var1);
      }

      if (Config.waterOpacityChanged) {
         Config.waterOpacityChanged = false;
         ClearWater.updateWaterOpacity(Config.getGameSettings(), var1);
      }

      if (this.difficultyUpdateWorld == var1 && this.difficultyUpdatePos != null) {
         this.difficultyLast = var1.E(this.difficultyUpdatePos);
         this.difficultyUpdateWorld = null;
         this.difficultyUpdatePos = null;
      }
   }

   public DifficultyInstance getDifficultyAsync(World var1, BlockPos var2) {
      this.difficultyUpdateWorld = var1;
      this.difficultyUpdatePos = var2;
      return this.difficultyLast;
   }

   @Override
   public void setDifficultyForAllWorlds(EnumDifficulty var1) {
      super.setDifficultyForAllWorlds(var1);
      if (this.mc.theWorld != null) {
         this.mc.theWorld.P().setDifficulty(var1);
      }
   }

   @Override
   public File getDataDirectory() {
      return this.mc.mcDataDir;
   }

   @Override
   public void saveAllWorlds(boolean var1) {
      if (var1) {
         int var2 = this.getTickCounter();
         int var3 = this.mc.gameSettings.ofAutoSaveTicks;
         if (var2 < this.ticksSaveLast + var3) {
            return;
         }

         this.ticksSaveLast = var2;
      }

      super.saveAllWorlds(var1);
   }

   @Override
   public boolean shouldBroadcastConsoleToOps() {
      return true;
   }

   @Override
   public void stopServer() {
      super.stopServer();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }
   }

   public IntegratedServer(Minecraft var1, String var2, String var3, WorldSettings var4) {
      super(new File(var1.mcDataDir, "saves"), var1.getProxy(), new File(var1.mcDataDir, USER_CACHE_FILE.getName()));
      this.difficultyUpdatePos = null;
      this.difficultyLast = null;
      this.setServerOwner(var1.getSession().getUsername());
      this.method_06909(var2);
      this.method_06846(var3);
      this.method_06820(var1.method_20336());
      this.method_06847(var4.method_26031());
      this.method_06851(256);
      this.setConfigManager(new IntegratedPlayerList(this));
      this.mc = var1;
      this.theWorldSettings = this.isDemo() ? DemoWorldServer.demoWorldSettings : var4;
      ISaveHandler var5 = this.getActiveAnvilConverter().getSaveLoader(var2, false);
      WorldInfo var6 = var5.loadWorldInfo();
      if (var6 != null) {
         NBTTagCompound var7 = var6.getPlayerNBTTagCompound();
         if (var7 != null && var7.hasKey("Dimension")) {
            int var8 = var7.getInteger("Dimension");
            PacketThreadUtil.lastDimensionId = var8;
            this.mc.loadingScreen.setLoadingProgress(-1);
         }
      }
   }

   public void fixWorldWeather(WorldServer var1) {
      WorldInfo var2 = var1.P();
      if (var2.isRaining() || var2.isThundering()) {
         var2.setRainTime(0);
         var2.setRaining(false);
         var1.k(0.0F);
         var2.setThunderTime(0);
         var2.setThundering(false);
         var1.i(0.0F);
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(2, 0.0F));
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(7, 0.0F));
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(8, 0.0F));
      }
   }

   @Override
   public void tick() {
      this.onTick();
      boolean var1 = this.isGamePaused;
      this.isGamePaused = Minecraft.getMinecraft().getNetHandler() != null && Minecraft.getMinecraft().isGamePaused();
      if (!var1 && this.isGamePaused) {
         logger.info("Saving and pausing game...");
         this.getConfigurationManager().saveAllPlayerData();
         this.saveAllWorlds(false);
      }

      if (this.isGamePaused) {
         synchronized (this.futureTaskQueue) {
            while (!this.futureTaskQueue.isEmpty()) {
               Util.runTask(this.futureTaskQueue.poll(), logger);
            }
         }
      } else {
         super.tick();
         if (this.mc.gameSettings.renderDistanceChunks != this.getConfigurationManager().getViewDistance()) {
            logger.info("Changing view distance to {}, from {}", this.mc.gameSettings.renderDistanceChunks, this.getConfigurationManager().getViewDistance());
            this.getConfigurationManager().setViewDistance(this.mc.gameSettings.renderDistanceChunks);
         }

         if (this.mc.theWorld != null) {
            WorldInfo var9 = this.worldServers[0].P();
            WorldInfo var3 = this.mc.theWorld.P();
            if (!var9.isDifficultyLocked() && var3.getDifficulty() != var9.getDifficulty()) {
               logger.info("Changing difficulty to {}, from {}", var3.getDifficulty(), var9.getDifficulty());
               this.setDifficultyForAllWorlds(var3.getDifficulty());
            } else if (var3.isDifficultyLocked() && !var9.isDifficultyLocked()) {
               logger.info("Locking difficulty to {}", var3.getDifficulty());

               for (WorldServer var7 : this.worldServers) {
                  if (var7 != null) {
                     var7.P().setDifficultyLocked(true);
                  }
               }
            }
         }
      }
   }

   @Override
   public void initiateShutdown() {
      if (!Reflector.MinecraftForge.exists() || this.isServerRunning()) {
         Futures.getUnchecked(this.addScheduledTask(new Runnable() {
            @Override
            public void run() {
               for (EntityPlayerMP var2 : Lists.newArrayList(IntegratedServer.this.getConfigurationManager().getPlayerList())) {
                  IntegratedServer.this.getConfigurationManager().playerLoggedOut(var2);
               }
            }
         }));
      }

      super.initiateShutdown();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }
   }

   @Override
   public boolean shouldBroadcastRconToOps() {
      return true;
   }

   @Override
   public boolean method_06818() {
      return false;
   }

   @Override
   public boolean isDedicatedServer() {
      return false;
   }

   @Override
   public EnumDifficulty getDifficulty() {
      return this.mc.theWorld == null ? this.mc.gameSettings.difficulty : this.mc.theWorld.P().getDifficulty();
   }

   public void onTick() {
      for (WorldServer var2 : Arrays.asList(this.worldServers)) {
         this.onTick(var2);
      }
   }

   @Override
   public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
      super.addServerStatsToSnooper(var1);
      var1.addClientStat("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
   }

   @Override
   public void loadAllWorlds(String var1, String var2, long var3, WorldType var5, String var6) {
      this.convertMapIfNeeded(var1);
      boolean var7 = Reflector.DimensionManager.exists();
      if (!var7) {
         this.worldServers = new WorldServer[3];
         this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
      }

      ISaveHandler var8 = this.getActiveAnvilConverter().getSaveLoader(var1, true);
      this.setResourcePackFromWorld(this.U(), var8);
      WorldInfo var9 = var8.loadWorldInfo();
      if (var9 == null) {
         var9 = new WorldInfo(this.theWorldSettings, var2);
      } else {
         var9.setWorldName(var2);
      }

      if (var7) {
         WorldServer var10 = this.isDemo()
            ? (WorldServer)new DemoWorldServer(this, var8, var9, 0, this.theProfiler).init()
            : (WorldServer)new WorldServer(this, var8, var9, 0, this.theProfiler).init();
         var10.initialize(this.theWorldSettings);
         Integer[] var11 = (Integer[])Reflector.call(Reflector.DimensionManager_getStaticDimensionIDs);
         Integer[] var12 = var11;
         int var13 = var11.length;

         for (int var14 = 0; var14 < var13; var14++) {
            int var15 = var12[var14];
            WorldServer var16 = var15 == 0 ? var10 : (WorldServer)new WorldServerMulti(this, var8, var15, var10, this.theProfiler).init();
            var16.addWorldAccess(new WorldManager(this, var16));
            if (!this.isSinglePlayer()) {
               var16.P().setGameType(this.getGameType());
            }

            if (Reflector.EventBus.exists()) {
               Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, var16);
            }
         }

         this.getConfigurationManager().setPlayerManager(new WorldServer[]{var10});
         if (var10.P().getDifficulty() == null) {
            this.setDifficultyForAllWorlds(this.mc.gameSettings.difficulty);
         }
      } else {
         for (int var17 = 0; var17 < this.worldServers.length; var17++) {
            byte var18 = 0;
            if (var17 == 1) {
               var18 = -1;
            }

            if (var17 == 2) {
               var18 = 1;
            }

            if (var17 == 0) {
               if (this.isDemo()) {
                  this.worldServers[var17] = (WorldServer)new DemoWorldServer(this, var8, var9, var18, this.theProfiler).init();
               } else {
                  this.worldServers[var17] = (WorldServer)new WorldServer(this, var8, var9, var18, this.theProfiler).init();
               }

               this.worldServers[var17].initialize(this.theWorldSettings);
            } else {
               this.worldServers[var17] = (WorldServer)new WorldServerMulti(this, var8, var18, this.worldServers[0], this.theProfiler).init();
            }

            this.worldServers[var17].addWorldAccess(new WorldManager(this, this.worldServers[var17]));
         }

         this.getConfigurationManager().setPlayerManager(this.worldServers);
         if (this.worldServers[0].P().getDifficulty() == null) {
            this.setDifficultyForAllWorlds(this.mc.gameSettings.difficulty);
         }
      }

      this.initialWorldChunkLoad();
   }

   @Override
   public boolean method_06823() {
      return false;
   }

   @Override
   public WorldSettings.GameType getGameType() {
      return this.theWorldSettings.getGameType();
   }

   @Override
   public void setGameType(WorldSettings.GameType var1) {
      this.getConfigurationManager().setGameType(var1);
   }

   public void fixWorldTime(WorldServer var1) {
      WorldInfo var2 = var1.P();
      if (var2.getGameType().getID() == 1) {
         long var3 = var1.L();
         long var5 = var3 % 24000L;
         if (Config.isTimeDayOnly()) {
            if (var5 <= 1000L) {
               var1.setWorldTime(var3 - var5 + 1001L);
            }

            if (var5 >= 11000L) {
               var1.setWorldTime(var3 - var5 + 24001L);
            }
         }

         if (Config.isTimeNightOnly()) {
            if (var5 <= 14000L) {
               var1.setWorldTime(var3 - var5 + 14001L);
            }

            if (var5 >= 22000L) {
               var1.setWorldTime(var3 - var5 + 24000L + 14001L);
            }
         }
      }
   }

   @Override
   public String shareToLAN(WorldSettings.GameType var1, boolean var2) {
      try {
         int var3 = -1;

         try {
            var3 = HttpUtil.getSuitableLanPort();
         } catch (IOException var5) {
         }

         if (var3 <= 0) {
            var3 = 25564;
         }

         this.getNetworkSystem().addLanEndpoint((InetAddress)null, var3);
         logger.info("Started on " + var3);
         this.isPublic = true;
         this.lanServerPing = new ThreadLanServerPing(this.am(), var3 + "");
         this.lanServerPing.start();
         this.getConfigurationManager().setGameType(var1);
         this.getConfigurationManager().setCommandsAllowedForAll(var2);
         return var3 + "";
      } catch (IOException var6) {
         return null;
      }
   }

   @Override
   public boolean isHardcore() {
      return this.theWorldSettings.getHardcoreEnabled();
   }

   public void setStaticInstance() {
      this.setInstance();
   }
}
