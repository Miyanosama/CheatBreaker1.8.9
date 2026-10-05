package net.minecraft.server.management;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S38PacketPlayerListItem$Action;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S44PacketWorldBorder$Action;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.demo.DemoWorldManager;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ServerConfigurationManager {
   public MinecraftServer mcServer;
   public int maxPlayers;
   public Map<UUID, StatisticsFile> playerStatFiles;
   public static File FILE_OPS = new File("ops.json");
   public static File FILE_PLAYERBANS = new File("banned-players.json");
   public BanList bannedIPs;
   public UserListOps ops;
   public boolean commandsAllowedForAll;
   public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
   public static File FILE_IPBANS = new File("banned-ips.json");
   public Map<UUID, EntityPlayerMP> uuidToPlayerMap;
   public S0DPacketCollectItem field_0020;
   public static Logger logger = LogManager.getLogger();
   public UserListWhitelist whiteListedPlayers;
   public List<EntityPlayerMP> playerEntityList = Lists.newArrayList();
   public int viewDistance;
   public GuiErrorScreen field_0002;
   public int playerPingIndex;
   public static File FILE_WHITELIST = new File("whitelist.json");
   public WorldSettings$GameType gameType;
   public UserListBans bannedPlayers;
   public IPlayerFileData playerNBTManagerObj;
   public boolean whiteListEnforced;

   public boolean canSendCommands(GameProfile var1) {
      return this.ops.hasEntry(var1)
         || this.mcServer.isSinglePlayer()
            && this.mcServer.worldServers[0].P().areCommandsAllowed()
            && this.mcServer.getServerOwner().equalsIgnoreCase(var1.getName())
         || this.commandsAllowedForAll;
   }

   public List<EntityPlayerMP> getPlayersMatchingAddress(String var1) {
      ArrayList var2 = Lists.newArrayList();

      for (EntityPlayerMP var4 : this.playerEntityList) {
         if (var4.getPlayerIP().equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public void syncPlayerInventory(EntityPlayerMP var1) {
      var1.sendContainerToPlayer(var1.bj);
      var1.setPlayerHealthUpdated();
      var1.playerNetServerHandler.sendPacket(new S09PacketHeldItemChange(var1.bi.currentItem));
   }

   public void setPlayerManager(WorldServer[] var1) {
      this.playerNBTManagerObj = var1[0].O().getPlayerNBTManager();
      var1[0].af().addListener(new ServerConfigurationManager$1(this));
   }

   public void transferPlayerToDimension(EntityPlayerMP var1, int var2) {
      int var3 = var1.am;
      WorldServer var4 = this.mcServer.worldServerForDimension(var1.am);
      var1.am = var2;
      WorldServer var5 = this.mcServer.worldServerForDimension(var1.am);
      var1.playerNetServerHandler
         .sendPacket(new S07PacketRespawn(var1.am, var1.o.getDifficulty(), var1.o.P().getTerrainType(), var1.theItemInWorldManager.getGameType()));
      var4.f(var1);
      var1.I = false;
      this.transferEntityToWorld(var1, var3, var4, var5);
      this.preparePlayer(var1, var4);
      var1.playerNetServerHandler.setPlayerLocation(var1.s, var1.t, var1.u, var1.y, var1.z);
      var1.theItemInWorldManager.setWorld(var5);
      this.updateTimeAndWeatherForPlayer(var1, var5);
      this.syncPlayerInventory(var1);

      for (PotionEffect var7 : var1.getActivePotionEffects()) {
         var1.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(var1.F(), var7));
      }
   }

   public BanList getBannedIPs() {
      return this.bannedIPs;
   }

   public void sendToAllNear(double var1, double var3, double var5, double var7, int var9, Packet var10) {
      this.sendToAllNearExcept((EntityPlayer)null, var1, var3, var5, var7, var9, var10);
   }

   public void preparePlayer(EntityPlayerMP var1, WorldServer var2) {
      WorldServer var3 = var1.getServerForPlayer();
      if (var2 != null) {
         var2.getPlayerManager().removePlayer(var1);
      }

      var3.getPlayerManager().addPlayer(var1);
      var3.theChunkProviderServer.loadChunk((int)var1.s >> 4, (int)var1.u >> 4);
   }

   public String[] getAllUsernames() {
      String[] var1 = new String[this.playerEntityList.size()];

      for (int var2 = 0; var2 < this.playerEntityList.size(); var2++) {
         var1[var2] = this.playerEntityList.get(var2).z_();
      }

      return var1;
   }

   public EntityPlayerMP getPlayerByUsername(String var1) {
      for (EntityPlayerMP var3 : this.playerEntityList) {
         if (var3.z_().equalsIgnoreCase(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void sendChatMsgImpl(IChatComponent var1, boolean var2) {
      this.mcServer.addChatMessage(var1);
      byte var3 = (byte)(var2 ? 1 : 0);
      this.sendPacketToAllPlayers(new S02PacketChat(var1, var3));
   }

   public NBTTagCompound getHostPlayerData() {
      return null;
   }

   public String allowUserToConnect(SocketAddress var1, GameProfile var2) {
      if (this.bannedPlayers.isBanned(var2)) {
         UserListBansEntry var5 = this.bannedPlayers.getEntry(var2);
         String var6 = "You are banned from this server!\nReason: " + var5.getBanReason();
         if (var5.getBanEndDate() != null) {
            var6 = var6 + "\nYour ban will be removed on " + dateFormat.format(var5.getBanEndDate());
         }

         return var6;
      } else if (!this.canJoin(var2)) {
         return "You are not white-listed on this server!";
      } else if (this.bannedIPs.isBanned(var1)) {
         IPBanEntry var3 = this.bannedIPs.getBanEntry(var1);
         String var4 = "Your IP address is banned from this server!\nReason: " + var3.getBanReason();
         if (var3.getBanEndDate() != null) {
            var4 = var4 + "\nYour ban will be removed on " + dateFormat.format(var3.getBanEndDate());
         }

         return var4;
      } else {
         return this.playerEntityList.size() >= this.maxPlayers && !this.bypassesPlayerLimit(var2) ? "The server is full!" : null;
      }
   }

   public void updateTimeAndWeatherForPlayer(EntityPlayerMP var1, WorldServer var2) {
      WorldBorder var3 = this.mcServer.worldServers[0].af();
      var1.playerNetServerHandler.sendPacket(new S44PacketWorldBorder(var3, S44PacketWorldBorder$Action.INITIALIZE));
      var1.playerNetServerHandler.sendPacket(new S03PacketTimeUpdate(var2.K(), var2.L(), var2.Q().getBoolean("doDaylightCycle")));
      if (var2.isRaining()) {
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, var2.j(1.0F)));
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, var2.h(1.0F)));
      }
   }

   public void setPlayerGameTypeBasedOnOther(EntityPlayerMP var1, EntityPlayerMP var2, World var3) {
      if (var2 != null) {
         var1.theItemInWorldManager.setGameType(var2.theItemInWorldManager.getGameType());
      } else if (this.gameType != null) {
         var1.theItemInWorldManager.setGameType(this.gameType);
      }

      var1.theItemInWorldManager.initializeGameType(var3.P().getGameType());
   }

   public void saveAllPlayerData() {
      for (int var1 = 0; var1 < this.playerEntityList.size(); var1++) {
         this.writePlayerData(this.playerEntityList.get(var1));
      }
   }

   public void addOp(GameProfile var1) {
      this.ops.addEntry(new UserListOpsEntry(var1, this.mcServer.getOpPermissionLevel(), this.ops.bypassesPlayerLimit(var1)));
   }

   public void addWhitelistedPlayer(GameProfile var1) {
      this.whiteListedPlayers.addEntry(new UserListWhitelistEntry(var1));
   }

   public String[] getAvailablePlayerDat() {
      return this.mcServer.worldServers[0].O().getPlayerNBTManager().getAvailablePlayerDat();
   }

   public String[] getWhitelistedPlayerNames() {
      return this.whiteListedPlayers.getKeys();
   }

   public void playerLoggedOut(EntityPlayerMP var1) {
      var1.triggerAchievement(StatList.leaveGameStat);
      this.writePlayerData(var1);
      WorldServer var2 = var1.getServerForPlayer();
      if (var1.m != null) {
         var2.f(var1.m);
         logger.debug("removing player mount");
      }

      var2.removeEntity(var1);
      var2.getPlayerManager().removePlayer(var1);
      this.playerEntityList.remove(var1);
      UUID var3 = var1.aK();
      EntityPlayerMP var4 = this.uuidToPlayerMap.get(var3);
      if (var4 == var1) {
         this.uuidToPlayerMap.remove(var3);
         this.playerStatFiles.remove(var3);
      }

      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem$Action.REMOVE_PLAYER, var1));
   }

   public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP var1, int var2, boolean var3) {
      var1.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(var1);
      var1.getServerForPlayer().getEntityTracker().untrackEntity(var1);
      var1.getServerForPlayer().getPlayerManager().removePlayer(var1);
      this.playerEntityList.remove(var1);
      this.mcServer.worldServerForDimension(var1.am).f(var1);
      BlockPos var4 = var1.getBedLocation();
      boolean var5 = var1.isSpawnForced();
      var1.am = var2;
      Object var6;
      if (this.mcServer.isDemo()) {
         var6 = new DemoWorldManager(this.mcServer.worldServerForDimension(var1.am));
      } else {
         var6 = new ItemInWorldManager(this.mcServer.worldServerForDimension(var1.am));
      }

      EntityPlayerMP var7 = new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(var1.am), var1.getGameProfile(), (ItemInWorldManager)var6);
      var7.playerNetServerHandler = var1.playerNetServerHandler;
      var7.clonePlayer(var1, var3);
      var7.setEntityId(var1.F());
      var7.setCommandStats(var1);
      WorldServer var8 = this.mcServer.worldServerForDimension(var1.am);
      this.setPlayerGameTypeBasedOnOther(var7, var1, var8);
      if (var4 != null) {
         BlockPos var9 = EntityPlayer.getBedSpawnLocation(this.mcServer.worldServerForDimension(var1.am), var4, var5);
         if (var9 != null) {
            var7.a_(var9.getX() + 0.5F, var9.getY() + 0.1F, var9.getZ() + 0.5F, 0.0F, 0.0F);
            var7.setSpawnPoint(var4, var5);
         } else {
            var7.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(0, 0.0F));
         }
      }

      var8.theChunkProviderServer.loadChunk((int)var7.s >> 4, (int)var7.u >> 4);

      while (!var8.a(var7, var7.getEntityBoundingBox()).isEmpty() && var7.t < 256.0) {
         var7.b(var7.s, var7.t + 1.0, var7.u);
      }

      var7.playerNetServerHandler
         .sendPacket(new S07PacketRespawn(var7.am, var7.o.getDifficulty(), var7.o.P().getTerrainType(), var7.theItemInWorldManager.getGameType()));
      BlockPos var10 = var8.M();
      var7.playerNetServerHandler.setPlayerLocation(var7.s, var7.t, var7.u, var7.y, var7.z);
      var7.playerNetServerHandler.sendPacket(new S05PacketSpawnPosition(var10));
      var7.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(var7.bD, var7.bC, var7.bB));
      this.updateTimeAndWeatherForPlayer(var7, var8);
      var8.getPlayerManager().addPlayer(var7);
      var8.spawnEntityInWorld(var7);
      this.playerEntityList.add(var7);
      this.uuidToPlayerMap.put(var7.aK(), var7);
      var7.addSelfToInternalCraftingInventory();
      var7.setHealth(var7.getHealth());
      return var7;
   }

   public void sendToAllNearExcept(EntityPlayer var1, double var2, double var4, double var6, double var8, int var10, Packet var11) {
      for (int var12 = 0; var12 < this.playerEntityList.size(); var12++) {
         EntityPlayerMP var13 = this.playerEntityList.get(var12);
         if (var13 != var1 && var13.am == var10) {
            double var14 = var2 - var13.s;
            double var16 = var4 - var13.t;
            double var18 = var6 - var13.u;
            if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
               var13.playerNetServerHandler.sendPacket(var11);
            }
         }
      }
   }

   public void method_29348(EntityPlayer var1, IChatComponent var2) {
      Team var3 = var1.getTeam();
      if (var3 == null) {
         this.sendChatMsg(var2);
      } else {
         for (int var4 = 0; var4 < this.playerEntityList.size(); var4++) {
            EntityPlayerMP var5 = this.playerEntityList.get(var4);
            if (var5.getTeam() != var3) {
               var5.addChatMessage(var2);
            }
         }
      }
   }

   public boolean bypassesPlayerLimit(GameProfile var1) {
      return false;
   }

   public EntityPlayerMP createPlayerForUser(GameProfile var1) {
      UUID var2 = EntityPlayer.getUUID(var1);
      ArrayList var3 = Lists.newArrayList();

      for (int var4 = 0; var4 < this.playerEntityList.size(); var4++) {
         EntityPlayerMP var5 = this.playerEntityList.get(var4);
         if (var5.aK().equals(var2)) {
            var3.add(var5);
         }
      }

      EntityPlayerMP var7 = this.uuidToPlayerMap.get(var1.getId());
      if (var7 != null && !var3.contains(var7)) {
         var3.add(var7);
      }

      for (EntityPlayerMP var6 : var3) {
         var6.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
      }

      Object var9;
      if (this.mcServer.isDemo()) {
         var9 = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
      } else {
         var9 = new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
      }

      return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), var1, (ItemInWorldManager)var9);
   }

   public void sendPacketToAllPlayers(Packet var1) {
      for (int var2 = 0; var2 < this.playerEntityList.size(); var2++) {
         this.playerEntityList.get(var2).playerNetServerHandler.sendPacket(var1);
      }
   }

   public void onTick() {
      if (++this.playerPingIndex > 600) {
         this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem$Action.UPDATE_LATENCY, this.playerEntityList));
         this.playerPingIndex = 0;
      }
   }

   public String[] getOppedPlayerNames() {
      return this.ops.getKeys();
   }

   public void method_29379(EntityPlayer var1, IChatComponent var2) {
      Team var3 = var1.getTeam();
      if (var3 != null) {
         for (String var5 : var3.getMembershipCollection()) {
            EntityPlayerMP var6 = this.getPlayerByUsername(var5);
            if (var6 != null && var6 != var1) {
               var6.addChatMessage(var2);
            }
         }
      }
   }

   public UserListWhitelist getWhitelistedPlayers() {
      return this.whiteListedPlayers;
   }

   public UserListBans getBannedPlayers() {
      return this.bannedPlayers;
   }

   public GameProfile[] getAllProfiles() {
      GameProfile[] var1 = new GameProfile[this.playerEntityList.size()];

      for (int var2 = 0; var2 < this.playerEntityList.size(); var2++) {
         var1[var2] = this.playerEntityList.get(var2).getGameProfile();
      }

      return var1;
   }

   public void setCommandsAllowedForAll(boolean var1) {
      this.commandsAllowedForAll = var1;
   }

   public void sendChatMsg(IChatComponent var1) {
      this.sendChatMsgImpl(var1, true);
   }

   public void serverUpdateMountedMovingPlayer(EntityPlayerMP var1) {
      var1.getServerForPlayer().getPlayerManager().updateMountedMovingPlayer(var1);
   }

   public void setViewDistance(int var1) {
      this.viewDistance = var1;
      if (this.mcServer.worldServers != null) {
         for (WorldServer var5 : this.mcServer.worldServers) {
            if (var5 != null) {
               var5.getPlayerManager().setPlayerViewRadius(var1);
            }
         }
      }
   }

   public void writePlayerData(EntityPlayerMP var1) {
      this.playerNBTManagerObj.writePlayerData(var1);
      StatisticsFile var2 = this.playerStatFiles.get(var1.aK());
      if (var2 != null) {
         var2.saveStatFile();
      }
   }

   public MinecraftServer getServerInstance() {
      return this.mcServer;
   }

   public void removePlayerFromWhitelist(GameProfile var1) {
      this.whiteListedPlayers.removeEntry(var1);
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public int getCurrentPlayerCount() {
      return this.playerEntityList.size();
   }

   public String func_181058_b(boolean var1) {
      String var2 = "";
      ArrayList var3 = Lists.newArrayList(this.playerEntityList);

      for (int var4 = 0; var4 < var3.size(); var4++) {
         if (var4 > 0) {
            var2 = var2 + ", ";
         }

         var2 = var2 + ((EntityPlayerMP)var3.get(var4)).z_();
         if (var1) {
            var2 = var2 + " (" + ((EntityPlayerMP)var3.get(var4)).aK().toString() + ")";
         }
      }

      return var2;
   }

   public void transferEntityToWorld(Entity var1, int var2, WorldServer var3, WorldServer var4) {
      double var5 = var1.s;
      double var7 = var1.u;
      double var9 = 8.0;
      float var11 = var1.y;
      var3.B.startSection("moving");
      if (var1.am == -1) {
         var5 = MathHelper.clamp_double(var5 / var9, var4.af().minX() + 16.0, var4.af().maxX() - 16.0);
         var7 = MathHelper.clamp_double(var7 / var9, var4.af().minZ() + 16.0, var4.af().maxZ() - 16.0);
         var1.a_(var5, var1.t, var7, var1.y, var1.z);
         if (var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      } else if (var1.am == 0) {
         var5 = MathHelper.clamp_double(var5 * var9, var4.af().minX() + 16.0, var4.af().maxX() - 16.0);
         var7 = MathHelper.clamp_double(var7 * var9, var4.af().minZ() + 16.0, var4.af().maxZ() - 16.0);
         var1.a_(var5, var1.t, var7, var1.y, var1.z);
         if (var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      } else {
         BlockPos var12;
         if (var2 == 1) {
            var12 = var4.M();
         } else {
            var12 = var4.getSpawnCoordinate();
         }

         var5 = var12.getX();
         var1.t = var12.getY();
         var7 = var12.getZ();
         var1.a_(var5, var1.t, var7, 90.0F, 0.0F);
         if (var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      }

      var3.B.endSection();
      if (var2 != 1) {
         var3.B.startSection("placing");
         var5 = MathHelper.clamp_int((int)var5, -29999872, 29999872);
         var7 = MathHelper.clamp_int((int)var7, -29999872, 29999872);
         if (var1.isEntityAlive()) {
            var1.a_(var5, var1.t, var7, var1.y, var1.z);
            var4.getDefaultTeleporter().placeInPortal(var1, var11);
            var4.spawnEntityInWorld(var1);
            var4.updateEntityWithOptionalForce(var1, false);
         }

         var3.B.endSection();
      }

      var1.setWorld(var4);
   }

   public void removeAllPlayers() {
      for (int var1 = 0; var1 < this.playerEntityList.size(); var1++) {
         this.playerEntityList.get(var1).playerNetServerHandler.kickPlayerFromServer("Server closed");
      }
   }

   public void sendScoreboard(ServerScoreboard var1, EntityPlayerMP var2) {
      HashSet var3 = Sets.newHashSet();

      for (ScorePlayerTeam var5 : var1.getTeams()) {
         var2.playerNetServerHandler.sendPacket(new S3EPacketTeams(var5, 0));
      }

      for (int var8 = 0; var8 < 19; var8++) {
         ScoreObjective var9 = var1.getObjectiveInDisplaySlot(var8);
         if (var9 != null && !var3.contains(var9)) {
            for (Packet var7 : var1.func_96550_d(var9)) {
               var2.playerNetServerHandler.sendPacket(var7);
            }

            var3.add(var9);
         }
      }
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.gameType = var1;
   }

   public EntityPlayerMP getPlayerByUUID(UUID var1) {
      return this.uuidToPlayerMap.get(var1);
   }

   public void removeOp(GameProfile var1) {
      this.ops.removeEntry(var1);
   }

   public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP var1) {
      NBTTagCompound var2 = this.mcServer.worldServers[0].P().getPlayerNBTTagCompound();
      NBTTagCompound var3;
      if (var1.z_().equals(this.mcServer.getServerOwner()) && var2 != null) {
         var1.f(var2);
         var3 = var2;
         logger.debug("loading single player");
      } else {
         var3 = this.playerNBTManagerObj.readPlayerData(var1);
      }

      return var3;
   }

   public void setWhiteListEnabled(boolean var1) {
      this.whiteListEnforced = var1;
   }

   public void initializeConnectionToPlayer(NetworkManager var1, EntityPlayerMP var2) {
      GameProfile var3 = var2.getGameProfile();
      PlayerProfileCache var4 = this.mcServer.getPlayerProfileCache();
      GameProfile var5 = var4.getProfileByUUID(var3.getId());
      String var6 = var5 == null ? var3.getName() : var5.getName();
      var4.addEntry(var3);
      NBTTagCompound var7 = this.readPlayerDataFromFile(var2);
      var2.setWorld(this.mcServer.worldServerForDimension(var2.am));
      var2.theItemInWorldManager.setWorld((WorldServer)var2.o);
      String var8 = "local";
      if (var1.getRemoteAddress() != null) {
         var8 = var1.getRemoteAddress().toString();
      }

      logger.info(var2.z_() + "[" + var8 + "] logged in with entity id " + var2.F() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
      WorldServer var9 = this.mcServer.worldServerForDimension(var2.am);
      WorldInfo var10 = var9.P();
      BlockPos var11 = var9.M();
      this.setPlayerGameTypeBasedOnOther(var2, (EntityPlayerMP)null, var9);
      NetHandlerPlayServer var12 = new NetHandlerPlayServer(this.mcServer, var1, var2);
      var12.sendPacket(
         new S01PacketJoinGame(
            var2.F(),
            var2.theItemInWorldManager.getGameType(),
            var10.isHardcoreModeEnabled(),
            var9.t.getDimensionId(),
            var9.getDifficulty(),
            this.getMaxPlayers(),
            var10.getTerrainType(),
            var9.Q().getBoolean("reducedDebugInfo")
         )
      );
      var12.sendPacket(new S3FPacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(this.getServerInstance().getServerModName())));
      var12.sendPacket(new S41PacketServerDifficulty(var10.getDifficulty(), var10.isDifficultyLocked()));
      var12.sendPacket(new S05PacketSpawnPosition(var11));
      var12.sendPacket(new S39PacketPlayerAbilities(var2.bA));
      var12.sendPacket(new S09PacketHeldItemChange(var2.bi.currentItem));
      var2.getStatFile().func_150877_d();
      var2.getStatFile().sendAchievements(var2);
      this.sendScoreboard((ServerScoreboard)var9.Z(), var2);
      this.mcServer.refreshStatusNextTick();
      ChatComponentTranslation var13;
      if (!var2.z_().equalsIgnoreCase(var6)) {
         var13 = new ChatComponentTranslation("multiplayer.player.joined.renamed", var2.getDisplayName(), var6);
      } else {
         var13 = new ChatComponentTranslation("multiplayer.player.joined", var2.getDisplayName());
      }

      var13.getChatStyle().setColor(EnumChatFormatting.YELLOW);
      this.sendChatMsg(var13);
      this.playerLoggedIn(var2);
      var12.setPlayerLocation(var2.s, var2.t, var2.u, var2.y, var2.z);
      this.updateTimeAndWeatherForPlayer(var2, var9);
      if (this.mcServer.getResourcePackUrl().length() > 0) {
         var2.loadResourcePack(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
      }

      for (PotionEffect var15 : var2.getActivePotionEffects()) {
         var12.sendPacket(new S1DPacketEntityEffect(var2.F(), var15));
      }

      var2.addSelfToInternalCraftingInventory();
      if (var7 != null && var7.hasKey("Riding", 10)) {
         Entity var16 = EntityList.createEntityFromNBT(var7.getCompoundTag("Riding"), var9);
         if (var16 != null) {
            var16.n = true;
            var9.spawnEntityInWorld(var16);
            var2.mountEntity(var16);
            var16.n = false;
         }
      }
   }

   public int getEntityViewDistance() {
      return PlayerManager.getFurthestViewableBlock(this.getViewDistance());
   }

   public boolean canJoin(GameProfile var1) {
      return !this.whiteListEnforced || this.ops.hasEntry(var1) || this.whiteListedPlayers.hasEntry(var1);
   }

   public void sendPacketToAllPlayersInDimension(Packet var1, int var2) {
      for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
         EntityPlayerMP var4 = this.playerEntityList.get(var3);
         if (var4.am == var2) {
            var4.playerNetServerHandler.sendPacket(var1);
         }
      }
   }

   public StatisticsFile getPlayerStatsFile(EntityPlayer var1) {
      UUID var2 = var1.aK();
      StatisticsFile var3 = var2 == null ? null : this.playerStatFiles.get(var2);
      if (var3 == null) {
         File var4 = new File(this.mcServer.worldServerForDimension(0).O().getWorldDirectory(), "stats");
         File var5 = new File(var4, var2.toString() + ".json");
         if (!var5.exists()) {
            File var6 = new File(var4, var1.z_() + ".json");
            if (var6.exists() && var6.isFile()) {
               var6.renameTo(var5);
            }
         }

         var3 = new StatisticsFile(this.mcServer, var5);
         var3.readStatFile();
         this.playerStatFiles.put(var2, var3);
      }

      return var3;
   }

   public void loadWhiteList() {
   }

   public ServerConfigurationManager(MinecraftServer var1) {
      this.uuidToPlayerMap = Maps.newHashMap();
      this.bannedPlayers = new UserListBans(FILE_PLAYERBANS);
      this.bannedIPs = new BanList(FILE_IPBANS);
      this.ops = new UserListOps(FILE_OPS);
      this.whiteListedPlayers = new UserListWhitelist(FILE_WHITELIST);
      this.playerStatFiles = Maps.newHashMap();
      this.mcServer = var1;
      this.bannedPlayers.setLanServer(false);
      this.bannedIPs.setLanServer(false);
      this.maxPlayers = 8;
   }

   public UserListOps getOppedPlayers() {
      return this.ops;
   }

   public int getViewDistance() {
      return this.viewDistance;
   }

   public void playerLoggedIn(EntityPlayerMP var1) {
      this.playerEntityList.add(var1);
      this.uuidToPlayerMap.put(var1.aK(), var1);
      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem$Action.ADD_PLAYER, var1));
      WorldServer var2 = this.mcServer.worldServerForDimension(var1.am);
      var2.spawnEntityInWorld(var1);
      this.preparePlayer(var1, (WorldServer)null);

      for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
         EntityPlayerMP var4 = this.playerEntityList.get(var3);
         var1.playerNetServerHandler.sendPacket(new S38PacketPlayerListItem(S38PacketPlayerListItem$Action.ADD_PLAYER, var4));
      }
   }

   public List<EntityPlayerMP> getPlayerList() {
      return this.playerEntityList;
   }
}
