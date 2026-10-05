package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S36PacketSignEditorOpen implements Packet<INetHandlerPlayClient> {
   public BlockPos signPosition;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeBlockPos(this.signPosition);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
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
