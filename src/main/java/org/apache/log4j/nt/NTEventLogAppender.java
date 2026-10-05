package org.apache.log4j.nt;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class NTEventLogAppender extends AppenderSkeleton {
   public int _handle = 0;
   public String server;
   public String source = null;

   public native void deregisterEventSource(int var1);

   public NTEventLogAppender(String var1, String var2, Layout var3) {
      this.server = null;
      if (var2 == null) {
         var2 = "Log4j";
      }

      if (var3 == null) {
         this.layout = new TTCCLayout();
      } else {
         this.layout = var3;
      }

      try {
         this._handle = this.registerEventSource(var1, var2);
      } catch (Exception var5) {
         var5.printStackTrace();
         this._handle = 0;
      }
   }

   public boolean requiresLayout() {
      return true;
   }

   public NTEventLogAppender(String var1, String var2) {
      this(var1, var2, null);
   }

   public void close() {
   }

   public String getSource() {
      return this.source;
   }

   public native int registerEventSource(String var1, String var2);

   public native void reportEvent(int var1, String var2, int var3);

   public NTEventLogAppender(String var1) {
      this(null, var1, null);
   }

   public void finalize() {
      this.deregisterEventSource(this._handle);
      this._handle = 0;
   }

   public NTEventLogAppender() {
      this(null, null, null);
   }

   public void activateOptions() {
      if (this.source != null) {
         try {
            this._handle = this.registerEventSource(this.server, this.source);
         } catch (Exception var2) {
            LogLog.error("Could not register event source.", var2);
            this._handle = 0;
         }
      }
   }

   public NTEventLogAppender(String var1, Layout var2) {
      this(null, var1, var2);
   }

   public void append(LoggingEvent var1) {
      StringBuffer var2 = new StringBuffer();
      var2.append(this.layout.format(var1));
      if (this.layout.ignoresThrowable()) {
         String[] var3 = var1.getThrowableStrRep();
         if (var3 != null) {
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; var5++) {
               var2.append(var3[var5]);
            }
         }
      }

      int var6 = var1.getLevel().toInt();
      this.reportEvent(this._handle, var2.toString(), var6);
   }

   static {
      String[] var0;
      try {
         var0 = new String[]{System.getProperty("os.arch")};
      } catch (SecurityException var4) {
         var0 = new String[]{"amd64", "ia64", "x86"};
      }

      boolean var1 = false;

      for (int var2 = 0; var2 < var0.length; var2++) {
         try {
            System.loadLibrary("NTEventLogAppender." + var0[var2]);
            var1 = true;
            break;
         } catch (UnsatisfiedLinkError var5) {
            var1 = false;
         }
      }

      if (!var1) {
         System.loadLibrary("NTEventLogAppender");
      }
   }

   public void setSource(String var1) {
      this.source = var1.trim();
   }

   public NTEventLogAppender(Layout var1) {
      this(null, null, var1);
   }
}
