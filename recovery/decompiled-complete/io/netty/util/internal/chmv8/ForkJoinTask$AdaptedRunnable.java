package io.netty.util.internal.chmv8;

import io.netty.handler.codec.http.ComposedLastHttpContent;
import io.netty.util.ThreadDeathWatcher$Watcher;
import java.util.concurrent.RunnableFuture;
import net.minecraft.client.gui.GuiScreenBook;

public class ForkJoinTask$AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
   public T result;
   public ThreadDeathWatcher$Watcher __junk1944628478437903801;
   public Runnable runnable;
   public static long serialVersionUID;
   public ComposedLastHttpContent __junk4075075060439596938;
   public GuiScreenBook __junk6218249617594450056;

   @Override
   public boolean exec() {
      this.runnable.run();
      return true;
   }

   @Override
   public T getRawResult() {
      return this.result;
   }

   @Override
   public void run() {
      this.invoke();
   }

   public ForkJoinTask$AdaptedRunnable(Runnable var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.runnable = var1;
         this.result = (T)var2;
      }
   }

   @Override
   public void setRawResult(T var1) {
      this.result = (T)var1;
   }
}
