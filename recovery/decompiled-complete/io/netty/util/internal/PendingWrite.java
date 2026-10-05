package io.netty.util.internal;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Promise;
import net.optifine.BlockPosM$1$1;
import org.slf4j.MDC;

public class PendingWrite {
   public Object msg;
   public MDC __junk1180665691159243855;
   public BlockPosM$1$1 __junk5281535710334600789;
   public Recycler$Handle handle;
   public static Recycler<PendingWrite> RECYCLER = new PendingWrite$1();
   public Promise<Void> promise;

   public PendingWrite(Recycler$Handle var1) {
      this.handle = var1;
   }

   public static PendingWrite newInstance(Object var0, Promise<Void> var1) {
      PendingWrite var2 = RECYCLER.get();
      var2.msg = var0;
      var2.promise = var1;
      return var2;
   }

   public boolean recycle() {
      this.msg = null;
      this.promise = null;
      return RECYCLER.recycle(this, this.handle);
   }

   public boolean successAndRecycle() {
      if (this.promise != null) {
         this.promise.setSuccess(null);
      }

      return this.recycle();
   }

   public boolean failAndRecycle(Throwable var1) {
      ReferenceCountUtil.release(this.msg);
      if (this.promise != null) {
         this.promise.setFailure(var1);
      }

      return this.recycle();
   }

   public Promise<Void> promise() {
      return this.promise;
   }

   public Promise<Void> recycleAndGet() {
      Promise var1 = this.promise;
      this.recycle();
      return var1;
   }

   public Object msg() {
      return this.msg;
   }
}
