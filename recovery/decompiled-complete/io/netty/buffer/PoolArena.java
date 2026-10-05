package io.netty.buffer;

import io.netty.util.ReferenceCountUtil$ReleasingTask;
import io.netty.util.internal.StringUtil;
import net.minecraft.entity.passive.EntityCow;

public abstract class PoolArena<T> {
   public int numSmallSubpagePools;
   public PoolSubpage<T>[] tinySubpagePools;
   public PoolChunkList<T> qInit;
   public int maxOrder;
   public PoolChunkList<T> q000;
   public PoolChunkList<T> q025;
   public PooledByteBufAllocator parent;
   public PoolChunkList<T> q075;
   public PoolSubpage<T>[] smallSubpagePools;
   public PoolChunkList<T> q100;
   public int chunkSize;
   public ReferenceCountUtil$ReleasingTask __junk1961594707193861962;
   public static int numTinySubpagePools;
   public EntityCow __junk436379172009029791;
   public int pageSize;
   public PoolChunkList<T> q050;
   public int subpageOverflowMask;
   public int pageShifts;

   public synchronized void allocateNormal(PooledByteBuf<T> var1, int var2, int var3) {
      if (!this.q050.allocate(var1, var2, var3)
         && !this.q025.allocate(var1, var2, var3)
         && !this.q000.allocate(var1, var2, var3)
         && !this.qInit.allocate(var1, var2, var3)
         && !this.q075.allocate(var1, var2, var3)
         && !this.q100.allocate(var1, var2, var3)) {
         PoolChunk var4 = this.newChunk(this.pageSize, this.maxOrder, this.pageShifts, this.chunkSize);
         long var5 = var4.allocate(var3);
         if (!$assertionsDisabled && var5 <= (-332557146623208350L & 332557146138888836L)) {
            throw new AssertionError();
         } else {
            var4.initBuf(var1, var5, var2);
            this.qInit.add(var4);
         }
      }
   }

   public PoolSubpage<T>[] newSubpagePoolArray(int var1) {
      return new PoolSubpage[var1];
   }

   public boolean isTinyOrSmall(int var1) {
      return (var1 & this.subpageOverflowMask) == 0;
   }

   public void allocateHuge(PooledByteBuf<T> var1, int var2) {
      var1.initUnpooled(this.newUnpooledChunk(var2), var2);
   }

   public PoolSubpage<T> findSubpagePoolHead(int var1) {
      int var2;
      PoolSubpage[] var3;
      if (isTiny(var1)) {
         var2 = var1 >>> 4;
         var3 = this.tinySubpagePools;
      } else {
         var2 = 0;

         for (int var4 = var1 >>> 10; var4 != 0; var2++) {
            var4 >>>= 1;
         }

         var3 = this.smallSubpagePools;
      }

      return var3[var2];
   }

   public PoolSubpage<T> newSubpagePoolHead(int var1) {
      PoolSubpage var2 = new PoolSubpage(var1);
      var2.prev = var2;
      var2.next = var2;
      return var2;
   }

   public void free(PoolChunk<T> var1, long var2, int var4) {
      if (var1.unpooled) {
         this.destroyChunk(var1);
      } else {
         PoolThreadCache var5 = this.parent.threadCache.get();
         if (var5.add(this, var1, var2, var4)) {
            return;
         }

         synchronized (this) {
            var1.parent.free(var1, var2);
         }
      }
   }

   public static boolean isTiny(int var0) {
      return (var0 & -512) == 0;
   }

   public abstract void memoryCopy(T var1, int var2, T var3, int var4, int var5);

   @Override
   public synchronized String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Chunk(s) at 0~25%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.qInit);
      var1.append(StringUtil.NEWLINE);
      var1.append("Chunk(s) at 0~50%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.q000);
      var1.append(StringUtil.NEWLINE);
      var1.append("Chunk(s) at 25~75%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.q025);
      var1.append(StringUtil.NEWLINE);
      var1.append("Chunk(s) at 50~100%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.q050);
      var1.append(StringUtil.NEWLINE);
      var1.append("Chunk(s) at 75~100%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.q075);
      var1.append(StringUtil.NEWLINE);
      var1.append("Chunk(s) at 100%:");
      var1.append(StringUtil.NEWLINE);
      var1.append(this.q100);
      var1.append(StringUtil.NEWLINE);
      var1.append("tiny subpages:");

      for (int var2 = 1; var2 < this.tinySubpagePools.length; var2++) {
         PoolSubpage var3 = this.tinySubpagePools[var2];
         if (var3.next != var3) {
            var1.append(StringUtil.NEWLINE);
            var1.append(var2);
            var1.append(": ");
            PoolSubpage var4 = var3.next;

            while (true) {
               var1.append(var4);
               var4 = var4.next;
               if (var4 == var3) {
                  break;
               }
            }
         }
      }

      var1.append(StringUtil.NEWLINE);
      var1.append("small subpages:");

      for (int var5 = 1; var5 < this.smallSubpagePools.length; var5++) {
         PoolSubpage var6 = this.smallSubpagePools[var5];
         if (var6.next != var6) {
            var1.append(StringUtil.NEWLINE);
            var1.append(var5);
            var1.append(": ");
            PoolSubpage var7 = var6.next;

            while (true) {
               var1.append(var7);
               var7 = var7.next;
               if (var7 == var6) {
                  break;
               }
            }
         }
      }

      var1.append(StringUtil.NEWLINE);
      return var1.toString();
   }

   public static int smallIdx(int var0) {
      int var1 = 0;

      for (int var2 = var0 >>> 10; var2 != 0; var1++) {
         var2 >>>= 1;
      }

      return var1;
   }

   public abstract PoolChunk<T> newUnpooledChunk(int var1);

   public abstract PoolChunk<T> newChunk(int var1, int var2, int var3, int var4);

   public static int tinyIdx(int var0) {
      return var0 >>> 4;
   }

   public void allocate(PoolThreadCache var1, PooledByteBuf<T> var2, int var3) {
      int var4 = this.normalizeCapacity(var3);
      if (this.isTinyOrSmall(var4)) {
         int var5;
         PoolSubpage[] var6;
         if (isTiny(var4)) {
            if (var1.allocateTiny(this, var2, var3, var4)) {
               return;
            }

            var5 = tinyIdx(var4);
            var6 = this.tinySubpagePools;
         } else {
            if (var1.allocateSmall(this, var2, var3, var4)) {
               return;
            }

            var5 = smallIdx(var4);
            var6 = this.smallSubpagePools;
         }

         synchronized (this) {
            PoolSubpage var8 = var6[var5];
            PoolSubpage var9 = var8.next;
            if (var9 != var8) {
               if ($assertionsDisabled || var9.doNotDestroy && var9.elemSize == var4) {
                  long var10 = var9.allocate();
                  if (!$assertionsDisabled && var10 < (2618127126934587393L & -2618127127004520244L)) {
                     throw new AssertionError();
                  }

                  var9.chunk.initBufWithSubpage(var2, var10, var3);
                  return;
               }

               throw new AssertionError();
            }
         }
      } else {
         if (var4 > this.chunkSize) {
            this.allocateHuge(var2, var3);
            return;
         }

         if (var1.allocateNormal(this, var2, var3, var4)) {
            return;
         }
      }

      this.allocateNormal(var2, var3, var4);
   }

   public void reallocate(PooledByteBuf<T> var1, int var2, boolean var3) {
      if (var2 >= 0 && var2 <= var1.maxCapacity()) {
         int var4 = var1.length;
         if (var4 != var2) {
            PoolChunk var5 = var1.chunk;
            long var6 = var1.handle;
            Object var8 = var1.memory;
            int var9 = var1.offset;
            int var10 = var1.maxLength;
            int var11 = var1.readerIndex();
            int var12 = var1.writerIndex();
            this.allocate(this.parent.threadCache.get(), var1, var2);
            if (var2 > var4) {
               this.memoryCopy((T)var8, var9, var1.memory, var1.offset, var4);
            } else if (var2 < var4) {
               if (var11 < var2) {
                  if (var12 > var2) {
                     var12 = var2;
                  }

                  this.memoryCopy((T)var8, var9 + var11, var1.memory, var1.offset + var11, var12 - var11);
               } else {
                  var12 = var2;
                  var11 = var2;
               }
            }

            var1.setIndex(var11, var12);
            if (var3) {
               this.free(var5, var6, var10);
            }
         }
      } else {
         throw new IllegalArgumentException("newCapacity: " + var2);
      }
   }

   public int normalizeCapacity(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("capacity: " + var1 + " (expected: 0+)");
      } else if (var1 >= this.chunkSize) {
         return var1;
      } else if (!isTiny(var1)) {
         int var2 = var1 - 1;
         var2 |= var2 >>> 1;
         var2 |= var2 >>> 2;
         var2 |= var2 >>> 4;
         var2 |= var2 >>> 8;
         var2 |= var2 >>> 16;
         if (++var2 < 0) {
            var2 >>>= 1;
         }

         return var2;
      } else {
         return (var1 & 15) == 0 ? var1 : (var1 & -16) + 16;
      }
   }

   public PooledByteBuf<T> allocate(PoolThreadCache var1, int var2, int var3) {
      PooledByteBuf var4 = this.newByteBuf(var3);
      this.allocate(var1, var4, var2);
      return var4;
   }

   public PoolArena(PooledByteBufAllocator var1, int var2, int var3, int var4, int var5) {
      this.parent = var1;
      this.pageSize = var2;
      this.maxOrder = var3;
      this.pageShifts = var4;
      this.chunkSize = var5;
      this.subpageOverflowMask = ~(var2 - 1);
      this.tinySubpagePools = this.newSubpagePoolArray(32);

      for (int var6 = 0; var6 < this.tinySubpagePools.length; var6++) {
         this.tinySubpagePools[var6] = this.newSubpagePoolHead(var2);
      }

      this.numSmallSubpagePools = var4 - 9;
      this.smallSubpagePools = this.newSubpagePoolArray(this.numSmallSubpagePools);

      for (int var7 = 0; var7 < this.smallSubpagePools.length; var7++) {
         this.smallSubpagePools[var7] = this.newSubpagePoolHead(var2);
      }

      this.q100 = new PoolChunkList<>(this, null, 100, Integer.MAX_VALUE);
      this.q075 = new PoolChunkList<>(this, this.q100, 75, 100);
      this.q050 = new PoolChunkList<>(this, this.q075, 50, 100);
      this.q025 = new PoolChunkList<>(this, this.q050, 25, 75);
      this.q000 = new PoolChunkList<>(this, this.q025, 1, 50);
      this.qInit = new PoolChunkList<>(this, this.q000, Integer.MIN_VALUE, 25);
      this.q100.prevList = this.q075;
      this.q075.prevList = this.q050;
      this.q050.prevList = this.q025;
      this.q025.prevList = this.q000;
      this.q000.prevList = null;
      this.qInit.prevList = this.qInit;
   }

   public abstract void destroyChunk(PoolChunk<T> var1);

   public abstract boolean isDirect();

   public abstract PooledByteBuf<T> newByteBuf(int var1);
}
