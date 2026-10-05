package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;

public class UnpooledByteBufAllocator extends AbstractByteBufAllocator {
   public static UnpooledByteBufAllocator DEFAULT = new UnpooledByteBufAllocator(PlatformDependent.directBufferPreferred());

   @Override
   public ByteBuf newHeapBuffer(int var1, int var2) {
      return new UnpooledHeapByteBuf(this, var1, var2);
   }

   @Override
   public boolean isDirectBufferPooled() {
      return false;
   }

   @Override
   public ByteBuf newDirectBuffer(int var1, int var2) {
      Object var3;
      if (PlatformDependent.hasUnsafe()) {
         var3 = new UnpooledUnsafeDirectByteBuf(this, var1, var2);
      } else {
         var3 = new UnpooledDirectByteBuf(this, var1, var2);
      }

      return toLeakAwareBuffer((ByteBuf)var3);
   }

   public UnpooledByteBufAllocator(boolean var1) {
      super(var1);
   }
}
