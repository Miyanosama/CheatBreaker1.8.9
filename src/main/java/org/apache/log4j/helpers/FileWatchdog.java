package org.apache.log4j.helpers;

import java.io.File;

public abstract class FileWatchdog extends Thread {
   public boolean warnedAlready;
   public File file;
   public long lastModif;
   public boolean interrupted;
   public long delay = 60000L;
   public static final long recoveredField1306 = 60000L;
   public String filename;

   public FileWatchdog(String var1) {
      super("FileWatchdog");
      this.lastModif = 0L;
      this.warnedAlready = false;
      this.interrupted = false;
      this.filename = var1;
      this.file = new File(var1);
      this.setDaemon(true);
      this.checkAndConfigure();
   }

   public void run() {
      while (!this.interrupted) {
         try {
            Thread.sleep(this.delay);
         } catch (InterruptedException var2) {
         }

         this.checkAndConfigure();
      }
   }

   public abstract void doOnChange();

   public void setDelay(long var1) {
      this.delay = var1;
   }

   public void checkAndConfigure() {
      boolean var1;
      try {
         var1 = this.file.exists();
      } catch (SecurityException var4) {
         LogLog.warn("Was not allowed to read check file existance, file:[" + this.filename + "].");
         this.interrupted = true;
         return;
      }

      if (var1) {
         long var2 = this.file.lastModified();
         if (var2 > this.lastModif) {
            this.lastModif = var2;
            this.doOnChange();
            this.warnedAlready = false;
         }
      } else if (!this.warnedAlready) {
         LogLog.debug("[" + this.filename + "] does not exist.");
         this.warnedAlready = true;
      }
   }
}
