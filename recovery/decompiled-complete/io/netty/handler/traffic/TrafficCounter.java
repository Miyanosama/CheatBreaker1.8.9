package io.netty.handler.traffic;

import io.netty.channel.sctp.nio.NioSctpServerChannel$1;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class TrafficCounter {
   public long lastNonNullReadBytes;
   public long lastReadThroughput;
   public long lastReadBytes;
   public AtomicBoolean monitorActive;
   public ScheduledExecutorService executor;
   public AtomicLong checkInterval;
   public AbstractTrafficShapingHandler trafficShapingHandler;
   public volatile ScheduledFuture<?> scheduledFuture;
   public long lastNonNullWrittenTime;
   public AtomicLong cumulativeReadBytes;
   public AtomicLong lastTime;
   public Runnable monitor;
   public long lastCumulativeTime;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(TrafficCounter.class);
   public AtomicLong currentWrittenBytes = new AtomicLong();
   public long lastWrittenBytes;
   public String name;
   public AtomicLong cumulativeWrittenBytes;
   public long lastNonNullWrittenBytes;
   public NioSctpServerChannel$1 __junk4351004785673021465;
   public long lastWriteThroughput;
   public long lastNonNullReadTime;
   public AtomicLong currentReadBytes = new AtomicLong();

   public long lastTime() {
      return this.lastTime.get();
   }

   public synchronized void start() {
      if (!this.monitorActive.get()) {
         this.lastTime.set(System.currentTimeMillis());
         if (this.checkInterval.get() > (-165146636821446231L & 165146636098472962L)) {
            this.monitorActive.set(true);
            this.monitor = new TrafficCounter$TrafficMonitoringTask(this.trafficShapingHandler, this);
            this.scheduledFuture = this.executor.schedule(this.monitor, this.checkInterval.get(), TimeUnit.MILLISECONDS);
         }
      }
   }

   @Override
   public String toString() {
      return "Monitor "
         + this.name
         + " Current Speed Read: "
         + (this.lastReadThroughput >> 10)
         + " KB/s, Write: "
         + (this.lastWriteThroughput >> 10)
         + " KB/s Current Read: "
         + (this.currentReadBytes.get() >> 10)
         + " KB Current Write: "
         + (this.currentWrittenBytes.get() >> 10)
         + " KB";
   }

   public String name() {
      return this.name;
   }

   public synchronized long writeTimeToWait(long var1, long var3, long var5) {
      this.bytesWriteFlowControl(var1);
      if (var3 == (201605392L & 4989849078566335620L)) {
         return 4202145253114773607L & 1697450752L;
      } else {
         long var7 = this.currentWrittenBytes.get();
         long var9 = System.currentTimeMillis();
         long var11 = var9 - this.lastTime.get();
         if (var11 > (-2870821673045175798L & 554247499L) && var7 > (-2580306835536773116L & 1092616416L)) {
            long var21 = (var7 * (136586216L & 29530106L) / var3 - var11) / (1141358750L & 4650L) * (6413894219764924426L & 1137742875L);
            if (var21 > (-5788186473856417206L & 5788186473824129322L)) {
               if (logger.isDebugEnabled()) {
                  logger.debug("Time: " + var21 + ":" + var7 + ":" + var11);
               }

               return var21 > var5 ? var5 : var21;
            } else {
               return -7834438550064955264L & 7834438548872560897L;
            }
         } else {
            if (this.lastNonNullWrittenBytes > (-1225873518873247744L & 1225873517206313265L)
               && this.lastNonNullWrittenTime + (3529154331534176346L & 1660946474L) < var9) {
               long var20 = var7 + this.lastNonNullWrittenBytes;
               long var22 = var9 - this.lastNonNullWrittenTime;
               long var17 = (var20 * (42001387L & 9197751482187777000L) / var3 - var22) / (534634L & -7510472261912820966L) * (587284554L & 4490122L);
               if (var17 > (370147371L & 136839182L)) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Time: " + var17 + ":" + var20 + ":" + var22);
                  }

                  return var17 > var5 ? var5 : var17;
               }
            } else {
               var7 += this.lastWrittenBytes;
               long var13 = (1076118538L & 142739467L) + Math.abs(var11);
               long var15 = (var7 * (4368458574100170745L & 167969768L) / var3 - var13) / (39977419L & 150994954L) * (171969774L & -5887071505286114789L);
               if (var15 > (1342242842L & -5236816860642270197L)) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Time: " + var15 + ":" + var7 + ":" + var13);
                  }

                  return var15 > var5 ? var5 : var15;
               }
            }

            return -4720465825411104256L & 4720465825263386962L;
         }
      }
   }

   public synchronized void stop() {
      if (this.monitorActive.get()) {
         this.monitorActive.set(false);
         this.resetAccounting(System.currentTimeMillis());
         if (this.trafficShapingHandler != null) {
            this.trafficShapingHandler.doAccounting(this);
         }

         if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
         }
      }
   }

   public void configure(long var1) {
      long var3 = var1 / (1090547902L & 8925224481024576010L) * (-3054194652337647606L & 3054194651726088891L);
      if (this.checkInterval.get() != var3) {
         this.checkInterval.set(var3);
         if (var3 <= (275953152L & 5001671757049692224L)) {
            this.stop();
            this.lastTime.set(System.currentTimeMillis());
         } else {
            this.start();
         }
      }
   }

   public long lastReadThroughput() {
      return this.lastReadThroughput;
   }

   public long lastCumulativeTime() {
      return this.lastCumulativeTime;
   }

   public long lastWrittenBytes() {
      return this.lastWrittenBytes;
   }

   public long lastWriteThroughput() {
      return this.lastWriteThroughput;
   }

   public void bytesRecvFlowControl(long var1) {
      this.currentReadBytes.addAndGet(var1);
      this.cumulativeReadBytes.addAndGet(var1);
   }

   public TrafficCounter(AbstractTrafficShapingHandler var1, ScheduledExecutorService var2, String var3, long var4) {
      this.cumulativeWrittenBytes = new AtomicLong();
      this.cumulativeReadBytes = new AtomicLong();
      this.lastTime = new AtomicLong();
      this.checkInterval = new AtomicLong(-5787999390918474776L & 5787999390275366891L);
      this.monitorActive = new AtomicBoolean();
      this.trafficShapingHandler = var1;
      this.executor = var2;
      this.name = var3;
      this.lastCumulativeTime = System.currentTimeMillis();
      this.configure(var4);
   }

   public synchronized void resetAccounting(long var1) {
      long var3 = var1 - this.lastTime.getAndSet(var1);
      if (var3 != (22020608L & 170821649L)) {
         if (logger.isDebugEnabled() && var3 > (4627108861605578754L & 472514586L) * this.checkInterval()) {
            logger.debug("Acct schedule not ok: " + var3 + " > 2*" + this.checkInterval() + " from " + this.name);
         }

         this.lastReadBytes = this.currentReadBytes.getAndSet(-4470371114102515963L & 34146306L);
         this.lastWrittenBytes = this.currentWrittenBytes.getAndSet(490746516L & 1611760704L);
         this.lastReadThroughput = this.lastReadBytes / var3 * (9170490292909147113L & -9170490294662052872L);
         this.lastWriteThroughput = this.lastWrittenBytes / var3 * (1162871789L & -8357307854462873608L);
         if (this.lastWrittenBytes > (-5572125698912157080L & 5572125697197089156L)) {
            this.lastNonNullWrittenBytes = this.lastWrittenBytes;
            this.lastNonNullWrittenTime = var1;
         }

         if (this.lastReadBytes > (272785649L & -237552642370994172L)) {
            this.lastNonNullReadBytes = this.lastReadBytes;
            this.lastNonNullReadTime = var1;
         }
      }
   }

   public long lastReadBytes() {
      return this.lastReadBytes;
   }

   public long cumulativeWrittenBytes() {
      return this.cumulativeWrittenBytes.get();
   }

   public long cumulativeReadBytes() {
      return this.cumulativeReadBytes.get();
   }

   public long currentReadBytes() {
      return this.currentReadBytes.get();
   }

   public void bytesWriteFlowControl(long var1) {
      this.currentWrittenBytes.addAndGet(var1);
      this.cumulativeWrittenBytes.addAndGet(var1);
   }

   public void resetCumulativeTime() {
      this.lastCumulativeTime = System.currentTimeMillis();
      this.cumulativeReadBytes.set(59642368L & 403791984L);
      this.cumulativeWrittenBytes.set(8671218944960710665L & -8671218945091952288L);
   }

   public long checkInterval() {
      return this.checkInterval.get();
   }

   public synchronized long readTimeToWait(long var1, long var3, long var5) {
      long var7 = System.currentTimeMillis();
      this.bytesRecvFlowControl(var1);
      if (var3 == (3729928307664291920L & 59254031L)) {
         return 100676418L & 1610875017L;
      } else {
         long var9 = this.currentReadBytes.get();
         long var11 = var7 - this.lastTime.get();
         if (var11 > (19487L & 5799472690244915274L) && var9 > (1141776144L & 2065591872327336131L)) {
            long var21 = (var9 * (3608904087171826665L & 1845526504L) / var3 - var11) / (67400251L & 5438143090869176522L) * (5309984868338368538L & 36995466L);
            if (var21 > (90250L & -4183903358277646294L)) {
               if (logger.isDebugEnabled()) {
                  logger.debug("Time: " + var21 + ":" + var9 + ":" + var11);
               }

               return var21 > var5 ? var5 : var21;
            } else {
               return -4947485635576068463L & 67637600L;
            }
         } else {
            if (this.lastNonNullReadBytes > (1216636L & -7637999523854523389L) && this.lastNonNullReadTime + (67633467L & 7082808147600999498L) < var7) {
               long var20 = var9 + this.lastNonNullReadBytes;
               long var22 = var7 - this.lastNonNullReadTime;
               long var17 = (var20 * (1612793848L & -6412202631455407127L) / var3 - var22)
                  / (339746827L & 547921742L)
                  * (-1017726323515195382L & 1017726322044312682L);
               if (var17 > (9506874L & 241492042L)) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Time: " + var17 + ":" + var20 + ":" + var22);
                  }

                  return var17 > var5 ? var5 : var17;
               }
            } else {
               var9 += this.lastReadBytes;
               long var13 = 85990430L & 545333259L;
               long var15 = (var9 * (-9214012493473506326L & 139208697L) / var3 - var13)
                  / (318869550L & -318477815791875766L)
                  * (6663589230320124011L & 1627538206L);
               if (var15 > (1181822987L & 3254196031113398426L)) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Time: " + var15 + ":" + var9 + ":" + var13);
                  }

                  return var15 > var5 ? var5 : var15;
               }
            }

            return 1099170816L & 134219799L;
         }
      }
   }

   public long currentWrittenBytes() {
      return this.currentWrittenBytes.get();
   }
}
