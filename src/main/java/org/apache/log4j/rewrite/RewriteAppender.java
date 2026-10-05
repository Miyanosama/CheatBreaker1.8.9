package org.apache.log4j.rewrite;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.w3c.dom.Element;

public class RewriteAppender extends AppenderSkeleton implements UnrecognizedElementHandler, AppenderAttachable {
   public AppenderAttachableImpl appenders = new AppenderAttachableImpl();
   public static Class class$org$apache$log4j$rewrite$RewritePolicy;
   public RewritePolicy policy;

   public void close() {
      this.closed = true;
      synchronized (this.appenders) {
         Enumeration var2 = this.appenders.getAllAppenders();
         if (var2 != null) {
            while (var2.hasMoreElements()) {
               Object var3 = var2.nextElement();
               if (var3 instanceof Appender) {
                  ((Appender)var3).close();
               }
            }
         }
      }
   }

   public void removeAppender(String var1) {
      synchronized (this.appenders) {
         this.appenders.removeAppender(var1);
      }
   }

   public void removeAppender(Appender var1) {
      synchronized (this.appenders) {
         this.appenders.removeAppender(var1);
      }
   }

   public boolean parseUnrecognizedElement(Element var1, Properties var2) throws java.lang.Exception {
      String var3 = var1.getNodeName();
      if ("rewritePolicy".equals(var3)) {
         Object var4 = DOMConfigurator.parseElement(
            var1,
            var2,
            class$org$apache$log4j$rewrite$RewritePolicy == null
               ? (class$org$apache$log4j$rewrite$RewritePolicy = class$("org.apache.log4j.rewrite.RewritePolicy"))
               : class$org$apache$log4j$rewrite$RewritePolicy
         );
         if (var4 != null) {
            if (var4 instanceof OptionHandler) {
               ((OptionHandler)var4).activateOptions();
            }

            this.setRewritePolicy((RewritePolicy)var4);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isAttached(Appender var1) {
      synchronized (this.appenders) {
         return this.appenders.isAttached(var1);
      }
   }

   public Enumeration getAllAppenders() {
      synchronized (this.appenders) {
         return this.appenders.getAllAppenders();
      }
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public void addAppender(Appender var1) {
      synchronized (this.appenders) {
         this.appenders.addAppender(var1);
      }
   }

   public void setRewritePolicy(RewritePolicy var1) {
      this.policy = var1;
   }

   public void append(LoggingEvent var1) {
      LoggingEvent var2 = var1;
      if (this.policy != null) {
         var2 = this.policy.rewrite(var1);
      }

      if (var2 != null) {
         synchronized (this.appenders) {
            this.appenders.appendLoopOnAppenders(var2);
         }
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public Appender getAppender(String var1) {
      synchronized (this.appenders) {
         return this.appenders.getAppender(var1);
      }
   }

   public void removeAllAppenders() {
      synchronized (this.appenders) {
         this.appenders.removeAllAppenders();
      }
   }
}
