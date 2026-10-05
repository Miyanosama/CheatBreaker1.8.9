package org.apache.log4j;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class Category implements AppenderAttachable {
   public volatile Category parent;
   public String name;
   public ResourceBundle resourceBundle;
   public static Class class$org$apache$log4j$Category;
   public AppenderAttachableImpl aai;
   public LoggerRepository repository;
   public static String FQCN = (class$org$apache$log4j$Category == null
         ? (class$org$apache$log4j$Category = class$("org.apache.log4j.Category"))
         : class$org$apache$log4j$Category)
      .getName();
   public boolean additive = true;
   public volatile Level level;

   public void callAppenders(LoggingEvent var1) {
      int var2 = 0;

      for (Category var3 = this; var3 != null; var3 = var3.parent) {
         synchronized (var3) {
            if (var3.aai != null) {
               var2 += var3.aai.appendLoopOnAppenders(var1);
            }

            if (!var3.additive) {
               break;
            }
         }
      }

      if (var2 == 0) {
         this.repository.emitNoAppenderWarning(this);
      }
   }

   public synchronized Appender getAppender(String var1) {
      return this.aai != null && var1 != null ? this.aai.getAppender(var1) : null;
   }

   public void fatal(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(50000)) {
         if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.FATAL, var1, var2);
         }
      }
   }

   public void setAdditivity(boolean var1) {
      this.additive = var1;
   }

   public static void shutdown() {
      LogManager.shutdown();
   }

   public void info(Object var1) {
      if (!this.repository.isDisabled(20000)) {
         if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.INFO, var1, null);
         }
      }
   }

   public void log(String var1, Priority var2, Object var3, Throwable var4) {
      if (!this.repository.isDisabled(var2.level)) {
         if (var2.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(var1, var2, var3, var4);
         }
      }
   }

   public boolean isEnabledFor(Priority var1) {
      return this.repository.isDisabled(var1.level) ? false : var1.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public static Category getRoot() {
      return LogManager.getRootLogger();
   }

   public void debug(Object var1) {
      if (!this.repository.isDisabled(10000)) {
         if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.DEBUG, var1, null);
         }
      }
   }

   public Level getLevel() {
      return this.level;
   }

   public String getResourceBundleString(String var1) {
      ResourceBundle var2 = this.getResourceBundle();
      if (var2 == null) {
         return null;
      } else {
         try {
            return var2.getString(var1);
         } catch (MissingResourceException var4) {
            this.error("No resource is associated with key \"" + var1 + "\".");
            return null;
         }
      }
   }

   public void log(Priority var1, Object var2, Throwable var3) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, var1, var2, var3);
         }
      }
   }

   public boolean isInfoEnabled() {
      return this.repository.isDisabled(20000) ? false : Level.INFO.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public void warn(Object var1) {
      if (!this.repository.isDisabled(30000)) {
         if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.WARN, var1, null);
         }
      }
   }

   public boolean isDebugEnabled() {
      return this.repository.isDisabled(10000) ? false : Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public void forcedLog(String var1, Priority var2, Object var3, Throwable var4) {
      this.callAppenders(new LoggingEvent(var1, this, var2, var3, var4));
   }

   public static Logger exists(String var0) {
      return LogManager.exists(var0);
   }

   public void debug(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(10000)) {
         if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.DEBUG, var1, var2);
         }
      }
   }

   public LoggerRepository method_08227() {
      return this.repository;
   }

   public void assertLog(boolean var1, String var2) {
      if (!var1) {
         this.error(var2);
      }
   }

   public synchronized void removeAppender(String var1) {
      if (var1 != null && this.aai != null) {
         Appender var2 = this.aai.getAppender(var1);
         this.aai.removeAppender(var1);
         if (var2 != null) {
            this.fireRemoveAppenderEvent(var2);
         }
      }
   }

   public void warn(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(30000)) {
         if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.WARN, var1, var2);
         }
      }
   }

   public String getName() {
      return this.name;
   }

   public ResourceBundle getResourceBundle() {
      for (Category var1 = this; var1 != null; var1 = var1.parent) {
         if (var1.resourceBundle != null) {
            return var1.resourceBundle;
         }
      }

      return null;
   }

   public boolean getAdditivity() {
      return this.additive;
   }

   public synchronized void removeAllAppenders() {
      if (this.aai != null) {
         Vector var1 = new Vector();
         Enumeration var2 = this.aai.getAllAppenders();

         while (var2 != null && var2.hasMoreElements()) {
            var1.add(var2.nextElement());
         }

         this.aai.removeAllAppenders();
         var2 = var1.elements();

         while (var2.hasMoreElements()) {
            this.fireRemoveAppenderEvent((Appender)var2.nextElement());
         }

         this.aai = null;
      }
   }

   public synchronized void addAppender(Appender var1) {
      if (this.aai == null) {
         this.aai = new AppenderAttachableImpl();
      }

      this.aai.addAppender(var1);
      this.repository.fireAddAppenderEvent(this, var1);
   }

   public synchronized Enumeration getAllAppenders() {
      return (Enumeration)(this.aai == null ? NullEnumeration.getInstance() : this.aai.getAllAppenders());
   }

   public Category(String var1) {
      this.name = var1;
   }

   public Priority getChainedPriority() {
      for (Category var1 = this; var1 != null; var1 = var1.parent) {
         if (var1.level != null) {
            return var1.level;
         }
      }

      return null;
   }

   public Category getParent() {
      return this.parent;
   }

   public Level getEffectiveLevel() {
      for (Category var1 = this; var1 != null; var1 = var1.parent) {
         if (var1.level != null) {
            return var1.level;
         }
      }

      return null;
   }

   public void error(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(40000)) {
         if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.ERROR, var1, var2);
         }
      }
   }

   public Level method_08259() {
      return this.level;
   }

   public boolean isAttached(Appender var1) {
      return var1 != null && this.aai != null ? this.aai.isAttached(var1) : false;
   }

   public void setLevel(Level var1) {
      this.level = var1;
   }

   public void fatal(Object var1) {
      if (!this.repository.isDisabled(50000)) {
         if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.FATAL, var1, null);
         }
      }
   }

   public static LoggerRepository getDefaultHierarchy() {
      return LogManager.getLoggerRepository();
   }

   public static Enumeration getCurrentCategories() {
      return LogManager.getCurrentLoggers();
   }

   public synchronized void removeAppender(Appender var1) {
      if (var1 != null && this.aai != null) {
         boolean var2 = this.aai.isAttached(var1);
         this.aai.removeAppender(var1);
         if (var2) {
            this.fireRemoveAppenderEvent(var1);
         }
      }
   }

   public void setPriority(Priority var1) {
      this.level = (Level)var1;
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static Category getInstance(Class var0) {
      return LogManager.getLogger(var0);
   }

   public void setHierarchy(LoggerRepository var1) {
      this.repository = var1;
   }

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }

   public void log(Priority var1, Object var2) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, var1, var2, null);
         }
      }
   }

   public void fireRemoveAppenderEvent(Appender var1) {
      if (var1 != null) {
         if (this.repository instanceof Hierarchy) {
            ((Hierarchy)this.repository).fireRemoveAppenderEvent(this, var1);
         } else if (this.repository instanceof HierarchyEventListener) {
            ((HierarchyEventListener)this.repository).removeAppenderEvent(this, var1);
         }
      }
   }

   public void error(Object var1) {
      if (!this.repository.isDisabled(40000)) {
         if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.ERROR, var1, null);
         }
      }
   }

   public void l7dlog(Priority var1, String var2, Object[] var3, Throwable var4) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            String var5 = this.getResourceBundleString(var2);
            String var6;
            if (var5 == null) {
               var6 = var2;
            } else {
               var6 = MessageFormat.format(var5, var3);
            }

            this.forcedLog(FQCN, var1, var6, var4);
         }
      }
   }

   public void setResourceBundle(ResourceBundle var1) {
      this.resourceBundle = var1;
   }

   public void l7dlog(Priority var1, String var2, Throwable var3) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            String var4 = this.getResourceBundleString(var2);
            if (var4 == null) {
               var4 = var2;
            }

            this.forcedLog(FQCN, var1, var4, var3);
         }
      }
   }

   public static Category getInstance(String var0) {
      return LogManager.getLogger(var0);
   }

   public void info(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(20000)) {
         if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.INFO, var1, var2);
         }
      }
   }

   public synchronized void closeNestedAppenders() {
      Enumeration var1 = this.getAllAppenders();
      if (var1 != null) {
         while (var1.hasMoreElements()) {
            Appender var2 = (Appender)var1.nextElement();
            if (var2 instanceof AppenderAttachable) {
               var2.close();
            }
         }
      }
   }
}
