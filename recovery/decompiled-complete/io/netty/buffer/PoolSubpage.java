package io.netty.buffer;

import net.minecraft.client.renderer.RenderHelper;

public class PoolSubpage<T> {
   public PoolSubpage<T> next;
   public int pageSize;
   public long[] bitmap;
   public int numAvail;
   public int maxNumElems;
   public RenderHelper __junk8166256468501036415;
   public int elemSize;
   public int nextAvail;
   public PoolSubpage<T> prev;
   public boolean doNotDestroy;
   public int bitmapLength;
   public PoolChunk<T> chunk;
   public int memoryMapIdx;
   public int runOffset;

   public void setNextAvail(int var1) {
      this.nextAvail = var1;
   }

   public void addToPool() {
      PoolSubpage var1 = this.chunk.arena.findSubpagePoolHead(this.elemSize);
      if ($assertionsDisabled || this.prev == null && this.next == null) {
         this.prev = var1;
         this.next = var1.next;
         this.next.prev = this;
         var1.next = this;
      } else {
         throw new AssertionError();
      }
   }

   public int findNextAvail() {
      long[] var1 = this.bitmap;
      int var2 = this.bitmapLength;

      for (int var3 = 0; var3 < var2; var3++) {
         long var4 = var1[var3];
         if ((var4 ^ -1L & -1L) != (-5857690028870659968L & 5857690027947516972L)) {
            return this.findNextAvail0(var3, var4);
         }
      }

      return -1;
   }

   public boolean free(int var1) {
      if (this.elemSize == 0) {
         return true;
      } else {
         int var2 = var1 >>> 6;
         int var3 = var1 & 63;
         if (!$assertionsDisabled && (this.bitmap[var2] >>> var3 & 7891000186521060385L & 545654985L) == (648331376239575552L & 1074370853L)) {
            throw new AssertionError();
         } else {
            this.bitmap[var2] = this.bitmap[var2] ^ (1879533577L & 4723217L) << var3;
            this.setNextAvail(var1);
            if (this.numAvail++ == 0) {
               this.addToPool();
               return true;
            } else if (this.numAvail != this.maxNumElems) {
               return true;
            } else if (this.prev == this.next) {
               return true;
            } else {
               this.doNotDestroy = false;
               this.removeFromPool();
               return false;
            }
         }
      }
   }

   public long toHandle(int var1) {
      return 4611686018638217384L & 4611686019556188227L | (long)var1 << 32 | this.memoryMapIdx;
   }

   public PoolSubpage(int var1) {
      this.chunk = null;
      this.memoryMapIdx = -1;
      this.runOffset = -1;
      this.elemSize = -1;
      this.pageSize = var1;
      this.bitmap = null;
   }

   public long allocate() {
      if (this.elemSize == 0) {
         return this.toHandle(0);
      } else if (this.numAvail != 0 && this.doNotDestroy) {
         int var1 = this.getNextAvail();
         int var2 = var1 >>> 6;
         int var3 = var1 & 63;
         if (!$assertionsDisabled && (this.bitmap[var2] >>> var3 & 675963409L & 271586305L) != (160038912L & 4268284L)) {
            throw new AssertionError();
         } else {
            this.bitmap[var2] = this.bitmap[var2] | (746505148264615145L & -746505149649133551L) << var3;
            if (--this.numAvail == 0) {
               this.removeFromPool();
            }

            return this.toHandle(var1);
         }
      } else {
         return -1L & -1L;
      }
   }

   public PoolSubpage(PoolChunk<T> var1, int var2, int var3, int var4, int var5) {
      this.chunk = var1;
      this.memoryMapIdx = var2;
      this.runOffset = var3;
      this.pageSize = var4;
      this.bitmap = new long[var4 >>> 10];
      this.init(var5);
   }

   public int getNextAvail() {
      int var1 = this.nextAvail;
      if (var1 >= 0) {
         this.nextAvail = -1;
         return var1;
      } else {
         return this.findNextAvail();
      }
   }

   public void removeFromPool() {
      if ($assertionsDisabled || this.prev != null && this.next != null) {
         this.prev.next = this.next;
         this.next.prev = this.prev;
         this.next = null;
         this.prev = null;
      } else {
         throw new AssertionError();
      }
   }

   public void init(int var1) {
      this.doNotDestroy = true;
      this.elemSize = var1;
      if (var1 != 0) {
         this.maxNumElems = this.numAvail = this.pageSize / var1;
         this.nextAvail = 0;
         this.bitmapLength = this.maxNumElems >>> 6;
         if ((this.maxNumElems & 63) != 0) {
            this.bitmapLength++;
         }

         for (int var2 = 0; var2 < this.bitmapLength; var2++) {
            this.bitmap[var2] = 6911992177017225504L & 67108937L;
         }
      }

      this.addToPool();
   }

   @Override
   public String toString() {
      return !this.doNotDestroy
         ? "(" + this.memoryMapIdx + ": not in use)"
         : String.valueOf('(')
            + this.memoryMapIdx
            + ": "
            + (this.maxNumElems - this.numAvail)
            + '/'
            + this.maxNumElems
            + ", offset: "
            + this.runOffset
            + ", length: "
            + this.pageSize
            + ", elemSize: "
            + this.elemSize
            + ')';
   }

   public int findNextAvail0(int var1, long var2) {
      int var4 = this.maxNumElems;
      int var5 = var1 << 6;

      for (int var6 = 0; var6 < 64; var6++) {
         if ((var2 & 2172140026668777641L & -2172140027937947647L) == (-5947266361518454654L & 5947266359766549345L)) {
            int var7 = var5 | var6;
            if (var7 < var4) {
               return var7;
            }
            break;
         }

         var2 >>>= 1;
      }

      return -1;
   }
}
