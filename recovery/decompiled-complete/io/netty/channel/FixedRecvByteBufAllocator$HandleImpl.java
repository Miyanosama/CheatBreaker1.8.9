package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.network.play.server.S23PacketBlockChange;

public class FixedRecvByteBufAllocator$HandleImpl implements RecvByteBufAllocator$Handle {
   public S23PacketBlockChange __junk299074948488975965;
   public BlockHalfWoodSlab __junk6200524444109202246;
   public int bufferSize;
   public BlockDoor __junk4535344687888398194;

   @Override
   public ByteBuf allocate(ByteBufAllocator var1) {
      return var1.ioBuffer(this.bufferSize);
   }

   @Override
   public int guess() {
      return this.bufferSize;
   }

   @Override
   public void record(int var1) {
   }

   public FixedRecvByteBufAllocator$HandleImpl(int var1) {
      this.bufferSize = var1;
   }
}
