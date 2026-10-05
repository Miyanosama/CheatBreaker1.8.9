package io.netty.handler.traffic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.udt.nio.NioUdtMessageConnectorChannel$1;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.SingleThreadEventExecutor$2;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.audio.SoundHandler$2;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.model.ModelBanner;
import org.apache.log4j.helpers.SyslogWriter;

public abstract class AbstractTrafficShapingHandler extends ChannelDuplexHandler {
   public static long DEFAULT_MAX_TIME;
   public long writeLimit;
   public static AttributeKey<Runnable> REOPEN_TASK = AttributeKey.valueOf(AbstractTrafficShapingHandler.class.getName() + ".REOPEN_TASK");
   public SoundHandler$2 __junk8233135260963709237;
   public SyslogWriter __junk3228432226828099526;
   public long readLimit;
   public long checkInterval;
   public SingleThreadEventExecutor$2 __junk249431980858587353;
   public ModelBanner __junk3426048697591622952;
   public static long MINIMAL_WAIT;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(AbstractTrafficShapingHandler.class);
   public static long DEFAULT_CHECK_INTERVAL;
   public NioUdtMessageConnectorChannel$1 __junk1835665983547287521;
   public static AttributeKey<Boolean> READ_SUSPENDED = AttributeKey.valueOf(AbstractTrafficShapingHandler.class.getName() + ".READ_SUSPENDED");
   public GuiPlayerTabOverlay __junk2868776248852374999;
   public TrafficCounter trafficCounter;
   public long maxTime = 114360L & 4051142797881523098L;

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      long var3 = this.calculateSize(var2);
      if (var3 > (2122733294243060008L & -2122733294405992384L) && this.trafficCounter != null) {
         long var5 = this.trafficCounter.readTimeToWait(var3, this.readLimit, this.maxTime);
         if (var5 >= (4972984176082027786L & -4972984176701044182L)) {
            if (logger.isDebugEnabled()) {
               logger.debug(
                  "Channel:" + var1.channel().hashCode() + " Read Suspend: " + var5 + ":" + var1.channel().config().isAutoRead() + ":" + isHandlerActive(var1)
               );
            }

            if (var1.channel().config().isAutoRead() && isHandlerActive(var1)) {
               var1.channel().config().setAutoRead(false);
               var1.attr(READ_SUSPENDED).set(true);
               Attribute var7 = var1.attr(REOPEN_TASK);
               Object var8 = (Runnable)var7.get();
               if (var8 == null) {
                  var8 = new AbstractTrafficShapingHandler$ReopenReadTimerTask(var1);
                  var7.set(var8);
               }

               var1.executor().schedule((Runnable)var8, var5, TimeUnit.MILLISECONDS);
               if (logger.isDebugEnabled()) {
                  logger.debug(
                     "Channel:"
                        + var1.channel().hashCode()
                        + " Suspend final status => "
                        + var1.channel().config().isAutoRead()
                        + ":"
                        + isHandlerActive(var1)
                        + " will reopened at: "
                        + var5
                  );
               }
            }
         }
      }

      var1.fireChannelRead(var2);
   }

   public AbstractTrafficShapingHandler(long var1) {
      this(201867916L & 12582978L, 4388050235298342914L & -4388050236710846204L, var1, 1917237978L & 202914712L);
   }

   public void setTrafficCounter(TrafficCounter var1) {
      this.trafficCounter = var1;
   }

   @Override
   public String toString() {
      return "TrafficShaping with Write Limit: "
         + this.writeLimit
         + " Read Limit: "
         + this.readLimit
         + " and Counter: "
         + (this.trafficCounter != null ? this.trafficCounter.toString() : "none");
   }

   public long getReadLimit() {
      return this.readLimit;
   }

   public void setMaxTimeWait(long var1) {
      this.maxTime = var1;
   }

   public AbstractTrafficShapingHandler(long var1, long var3, long var5, long var7) {
      this.checkInterval = -4071203759944481800L & 4071203758424988648L;
      this.writeLimit = var1;
      this.readLimit = var3;
      this.checkInterval = var5;
      this.maxTime = var7;
   }

   public void doAccounting(TrafficCounter var1) {
   }

   public void configure(long var1) {
      this.checkInterval = var1;
      if (this.trafficCounter != null) {
         this.trafficCounter.configure(this.checkInterval);
      }
   }

   public long calculateSize(Object var1) {
      if (var1 instanceof ByteBuf) {
         return ((ByteBuf)var1).readableBytes();
      } else {
         return var1 instanceof ByteBufHolder ? ((ByteBufHolder)var1).content().readableBytes() : -1L & -1L;
      }
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      long var4 = this.calculateSize(var2);
      if (var4 > (1064971L & 67938340L) && this.trafficCounter != null) {
         long var6 = this.trafficCounter.writeTimeToWait(var4, this.writeLimit, this.maxTime);
         if (var6 >= (-2869866757927726070L & 2869866756309024970L)) {
            if (logger.isDebugEnabled()) {
               logger.debug(
                  "Channel:" + var1.channel().hashCode() + " Write suspend: " + var6 + ":" + var1.channel().config().isAutoRead() + ":" + isHandlerActive(var1)
               );
            }

            this.submitWrite(var1, var2, var6, var3);
            return;
         }
      }

      this.submitWrite(var1, var2, -3308267456253919229L & 3308267455597511940L, var3);
   }

   public long getMaxTimeWait() {
      return this.maxTime;
   }

   @Override
   public void read(ChannelHandlerContext var1) {
      if (isHandlerActive(var1)) {
         var1.read();
      }
   }

   public AbstractTrafficShapingHandler() {
      this(-2072998549170743782L & 153632897L, 15265L & 6576835820099272708L, 7452847072404857832L & 52988924L, 6756329958610206617L & -6756329960677295458L);
   }

   public void setCheckInterval(long var1) {
      this.checkInterval = var1;
      if (this.trafficCounter != null) {
         this.trafficCounter.configure(var1);
      }
   }

   public void configure(long var1, long var3) {
      this.writeLimit = var1;
      this.readLimit = var3;
      if (this.trafficCounter != null) {
         this.trafficCounter.resetAccounting(System.currentTimeMillis() + (77088769L & -6889526173141561023L));
      }
   }

   public void setReadLimit(long var1) {
      this.readLimit = var1;
      if (this.trafficCounter != null) {
         this.trafficCounter.resetAccounting(System.currentTimeMillis() + (-8969947757639791357L & 8969947756290924621L));
      }
   }

   public AbstractTrafficShapingHandler(long var1, long var3) {
      this(var1, var3, 276867050L & 207897596L, 145292952L & 626080732L);
   }

   public long getWriteLimit() {
      return this.writeLimit;
   }

   public void configure(long var1, long var3, long var5) {
      this.configure(var1, var3);
      this.configure(var5);
   }

   public void setWriteLimit(long var1) {
      this.writeLimit = var1;
      if (this.trafficCounter != null) {
         this.trafficCounter.resetAccounting(System.currentTimeMillis() + (-5337539269947752445L & 5337539269324614921L));
      }
   }

   public AbstractTrafficShapingHandler(long var1, long var3, long var5) {
      this(var1, var3, var5, -4395020779566088552L & 672545689L);
   }

   public static boolean isHandlerActive(ChannelHandlerContext var0) {
      Boolean var1 = var0.attr(READ_SUSPENDED).get();
      return var1 == null || Boolean.FALSE.equals(var1);
   }

   public abstract void submitWrite(ChannelHandlerContext var1, Object var2, long var3, ChannelPromise var5);

   public TrafficCounter trafficCounter() {
      return this.trafficCounter;
   }

   public long getCheckInterval() {
      return this.checkInterval;
   }
}
