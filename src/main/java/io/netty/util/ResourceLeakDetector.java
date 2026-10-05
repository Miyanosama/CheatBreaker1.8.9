package io.netty.util;

import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.network.CheatBreakerPingHandler;
import io.netty.channel.ChannelMetadata;
import io.netty.handler.codec.spdy.SpdySession;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.optifine.shaders.MultiTexID;

public class ResourceLeakDetector<T> {
   public ReferenceQueue<Object> refQueue;
   public ConcurrentMap<String, Boolean> reportedLeaks;
   public long leakCheckCnt;
   public static final String PROP_LEVEL = "io.netty.leakDetectionLevel";
   public int samplingInterval;
   public static final int DEFAULT_SAMPLING_INTERVAL = 113;
   public ResourceLeakDetector<T>.DefaultResourceLeak head = new ResourceLeakDetector.DefaultResourceLeak(null);
   public ResourceLeakDetector<T>.DefaultResourceLeak tail = new ResourceLeakDetector.DefaultResourceLeak(null);
   public AtomicBoolean loggedTooManyActive;
   public static ResourceLeakDetector.Level DEFAULT_LEVEL = ResourceLeakDetector.Level.SIMPLE;
   public long active;
   public static String[] STACK_TRACE_ELEMENT_EXCLUSIONS;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ResourceLeakDetector.class);
   public static ResourceLeakDetector.Level level;
   public long maxActive;
   public String resourceType;

   public ResourceLeakDetector(Class<?> var1) {
      this(StringUtil.simpleClassName(var1));
   }

   static {
      boolean var0;
      if (SystemPropertyUtil.get("io.netty.noResourceLeakDetection") != null) {
         var0 = SystemPropertyUtil.getBoolean("io.netty.noResourceLeakDetection", false);
         logger.debug("-Dio.netty.noResourceLeakDetection: {}", var0);
         logger.warn(
            "-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", "io.netty.leakDetectionLevel", DEFAULT_LEVEL.name().toLowerCase()
         );
      } else {
         var0 = false;
      }

      ResourceLeakDetector.Level var1 = var0 ? ResourceLeakDetector.Level.DISABLED : DEFAULT_LEVEL;
      String var2 = SystemPropertyUtil.get("io.netty.leakDetectionLevel", var1.name()).trim().toUpperCase();
      ResourceLeakDetector.Level var3 = DEFAULT_LEVEL;

      for (ResourceLeakDetector.Level var5 : EnumSet.allOf(ResourceLeakDetector.Level.class)) {
         if (var2.equals(var5.name()) || var2.equals(String.valueOf(var5.ordinal()))) {
            var3 = var5;
         }
      }

      level = var3;
      if (logger.isDebugEnabled()) {
         logger.debug("-D{}: {}", "io.netty.leakDetectionLevel", var3.name().toLowerCase());
      }

      STACK_TRACE_ELEMENT_EXCLUSIONS = new String[]{"io.netty.buffer.AbstractByteBufAllocator.toLeakAwareBuffer("};
   }

   public ResourceLeakDetector(String var1, int var2, long var3) {
      this.refQueue = new ReferenceQueue<>();
      this.reportedLeaks = PlatformDependent.newConcurrentHashMap();
      this.loggedTooManyActive = new AtomicBoolean();
      if (var1 == null) {
         throw new NullPointerException("resourceType");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("samplingInterval: " + var2 + " (expected: 1+)");
      } else if (var3 <= 0L) {
         throw new IllegalArgumentException("maxActive: " + var3 + " (expected: 1+)");
      } else {
         this.resourceType = var1;
         this.samplingInterval = var2;
         this.maxActive = var3;
         this.head.next = this.tail;
         this.tail.prev = this.head;
      }
   }

   public static boolean isEnabled() {
      return getLevel().ordinal() > ResourceLeakDetector.Level.DISABLED.ordinal();
   }

   public static String newRecord(int var0) {
      StringBuilder var1 = new StringBuilder(4096);
      StackTraceElement[] var2 = new Throwable().getStackTrace();

      for (StackTraceElement var6 : var2) {
         if (var0 > 0) {
            var0--;
         } else {
            String var7 = var6.toString();
            boolean var8 = false;

            for (String var12 : STACK_TRACE_ELEMENT_EXCLUSIONS) {
               if (var7.startsWith(var12)) {
                  var8 = true;
                  break;
               }
            }

            if (!var8) {
               var1.append('\t');
               var1.append(var7);
               var1.append(StringUtil.NEWLINE);
            }
         }
      }

      return var1.toString();
   }

   public static void setEnabled(boolean var0) {
      setLevel(var0 ? ResourceLeakDetector.Level.SIMPLE : ResourceLeakDetector.Level.DISABLED);
   }

   public void reportLeak(ResourceLeakDetector.Level var1) {
      if (logger.isErrorEnabled()) {
         int var5 = var1 == ResourceLeakDetector.Level.PARANOID ? 1 : this.samplingInterval;
         if (this.active * var5 > this.maxActive && this.loggedTooManyActive.compareAndSet(false, true)) {
            logger.error(
               "LEAK: You are creating too many "
                  + this.resourceType
                  + " instances.  "
                  + this.resourceType
                  + " is a shared resource that must be reused across the JVM,"
                  + "so that only a few instances are created."
            );
         }

         while (true) {
            ResourceLeakDetector.DefaultResourceLeak var3 = (ResourceLeakDetector.DefaultResourceLeak)this.refQueue.poll();
            if (var3 == null) {
               return;
            }

            var3.clear();
            if (var3.close()) {
               String var4 = var3.toString();
               if (this.reportedLeaks.putIfAbsent(var4, Boolean.TRUE) == null) {
                  if (var4.isEmpty()) {
                     logger.error(
                        "LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel()",
                        this.resourceType,
                        "io.netty.leakDetectionLevel",
                        ResourceLeakDetector.Level.ADVANCED.name().toLowerCase(),
                        StringUtil.simpleClassName(this)
                     );
                  } else {
                     logger.error("LEAK: {}.release() was not called before it's garbage-collected.{}", this.resourceType, var4);
                  }
               }
            }
         }
      } else {
         while (true) {
            ResourceLeakDetector.DefaultResourceLeak var2 = (ResourceLeakDetector.DefaultResourceLeak)this.refQueue.poll();
            if (var2 == null) {
               return;
            }

            var2.close();
         }
      }
   }

   public ResourceLeak open(T var1) {
      ResourceLeakDetector.Level var2 = level;
      if (var2 == ResourceLeakDetector.Level.DISABLED) {
         return null;
      } else if (var2.ordinal() < ResourceLeakDetector.Level.PARANOID.ordinal()) {
         if (this.leakCheckCnt++ % this.samplingInterval == 0L) {
            this.reportLeak(var2);
            return new ResourceLeakDetector.DefaultResourceLeak(var1);
         } else {
            return null;
         }
      } else {
         this.reportLeak(var2);
         return new ResourceLeakDetector.DefaultResourceLeak(var1);
      }
   }

   public ResourceLeakDetector(String var1) {
      this(var1, 113, Long.MAX_VALUE);
   }

   public ResourceLeakDetector(Class<?> var1, int var2, long var3) {
      this(StringUtil.simpleClassName(var1), var2, var3);
   }

   public static ResourceLeakDetector.Level getLevel() {
      return level;
   }

   public static void setLevel(ResourceLeakDetector.Level var0) {
      if (var0 == null) {
         throw new NullPointerException("level");
      } else {
         level = var0;
      }
   }

   public final class DefaultResourceLeak extends PhantomReference<Object> implements ResourceLeak {
      public static final int MAX_RECORDS = 4;
      public AtomicBoolean freed;
      public Deque<String> lastRecords = new ArrayDeque<>();
      public String creationRecord;
      public ResourceLeakDetector<T>.DefaultResourceLeak prev;
      public ResourceLeakDetector<T>.DefaultResourceLeak next;

      @Override
      public void record() {
         if (this.creationRecord != null) {
            String var1 = ResourceLeakDetector.newRecord(2);
            synchronized (this.lastRecords) {
               int var3 = this.lastRecords.size();
               if (var3 == 0 || !this.lastRecords.getLast().equals(var1)) {
                  this.lastRecords.add(var1);
               }

               if (var3 > 4) {
                  this.lastRecords.removeFirst();
               }
            }
         }
      }

      public DefaultResourceLeak(Object var2) {
         super(var2, var2 != null ? ResourceLeakDetector.this.refQueue : null);
         if (var2 != null) {
            ResourceLeakDetector.Level var3 = ResourceLeakDetector.getLevel();
            if (var3.ordinal() >= ResourceLeakDetector.Level.ADVANCED.ordinal()) {
               this.creationRecord = ResourceLeakDetector.newRecord(3);
            } else {
               this.creationRecord = null;
            }

            synchronized (ResourceLeakDetector.this.head) {
               this.prev = ResourceLeakDetector.this.head;
               this.next = ResourceLeakDetector.this.head.next;
               ResourceLeakDetector.this.head.next.prev = this;
               ResourceLeakDetector.this.head.next = this;
               ResourceLeakDetector.this.active++;
            }

            this.freed = new AtomicBoolean();
         } else {
            this.creationRecord = null;
            this.freed = new AtomicBoolean(true);
         }
      }

      @Override
      public String toString() {
         if (this.creationRecord == null) {
            return "";
         } else {
            Object[] var1;
            synchronized (this.lastRecords) {
               var1 = this.lastRecords.toArray();
            }

            StringBuilder var5 = new StringBuilder(16384);
            var5.append(StringUtil.NEWLINE);
            var5.append("Recent access records: ");
            var5.append(var1.length);
            var5.append(StringUtil.NEWLINE);
            if (var1.length > 0) {
               for (int var3 = var1.length - 1; var3 >= 0; var3--) {
                  var5.append('#');
                  var5.append(var3 + 1);
                  var5.append(':');
                  var5.append(StringUtil.NEWLINE);
                  var5.append(var1[var3]);
               }
            }

            var5.append("Created at:");
            var5.append(StringUtil.NEWLINE);
            var5.append(this.creationRecord);
            var5.setLength(var5.length() - StringUtil.NEWLINE.length());
            return var5.toString();
         }
      }

      @Override
      public boolean close() {
         if (this.freed.compareAndSet(false, true)) {
            synchronized (ResourceLeakDetector.this.head) {
               ResourceLeakDetector.this.active--;
               this.prev.next = this.next;
               this.next.prev = this.prev;
               this.prev = null;
               this.next = null;
               return true;
            }
         } else {
            return false;
         }
      }
   }

   public static enum Level {
      DISABLED,
      SIMPLE,
      ADVANCED,
      PARANOID;

   }
}
