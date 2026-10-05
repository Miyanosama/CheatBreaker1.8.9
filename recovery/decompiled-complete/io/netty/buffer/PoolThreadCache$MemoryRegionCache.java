package io.netty.buffer;

import com.cheatbreaker.client.nethandler.server.PacketWorldBorderUpdate;

public abstract class PoolThreadCache$MemoryRegionCache<T> {
   public int tail;
   public int maxEntriesInUse;
   public PoolThreadCache$MemoryRegionCache$Entry<T>[] entries;
   public int entriesInUse;
   public int maxUnusedCached;
   public int head;
   public PacketWorldBorderUpdate __junk7178415668950301143;

   public int nextIdx(int var1) {
      return var1 + 1 & this.entries.length - 1;
   }

   public static int powerOfTwo(int var0) {
      if (var0 <= 2) {
         return 2;
      } else {
         var0 = --var0 | var0 >> 1;
         var0 |= var0 >> 2;
         var0 |= var0 >> 4;
         var0 |= var0 >> 8;
         var0 |= var0 >> 16;
         return var0 + 1;
      }
   }

   public void trim() {
      int var1 = this.size() - this.maxEntriesInUse;
      this.entriesInUse = 0;
      this.maxEntriesInUse = 0;
      if (var1 > this.maxUnusedCached) {
         for (int var2 = this.head; var1 > 0; var1--) {
            if (!freeEntry(this.entries[var2])) {
               return;
            }

            var2 = this.nextIdx(var2);
         }
      }
   }

   public int size() {
      return this.tail - this.head & this.entries.length - 1;
   }

   public static boolean freeEntry(PoolThreadCache$MemoryRegionCache$Entry var0) {
      PoolChunk var1 = var0.chunk;
      if (var1 == null) {
         return false;
      } else {
         synchronized (var1.arena) {
            var1.parent.free(var1, var0.handle);
         }

         var0.chunk = null;
         return true;
      }
   }

   public boolean add(PoolChunk<T> var1, long var2) {
      PoolThreadCache$MemoryRegionCache$Entry var4 = this.entries[this.tail];
      if (var4.chunk != null) {
         return false;
      } else {
         this.entriesInUse--;
         var4.chunk = var1;
         var4.handle = var2;
         this.tail = this.nextIdx(this.tail);
         return true;
      }
   }

   public int free() {
      int var1 = 0;
      this.entriesInUse = 0;
      this.maxEntriesInUse = 0;

      for (int var2 = this.head; freeEntry(this.entries[var2]); var2 = this.nextIdx(var2)) {
         var1++;
      }

      return var1;
   }

   public boolean allocate(PooledByteBuf<T> var1, int var2) {
      PoolThreadCache$MemoryRegionCache$Entry var3 = this.entries[this.head];
      if (var3.chunk == null) {
         return false;
      } else {
         this.entriesInUse++;
         if (this.maxEntriesInUse < this.entriesInUse) {
            this.maxEntriesInUse = this.entriesInUse;
         }

         this.initBuf(var3.chunk, var3.handle, var1, var2);
         var3.chunk = null;
         this.head = this.nextIdx(this.head);
         return true;
      }
   }

   public PoolThreadCache$MemoryRegionCache(int var1) {
      this.entries = new PoolThreadCache$MemoryRegionCache$Entry[powerOfTwo(var1)];

      for (int var2 = 0; var2 < this.entries.length; var2++) {
         this.entries[var2] = new PoolThreadCache$MemoryRegionCache$Entry<>(null);
      }

      this.maxUnusedCached = var1 / 2;
   }

   public abstract void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5);
}
