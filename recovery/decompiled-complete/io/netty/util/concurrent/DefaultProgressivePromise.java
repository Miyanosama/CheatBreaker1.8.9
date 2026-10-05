package io.netty.util.concurrent;

import io.netty.channel.local.LocalServerChannel$2;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.world.WorldServer$1;

public class DefaultProgressivePromise<V> extends DefaultPromise<V> implements ProgressivePromise<V> {
   public S45PacketTitle __junk7922338700675245228;
   public Blocks __junk2829964608744383201;
   public WorldServer$1 __junk5269354387990430856;
   public LocalServerChannel$2 __junk1184259917494049240;

   public DefaultProgressivePromise(EventExecutor var1) {
      super(var1);
   }

   @Override
   public ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1) {
      super.removeListener(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> setFailure(Throwable var1) {
      super.setFailure(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> await() {
      super.await();
      return this;
   }

   public DefaultProgressivePromise() {
   }

   @Override
   public ProgressivePromise<V> setSuccess(V var1) {
      super.setSuccess((V)var1);
      return this;
   }

   @Override
   public boolean tryProgress(long var1, long var3) {
      if (var3 < (-8194713600866713403L & 44068904L)) {
         var3 = -1L & -1L;
         if (var1 < (-2247922118937276382L & 2247922118659260632L) || this.isDone()) {
            return false;
         }
      } else if (var1 < (139330144L & 589305870L) || var1 > var3 || this.isDone()) {
         return false;
      }

      this.notifyProgressiveListeners(var1, var3);
      return true;
   }

   @Override
   public ProgressivePromise<V> syncUninterruptibly() {
      super.syncUninterruptibly();
      return this;
   }

   @Override
   public ProgressivePromise<V> setProgress(long var1, long var3) {
      if (var3 < (-786371263650843392L & 786371261998678540L)) {
         var3 = -1L & -1L;
         if (var1 < (558269957340796449L & -558269958916652902L)) {
            throw new IllegalArgumentException("progress: " + var1 + " (expected: >= 0)");
         }
      } else if (var1 < (-506469731104980472L & 506469729321881746L) || var1 > var3) {
         throw new IllegalArgumentException("progress: " + var1 + " (expected: 0 <= progress <= total (" + var3 + "))");
      }

      if (this.isDone()) {
         throw new IllegalStateException("complete already");
      } else {
         this.notifyProgressiveListeners(var1, var3);
         return this;
      }
   }

   @Override
   public ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      super.addListeners(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> awaitUninterruptibly() {
      super.awaitUninterruptibly();
      return this;
   }

   @Override
   public ProgressivePromise<V> sync() {
      super.sync();
      return this;
   }
}
