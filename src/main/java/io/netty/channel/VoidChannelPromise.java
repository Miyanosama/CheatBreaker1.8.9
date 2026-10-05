package io.netty.channel;

import io.netty.util.concurrent.AbstractFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.util.JsonException;

public class VoidChannelPromise extends AbstractFuture<Void> implements ChannelPromise {
   public Channel channel;
   public boolean fireException;

   public VoidChannelPromise sync() {
      fail();
      return this;
   }

   @Override
   public boolean setUncancellable() {
      return true;
   }

   @Override
   public boolean trySuccess() {
      return false;
   }

   public VoidChannelPromise syncUninterruptibly() {
      fail();
      return this;
   }

   public boolean trySuccess(Void var1) {
      return false;
   }

   public static void fail() {
      throw new IllegalStateException("void future");
   }

   public VoidChannelPromise awaitUninterruptibly() {
      fail();
      return this;
   }

   @Override
   public boolean isSuccess() {
      return false;
   }

   @Override
   public boolean isDone() {
      return false;
   }

   public VoidChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      fail();
      return this;
   }

   @Override
   public Throwable cause() {
      return null;
   }

   @Override
   public boolean awaitUninterruptibly(long var1) {
      fail();
      return false;
   }

   public VoidChannelPromise setSuccess() {
      return this;
   }

   public VoidChannelPromise setFailure(Throwable var1) {
      this.fireException(var1);
      return this;
   }

   @Override
   public boolean await(long var1) {
      fail();
      return false;
   }

   @Override
   public boolean tryFailure(Throwable var1) {
      this.fireException(var1);
      return false;
   }

   public VoidChannelPromise await() throws java.lang.InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return this;
      }
   }

   @Override
   public boolean isCancelled() {
      return false;
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   public Void getNow() {
      return null;
   }

   public void fireException(Throwable var1) {
      if (this.fireException && this.channel.isRegistered()) {
         this.channel.pipeline().fireExceptionCaught(var1);
      }
   }

   @Override
   public boolean await(long var1, TimeUnit var3) {
      fail();
      return false;
   }

   public VoidChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      return this;
   }

   public VoidChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      fail();
      return this;
   }

   public VoidChannelPromise setSuccess(Void var1) {
      return this;
   }

   @Override
   public boolean awaitUninterruptibly(long var1, TimeUnit var3) {
      fail();
      return false;
   }

   public VoidChannelPromise(Channel var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         this.channel = var1;
         this.fireException = var2;
      }
   }

   public VoidChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      return this;
   }

   @Override
   public boolean cancel(boolean var1) {
      return false;
   }

   @Override
   public boolean isCancellable() {
      return false;
   }
}
