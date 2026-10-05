package io.netty.handler.codec.http.cors;

import java.util.concurrent.Callable;

public class CorsConfig$ConstantValueGenerator implements Callable<Object> {
   public Object value;

   public CorsConfig$ConstantValueGenerator(Object var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("value must not be null");
      } else {
         this.value = var1;
      }
   }

   @Override
   public Object call() {
      return this.value;
   }
}
