package org.apache.log4j.spi;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

public class RootCategory extends Logger {
   public RootCategory(Level var1) {
      super("root");
      this.setLevel(var1);
   }

   public void setLevel(Level var1) {
      if (var1 == null) {
         LogLog.error("You have tried to set a null level to root.", new Throwable());
      } else {
         this.level = var1;
      }
   }

   public void setPriority(Level var1) {
      this.setLevel(var1);
   }

   public Level getChainedLevel() {
      return this.level;
   }
}
