package net.minecraft.network.play.server;

import io.netty.handler.ssl.JettyNpnSslEngine$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S05PacketSpawnPosition implements Packet<INetHandlerPlayClient> {
   public JettyNpnSslEngine$1 field_0000;
   public BlockPos spawnBlockPos;

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.spawnBlockPos = var1.readBlockPos();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPosition(this);
   }

   public S05PacketSpawnPosition() {
   }

   public BlockPos getSpawnPos() {
      return this.spawnBlockPos;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeBlockPos(this.spawnBlockPos);
   }

   public S05PacketSpawnPosition(BlockPos var1) {
      this.spawnBlockPos = var1;
   }
}
