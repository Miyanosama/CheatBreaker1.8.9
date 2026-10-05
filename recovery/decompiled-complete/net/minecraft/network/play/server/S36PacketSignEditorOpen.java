package net.minecraft.network.play.server;

import io.netty.buffer.PoolThreadCache$MemoryRegionCache$Entry;
import io.netty.util.internal.PendingWrite$1;
import io.netty.util.internal.chmv8.ForkJoinTask$AdaptedRunnable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import recovered.unidentified.UnidentifiedClass0300;

public class S36PacketSignEditorOpen implements Packet<INetHandlerPlayClient> {
   public PendingWrite$1 field_0003;
   public BlockPos signPosition;
   public UnidentifiedClass0300 field_0002;
   public PoolThreadCache$MemoryRegionCache$Entry field_0004;
   public ForkJoinTask$AdaptedRunnable field_0000;
   public EntityAIFollowOwner field_0001;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeBlockPos(this.signPosition);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.signPosition = var1.readBlockPos();
   }

   public S36PacketSignEditorOpen() {
   }

   public S36PacketSignEditorOpen(BlockPos var1) {
      this.signPosition = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSignEditorOpen(this);
   }

   public BlockPos getSignPosition() {
      return this.signPosition;
   }
}
