package net.minecraft.entity.player;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S42PacketCombatEvent$Event;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.Team$EnumVisible;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayerMP extends EntityPlayer implements ICrafting {
   public List<ChunkCoordIntPair> loadedChunks;
   public ReadTimeoutHandler field_0022;
   public NetHandlerPlayServer playerNetServerHandler;
   public boolean chatColours;
   public int lastFoodLevel;
   public static Logger logger = LogManager.getLogger();
   public List<Integer> destroyedItemsNetCache;
   public int currentWindowId;
   public long playerLastActiveTime;
   public MinecraftServer mcServer;
   public boolean playerConqueredTheEnd;
   public double managedPosZ;
   public boolean isChangingQuantityOnly;
   public int respawnInvulnerabilityTicks;
   public String translator = "en_US";
   public float combinedHealth;
   public float lastHealth;
   public Entity spectatingEntity;
   public double managedPosX;
   public int ping;
   public StatisticsFile statsFile;
   public int lastExperience;
   public ItemInWorldManager theItemInWorldManager;
   public boolean wasHungry;
   public EntityPlayer$EnumChatVisibility chatVisibility;

   @Override
   public void sendSlotContents(Container var1, int var2, ItemStack var3) {
      if (!(var1.a(var2) instanceof SlotCrafting) && !this.isChangingQuantityOnly) {
         this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(var1.d, var2, var3));
      }
   }

   @Override
   public boolean isSpectator() {
      return this.theItemInWorldManager.getGameType() == WorldSettings$GameType.SPECTATOR;
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      if ("seed".equals(var2) && !this.mcServer.isDedicatedServer()) {
         return true;
      } else if (!"tell".equals(var2) && !"help".equals(var2) && !"me".equals(var2) && !"trigger".equals(var2)) {
         if (this.mcServer.getConfigurationManager().canSendCommands(this.getGameProfile())) {
            UserListOpsEntry var3 = this.mcServer.getConfigurationManager().getOppedPlayers().getEntry(this.getGameProfile());
            return var3 != null ? var3.getPermissionLevel() >= var1 : this.mcServer.getOpPermissionLevel() >= var1;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   @Override
   public void onCriticalHit(Entity var1) {
      this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(var1, 4));
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.s, this.t + 0.5, this.u);
   }

   public void setSpectatingEntity(Entity var1) {
      Entity var2 = this.getSpectatingEntity();
      this.spectatingEntity = (Entity)(var1 == null ? this : var1);
      if (var2 != this.spectatingEntity) {
         this.playerNetServerHandler.sendPacket(new S43PacketCamera(this.spectatingEntity));
         this.setPositionAndUpdate(this.spectatingEntity.s, this.spectatingEntity.t, this.spectatingEntity.u);
      }
   }

   public void setEntityActionState(float var1, float var2, boolean var3, boolean var4) {
      if (this.m != null) {
         if (var1 >= -1.0F && var1 <= 1.0F) {
            this.aZ = var1;
         }

         if (var2 >= -1.0F && var2 <= 1.0F) {
            this.ba = var2;
         }

         this.aY = var3;
         this.setSneaking(var4);
      }
   }

   @Override
   public void closeScreen() {
      this.playerNetServerHandler.sendPacket(new S2EPacketCloseWindow(this.bk.d));
      this.closeContainer();
   }

   @Override
   public void a(PotionEffect var1, boolean var2) {
      super.a(var1, var2);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.F(), var1));
   }

   @Override
   public void setGameType(WorldSettings$GameType var1) {
      this.theItemInWorldManager.setGameType(var1);
      this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(3, var1.getID()));
      if (var1 == WorldSettings$GameType.SPECTATOR) {
         this.mountEntity((Entity)null);
      } else {
         this.setSpectatingEntity(this);
      }

      this.sendPlayerAbilities();
      this.markPotionsDirty();
   }

   @Override
   public void sendEndCombat() {
      super.sendEndCombat();
      this.playerNetServerHandler.sendPacket(new S42PacketCombatEvent(this.getCombatTracker(), S42PacketCombatEvent$Event.END_COMBAT));
   }

   public Entity getSpectatingEntity() {
      return (Entity)(this.spectatingEntity == null ? this : this.spectatingEntity);
   }

   @Override
   public void onFinishedPotionEffect(PotionEffect var1) {
      super.onFinishedPotionEffect(var1);
      this.playerNetServerHandler.sendPacket(new S1EPacketRemoveEntityEffect(this.F(), var1));
   }

   @Override
   public void setItemInUse(ItemStack var1, int var2) {
      super.setItemInUse(var1, var2);
      if (var1 != null && var1.getItem() != null && var1.getItem().getItemUseAction(var1) == EnumAction.EAT) {
         this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(this, 3));
      }
   }

   @Override
   public void travelToDimension(int var1) {
      if (this.am == 1 && var1 == 1) {
         this.triggerAchievement(AchievementList.theEnd2);
         this.o.removeEntity(this);
         this.playerConqueredTheEnd = true;
         this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(4, 0.0F));
      } else {
         if (this.am == 0 && var1 == 1) {
            this.triggerAchievement(AchievementList.theEnd);
            BlockPos var2 = this.mcServer.worldServerForDimension(var1).getSpawnCoordinate();
            if (var2 != null) {
               this.playerNetServerHandler.setPlayerLocation(var2.getX(), var2.getY(), var2.getZ(), 0.0F, 0.0F);
            }

            var1 = 1;
         } else {
            this.triggerAchievement(AchievementList.portal);
         }

         this.mcServer.getConfigurationManager().transferPlayerToDimension(this, var1);
         this.lastExperience = -1;
         this.lastHealth = -1.0F;
         this.lastFoodLevel = -1;
      }
   }

   @Override
   public void onEnchantmentCritical(Entity var1) {
      this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(var1, 5));
   }

   public void updateBiomesExplored() {
      BiomeGenBase var1 = this.o.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.s), 0, MathHelper.floor_double(this.u)));
      String var2 = var1.ah;
      JsonSerializableSet var3 = this.getStatFile().b(AchievementList.exploreAllBiomes);
      if (var3 == null) {
         var3 = this.getStatFile().a(AchievementList.exploreAllBiomes, new JsonSerializableSet());
      }

      var3.add(var2);
      if (this.getStatFile().canUnlockAchievement(AchievementList.exploreAllBiomes) && var3.size() >= BiomeGenBase.explorationBiomesList.size()) {
         HashSet var4 = Sets.newHashSet(BiomeGenBase.explorationBiomesList);

         for (String var6 : var3) {
            Iterator var7 = var4.iterator();

            while (var7.hasNext()) {
               BiomeGenBase var8 = (BiomeGenBase)var7.next();
               if (var8.ah.equals(var6)) {
                  var7.remove();
               }
            }

            if (var4.isEmpty()) {
               break;
            }
         }

         if (var4.isEmpty()) {
            this.triggerAchievement(AchievementList.exploreAllBiomes);
         }
      }
   }

   @Override
   public void addStat(StatBase var1, int var2) {
      if (var1 != null) {
         this.statsFile.increaseStat(this, var1, var2);

         for (ScoreObjective var4 : this.getWorldScoreboard().getObjectivesFromCriteria(var1.getCriteria())) {
            this.getWorldScoreboard().getValueFromObjective(this.z_(), var4).increseScore(var2);
         }

         if (this.statsFile.func_150879_e()) {
            this.statsFile.func_150876_a(this);
         }
      }
   }

   public void loadResourcePack(String var1, String var2) {
      this.playerNetServerHandler.sendPacket(new S48PacketResourcePackSend(var1, var2));
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         boolean var3 = this.mcServer.isDedicatedServer() && this.canPlayersAttack() && "fall".equals(var1.damageType);
         if (!var3 && this.respawnInvulnerabilityTicks > 0 && var1 != DamageSource.outOfWorld) {
            return false;
         } else {
            if (var1 instanceof EntityDamageSource) {
               Entity var4 = var1.getEntity();
               if (var4 instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)var4)) {
                  return false;
               }

               if (var4 instanceof EntityArrow) {
                  EntityArrow var5 = (EntityArrow)var4;
                  if (var5.shootingEntity instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)var5.shootingEntity)) {
                     return false;
                  }
               }
            }

            return super.attackEntityFrom(var1, var2);
         }
      }
   }

   @Override
   public void onItemUseFinish() {
      this.playerNetServerHandler.sendPacket(new S19PacketEntityStatus(this, (byte)9));
      super.onItemUseFinish();
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(var1));
   }

   public EntityPlayerMP(MinecraftServer var1, WorldServer var2, GameProfile var3, ItemInWorldManager var4) {
      super(var2, var3);
      this.loadedChunks = Lists.newLinkedList();
      this.destroyedItemsNetCache = Lists.newLinkedList();
      this.combinedHealth = Float.MIN_VALUE;
      this.lastHealth = -1.0E8F;
      this.lastFoodLevel = -99999999;
      this.wasHungry = true;
      this.lastExperience = -99999999;
      this.respawnInvulnerabilityTicks = 60;
      this.chatColours = true;
      this.playerLastActiveTime = System.currentTimeMillis();
      this.spectatingEntity = null;
      var4.b = this;
      this.theItemInWorldManager = var4;
      BlockPos var5 = var2.M();
      if (!var2.t.getHasNoSky() && var2.P().getGameType() != WorldSettings$GameType.ADVENTURE) {
         int var6 = Math.max(5, var1.getSpawnProtectionSize() - 6);
         int var7 = MathHelper.floor_double(var2.af().getClosestDistance(var5.getX(), var5.getZ()));
         if (var7 < var6) {
            var6 = var7;
         }

         if (var7 <= 1) {
            var6 = 1;
         }

         var5 = var2.getTopSolidOrLiquidBlock(var5.add(this.V.nextInt(var6 * 2) - var6, 0, this.V.nextInt(var6 * 2) - var6));
      }

      this.mcServer = var1;
      this.statsFile = var1.getConfigurationManager().getPlayerStatsFile(this);
      this.S = 0.0F;
      this.moveToBlockPosAndAngles(var5, 0.0F, 0.0F);

      while (!var2.a(this, this.getEntityBoundingBox()).isEmpty() && this.t < 255.0) {
         this.b(this.s, this.t + 1.0, this.u);
      }
   }

   public void removeEntity(Entity var1) {
      if (var1 instanceof EntityPlayer) {
         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(var1.F()));
      } else {
         this.destroyedItemsNetCache.add(var1.F());
      }
   }

   public void addSelfToInternalCraftingInventory() {
      this.bk.onCraftGuiOpened(this);
   }

   @Override
   public void mountEntity(Entity var1) {
      Entity var2 = this.m;
      super.mountEntity(var1);
      if (var1 != var2) {
         this.playerNetServerHandler.sendPacket(new S1BPacketEntityAttach(0, this, this.m));
         this.playerNetServerHandler.setPlayerLocation(this.s, this.t, this.u, this.y, this.z);
      }
   }

   public void handleClientSettings(C15PacketClientSettings var1) {
      this.translator = var1.getLang();
      this.chatVisibility = var1.getChatVisibility();
      this.chatColours = var1.isColorsEnabled();
      this.H().updateObject(10, (byte)var1.getModelPartFlags());
   }

   public long getLastActiveTime() {
      return this.playerLastActiveTime;
   }

   public void markPlayerActive() {
      this.playerLastActiveTime = MinecraftServer.getCurrentTimeMillis();
   }

   public void method_20784() {
      try {
         super.onUpdate();

         for (int var1 = 0; var1 < this.bi.getSizeInventory(); var1++) {
            ItemStack var6 = this.bi.getStackInSlot(var1);
            if (var6 != null && var6.getItem().isMap()) {
               Packet var8 = ((ItemMapBase)var6.getItem()).createMapDataPacket(var6, this.o, this);
               if (var8 != null) {
                  this.playerNetServerHandler.sendPacket(var8);
               }
            }
         }

         if (this.getHealth() != this.lastHealth
            || this.lastFoodLevel != this.foodStats.getFoodLevel()
            || this.foodStats.getSaturationLevel() == 0.0F != this.wasHungry) {
            this.playerNetServerHandler
               .sendPacket(new S06PacketUpdateHealth(this.getHealth(), this.foodStats.getFoodLevel(), this.foodStats.getSaturationLevel()));
            this.lastHealth = this.getHealth();
            this.lastFoodLevel = this.foodStats.getFoodLevel();
            this.wasHungry = this.foodStats.getSaturationLevel() == 0.0F;
         }

         if (this.getHealth() + this.getAbsorptionAmount() != this.combinedHealth) {
            this.combinedHealth = this.getHealth() + this.getAbsorptionAmount();

            for (ScoreObjective var7 : this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.health)) {
               this.getWorldScoreboard().getValueFromObjective(this.z_(), var7).func_96651_a(Arrays.asList(this));
            }
         }

         if (this.bC != this.lastExperience) {
            this.lastExperience = this.bC;
            this.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(this.bD, this.bC, this.bB));
         }

         if (this.W % 20 * 5 == 0 && !this.getStatFile().hasAchievementUnlocked(AchievementList.exploreAllBiomes)) {
            this.updateBiomesExplored();
         }
      } catch (Throwable var4) {
         CrashReport var2 = CrashReport.makeCrashReport(var4, "Ticking player");
         CrashReportCategory var3 = var2.makeCategory("Player being ticked");
         this.addEntityCrashInfo(var3);
         throw new ReportedException(var2);
      }
   }

   public void mountEntityAndWakeUp() {
      if (this.l != null) {
         this.l.mountEntity(this);
      }

      if (this.bw) {
         this.wakeUpPlayer(true, false, false);
      }
   }

   @Override
   public void setPositionAndUpdate(double var1, double var3, double var5) {
      this.playerNetServerHandler.setPlayerLocation(var1, var3, var5, this.y, this.z);
   }

   @Override
   public void func_175145_a(StatBase var1) {
      if (var1 != null) {
         this.statsFile.unlockAchievement(this, var1, 0);

         for (ScoreObjective var3 : this.getWorldScoreboard().getObjectivesFromCriteria(var1.getCriteria())) {
            this.getWorldScoreboard().getValueFromObjective(this.z_(), var3).setScorePoints(0);
         }

         if (this.statsFile.func_150879_e()) {
            this.statsFile.func_150876_a(this);
         }
      }
   }

   @Override
   public boolean canAttackPlayer(EntityPlayer var1) {
      return !this.canPlayersAttack() ? false : super.canAttackPlayer(var1);
   }

   @Override
   public void updatePotionMetadata() {
      if (this.isSpectator()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(true);
      } else {
         super.updatePotionMetadata();
      }

      this.getServerForPlayer().getEntityTracker().func_180245_a(this);
   }

   @Override
   public void updateFallState(double var1, boolean var3, Block var4, BlockPos var5) {
   }

   @Override
   public void openEditSign(TileEntitySign var1) {
      var1.setPlayer(this);
      this.playerNetServerHandler.sendPacket(new S36PacketSignEditorOpen(var1.v()));
   }

   public EntityPlayer$EnumChatVisibility getChatVisibility() {
      return this.chatVisibility;
   }

   @Override
   public boolean isSpectatedByPlayer(EntityPlayerMP var1) {
      return var1.isSpectator() ? this.getSpectatingEntity() == this : (this.isSpectator() ? false : super.isSpectatedByPlayer(var1));
   }

   @Override
   public void sendProgressBarUpdate(Container var1, int var2, int var3) {
      this.playerNetServerHandler.sendPacket(new S31PacketWindowProperty(var1.d, var2, var3));
   }

   @Override
   public void displayVillagerTradeGui(IMerchant var1) {
      this.getNextWindowId();
      this.bk = new ContainerMerchant(this.bi, var1, this.o);
      this.bk.d = this.currentWindowId;
      this.bk.onCraftGuiOpened(this);
      InventoryMerchant var2 = ((ContainerMerchant)this.bk).getMerchantInventory();
      IChatComponent var3 = var1.getDisplayName();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "minecraft:villager", var3, var2.getSizeInventory()));
      MerchantRecipeList var4 = var1.getRecipes(this);
      if (var4 != null) {
         PacketBuffer var5 = new PacketBuffer(Unpooled.buffer());
         var5.writeInt(this.currentWindowId);
         var4.writeToBuf(var5);
         this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|TrList", var5));
      }
   }

   public void getNextWindowId() {
      this.currentWindowId = this.currentWindowId % 100 + 1;
   }

   @Override
   public void onNewPotionEffect(PotionEffect var1) {
      super.onNewPotionEffect(var1);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.F(), var1));
   }

   @Override
   public void displayGUIBook(ItemStack var1) {
      Item var2 = var1.getItem();
      if (var2 == Items.written_book) {
         this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|BOpen", new PacketBuffer(Unpooled.buffer())));
      }
   }

   @Override
   public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
      if (this.bJ()) {
         this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(this, 2));
      }

      super.wakeUpPlayer(var1, var2, var3);
      if (this.playerNetServerHandler != null) {
         this.playerNetServerHandler.setPlayerLocation(this.s, this.t, this.u, this.y, this.z);
      }
   }

   public void sendTileEntityUpdate(TileEntity var1) {
      if (var1 != null) {
         Packet var2 = var1.getDescriptionPacket();
         if (var2 != null) {
            this.playerNetServerHandler.sendPacket(var2);
         }
      }
   }

   @Override
   public void updateCraftingInventory(Container var1, List<ItemStack> var2) {
      this.playerNetServerHandler.sendPacket(new S30PacketWindowItems(var1.d, var2));
      this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, this.bi.getItemStack()));
   }

   @Override
   public void displayGUIHorse(EntityHorse var1, IInventory var2) {
      if (this.bk != this.bj) {
         this.closeScreen();
      }

      this.getNextWindowId();
      this.playerNetServerHandler
         .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "EntityHorse", var2.getDisplayName(), var2.getSizeInventory(), var1.F()));
      this.bk = new ContainerHorseInventory(this.bi, var2, var1, this);
      this.bk.d = this.currentWindowId;
      this.bk.onCraftGuiOpened(this);
   }

   @Override
   public void onUpdate() {
      this.theItemInWorldManager.updateBlockRemoving();
      this.respawnInvulnerabilityTicks--;
      if (this.Z > 0) {
         this.Z--;
      }

      this.bk.detectAndSendChanges();
      if (!this.o.D && !this.bk.canInteractWith(this)) {
         this.closeScreen();
         this.bk = this.bj;
      }

      while (!this.destroyedItemsNetCache.isEmpty()) {
         int var1 = Math.min(this.destroyedItemsNetCache.size(), Integer.MAX_VALUE);
         int[] var2 = new int[var1];
         Iterator var3 = this.destroyedItemsNetCache.iterator();
         int var4 = 0;

         while (var3.hasNext() && var4 < var1) {
            var2[var4++] = (Integer)var3.next();
            var3.remove();
         }

         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(var2));
      }

      if (!this.loadedChunks.isEmpty()) {
         ArrayList var6 = Lists.newArrayList();
         Iterator var8 = this.loadedChunks.iterator();
         ArrayList var9 = Lists.newArrayList();

         while (var8.hasNext() && var6.size() < 10) {
            ChunkCoordIntPair var10 = (ChunkCoordIntPair)var8.next();
            if (var10 != null) {
               if (this.o.e(new BlockPos(var10.chunkXPos << 4, 0, var10.chunkZPos << 4))) {
                  Chunk var5 = this.o.a(var10.chunkXPos, var10.chunkZPos);
                  if (var5.isPopulated()) {
                     var6.add(var5);
                     var9.addAll(
                        ((WorldServer)this.o)
                           .getTileEntitiesIn(var10.chunkXPos * 16, 0, var10.chunkZPos * 16, var10.chunkXPos * 16 + 16, 256, var10.chunkZPos * 16 + 16)
                     );
                     var8.remove();
                  }
               }
            } else {
               var8.remove();
            }
         }

         if (!var6.isEmpty()) {
            if (var6.size() == 1) {
               this.playerNetServerHandler.sendPacket(new S21PacketChunkData((Chunk)var6.get(0), true, 65535));
            } else {
               this.playerNetServerHandler.sendPacket(new S26PacketMapChunkBulk(var6));
            }

            for (TileEntity var13 : var9) {
               this.sendTileEntityUpdate(var13);
            }

            for (Chunk var14 : var6) {
               this.getServerForPlayer().getEntityTracker().func_85172_a(this, var14);
            }
         }
      }

      Entity var7 = this.getSpectatingEntity();
      if (var7 != this) {
         if (!var7.isEntityAlive()) {
            this.setSpectatingEntity(this);
         } else {
            this.a(var7.s, var7.t, var7.u, var7.y, var7.z);
            this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this);
            if (this.isSneaking()) {
               this.setSpectatingEntity(this);
            }
         }
      }
   }

   public boolean canPlayersAttack() {
      return this.mcServer.isPVPEnabled();
   }

   @Override
   public void onDeath(DamageSource var1) {
      if (this.o.Q().getBoolean("showDeathMessages")) {
         Team var2 = this.getTeam();
         if (var2 == null || var2.getDeathMessageVisibility() == Team$EnumVisible.ALWAYS) {
            this.mcServer.getConfigurationManager().sendChatMsg(this.getCombatTracker().getDeathMessage());
         } else if (var2.getDeathMessageVisibility() == Team$EnumVisible.HIDE_FOR_OTHER_TEAMS) {
            this.mcServer.getConfigurationManager().method_29379(this, this.getCombatTracker().getDeathMessage());
         } else if (var2.getDeathMessageVisibility() == Team$EnumVisible.HIDE_FOR_OWN_TEAM) {
            this.mcServer.getConfigurationManager().method_29348(this, this.getCombatTracker().getDeathMessage());
         }
      }

      if (!this.o.Q().getBoolean("keepInventory")) {
         this.bi.dropAllItems();
      }

      for (ScoreObjective var3 : this.o.Z().getObjectivesFromCriteria(IScoreObjectiveCriteria.deathCount)) {
         Score var4 = this.getWorldScoreboard().getValueFromObjective(this.z_(), var3);
         var4.func_96648_a();
      }

      EntityLivingBase var6 = this.getAttackingEntity();
      if (var6 != null) {
         EntityList$EntityEggInfo var7 = EntityList.entityEggs.get(EntityList.getEntityID(var6));
         if (var7 != null) {
            this.triggerAchievement(var7.field_151513_e);
         }

         var6.addToPlayerScore(this, this.scoreValue);
      }

      this.triggerAchievement(StatList.deathsStat);
      this.func_175145_a(StatList.timeSinceDeathStat);
      this.getCombatTracker().reset();
   }

   @Override
   public void sendPlayerAbilities() {
      if (this.playerNetServerHandler != null) {
         this.playerNetServerHandler.sendPacket(new S39PacketPlayerAbilities(this.bA));
         this.updatePotionMetadata();
      }
   }

   public String getPlayerIP() {
      String var1 = this.playerNetServerHandler.netManager.getRemoteAddress().toString();
      var1 = var1.substring(var1.indexOf("/") + 1);
      return var1.substring(0, var1.indexOf(":"));
   }

   public IChatComponent getTabListDisplayName() {
      return null;
   }

   public void sendContainerToPlayer(Container var1) {
      this.updateCraftingInventory(var1, var1.getInventory());
   }

   @Override
   public void clonePlayer(EntityPlayer var1, boolean var2) {
      super.clonePlayer(var1, var2);
      this.lastExperience = -1;
      this.lastHealth = -1.0F;
      this.lastFoodLevel = -1;
      this.destroyedItemsNetCache.addAll(((EntityPlayerMP)var1).destroyedItemsNetCache);
   }

   @Override
   public void addExperienceLevel(int var1) {
      super.addExperienceLevel(var1);
      this.lastExperience = -1;
   }

   @Override
   public void attackTargetEntityWithCurrentItem(Entity var1) {
      if (this.theItemInWorldManager.getGameType() == WorldSettings$GameType.SPECTATOR) {
         this.setSpectatingEntity(var1);
      } else {
         super.attackTargetEntityWithCurrentItem(var1);
      }
   }

   @Override
   public void sendEnterCombat() {
      super.sendEnterCombat();
      this.playerNetServerHandler.sendPacket(new S42PacketCombatEvent(this.getCombatTracker(), S42PacketCombatEvent$Event.ENTER_COMBAT));
   }

   @Override
   public void sendAllWindowProperties(Container var1, IInventory var2) {
      for (int var3 = 0; var3 < var2.getFieldCount(); var3++) {
         this.playerNetServerHandler.sendPacket(new S31PacketWindowProperty(var1.d, var3, var2.getField(var3)));
      }
   }

   public void updateHeldItem() {
      if (!this.isChangingQuantityOnly) {
         this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, this.bi.getItemStack()));
      }
   }

   @Override
   public void a(Entity var1, int var2) {
      super.a(var1, var2);
      this.bk.detectAndSendChanges();
   }

   @Override
   public EntityPlayer$EnumStatus trySleep(BlockPos var1) {
      EntityPlayer$EnumStatus var2 = super.trySleep(var1);
      if (var2 == EntityPlayer$EnumStatus.OK) {
         S0APacketUseBed var3 = new S0APacketUseBed(this, var1);
         this.getServerForPlayer().getEntityTracker().sendToAllTrackingEntity(this, var3);
         this.playerNetServerHandler.setPlayerLocation(this.s, this.t, this.u, this.y, this.z);
         this.playerNetServerHandler.sendPacket(var3);
      }

      return var2;
   }

   public StatisticsFile getStatFile() {
      return this.statsFile;
   }

   @Override
   public void addChatComponentMessage(IChatComponent var1) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(var1));
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("playerGameType", 99)) {
         if (MinecraftServer.getServer().getForceGamemode()) {
            this.theItemInWorldManager.setGameType(MinecraftServer.getServer().getGameType());
         } else {
            this.theItemInWorldManager.setGameType(WorldSettings$GameType.getByID(var1.getInteger("playerGameType")));
         }
      }
   }

   public void setPlayerHealthUpdated() {
      this.lastHealth = -1.0E8F;
   }

   public WorldServer getServerForPlayer() {
      return (WorldServer)this.o;
   }

   @Override
   public void displayGUIChest(IInventory var1) {
      if (this.bk != this.bj) {
         this.closeScreen();
      }

      if (var1 instanceof ILockableContainer) {
         ILockableContainer var2 = (ILockableContainer)var1;
         if (var2.B_() && !this.canOpen(var2.getLockCode()) && !this.isSpectator()) {
            this.playerNetServerHandler.sendPacket(new S02PacketChat(new ChatComponentTranslation("container.isLocked", var1.getDisplayName()), (byte)2));
            this.playerNetServerHandler.sendPacket(new S29PacketSoundEffect("random.door_close", this.s, this.t, this.u, 1.0F, 1.0F));
            return;
         }
      }

      this.getNextWindowId();
      if (var1 instanceof IInteractionObject) {
         this.playerNetServerHandler
            .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, ((IInteractionObject)var1).getGuiID(), var1.getDisplayName(), var1.getSizeInventory()));
         this.bk = ((IInteractionObject)var1).createContainer(this.bi, this);
      } else {
         this.playerNetServerHandler
            .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "minecraft:container", var1.getDisplayName(), var1.getSizeInventory()));
         this.bk = new ContainerChest(this.bi, var1, this);
      }

      this.bk.d = this.currentWindowId;
      this.bk.onCraftGuiOpened(this);
   }

   public void closeContainer() {
      this.bk.onContainerClosed(this);
      this.bk = this.bj;
   }

   public void handleFalling(double var1, boolean var3) {
      int var4 = MathHelper.floor_double(this.s);
      int var5 = MathHelper.floor_double(this.t - 0.2F);
      int var6 = MathHelper.floor_double(this.u);
      BlockPos var7 = new BlockPos(var4, var5, var6);
      Block var8 = this.o.getBlockState(var7).getBlock();
      if (var8.getMaterial() == Material.air) {
         Block var9 = this.o.getBlockState(var7.down()).getBlock();
         if (var9 instanceof BlockFence || var9 instanceof BlockWall || var9 instanceof BlockFenceGate) {
            var7 = var7.down();
            var8 = this.o.getBlockState(var7).getBlock();
         }
      }

      super.updateFallState(var1, var3, var8, var7);
   }

   @Override
   public void displayGui(IInteractionObject var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, var1.getGuiID(), var1.getDisplayName()));
      this.bk = var1.createContainer(this.bi, this);
      this.bk.d = this.currentWindowId;
      this.bk.onCraftGuiOpened(this);
   }

   @Override
   public void removeExperienceLevel(int var1) {
      super.removeExperienceLevel(var1);
      this.lastExperience = -1;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
   }
}
