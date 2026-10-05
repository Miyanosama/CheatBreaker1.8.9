package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class NOPLoggerRepository implements LoggerRepository {
   public void setThreshold(String var1) {
   }

   public Logger exists(String var1) {
      return null;
   }

   public void setThreshold(Level var1) {
   }

   public Logger getLogger(String var1, LoggerFactory var2) {
      return new NOPLogger(this, var1);
   }

   public Enumeration getCurrentLoggers() {
      return new Vector().elements();
   }

   public void emitNoAppenderWarning(Category var1) {
   }

   public boolean isDisabled(int var1) {
      return true;
   }

   public Logger getLogger(String var1) {
      return new NOPLogger(this, var1);
   }

   public void addHierarchyEventListener(HierarchyEventListener var1) {
   }

   public void fireAddAppenderEvent(Category var1, Appender var2) {
   }

   public void shutdown() {
   }

   public Enumeration getCurrentCategories() {
      return this.getCurrentLoggers();
   }

   public Logger getRootLogger() {
      return new NOPLogger(this, "root");
   }

   public Level getThreshold() {
      return Level.OFF;
   }

   public void resetConfiguration() {
   }
}
