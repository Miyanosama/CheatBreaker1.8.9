package io.netty.buffer;

import io.netty.util.Recycler;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.world.biome.BiomeGenEnd;

public abstract class PooledByteBuf<T> extends AbstractReferenceCountedByteBuf {
   public int maxLength;
   public int length;
   public long handle;
   public PoolChunk<T> chunk;
   public T memory;
   public ByteBuffer tmpNioBuf;
   public Recycler.Handle recyclerHandle;
   public static final boolean $assertionsDisabled = !PooledByteBuf.class.desiredAssertionStatus();
   public int offset;

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   public void init(PoolChunk<T> var1, long var2, int var4, int var5, int var6) {
      if (!$assertionsDisabled && var2 < 0L) {
         throw new AssertionError();
      } else if (!$assertionsDisabled && var1 == null) {
         throw new AssertionError();
      } else {
         this.chunk = var1;
         this.handle = var2;
         this.memory = var1.memory;
         this.offset = var4;
         this.length = var5;
         this.maxLength = var6;
         this.setIndex(0, 0);
         this.tmpNioBuf = null;
      }
   }

   @Override
   public void deallocate() {
      if (this.handle >= 0L) {
         long var1 = this.handle;
         this.handle = -1L;
         this.memory = null;
         this.chunk.arena.free(this.chunk, var1, this.maxLength);
         this.recycle();
      }
   }

   public void initUnpooled(PoolChunk<T> var1, int var2) {
      if (!$assertionsDisabled && var1 == null) {
         throw new AssertionError();
      } else {
         this.chunk = var1;
         this.handle = 0L;
         this.memory = var1.memory;
         this.offset = 0;
         this.length = this.maxLength = var2;
         this.setIndex(0, 0);
         this.tmpNioBuf = null;
      }
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.chunk.arena.parent;
   }

   public abstract ByteBuffer newInternalNioBuffer(T var1);

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   public PooledByteBuf(Recycler.Handle var1, int var2) {
      super(var2);
      this.recyclerHandle = var1;
   }

   public void recycle() {
      Recycler.Handle var1 = this.recyclerHandle;
      if (var1 != null) {
         ((Recycler<PooledByteBuf<T>>)this.recycler()).recycle(this, var1);
      }
   }

   public int idx(int var1) {
      return this.offset + var1;
   }

   public ByteBuffer internalNioBuffer() {
      ByteBuffer var1 = this.tmpNioBuf;
      if (var1 == null) {
         this.tmpNioBuf = var1 = this.newInternalNioBuffer(this.memory);
      }

      return var1;
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.ensureAccessible();
      if (this.chunk.unpooled) {
         if (var1 == this.length) {
            return this;
         }
      } else if (var1 > this.length) {
         if (var1 <= this.maxLength) {
            this.length = var1;
            return this;
         }
      } else {
         if (var1 >= this.length) {
            return this;
         }

         if (var1 > this.maxLength >>> 1) {
            if (this.maxLength > 512) {
               this.length = var1;
               this.setIndex(Math.min(this.readerIndex(), var1), Math.min(this.writerIndex(), var1));
               return this;
            }

            if (var1 > this.maxLength - 16) {
               this.length = var1;
               this.setIndex(Math.min(this.readerIndex(), var1), Math.min(this.writerIndex(), var1));
               return this;
            }
         }
      }

      this.chunk.arena.reallocate(this, var1, true);
      return this;
   }

   @Override
   public int capacity() {
      return this.length;
   }

   public abstract Recycler<?> recycler();
}
