package io.netty.channel.rxtx;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.internal.IntegerHolder;
import java.util.Map;
import net.minecraft.potion.PotionAttackDamage;

public class DefaultRxtxChannelConfig extends DefaultChannelConfig implements RxtxChannelConfig {
   public volatile int baudrate = 115200;
   public volatile RxtxChannelConfig.Stopbits stopbits = RxtxChannelConfig.Stopbits.STOPBITS_1;
   public volatile int readTimeout;
   public volatile boolean dtr;
   public volatile boolean rts;
   public volatile RxtxChannelConfig.Paritybit paritybit;
   public volatile int waitTime;
   public volatile RxtxChannelConfig.Databits databits = RxtxChannelConfig.Databits.DATABITS_8;

   @Override
   public RxtxChannelConfig setBaudrate(int var1) {
      this.baudrate = var1;
      return this;
   }

   @Override
   public RxtxChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public int getReadTimeout() {
      return this.readTimeout;
   }

   @Override
   public RxtxChannelConfig setWaitTimeMillis(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Wait time must be >= 0");
      } else {
         this.waitTime = var1;
         return this;
      }
   }

   @Override
   public RxtxChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == RxtxChannelOption.BAUD_RATE) {
         this.setBaudrate((Integer)var2);
      } else if (var1 == RxtxChannelOption.DTR) {
         this.setDtr((Boolean)var2);
      } else if (var1 == RxtxChannelOption.RTS) {
         this.setRts((Boolean)var2);
      } else if (var1 == RxtxChannelOption.STOP_BITS) {
         this.setStopbits((RxtxChannelConfig.Stopbits)var2);
      } else if (var1 == RxtxChannelOption.DATA_BITS) {
         this.setDatabits((RxtxChannelConfig.Databits)var2);
      } else if (var1 == RxtxChannelOption.PARITY_BIT) {
         this.setParitybit((RxtxChannelConfig.Paritybit)var2);
      } else if (var1 == RxtxChannelOption.WAIT_TIME) {
         this.setWaitTimeMillis((Integer)var2);
      } else {
         if (var1 != RxtxChannelOption.READ_TIMEOUT) {
            return super.setOption(var1, var2);
         }

         this.setReadTimeout((Integer)var2);
      }

      return true;
   }

   @Override
   public RxtxChannelConfig setRts(boolean var1) {
      this.rts = var1;
      return this;
   }

   @Override
   public RxtxChannelConfig.Stopbits getStopbits() {
      return this.stopbits;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == RxtxChannelOption.BAUD_RATE) {
         return (T)(Object)this.getBaudrate();
      } else if (var1 == RxtxChannelOption.DTR) {
         return (T)(Object)this.isDtr();
      } else if (var1 == RxtxChannelOption.RTS) {
         return (T)(Object)this.isRts();
      } else if (var1 == RxtxChannelOption.STOP_BITS) {
         return (T)this.getStopbits();
      } else if (var1 == RxtxChannelOption.DATA_BITS) {
         return (T)this.getDatabits();
      } else if (var1 == RxtxChannelOption.PARITY_BIT) {
         return (T)this.getParitybit();
      } else if (var1 == RxtxChannelOption.WAIT_TIME) {
         return (T)(Object)this.getWaitTimeMillis();
      } else {
         return (T)(var1 == RxtxChannelOption.READ_TIMEOUT ? this.getReadTimeout() : super.getOption(var1));
      }
   }

   @Override
   public RxtxChannelConfig.Paritybit getParitybit() {
      return this.paritybit;
   }

   @Override
   public boolean isRts() {
      return this.rts;
   }

   @Override
   public int getWaitTimeMillis() {
      return this.waitTime;
   }

   @Override
   public RxtxChannelConfig.Databits getDatabits() {
      return this.databits;
   }

   @Override
   public RxtxChannelConfig setDatabits(RxtxChannelConfig.Databits var1) {
      this.databits = var1;
      return this;
   }

   @Override
   public RxtxChannelConfig setDtr(boolean var1) {
      this.dtr = var1;
      return this;
   }

   @Override
   public RxtxChannelConfig setStopbits(RxtxChannelConfig.Stopbits var1) {
      this.stopbits = var1;
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         super.getOptions(),
         RxtxChannelOption.BAUD_RATE,
         RxtxChannelOption.DTR,
         RxtxChannelOption.RTS,
         RxtxChannelOption.STOP_BITS,
         RxtxChannelOption.DATA_BITS,
         RxtxChannelOption.PARITY_BIT,
         RxtxChannelOption.WAIT_TIME
      );
   }

   @Override
   public RxtxChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public boolean isDtr() {
      return this.dtr;
   }

   @Override
   public int getBaudrate() {
      return this.baudrate;
   }

   @Override
   public RxtxChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   public DefaultRxtxChannelConfig(RxtxChannel var1) {
      super(var1);
      this.paritybit = RxtxChannelConfig.Paritybit.NONE;
      this.readTimeout = 1000;
   }

   @Override
   public RxtxChannelConfig setReadTimeout(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("readTime must be >= 0");
      } else {
         this.readTimeout = var1;
         return this;
      }
   }

   @Override
   public RxtxChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public RxtxChannelConfig setParitybit(RxtxChannelConfig.Paritybit var1) {
      this.paritybit = var1;
      return this;
   }
}
