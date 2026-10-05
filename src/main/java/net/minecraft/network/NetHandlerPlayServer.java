package net.minecraft.network;

import com.cheatbreaker.client.network.CustomPayloadSender;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements ITickable, INetHandlerPlayServer {
   public int recoveredField2568;
   public int recoveredField2569;
   public double recoveredField2570;
   public double recoveredField2571;
   public boolean field_147366_g;
   public static Logger logger = LogManager.getLogger();
   public IntHashMap<Short> field_147372_n = new IntHashMap<>();
   public long lastPingTime;
   public int recoveredField2572;
   public MinecraftServer serverController;
   public boolean hasMoved = true;
   public int recoveredField2573;
   public int field_147378_h;
   public EntityPlayerMP playerEntity;
   public int recoveredField2574;
   public long lastSentPingPacket;
   public NetworkManager netManager;
   public double recoveredField2575;

   public long currentTimeMillis() {
      return System.nanoTime() / 1000000L;
   }

   public NetHandlerPlayServer(MinecraftServer var1, NetworkManager var2, EntityPlayerMP var3) {
      this.serverController = var1;
      this.netManager = var2;
      var2.setNetHandler(this);
      this.playerEntity = var3;
      var3.playerNetServerHandler = this;
   }

   @Override
   public void onDisconnect(IChatComponent var1) {
      logger.info(this.playerEntity.z_() + " lost connection: " + var1);
      this.serverController.refreshStatusNextTick();
      ChatComponentTranslation var2 = new ChatComponentTranslation("multiplayer.player.left", this.playerEntity.getDisplayName());
      var2.getChatStyle().setColor(EnumChatFormatting.YELLOW);
      this.serverController.getConfigurationManager().sendChatMsg(var2);
      this.playerEntity.mountEntityAndWakeUp();
      this.serverController.getConfigurationManager().playerLoggedOut(this.playerEntity);
      if (this.serverController.isSinglePlayer() && this.playerEntity.z_().equals(this.serverController.getServerOwner())) {
         logger.info("Stopping singleplayer server as player logged out");
         this.serverController.initiateShutdown();
      }
   }

   @Override
   public void processCloseWindow(C0DPacketCloseWindow var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.closeContainer();
   }

   public void setPlayerLocation(double var1, double var3, double var5, float var7, float var8) {
      this.setPlayerLocation(var1, var3, var5, var7, var8, Collections.emptySet());
   }

   @Override
   public void handleResourcePackStatus(C19PacketResourcePackStatus var1) {
   }

   @Override
   public void processEntityAction(C0BPacketEntityAction var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      switch (var1.getAction()) {
         case START_SNEAKING:
            this.playerEntity.setSneaking(true);
            break;
         case STOP_SNEAKING:
            this.playerEntity.setSneaking(false);
            break;
         case START_SPRINTING:
            this.playerEntity.setSprinting(true);
            break;
         case STOP_SPRINTING:
            this.playerEntity.setSprinting(false);
            break;
         case STOP_SLEEPING:
            this.playerEntity.wakeUpPlayer(false, true, true);
            this.hasMoved = false;
            break;
         case RIDING_JUMP:
            if (this.playerEntity.m instanceof EntityHorse) {
               ((EntityHorse)this.playerEntity.m).setJumpPower(var1.getAuxData());
            }
            break;
         case OPEN_INVENTORY:
            if (this.playerEntity.m instanceof EntityHorse) {
               ((EntityHorse)this.playerEntity.m).openGUI(this.playerEntity);
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid client command!");
      }
   }

   public NetworkManager getNetworkManager() {
      return this.netManager;
   }

   @Override
   public void processTabComplete(C14PacketTabComplete var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      ArrayList var2 = Lists.newArrayList();

      for (String var4 : this.serverController.getTabCompletions(this.playerEntity, var1.getMessage(), var1.getTargetBlock())) {
         var2.add(var4);
      }

      this.playerEntity.playerNetServerHandler.sendPacket(new S3APacketTabComplete((java.lang.String[])var2.toArray(new String[var2.size()])));
   }

   @Override
   public void processClientSettings(C15PacketClientSettings var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.handleClientSettings(var1);
   }

   @Override
   public void processUseEntity(C02PacketUseEntity var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.am);
      Entity var3 = var1.getEntityFromWorld(var2);
      this.playerEntity.markPlayerActive();
      if (var3 != null) {
         boolean var4 = this.playerEntity.t(var3);
         double var5 = 36.0;
         if (!var4) {
            var5 = 9.0;
         }

         if (this.playerEntity.h(var3) < var5) {
            if (var1.getAction() == C02PacketUseEntity.Action.INTERACT) {
               this.playerEntity.interactWith(var3);
            } else if (var1.getAction() == C02PacketUseEntity.Action.INTERACT_AT) {
               var3.interactAt(this.playerEntity, var1.getHitVec());
            } else if (var1.getAction() == C02PacketUseEntity.Action.ATTACK) {
               if (var3 instanceof EntityItem || var3 instanceof EntityXPOrb || var3 instanceof EntityArrow || var3 == this.playerEntity) {
                  this.kickPlayerFromServer("Attempting to attack an invalid entity");
                  this.serverController.logWarning("Player " + this.playerEntity.z_() + " tried to attack an invalid entity");
                  return;
               }

               this.playerEntity.attackTargetEntityWithCurrentItem(var3);
            }
         }
      }
   }

   public void sendPacket(final Packet var1) {
      if (var1 instanceof S02PacketChat) {
         S02PacketChat var2 = (S02PacketChat)var1;
         EntityPlayer.EnumChatVisibility var3 = this.playerEntity.getChatVisibility();
         if (var3 == EntityPlayer.EnumChatVisibility.HIDDEN) {
            return;
         }

         if (var3 == EntityPlayer.EnumChatVisibility.SYSTEM && !var2.isChat()) {
            return;
         }
      }

      try {
         this.netManager.sendPacket(var1);
      } catch (Throwable var5) {
         CrashReport var6 = CrashReport.makeCrashReport(var5, "Sending packet");
         CrashReportCategory var4 = var6.makeCategory("Packet being sent");
         var4.addCrashSectionCallable("Packet class", new Callable<String>() {
            public String call() throws java.lang.Exception {
               return var1.getClass().getCanonicalName();
            }
         });
         throw new ReportedException(var6);
      }
   }

   @Override
   public void processClientStatus(C16PacketClientStatus var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      C16PacketClientStatus.EnumState var2 = var1.getStatus();
      switch (var2) {
         case PERFORM_RESPAWN:
            if (this.playerEntity.playerConqueredTheEnd) {
               this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, true);
            } else if (this.playerEntity.getServerForPlayer().P().isHardcoreModeEnabled()) {
               if (this.serverController.isSinglePlayer() && this.playerEntity.z_().equals(this.serverController.getServerOwner())) {
                  this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
                  this.serverController.deleteWorldAndStopServer();
               } else {
                  UserListBansEntry var3 = new UserListBansEntry(
                     this.playerEntity.getGameProfile(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore"
                  );
                  this.serverController.getConfigurationManager().getBannedPlayers().addEntry(var3);
                  this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
               }
            } else {
               if (this.playerEntity.getHealth() > 0.0F) {
                  return;
               }

               this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, false);
            }
            break;
         case REQUEST_STATS:
            this.playerEntity.getStatFile().func_150876_a(this.playerEntity);
            break;
         case OPEN_INVENTORY_ACHIEVEMENT:
            this.playerEntity.triggerAchievement(AchievementList.openInventory);
      }
   }

   @Override
   public void processPlayerDigging(C07PacketPlayerDigging var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.am);
      BlockPos var3 = var1.getPosition();
      this.playerEntity.markPlayerActive();
      switch (var1.getStatus()) {
         case DROP_ITEM:
            if (!this.playerEntity.isSpectator()) {
               this.playerEntity.dropOneItem(false);
            }

            return;
         case DROP_ALL_ITEMS:
            if (!this.playerEntity.isSpectator()) {
               this.playerEntity.dropOneItem(true);
            }

            return;
         case RELEASE_USE_ITEM:
            this.playerEntity.stopUsingItem();
            return;
         case START_DESTROY_BLOCK:
         case ABORT_DESTROY_BLOCK:
         case STOP_DESTROY_BLOCK:
            double var4 = this.playerEntity.s - (var3.getX() + 0.5);
            double var6 = this.playerEntity.t - (var3.getY() + 0.5) + 1.5;
            double var8 = this.playerEntity.u - (var3.getZ() + 0.5);
            double var10 = var4 * var4 + var6 * var6 + var8 * var8;
            if (var10 > 36.0) {
               return;
            } else if (var3.getY() >= this.serverController.getBuildLimit()) {
               return;
            } else {
               if (var1.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                  if (!this.serverController.isBlockProtected(var2, var3, this.playerEntity) && var2.af().contains(var3)) {
                     this.playerEntity.theItemInWorldManager.onBlockClicked(var3, var1.getFacing());
                  } else {
                     this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(var2, var3));
                  }
               } else {
                  if (var1.getStatus() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                     this.playerEntity.theItemInWorldManager.blockRemoving(var3);
                  } else if (var1.getStatus() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
                     this.playerEntity.theItemInWorldManager.cancelDestroyingBlock();
                  }

                  if (var2.getBlockState(var3).getBlock().getMaterial() != Material.air) {
                     this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(var2, var3));
                  }
               }

               return;
            }
         default:
            throw new IllegalArgumentException("Invalid player action");
      }
   }

   public void kickPlayerFromServer(String var1) {
      final ChatComponentText var2 = new ChatComponentText(var1);
      this.netManager.sendPacket(new S40PacketDisconnect(var2), new GenericFutureListener<Future<? super Void>>() {
         @Override
         public void operationComplete(Future<? super Void> var1) throws java.lang.Exception {
            NetHandlerPlayServer.this.netManager.closeChannel(var2);
         }
      });
      this.netManager.disableAutoRead();
      Futures.getUnchecked(this.serverController.addScheduledTask(new Runnable() {
         @Override
         public void run() {
            NetHandlerPlayServer.this.netManager.checkDisconnected();
         }
      }));
   }

   @Override
   public void processPlayerAbilities(C13PacketPlayerAbilities var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.bA.isFlying = var1.isFlying() && this.playerEntity.bA.allowFlying;
   }

   @Override
   public void processInput(C0CPacketInput var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.setEntityActionState(var1.getStrafeSpeed(), var1.getForwardSpeed(), var1.isJumping(), var1.isSneaking());
   }

   public boolean func_183006_b(C03PacketPlayer var1) {
      return !Doubles.isFinite(var1.method_05060())
         || !Doubles.isFinite(var1.method_05059())
         || !Doubles.isFinite(var1.method_05063())
         || !Floats.isFinite(var1.method_05057())
         || !Floats.isFinite(var1.method_05065());
   }

   @Override
   public void handleSpectate(C18PacketSpectate var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.isSpectator()) {
         Entity var2 = null;

         for (WorldServer var6 : this.serverController.worldServers) {
            if (var6 != null) {
               var2 = var1.getEntity(var6);
               if (var2 != null) {
                  break;
               }
            }
         }

         if (var2 != null) {
            this.playerEntity.setSpectatingEntity(this.playerEntity);
            this.playerEntity.mountEntity((Entity)null);
            if (var2.o != this.playerEntity.o) {
               WorldServer var7 = this.playerEntity.getServerForPlayer();
               WorldServer var8 = (WorldServer)var2.o;
               this.playerEntity.am = var2.am;
               this.sendPacket(
                  new S07PacketRespawn(
                     this.playerEntity.am, var7.getDifficulty(), var7.P().getTerrainType(), this.playerEntity.theItemInWorldManager.getGameType()
                  )
               );
               var7.f(this.playerEntity);
               this.playerEntity.I = false;
               this.playerEntity.a_(var2.s, var2.t, var2.u, var2.y, var2.z);
               if (this.playerEntity.isEntityAlive()) {
                  var7.updateEntityWithOptionalForce(this.playerEntity, false);
                  var8.spawnEntityInWorld(this.playerEntity);
                  var8.updateEntityWithOptionalForce(this.playerEntity, false);
               }

               this.playerEntity.setWorld(var8);
               this.serverController.getConfigurationManager().preparePlayer(this.playerEntity, var7);
               this.playerEntity.setPositionAndUpdate(var2.s, var2.t, var2.u);
               this.playerEntity.theItemInWorldManager.setWorld(var8);
               this.serverController.getConfigurationManager().updateTimeAndWeatherForPlayer(this.playerEntity, var8);
               this.serverController.getConfigurationManager().syncPlayerInventory(this.playerEntity);
            } else {
               this.playerEntity.setPositionAndUpdate(var2.s, var2.t, var2.u);
            }
         }
      }
   }

   @Override
   public void processConfirmTransaction(C0FPacketConfirmTransaction var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      Short var2 = this.field_147372_n.lookup(this.playerEntity.bk.d);
      if (var2 != null
         && var1.getUid() == var2
         && this.playerEntity.bk.d == var1.getWindowId()
         && !this.playerEntity.bk.getCanCraft(this.playerEntity)
         && !this.playerEntity.isSpectator()) {
         this.playerEntity.bk.setCanCraft(this.playerEntity, true);
      }
   }

   public void handleSlashCommand(String var1) {
      this.serverController.getCommandManager().executeCommand(this.playerEntity, var1);
   }

   @Override
   public void processEnchantItem(C11PacketEnchantItem var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      if (this.playerEntity.bk.d == var1.getWindowId() && this.playerEntity.bk.getCanCraft(this.playerEntity) && !this.playerEntity.isSpectator()) {
         this.playerEntity.bk.enchantItem(this.playerEntity, var1.getButton());
         this.playerEntity.bk.detectAndSendChanges();
      }
   }

   @Override
   public void method_29428(CustomPayloadSender var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if ("MC|BEdit".equals(var1.method_28755())) {
         PacketBuffer var44 = new PacketBuffer(Unpooled.wrappedBuffer(var1.method_28757()));

         try {
            ItemStack var49 = var44.readItemStackFromBuffer();
            if (var49 == null) {
               return;
            }

            if (!ItemWritableBook.isNBTValid(var49.getTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack var52 = this.playerEntity.bi.getCurrentItem();
            if (var52 == null) {
               return;
            }

            if (var49.getItem() == Items.writable_book && var49.getItem() == var52.getItem()) {
               var52.setTagInfo("pages", var49.getTagCompound().getTagList("pages", 8));
            }
         } catch (Exception var36) {
            logger.error("Couldn't handle book info", var36);
            return;
         } finally {
            var44.release();
         }
      } else if ("MC|BSign".equals(var1.method_28755())) {
         PacketBuffer var43 = new PacketBuffer(Unpooled.wrappedBuffer(var1.method_28757()));

         try {
            ItemStack var48 = var43.readItemStackFromBuffer();
            if (var48 == null) {
               return;
            }

            if (!ItemEditableBook.validBookTagContents(var48.getTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack var51 = this.playerEntity.bi.getCurrentItem();
            if (var51 == null) {
               return;
            }

            if (var48.getItem() == Items.written_book && var51.getItem() == Items.writable_book) {
               var51.setTagInfo("author", new NBTTagString(this.playerEntity.z_()));
               var51.setTagInfo("title", new NBTTagString(var48.getTagCompound().getString("title")));
               var51.setTagInfo("pages", var48.getTagCompound().getTagList("pages", 8));
               var51.setItem(Items.written_book);
            }
         } catch (Exception var38) {
            logger.error("Couldn't sign book", var38);
            return;
         } finally {
            var43.release();
         }
      } else {
         if ("MC|TrSel".equals(var1.method_28755())) {
            try {
               int var2 = var1.method_28757().readInt();
               Container var3 = this.playerEntity.bk;
               if (var3 instanceof ContainerMerchant) {
                  ((ContainerMerchant)var3).setCurrentRecipeIndex(var2);
               }
            } catch (Exception var35) {
               logger.error("Couldn't select trade", var35);
            }
         } else if ("MC|AdvCdm".equals(var1.method_28755())) {
            if (!this.serverController.isCommandBlockEnabled()) {
               this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.notEnabled"));
            } else if (this.playerEntity.canCommandSenderUseCommand(2, "") && this.playerEntity.bA.isCreativeMode) {
               PacketBuffer var40 = var1.method_28757();

               try {
                  byte var45 = var40.readByte();
                  CommandBlockLogic var4 = null;
                  if (var45 == 0) {
                     TileEntity var5 = this.playerEntity.o.getTileEntity(new BlockPos(var40.readInt(), var40.readInt(), var40.readInt()));
                     if (var5 instanceof TileEntityCommandBlock) {
                        var4 = ((TileEntityCommandBlock)var5).getCommandBlockLogic();
                     }
                  } else if (var45 == 1) {
                     Entity var53 = this.playerEntity.o.getEntityByID(var40.readInt());
                     if (var53 instanceof EntityMinecartCommandBlock) {
                        var4 = ((EntityMinecartCommandBlock)var53).getCommandBlockLogic();
                     }
                  }

                  String var54 = var40.readStringFromBuffer(var40.readableBytes());
                  boolean var6 = var40.readBoolean();
                  if (var4 != null) {
                     var4.setCommand(var54);
                     var4.setTrackOutput(var6);
                     if (!var6) {
                        var4.setLastOutput((IChatComponent)null);
                     }

                     var4.updateCommand();
                     this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.setCommand.success", var54));
                  }
               } catch (Exception var33) {
                  logger.error("Couldn't set command block", var33);
               } finally {
                  var40.release();
               }
            } else {
               this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.notAllowed"));
            }
         } else if ("MC|Beacon".equals(var1.method_28755())) {
            if (this.playerEntity.bk instanceof ContainerBeacon) {
               try {
                  PacketBuffer var41 = var1.method_28757();
                  int var46 = var41.readInt();
                  int var50 = var41.readInt();
                  ContainerBeacon var55 = (ContainerBeacon)this.playerEntity.bk;
                  Slot var56 = var55.a(0);
                  if (var56.getHasStack()) {
                     var56.decrStackSize(1);
                     IInventory var7 = var55.func_180611_e();
                     var7.setField(1, var46);
                     var7.setField(2, var50);
                     var7.markDirty();
                  }
               } catch (Exception var32) {
                  logger.error("Couldn't set beacon", var32);
               }
            }
         } else if ("MC|ItemName".equals(var1.method_28755()) && this.playerEntity.bk instanceof ContainerRepair) {
            ContainerRepair var42 = (ContainerRepair)this.playerEntity.bk;
            if (var1.method_28757() != null && var1.method_28757().readableBytes() >= 1) {
               String var47 = ChatAllowedCharacters.filterAllowedCharacters(var1.method_28757().readStringFromBuffer(32767));
               if (var47.length() <= 30) {
                  var42.updateItemName(var47);
               }
            } else {
               var42.updateItemName("");
            }
         }
      }
   }

   @Override
   public void update() {
      this.field_147366_g = false;
      this.recoveredField2568++;
      this.serverController.theProfiler.startSection("keepAlive");
      if (this.recoveredField2568 - this.lastSentPingPacket > 40L) {
         this.lastSentPingPacket = this.recoveredField2568;
         this.lastPingTime = this.currentTimeMillis();
         this.field_147378_h = (int)this.lastPingTime;
         this.sendPacket(new S00PacketKeepAlive(this.field_147378_h));
      }

      this.serverController.theProfiler.endSection();
      if (this.recoveredField2572 > 0) {
         this.recoveredField2572--;
      }

      if (this.recoveredField2573 > 0) {
         this.recoveredField2573--;
      }

      if (this.playerEntity.getLastActiveTime() > 0L
         && this.serverController.method_06871() > 0
         && MinecraftServer.getCurrentTimeMillis() - this.playerEntity.getLastActiveTime() > this.serverController.method_06871() * 1000 * 60) {
         this.kickPlayerFromServer("You have been idle for too long!");
      }
   }

   @Override
   public void handleAnimation(C0APacketAnimation var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      this.playerEntity.swingItem();
   }

   @Override
   public void processKeepAlive(C00PacketKeepAlive var1) {
      if (var1.getKey() == this.field_147378_h) {
         int var2 = (int)(this.currentTimeMillis() - this.lastPingTime);
         this.playerEntity.ping = (this.playerEntity.ping * 3 + var2) / 4;
      }
   }

   @Override
   public void processHeldItemChange(C09PacketHeldItemChange var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if (var1.getSlotId() >= 0 && var1.getSlotId() < InventoryPlayer.getHotbarSize()) {
         this.playerEntity.bi.currentItem = var1.getSlotId();
         this.playerEntity.markPlayerActive();
      } else {
         logger.warn(this.playerEntity.z_() + " tried to set an invalid carried item");
      }
   }

   @Override
   public void processCreativeInventoryAction(C10PacketCreativeInventoryAction var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.theItemInWorldManager.isCreative()) {
         boolean var2 = var1.getSlotId() < 0;
         ItemStack var3 = var1.getStack();
         if (var3 != null && var3.hasTagCompound() && var3.getTagCompound().hasKey("BlockEntityTag", 10)) {
            NBTTagCompound var4 = var3.getTagCompound().getCompoundTag("BlockEntityTag");
            if (var4.hasKey("x") && var4.hasKey("y") && var4.hasKey("z")) {
               BlockPos var5 = new BlockPos(var4.getInteger("x"), var4.getInteger("y"), var4.getInteger("z"));
               TileEntity var6 = this.playerEntity.o.getTileEntity(var5);
               if (var6 != null) {
                  NBTTagCompound var7 = new NBTTagCompound();
                  var6.writeToNBT(var7);
                  var7.removeTag("x");
                  var7.removeTag("y");
                  var7.removeTag("z");
                  var3.setTagInfo("BlockEntityTag", var7);
               }
            }
         }

         boolean var8 = var1.getSlotId() >= 1 && var1.getSlotId() < 36 + InventoryPlayer.getHotbarSize();
         boolean var9 = var3 == null || var3.getItem() != null;
         boolean var10 = var3 == null || var3.getMetadata() >= 0 && var3.stackSize <= 64 && var3.stackSize > 0;
         if (var8 && var9 && var10) {
            if (var3 == null) {
               this.playerEntity.bj.putStackInSlot(var1.getSlotId(), (ItemStack)null);
            } else {
               this.playerEntity.bj.putStackInSlot(var1.getSlotId(), var3);
            }

            this.playerEntity.bj.setCanCraft(this.playerEntity, true);
         } else if (var2 && var9 && var10 && this.recoveredField2573 < 200) {
            this.recoveredField2573 += 20;
            EntityItem var11 = this.playerEntity.dropPlayerItemWithRandomChoice(var3, true);
            if (var11 != null) {
               var11.setAgeToCreativeDespawnTime();
            }
         }
      }
   }

   @Override
   public void processClickWindow(C0EPacketClickWindow var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      if (this.playerEntity.bk.d == var1.getWindowId() && this.playerEntity.bk.getCanCraft(this.playerEntity)) {
         if (this.playerEntity.isSpectator()) {
            ArrayList var2 = Lists.newArrayList();

            for (int var3 = 0; var3 < this.playerEntity.bk.c.size(); var3++) {
               var2.add(this.playerEntity.bk.c.get(var3).getStack());
            }

            this.playerEntity.updateCraftingInventory(this.playerEntity.bk, var2);
         } else {
            ItemStack var5 = this.playerEntity.bk.slotClick(var1.getSlotId(), var1.getUsedButton(), var1.getMode(), this.playerEntity);
            if (ItemStack.areItemStacksEqual(var1.getClickedItem(), var5)) {
               this.playerEntity.playerNetServerHandler.sendPacket(new S32PacketConfirmTransaction(var1.getWindowId(), var1.getActionNumber(), true));
               this.playerEntity.isChangingQuantityOnly = true;
               this.playerEntity.bk.detectAndSendChanges();
               this.playerEntity.updateHeldItem();
               this.playerEntity.isChangingQuantityOnly = false;
            } else {
               this.field_147372_n.addKey(this.playerEntity.bk.d, var1.getActionNumber());
               this.playerEntity.playerNetServerHandler.sendPacket(new S32PacketConfirmTransaction(var1.getWindowId(), var1.getActionNumber(), false));
               this.playerEntity.bk.setCanCraft(this.playerEntity, false);
               ArrayList var6 = Lists.newArrayList();

               for (int var4 = 0; var4 < this.playerEntity.bk.c.size(); var4++) {
                  var6.add(this.playerEntity.bk.c.get(var4).getStack());
               }

               this.playerEntity.updateCraftingInventory(this.playerEntity.bk, var6);
            }
         }
      }
   }

   @Override
   public void processUpdateSign(C12PacketUpdateSign var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.am);
      BlockPos var3 = var1.getPosition();
      if (var2.e(var3)) {
         TileEntity var4 = var2.getTileEntity(var3);
         if (!(var4 instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign var5 = (TileEntitySign)var4;
         if (!var5.getIsEditable() || var5.getPlayer() != this.playerEntity) {
            this.serverController.logWarning("Player " + this.playerEntity.z_() + " just tried to change non-editable sign");
            return;
         }

         IChatComponent[] var6 = var1.getLines();

         for (int var7 = 0; var7 < var6.length; var7++) {
            var5.signText[var7] = new ChatComponentText(EnumChatFormatting.getTextWithoutFormattingCodes(var6[var7].getUnformattedText()));
         }

         var5.markDirty();
         var2.h(var3);
      }
   }

   public void setPlayerLocation(double var1, double var3, double var5, float var7, float var8, Set<S08PacketPlayerPosLook.EnumFlags> var9) {
      this.hasMoved = false;
      this.recoveredField2571 = var1;
      this.recoveredField2575 = var3;
      this.recoveredField2570 = var5;
      if (var9.contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         this.recoveredField2571 = this.recoveredField2571 + this.playerEntity.s;
      }

      if (var9.contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         this.recoveredField2575 = this.recoveredField2575 + this.playerEntity.t;
      }

      if (var9.contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         this.recoveredField2570 = this.recoveredField2570 + this.playerEntity.u;
      }

      float var10 = var7;
      float var11 = var8;
      if (var9.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         var10 = var7 + this.playerEntity.y;
      }

      if (var9.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         var11 = var8 + this.playerEntity.z;
      }

      this.playerEntity.a(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, var10, var11);
      this.playerEntity.playerNetServerHandler.sendPacket(new S08PacketPlayerPosLook(var1, var3, var5, var7, var8, var9));
   }

   @Override
   public void processChatMessage(C01PacketChatMessage var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.getChatVisibility() == EntityPlayer.EnumChatVisibility.HIDDEN) {
         ChatComponentTranslation var2 = new ChatComponentTranslation("chat.cannotSend");
         var2.getChatStyle().setColor(EnumChatFormatting.RED);
         this.sendPacket(new S02PacketChat(var2));
      } else {
         this.playerEntity.markPlayerActive();
         String var4 = var1.getMessage();
         var4 = StringUtils.normalizeSpace(var4);

         for (int var3 = 0; var3 < var4.length(); var3++) {
            if (!ChatAllowedCharacters.isAllowedCharacter(var4.charAt(var3))) {
               this.kickPlayerFromServer("Illegal characters in chat");
               return;
            }
         }

         if (var4.startsWith("/")) {
            this.handleSlashCommand(var4);
         } else {
            ChatComponentTranslation var6 = new ChatComponentTranslation("chat.type.text", this.playerEntity.getDisplayName(), var4);
            this.serverController.getConfigurationManager().sendChatMsgImpl(var6, false);
         }

         this.recoveredField2572 += 20;
         if (this.recoveredField2572 > 200 && !this.serverController.getConfigurationManager().canSendCommands(this.playerEntity.getGameProfile())) {
            this.kickPlayerFromServer("disconnect.spam");
         }
      }
   }

   @Override
   public void processPlayerBlockPlacement(C08PacketPlayerBlockPlacement var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.am);
      ItemStack var3 = this.playerEntity.bi.getCurrentItem();
      boolean var4 = false;
      BlockPos var5 = var1.getPosition();
      EnumFacing var6 = EnumFacing.getFront(var1.getPlacedBlockDirection());
      this.playerEntity.markPlayerActive();
      if (var1.getPlacedBlockDirection() == 255) {
         if (var3 == null) {
            return;
         }

         this.playerEntity.theItemInWorldManager.tryUseItem(this.playerEntity, var2, var3);
      } else if (var5.getY() < this.serverController.getBuildLimit() - 1 || var6 != EnumFacing.UP && var5.getY() < this.serverController.getBuildLimit()) {
         if (this.hasMoved
            && this.playerEntity.e(var5.getX() + 0.5, var5.getY() + 0.5, var5.getZ() + 0.5) < 64.0
            && !this.serverController.isBlockProtected(var2, var5, this.playerEntity)
            && var2.af().contains(var5)) {
            this.playerEntity
               .theItemInWorldManager
               .activateBlockOrUseItem(
                  this.playerEntity, var2, var3, var5, var6, var1.getPlacedBlockOffsetX(), var1.getPlacedBlockOffsetY(), var1.getPlacedBlockOffsetZ()
               );
         }

         var4 = true;
      } else {
         ChatComponentTranslation var7 = new ChatComponentTranslation("build.tooHigh", this.serverController.getBuildLimit());
         var7.getChatStyle().setColor(EnumChatFormatting.RED);
         this.playerEntity.playerNetServerHandler.sendPacket(new S02PacketChat(var7));
         var4 = true;
      }

      if (var4) {
         this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(var2, var5));
         this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(var2, var5.a(var6)));
      }

      var3 = this.playerEntity.bi.getCurrentItem();
      if (var3 != null && var3.stackSize == 0) {
         this.playerEntity.bi.mainInventory[this.playerEntity.bi.currentItem] = null;
         var3 = null;
      }

      if (var3 == null || var3.getMaxItemUseDuration() == 0) {
         this.playerEntity.isChangingQuantityOnly = true;
         this.playerEntity.bi.mainInventory[this.playerEntity.bi.currentItem] = ItemStack.copyItemStack(
            this.playerEntity.bi.mainInventory[this.playerEntity.bi.currentItem]
         );
         Slot var9 = this.playerEntity.bk.getSlotFromInventory(this.playerEntity.bi, this.playerEntity.bi.currentItem);
         this.playerEntity.bk.detectAndSendChanges();
         this.playerEntity.isChangingQuantityOnly = false;
         if (!ItemStack.areItemStacksEqual(this.playerEntity.bi.getCurrentItem(), var1.getStack())) {
            this.sendPacket(new S2FPacketSetSlot(this.playerEntity.bk.d, var9.slotNumber, this.playerEntity.bi.getCurrentItem()));
         }
      }
   }

   @Override
   public void processPlayer(C03PacketPlayer var1) {
      PacketThreadUtil.checkThreadAndEnqueue(var1, this, this.playerEntity.getServerForPlayer());
      if (this.func_183006_b(var1)) {
         this.kickPlayerFromServer("Invalid move packet received");
      } else {
         WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.am);
         this.field_147366_g = true;
         if (!this.playerEntity.playerConqueredTheEnd) {
            double var3 = this.playerEntity.s;
            double var5 = this.playerEntity.t;
            double var7 = this.playerEntity.u;
            double var9 = 0.0;
            double var11 = var1.method_05060() - this.recoveredField2571;
            double var13 = var1.method_05059() - this.recoveredField2575;
            double var15 = var1.method_05063() - this.recoveredField2570;
            if (var1.method_05066()) {
               var9 = var11 * var11 + var13 * var13 + var15 * var15;
               if (!this.hasMoved && var9 < 0.25) {
                  this.hasMoved = true;
               }
            }

            if (this.hasMoved) {
               this.recoveredField2574 = this.recoveredField2568;
               if (this.playerEntity.m != null) {
                  float var41 = this.playerEntity.y;
                  float var18 = this.playerEntity.z;
                  this.playerEntity.m.updateRiderPosition();
                  double var42 = this.playerEntity.s;
                  double var43 = this.playerEntity.t;
                  double var44 = this.playerEntity.u;
                  if (var1.method_05058()) {
                     var41 = var1.method_05065();
                     var18 = var1.method_05057();
                  }

                  this.playerEntity.C = var1.method_05064();
                  this.playerEntity.method_20784();
                  this.playerEntity.a(var42, var43, var44, var41, var18);
                  if (this.playerEntity.m != null) {
                     this.playerEntity.m.updateRiderPosition();
                  }

                  this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
                  if (this.playerEntity.m != null) {
                     if (var9 > 4.0) {
                        Entity var45 = this.playerEntity.m;
                        this.playerEntity.playerNetServerHandler.sendPacket(new S18PacketEntityTeleport(var45));
                        this.setPlayerLocation(this.playerEntity.s, this.playerEntity.t, this.playerEntity.u, this.playerEntity.y, this.playerEntity.z);
                     }

                     this.playerEntity.m.ai = true;
                  }

                  if (this.hasMoved) {
                     this.recoveredField2571 = this.playerEntity.s;
                     this.recoveredField2575 = this.playerEntity.t;
                     this.recoveredField2570 = this.playerEntity.u;
                  }

                  var2.updateEntity(this.playerEntity);
                  return;
               }

               if (this.playerEntity.bJ()) {
                  this.playerEntity.method_20784();
                  this.playerEntity.a(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, this.playerEntity.y, this.playerEntity.z);
                  var2.updateEntity(this.playerEntity);
                  return;
               }

               double var17 = this.playerEntity.t;
               this.recoveredField2571 = this.playerEntity.s;
               this.recoveredField2575 = this.playerEntity.t;
               this.recoveredField2570 = this.playerEntity.u;
               double var19 = this.playerEntity.s;
               double var21 = this.playerEntity.t;
               double var23 = this.playerEntity.u;
               float var25 = this.playerEntity.y;
               float var26 = this.playerEntity.z;
               if (var1.method_05066() && var1.method_05059() == -999.0) {
                  var1.setMoving(false);
               }

               if (var1.method_05066()) {
                  var19 = var1.method_05060();
                  var21 = var1.method_05059();
                  var23 = var1.method_05063();
                  if (Math.abs(var1.method_05060()) > 3.0E7 || Math.abs(var1.method_05063()) > 3.0E7) {
                     this.kickPlayerFromServer("Illegal position");
                     return;
                  }
               }

               if (var1.method_05058()) {
                  var25 = var1.method_05065();
                  var26 = var1.method_05057();
               }

               this.playerEntity.method_20784();
               this.playerEntity.a(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, var25, var26);
               if (!this.hasMoved) {
                  return;
               }

               double var27 = var19 - this.playerEntity.s;
               double var29 = var21 - this.playerEntity.t;
               double var31 = var23 - this.playerEntity.u;
               double var33 = this.playerEntity.v * this.playerEntity.v + this.playerEntity.w * this.playerEntity.w + this.playerEntity.x * this.playerEntity.x;
               double var35 = var27 * var27 + var29 * var29 + var31 * var31;
               if (var35 - var33 > 100.0 && (!this.serverController.isSinglePlayer() || !this.serverController.getServerOwner().equals(this.playerEntity.z_()))
                  )
                {
                  logger.warn(
                     this.playerEntity.z_() + " moved too quickly! " + var27 + "," + var29 + "," + var31 + " (" + var27 + ", " + var29 + ", " + var31 + ")"
                  );
                  this.setPlayerLocation(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, this.playerEntity.y, this.playerEntity.z);
                  return;
               }

               float var37 = 0.0625F;
               boolean var38 = var2.a(this.playerEntity, this.playerEntity.getEntityBoundingBox().contract(var37, var37, var37)).isEmpty();
               if (this.playerEntity.C && !var1.method_05064() && var29 > 0.0) {
                  this.playerEntity.jump();
               }

               this.playerEntity.d(var27, var29, var31);
               this.playerEntity.C = var1.method_05064();
               var27 = var19 - this.playerEntity.s;
               var29 = var21 - this.playerEntity.t;
               if (var29 > -0.5 || var29 < 0.5) {
                  var29 = 0.0;
               }

               var31 = var23 - this.playerEntity.u;
               var35 = var27 * var27 + var29 * var29 + var31 * var31;
               boolean var39 = false;
               if (var35 > 0.0625 && !this.playerEntity.bJ() && !this.playerEntity.theItemInWorldManager.isCreative()) {
                  var39 = true;
                  logger.warn(this.playerEntity.z_() + " moved wrongly!");
               }

               this.playerEntity.a(var19, var21, var23, var25, var26);
               this.playerEntity.addMovementStat(this.playerEntity.s - var3, this.playerEntity.t - var5, this.playerEntity.u - var7);
               if (!this.playerEntity.T) {
                  boolean var40 = var2.a(this.playerEntity, this.playerEntity.getEntityBoundingBox().contract(var37, var37, var37)).isEmpty();
                  if (var38 && (var39 || !var40) && !this.playerEntity.bJ()) {
                     this.setPlayerLocation(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, var25, var26);
                     return;
                  }
               }

               AxisAlignedBB var50 = this.playerEntity.getEntityBoundingBox().expand(var37, var37, var37).addCoord(0.0, -0.55, 0.0);
               if (this.serverController.method_06894() || this.playerEntity.bA.allowFlying || var2.checkBlockCollision(var50)) {
                  this.recoveredField2569 = 0;
               } else if (var29 >= -0.03125) {
                  this.recoveredField2569++;
                  if (this.recoveredField2569 > 80) {
                     logger.warn(this.playerEntity.z_() + " was kicked for floating too long!");
                     this.kickPlayerFromServer("Flying is not enabled on this server");
                     return;
                  }
               }

               this.playerEntity.C = var1.method_05064();
               this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
               this.playerEntity.handleFalling(this.playerEntity.t - var17, var1.method_05064());
            } else if (this.recoveredField2568 - this.recoveredField2574 > 20) {
               this.setPlayerLocation(this.recoveredField2571, this.recoveredField2575, this.recoveredField2570, this.playerEntity.y, this.playerEntity.z);
            }
         }
      }
   }
}
