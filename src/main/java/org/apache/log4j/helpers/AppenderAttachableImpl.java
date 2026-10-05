package org.apache.log4j.helpers;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AppenderAttachableImpl implements AppenderAttachable {
   public Vector appenderList;

   public void removeAppender(Appender var1) {
      if (var1 != null && this.appenderList != null) {
         this.appenderList.removeElement(var1);
      }
   }

   public boolean isAttached(Appender var1) {
      if (this.appenderList != null && var1 != null) {
         int var2 = this.appenderList.size();

         for (int var4 = 0; var4 < var2; var4++) {
            Appender var3 = (Appender)this.appenderList.elementAt(var4);
            if (var3 == var1) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void removeAllAppenders() {
      if (this.appenderList != null) {
         int var1 = this.appenderList.size();

         for (int var2 = 0; var2 < var1; var2++) {
            Appender var3 = (Appender)this.appenderList.elementAt(var2);
            var3.close();
         }

         this.appenderList.removeAllElements();
         this.appenderList = null;
      }
   }

   public int appendLoopOnAppenders(LoggingEvent var1) {
      int var2 = 0;
      if (this.appenderList != null) {
         var2 = this.appenderList.size();

         for (int var4 = 0; var4 < var2; var4++) {
            Appender var3 = (Appender)this.appenderList.elementAt(var4);
            var3.doAppend(var1);
         }
      }

      return var2;
   }

   public Enumeration getAllAppenders() {
      return this.appenderList == null ? null : this.appenderList.elements();
   }

   public void addAppender(Appender var1) {
      if (var1 != null) {
         if (this.appenderList == null) {
            this.appenderList = new Vector(1);
         }

         if (!this.appenderList.contains(var1)) {
            this.appenderList.addElement(var1);
         }
      }
   }

   public void removeAppender(String var1) {
      if (var1 != null && this.appenderList != null) {
         int var2 = this.appenderList.size();

         for (int var3 = 0; var3 < var2; var3++) {
            if (var1.equals(((Appender)this.appenderList.elementAt(var3)).getName())) {
               this.appenderList.removeElementAt(var3);
               break;
            }
         }
      }
   }

   public Appender getAppender(String var1) {
      if (this.appenderList != null && var1 != null) {
         int var2 = this.appenderList.size();

         for (int var4 = 0; var4 < var2; var4++) {
            Appender var3 = (Appender)this.appenderList.elementAt(var4);
            if (var1.equals(var3.getName())) {
               return var3;
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
