package io.netty.util.internal.chmv8;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import net.minecraft.server.management.PlayerProfileCache$Serializer;
import net.minecraft.util.LoggingPrintStream;
import net.optifine.util.ResUtils;

public class ForkJoinTask$AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
   public static long serialVersionUID;
   public PlayerProfileCache$Serializer __junk986707792577576949;
   public Callable<? extends T> callable;
   public T result;
   public ResUtils __junk7088691406772103896;
   public LoggingPrintStream __junk1145691021204670589;

   @Override
   public boolean exec() {
      try {
         this.result = (T)this.callable.call();
         return true;
      } catch (Error var2) {
         throw var2;
      } catch (RuntimeException var3) {
         throw var3;
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   @Override
   public T getRawResult() {
      return this.result;
   }

   public ForkJoinTask$AdaptedCallable(Callable<? extends T> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.callable = var1;
      }
   }

   @Override
   public void run() {
      this.invoke();
   }

   @Override
   public void setRawResult(T var1) {
      this.result = (T)var1;
   }
}
