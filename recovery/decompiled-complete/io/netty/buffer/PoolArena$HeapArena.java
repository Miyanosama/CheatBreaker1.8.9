package io.netty.buffer;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.network.NetworkManager;

public class PoolArena$HeapArena extends PoolArena<byte[]> {
   public BlockTrapDoor __junk2718716805253739320;
   public NetworkManager __junk492426513579132034;

   @Override
   public void destroyChunk(PoolChunk<byte[]> var1) {
   }

   @Override
   public PoolChunk<byte[]> newChunk(int var1, int var2, int var3, int var4) {
      return new PoolChunk<>(this, new byte[var4], var1, var2, var3, var4);
   }

   @Override
   public PooledByteBuf<byte[]> newByteBuf(int var1) {
      return PooledHeapByteBuf.newInstance(var1);
   }

   public void memoryCopy(byte[] var1, int var2, byte[] var3, int var4, int var5) {
      if (var5 != 0) {
         System.arraycopy(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public boolean isDirect() {
      return false;
   }

   public PoolArena$HeapArena(PooledByteBufAllocator var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
   }

   @Override
   public PoolChunk<byte[]> newUnpooledChunk(int var1) {
      return new PoolChunk<>(this, new byte[var1], var1);
   }
}
