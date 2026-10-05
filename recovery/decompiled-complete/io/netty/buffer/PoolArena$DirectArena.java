package io.netty.buffer;

import com.cheatbreaker.client.module.type.TextureOptionsModule;
import io.netty.util.internal.PlatformDependent;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class PoolArena$DirectArena extends PoolArena<ByteBuffer> {
   public TextureOptionsModule __junk390341194550822146;
   public static boolean HAS_UNSAFE = PlatformDependent.hasUnsafe();

   @Override
   public PooledByteBuf<ByteBuffer> newByteBuf(int var1) {
      return (PooledByteBuf<ByteBuffer>)(HAS_UNSAFE ? PooledUnsafeDirectByteBuf.newInstance(var1) : PooledDirectByteBuf.newInstance(var1));
   }

   @Override
   public void destroyChunk(PoolChunk<ByteBuffer> var1) {
      PlatformDependent.freeDirectBuffer((ByteBuffer)var1.memory);
   }

   @Override
   public boolean isDirect() {
      return true;
   }

   @Override
   public PoolChunk<ByteBuffer> newUnpooledChunk(int var1) {
      return new PoolChunk<>(this, ByteBuffer.allocateDirect(var1), var1);
   }

   @Override
   public PoolChunk<ByteBuffer> newChunk(int var1, int var2, int var3, int var4) {
      return new PoolChunk<>(this, ByteBuffer.allocateDirect(var4), var1, var2, var3, var4);
   }

   public PoolArena$DirectArena(PooledByteBufAllocator var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
   }

   public void memoryCopy(ByteBuffer var1, int var2, ByteBuffer var3, int var4, int var5) {
      if (var5 != 0) {
         if (HAS_UNSAFE) {
            PlatformDependent.copyMemory(PlatformDependent.directBufferAddress(var1) + var2, PlatformDependent.directBufferAddress(var3) + var4, var5);
         } else {
            var1 = var1.duplicate();
            var3 = var3.duplicate();
            ((Buffer)var1).position(var2).limit(var2 + var5);
            ((Buffer)var3).position(var4);
            var3.put(var1);
         }
      }
   }
}
