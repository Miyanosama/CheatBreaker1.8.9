package org.apache.log4j.lf5.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.apache.log4j.lf5.Log4JLogRecord;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.apache.log4j.lf5.viewer.LogFactor5LoadingDialog;

public class LogFileParser implements Runnable {
   public static final String recoveredField2047 = "[slf5s.THREAD]";
   public static final String recoveredField2048 = "[slf5s.MESSAGE]";
   public static final String recoveredField2049 = "[slf5s.LOCATION]";
   public static final String recoveredField2050 = "[slf5s.CATEGORY]";
   public static final String recoveredField2051 = "[slf5s.start]";
   public static final String recoveredField2052 = "[slf5s.DATE]";
   public static final String recoveredField2053 = "[slf5s.PRIORITY]";
   public LogBrokerMonitor _monitor;
   public static final String recoveredField2054 = "[slf5s.NDC]";
   public InputStream _in = null;
   public static final String recoveredField2055 = "[slf5s.";
   public LogFactor5LoadingDialog _loadDialog;
   public static SimpleDateFormat _sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,S");

   public String parseCategory(String var1) {
      return this.parseAttribute("[slf5s.CATEGORY]", var1);
   }

   public LogRecord createLogRecord(String var1) {
      if (var1 != null && var1.trim().length() != 0) {
         Log4JLogRecord var2 = new Log4JLogRecord();
         var2.setMillis(this.parseDate(var1));
         var2.setLevel(this.parsePriority(var1));
         var2.setCategory(this.parseCategory(var1));
         var2.setLocation(this.parseLocation(var1));
         var2.setThreadDescription(this.parseThread(var1));
         var2.setNDC(this.parseNDC(var1));
         var2.setMessage(this.parseMessage(var1));
         var2.setThrownStackTrace(this.parseThrowable(var1));
         return var2;
      } else {
         return null;
      }
   }

   public String getAttribute(int var1, String var2) {
      int var3 = var2.lastIndexOf("[slf5s.", var1 - 1);
      if (var3 == -1) {
         return var2.substring(0, var1);
      } else {
         var3 = var2.indexOf("]", var3);
         return var2.substring(var3 + 1, var1).trim();
      }
   }

   public void parse(LogBrokerMonitor var1) throws java.lang.RuntimeException {
      this._monitor = var1;
      Thread var2 = new Thread(this);
      var2.start();
   }

   public void destroyDialog() {
      this._loadDialog.hide();
      this._loadDialog.dispose();
   }

   public void displayError(String var1) {
      new LogFactor5ErrorDialog(this._monitor.getBaseFrame(), var1);
   }

   public long parseDate(String var1) {
      try {
         String var2 = this.parseAttribute("[slf5s.DATE]", var1);
         if (var2 == null) {
            return 0L;
         } else {
            Date var3 = _sdf.parse(var2);
            return var3.getTime();
         }
      } catch (ParseException var4) {
         return 0L;
      }
   }

   public String parseLocation(String var1) {
      return this.parseAttribute("[slf5s.LOCATION]", var1);
   }

   public String parseNDC(String var1) {
      return this.parseAttribute("[slf5s.NDC]", var1);
   }

   public String parseThread(String var1) {
      return this.parseAttribute("[slf5s.THREAD]", var1);
   }

   public void run() {
      int var1 = 0;
      int var2 = 0;
      boolean var4 = false;
      this._loadDialog = new LogFactor5LoadingDialog(this._monitor.getBaseFrame(), "Loading file...");

      try {
         String var5 = this.loadLogFile(this._in);

         while ((var2 = var5.indexOf("[slf5s.start]", var1)) != -1) {
            LogRecord var3 = this.createLogRecord(var5.substring(var1, var2));
            var4 = true;
            if (var3 != null) {
               this._monitor.addMessage(var3);
            }

            var1 = var2 + "[slf5s.start]".length();
         }

         if (var1 < var5.length() && var4) {
            LogRecord var9 = this.createLogRecord(var5.substring(var1));
            if (var9 != null) {
               this._monitor.addMessage(var9);
            }
         }

         if (!var4) {
            throw new RuntimeException("Invalid log file format");
         }

         SwingUtilities.invokeLater(new LogFileParser$1(this));
      } catch (RuntimeException var6) {
         this.destroyDialog();
         this.displayError("Error - Invalid log file format.\nPlease see documentation on how to load log files.");
      } catch (IOException var7) {
         this.destroyDialog();
         this.displayError("Error - Unable to load log file!");
      }

      this._in = null;
   }

   public String loadLogFile(InputStream var1) throws java.io.IOException {
      BufferedInputStream var2 = new BufferedInputStream(var1);
      int var3 = 0;
      int var4 = var2.available();
      StringBuffer var5 = null;
      if (var4 > 0) {
         var5 = new StringBuffer(var4);
      } else {
         var5 = new StringBuffer(1024);
      }

      while ((var3 = var2.read()) != -1) {
         var5.append((char)var3);
      }

      var2.close();
      Object var6 = null;
      return var5.toString();
   }

   // $VF: synthetic method
   public static void access$000(LogFileParser var0) {
      var0.destroyDialog();
   }

   public String parseMessage(String var1) {
      return this.parseAttribute("[slf5s.MESSAGE]", var1);
   }

   public LogFileParser(File var1) throws java.io.IOException, java.io.FileNotFoundException {
      this(new FileInputStream(var1));
   }

   public String parseAttribute(String var1, String var2) {
      int var3 = var2.indexOf(var1);
      return var3 == -1 ? null : this.getAttribute(var3, var2);
   }

   public LogFileParser(InputStream var1) throws java.io.IOException {
      this._in = var1;
   }

   public LogLevel parsePriority(String var1) {
      String var2 = this.parseAttribute("[slf5s.PRIORITY]", var1);
      if (var2 != null) {
         try {
            return LogLevel.valueOf(var2);
         } catch (LogLevelFormatException var4) {
            return LogLevel.DEBUG;
         }
      } else {
         return LogLevel.DEBUG;
      }
   }

   public String parseThrowable(String var1) {
      return this.getAttribute(var1.length(), var1);
   }
}
