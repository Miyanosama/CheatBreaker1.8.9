package io.netty.channel;

import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.ThreadFactory;
import recovered.unidentified.UnidentifiedClass1118;

public abstract class MultithreadEventLoopGroup extends MultithreadEventExecutorGroup implements EventLoopGroup {
   public ObjectEncoder __junk3354861418874038778;
   public UnidentifiedClass1118 __junk2273676660360974321;
   public static int DEFAULT_EVENT_LOOP_THREADS = Math.max(
      1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2)
   );
   public static InternalLogger logger = InternalLoggerFactory.getInstance(MultithreadEventLoopGroup.class);

   public MultithreadEventLoopGroup(int var1, ThreadFactory var2, Object... var3) {
      super(var1 == 0 ? DEFAULT_EVENT_LOOP_THREADS : var1, var2, var3);
   }

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      return this.next().register(var1, var2);
   }

   @Override
   public ChannelFuture register(Channel var1) {
      return this.next().register(var1);
   }

   static {
      if (logger.isDebugEnabled()) {
         logger.debug("-Dio.netty.eventLoopThreads: {}", DEFAULT_EVENT_LOOP_THREADS);
      }
   }

   @Override
   public ThreadFactory newDefaultThreadFactory() {
      return new DefaultThreadFactory(this.getClass(), 10);
   }

   @Override
   public EventLoop next() {
      return (EventLoop)super.next();
   }
}
