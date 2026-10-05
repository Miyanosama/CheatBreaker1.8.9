package io.netty.buffer;

import io.netty.handler.codec.http.HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2;

public class PoolChunk<T> {
   public PoolChunk<T> next;
   public PoolChunk<T> prev;
   public byte unusable;
   public int freeBytes;
   public T memory;
   public PoolArena<T> arena;
   public int maxOrder;
   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2 __junk1578725747914178956;
   public byte[] depthMap;
   public byte[] memoryMap;
   public boolean unpooled;
   public int chunkSize;
   public PoolSubpage<T>[] subpages;
   public PoolChunkList<T> parent;
   public int maxSubpageAllocs;
   public int pageShifts;
   public int log2ChunkSize;
   public int pageSize;
   public int subpageOverflowMask;

   public long allocate(int var1) {
      return (var1 & this.subpageOverflowMask) != 0 ? this.allocateRun(var1) : this.allocateSubpage(var1);
   }

   public byte value(int var1) {
      return this.memoryMap[var1];
   }

   public int allocateNode(int var1) {
      byte var2 = 1;
      int var3 = -(1 << var1);
      byte var4 = this.value(var2);
      if (var4 > var1) {
         return -1;
      } else {
         while (var4 < var1 || (var2 & var3) == 0) {
            var2 <<= 1;
            var4 = this.value(var2);
            if (var4 > var1) {
               var2 ^= 1;
               var4 = this.value(var2);
            }
         }

         byte var5 = this.value(var2);
         if ($assertionsDisabled || var5 == var1 && (var2 & var3) == 1 << var1) {
            this.setValue(var2, this.unusable);
            this.updateParentsAlloc(var2);
            return var2;
         } else {
            throw new AssertionError(String.format("val = %d, id & initial = %d, d = %d", var5, var2 & var3, var1));
         }
      }
   }

   public PoolChunk(PoolArena<T> var1, T var2, int var3) {
      this.unpooled = true;
      this.arena = var1;
      this.memory = (T)var2;
      this.memoryMap = null;
      this.depthMap = null;
      this.subpages = null;
      this.subpageOverflowMask = 0;
      this.pageSize = 0;
      this.pageShifts = 0;
      this.maxOrder = 0;
      this.unusable = (byte)(this.maxOrder + 1);
      this.chunkSize = var3;
      this.log2ChunkSize = log2(this.chunkSize);
      this.maxSubpageAllocs = 0;
   }

   public PoolChunk(PoolArena<T> var1, T var2, int var3, int var4, int var5, int var6) {
      this.unpooled = false;
      this.arena = var1;
      this.memory = (T)var2;
      this.pageSize = var3;
      this.pageShifts = var5;
      this.maxOrder = var4;
      this.chunkSize = var6;
      this.unusable = (byte)(var4 + 1);
      this.log2ChunkSize = log2(var6);
      this.subpageOverflowMask = ~(var3 - 1);
      this.freeBytes = var6;
      if (!$assertionsDisabled && var4 >= 30) {
         throw new AssertionError("maxOrder should be < 30, but is: " + var4);
      } else {
         this.maxSubpageAllocs = 1 << var4;
         this.memoryMap = new byte[this.maxSubpageAllocs << 1];
         this.depthMap = new byte[this.memoryMap.length];
         int var7 = 1;

         for (int var8 = 0; var8 <= var4; var8++) {
            int var9 = 1 << var8;

            for (int var10 = 0; var10 < var9; var10++) {
               this.memoryMap[var7] = (byte)var8;
               this.depthMap[var7] = (byte)var8;
               var7++;
            }
         }

         this.subpages = this.newSubpageArray(this.maxSubpageAllocs);
      }
   }

   public void setValue(int var1, byte var2) {
      this.memoryMap[var1] = var2;
   }

   public void initBufWithSubpage(PooledByteBuf<T> var1, long var2, int var4, int var5) {
      if (!$assertionsDisabled && var4 == 0) {
         throw new AssertionError();
      } else {
         int var6 = (int)var2;
         PoolSubpage var7 = this.subpages[this.subpageIdx(var6)];
         if (!$assertionsDisabled && !var7.doNotDestroy) {
            throw new AssertionError();
         } else if (!$assertionsDisabled && var5 > var7.elemSize) {
            throw new AssertionError();
         } else {
            var1.init(this, var2, this.runOffset(var6) + (var4 & 1073741823) * var7.elemSize, var5, var7.elemSize);
         }
      }
   }

   public int subpageIdx(int var1) {
      return var1 ^ this.maxSubpageAllocs;
   }

   public int runLength(int var1) {
      return 1 << this.log2ChunkSize - this.depth(var1);
   }

   public static int log2(int var0) {
      return 31 - Integer.numberOfLeadingZeros(var0);
   }

   public long allocateRun(int var1) {
      int var2 = this.maxOrder - (log2(var1) - this.pageShifts);
      int var3 = this.allocateNode(var2);
      if (var3 < 0) {
         return var3;
      } else {
         this.freeBytes = this.freeBytes - this.runLength(var3);
         return var3;
      }
   }

   public int usage() {
      int var1 = this.freeBytes;
      if (var1 == 0) {
         return 100;
      } else {
         int var2 = (int)(var1 * (3403580496127462500L & -3403580497357438620L) / this.chunkSize);
         return var2 == 0 ? 99 : 100 - var2;
      }
   }

   public int runOffset(int var1) {
      int var2 = var1 ^ 1 << this.depth(var1);
      return var2 * this.runLength(var1);
   }

   public void initBuf(PooledByteBuf<T> var1, long var2, int var4) {
      int var5 = (int)var2;
      int var6 = (int)(var2 >>> 32);
      if (var6 == 0) {
         byte var7 = this.value(var5);
         if (!$assertionsDisabled && var7 != this.unusable) {
            throw new AssertionError(String.valueOf((int)var7));
         }

         var1.init(this, var2, this.runOffset(var5), var4, this.runLength(var5));
      } else {
         this.initBufWithSubpage(var1, var2, var6, var4);
      }
   }

   public void updateParentsAlloc(int var1) {
      while (var1 > 1) {
         int var2 = var1 >>> 1;
         byte var3 = this.value(var1);
         byte var4 = this.value(var1 ^ 1);
         byte var5 = var3 < var4 ? var3 : var4;
         this.setValue(var2, var5);
         var1 = var2;
      }
   }

   public void free(long var1) {
      int var3 = (int)var1;
      int var4 = (int)(var1 >>> 32);
      if (var4 != 0) {
         PoolSubpage var5 = this.subpages[this.subpageIdx(var3)];
         if (!$assertionsDisabled && (var5 == null || !var5.doNotDestroy)) {
            throw new AssertionError();
         }

         if (var5.free(var4 & 1073741823)) {
            return;
         }
      }

      this.freeBytes = this.freeBytes + this.runLength(var3);
      this.setValue(var3, this.depth(var3));
      this.updateParentsFree(var3);
   }

   public void initBufWithSubpage(PooledByteBuf<T> var1, long var2, int var4) {
      this.initBufWithSubpage(var1, var2, (int)(var2 >>> 32), var4);
   }

   public long allocateSubpage(int var1) {
      int var2 = this.maxOrder;
      int var3 = this.allocateNode(var2);
      if (var3 < 0) {
         return var3;
      } else {
         PoolSubpage[] var4 = this.subpages;
         int var5 = this.pageSize;
         this.freeBytes -= var5;
         int var6 = this.subpageIdx(var3);
         PoolSubpage var7 = var4[var6];
         if (var7 == null) {
            var7 = new PoolSubpage<>(this, var3, this.runOffset(var3), var5, var1);
            var4[var6] = var7;
         } else {
            var7.init(var1);
         }

         return var7.allocate();
      }
   }

   public void updateParentsFree(int var1) {
      int var2 = this.depth(var1) + 1;

      while (var1 > 1) {
         int var3 = var1 >>> 1;
         byte var4 = this.value(var1);
         byte var5 = this.value(var1 ^ 1);
         var2--;
         if (var4 == var2 && var5 == var2) {
            this.setValue(var3, (byte)(var2 - 1));
         } else {
            byte var6 = var4 < var5 ? var4 : var5;
            this.setValue(var3, var6);
         }

         var1 = var3;
      }
   }

   public byte depth(int var1) {
      return this.depthMap[var1];
   }

   public PoolSubpage<T>[] newSubpageArray(int var1) {
      return new PoolSubpage[var1];
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Chunk(");
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      var1.append(": ");
      var1.append(this.usage());
      var1.append("%, ");
      var1.append(this.chunkSize - this.freeBytes);
      var1.append('/');
      var1.append(this.chunkSize);
      var1.append(')');
      return var1.toString();
   }
}
