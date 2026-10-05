package org.apache.log4j.helpers;

import io.netty.handler.codec.http.HttpResponseDecoder;
import java.io.File;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonDeath;
import net.optifine.entity.model.anim.ModelVariableFloat;

public abstract class FileWatchdog extends Thread {
   public HttpResponseDecoder field_0007;
   public boolean warnedAlready;
   public File file;
   public long lastModif;
   public boolean interrupted;
   public long delay = 103349856L & 1108586L;
   public static long field_0005;
   public ModelVariableFloat field_0003;
   public String filename;
   public LayerEnderDragonDeath field_0000;

   public FileWatchdog(String var1) {
      super("FileWatchdog");
      this.lastModif = 655890L & 5654872495514195204L;
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
