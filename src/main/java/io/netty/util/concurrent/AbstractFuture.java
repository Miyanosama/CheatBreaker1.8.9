package io.netty.util.concurrent;

import io.netty.handler.codec.compression.JZlibDecoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractFuture<V> implements Future<V> {

   @Override
   public V get() throws java.lang.InterruptedException, java.util.concurrent.ExecutionException {
      this.await();
      Throwable var1 = this.cause();
      if (var1 == null) {
         return this.getNow();
      } else {
         throw new ExecutionException(var1);
      }
   }

   @Override
   public V get(long var1, TimeUnit var3) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
      if (this.await(var1, var3)) {
         Throwable var4 = this.cause();
         if (var4 == null) {
            return this.getNow();
         } else {
            throw new ExecutionException(var4);
         }
      } else {
         throw new TimeoutException();
      }
   }
}
