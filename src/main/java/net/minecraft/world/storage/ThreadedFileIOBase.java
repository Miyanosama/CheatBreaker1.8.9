package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class ThreadedFileIOBase implements Runnable {
   public static ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
   public List<IThreadedFileIO> threadedIOQueue = Collections.synchronizedList(Lists.newArrayList());
   public volatile long recoveredField3516;
   public volatile boolean isThreadWaiting;
   public volatile long recoveredField3517;

   public void waitForFinish() throws java.lang.InterruptedException {
      this.isThreadWaiting = true;

      while (this.recoveredField3517 != this.recoveredField3516) {
         Thread.sleep(10L);
      }

      this.isThreadWaiting = false;
   }

   public ThreadedFileIOBase() {
      Thread var1 = new Thread(this, "File IO Thread");
      var1.setPriority(1);
      var1.start();
   }

   public void queueIO(IThreadedFileIO var1) {
      if (!this.threadedIOQueue.contains(var1)) {
         this.recoveredField3517++;
         this.threadedIOQueue.add(var1);
      }
   }

   public void processQueue() {
      for (int var1 = 0; var1 < this.threadedIOQueue.size(); var1++) {
         IThreadedFileIO var2 = this.threadedIOQueue.get(var1);
         boolean var3 = var2.writeNextIO();
         if (!var3) {
            this.threadedIOQueue.remove(var1--);
            this.recoveredField3516++;
         }

         try {
            Thread.sleep(this.isThreadWaiting ? 0L : 10L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if (this.threadedIOQueue.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }
      }
   }

   public static ThreadedFileIOBase getThreadedIOInstance() {
      return threadedIOInstance;
   }

   @Override
   public void run() {
      while (true) {
         this.processQueue();
      }
   }
}
