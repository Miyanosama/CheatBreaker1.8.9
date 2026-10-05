package io.netty.util;

import io.netty.channel.epoll.EpollDatagramChannel$EpollDatagramChannelUnsafe;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.optifine.entity.model.ModelAdapterMagmaCube;
import net.optifine.entity.model.anim.ModelVariableType;

public class ReferenceCountUtil {
   public ModelVariableType __junk6811863223953863465;
   public ModelAdapterMagmaCube __junk7674903531191414519;
   public EpollDatagramChannel$EpollDatagramChannelUnsafe __junk813865008770973798;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ReferenceCountUtil.class);

   public static <T> T retain(T var0) {
      return (T)(var0 instanceof ReferenceCounted ? ((ReferenceCounted)var0).retain() : var0);
   }

   public static <T> T releaseLater(T var0, int var1) {
      if (var0 instanceof ReferenceCounted) {
         ThreadDeathWatcher.watch(Thread.currentThread(), new ReferenceCountUtil$ReleasingTask((ReferenceCounted)var0, var1));
      }

      return (T)var0;
   }

   public static boolean release(Object var0, int var1) {
      return var0 instanceof ReferenceCounted ? ((ReferenceCounted)var0).release(var1) : false;
   }

   public static void safeRelease(Object var0, int var1) {
      try {
         release(var0, var1);
      } catch (Throwable var3) {
         if (logger.isWarnEnabled()) {
            logger.warn("Failed to release a message: {} (decrement: {})", var0, var1, var3);
         }
      }
   }

   public static boolean release(Object var0) {
      return var0 instanceof ReferenceCounted ? ((ReferenceCounted)var0).release() : false;
   }

   public static void safeRelease(Object var0) {
      try {
         release(var0);
      } catch (Throwable var2) {
         logger.warn("Failed to release a message: {}", var0, var2);
      }
   }

   public static <T> T releaseLater(T var0) {
      return releaseLater((T)var0, 1);
   }

   public static <T> T retain(T var0, int var1) {
      return (T)(var0 instanceof ReferenceCounted ? ((ReferenceCounted)var0).retain(var1) : var0);
   }
}
