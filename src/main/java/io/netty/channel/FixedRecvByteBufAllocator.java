package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.network.play.server.S23PacketBlockChange;

public class FixedRecvByteBufAllocator implements RecvByteBufAllocator {
   public RecvByteBufAllocator.Handle handle;

   @Override
   public RecvByteBufAllocator.Handle newHandle() {
      return this.handle;
   }

   public FixedRecvByteBufAllocator(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("bufferSize must greater than 0: " + var1);
      } else {
         this.handle = new FixedRecvByteBufAllocator.HandleImpl(var1);
      }
   }

   public static final class HandleImpl implements RecvByteBufAllocator.Handle {
      public int bufferSize;

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

      public HandleImpl(int var1) {
         this.bufferSize = var1;
      }
   }
}
