package io.netty.buffer;

import com.cheatbreaker.client.nethandler.server.PacketWorldBorderUpdate;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.handler.codec.compression.ZlibUtil;
import io.netty.handler.codec.socks.SocksInitRequest;
import io.netty.handler.ssl.NotSslRecordException;
import io.netty.util.ThreadDeathWatcher;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import javax.vecmath.TexCoord2f;
import junit.swingui.TestSuitePanel$1;
import net.minecraft.block.BlockPane;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import org.apache.log4j.EnhancedThrowableRenderer;
import org.apache.log4j.spi.LocationInfo;
import org.davidmoten.text.utils.WordWrap$Builder;

public class PoolThreadCache {
   public int allocations;
   public PoolThreadCache.MemoryRegionCache<ByteBuffer>[] normalDirectCaches;
   public PoolArena<byte[]> heapArena;
   public PoolArena<ByteBuffer> directArena;
   public PoolThreadCache.MemoryRegionCache<byte[]>[] normalHeapCaches;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(PoolThreadCache.class);
   public int numShiftsNormalHeap;
   public PoolThreadCache.MemoryRegionCache<ByteBuffer>[] tinySubPageDirectCaches;
   public Runnable freeTask;
   public int numShiftsNormalDirect;
   public PoolThreadCache.MemoryRegionCache<ByteBuffer>[] smallSubPageDirectCaches;
   public int freeSweepAllocationThreshold;
   public PoolThreadCache.MemoryRegionCache<byte[]>[] smallSubPageHeapCaches;
   public Thread thread = Thread.currentThread();
   public PoolThreadCache.MemoryRegionCache<byte[]>[] tinySubPageHeapCaches;

   public PoolThreadCache.MemoryRegionCache<?> cacheForTiny(PoolArena<?> var1, int var2) {
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

   public static void trim(PoolThreadCache.MemoryRegionCache<?>[] var0) {
      if (var0 != null) {
         for (PoolThreadCache.MemoryRegionCache var4 : var0) {
            trim(var4);
         }
      }
   }

   public boolean allocateSmall(PoolArena<?> var1, PooledByteBuf<?> var2, int var3, int var4) {
      return this.allocate(this.cacheForSmall(var1, var4), var2, var3);
   }

   public boolean add(PoolArena<?> var1, PoolChunk var2, long var3, int var5) {
      PoolThreadCache.MemoryRegionCache var6;
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

   public static int free(PoolThreadCache.MemoryRegionCache<?> var0) {
      return var0 == null ? 0 : var0.free();
   }

   public static <T> PoolThreadCache.NormalMemoryRegionCache<T>[] createNormalCaches(int var0, int var1, PoolArena<T> var2) {
      if (var0 <= 0) {
         return null;
      } else {
         int var3 = Math.min(var2.chunkSize, var1);
         int var4 = Math.max(1, var3 / var2.pageSize);
         PoolThreadCache.NormalMemoryRegionCache[] var5 = new PoolThreadCache.NormalMemoryRegionCache[var4];

         for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = new PoolThreadCache.NormalMemoryRegionCache(var0);
         }

         return var5;
      }
   }

   public static int free(PoolThreadCache.MemoryRegionCache<?>[] var0) {
      if (var0 == null) {
         return 0;
      } else {
         int var1 = 0;

         for (PoolThreadCache.MemoryRegionCache var5 : var0) {
            var1 += free(var5);
         }

         return var1;
      }
   }

   public static <T> PoolThreadCache.SubPageMemoryRegionCache<T>[] createSubPageCaches(int var0, int var1) {
      if (var0 <= 0) {
         return null;
      } else {
         PoolThreadCache.SubPageMemoryRegionCache[] var2 = new PoolThreadCache.SubPageMemoryRegionCache[var1];

         for (int var3 = 0; var3 < var2.length; var3++) {
            var2[var3] = new PoolThreadCache.SubPageMemoryRegionCache(var0);
         }

         return var2;
      }
   }

   public PoolThreadCache.MemoryRegionCache<?> cacheForNormal(PoolArena<?> var1, int var2) {
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
      this.freeTask = new Runnable() {

         @Override
         public void run() {
            PoolThreadCache.this.free0();
         }
      };
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

   public static <T> PoolThreadCache.MemoryRegionCache<T> cache(PoolThreadCache.MemoryRegionCache<T>[] var0, int var1) {
      return var0 != null && var1 <= var0.length - 1 ? var0[var1] : null;
   }

   public static void trim(PoolThreadCache.MemoryRegionCache<?> var0) {
      if (var0 != null) {
         var0.trim();
      }
   }

   public boolean allocate(PoolThreadCache.MemoryRegionCache<?> var1, PooledByteBuf var2, int var3) {
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

   public PoolThreadCache.MemoryRegionCache<?> cacheForSmall(PoolArena<?> var1, int var2) {
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

   public abstract static class MemoryRegionCache<T> {
      public int tail;
      public int maxEntriesInUse;
      public PoolThreadCache.MemoryRegionCache.Entry<T>[] entries;
      public int entriesInUse;
      public int maxUnusedCached;
      public int head;

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

      public static boolean freeEntry(PoolThreadCache.MemoryRegionCache.Entry var0) {
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
         PoolThreadCache.MemoryRegionCache.Entry var4 = this.entries[this.tail];
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
         PoolThreadCache.MemoryRegionCache.Entry var3 = this.entries[this.head];
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

      public MemoryRegionCache(int var1) {
         this.entries = new PoolThreadCache.MemoryRegionCache.Entry[powerOfTwo(var1)];

         for (int var2 = 0; var2 < this.entries.length; var2++) {
            this.entries[var2] = new PoolThreadCache.MemoryRegionCache.Entry<>();
         }

         this.maxUnusedCached = var1 / 2;
      }

      public abstract void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5);

      public static final class Entry<T> {
         public PoolChunk<T> chunk;
         public long handle;

         public Entry() {
         }
      }
   }

   public static final class NormalMemoryRegionCache<T> extends PoolThreadCache.MemoryRegionCache<T> {

      @Override
      public void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5) {
         var1.initBuf(var4, var2, var5);
      }

      public NormalMemoryRegionCache(int var1) {
         super(var1);
      }
   }

   public static final class SubPageMemoryRegionCache<T> extends PoolThreadCache.MemoryRegionCache<T> {

      public SubPageMemoryRegionCache(int var1) {
         super(var1);
      }

      @Override
      public void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5) {
         var1.initBufWithSubpage(var4, var2, var5);
      }
   }
}
