package net.minecraft.server;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Util;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements ICommandSender, IThreadListener, IPlayerUsage, Runnable {
   public String resourcePackHash;
   public List<ITickable> playersOnline;
   public PlayerUsageSnooper recoveredField1169 = new PlayerUsageSnooper("server", this, getCurrentTimeMillis());
   public boolean recoveredField1170;
   public ServerStatusResponse recoveredField1171;
   public boolean serverRunning;
   public long recoveredField1172;
   public String currentTask;
   public boolean recoveredField1173;
   public ICommandManager commandManager;
   public Profiler theProfiler;
   public int recoveredField1174;
   public Proxy serverProxy;
   public WorldServer[] worldServers;
   public NetworkSystem recoveredField1175;
   public boolean recoveredField1176;
   public boolean recoveredField1177;
   public boolean worldIsBeingDeleted;
   public long recoveredField1178;
   public int percentDone;
   public MinecraftSessionService sessionService;
   public int tickCounter;
   public KeyPair serverKeyPair;
   public long[] recoveredField1179;
   public long recoveredField1180;
   public String recoveredField1181;
   public boolean recoveredField1182;
   public String recoveredField1183;
   public boolean canSpawnAnimals;
   public GameProfileRepository profileRepo;
   public ServerConfigurationManager serverConfigManager;
   public int recoveredField1184;
   public Random recoveredField1185;
   public boolean recoveredField1186;
   public boolean startProfiling;
   public static Logger logger = LogManager.getLogger();
   public ISaveFormat recoveredField1187;
   public PlayerProfileCache recoveredField1188;
   public String resourcePackUrl;
   public String serverOwner;
   public static File USER_CACHE_FILE = new File("usercache.json");
   public long[][] timeOfLastDimensionTick;
   public boolean recoveredField1189;
   public File anvilFile;
   public Queue<FutureTask<?>> futureTaskQueue;
   public boolean recoveredField1190;
   public String recoveredField1191;
   public Thread serverThread;
   public int recoveredField1192;
   public boolean recoveredField1193;
   public static MinecraftServer mcServer;
   public String recoveredField1194;
   public YggdrasilAuthenticationService recoveredField1195;

   public void h(boolean var1) {
      this.recoveredField1190 = var1;
   }

   public void setDifficultyForAllWorlds(EnumDifficulty var1) {
      for (int var2 = 0; var2 < this.worldServers.length; var2++) {
         WorldServer var3 = this.worldServers[var2];
         if (var3 != null) {
            if (var3.P().isHardcoreModeEnabled()) {
               var3.P().setDifficulty(EnumDifficulty.HARD);
               var3.setAllowedSpawnTypes(true, true);
            } else if (this.isSinglePlayer()) {
               var3.P().setDifficulty(var1);
               var3.setAllowedSpawnTypes(var3.getDifficulty() != EnumDifficulty.PEACEFUL, true);
            } else {
               var3.P().setDifficulty(var1);
               var3.setAllowedSpawnTypes(this.allowSpawnMonsters(), this.canSpawnAnimals);
            }
         }
      }
   }

   public void setInstance() {
      mcServer = this;
   }

   public File getDataDirectory() {
      return new File(".");
   }

   @Override
   public ListenableFuture<Object> addScheduledTask(Runnable var1) {
      Validate.notNull(var1);
      return this.callFromMainThread(Executors.callable(var1));
   }

   public int getBuildLimit() {
      return this.recoveredField1174;
   }

   public boolean method_06917() {
      return this.recoveredField1170;
   }

   public abstract boolean method_06818();

   public int method_06871() {
      return this.recoveredField1192;
   }

   public boolean isSinglePlayer() {
      return this.serverOwner != null;
   }

   public void refreshStatusNextTick() {
      this.recoveredField1178 = 0L;
   }

   public void addFaviconToStatusResponse(ServerStatusResponse var1) {
      File var2 = this.getFile("server-icon.png");
      if (var2.isFile()) {
         ByteBuf var3 = Unpooled.buffer();

         try {
            BufferedImage var4 = ImageIO.read(var2);
            Validate.validState(var4.getWidth() == 64, "Must be 64 pixels wide");
            Validate.validState(var4.getHeight() == 64, "Must be 64 pixels high");
            ImageIO.write(var4, "PNG", new ByteBufOutputStream(var3));
            ByteBuf var5 = Base64.encode(var3);
            var1.setFavicon("data:image/png;base64," + var5.toString(Charsets.UTF_8));
         } catch (Exception var9) {
            logger.error("Couldn't load server icon", var9);
         } finally {
            var3.release();
         }
      }
   }

   public boolean getGuiEnabled() {
      return false;
   }

   public MinecraftServer(File var1, Proxy var2, File var3) {
      this.playersOnline = Lists.newArrayList();
      this.theProfiler = new Profiler();
      this.recoveredField1171 = new ServerStatusResponse();
      this.recoveredField1185 = new Random();
      this.recoveredField1184 = -1;
      this.serverRunning = true;
      this.recoveredField1192 = 0;
      this.recoveredField1179 = new long[100];
      this.resourcePackUrl = "";
      this.resourcePackHash = "";
      this.recoveredField1178 = 0L;
      this.futureTaskQueue = Queues.newArrayDeque();
      this.recoveredField1172 = getCurrentTimeMillis();
      this.serverProxy = var2;
      mcServer = this;
      this.anvilFile = var1;
      this.recoveredField1175 = new NetworkSystem(this);
      this.recoveredField1188 = new PlayerProfileCache(this, var3);
      this.commandManager = this.createNewCommandManager();
      this.recoveredField1187 = new AnvilSaveConverter(var1);
      this.recoveredField1195 = new YggdrasilAuthenticationService(var2, UUID.randomUUID().toString());
      this.sessionService = this.recoveredField1195.createMinecraftSessionService();
      this.profileRepo = this.recoveredField1195.createProfileRepository();
   }

   public void initiateShutdown() {
      this.serverRunning = false;
   }

   public String getResourcePackHash() {
      return this.resourcePackHash;
   }

   public CrashReport addServerInfoToCrashReport(CrashReport var1) {
      var1.getCategory().addCrashSectionCallable("Profiler Position", new Callable<String>() {
         public String call() {
            return MinecraftServer.this.theProfiler.profilingEnabled ? MinecraftServer.this.theProfiler.getNameOfLastSection() : "N/A (disabled)";
         }
      });
      if (this.serverConfigManager != null) {
         var1.getCategory()
            .addCrashSectionCallable(
               "Player Count",
               new Callable<String>() {
                  public String call() {
                     return MinecraftServer.this.serverConfigManager.getCurrentPlayerCount()
                        + " / "
                        + MinecraftServer.this.serverConfigManager.getMaxPlayers()
                        + "; "
                        + MinecraftServer.this.serverConfigManager.getPlayerList();
                  }
               }
            );
      }

      return var1;
   }

   public File getFile(String var1) {
      return new File(this.getDataDirectory(), var1);
   }

   public abstract EnumDifficulty getDifficulty();

   public PlayerUsageSnooper getPlayerUsageSnooper() {
      return this.recoveredField1169;
   }

   public abstract boolean startServer() throws java.io.IOException ;

   public boolean isPVPEnabled() {
      return this.recoveredField1176;
   }

   public void setCanSpawnAnimals(boolean var1) {
      this.canSpawnAnimals = var1;
   }

   public void updateTimeLightAndEntities() {
      this.theProfiler.startSection("jobs");
      synchronized (this.futureTaskQueue) {
         while (!this.futureTaskQueue.isEmpty()) {
            Util.runTask(this.futureTaskQueue.poll(), logger);
         }
      }

      this.theProfiler.endStartSection("levels");

      for (int var10 = 0; var10 < this.worldServers.length; var10++) {
         long var2 = System.nanoTime();
         if (var10 == 0 || this.getAllowNether()) {
            WorldServer var4 = this.worldServers[var10];
            this.theProfiler.startSection(var4.P().getWorldName());
            if (this.tickCounter % 20 == 0) {
               this.theProfiler.startSection("timeSync");
               this.serverConfigManager
                  .sendPacketToAllPlayersInDimension(
                     new S03PacketTimeUpdate(var4.K(), var4.L(), var4.Q().getBoolean("doDaylightCycle")), var4.t.getDimensionId()
                  );
               this.theProfiler.endSection();
            }

            this.theProfiler.startSection("tick");

            try {
               var4.tick();
            } catch (Throwable var8) {
               CrashReport var6 = CrashReport.makeCrashReport(var8, "Exception ticking world");
               var4.addWorldInfoToCrashReport(var6);
               throw new ReportedException(var6);
            }

            try {
               var4.updateEntities();
            } catch (Throwable var7) {
               CrashReport var12 = CrashReport.makeCrashReport(var7, "Exception ticking world entities");
               var4.addWorldInfoToCrashReport(var12);
               throw new ReportedException(var12);
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("tracker");
            var4.getEntityTracker().updateTrackedEntities();
            this.theProfiler.endSection();
            this.theProfiler.endSection();
         }

         this.timeOfLastDimensionTick[var10][this.tickCounter % 100] = System.nanoTime() - var2;
      }

      this.theProfiler.endStartSection("connection");
      this.getNetworkSystem().networkTick();
      this.theProfiler.endStartSection("players");
      this.serverConfigManager.onTick();
      this.theProfiler.endStartSection("tickables");

      for (int var11 = 0; var11 < this.playersOnline.size(); var11++) {
         this.playersOnline.get(var11).update();
      }

      this.theProfiler.endSection();
   }

   @Override
   public void run() {
      try {
         if (this.startServer()) {
            this.recoveredField1172 = getCurrentTimeMillis();
            long var1 = 0L;
            this.recoveredField1171.setServerDescription(new ChatComponentText(this.recoveredField1194));
            this.recoveredField1171.setProtocolVersionInfo(new ServerStatusResponse.MinecraftProtocolVersionIdentifier("1.8.9", 47));
            this.addFaviconToStatusResponse(this.recoveredField1171);

            while (this.serverRunning) {
               long var49 = getCurrentTimeMillis();
               long var5 = var49 - this.recoveredField1172;
               if (var5 > 2000L && this.recoveredField1172 - this.recoveredField1180 >= 15000L) {
                  logger.warn(
                     "Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", var5, var5 / 50L
                  );
                  var5 = 2000L;
                  this.recoveredField1180 = this.recoveredField1172;
               }

               if (var5 < 0L) {
                  logger.warn("Time ran backwards! Did the system time change?");
                  var5 = 0L;
               }

               var1 += var5;
               this.recoveredField1172 = var49;
               if (this.worldServers[0].areAllPlayersAsleep()) {
                  this.tick();
                  var1 = 0L;
               } else {
                  while (var1 > 50L) {
                     var1 -= 50L;
                     this.tick();
                  }
               }

               Thread.sleep(Math.max(1L, 50L - var1));
               this.recoveredField1170 = true;
            }
         } else {
            this.finalTick((CrashReport)null);
         }
      } catch (Throwable var46) {
         logger.error("Encountered an unexpected exception", var46);
         Object var2 = null;
         if (var46 instanceof ReportedException) {
            var2 = this.addServerInfoToCrashReport(((ReportedException)var46).getCrashReport());
         } else {
            var2 = this.addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", var46));
         }

         File var3 = new File(
            new File(this.getDataDirectory(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt"
         );
         if (((CrashReport)var2).saveToFile(var3)) {
            logger.error("This crash report has been saved to: " + var3.getAbsolutePath());
         } else {
            logger.error("We were unable to save this crash report to disk.");
         }

         this.finalTick((CrashReport)var2);
      } finally {
         try {
            this.recoveredField1177 = true;
            this.stopServer();
         } catch (Throwable var44) {
            logger.error("Exception stopping the server", var44);
         } finally {
            this.systemExitNow();
         }
      }
   }

   public GameProfileRepository getGameProfileRepository() {
      return this.profileRepo;
   }

   public void setResourcePackFromWorld(String var1, ISaveHandler var2) {
      File var3 = new File(var2.getWorldDirectory(), "resources.zip");
      if (var3.isFile()) {
         this.setResourcePack("level://" + var1 + "/" + var3.getName(), "");
      }
   }

   public void method_06847(boolean var1) {
      this.recoveredField1182 = var1;
   }

   public void saveAllWorlds(boolean var1) {
      if (!this.worldIsBeingDeleted) {
         for (WorldServer var5 : this.worldServers) {
            if (var5 != null) {
               if (!var1) {
                  logger.info("Saving chunks for level '" + var5.P().getWorldName() + "'/" + var5.t.getDimensionName());
               }

               try {
                  var5.saveAllChunks(true, (IProgressUpdate)null);
               } catch (MinecraftException var7) {
                  logger.warn(var7.getMessage());
               }
            }
         }
      }
   }

   public void convertMapIfNeeded(String var1) {
      if (this.getActiveAnvilConverter().isOldMapFormat(var1)) {
         logger.info("Converting map!");
         this.setUserMessage("menu.convertingLevel");
         this.getActiveAnvilConverter().convertMapFormat(var1, new IProgressUpdate() {
            public long startTime = System.currentTimeMillis();

            @Override
            public void setLoadingProgress(int var1) {
               if (System.currentTimeMillis() - this.startTime >= 1000L) {
                  this.startTime = System.currentTimeMillis();
                  MinecraftServer.logger.info("Converting... " + var1 + "%");
               }
            }

            @Override
            public void displayLoadingString(String var1) {
            }

            @Override
            public void setDoneWorking() {
            }

            @Override
            public void resetProgressAndMessage(String var1) {
            }

            @Override
            public void displaySavingString(String var1) {
            }
         });
      }
   }

   @Override
   public BlockPos getPosition() {
      return BlockPos.ORIGIN;
   }

   public String getResourcePackUrl() {
      return this.resourcePackUrl;
   }

   public void startServerThread() {
      this.serverThread = new Thread(this, "Server thread");
      this.serverThread.start();
   }

   public void systemExitNow() {
   }

   public boolean isServerRunning() {
      return this.serverRunning;
   }

   public void method_06846(String var1) {
      this.recoveredField1183 = var1;
   }

   public void setResourcePack(String var1, String var2) {
      this.resourcePackUrl = var1;
      this.resourcePackHash = var2;
   }

   public void l(String var1) {
      this.recoveredField1194 = var1;
   }

   public Proxy getServerProxy() {
      return this.serverProxy;
   }

   public String[] getAllUsernames() {
      return this.serverConfigManager.getAllUsernames();
   }

   public ServerCommandManager createNewCommandManager() {
      return new ServerCommandManager();
   }

   @Override
   public Vec3 q_() {
      return new Vec3(0.0, 0.0, 0.0);
   }

   public void clearCurrentTask() {
      this.currentTask = null;
      this.percentDone = 0;
   }

   public boolean isServerInOnlineMode() {
      return this.recoveredField1186;
   }

   public String getServerModName() {
      return "vanilla";
   }

   public void deleteWorldAndStopServer() {
      this.worldIsBeingDeleted = true;
      this.getActiveAnvilConverter().flushCache();

      for (int var1 = 0; var1 < this.worldServers.length; var1++) {
         WorldServer var2 = this.worldServers[var1];
         if (var2 != null) {
            var2.flush();
         }
      }

      this.getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].O().getWorldDirectoryName());
      this.initiateShutdown();
   }

   @Override
   public boolean isCallingFromMinecraftThread() {
      return Thread.currentThread() == this.serverThread;
   }

   public void logWarning(String var1) {
      logger.warn(var1);
   }

   public abstract boolean shouldBroadcastConsoleToOps();

   public void stopServer() {
      if (!this.worldIsBeingDeleted) {
         logger.info("Stopping server");
         if (this.getNetworkSystem() != null) {
            this.getNetworkSystem().terminateEndpoints();
         }

         if (this.serverConfigManager != null) {
            logger.info("Saving players");
            this.serverConfigManager.saveAllPlayerData();
            this.serverConfigManager.removeAllPlayers();
         }

         if (this.worldServers != null) {
            logger.info("Saving worlds");
            this.saveAllWorlds(false);

            for (int var1 = 0; var1 < this.worldServers.length; var1++) {
               WorldServer var2 = this.worldServers[var1];
               var2.flush();
            }
         }

         if (this.recoveredField1169.isSnooperRunning()) {
            this.recoveredField1169.stopSnooper();
         }
      }
   }

   public KeyPair getKeyPair() {
      return this.serverKeyPair;
   }

   public void setGameType(WorldSettings.GameType var1) {
      for (int var2 = 0; var2 < this.worldServers.length; var2++) {
         getServer().worldServers[var2].P().setGameType(var1);
      }
   }

   public boolean allowSpawnMonsters() {
      return true;
   }

   public boolean isBlockProtected(World var1, BlockPos var2, EntityPlayer var3) {
      return false;
   }

   public void setKeyPair(KeyPair var1) {
      this.serverKeyPair = var1;
   }

   public ServerStatusResponse getServerStatusResponse() {
      return this.recoveredField1171;
   }

   @Override
   public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
      var1.addClientStat("whitelist_enabled", false);
      var1.addClientStat("whitelist_count", 0);
      if (this.serverConfigManager != null) {
         var1.addClientStat("players_current", this.getCurrentPlayerCount());
         var1.addClientStat("players_max", this.getMaxPlayers());
         var1.addClientStat("players_seen", this.serverConfigManager.getAvailablePlayerDat().length);
      }

      var1.addClientStat("uses_auth", this.recoveredField1186);
      var1.addClientStat("gui_state", this.getGuiEnabled() ? "enabled" : "disabled");
      var1.addClientStat("run_time", (getCurrentTimeMillis() - var1.getMinecraftStartTimeMillis()) / 60L * 1000L);
      var1.addClientStat("avg_tick_ms", (int)(MathHelper.average(this.recoveredField1179) * 1.0E-6));
      int var2 = 0;
      if (this.worldServers != null) {
         for (int var3 = 0; var3 < this.worldServers.length; var3++) {
            if (this.worldServers[var3] != null) {
               WorldServer var4 = this.worldServers[var3];
               WorldInfo var5 = var4.P();
               var1.addClientStat("world[" + var2 + "][dimension]", var4.t.getDimensionId());
               var1.addClientStat("world[" + var2 + "][mode]", var5.getGameType());
               var1.addClientStat("world[" + var2 + "][difficulty]", var4.getDifficulty());
               var1.addClientStat("world[" + var2 + "][hardcore]", var5.isHardcoreModeEnabled());
               var1.addClientStat("world[" + var2 + "][generator_name]", var5.getTerrainType().getWorldTypeName());
               var1.addClientStat("world[" + var2 + "][generator_version]", var5.getTerrainType().getGeneratorVersion());
               var1.addClientStat("world[" + var2 + "][height]", this.recoveredField1174);
               var1.addClientStat("world[" + var2 + "][chunks_loaded]", var4.N().getLoadedChunkCount());
               var2++;
            }
         }
      }

      var1.addClientStat("worlds", var2);
   }

   public abstract boolean shouldBroadcastRconToOps();

   public GameProfile[] getGameProfiles() {
      return this.serverConfigManager.getAllProfiles();
   }

   public void initialWorldChunkLoad() {
      byte var1 = 16;
      byte var2 = 4;
      short var3 = 192;
      short var4 = 625;
      int var5 = 0;
      this.setUserMessage("menu.generatingTerrain");
      byte var6 = 0;
      logger.info("Preparing start region for level " + var6);
      WorldServer var7 = this.worldServers[var6];
      BlockPos var8 = var7.M();
      long var9 = getCurrentTimeMillis();

      for (int var11 = -192; var11 <= 192 && this.isServerRunning(); var11 += 16) {
         for (int var12 = -192; var12 <= 192 && this.isServerRunning(); var12 += 16) {
            long var13 = getCurrentTimeMillis();
            if (var13 - var9 > 1000L) {
               this.outputPercentRemaining("Preparing spawn area", var5 * 100 / 625);
               var9 = var13;
            }

            var5++;
            var7.theChunkProviderServer.loadChunk(var8.getX() + var11 >> 4, var8.getZ() + var12 >> 4);
         }
      }

      this.clearCurrentTask();
   }

   public NetworkSystem getNetworkSystem() {
      return this.recoveredField1175;
   }

   public void tick() {
      long var1 = System.nanoTime();
      this.tickCounter++;
      if (this.startProfiling) {
         this.startProfiling = false;
         this.theProfiler.profilingEnabled = true;
         this.theProfiler.clearProfiling();
      }

      this.theProfiler.startSection("root");
      this.updateTimeLightAndEntities();
      if (var1 - this.recoveredField1178 >= 5000000000L) {
         this.recoveredField1178 = var1;
         this.recoveredField1171.setPlayerCountData(new ServerStatusResponse.PlayerCountData(this.getMaxPlayers(), this.getCurrentPlayerCount()));
         GameProfile[] var3 = new GameProfile[Math.min(this.getCurrentPlayerCount(), 12)];
         int var4 = MathHelper.getRandomIntegerInRange(this.recoveredField1185, 0, this.getCurrentPlayerCount() - var3.length);

         for (int var5 = 0; var5 < var3.length; var5++) {
            var3[var5] = this.serverConfigManager.getPlayerList().get(var4 + var5).getGameProfile();
         }

         Collections.shuffle(Arrays.asList(var3));
         this.recoveredField1171.getPlayerCountData().setPlayers(var3);
      }

      if (this.tickCounter % 900 == 0) {
         this.theProfiler.startSection("save");
         this.serverConfigManager.saveAllPlayerData();
         this.saveAllWorlds(true);
         this.theProfiler.endSection();
      }

      this.theProfiler.startSection("tallying");
      this.recoveredField1179[this.tickCounter % 100] = System.nanoTime() - var1;
      this.theProfiler.endSection();
      this.theProfiler.startSection("snooper");
      if (!this.recoveredField1169.isSnooperRunning() && this.tickCounter > 100) {
         this.recoveredField1169.startSnooper();
      }

      if (this.tickCounter % 6000 == 0) {
         this.recoveredField1169.addMemoryStatsToSnooper();
      }

      this.theProfiler.endSection();
      this.theProfiler.endSection();
   }

   @Override
   public World s_() {
      return this.worldServers[0];
   }

   public int getCurrentPlayerCount() {
      return this.serverConfigManager.getCurrentPlayerCount();
   }

   public MinecraftSessionService getMinecraftSessionService() {
      return this.sessionService;
   }

   public String am() {
      return this.recoveredField1194;
   }

   public void outputPercentRemaining(String var1, int var2) {
      this.currentTask = var1;
      this.percentDone = var2;
      logger.info(var1 + ": " + var2 + "%");
   }

   public void setServerOwner(String var1) {
      this.serverOwner = var1;
   }

   public boolean getAllowNether() {
      return true;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      logger.info(var1.getUnformattedText());
   }

   public int getMaxPlayers() {
      return this.serverConfigManager.getMaxPlayers();
   }

   public boolean getForceGamemode() {
      return this.recoveredField1193;
   }

   public ServerConfigurationManager getConfigurationManager() {
      return this.serverConfigManager;
   }

   public Entity getEntityFromUuid(UUID var1) {
      for (WorldServer var5 : this.worldServers) {
         if (var5 != null) {
            Entity var6 = var5.getEntityFromUuid(var1);
            if (var6 != null) {
               return var6;
            }
         }
      }

      return null;
   }

   @Override
   public boolean C_() {
      return getServer().worldServers[0].Q().getBoolean("sendCommandFeedback");
   }

   public void method_06820(boolean var1) {
      this.recoveredField1189 = var1;
   }

   public String U() {
      return this.recoveredField1191;
   }

   public void loadAllWorlds(String var1, String var2, long var3, WorldType var5, String var6) {
      this.convertMapIfNeeded(var1);
      this.setUserMessage("menu.loadingLevel");
      this.worldServers = new WorldServer[3];
      this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
      ISaveHandler var7 = this.recoveredField1187.getSaveLoader(var1, true);
      this.setResourcePackFromWorld(this.U(), var7);
      WorldInfo var8 = var7.loadWorldInfo();
      WorldSettings var9;
      if (var8 == null) {
         if (this.isDemo()) {
            var9 = DemoWorldServer.demoWorldSettings;
         } else {
            var9 = new WorldSettings(var3, this.getGameType(), this.method_06823(), this.isHardcore(), var5);
            var9.setWorldName(var6);
            if (this.recoveredField1182) {
               var9.enableBonusChest();
            }
         }

         var8 = new WorldInfo(var9, var2);
      } else {
         var8.setWorldName(var2);
         var9 = new WorldSettings(var8);
      }

      for (int var10 = 0; var10 < this.worldServers.length; var10++) {
         byte var11 = 0;
         if (var10 == 1) {
            var11 = -1;
         }

         if (var10 == 2) {
            var11 = 1;
         }

         if (var10 == 0) {
            if (this.isDemo()) {
               this.worldServers[var10] = (WorldServer)new DemoWorldServer(this, var7, var8, var11, this.theProfiler).init();
            } else {
               this.worldServers[var10] = (WorldServer)new WorldServer(this, var7, var8, var11, this.theProfiler).init();
            }

            this.worldServers[var10].initialize(var9);
         } else {
            this.worldServers[var10] = (WorldServer)new WorldServerMulti(this, var7, var11, this.worldServers[0], this.theProfiler).init();
         }

         this.worldServers[var10].addWorldAccess(new WorldManager(this, this.worldServers[var10]));
         if (!this.isSinglePlayer()) {
            this.worldServers[var10].P().setGameType(this.getGameType());
         }
      }

      this.serverConfigManager.setPlayerManager(this.worldServers);
      this.setDifficultyForAllWorlds(this.getDifficulty());
      this.initialWorldChunkLoad();
   }

   @Override
   public Entity p_() {
      return null;
   }

   public void f(boolean var1) {
      this.recoveredField1173 = var1;
   }

   public void setConfigManager(ServerConfigurationManager var1) {
      this.serverConfigManager = var1;
   }

   public PlayerProfileCache getPlayerProfileCache() {
      return this.recoveredField1188;
   }

   public boolean method_06888() {
      return this.recoveredField1173;
   }

   public abstract String shareToLAN(WorldSettings.GameType var1, boolean var2);

   public MinecraftServer(Proxy var1, File var2) {
      this.playersOnline = Lists.newArrayList();
      this.theProfiler = new Profiler();
      this.recoveredField1171 = new ServerStatusResponse();
      this.recoveredField1185 = new Random();
      this.recoveredField1184 = -1;
      this.serverRunning = true;
      this.recoveredField1192 = 0;
      this.recoveredField1179 = new long[100];
      this.resourcePackUrl = "";
      this.resourcePackHash = "";
      this.recoveredField1178 = 0L;
      this.futureTaskQueue = Queues.newArrayDeque();
      this.recoveredField1172 = getCurrentTimeMillis();
      this.serverProxy = var1;
      mcServer = this;
      this.anvilFile = null;
      this.recoveredField1175 = null;
      this.recoveredField1188 = new PlayerProfileCache(this, var2);
      this.commandManager = null;
      this.recoveredField1187 = null;
      this.recoveredField1195 = new YggdrasilAuthenticationService(var1, UUID.randomUUID().toString());
      this.sessionService = this.recoveredField1195.createMinecraftSessionService();
      this.profileRepo = this.recoveredField1195.createProfileRepository();
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentText(this.z_());
   }

   public int getTickCounter() {
      return this.tickCounter;
   }

   public boolean isAnnouncingPlayerAchievements() {
      return true;
   }

   public void g(boolean var1) {
      this.recoveredField1176 = var1;
   }

   public boolean getCanSpawnAnimals() {
      return this.canSpawnAnimals;
   }

   @Override
   public void setCommandStat(CommandResultStats.Type var1, int var2) {
   }

   public void method_06851(int var1) {
      this.recoveredField1174 = var1;
   }

   public static long getCurrentTimeMillis() {
      return System.currentTimeMillis();
   }

   public abstract int getOpPermissionLevel();

   public WorldServer worldServerForDimension(int var1) {
      return var1 == -1 ? this.worldServers[1] : (var1 == 1 ? this.worldServers[2] : this.worldServers[0]);
   }

   public abstract boolean isDedicatedServer();

   public boolean isAnvilFileSet() {
      return this.anvilFile != null;
   }

   public void enableProfiling() {
      this.startProfiling = true;
   }

   public void d(boolean var1) {
      this.recoveredField1186 = var1;
   }

   @Override
   public void addServerTypeToSnooper(PlayerUsageSnooper var1) {
      var1.addStatToSnooper("singleplayer", this.isSinglePlayer());
      var1.addStatToSnooper("server_brand", this.getServerModName());
      var1.addStatToSnooper("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
      var1.addStatToSnooper("dedicated", this.isDedicatedServer());
   }

   public String getMinecraftVersion() {
      return "1.8.9";
   }

   public boolean method_06889() {
      return this.recoveredField1177;
   }

   public <V> ListenableFuture<V> callFromMainThread(Callable<V> var1) {
      Validate.notNull(var1);
      if (!this.isCallingFromMinecraftThread() && !this.method_06889()) {
         ListenableFutureTask var2 = ListenableFutureTask.create(var1);
         synchronized (this.futureTaskQueue) {
            this.futureTaskQueue.add(var2);
            return var2;
         }
      } else {
         try {
            return Futures.immediateFuture((V)var1.call());
         } catch (Exception var6) {
            return Futures.immediateFailedCheckedFuture(var6);
         }
      }
   }

   public int getMaxWorldSize() {
      return 29999984;
   }

   public List<String> getTabCompletions(ICommandSender var1, String var2, BlockPos var3) {
      ArrayList var4 = Lists.newArrayList();
      if (var2.startsWith("/")) {
         var2 = var2.substring(1);
         boolean var12 = !var2.contains(" ");
         List var13 = this.commandManager.getTabCompletionOptions(var1, var2, var3);
         if (var13 != null) {
            for (String var15 : (Iterable<String>)(Iterable<?>)(var13)) {
               if (var12) {
                  var4.add("/" + var15);
               } else {
                  var4.add(var15);
               }
            }
         }

         return var4;
      } else {
         String[] var5 = var2.split(" ", -1);
         String var6 = var5[var5.length - 1];

         for (String var10 : this.serverConfigManager.getAllUsernames()) {
            if (CommandBase.doesStringStartWith(var6, var10)) {
               var4.add(var10);
            }
         }

         return var4;
      }
   }

   public abstract boolean method_06823();

   @Override
   public boolean isSnooperEnabled() {
      return true;
   }

   public abstract WorldSettings.GameType getGameType();

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   public void method_06909(String var1) {
      this.recoveredField1191 = var1;
   }

   public int getSpawnProtectionSize() {
      return 16;
   }

   public ISaveFormat getActiveAnvilConverter() {
      return this.recoveredField1187;
   }

   @Override
   public String z_() {
      return "Server";
   }

   public boolean method_06894() {
      return this.recoveredField1190;
   }

   public String V() {
      return this.recoveredField1183;
   }

   public void setPlayerIdleTimeout(int var1) {
      this.recoveredField1192 = var1;
   }

   public int getNetworkCompressionTreshold() {
      return 256;
   }

   public String getServerOwner() {
      return this.serverOwner;
   }

   public ICommandManager getCommandManager() {
      return this.commandManager;
   }

   public synchronized void setUserMessage(String var1) {
      this.recoveredField1181 = var1;
   }

   public abstract boolean isCommandBlockEnabled();

   public static MinecraftServer getServer() {
      return mcServer;
   }

   public synchronized String method_06876() {
      return this.recoveredField1181;
   }

   public void finalTick(CrashReport var1) {
   }

   public abstract boolean isHardcore();

   public boolean isDemo() {
      return this.recoveredField1189;
   }
}
