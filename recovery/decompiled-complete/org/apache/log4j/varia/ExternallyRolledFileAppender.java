package org.apache.log4j.varia;

import net.minecraft.inventory.ContainerWorkbench;
import org.apache.log4j.RollingFileAppender;

public class ExternallyRolledFileAppender extends RollingFileAppender {
   public ContainerWorkbench field_0003;
   public static String field_0004;
   public HUP hup;
   public static String field_0001;
   public int port = 0;

   public int getPort() {
      return this.port;
   }

   public void activateOptions() {
      super.activateOptions();
      if (this.port != 0) {
         if (this.hup != null) {
            this.hup.interrupt();
         }

         this.hup = new HUP(this, this.port);
         this.hup.setDaemon(true);
         this.hup.start();
      }
   }

   public void setPort(int var1) {
      this.port = var1;
   }
}
