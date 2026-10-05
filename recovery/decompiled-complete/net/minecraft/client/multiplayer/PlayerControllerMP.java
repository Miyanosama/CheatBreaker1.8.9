package net.minecraft.client.multiplayer;

import com.cheatbreaker.client.module.type.ReachDisplayModule;
import com.cheatbreaker.client.nethandler.server.PacketTeammates;
import io.netty.handler.codec.socks.SocksMessageType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity$Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging$Action;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings$GameType;

public class PlayerControllerMP {
   public Minecraft mc;
   public int blockHitDelay;
   public ItemStack currentItemHittingBlock;
   public SocksMessageType field_0008;
   public float stepSoundTickCounter;
   public BlockPos currentBlock = new BlockPos(-1, -1, -1);
   public WorldSettings$GameType currentGameType = WorldSettings$GameType.SURVIVAL;
   public boolean isHittingBlock;
   public int currentPlayerItem;
   public float curBlockDamageMP;
   public PacketTeammates field_0000;
   public NetHandlerPlayClient netClientHandler;

   public WorldSettings$GameType getCurrentGameType() {
      return this.currentGameType;
   }

   public boolean method_19874() {
      return this.currentGameType == WorldSettings$GameType.SPECTATOR;
   }

   public boolean interactWithEntitySendPacket(EntityPlayer var1, Entity var2) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(var2, C02PacketUseEntity$Action.INTERACT));
      return this.currentGameType != WorldSettings$GameType.SPECTATOR && var1.interactWith(var2);
   }

   public void flipPlayer(EntityPlayer var1) {
      var1.y = -180.0F;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.currentGameType = var1;
      this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.bA);
   }

   public boolean isPlayerRightClickingOnEntity(EntityPlayer var1, Entity var2, MovingObjectPosition var3) {
      this.syncCurrentPlayItem();
      Vec3 var4 = new Vec3(var3.hitVec.xCoord - var2.s, var3.hitVec.yCoord - var2.t, var3.hitVec.zCoord - var2.u);
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(var2, var4));
      return this.currentGameType != WorldSettings$GameType.SPECTATOR && var2.interactAt(var1, var4);
   }

   public EntityPlayerSP func_178892_a(World var1, StatFileWriter var2) {
      return new EntityPlayerSP(this.mc, var1, this.netClientHandler, var2);
   }

   public float getBlockReachDistance() {
      return this.currentGameType.isCreative() ? 5.0F : 4.5F;
   }

   public void sendSlotPacket(ItemStack var1, int var2) {
      if (this.currentGameType.isCreative()) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(var2, var1));
      }
   }

   public boolean onPlayerDestroyBlock(BlockPos var1, EnumFacing var2) {
      if (this.currentGameType.isAdventure()) {
         if (this.currentGameType == WorldSettings$GameType.SPECTATOR) {
            return false;
         }

         if (!this.mc.thePlayer.cn()) {
            Block var3 = this.mc.theWorld.getBlockState(var1).getBlock();
            ItemStack var4 = this.mc.thePlayer.getCurrentEquippedItem();
            if (var4 == null) {
               return false;
            }

            if (!var4.canDestroy(var3)) {
               return false;
            }
         }
      }

      if (this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
         return false;
      } else {
         WorldClient var8 = this.mc.theWorld;
         IBlockState var9 = var8.getBlockState(var1);
         Block var5 = var9.getBlock();
         if (var5.getMaterial() == Material.air) {
            return false;
         } else {
            var8.b(2001, var1, Block.getStateId(var9));
            boolean var6 = var8.setBlockToAir(var1);
            if (var6) {
               var5.onBlockDestroyedByPlayer(var8, var1, var9);
            }

            this.currentBlock = new BlockPos(this.currentBlock.getX(), -1, this.currentBlock.getZ());
            if (!this.currentGameType.isCreative()) {
               ItemStack var7 = this.mc.thePlayer.getCurrentEquippedItem();
               if (var7 != null) {
                  var7.onBlockDestroyed(var8, var5, var1, this.mc.thePlayer);
                  if (var7.stackSize == 0) {
                     this.mc.thePlayer.ca();
                  }
               }
            }

            return var6;
         }
      }
   }

   public boolean isInCreativeMode() {
      return this.currentGameType.isCreative();
   }

   public void setPlayerCapabilities(EntityPlayer var1) {
      this.currentGameType.configurePlayerCapabilities(var1.bA);
   }

   public boolean onPlayerRightClick(EntityPlayerSP var1, WorldClient var2, ItemStack var3, BlockPos var4, EnumFacing var5, Vec3 var6) {
      this.syncCurrentPlayItem();
      float var7 = (float)(var6.xCoord - var4.getX());
      float var8 = (float)(var6.yCoord - var4.getY());
      float var9 = (float)(var6.zCoord - var4.getZ());
      boolean var10 = false;
      if (!this.mc.theWorld.af().contains(var4)) {
         return false;
      } else {
         if (this.currentGameType != WorldSettings$GameType.SPECTATOR) {
            IBlockState var11 = var2.getBlockState(var4);
            if ((!var1.isSneaking() || var1.getHeldItem() == null) && var11.getBlock().onBlockActivated(var2, var4, var11, var1, var5, var7, var8, var9)) {
               var10 = true;
            }

            if (!var10 && var3 != null && var3.getItem() instanceof ItemBlock) {
               ItemBlock var12 = (ItemBlock)var3.getItem();
               if (!var12.canPlaceBlockOnSide(var2, var4, var5, var1, var3)) {
                  return false;
               }
            }
         }

         this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(var4, var5.getIndex(), var1.bi.getCurrentItem(), var7, var8, var9));
         if (var10 || this.currentGameType == WorldSettings$GameType.SPECTATOR) {
            return true;
         } else if (var3 == null) {
            return false;
         } else if (this.currentGameType.isCreative()) {
            int var14 = var3.getMetadata();
            int var15 = var3.stackSize;
            boolean var13 = var3.onItemUse(var1, var2, var4, var5, var7, var8, var9);
            var3.setItemDamage(var14);
            var3.stackSize = var15;
            return var13;
         } else {
            return var3.onItemUse(var1, var2, var4, var5, var7, var8, var9);
         }
      }
   }

   public boolean getIsHittingBlock() {
      return this.isHittingBlock;
   }

   public boolean onPlayerDamageBlock(BlockPos var1, EnumFacing var2) {
      this.syncCurrentPlayItem();
      if (this.blockHitDelay > 0) {
         this.blockHitDelay--;
         return true;
      } else if (this.currentGameType.isCreative() && this.mc.theWorld.af().contains(var1)) {
         this.blockHitDelay = 5;
         this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.START_DESTROY_BLOCK, var1, var2));
         clickBlockCreative(this.mc, this, var1, var2);
         return true;
      } else if (this.isHittingPosition(var1)) {
         Block var3 = this.mc.theWorld.getBlockState(var1).getBlock();
         if (var3.getMaterial() == Material.air) {
            this.isHittingBlock = false;
            return false;
         } else {
            this.curBlockDamageMP = this.curBlockDamageMP + var3.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.o, var1);
            if (this.stepSoundTickCounter % 4.0F == 0.0F) {
               this.mc
                  .getSoundHandler()
                  .playSound(
                     new PositionedSoundRecord(
                        new ResourceLocation(var3.stepSound.getStepSound()),
                        (var3.stepSound.getVolume() + 1.0F) / 8.0F,
                        var3.stepSound.getFrequency() * 0.5F,
                        var1.getX() + 0.5F,
                        var1.getY() + 0.5F,
                        var1.getZ() + 0.5F
                     )
                  );
            }

            this.stepSoundTickCounter++;
            if (this.curBlockDamageMP >= 1.0F) {
               this.isHittingBlock = false;
               this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.STOP_DESTROY_BLOCK, var1, var2));
               this.onPlayerDestroyBlock(var1, var2);
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.blockHitDelay = 5;
            }

            this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.F(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            return true;
         }
      } else {
         return this.clickBlock(var1, var2);
      }
   }

   public void syncCurrentPlayItem() {
      int var1 = this.mc.thePlayer.bi.currentItem;
      if (var1 != this.currentPlayerItem) {
         this.currentPlayerItem = var1;
         this.netClientHandler.addToSendQueue(new C09PacketHeldItemChange(this.currentPlayerItem));
      }
   }

   public boolean sendUseItem(EntityPlayer var1, World var2, ItemStack var3) {
      if (this.currentGameType == WorldSettings$GameType.SPECTATOR) {
         return false;
      } else {
         this.syncCurrentPlayItem();
         this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(var1.bi.getCurrentItem()));
         int var4 = var3.stackSize;
         ItemStack var5 = var3.useItemRightClick(var2, var1);
         if (var5 != var3 || var5 != null && var5.stackSize != var4) {
            var1.bi.mainInventory[var1.bi.currentItem] = var5;
            if (var5.stackSize == 0) {
               var1.bi.mainInventory[var1.bi.currentItem] = null;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean method_19878() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean method_19872() {
      return this.currentGameType == WorldSettings$GameType.SPECTATOR;
   }

   public boolean extendedReach() {
      return this.currentGameType.isCreative();
   }

   public boolean isHittingPosition(BlockPos var1) {
      ItemStack var2 = this.mc.thePlayer.getHeldItem();
      boolean var3 = this.currentItemHittingBlock == null && var2 == null;
      if (this.currentItemHittingBlock != null && var2 != null) {
         var3 = var2.getItem() == this.currentItemHittingBlock.getItem()
            && ItemStack.areItemStackTagsEqual(var2, this.currentItemHittingBlock)
            && (var2.isItemStackDamageable() || var2.getMetadata() == this.currentItemHittingBlock.getMetadata());
      }

      return var1.equals(this.currentBlock) && var3;
   }

   public boolean clickBlock(BlockPos var1, EnumFacing var2) {
      if (this.currentGameType.isAdventure()) {
         if (this.currentGameType == WorldSettings$GameType.SPECTATOR) {
            return false;
         }

         if (!this.mc.thePlayer.cn()) {
            Block var3 = this.mc.theWorld.getBlockState(var1).getBlock();
            ItemStack var4 = this.mc.thePlayer.getCurrentEquippedItem();
            if (var4 == null) {
               return false;
            }

            if (!var4.canDestroy(var3)) {
               return false;
            }
         }
      }

      if (!this.mc.theWorld.af().contains(var1)) {
         return false;
      } else {
         if (this.currentGameType.isCreative()) {
            this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.START_DESTROY_BLOCK, var1, var2));
            clickBlockCreative(this.mc, this, var1, var2);
            this.blockHitDelay = 5;
         } else if (!this.isHittingBlock || !this.isHittingPosition(var1)) {
            if (this.isHittingBlock) {
               this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.ABORT_DESTROY_BLOCK, this.currentBlock, var2));
            }

            this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.START_DESTROY_BLOCK, var1, var2));
            Block var5 = this.mc.theWorld.getBlockState(var1).getBlock();
            boolean var6 = var5.getMaterial() != Material.air;
            if (var6 && this.curBlockDamageMP == 0.0F) {
               var5.onBlockClicked(this.mc.theWorld, var1, this.mc.thePlayer);
            }

            if (var6 && var5.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.o, var1) >= 1.0F) {
               this.onPlayerDestroyBlock(var1, var2);
            } else {
               this.isHittingBlock = true;
               this.currentBlock = var1;
               this.currentItemHittingBlock = this.mc.thePlayer.getHeldItem();
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.F(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   public boolean isRidingHorse() {
      return this.mc.thePlayer.au() && this.mc.thePlayer.m instanceof EntityHorse;
   }

   public void attackEntity(EntityPlayer var1, Entity var2) {
      if (var1 == this.mc.thePlayer) {
         Vec3 var3 = this.mc.getRenderViewEntity().getPositionEyes(1.0F);
         ReachDisplayModule.field_0004 = this.mc.objectMouseOver.hitVec.distanceTo(var3);
         ReachDisplayModule.field_0000 = System.currentTimeMillis();
      }

      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(var2, C02PacketUseEntity$Action.ATTACK));
      if (this.currentGameType != WorldSettings$GameType.SPECTATOR) {
         var1.attackTargetEntityWithCurrentItem(var2);
      }
   }

   public ItemStack windowClick(int var1, int var2, int var3, int var4, EntityPlayer var5) {
      short var6 = var5.bk.getNextTransactionID(var5.bi);
      ItemStack var7 = var5.bk.slotClick(var2, var3, var4, var5);
      this.netClientHandler.addToSendQueue(new C0EPacketClickWindow(var1, var2, var3, var4, var7, var6));
      return var7;
   }

   public PlayerControllerMP(Minecraft var1, NetHandlerPlayClient var2) {
      this.mc = var1;
      this.netClientHandler = var2;
   }

   public void resetBlockRemoving() {
      if (this.isHittingBlock) {
         this.netClientHandler
            .addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.ABORT_DESTROY_BLOCK, this.currentBlock, EnumFacing.DOWN));
         this.isHittingBlock = false;
         this.curBlockDamageMP = 0.0F;
         this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.F(), this.currentBlock, -1);
      }
   }

   public boolean isNotCreative() {
      return !this.currentGameType.isCreative();
   }

   public void updateController() {
      this.syncCurrentPlayItem();
      if (this.netClientHandler.getNetworkManager().isChannelOpen()) {
         this.netClientHandler.getNetworkManager().processReceivedPackets();
      } else {
         this.netClientHandler.getNetworkManager().checkDisconnected();
      }
   }

   public void sendPacketDropItem(ItemStack var1) {
      if (this.currentGameType.isCreative() && var1 != null) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(-1, var1));
      }
   }

   public void onStoppedUsingItem(EntityPlayer var1) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging$Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
      var1.stopUsingItem();
   }

   public void sendEnchantPacket(int var1, int var2) {
      this.netClientHandler.addToSendQueue(new C11PacketEnchantItem(var1, var2));
   }

   public static void clickBlockCreative(Minecraft var0, PlayerControllerMP var1, BlockPos var2, EnumFacing var3) {
      if (!var0.theWorld.extinguishFire(var0.thePlayer, var2, var3)) {
         var1.onPlayerDestroyBlock(var2, var3);
      }
   }

   public boolean shouldDrawHUD() {
      return this.currentGameType.isSurvivalOrAdventure();
   }
}
