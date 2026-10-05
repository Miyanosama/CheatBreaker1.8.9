package io.netty.channel;

import net.minecraft.client.renderer.vertex.VertexFormat;

public class FixedRecvByteBufAllocator implements RecvByteBufAllocator {
   public RecvByteBufAllocator$Handle handle;
   public VertexFormat __junk4490553182931363308;

   @Override
   public RecvByteBufAllocator$Handle newHandle() {
      return this.handle;
   }

   public FixedRecvByteBufAllocator(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("bufferSize must greater than 0: " + var1);
      } else {
         this.handle = new FixedRecvByteBufAllocator$HandleImpl(var1);
      }
   }
}
