package org.apache.log4j.xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.FileWatchdog;

public class XMLWatchdog extends FileWatchdog {
   public void doOnChange() {
      new DOMConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
   }

   public XMLWatchdog(String var1) {
      super(var1);
   }
}
