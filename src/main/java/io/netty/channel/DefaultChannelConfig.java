package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.nio.AbstractNioByteChannel;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.BlockLilyPad;

public class DefaultChannelConfig implements ChannelConfig {
   public volatile int writeBufferLowWaterMark;
   public Channel channel;
   public volatile MessageSizeEstimator msgSizeEstimator;
   public volatile boolean autoClose;
   public volatile int writeBufferHighWaterMark;
   public static final int DEFAULT_CONNECT_TIMEOUT = 30000;
   public volatile boolean autoRead;
   public volatile int writeSpinCount;
   public static RecvByteBufAllocator DEFAULT_RCVBUF_ALLOCATOR = AdaptiveRecvByteBufAllocator.DEFAULT;
   public volatile RecvByteBufAllocator rcvBufAllocator;
   public volatile int maxMessagesPerRead;
   public volatile int connectTimeoutMillis;
   public static MessageSizeEstimator DEFAULT_MSG_SIZE_ESTIMATOR = DefaultMessageSizeEstimator.DEFAULT;
   public volatile ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;

   @Override
   public boolean setOptions(Map<ChannelOption<?>, ?> var1) {
      if (var1 == null) {
         throw new NullPointerException("options");
      } else {
         boolean var2 = true;

         for (Entry var4 : var1.entrySet()) {
            if (!this.setOption((ChannelOption<Object>)var4.getKey(), var4.getValue())) {
               var2 = false;
            }
         }

         return var2;
      }
   }

   public Map<ChannelOption<?>, Object> getOptions(Map<ChannelOption<?>, Object> var1, ChannelOption<?>... var2) {
      if (var1 == null) {
         var1 = new IdentityHashMap();
      }

      for (ChannelOption var6 : var2) {
         var1.put(var6, this.getOption(var6));
      }

      return (Map<ChannelOption<?>, Object>)var1;
   }

   @Override
   public boolean isAutoRead() {
      return this.autoRead;
   }

   public <T> void validate(ChannelOption<T> var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException("option");
      } else {
         var1.validate(var2);
      }
   }

   @Override
   public ChannelConfig setAutoClose(boolean var1) {
      this.autoClose = var1;
      return this;
   }

   @Override
   public int getConnectTimeoutMillis() {
      return this.connectTimeoutMillis;
   }

   @Override
   public int getMaxMessagesPerRead() {
      return this.maxMessagesPerRead;
   }

   @Override
   public ChannelConfig setWriteBufferLowWaterMark(int var1) {
      if (var1 > this.getWriteBufferHighWaterMark()) {
         throw new IllegalArgumentException(
            "writeBufferLowWaterMark cannot be greater than writeBufferHighWaterMark (" + this.getWriteBufferHighWaterMark() + "): " + var1
         );
      } else if (var1 < 0) {
         throw new IllegalArgumentException("writeBufferLowWaterMark must be >= 0");
      } else {
         this.writeBufferLowWaterMark = var1;
         return this;
      }
   }

   @Override
   public ByteBufAllocator getAllocator() {
      return this.allocator;
   }

   @Override
   public ChannelConfig setWriteBufferHighWaterMark(int var1) {
      if (var1 < this.getWriteBufferLowWaterMark()) {
         throw new IllegalArgumentException(
            "writeBufferHighWaterMark cannot be less than writeBufferLowWaterMark (" + this.getWriteBufferLowWaterMark() + "): " + var1
         );
      } else if (var1 < 0) {
         throw new IllegalArgumentException("writeBufferHighWaterMark must be >= 0");
      } else {
         this.writeBufferHighWaterMark = var1;
         return this;
      }
   }

   @Override
   public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      if (var1 == null) {
         throw new NullPointerException("allocator");
      } else {
         this.rcvBufAllocator = var1;
         return this;
      }
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         null,
         ChannelOption.CONNECT_TIMEOUT_MILLIS,
         ChannelOption.MAX_MESSAGES_PER_READ,
         ChannelOption.WRITE_SPIN_COUNT,
         ChannelOption.ALLOCATOR,
         ChannelOption.AUTO_READ,
         ChannelOption.AUTO_CLOSE,
         ChannelOption.RCVBUF_ALLOCATOR,
         ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK,
         ChannelOption.WRITE_BUFFER_LOW_WATER_MARK,
         ChannelOption.MESSAGE_SIZE_ESTIMATOR
      );
   }

   public DefaultChannelConfig(Channel var1) {
      this.rcvBufAllocator = DEFAULT_RCVBUF_ALLOCATOR;
      this.msgSizeEstimator = DEFAULT_MSG_SIZE_ESTIMATOR;
      this.connectTimeoutMillis = 30000;
      this.writeSpinCount = 16;
      this.autoRead = true;
      this.autoClose = true;
      this.writeBufferHighWaterMark = 65536;
      this.writeBufferLowWaterMark = 32768;
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         this.channel = var1;
         if (!(var1 instanceof ServerChannel) && !(var1 instanceof AbstractNioByteChannel)) {
            this.maxMessagesPerRead = 1;
         } else {
            this.maxMessagesPerRead = 16;
         }
      }
   }

   @Override
   public ChannelConfig setWriteSpinCount(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("writeSpinCount must be a positive integer.");
      } else {
         this.writeSpinCount = var1;
         return this;
      }
   }

   @Override
   public int getWriteSpinCount() {
      return this.writeSpinCount;
   }

   @Override
   public ChannelConfig setAllocator(ByteBufAllocator var1) {
      if (var1 == null) {
         throw new NullPointerException("allocator");
      } else {
         this.allocator = var1;
         return this;
      }
   }

   @Override
   public ChannelConfig setConnectTimeoutMillis(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(String.format("connectTimeoutMillis: %d (expected: >= 0)", var1));
      } else {
         this.connectTimeoutMillis = var1;
         return this;
      }
   }

   @Override
   public boolean isAutoClose() {
      return this.autoClose;
   }

   @Override
   public MessageSizeEstimator getMessageSizeEstimator() {
      return this.msgSizeEstimator;
   }

   @Override
   public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      if (var1 == null) {
         throw new NullPointerException("estimator");
      } else {
         this.msgSizeEstimator = var1;
         return this;
      }
   }

   @Override
   public ChannelConfig setMaxMessagesPerRead(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("maxMessagesPerRead: " + var1 + " (expected: > 0)");
      } else {
         this.maxMessagesPerRead = var1;
         return this;
      }
   }

   @Override
   public int getWriteBufferLowWaterMark() {
      return this.writeBufferLowWaterMark;
   }

   @Override
   public ChannelConfig setAutoRead(boolean var1) {
      boolean var2 = this.autoRead;
      this.autoRead = var1;
      if (var1 && !var2) {
         this.channel.read();
      } else if (!var1 && var2) {
         this.autoReadCleared();
      }

      return this;
   }

   public void autoReadCleared() {
   }

   @Override
   public int getWriteBufferHighWaterMark() {
      return this.writeBufferHighWaterMark;
   }

   @Override
   public RecvByteBufAllocator getRecvByteBufAllocator() {
      return this.rcvBufAllocator;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == null) {
         throw new NullPointerException("option");
      } else if (var1 == ChannelOption.CONNECT_TIMEOUT_MILLIS) {
         return (T)(Object)this.getConnectTimeoutMillis();
      } else if (var1 == ChannelOption.MAX_MESSAGES_PER_READ) {
         return (T)(Object)this.getMaxMessagesPerRead();
      } else if (var1 == ChannelOption.WRITE_SPIN_COUNT) {
         return (T)(Object)this.getWriteSpinCount();
      } else if (var1 == ChannelOption.ALLOCATOR) {
         return (T)this.getAllocator();
      } else if (var1 == ChannelOption.RCVBUF_ALLOCATOR) {
         return (T)this.getRecvByteBufAllocator();
      } else if (var1 == ChannelOption.AUTO_READ) {
         return (T)(Object)this.isAutoRead();
      } else if (var1 == ChannelOption.AUTO_CLOSE) {
         return (T)(Object)this.isAutoClose();
      } else if (var1 == ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK) {
         return (T)(Object)this.getWriteBufferHighWaterMark();
      } else if (var1 == ChannelOption.WRITE_BUFFER_LOW_WATER_MARK) {
         return (T)(Object)this.getWriteBufferLowWaterMark();
      } else {
         return (T)(var1 == ChannelOption.MESSAGE_SIZE_ESTIMATOR ? this.getMessageSizeEstimator() : null);
      }
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.CONNECT_TIMEOUT_MILLIS) {
         this.setConnectTimeoutMillis((Integer)var2);
      } else if (var1 == ChannelOption.MAX_MESSAGES_PER_READ) {
         this.setMaxMessagesPerRead((Integer)var2);
      } else if (var1 == ChannelOption.WRITE_SPIN_COUNT) {
         this.setWriteSpinCount((Integer)var2);
      } else if (var1 == ChannelOption.ALLOCATOR) {
         this.setAllocator((ByteBufAllocator)var2);
      } else if (var1 == ChannelOption.RCVBUF_ALLOCATOR) {
         this.setRecvByteBufAllocator((RecvByteBufAllocator)var2);
      } else if (var1 == ChannelOption.AUTO_READ) {
         this.setAutoRead((Boolean)var2);
      } else if (var1 == ChannelOption.AUTO_CLOSE) {
         this.setAutoClose((Boolean)var2);
      } else if (var1 == ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK) {
         this.setWriteBufferHighWaterMark((Integer)var2);
      } else if (var1 == ChannelOption.WRITE_BUFFER_LOW_WATER_MARK) {
         this.setWriteBufferLowWaterMark((Integer)var2);
      } else {
         if (var1 != ChannelOption.MESSAGE_SIZE_ESTIMATOR) {
            return false;
         }

         this.setMessageSizeEstimator((MessageSizeEstimator)var2);
      }

      return true;
   }
}
