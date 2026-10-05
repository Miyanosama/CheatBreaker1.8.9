package io.netty.util.concurrent;

import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.util.internal.PlatformDependent;

public class FailedFuture<V> extends CompleteFuture<V> {
   public HttpObjectDecoder __junk6278370063737605734;
   public Throwable cause;

   public FailedFuture(EventExecutor var1, Throwable var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("cause");
      } else {
         this.cause = var2;
      }
   }

   @Override
   public Future<V> syncUninterruptibly() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   @Override
   public V getNow() {
      return null;
   }

   @Override
   public Future<V> sync() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   @Override
   public boolean isSuccess() {
      return false;
   }

   @Override
   public Throwable cause() {
      return this.cause;
   }
}
