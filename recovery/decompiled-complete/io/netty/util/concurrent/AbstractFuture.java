package io.netty.util.concurrent;

import io.netty.handler.codec.compression.JZlibDecoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.minecraft.item.Item$13;

public abstract class AbstractFuture<V> implements Future<V> {
   public JZlibDecoder __junk3895247983573265029;
   public Item$13 __junk4909458804505305454;

   @Override
   public V get() {
      this.await();
      Throwable var1 = this.cause();
      if (var1 == null) {
         return this.getNow();
      } else {
         throw new ExecutionException(var1);
      }
   }

   @Override
   public V get(long var1, TimeUnit var3) {
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
