package io.netty.buffer;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.block.BlockQuartz;
import net.minecraft.item.ItemNameTag;
import net.minecraft.stats.StatBasic;
import org.java_websocket.exceptions.LimitExceededException;
import com.cheatbreaker.client.emote.type.ShrugEmote;

public class PooledByteBufAllocator extends AbstractByteBufAllocator {
   public int normalCacheSize;
   public static final int MAX_CHUNK_SIZE = 1073741824;
   public int tinyCacheSize;
   public static int DEFAULT_NORMAL_CACHE_SIZE;
   public PoolArena<ByteBuffer>[] directArenas;
   public static PooledByteBufAllocator DEFAULT;
   public PoolArena<byte[]>[] heapArenas;
   public PooledByteBufAllocator.PoolThreadLocalCache threadCache = new PooledByteBufAllocator.PoolThreadLocalCache();
   public static int DEFAULT_SMALL_CACHE_SIZE;
   public int smallCacheSize;
   public static int DEFAULT_TINY_CACHE_SIZE;
   public static final int MIN_PAGE_SIZE = 4096;
   public static int DEFAULT_MAX_CACHED_BUFFER_CAPACITY;
   public static int DEFAULT_NUM_DIRECT_ARENA;
   public static int DEFAULT_NUM_HEAP_ARENA;
   public static int DEFAULT_CACHE_TRIM_INTERVAL;
   public static int DEFAULT_MAX_ORDER;
   public static int DEFAULT_PAGE_SIZE;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(PooledByteBufAllocator.class);

   public PooledByteBufAllocator(boolean var1) {
      this(var1, DEFAULT_NUM_HEAP_ARENA, DEFAULT_NUM_DIRECT_ARENA, DEFAULT_PAGE_SIZE, DEFAULT_MAX_ORDER);
   }

   public void freeThreadLocalCache() {
      this.threadCache.remove();
   }

   public PooledByteBufAllocator(boolean var1, int var2, int var3, int var4, int var5) {
      this(var1, var2, var3, var4, var5, DEFAULT_TINY_CACHE_SIZE, DEFAULT_SMALL_CACHE_SIZE, DEFAULT_NORMAL_CACHE_SIZE);
   }

   public PooledByteBufAllocator(boolean var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      super(var1);
      this.tinyCacheSize = var6;
      this.smallCacheSize = var7;
      this.normalCacheSize = var8;
      int var9 = validateAndCalculateChunkSize(var4, var5);
      if (var2 < 0) {
         throw new IllegalArgumentException("nHeapArena: " + var2 + " (expected: >= 0)");
      } else if (var3 < 0) {
         throw new IllegalArgumentException("nDirectArea: " + var3 + " (expected: >= 0)");
      } else {
         int var10 = validateAndCalculatePageShifts(var4);
         if (var2 > 0) {
            this.heapArenas = newArenaArray(var2);

            for (int var11 = 0; var11 < this.heapArenas.length; var11++) {
               this.heapArenas[var11] = new PoolArena.HeapArena(this, var4, var5, var10, var9);
            }
         } else {
            this.heapArenas = null;
         }

         if (var3 > 0) {
            this.directArenas = newArenaArray(var3);

            for (int var12 = 0; var12 < this.directArenas.length; var12++) {
               this.directArenas[var12] = new PoolArena.DirectArena(this, var4, var5, var10, var9);
            }
         } else {
            this.directArenas = null;
         }
      }
   }

   @Override
   public ByteBuf newDirectBuffer(int var1, int var2) {
      PoolThreadCache var3 = this.threadCache.get();
      PoolArena var4 = var3.directArena;
      Object var5;
      if (var4 != null) {
         var5 = var4.allocate(var3, var1, var2);
      } else if (PlatformDependent.hasUnsafe()) {
         var5 = new UnpooledUnsafeDirectByteBuf(this, var1, var2);
      } else {
         var5 = new UnpooledDirectByteBuf(this, var1, var2);
      }

      return toLeakAwareBuffer((ByteBuf)var5);
   }

   @Override
   public ByteBuf newHeapBuffer(int var1, int var2) {
      PoolThreadCache var3 = this.threadCache.get();
      PoolArena var4 = var3.heapArena;
      Object var5;
      if (var4 != null) {
         var5 = var4.allocate(var3, var1, var2);
      } else {
         var5 = new UnpooledHeapByteBuf(this, var1, var2);
      }

      return toLeakAwareBuffer((ByteBuf)var5);
   }

   public PooledByteBufAllocator() {
      this(false);
   }

   public PooledByteBufAllocator(boolean var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9) {
      this(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   @Override
   public boolean isDirectBufferPooled() {
      return this.directArenas != null;
   }

   public boolean hasThreadLocalCache() {
      return this.threadCache.isSet();
   }

   static {
      int var0 = SystemPropertyUtil.getInt("io.netty.allocator.pageSize", 8192);
      Throwable var1 = null;

      try {
         validateAndCalculatePageShifts(var0);
      } catch (Throwable var7) {
         var1 = var7;
         var0 = 8192;
      }

      DEFAULT_PAGE_SIZE = var0;
      int var2 = SystemPropertyUtil.getInt("io.netty.allocator.maxOrder", 11);
      Throwable var3 = null;

      try {
         validateAndCalculateChunkSize(DEFAULT_PAGE_SIZE, var2);
      } catch (Throwable var6) {
         var3 = var6;
         var2 = 11;
      }

      DEFAULT_MAX_ORDER = var2;
      Runtime var4 = Runtime.getRuntime();
      int var5 = DEFAULT_PAGE_SIZE << DEFAULT_MAX_ORDER;
      DEFAULT_NUM_HEAP_ARENA = Math.max(
         0,
         SystemPropertyUtil.getInt(
            "io.netty.allocator.numHeapArenas", (int)Math.min((long)var4.availableProcessors(), Runtime.getRuntime().maxMemory() / var5 / 2L / 3L)
         )
      );
      DEFAULT_NUM_DIRECT_ARENA = Math.max(
         0,
         SystemPropertyUtil.getInt(
            "io.netty.allocator.numDirectArenas", (int)Math.min((long)var4.availableProcessors(), PlatformDependent.maxDirectMemory() / var5 / 2L / 3L)
         )
      );
      DEFAULT_TINY_CACHE_SIZE = SystemPropertyUtil.getInt("io.netty.allocator.tinyCacheSize", 512);
      DEFAULT_SMALL_CACHE_SIZE = SystemPropertyUtil.getInt("io.netty.allocator.smallCacheSize", 256);
      DEFAULT_NORMAL_CACHE_SIZE = SystemPropertyUtil.getInt("io.netty.allocator.normalCacheSize", 64);
      DEFAULT_MAX_CACHED_BUFFER_CAPACITY = SystemPropertyUtil.getInt("io.netty.allocator.maxCachedBufferCapacity", 32768);
      DEFAULT_CACHE_TRIM_INTERVAL = SystemPropertyUtil.getInt("io.netty.allocator.cacheTrimInterval", 8192);
      if (logger.isDebugEnabled()) {
         logger.debug("-Dio.netty.allocator.numHeapArenas: {}", DEFAULT_NUM_HEAP_ARENA);
         logger.debug("-Dio.netty.allocator.numDirectArenas: {}", DEFAULT_NUM_DIRECT_ARENA);
         if (var1 == null) {
            logger.debug("-Dio.netty.allocator.pageSize: {}", DEFAULT_PAGE_SIZE);
         } else {
            logger.debug("-Dio.netty.allocator.pageSize: {}", DEFAULT_PAGE_SIZE, var1);
         }

         if (var3 == null) {
            logger.debug("-Dio.netty.allocator.maxOrder: {}", DEFAULT_MAX_ORDER);
         } else {
            logger.debug("-Dio.netty.allocator.maxOrder: {}", DEFAULT_MAX_ORDER, var3);
         }

         logger.debug("-Dio.netty.allocator.chunkSize: {}", DEFAULT_PAGE_SIZE << DEFAULT_MAX_ORDER);
         logger.debug("-Dio.netty.allocator.tinyCacheSize: {}", DEFAULT_TINY_CACHE_SIZE);
         logger.debug("-Dio.netty.allocator.smallCacheSize: {}", DEFAULT_SMALL_CACHE_SIZE);
         logger.debug("-Dio.netty.allocator.normalCacheSize: {}", DEFAULT_NORMAL_CACHE_SIZE);
         logger.debug("-Dio.netty.allocator.maxCachedBufferCapacity: {}", DEFAULT_MAX_CACHED_BUFFER_CAPACITY);
         logger.debug("-Dio.netty.allocator.cacheTrimInterval: {}", DEFAULT_CACHE_TRIM_INTERVAL);
      }

      DEFAULT = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());
   }

   public static int validateAndCalculateChunkSize(int var0, int var1) {
      if (var1 > 14) {
         throw new IllegalArgumentException("maxOrder: " + var1 + " (expected: 0-14)");
      } else {
         int var2 = var0;

         for (int var3 = var1; var3 > 0; var3--) {
            if (var2 > 536870912) {
               throw new IllegalArgumentException(String.format("pageSize (%d) << maxOrder (%d) must not exceed %d", var0, var1, 1073741824));
            }

            var2 <<= 1;
         }

         return var2;
      }
   }

   public static <T> PoolArena<T>[] newArenaArray(int var0) {
      return new PoolArena[var0];
   }

   public PooledByteBufAllocator(int var1, int var2, int var3, int var4) {
      this(false, var1, var2, var3, var4);
   }

   public static int validateAndCalculatePageShifts(int var0) {
      if (var0 < 4096) {
         throw new IllegalArgumentException("pageSize: " + var0 + " (expected: " + 4096 + "+)");
      } else if ((var0 & var0 - 1) != 0) {
         throw new IllegalArgumentException("pageSize: " + var0 + " (expected: power of 2)");
      } else {
         return 31 - Integer.numberOfLeadingZeros(var0);
      }
   }

   public final class PoolThreadLocalCache extends FastThreadLocal<PoolThreadCache> {
      public AtomicInteger index = new AtomicInteger();

      public PoolThreadCache initialValue() {
         int var1 = this.index.getAndIncrement();
         PoolArena var2;
         if (PooledByteBufAllocator.this.heapArenas != null) {
            var2 = PooledByteBufAllocator.this.heapArenas[Math.abs(var1 % PooledByteBufAllocator.this.heapArenas.length)];
         } else {
            var2 = null;
         }

         PoolArena var3;
         if (PooledByteBufAllocator.this.directArenas != null) {
            var3 = PooledByteBufAllocator.this.directArenas[Math.abs(var1 % PooledByteBufAllocator.this.directArenas.length)];
         } else {
            var3 = null;
         }

         return new PoolThreadCache(
            var2,
            var3,
            PooledByteBufAllocator.this.tinyCacheSize,
            PooledByteBufAllocator.this.smallCacheSize,
            PooledByteBufAllocator.this.normalCacheSize,
            PooledByteBufAllocator.DEFAULT_MAX_CACHED_BUFFER_CAPACITY,
            PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL
         );
      }

      public void onRemoval(PoolThreadCache var1) {
         var1.free();
      }
   }
}
