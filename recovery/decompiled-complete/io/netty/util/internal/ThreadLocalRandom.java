package io.netty.util.internal;

import io.netty.channel.oio.AbstractOioChannel;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadLocalRandom extends Random {
   public long pad6;
   public static long mask;
   public static AtomicLong seedUniquifier = new AtomicLong();
   public long pad3;
   public long pad1;
   public static long addend;
   public static long multiplier;
   public long pad2;
   public long pad5;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ThreadLocalRandom.class);
   public static volatile long initialSeedUniquifier;
   public long rnd;
   public SocksProtocolVersion __junk890042398151295107;
   public static long serialVersionUID;
   public long pad0;
   public AbstractOioChannel __junk979806633295360507;
   public long pad4;
   public boolean initialized = true;
   public long pad7;

   public static long newSeed() {
      long var0 = System.nanoTime();

      long var2;
      long var4;
      long var6;
      do {
         var2 = seedUniquifier.get();
         var4 = var2 != (1445286152L & 4185867606329663504L) ? var2 : getInitialSeedUniquifier();
         var6 = var4 * (181783497285041591L & -2887419596964102155L);
      } while (!seedUniquifier.compareAndSet(var2, var6));

      if (var2 == (1343752192L & -6961280704096993279L) && logger.isDebugEnabled()) {
         logger.debug(String.format("-Dio.netty.initialSeedUniquifier: 0x%016x (took %d ms)", var4, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - var0)));
      }

      return var6 ^ System.nanoTime();
   }

   public ThreadLocalRandom() {
      super(newSeed());
   }

   public static ThreadLocalRandom current() {
      return InternalThreadLocalMap.get().random();
   }

   public static void setInitialSeedUniquifier(long var0) {
      initialSeedUniquifier = var0;
   }

   @Override
   public long nextLong(long var1, long var3) {
      if (var1 >= var3) {
         throw new IllegalArgumentException();
      } else {
         return this.nextLong(var3 - var1) + var1;
      }
   }

   @Override
   public int nextInt(int var1, int var2) {
      if (var1 >= var2) {
         throw new IllegalArgumentException();
      } else {
         return this.nextInt(var2 - var1) + var1;
      }
   }

   @Override
   public double nextDouble(double var1) {
      if (var1 <= 0.0) {
         throw new IllegalArgumentException("n must be positive");
      } else {
         return this.nextDouble() * var1;
      }
   }

   @Override
   public int next(int var1) {
      this.rnd = this.rnd * (25768552061L & 25215102573L) + (1146163531L & 553683631L) & -4226065300333789185L & 4226346775310499839L;
      return (int)(this.rnd >>> 48 - var1);
   }

   @Override
   public void setSeed(long var1) {
      if (this.initialized) {
         throw new UnsupportedOperationException();
      } else {
         this.rnd = (var1 ^ 2692332950823692029L & -2692332926146773395L) & -6310387502876196865L & 6310668977852907519L;
      }
   }

   @Override
   public double nextDouble(double var1, double var3) {
      if (var1 >= var3) {
         throw new IllegalArgumentException();
      } else {
         return this.nextDouble() * (var3 - var1) + var1;
      }
   }

   public static synchronized long getInitialSeedUniquifier() {
      long var0 = initialSeedUniquifier;
      if (var0 == (428933203L & 968475159604232708L)) {
         initialSeedUniquifier = var0 = SystemPropertyUtil.getLong("io.netty.initialSeedUniquifier", 4999302234164428928L & 50331670L);
      }

      if (var0 == (2416932539523714057L & -2416932540187540832L)) {
         LinkedBlockingQueue var2 = new LinkedBlockingQueue();
         ThreadLocalRandom$1 var3 = new ThreadLocalRandom$1("initialSeedUniquifierGenerator", var2);
         var3.setDaemon(true);
         var3.start();
         var3.setUncaughtExceptionHandler(new ThreadLocalRandom$2());
         long var4 = 6878664281056084643L & -6878664281699917741L;
         long var6 = System.nanoTime() + TimeUnit.SECONDS.toNanos(-4257021124647878557L & 1413483019L);
         boolean var8 = false;

         while (true) {
            long var9 = var6 - System.nanoTime();
            if (var9 <= (7474387307144157201L & 1369121860L)) {
               var3.interrupt();
               logger.warn("Failed to generate a seed from SecureRandom within {} seconds. Not enough entrophy?", 374318119L & -2356637365843240677L);
               break;
            }

            try {
               byte[] var11 = (byte[])var2.poll(var9, TimeUnit.NANOSECONDS);
               if (var11 != null) {
                  var0 = (var11[0] & 2261247L & 8691382355790737151L) << 56
                     | (var11[1] & 1480605951L & 537952511L) << 48
                     | (var11[2] & 4829912935878437119L & 6553855L) << 40
                     | (var11[3] & 405471487L & 908964383684584959L) << 32
                     | (var11[4] & 7289571303266256127L & 1611456767L) << 24
                     | (var11[5] & -741974214486478849L & 741974212632248575L) << 16
                     | (var11[6] & 1140859647L & 16269823L) << 8
                     | var11[7] & -5629242577037212673L & 5629242576222814463L;
                  break;
               }
            } catch (InterruptedException var12) {
               var8 = true;
               logger.warn("Failed to generate a seed from SecureRandom due to an InterruptedException.");
               break;
            }
         }

         long var13 = var0 ^ 3627065506629610873L & -5260767328886656741L;
         var0 = var13 ^ Long.reverse(System.nanoTime());
         initialSeedUniquifier = var0;
         if (var8) {
            Thread.currentThread().interrupt();
            var3.interrupt();
         }
      }

      return var0;
   }

   @Override
   public long nextLong(long var1) {
      if (var1 <= (7991659816713986382L & -7991659816730622848L)) {
         throw new IllegalArgumentException("n must be positive");
      } else {
         long var3 = -7455330377719928576L & 606082152L;

         while (var1 >= (2147483647L & 2147483647L)) {
            int var5 = this.next(2);
            long var6 = var1 >>> 1;
            long var8 = (var5 & 2) == 0 ? var6 : var1 - var6;
            if ((var5 & 1) == 0) {
               var3 += var1 - var8;
            }

            var1 = var8;
         }

         return var3 + this.nextInt((int)var1);
      }
   }
}
