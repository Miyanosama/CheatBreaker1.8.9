package io.netty.buffer;

import io.netty.channel.group.ChannelMatchers$1;
import io.netty.util.ThreadDeathWatcher;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import net.minecraft.entity.ai.EntityAITradePlayer;
import org.apache.log4j.EnhancedThrowableRenderer;
import org.apache.log4j.spi.LocationInfo;
import recovered.unidentified.UnidentifiedClass4984;

public class PoolThreadCache {
   public int allocations;
   public ChannelMatchers$1 __junk4709221831018653531;
   public PoolThreadCache$MemoryRegionCache<ByteBuffer>[] normalDirectCaches;
   public PoolArena<byte[]> heapArena;
   public LocationInfo __junk519352557768838746;
   public PoolArena<ByteBuffer> directArena;
   public PoolThreadCache$MemoryRegionCache<byte[]>[] normalHeapCaches;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(PoolThreadCache.class);
   public int numShiftsNormalHeap;
   public EntityAITradePlayer __junk4133491479627835068;
   public PoolThreadCache$MemoryRegionCache<ByteBuffer>[] tinySubPageDirectCaches;
   public Runnable freeTask;
   public UnidentifiedClass4984 __junk7912464287553249449;
   public int numShiftsNormalDirect;
   public PoolThreadCache$MemoryRegionCache<ByteBuffer>[] smallSubPageDirectCaches;
   public int freeSweepAllocationThreshold;
   public EnhancedThrowableRenderer __junk4576875195961417478;
   public PoolThreadCache$MemoryRegionCache<byte[]>[] smallSubPageHeapCaches;
   public Thread thread = Thread.currentThread();
   public PoolThreadCache$MemoryRegionCache<byte[]>[] tinySubPageHeapCaches;

   public PoolThreadCache$MemoryRegionCache<?> cacheForTiny(PoolArena<?> var1, int var2) {
      int var3 = PoolArena.tinyIdx(var2);
      return var1.isDirect() ? cache(this.tinySubPageDirectCaches, var3) : cache(this.tinySubPageHeapCaches, var3);
   }

   public boolean allocateNormal(PoolArena<?> var1, PooledByteBuf<?> var2, int var3, int var4) {
      return this.allocate(this.cacheForNormal(var1, var4), var2, var3);
   }

   public void free() {
      ThreadDeathWatcher.unwatch(this.thread, this.freeTask);
      this.free0();
   }

   public static void trim(PoolThreadCache$MemoryRegionCache<?>[] var0) {
      if (var0 != null) {
         for (PoolThreadCache$MemoryRegionCache var4 : var0) {
            trim(var4);
         }
      }
   }

   public boolean allocateSmall(PoolArena<?> var1, PooledByteBuf<?> var2, int var3, int var4) {
      return this.allocate(this.cacheForSmall(var1, var4), var2, var3);
   }

   public boolean add(PoolArena<?> var1, PoolChunk var2, long var3, int var5) {
      PoolThreadCache$MemoryRegionCache var6;
      if (var1.isTinyOrSmall(var5)) {
         if (PoolArena.isTiny(var5)) {
            var6 = this.cacheForTiny(var1, var5);
         } else {
            var6 = this.cacheForSmall(var1, var5);
         }
      } else {
         var6 = this.cacheForNormal(var1, var5);
      }

      return var6 == null ? false : var6.add(var2, var3);
   }

   public static int free(PoolThreadCache$MemoryRegionCache<?> var0) {
      return var0 == null ? 0 : var0.free();
   }

   public static <T> PoolThreadCache$NormalMemoryRegionCache<T>[] createNormalCaches(int var0, int var1, PoolArena<T> var2) {
      if (var0 <= 0) {
         return null;
      } else {
         int var3 = Math.min(var2.chunkSize, var1);
         int var4 = Math.max(1, var3 / var2.pageSize);
         PoolThreadCache$NormalMemoryRegionCache[] var5 = new PoolThreadCache$NormalMemoryRegionCache[var4];

         for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = new PoolThreadCache$NormalMemoryRegionCache(var0);
         }

         return var5;
      }
   }

   public static int free(PoolThreadCache$MemoryRegionCache<?>[] var0) {
      if (var0 == null) {
         return 0;
      } else {
         int var1 = 0;

         for (PoolThreadCache$MemoryRegionCache var5 : var0) {
            var1 += free(var5);
         }

         return var1;
      }
   }

   public static <T> PoolThreadCache$SubPageMemoryRegionCache<T>[] createSubPageCaches(int var0, int var1) {
      if (var0 <= 0) {
         return null;
      } else {
         PoolThreadCache$SubPageMemoryRegionCache[] var2 = new PoolThreadCache$SubPageMemoryRegionCache[var1];

         for (int var3 = 0; var3 < var2.length; var3++) {
            var2[var3] = new PoolThreadCache$SubPageMemoryRegionCache(var0);
         }

         return var2;
      }
   }

   public PoolThreadCache$MemoryRegionCache<?> cacheForNormal(PoolArena<?> var1, int var2) {
      if (var1.isDirect()) {
         int var4 = log2(var2 >> this.numShiftsNormalDirect);
         return cache(this.normalDirectCaches, var4);
      } else {
         int var3 = log2(var2 >> this.numShiftsNormalHeap);
         return cache(this.normalHeapCaches, var3);
      }
   }

   public void trim() {
      trim(this.tinySubPageDirectCaches);
      trim(this.smallSubPageDirectCaches);
      trim(this.normalDirectCaches);
      trim(this.tinySubPageHeapCaches);
      trim(this.smallSubPageHeapCaches);
      trim(this.normalHeapCaches);
   }

   public void free0() {
      int var1 = free(this.tinySubPageDirectCaches)
         + free(this.smallSubPageDirectCaches)
         + free(this.normalDirectCaches)
         + free(this.tinySubPageHeapCaches)
         + free(this.smallSubPageHeapCaches)
         + free(this.normalHeapCaches);
      if (var1 > 0 && logger.isDebugEnabled()) {
         logger.debug("Freed {} thread-local buffer(s) from thread: {}", var1, this.thread.getName());
      }
   }

   public PoolThreadCache(PoolArena<byte[]> var1, PoolArena<ByteBuffer> var2, int var3, int var4, int var5, int var6, int var7) {
      this.freeTask = new PoolThreadCache$1(this);
      if (var6 < 0) {
         throw new IllegalArgumentException("maxCachedBufferCapacity: " + var6 + " (expected: >= 0)");
      } else if (var7 < 1) {
         throw new IllegalArgumentException("freeSweepAllocationThreshold: " + var6 + " (expected: > 0)");
      } else {
         this.freeSweepAllocationThreshold = var7;
         this.heapArena = var1;
         this.directArena = var2;
         if (var2 != null) {
            this.tinySubPageDirectCaches = createSubPageCaches(var3, 32);
            this.smallSubPageDirectCaches = createSubPageCaches(var4, var2.numSmallSubpagePools);
            this.numShiftsNormalDirect = log2(var2.pageSize);
            this.normalDirectCaches = createNormalCaches(var5, var6, var2);
         } else {
            this.tinySubPageDirectCaches = null;
            this.smallSubPageDirectCaches = null;
            this.normalDirectCaches = null;
            this.numShiftsNormalDirect = -1;
         }

         if (var1 != null) {
            this.tinySubPageHeapCaches = createSubPageCaches(var3, 32);
            this.smallSubPageHeapCaches = createSubPageCaches(var4, var1.numSmallSubpagePools);
            this.numShiftsNormalHeap = log2(var1.pageSize);
            this.normalHeapCaches = createNormalCaches(var5, var6, var1);
         } else {
            this.tinySubPageHeapCaches = null;
            this.smallSubPageHeapCaches = null;
            this.normalHeapCaches = null;
            this.numShiftsNormalHeap = -1;
         }

         ThreadDeathWatcher.watch(this.thread, this.freeTask);
      }
   }

   public static <T> PoolThreadCache$MemoryRegionCache<T> cache(PoolThreadCache$MemoryRegionCache<T>[] var0, int var1) {
      return var0 != null && var1 <= var0.length - 1 ? var0[var1] : null;
   }

   public static void trim(PoolThreadCache$MemoryRegionCache<?> var0) {
      if (var0 != null) {
         PoolThreadCache$MemoryRegionCache.access$100(var0);
      }
   }

   public boolean allocate(PoolThreadCache$MemoryRegionCache<?> var1, PooledByteBuf var2, int var3) {
      if (var1 == null) {
         return false;
      } else {
         boolean var4 = var1.allocate(var2, var3);
         if (++this.allocations >= this.freeSweepAllocationThreshold) {
            this.allocations = 0;
            this.trim();
         }

         return var4;
      }
   }

   public PoolThreadCache$MemoryRegionCache<?> cacheForSmall(PoolArena<?> var1, int var2) {
      int var3 = PoolArena.smallIdx(var2);
      return var1.isDirect() ? cache(this.smallSubPageDirectCaches, var3) : cache(this.smallSubPageHeapCaches, var3);
   }

   public boolean allocateTiny(PoolArena<?> var1, PooledByteBuf<?> var2, int var3, int var4) {
      return this.allocate(this.cacheForTiny(var1, var4), var2, var3);
   }

   public static int log2(int var0) {
      int var1;
      for (var1 = 0; var0 > 1; var1++) {
         var0 >>= 1;
      }

      return var1;
   }
}
