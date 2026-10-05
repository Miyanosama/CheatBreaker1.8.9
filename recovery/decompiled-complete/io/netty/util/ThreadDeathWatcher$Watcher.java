package io.netty.util;

import io.netty.util.internal.RecyclableArrayList$1;
import java.util.ArrayList;
import java.util.List;
import recovered.unidentified.UnidentifiedClass3581;

public class ThreadDeathWatcher$Watcher implements Runnable {
   public UnidentifiedClass3581 __junk224258166637868927;
   public RecyclableArrayList$1 __junk2327419411356860604;
   public List<ThreadDeathWatcher$Entry> watchees = new ArrayList<>();

   @Override
   public void run() {
      while (true) {
         this.fetchWatchees();
         this.notifyWatchees();
         this.fetchWatchees();
         this.notifyWatchees();

         try {
            Thread.sleep(83985401L & 3385471334466196460L);
         } catch (InterruptedException var2) {
         }

         if (this.watchees.isEmpty() && ThreadDeathWatcher.access$100().isEmpty()) {
            boolean var1 = ThreadDeathWatcher.access$200().compareAndSet(true, false);
            if (!$assertionsDisabled && !var1) {
               throw new AssertionError();
            }

            if (ThreadDeathWatcher.access$100().isEmpty() || !ThreadDeathWatcher.access$200().compareAndSet(false, true)) {
               return;
            }
         }
      }
   }

   public void notifyWatchees() {
      List var1 = this.watchees;
      int var2 = 0;

      while (var2 < var1.size()) {
         ThreadDeathWatcher$Entry var3 = (ThreadDeathWatcher$Entry)var1.get(var2);
         if (!var3.thread.isAlive()) {
            var1.remove(var2);

            try {
               var3.task.run();
            } catch (Throwable var5) {
               ThreadDeathWatcher.access$300().warn("Thread death watcher task raised an exception:", var5);
            }
         } else {
            var2++;
         }
      }
   }

   public void fetchWatchees() {
      while (true) {
         ThreadDeathWatcher$Entry var1 = (ThreadDeathWatcher$Entry)ThreadDeathWatcher.access$100().poll();
         if (var1 == null) {
            return;
         }

         if (var1.isWatch) {
            this.watchees.add(var1);
         } else {
            this.watchees.remove(var1);
         }
      }
   }

   public ThreadDeathWatcher$Watcher() {
   }
}
