package org.apache.log4j.spi;

import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public interface LoggerRepository {
   void fireAddAppenderEvent(Category var1, Appender var2);

   boolean isDisabled(int var1);

   void resetConfiguration();

   void addHierarchyEventListener(HierarchyEventListener var1);

   Logger getLogger(String var1, LoggerFactory var2);

   Logger getLogger(String var1);

   Logger exists(String var1);

   Logger getRootLogger();

   Enumeration getCurrentCategories();

   void setThreshold(String var1);

   Enumeration getCurrentLoggers();

   Level getThreshold();

   void shutdown();

   void setThreshold(Level var1);

   void emitNoAppenderWarning(Category var1);
}
