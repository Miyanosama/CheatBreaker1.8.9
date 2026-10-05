package org.apache.log4j.varia;

import org.apache.log4j.RollingFileAppender;

public class ExternallyRolledFileAppender extends RollingFileAppender {
   public static final String recoveredField1814 = "OK";
   public HUP hup;
   public static final String recoveredField1815 = "RollOver";
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
