package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.enchantment.EnchantmentHelper$HurtIterator;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.world.DifficultyInstance;

public class ThreadedFileIOBase implements Runnable {
   public EntityEnderEye field_0003;
   public static ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
   public EnchantmentHelper$HurtIterator field_0002;
   public List<IThreadedFileIO> threadedIOQueue = Collections.synchronizedList(Lists.newArrayList());
   public volatile long field_0000;
   public volatile boolean isThreadWaiting;
   public volatile long field_0007;
   public DifficultyInstance field_0004;

   public void waitForFinish() {
      this.isThreadWaiting = true;

      while (this.field_0007 != this.field_0000) {
         Thread.sleep(7423117250397478922L & -7423117251135205574L);
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
         this.field_0007 += 144873793L & 828113943L;
         this.threadedIOQueue.add(var1);
      }
   }

   public void processQueue() {
      for (int var1 = 0; var1 < this.threadedIOQueue.size(); var1++) {
         IThreadedFileIO var2 = this.threadedIOQueue.get(var1);
         boolean var3 = var2.writeNextIO();
         if (!var3) {
            this.threadedIOQueue.remove(var1--);
            this.field_0000 += 143032329L & 3383249201889034819L;
         }

         try {
            Thread.sleep(this.isThreadWaiting ? 8606912059474641425L & 289426724L : 67282991L & -8303450682680147686L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if (this.threadedIOQueue.isEmpty()) {
         try {
            Thread.sleep(1753219867L & 4609744059771402265L);
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
