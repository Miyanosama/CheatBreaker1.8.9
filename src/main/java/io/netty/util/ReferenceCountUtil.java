package io.netty.util;

import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.optifine.entity.model.ModelAdapterMagmaCube;
import net.optifine.entity.model.anim.ModelVariableType;
import recovered.unidentified.UnidentifiedClass3246;
import com.cheatbreaker.client.util.discord.DiscordReadyListener;

public class ReferenceCountUtil {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ReferenceCountUtil.class);

   public static <T> T retain(T var0) {
      return (T)(var0 instanceof ReferenceCounted ? ((ReferenceCounted)var0).retain() : var0);
   }

   public static <T> T releaseLater(T var0, int var1) {
      if (var0 instanceof ReferenceCounted) {
         ThreadDeathWatcher.watch(Thread.currentThread(), new ReferenceCountUtil.ReleasingTask((ReferenceCounted)var0, var1));
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

   public static final class ReleasingTask implements Runnable {
      public int decrement;
      public ReferenceCounted obj;

      public ReleasingTask(ReferenceCounted var1, int var2) {
         this.obj = var1;
         this.decrement = var2;
      }

      @Override
      public void run() {
         try {
            if (!this.obj.release(this.decrement)) {
               ReferenceCountUtil.logger.warn("Non-zero refCnt: {}", this);
            } else {
               ReferenceCountUtil.logger.debug("Released: {}", this);
            }
         } catch (Exception var2) {
            ReferenceCountUtil.logger.warn("Failed to release an object: {}", this.obj, var2);
         }
      }

      @Override
      public String toString() {
         return StringUtil.simpleClassName(this.obj) + ".release(" + this.decrement + ") refCnt: " + this.obj.refCnt();
      }
   }
}
