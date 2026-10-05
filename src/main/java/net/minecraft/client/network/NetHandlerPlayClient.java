package net.minecraft.client.network;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.PluginMessageEvent;
import com.cheatbreaker.client.module.type.ChatModule;
import com.cheatbreaker.client.module.type.ComboCounterModule;
import com.cheatbreaker.client.module.type.SprintResetCounterModule;
import com.cheatbreaker.client.network.CustomPayloadSender;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.client.stream.MetadataPlayerDeath;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.realms.DisconnectedRealmsScreen;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.network.ServerResourcePackDownloadCallback;
import net.minecraft.client.network.LocalResourcePackLoadCallback;

public class NetHandlerPlayClient implements INetHandlerPlayClient {
   public GuiScreen guiScreenServer;
   public boolean doneLoadingTerrain;
   public boolean field_147308_k;
   public Minecraft gameController;
   public Random avRandomizer;
   public GameProfile profile;
   public NetworkManager netManager;
   public Map<UUID, NetworkPlayerInfo> playerInfoMap = Maps.newHashMap();
   public int currentServerMaxPlayers = 20;
   public static Logger logger = LogManager.getLogger();
   public WorldClient clientWorldController;

   public GameProfile getGameProfile() {
      return this.profile;
   }

   @Override
   public void handleUpdateTileEntity(S35PacketUpdateTileEntity var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (this.gameController.theWorld.e(var1.getPos())) {
         TileEntity var2 = this.gameController.theWorld.getTileEntity(var1.getPos());
         int var3 = var1.getTileEntityType();
         if (var3 == 1 && var2 instanceof TileEntityMobSpawner
            || var3 == 2 && var2 instanceof TileEntityCommandBlock
            || var3 == 3 && var2 instanceof TileEntityBeacon
            || var3 == 4 && var2 instanceof TileEntitySkull
            || var3 == 5 && var2 instanceof TileEntityFlowerPot
            || var3 == 6 && var2 instanceof TileEntityBanner) {
            var2.readFromNBT(var1.getNbtCompound());
         }
      }
   }

   @Override
   public void handleUpdateSign(S33PacketUpdateSign var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      boolean var2 = false;
      if (this.gameController.theWorld.e(var1.getPos())) {
         TileEntity var3 = this.gameController.theWorld.getTileEntity(var1.getPos());
         if (var3 instanceof TileEntitySign) {
            TileEntitySign var4 = (TileEntitySign)var3;
            if (var4.getIsEditable()) {
               System.arraycopy(var1.getLines(), 0, var4.signText, 0, 4);
               var4.markDirty();
            }

            var2 = true;
         }
      }

      if (!var2 && this.gameController.thePlayer != null) {
         this.gameController
            .thePlayer
            .addChatMessage(
               new ChatComponentText("Unable to locate sign at " + var1.getPos().getX() + ", " + var1.getPos().getY() + ", " + var1.getPos().getZ())
            );
      }
   }

   @Override
   public void handleEffect(S28PacketEffect var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.isSoundServerwide()) {
         this.gameController.theWorld.playBroadcastSound(var1.getSoundType(), var1.getSoundPos(), var1.getSoundData());
      } else {
         this.gameController.theWorld.b(var1.getSoundType(), var1.getSoundPos(), var1.getSoundData());
      }
   }

   @Override
   public void handleExplosion(S27PacketExplosion var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Explosion var2 = new Explosion(
         this.gameController.theWorld, (Entity)null, var1.getX(), var1.getY(), var1.getZ(), var1.getStrength(), var1.getAffectedBlockPositions()
      );
      var2.doExplosionB(true);
      this.gameController.thePlayer.v = this.gameController.thePlayer.v + var1.func_149149_c();
      this.gameController.thePlayer.w = this.gameController.thePlayer.w + var1.func_149144_d();
      this.gameController.thePlayer.x = this.gameController.thePlayer.x + var1.func_149147_e();
   }

   @Override
   public void handleTeams(S3EPacketTeams var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Scoreboard var2 = this.clientWorldController.Z();
      ScorePlayerTeam var3;
      if (var1.getAction() == 0) {
         var3 = var2.createTeam(var1.getName());
      } else {
         var3 = var2.getTeam(var1.getName());
      }

      if ((var1.getAction() == 0 || var1.getAction() == 2) && var3 != null) {
         var3.setTeamName(var1.getDisplayName());
         var3.setNamePrefix(var1.getPrefix());
         var3.setNameSuffix(var1.getSuffix());
         var3.setChatFormat(EnumChatFormatting.func_175744_a(var1.getColor()));
         var3.func_98298_a(var1.getFriendlyFlags());
         Team.EnumVisible var4 = Team.EnumVisible.func_178824_a(var1.getNameTagVisibility());
         if (var4 != null) {
            var3.setNameTagVisibility(var4);
         }
      }

      if (var1.getAction() == 0 || var1.getAction() == 3) {
         for (String var5 : var1.getPlayers()) {
            var2.addPlayerToTeam(var5, var1.getName());
         }
      }

      if (var1.getAction() == 4) {
         for (String var8 : var1.getPlayers()) {
            var2.removePlayerFromTeam(var8, var3);
         }
      }

      if (var1.getAction() == 1) {
         var2.method_25157(var3);
      }
   }

   public void cleanup() {
      this.clientWorldController = null;
   }

   @Override
   public void handleChat(S02PacketChat var1) {
      ChatModule var2 = CheatBreaker.getInstance().getModuleManager().chatModule;
      if (var1.getChatComponent().getUnformattedText().contains(Minecraft.getMinecraft().getSession().getUsername())
         && var2.isEnabled()
         && var2.recoveredField825.method_08908()
         && var2.recoveredField836.method_08908()
         && !CheatBreaker.getInstance().getGlobalSettings().recoveredField549.method_08908()
         && var2.recoveredField826.size() < 1) {
         CheatBreaker.getInstance().method_19741().method_26702("message");
         CheatBreaker.getInstance().getModuleManager().chatModule.recoveredField826.add(System.currentTimeMillis());
      }

      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.getType() == 2) {
         this.gameController.ingameGUI.setRecordPlaying(var1.getChatComponent(), false);
      } else {
         this.gameController.ingameGUI.getChatGUI().printChatMessage(var1.getChatComponent());
      }
   }

   @Override
   public void handleSignEditorOpen(S36PacketSignEditorOpen var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Object var2 = this.clientWorldController.getTileEntity(var1.getSignPosition());
      if (!(var2 instanceof TileEntitySign)) {
         var2 = new TileEntitySign();
         ((TileEntity)var2).setWorldObj(this.clientWorldController);
         ((TileEntity)var2).setPos(var1.getSignPosition());
      }

      this.gameController.thePlayer.openEditSign((TileEntitySign)var2);
   }

   public Collection<NetworkPlayerInfo> getPlayerInfoMap() {
      return this.playerInfoMap.values();
   }

   @Override
   public void handleSetSlot(S2FPacketSetSlot var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      if (var1.func_149175_c() == -1) {
         var2.bi.setItemStack(var1.func_149174_e());
      } else {
         boolean var3 = false;
         if (this.gameController.currentScreen instanceof GuiContainerCreative) {
            GuiContainerCreative var4 = (GuiContainerCreative)this.gameController.currentScreen;
            var3 = var4.getSelectedTabIndex() != CreativeTabs.tabInventory.getTabIndex();
         }

         if (var1.func_149175_c() == 0 && var1.func_149173_d() >= 36 && var1.func_149173_d() < 45) {
            ItemStack var5 = var2.bj.a(var1.func_149173_d()).getStack();
            if (var1.func_149174_e() != null && (var5 == null || var5.stackSize < var1.func_149174_e().stackSize)) {
               var1.func_149174_e().animationsToGo = 5;
            }

            var2.bj.putStackInSlot(var1.func_149173_d(), var1.func_149174_e());
         } else if (var1.func_149175_c() == var2.bk.d && (var1.func_149175_c() != 0 || !var3)) {
            var2.bk.putStackInSlot(var1.func_149173_d(), var1.func_149174_e());
         }
      }
   }

   @Override
   public void handleEntityNBT(S49PacketUpdateEntityNBT var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = var1.getEntity(this.clientWorldController);
      if (var2 != null) {
         var2.clientUpdateEntityNBT(var1.getTagCompound());
      }
   }

   @Override
   public void handlePlayerListItem(S38PacketPlayerListItem var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);

      for (S38PacketPlayerListItem.AddPlayerData var3 : var1.getEntries()) {
         if (var1.getAction() == S38PacketPlayerListItem.Action.REMOVE_PLAYER) {
            this.playerInfoMap.remove(var3.getProfile().getId());
         } else {
            NetworkPlayerInfo var4 = this.playerInfoMap.get(var3.getProfile().getId());
            if (var1.getAction() == S38PacketPlayerListItem.Action.ADD_PLAYER) {
               var4 = new NetworkPlayerInfo(var3);
               this.playerInfoMap.put(var4.getGameProfile().getId(), var4);
            }

            if (var4 != null) {
               switch (var1.getAction()) {
                  case ADD_PLAYER:
                     var4.setGameType(var3.getGameMode());
                     var4.setResponseTime(var3.getPing());
                     break;
                  case UPDATE_GAME_MODE:
                     var4.setGameType(var3.getGameMode());
                     break;
                  case UPDATE_LATENCY:
                     var4.setResponseTime(var3.getPing());
                     break;
                  case UPDATE_DISPLAY_NAME:
                     var4.setDisplayName(var3.getDisplayName());
               }
            }
         }
      }
   }

   @Override
   public void handleEntityHeadLook(S19PacketEntityHeadLook var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = var1.getEntity(this.clientWorldController);
      if (var2 != null) {
         float var3 = var1.getYaw() * 360 / 256.0F;
         var2.setRotationYawHead(var3);
      }
   }

   @Override
   public void handleScoreboardObjective(S3BPacketScoreboardObjective var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Scoreboard var2 = this.clientWorldController.Z();
      if (var1.func_149338_e() == 0) {
         ScoreObjective var3 = var2.addScoreObjective(var1.func_149339_c(), IScoreObjectiveCriteria.DUMMY);
         var3.setDisplayName(var1.func_149337_d());
         var3.setRenderType(var1.func_179817_d());
      } else {
         ScoreObjective var4 = var2.getObjective(var1.func_149339_c());
         if (var1.func_149338_e() == 1) {
            var2.removeObjective(var4);
         } else if (var1.func_149338_e() == 2) {
            var4.setDisplayName(var1.func_149337_d());
            var4.setRenderType(var1.func_179817_d());
         }
      }
   }

   @Override
   public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter var1) {
      this.gameController.ingameGUI.getTabList().setHeader(var1.getHeader().getFormattedText().length() == 0 ? null : var1.getHeader());
      this.gameController.ingameGUI.getTabList().setFooter(var1.getFooter().getFormattedText().length() == 0 ? null : var1.getFooter());
   }

   @Override
   public void handleSpawnPainting(S10PacketSpawnPainting var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPainting var2 = new EntityPainting(this.clientWorldController, var1.getPosition(), var1.getFacing(), var1.getTitle());
      this.clientWorldController.addEntityToWorld(var1.getEntityID(), var2);
   }

   @Override
   public void handleKeepAlive(S00PacketKeepAlive var1) {
      this.addToSendQueue(new C00PacketKeepAlive(var1.func_149134_c()));
   }

   @Override
   public void onDisconnect(IChatComponent var1) {
      this.gameController.loadWorld((WorldClient)null);
      CheatBreaker.getInstance().method_19817().method_21935(new DisconnectEvent());
      if (this.guiScreenServer != null) {
         if (this.guiScreenServer instanceof GuiScreenRealmsProxy) {
            this.gameController
               .displayGuiScreen(new DisconnectedRealmsScreen(((GuiScreenRealmsProxy)this.guiScreenServer).func_154321_a(), "disconnect.lost", var1).getProxy());
         } else {
            this.gameController.displayGuiScreen(new GuiDisconnected(this.guiScreenServer, "disconnect.lost", var1));
         }
      } else {
         this.gameController.displayGuiScreen(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", var1));
      }
   }

   @Override
   public void handleSetExperience(S1FPacketSetExperience var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.thePlayer.setXPStats(var1.func_149397_c(), var1.getTotalExperience(), var1.getLevel());
   }

   @Override
   public void handleSpawnPosition(S05PacketSpawnPosition var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.thePlayer.setSpawnPoint(var1.getSpawnPos(), true);
      this.gameController.theWorld.P().setSpawn(var1.getSpawnPos());
   }

   @Override
   public void handleMaps(S34PacketMaps var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      MapData var2 = ItemMap.loadMapData(var1.getMapId(), this.gameController.theWorld);
      var1.setMapdataTo(var2);
      this.gameController.entityRenderer.getMapItemRenderer().updateMapTexture(var2);
   }

   public void addToSendQueue(Packet var1) {
      if (var1 instanceof C02PacketUseEntity) {
         Entity var2 = ((C02PacketUseEntity)var1).getEntityFromWorld(this.gameController.theWorld);
         if (var2 != null && ((C02PacketUseEntity)var1).getAction() == C02PacketUseEntity.Action.ATTACK) {
            ComboCounterModule.recoveredField644 = var2.F();
            ComboCounterModule.recoveredField1835 = System.currentTimeMillis();
            SprintResetCounterModule.recoveredField689 = var2.F();
            SprintResetCounterModule.recoveredField1835 = System.currentTimeMillis();
         }
      }

      this.netManager.sendPacket(var1);
   }

   @Override
   public void handleEntityStatus(S19PacketEntityStatus var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = var1.getEntity(this.clientWorldController);
      if (var2 != null) {
         if (var1.getOpCode() == 21) {
            this.gameController.getSoundHandler().playSound(new GuardianSound((EntityGuardian)var2));
         } else {
            var2.handleStatusUpdate(var1.getOpCode());
            if (var1.getOpCode() != 2) {
               return;
            }

            Minecraft var3 = Minecraft.getMinecraft();
            EntityPlayerSP var4 = var3.thePlayer;
            if (ComboCounterModule.recoveredField644 == var2.F()) {
               ComboCounterModule.recoveredField644 = -1;
               if (ComboCounterModule.recoveredField643 != var2.F()) {
                  ComboCounterModule.recoveredField645.clear();
               }

               ComboCounterModule.recoveredField645.add(System.currentTimeMillis());
               ComboCounterModule.recoveredField642 = System.currentTimeMillis();
               ComboCounterModule.recoveredField643 = var2.F();
            } else if (var2.F() == var4.F()) {
               ComboCounterModule.recoveredField645.clear();
            }

            if (SprintResetCounterModule.recoveredField689 == var2.F()) {
               SprintResetCounterModule.recoveredField689 = -1;
               if (SprintResetCounterModule.recoveredField688 != var2.F()) {
                  SprintResetCounterModule.recoveredField682.clear();
               }

               if (var3.gameSettings.keyBindForward.isKeyDown() && var4.isSprinting()) {
                  SprintResetCounterModule.recoveredField682.add(System.currentTimeMillis());
               }

               SprintResetCounterModule.recoveredField691 = System.currentTimeMillis();
               SprintResetCounterModule.recoveredField688 = var2.F();
            }
         }
      }
   }

   @Override
   public void handleMultiBlockChange(S22PacketMultiBlockChange var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);

      for (S22PacketMultiBlockChange.BlockUpdateData var5 : var1.getChangedBlocks()) {
         this.clientWorldController.invalidateRegionAndSetBlock(var5.getPos(), var5.getBlockState());
      }
   }

   @Override
   public void handleDisconnect(S40PacketDisconnect var1) {
      this.netManager.closeChannel(var1.getReason());
   }

   @Override
   public void handleWindowItems(S30PacketWindowItems var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      if (var1.func_148911_c() == 0) {
         var2.bj.putStacksInSlots(var1.getItemStacks());
      } else if (var1.func_148911_c() == var2.bk.d) {
         var2.bk.putStacksInSlots(var1.getItemStacks());
      }
   }

   @Override
   public void handleResourcePack(S48PacketResourcePackSend var1) {
      final String var2 = var1.getURL();
      final String var3 = var1.getHash();
      if (var2.startsWith("level://")) {
         String var4 = var2.substring("level://".length());
         File var5 = new File(this.gameController.mcDataDir, "saves");
         File var6 = new File(var5, var4);
         if (var6.isFile()) {
            this.netManager.sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
            Futures.addCallback(this.gameController.getResourcePackRepository().setResourcePackInstance(var6), new LocalResourcePackLoadCallback(this, var3));
         } else {
            this.netManager.sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
         }
      } else if (this.gameController.getCurrentServerData() != null
         && this.gameController.getCurrentServerData().getResourceMode() == ServerData.ServerResourceMode.ENABLED) {
         this.netManager.sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
         Futures.addCallback(this.gameController.getResourcePackRepository().downloadResourcePack(var2, var3), new ServerResourcePackDownloadCallback(this, var3));
      } else if (this.gameController.getCurrentServerData() != null
         && this.gameController.getCurrentServerData().getResourceMode() != ServerData.ServerResourceMode.PROMPT) {
         this.netManager.sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.DECLINED));
      } else {
         this.gameController
            .addScheduledTask(
               new Runnable() {
                  @Override
                  public void run() {
                     NetHandlerPlayClient.this.gameController
                        .displayGuiScreen(
                           new GuiYesNo(
                              new GuiYesNoCallback() {
                                 @Override
                                 public void confirmClicked(boolean var1, int var2x) {
                                    NetHandlerPlayClient.this.gameController = Minecraft.getMinecraft();
                                    if (var1) {
                                       if (NetHandlerPlayClient.this.gameController.getCurrentServerData() != null) {
                                          NetHandlerPlayClient.this.gameController
                                             .getCurrentServerData()
                                             .setResourceMode(ServerData.ServerResourceMode.ENABLED);
                                       }

                                       NetHandlerPlayClient.this.netManager
                                          .sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
                                       Futures.addCallback(
                                          NetHandlerPlayClient.this.gameController.getResourcePackRepository().downloadResourcePack(var2, var3),
                                          new FutureCallback<Object>() {
                                             @Override
                                             public void onFailure(Throwable var1) {
                                                NetHandlerPlayClient.this.netManager
                                                   .sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
                                             }

                                             @Override
                                             public void onSuccess(Object var1) {
                                                NetHandlerPlayClient.this.netManager
                                                   .sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
                                             }
                                          }
                                       );
                                    } else {
                                       if (NetHandlerPlayClient.this.gameController.getCurrentServerData() != null) {
                                          NetHandlerPlayClient.this.gameController
                                             .getCurrentServerData()
                                             .setResourceMode(ServerData.ServerResourceMode.DISABLED);
                                       }

                                       NetHandlerPlayClient.this.netManager
                                          .sendPacket(new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.DECLINED));
                                    }

                                    ServerList.func_147414_b(NetHandlerPlayClient.this.gameController.getCurrentServerData());
                                    NetHandlerPlayClient.this.gameController.displayGuiScreen((GuiScreen)null);
                                 }
                              },
                              I18n.format("multiplayer.texturePrompt.line1"),
                              I18n.format("multiplayer.texturePrompt.line2"),
                              0
                           )
                        );
                  }
               }
            );
      }
   }

   public NetworkManager getNetworkManager() {
      return this.netManager;
   }

   @Override
   public void handleSpawnMob(S0FPacketSpawnMob var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      double var2 = var1.getX() / 32.0;
      double var4 = var1.getY() / 32.0;
      double var6 = var1.getZ() / 32.0;
      float var8 = var1.getYaw() * 360 / 256.0F;
      float var9 = var1.getPitch() * 360 / 256.0F;
      EntityLivingBase var10 = (EntityLivingBase)EntityList.createEntityByID(var1.getEntityType(), this.gameController.theWorld);
      var10.bW = var1.getX();
      var10.bX = var1.getY();
      var10.bY = var1.getZ();
      var10.aI = var10.aK = var1.getHeadPitch() * 360 / 256.0F;
      Entity[] var11 = var10.getParts();
      if (var11 != null) {
         int var12 = var1.getEntityID() - var10.F();

         for (int var13 = 0; var13 < var11.length; var13++) {
            var11[var13].setEntityId(var11[var13].F() + var12);
         }
      }

      var10.setEntityId(var1.getEntityID());
      var10.a(var2, var4, var6, var8, var9);
      var10.v = var1.getVelocityX() / 8000.0F;
      var10.w = var1.getVelocityY() / 8000.0F;
      var10.x = var1.getVelocityZ() / 8000.0F;
      this.clientWorldController.addEntityToWorld(var1.getEntityID(), var10);
      List var14 = var1.func_149027_c();
      if (var14 != null) {
         var10.H().updateWatchedObjectsFromList(var14);
      }
   }

   @Override
   public void handleHeldItemChange(S09PacketHeldItemChange var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.getHeldItemHotbarIndex() >= 0 && var1.getHeldItemHotbarIndex() < InventoryPlayer.getHotbarSize()) {
         this.gameController.thePlayer.bi.currentItem = var1.getHeldItemHotbarIndex();
      }
   }

   @Override
   public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityXPOrb var2 = new EntityXPOrb(this.clientWorldController, var1.getX() / 32.0, var1.getY() / 32.0, var1.getZ() / 32.0, var1.getXPValue());
      var2.bW = var1.getX();
      var2.bX = var1.getY();
      var2.bY = var1.getZ();
      var2.y = 0.0F;
      var2.z = 0.0F;
      var2.setEntityId(var1.getEntityID());
      this.clientWorldController.addEntityToWorld(var1.getEntityID(), var2);
   }

   @Override
   public void handleEntityMovement(S14PacketEntity var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = var1.getEntity(this.clientWorldController);
      if (var2 != null) {
         var2.bW = var2.bW + var1.method_05392();
         var2.bX = var2.bX + var1.method_05398();
         var2.bY = var2.bY + var1.method_05397();
         double var3 = var2.bW / 32.0;
         double var5 = var2.bX / 32.0;
         double var7 = var2.bY / 32.0;
         float var9 = var1.method_05390() ? var1.method_05396() * 360 / 256.0F : var2.y;
         float var10 = var1.method_05390() ? var1.method_05393() * 360 / 256.0F : var2.z;
         var2.setPositionAndRotation2(var3, var5, var7, var9, var10, 3, false);
         var2.C = var1.method_05391();
      }
   }

   @Override
   public void handleEntityEquipment(S04PacketEntityEquipment var1) {
      if (var1 != null) {
         PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
         Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityID());
         if (var2 != null) {
            var2.setCurrentItemOrArmor(var1.getEquipmentSlot(), var1.getItemStack());
         }
      }
   }

   @Override
   public void handleServerDifficulty(S41PacketServerDifficulty var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.theWorld.P().setDifficulty(var1.getDifficulty());
      this.gameController.theWorld.P().setDifficultyLocked(var1.isDifficultyLocked());
   }

   @Override
   public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      if (var2 instanceof EntityLivingBase) {
         ((EntityLivingBase)var2).removePotionEffectClient(var1.getEffectId());
      }
   }

   @Override
   public void handleSetCompressionLevel(S46PacketSetCompressionLevel var1) {
      if (!this.netManager.isLocalChannel()) {
         this.netManager.setCompressionTreshold(var1.getThreshold());
      }
   }

   @Override
   public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      double var2 = var1.func_149051_d() / 32.0;
      double var4 = var1.func_149050_e() / 32.0;
      double var6 = var1.func_149049_f() / 32.0;
      EntityLightningBolt var8 = null;
      if (var1.func_149053_g() == 1) {
         var8 = new EntityLightningBolt(this.clientWorldController, var2, var4, var6);
      }

      if (var8 != null) {
         var8.bW = var1.func_149051_d();
         var8.bX = var1.func_149050_e();
         var8.bY = var1.func_149049_f();
         var8.y = 0.0F;
         var8.z = 0.0F;
         var8.setEntityId(var1.func_149052_c());
         this.clientWorldController.addWeatherEffect(var8);
      }
   }

   @Override
   public void handleMapChunkBulk(S26PacketMapChunkBulk var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);

      for (int var2 = 0; var2 < var1.getChunkCount(); var2++) {
         int var3 = var1.getChunkX(var2);
         int var4 = var1.getChunkZ(var2);
         this.clientWorldController.doPreChunk(var3, var4, true);
         this.clientWorldController.invalidateBlockReceiveRegion(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
         Chunk var5 = this.clientWorldController.a(var3, var4);
         var5.fillChunk(var1.getChunkBytes(var2), var1.getChunkSize(var2), true);
         this.clientWorldController.markBlockRangeForRenderUpdate(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
         if (!(this.clientWorldController.t instanceof WorldProviderSurface)) {
            var5.resetRelightChecks();
         }
      }
   }

   @Override
   public void handleRespawn(S07PacketRespawn var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.getDimensionID() != this.gameController.thePlayer.am) {
         this.doneLoadingTerrain = false;
         Scoreboard var2 = this.clientWorldController.Z();
         this.clientWorldController = new WorldClient(
            this,
            new WorldSettings(0L, var1.getGameType(), false, this.gameController.theWorld.P().isHardcoreModeEnabled(), var1.getWorldType()),
            var1.getDimensionID(),
            var1.getDifficulty(),
            this.gameController.mcProfiler
         );
         this.clientWorldController.setWorldScoreboard(var2);
         this.gameController.loadWorld(this.clientWorldController);
         this.gameController.thePlayer.am = var1.getDimensionID();
         this.gameController.displayGuiScreen(new GuiDownloadTerrain(this));
      }

      this.gameController.setDimensionAndSpawnPlayer(var1.getDimensionID());
      this.gameController.playerController.setGameType(var1.getGameType());
   }

   @Override
   public void handleEntityMetadata(S1CPacketEntityMetadata var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      if (var2 != null && var1.func_149376_c() != null) {
         var2.H().updateWatchedObjectsFromList(var1.func_149376_c());
      }
   }

   @Override
   public void handleSpawnPlayer(S0CPacketSpawnPlayer var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      double var2 = var1.getX() / 32.0;
      double var4 = var1.getY() / 32.0;
      double var6 = var1.getZ() / 32.0;
      float var8 = var1.getYaw() * 360 / 256.0F;
      float var9 = var1.getPitch() * 360 / 256.0F;
      EntityOtherPlayerMP var10 = new EntityOtherPlayerMP(this.gameController.theWorld, this.getPlayerInfo(var1.getPlayer()).getGameProfile());
      var10.p = var10.P = var10.bW = var1.getX();
      var10.q = var10.Q = var10.bX = var1.getY();
      var10.r = var10.R = var10.bY = var1.getZ();
      int var11 = var1.getCurrentItemID();
      if (var11 == 0) {
         var10.bi.mainInventory[var10.bi.currentItem] = null;
      } else {
         var10.bi.mainInventory[var10.bi.currentItem] = new ItemStack(Item.getItemById(var11), 1, 0);
      }

      var10.a(var2, var4, var6, var8, var9);
      this.clientWorldController.addEntityToWorld(var1.getEntityID(), var10);
      List var12 = var1.func_148944_c();
      if (var12 != null) {
         var10.H().updateWatchedObjectsFromList(var12);
      }
   }

   @Override
   public void handleOpenWindow(S2DPacketOpenWindow var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      if ("minecraft:container".equals(var1.getGuiId())) {
         var2.displayGUIChest(new InventoryBasic(var1.getWindowTitle(), var1.getSlotCount()));
         var2.bk.d = var1.getWindowId();
      } else if ("minecraft:villager".equals(var1.getGuiId())) {
         var2.displayVillagerTradeGui(new NpcMerchant(var2, var1.getWindowTitle()));
         var2.bk.d = var1.getWindowId();
      } else if ("EntityHorse".equals(var1.getGuiId())) {
         Entity var3 = this.clientWorldController.getEntityByID(var1.getEntityId());
         if (var3 instanceof EntityHorse) {
            var2.displayGUIHorse((EntityHorse)var3, new AnimalChest(var1.getWindowTitle(), var1.getSlotCount()));
            var2.bk.d = var1.getWindowId();
         }
      } else if (!var1.hasSlots()) {
         var2.displayGui(new LocalBlockIntercommunication(var1.getGuiId(), var1.getWindowTitle()));
         var2.bk.d = var1.getWindowId();
      } else {
         ContainerLocalMenu var4 = new ContainerLocalMenu(var1.getGuiId(), var1.getWindowTitle(), var1.getSlotCount());
         var2.displayGUIChest(var4);
         var2.bk.d = var1.getWindowId();
      }
   }

   @Override
   public void handleChangeGameState(S2BPacketChangeGameState var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      int var3 = var1.getGameState();
      float var4 = var1.func_149137_d();
      int var5 = MathHelper.floor_float(var4 + 0.5F);
      if (var3 >= 0 && var3 < S2BPacketChangeGameState.MESSAGE_NAMES.length && S2BPacketChangeGameState.MESSAGE_NAMES[var3] != null) {
         var2.addChatComponentMessage(new ChatComponentTranslation(S2BPacketChangeGameState.MESSAGE_NAMES[var3]));
      }

      if (var3 == 1) {
         this.clientWorldController.P().setRaining(true);
         this.clientWorldController.k(0.0F);
      } else if (var3 == 2) {
         this.clientWorldController.P().setRaining(false);
         this.clientWorldController.k(1.0F);
      } else if (var3 == 3) {
         this.gameController.playerController.setGameType(WorldSettings.GameType.getByID(var5));
      } else if (var3 == 4) {
         this.gameController.displayGuiScreen(new GuiWinGame());
      } else if (var3 == 5) {
         GameSettings var6 = this.gameController.gameSettings;
         if (var4 == 0.0F) {
            this.gameController.displayGuiScreen(new GuiScreenDemo());
         } else if (var4 == 101.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(
                  new ChatComponentTranslation(
                     "demo.help.movement",
                     GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()),
                     GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()),
                     GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()),
                     GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode())
                  )
               );
         } else if (var4 == 102.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(new ChatComponentTranslation("demo.help.jump", GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode())));
         } else if (var4 == 103.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(new ChatComponentTranslation("demo.help.inventory", GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode())));
         }
      } else if (var3 == 6) {
         this.clientWorldController.playSound(var2.s, var2.t + var2.getEyeHeight(), var2.u, "random.successful_hit", 0.18F, 0.45F, false);
      } else if (var3 == 7) {
         this.clientWorldController.k(var4);
      } else if (var3 == 8) {
         this.clientWorldController.i(var4);
      } else if (var3 == 10) {
         this.clientWorldController.spawnParticle(EnumParticleTypes.MOB_APPEARANCE, var2.s, var2.t, var2.u, 0.0, 0.0, 0.0);
         this.clientWorldController.playSound(var2.s, var2.t, var2.u, "mob.guardian.curse", 1.0F, 1.0F, false);
      }
   }

   @Override
   public void handleBlockChange(S23PacketBlockChange var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.clientWorldController.invalidateRegionAndSetBlock(var1.getBlockPosition(), var1.getBlockState());
   }

   public NetHandlerPlayClient(Minecraft var1, GuiScreen var2, NetworkManager var3, GameProfile var4) {
      this.field_147308_k = false;
      this.avRandomizer = new Random();
      this.gameController = var1;
      this.guiScreenServer = var2;
      this.netManager = var3;
      this.profile = var4;
   }

   @Override
   public void handleStatistics(S37PacketStatistics var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      boolean var2 = false;

      for (Entry var4 : var1.func_148974_c().entrySet()) {
         StatBase var5 = (StatBase)var4.getKey();
         int var6 = (Integer)var4.getValue();
         if (var5.isAchievement() && var6 > 0) {
            if (this.field_147308_k && this.gameController.thePlayer.getStatFileWriter().a(var5) == 0) {
               Achievement var7 = (Achievement)var5;
               this.gameController.guiAchievement.displayAchievement(var7);
               this.gameController.getTwitchStream().func_152911_a(new MetadataAchievement(var7), 0L);
               if (var5 == AchievementList.openInventory) {
                  this.gameController.gameSettings.recoveredField2671 = false;
                  this.gameController.gameSettings.saveOptions();
               }
            }

            var2 = true;
         }

         this.gameController.thePlayer.getStatFileWriter().unlockAchievement(this.gameController.thePlayer, var5, var6);
      }

      if (!this.field_147308_k && !var2 && this.gameController.gameSettings.recoveredField2671) {
         this.gameController.guiAchievement.displayUnformattedAchievement(AchievementList.openInventory);
      }

      this.field_147308_k = true;
      if (this.gameController.currentScreen instanceof IProgressMeter) {
         ((IProgressMeter)this.gameController.currentScreen).doneLoading();
      }
   }

   @Override
   public void handleBlockAction(S24PacketBlockAction var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.theWorld.addBlockEvent(var1.getBlockPosition(), var1.getBlockType(), var1.getData1(), var1.getData2());
   }

   @Override
   public void handleCombatEvent(S42PacketCombatEvent var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.field_179775_c);
      EntityLivingBase var3 = var2 instanceof EntityLivingBase ? (EntityLivingBase)var2 : null;
      if (var1.eventType == S42PacketCombatEvent.Event.END_COMBAT) {
         long var4 = 1000 * var1.field_179772_d / 20;
         MetadataCombat var6 = new MetadataCombat(this.gameController.thePlayer, var3);
         this.gameController.getTwitchStream().func_176026_a(var6, 0L - var4, 0L);
      } else if (var1.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
         Entity var7 = this.clientWorldController.getEntityByID(var1.field_179774_b);
         if (var7 instanceof EntityPlayer) {
            MetadataPlayerDeath var5 = new MetadataPlayerDeath((EntityPlayer)var7, var3);
            var5.func_152807_a(var1.deathMessage);
            this.gameController.getTwitchStream().func_152911_a(var5, 0L);
         }
      }
   }

   @Override
   public void handlePlayerPosLook(S08PacketPlayerPosLook var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      double var3 = var1.getX();
      double var5 = var1.getY();
      double var7 = var1.getZ();
      float var9 = var1.getYaw();
      float var10 = var1.getPitch();
      if (var1.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         var3 += var2.s;
      } else {
         var2.v = 0.0;
      }

      if (var1.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         var5 += var2.t;
      } else {
         var2.w = 0.0;
      }

      if (var1.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         var7 += var2.u;
      } else {
         var2.x = 0.0;
      }

      if (var1.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         var10 += var2.z;
      }

      if (var1.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         var9 += var2.y;
      }

      var2.a(var3, var5, var7, var9, var10);
      this.netManager.sendPacket(new C03PacketPlayer.C06PacketPlayerPosLook(var2.s, var2.getEntityBoundingBox().b, var2.u, var2.y, var2.z, false));
      if (!this.doneLoadingTerrain) {
         this.gameController.thePlayer.p = this.gameController.thePlayer.s;
         this.gameController.thePlayer.q = this.gameController.thePlayer.t;
         this.gameController.thePlayer.r = this.gameController.thePlayer.u;
         this.doneLoadingTerrain = true;
         this.gameController.displayGuiScreen((GuiScreen)null);
      }
   }

   @Override
   public void handleWorldBorder(S44PacketWorldBorder var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      var1.func_179788_a(this.clientWorldController.af());
   }

   @Override
   public void handleCloseWindow(S2EPacketCloseWindow var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.thePlayer.closeScreenAndDropStack();
   }

   @Override
   public void handleBlockBreakAnim(S25PacketBlockBreakAnim var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.theWorld.sendBlockBreakProgress(var1.getBreakerId(), var1.getPosition(), var1.getProgress());
   }

   @Override
   public void handleSpawnObject(S0EPacketSpawnObject var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      double var2 = var1.getX() / 32.0;
      double var4 = var1.getY() / 32.0;
      double var6 = var1.getZ() / 32.0;
      Object var8 = null;
      if (var1.getType() == 10) {
         var8 = EntityMinecart.getMinecart(this.clientWorldController, var2, var4, var6, EntityMinecart.EnumMinecartType.byNetworkID(var1.func_149009_m()));
      } else if (var1.getType() == 90) {
         Entity var9 = this.clientWorldController.getEntityByID(var1.func_149009_m());
         if (var9 instanceof EntityPlayer) {
            var8 = new EntityFishHook(this.clientWorldController, var2, var4, var6, (EntityPlayer)var9);
         }

         var1.func_149002_g(0);
      } else if (var1.getType() == 60) {
         var8 = new EntityArrow(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 61) {
         var8 = new EntitySnowball(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 71) {
         var8 = new EntityItemFrame(
            this.clientWorldController,
            new BlockPos(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6)),
            EnumFacing.getHorizontal(var1.func_149009_m())
         );
         var1.func_149002_g(0);
      } else if (var1.getType() == 77) {
         var8 = new EntityLeashKnot(
            this.clientWorldController, new BlockPos(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6))
         );
         var1.func_149002_g(0);
      } else if (var1.getType() == 65) {
         var8 = new EntityEnderPearl(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 72) {
         var8 = new EntityEnderEye(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 76) {
         var8 = new EntityFireworkRocket(this.clientWorldController, var2, var4, var6, (ItemStack)null);
      } else if (var1.getType() == 63) {
         var8 = new EntityLargeFireball(
            this.clientWorldController, var2, var4, var6, var1.getSpeedX() / 8000.0, var1.getSpeedY() / 8000.0, var1.getSpeedZ() / 8000.0
         );
         var1.func_149002_g(0);
      } else if (var1.getType() == 64) {
         var8 = new EntitySmallFireball(
            this.clientWorldController, var2, var4, var6, var1.getSpeedX() / 8000.0, var1.getSpeedY() / 8000.0, var1.getSpeedZ() / 8000.0
         );
         var1.func_149002_g(0);
      } else if (var1.getType() == 66) {
         var8 = new EntityWitherSkull(
            this.clientWorldController, var2, var4, var6, var1.getSpeedX() / 8000.0, var1.getSpeedY() / 8000.0, var1.getSpeedZ() / 8000.0
         );
         var1.func_149002_g(0);
      } else if (var1.getType() == 62) {
         var8 = new EntityEgg(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 73) {
         var8 = new EntityPotion(this.clientWorldController, var2, var4, var6, var1.func_149009_m());
         var1.func_149002_g(0);
      } else if (var1.getType() == 75) {
         var8 = new EntityExpBottle(this.clientWorldController, var2, var4, var6);
         var1.func_149002_g(0);
      } else if (var1.getType() == 1) {
         var8 = new EntityBoat(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 50) {
         var8 = new EntityTNTPrimed(this.clientWorldController, var2, var4, var6, (EntityLivingBase)null);
      } else if (var1.getType() == 78) {
         var8 = new EntityArmorStand(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 51) {
         var8 = new EntityEnderCrystal(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 2) {
         var8 = new EntityItem(this.clientWorldController, var2, var4, var6);
      } else if (var1.getType() == 70) {
         var8 = new EntityFallingBlock(this.clientWorldController, var2, var4, var6, Block.getStateById(var1.func_149009_m() & 65535));
         var1.func_149002_g(0);
      }

      if (var8 != null) {
         ((Entity)var8).bW = var1.getX();
         ((Entity)var8).bX = var1.getY();
         ((Entity)var8).bY = var1.getZ();
         ((Entity)var8).z = var1.getPitch() * 360 / 256.0F;
         ((Entity)var8).y = var1.getYaw() * 360 / 256.0F;
         Entity[] var12 = ((Entity)var8).getParts();
         if (var12 != null) {
            int var10 = var1.getEntityID() - ((Entity)var8).F();

            for (int var11 = 0; var11 < var12.length; var11++) {
               var12[var11].setEntityId(var12[var11].F() + var10);
            }
         }

         ((Entity)var8).setEntityId(var1.getEntityID());
         this.clientWorldController.addEntityToWorld(var1.getEntityID(), (Entity)var8);
         if (var1.func_149009_m() > 0) {
            if (var1.getType() == 60) {
               Entity var13 = this.clientWorldController.getEntityByID(var1.func_149009_m());
               if (var13 instanceof EntityLivingBase && var8 instanceof EntityArrow) {
                  ((EntityArrow)var8).shootingEntity = var13;
               }
            }

            ((Entity)var8).setVelocity(var1.getSpeedX() / 8000.0, var1.getSpeedY() / 8000.0, var1.getSpeedZ() / 8000.0);
         }
      }
   }

   @Override
   public void handleConfirmTransaction(S32PacketConfirmTransaction var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Container var2 = null;
      EntityPlayerSP var3 = this.gameController.thePlayer;
      if (var1.getWindowId() == 0) {
         var2 = var3.bj;
      } else if (var1.getWindowId() == var3.bk.d) {
         var2 = var3.bk;
      }

      if (var2 != null && !var1.func_148888_e()) {
         this.addToSendQueue(new C0FPacketConfirmTransaction(var1.getWindowId(), var1.getActionNumber(), true));
      }
   }

   @Override
   public void handleTimeUpdate(S03PacketTimeUpdate var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.theWorld.method_09997(var1.getTotalWorldTime());
      if (CheatBreaker.getInstance().getModuleManager().recoveredField1726.recoveredField2038.getValue().equals("Server")
         || !CheatBreaker.getInstance().getModuleManager().recoveredField1726.isEnabled()) {
         this.gameController.theWorld.setWorldTime(var1.getTotalWorldTime());
      }
   }

   @Override
   public void handleTitle(S45PacketTitle var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      S45PacketTitle.Type var2 = var1.getType();
      String var3 = null;
      String var4 = null;
      String var5 = var1.getMessage() != null ? var1.getMessage().getFormattedText() : "";
      switch (var2) {
         case TITLE:
            var3 = var5;
            break;
         case SUBTITLE:
            var4 = var5;
            break;
         case RESET:
            this.gameController.ingameGUI.displayTitle("", "", -1, -1, -1);
            this.gameController.ingameGUI.setDefaultTitlesTimes();
            return;
      }

      this.gameController.ingameGUI.displayTitle(var3, var4, var1.getFadeInTime(), var1.getDisplayTime(), var1.getFadeOutTime());
   }

   @Override
   public void handleEntityEffect(S1DPacketEntityEffect var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      if (var2 instanceof EntityLivingBase) {
         PotionEffect var3 = new PotionEffect(var1.getEffectId(), var1.getDuration(), var1.getAmplifier(), false, var1.func_179707_f());
         var3.setPotionDurationMax(var1.func_149429_c());
         ((EntityLivingBase)var2).c(var3);
      }
   }

   @Override
   public void handleSoundEffect(S29PacketSoundEffect var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.theWorld.playSound(var1.getX(), var1.getY(), var1.getZ(), var1.getSoundName(), var1.getVolume(), var1.getPitch(), false);
   }

   @Override
   public void handleUpdateScore(S3CPacketUpdateScore var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Scoreboard var2 = this.clientWorldController.Z();
      ScoreObjective var3 = var2.getObjective(var1.getObjectiveName());
      if (var1.getScoreAction() == S3CPacketUpdateScore.Action.CHANGE) {
         Score var4 = var2.getValueFromObjective(var1.getPlayerName(), var3);
         var4.setScorePoints(var1.getScoreValue());
      } else if (var1.getScoreAction() == S3CPacketUpdateScore.Action.REMOVE) {
         if (StringUtils.isNullOrEmpty(var1.getObjectiveName())) {
            var2.removeObjectiveFromEntity(var1.getPlayerName(), (ScoreObjective)null);
         } else if (var3 != null) {
            var2.removeObjectiveFromEntity(var1.getPlayerName(), var3);
         }
      }
   }

   @Override
   public void handleTabComplete(S3APacketTabComplete var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      String[] var2 = var1.func_149630_c();
      if (this.gameController.currentScreen instanceof GuiChat) {
         GuiChat var3 = (GuiChat)this.gameController.currentScreen;
         var3.onAutocompleteResponse(var2);
      }
   }

   @Override
   public void handleEntityVelocity(S12PacketEntityVelocity var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityID());
      if (var2 != null) {
         var2.setVelocity(var1.getMotionX() / 8000.0, var1.getMotionY() / 8000.0, var1.getMotionZ() / 8000.0);
      }
   }

   public NetworkPlayerInfo getPlayerInfo(String var1) {
      for (NetworkPlayerInfo var3 : this.playerInfoMap.values()) {
         if (var3.getGameProfile().getName().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public void handleEntityProperties(S20PacketEntityProperties var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      if (var2 != null) {
         if (!(var2 instanceof EntityLivingBase)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + var2 + ")");
         }

         BaseAttributeMap var3 = ((EntityLivingBase)var2).getAttributeMap();

         for (S20PacketEntityProperties.Snapshot var5 : var1.func_149441_d()) {
            IAttributeInstance var6 = var3.getAttributeInstanceByName(var5.func_151409_a());
            if (var6 == null) {
               var6 = var3.registerAttribute(new RangedAttribute((IAttribute)null, var5.func_151409_a(), 0.0, Double.MIN_NORMAL, Double.MAX_VALUE));
            }

            var6.setBaseValue(var5.func_151410_b());
            var6.removeAllModifiers();

            for (AttributeModifier var8 : var5.func_151408_c()) {
               var6.applyModifier(var8);
            }
         }
      }
   }

   @Override
   public void handleUpdateHealth(S06PacketUpdateHealth var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.thePlayer.setPlayerSPHealth(var1.getHealth());
      this.gameController.thePlayer.getFoodStats().setFoodLevel(var1.getFoodLevel());
      this.gameController.thePlayer.getFoodStats().setFoodSaturationLevel(var1.getSaturationLevel());
   }

   @Override
   public void handleDestroyEntities(S13PacketDestroyEntities var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);

      for (int var2 = 0; var2 < var1.getEntityIDs().length; var2++) {
         this.clientWorldController.removeEntityFromWorld(var1.getEntityIDs()[var2]);
      }
   }

   @Override
   public void handleJoinGame(S01PacketJoinGame var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      this.gameController.playerController = new PlayerControllerMP(this.gameController, this);
      this.clientWorldController = new WorldClient(
         this,
         new WorldSettings(0L, var1.getGameType(), false, var1.isHardcoreMode(), var1.getWorldType()),
         var1.getDimension(),
         var1.getDifficulty(),
         this.gameController.mcProfiler
      );
      this.gameController.gameSettings.difficulty = var1.getDifficulty();
      this.gameController.loadWorld(this.clientWorldController);
      this.gameController.thePlayer.am = var1.getDimension();
      this.gameController.displayGuiScreen(new GuiDownloadTerrain(this));
      this.gameController.thePlayer.setEntityId(var1.getEntityId());
      this.currentServerMaxPlayers = var1.getMaxPlayers();
      this.gameController.thePlayer.setReducedDebug(var1.isReducedDebugInfo());
      this.gameController.playerController.setGameType(var1.getGameType());
      this.gameController.gameSettings.sendSettingsToServer();
      this.netManager.sendPacket(new CustomPayloadSender("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(ClientBrandRetriever.getClientModName())));
   }

   @Override
   public void handleWindowProperty(S31PacketWindowProperty var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      if (var2.bk != null && var2.bk.d == var1.getWindowId()) {
         var2.bk.updateProgressBar(var1.getVarIndex(), var1.getVarValue());
      }
   }

   @Override
   public void handleDisplayScoreboard(S3DPacketDisplayScoreboard var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Scoreboard var2 = this.clientWorldController.Z();
      if (var1.func_149370_d().length() == 0) {
         var2.setObjectiveInDisplaySlot(var1.func_149371_c(), (ScoreObjective)null);
      } else {
         ScoreObjective var3 = var2.getObjective(var1.func_149370_d());
         var2.setObjectiveInDisplaySlot(var1.func_149371_c(), var3);
      }
   }

   @Override
   public void handleEntityTeleport(S18PacketEntityTeleport var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      if (var2 != null) {
         var2.bW = var1.getX();
         var2.bX = var1.getY();
         var2.bY = var1.getZ();
         double var3 = var2.bW / 32.0;
         double var5 = var2.bX / 32.0;
         double var7 = var2.bY / 32.0;
         float var9 = var1.getYaw() * 360 / 256.0F;
         float var10 = var1.getPitch() * 360 / 256.0F;
         if (Math.abs(var2.s - var3) < 0.03125 && Math.abs(var2.t - var5) < 0.015625 && Math.abs(var2.u - var7) < 0.03125) {
            var2.setPositionAndRotation2(var2.s, var2.t, var2.u, var9, var10, 3, true);
         } else {
            var2.setPositionAndRotation2(var3, var5, var7, var9, var10, 3, true);
         }

         var2.C = var1.getOnGround();
      }
   }

   @Override
   public void handleParticles(S2APacketParticles var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.getParticleCount() == 0) {
         double var2 = var1.getParticleSpeed() * var1.getXOffset();
         double var4 = var1.getParticleSpeed() * var1.getYOffset();
         double var6 = var1.getParticleSpeed() * var1.getZOffset();

         try {
            this.clientWorldController
               .spawnParticle(
                  var1.getParticleType(),
                  var1.isLongDistance(),
                  var1.getXCoordinate(),
                  var1.getYCoordinate(),
                  var1.getZCoordinate(),
                  var2,
                  var4,
                  var6,
                  var1.getParticleArgs()
               );
         } catch (Throwable var17) {
            logger.warn("Could not spawn particle effect " + var1.getParticleType());
         }
      } else {
         for (int var18 = 0; var18 < var1.getParticleCount(); var18++) {
            double var3 = this.avRandomizer.nextGaussian() * var1.getXOffset();
            double var5 = this.avRandomizer.nextGaussian() * var1.getYOffset();
            double var7 = this.avRandomizer.nextGaussian() * var1.getZOffset();
            double var9 = this.avRandomizer.nextGaussian() * var1.getParticleSpeed();
            double var11 = this.avRandomizer.nextGaussian() * var1.getParticleSpeed();
            double var13 = this.avRandomizer.nextGaussian() * var1.getParticleSpeed();

            try {
               this.clientWorldController
                  .spawnParticle(
                     var1.getParticleType(),
                     var1.isLongDistance(),
                     var1.getXCoordinate() + var3,
                     var1.getYCoordinate() + var5,
                     var1.getZCoordinate() + var7,
                     var9,
                     var11,
                     var13,
                     var1.getParticleArgs()
                  );
            } catch (Throwable var16) {
               logger.warn("Could not spawn particle effect " + var1.getParticleType());
               return;
            }
         }
      }
   }

   @Override
   public void handleEntityAttach(S1BPacketEntityAttach var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Object var2 = this.clientWorldController.getEntityByID(var1.getEntityId());
      Entity var3 = this.clientWorldController.getEntityByID(var1.getVehicleEntityId());
      if (var1.getLeash() == 0) {
         boolean var4 = false;
         if (var1.getEntityId() == this.gameController.thePlayer.F()) {
            var2 = this.gameController.thePlayer;
            if (var3 instanceof EntityBoat) {
               ((EntityBoat)var3).setIsBoatEmpty(false);
            }

            var4 = ((Entity)var2).m == null && var3 != null;
         } else if (var3 instanceof EntityBoat) {
            ((EntityBoat)var3).setIsBoatEmpty(true);
         }

         if (var2 == null) {
            return;
         }

         ((Entity)var2).mountEntity(var3);
         if (var4) {
            GameSettings var5 = this.gameController.gameSettings;
            this.gameController
               .ingameGUI
               .setRecordPlaying(I18n.format("mount.onboard", GameSettings.getKeyDisplayString(var5.keyBindSneak.getKeyCode())), false);
         }
      } else if (var1.getLeash() == 1 && var2 instanceof EntityLiving) {
         if (var3 != null) {
            ((EntityLiving)var2).setLeashedToEntity(var3, false);
         } else {
            ((EntityLiving)var2).a(false, false);
         }
      }
   }

   @Override
   public void handleCollectItem(S0DPacketCollectItem var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getCollectedItemEntityID());
      EntityLivingBase var3 = (EntityLivingBase)this.clientWorldController.getEntityByID(var1.getEntityID());
      if (var3 == null) {
         var3 = this.gameController.thePlayer;
      }

      if (var2 != null) {
         if (var2 instanceof EntityXPOrb) {
            this.clientWorldController.a(var2, "random.orb", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         } else {
            this.clientWorldController.a(var2, "random.pop", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         }

         this.gameController.effectRenderer.addEffect(new EntityPickupFX(this.clientWorldController, var2, (Entity)var3, 0.5F));
         this.clientWorldController.removeEntityFromWorld(var1.getCollectedItemEntityID());
      }
   }

   @Override
   public void handleCustomPayload(S3FPacketCustomPayload var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      CheatBreaker.getInstance().method_19817().method_21935(new PluginMessageEvent(var1.getChannelName(), var1.getBufferData().array()));
      if ("MC|TrList".equals(var1.getChannelName())) {
         PacketBuffer var2 = var1.getBufferData();

         try {
            int var3 = var2.readInt();
            GuiScreen var4 = this.gameController.currentScreen;
            if (var4 != null && var4 instanceof GuiMerchant && var3 == this.gameController.thePlayer.bk.d) {
               IMerchant var5 = ((GuiMerchant)var4).getMerchant();
               MerchantRecipeList var6 = MerchantRecipeList.readFromBuf(var2);
               var5.setRecipes(var6);
            }
         } catch (IOException var10) {
            logger.error("Couldn't load trade info", var10);
         } finally {
            var2.release();
         }
      } else if ("MC|Brand".equals(var1.getChannelName())) {
         this.gameController.thePlayer.setClientBrand(var1.getBufferData().readStringFromBuffer(32767));
      } else if ("MC|BOpen".equals(var1.getChannelName())) {
         ItemStack var12 = this.gameController.thePlayer.getCurrentEquippedItem();
         if (var12 != null && var12.getItem() == Items.written_book) {
            this.gameController.displayGuiScreen(new GuiScreenBook(this.gameController.thePlayer, var12, false));
         }
      }
   }

   @Override
   public void handleCamera(S43PacketCamera var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = var1.getEntity(this.clientWorldController);
      if (var2 != null) {
         this.gameController.setRenderViewEntity(var2);
      }
   }

   public NetworkPlayerInfo getPlayerInfo(UUID var1) {
      return this.playerInfoMap.get(var1);
   }

   @Override
   public void handleUseBed(S0APacketUseBed var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      var1.getPlayer(this.clientWorldController).trySleep(var1.getBedPosition());
   }

   @Override
   public void handleChunkData(S21PacketChunkData var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      if (var1.func_149274_i()) {
         if (var1.getExtractedSize() == 0) {
            this.clientWorldController.doPreChunk(var1.getChunkX(), var1.getChunkZ(), false);
            return;
         }

         this.clientWorldController.doPreChunk(var1.getChunkX(), var1.getChunkZ(), true);
      }

      this.clientWorldController
         .invalidateBlockReceiveRegion(var1.getChunkX() << 4, 0, var1.getChunkZ() << 4, (var1.getChunkX() << 4) + 15, 256, (var1.getChunkZ() << 4) + 15);
      Chunk var2 = this.clientWorldController.a(var1.getChunkX(), var1.getChunkZ());
      var2.fillChunk(var1.getExtractedDataBytes(), var1.getExtractedSize(), var1.func_149274_i());
      this.clientWorldController
         .markBlockRangeForRenderUpdate(var1.getChunkX() << 4, 0, var1.getChunkZ() << 4, (var1.getChunkX() << 4) + 15, 256, (var1.getChunkZ() << 4) + 15);
      if (!var1.func_149274_i() || !(this.clientWorldController.t instanceof WorldProviderSurface)) {
         var2.resetRelightChecks();
      }
   }

   @Override
   public void handlePlayerAbilities(S39PacketPlayerAbilities var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      EntityPlayerSP var2 = this.gameController.thePlayer;
      var2.bA.isFlying = var1.isFlying();
      var2.bA.isCreativeMode = var1.isCreativeMode();
      var2.bA.disableDamage = var1.isInvulnerable();
      var2.bA.allowFlying = var1.isAllowFlying();
      var2.bA.setFlySpeed(var1.getFlySpeed());
      var2.bA.setPlayerWalkSpeed(var1.getWalkSpeed());
   }

   @Override
   public void handleAnimation(S0BPacketAnimation var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.gameController);
      Entity var2 = this.clientWorldController.getEntityByID(var1.getEntityID());
      if (var2 != null) {
         if (var1.getAnimationType() == 0) {
            EntityLivingBase var3 = (EntityLivingBase)var2;
            var3.swingItem();
         } else if (var1.getAnimationType() == 1) {
            var2.performHurtAnimation();
         } else if (var1.getAnimationType() == 2) {
            EntityPlayer var4 = (EntityPlayer)var2;
            var4.wakeUpPlayer(false, false, false);
         } else if (var1.getAnimationType() == 4) {
            if (CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled()
               && !CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1621.getValue().equals("Never")) {
               this.gameController.effectRenderer.emitParticleAtEntity(var2, EnumParticleTypes.CRIT);
            }
         } else if (var1.getAnimationType() == 5
            && CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled()
            && !CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1629.getValue().equals("Never")) {
            this.gameController.effectRenderer.emitParticleAtEntity(var2, EnumParticleTypes.CRIT_MAGIC);
         }
      }
   }
}
