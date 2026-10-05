package net.minecraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class WorldManager implements IWorldAccess {
   public MinecraftServer mcServer;
   public WorldServer theWorldServer;

   @Override
   public void spawnParticle(int var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
   }

   @Override
   public void sendBlockBreakProgress(int var1, BlockPos var2, int var3) {
      for (EntityPlayerMP var5 : this.mcServer.getConfigurationManager().getPlayerList()) {
         if (var5 != null && var5.o == this.theWorldServer && var5.F() != var1) {
            double var6 = var2.getX() - var5.s;
            double var8 = var2.getY() - var5.t;
            double var10 = var2.getZ() - var5.u;
            if (var6 * var6 + var8 * var8 + var10 * var10 < 1024.0) {
               var5.playerNetServerHandler.sendPacket(new S25PacketBlockBreakAnim(var1, var2, var3));
            }
         }
      }
   }

   @Override
   public void playRecord(String var1, BlockPos var2) {
   }

   @Override
   public void onEntityRemoved(Entity var1) {
      this.theWorldServer.getEntityTracker().untrackEntity(var1);
      this.theWorldServer.Z().func_181140_a(var1);
   }

   @Override
   public void markBlockRangeForRenderUpdate(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   @Override
   public void broadcastSound(int var1, BlockPos var2, int var3) {
      this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S28PacketEffect(var1, var2, var3, true));
   }

   public WorldManager(MinecraftServer var1, WorldServer var2) {
      this.mcServer = var1;
      this.theWorldServer = var2;
   }

   @Override
   public void playSound(String var1, double var2, double var4, double var6, float var8, float var9) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNear(
            var2,
            var4,
            var6,
            var8 > 1.0F ? 16.0F * var8 : 16.0,
            this.theWorldServer.t.getDimensionId(),
            new S29PacketSoundEffect(var1, var2, var4, var6, var8, var9)
         );
   }

   @Override
   public void notifyLightSet(BlockPos var1) {
   }

   @Override
   public void markBlockForUpdate(BlockPos var1) {
      this.theWorldServer.getPlayerManager().markBlockForUpdate(var1);
   }

   @Override
   public void playAuxSFX(EntityPlayer var1, int var2, BlockPos var3, int var4) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNearExcept(
            var1, var3.getX(), var3.getY(), var3.getZ(), 64.0, this.theWorldServer.t.getDimensionId(), new S28PacketEffect(var2, var3, var4, false)
         );
   }

   @Override
   public void playSoundToNearExcept(EntityPlayer var1, String var2, double var3, double var5, double var7, float var9, float var10) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNearExcept(
            var1,
            var3,
            var5,
            var7,
            var9 > 1.0F ? 16.0F * var9 : 16.0,
            this.theWorldServer.t.getDimensionId(),
            new S29PacketSoundEffect(var2, var3, var5, var7, var9, var10)
         );
   }

   @Override
   public void onEntityAdded(Entity var1) {
      this.theWorldServer.getEntityTracker().trackEntity(var1);
   }
}
