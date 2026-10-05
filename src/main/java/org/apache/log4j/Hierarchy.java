package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class Hierarchy implements LoggerRepository, ThrowableRendererSupport, RendererSupport {
   public Logger root;
   public ThrowableRenderer throwableRenderer;
   public RendererMap rendererMap;
   public boolean emittedNoResourceBundleWarning;
   public int thresholdInt;
   public LoggerFactory defaultFactory;
   public Hashtable ht;
   public Vector listeners;
   public Level threshold;
   public boolean emittedNoAppenderWarning = false;

   public void emitNoAppenderWarning(Category var1) {
      if (!this.emittedNoAppenderWarning) {
         LogLog.warn("No appenders could be found for logger (" + var1.getName() + ").");
         LogLog.warn("Please initialize the log4j system properly.");
         LogLog.warn("See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.");
         this.emittedNoAppenderWarning = true;
      }
   }

   public void overrideAsNeeded(String var1) {
      LogLog.warn("The Hiearchy.overrideAsNeeded method has been deprecated.");
   }

   public void updateParents(Logger var1) {
      String var2 = var1.name;
      int var3 = var2.length();
      boolean var4 = false;

      for (int var5 = var2.lastIndexOf(46, var3 - 1); var5 >= 0; var5 = var2.lastIndexOf(46, var5 - 1)) {
         String var6 = var2.substring(0, var5);
         CategoryKey var7 = new CategoryKey(var6);
         Object var8 = this.ht.get(var7);
         if (var8 == null) {
            ProvisionNode var9 = new ProvisionNode(var1);
            this.ht.put(var7, var9);
         } else {
            if (var8 instanceof Category) {
               var4 = true;
               var1.parent = (Category)var8;
               break;
            }

            if (var8 instanceof ProvisionNode) {
               ((ProvisionNode)var8).addElement(var1);
            } else {
               IllegalStateException var10 = new IllegalStateException("unexpected object type " + var8.getClass() + " in ht.");
               var10.printStackTrace();
            }
         }
      }

      if (!var4) {
         var1.parent = this.root;
      }
   }

   public void fireAddAppenderEvent(Category var1, Appender var2) {
      if (this.listeners != null) {
         int var3 = this.listeners.size();

         for (int var5 = 0; var5 < var3; var5++) {
            HierarchyEventListener var4 = (HierarchyEventListener)this.listeners.elementAt(var5);
            var4.addAppenderEvent(var1, var2);
         }
      }
   }

   public ThrowableRenderer getThrowableRenderer() {
      return this.throwableRenderer;
   }

   public void clear() {
      this.ht.clear();
   }

   public Enumeration getCurrentLoggers() {
      Vector var1 = new Vector(this.ht.size());
      Enumeration var2 = this.ht.elements();

      while (var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         if (var3 instanceof Logger) {
            var1.addElement(var3);
         }
      }

      return var1.elements();
   }

   public void setThrowableRenderer(ThrowableRenderer var1) {
      this.throwableRenderer = var1;
   }

   public Level getThreshold() {
      return this.threshold;
   }

   public void shutdown() {
      Logger var1 = this.getRootLogger();
      var1.closeNestedAppenders();
      synchronized (this.ht) {
         Enumeration var3 = this.getCurrentLoggers();

         while (var3.hasMoreElements()) {
            Logger var4 = (Logger)var3.nextElement();
            var4.closeNestedAppenders();
         }

         var1.removeAllAppenders();
         var3 = this.getCurrentLoggers();

         while (var3.hasMoreElements()) {
            Logger var8 = (Logger)var3.nextElement();
            var8.removeAllAppenders();
         }
      }
   }

   public Logger getLogger(String var1) {
      return this.getLogger(var1, this.defaultFactory);
   }

   public void method_28625(Class var1, ObjectRenderer var2) {
      this.rendererMap.put(var1, var2);
   }

   public void setRenderer(Class var1, ObjectRenderer var2) {
      this.rendererMap.put(var1, var2);
   }

   public Logger exists(String var1) {
      Object var2 = this.ht.get(new CategoryKey(var1));
      return var2 instanceof Logger ? (Logger)var2 : null;
   }

   public void addHierarchyEventListener(HierarchyEventListener var1) {
      if (this.listeners.contains(var1)) {
         LogLog.warn("Ignoring attempt to add an existent listener.");
      } else {
         this.listeners.addElement(var1);
      }
   }

   public void resetConfiguration() {
      this.getRootLogger().setLevel(Level.DEBUG);
      this.root.setResourceBundle(null);
      this.setThreshold(Level.ALL);
      synchronized (this.ht) {
         this.shutdown();
         Enumeration var2 = this.getCurrentLoggers();

         while (var2.hasMoreElements()) {
            Logger var3 = (Logger)var2.nextElement();
            var3.setLevel(null);
            var3.setAdditivity(true);
            var3.setResourceBundle(null);
         }
      }

      this.rendererMap.clear();
      this.throwableRenderer = null;
   }

   public Logger getRootLogger() {
      return this.root;
   }

   public void updateChildren(ProvisionNode var1, Logger var2) {
      int var3 = var1.size();

      for (int var4 = 0; var4 < var3; var4++) {
         Logger var5 = (Logger)var1.elementAt(var4);
         if (!var5.parent.name.startsWith(var2.name)) {
            var2.parent = var5.parent;
            var5.parent = var2;
         }
      }
   }

   public RendererMap getRendererMap() {
      return this.rendererMap;
   }

   public Logger getLogger(String var1, LoggerFactory var2) {
      CategoryKey var3 = new CategoryKey(var1);
      synchronized (this.ht) {
         Object var6 = this.ht.get(var3);
         if (var6 == null) {
            Logger var9 = var2.makeNewLoggerInstance(var1);
            var9.setHierarchy(this);
            this.ht.put(var3, var9);
            this.updateParents(var9);
            return var9;
         } else if (var6 instanceof Logger) {
            return (Logger)var6;
         } else if (var6 instanceof ProvisionNode) {
            Logger var4 = var2.makeNewLoggerInstance(var1);
            var4.setHierarchy(this);
            this.ht.put(var3, var4);
            this.updateChildren((ProvisionNode)var6, var4);
            this.updateParents(var4);
            return var4;
         } else {
            return null;
         }
      }
   }

   public Enumeration getCurrentCategories() {
      return this.getCurrentLoggers();
   }

   public boolean isDisabled(int var1) {
      return this.thresholdInt > var1;
   }

   public void setThreshold(String var1) {
      Level var2 = Level.toLevel(var1, null);
      if (var2 != null) {
         this.setThreshold(var2);
      } else {
         LogLog.warn("Could not convert [" + var1 + "] to Level.");
      }
   }

   public void fireRemoveAppenderEvent(Category var1, Appender var2) {
      if (this.listeners != null) {
         int var3 = this.listeners.size();

         for (int var5 = 0; var5 < var3; var5++) {
            HierarchyEventListener var4 = (HierarchyEventListener)this.listeners.elementAt(var5);
            var4.removeAppenderEvent(var1, var2);
         }
      }
   }

   public Hierarchy(Logger var1) {
      this.emittedNoResourceBundleWarning = false;
      this.throwableRenderer = null;
      this.ht = new Hashtable();
      this.listeners = new Vector(1);
      this.root = var1;
      this.setThreshold(Level.ALL);
      this.root.setHierarchy(this);
      this.rendererMap = new RendererMap();
      this.defaultFactory = new DefaultCategoryFactory();
   }

   public void setThreshold(Level var1) {
      if (var1 != null) {
         this.thresholdInt = var1.level;
         this.threshold = var1;
      }
   }

   public void setDisableOverride(String var1) {
      LogLog.warn("The Hiearchy.setDisableOverride method has been deprecated.");
   }
}
