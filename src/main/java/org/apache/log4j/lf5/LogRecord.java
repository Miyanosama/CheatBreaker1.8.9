package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public abstract class LogRecord implements Serializable {
   public Throwable _thrown;
   public String _category;
   public String _ndc;
   public String _location;
   public String _message;
   public long _sequenceNumber;
   public String _thread;
   public LogLevel _level;
   public long _millis = System.currentTimeMillis();
   public static long _seqCount = 0L;
   public String _thrownStackTrace;

   public abstract boolean isSevereLevel();

   public static synchronized void resetSequenceNumber() {
      _seqCount = 0L;
   }

   public void setSequenceNumber(long var1) {
      this._sequenceNumber = var1;
   }

   public String getThrownStackTrace() {
      return this._thrownStackTrace;
   }

   public static synchronized long getNextId() {
      _seqCount++;
      return _seqCount;
   }

   public void setThrown(Throwable var1) {
      if (var1 != null) {
         this._thrown = var1;
         StringWriter var2 = new StringWriter();
         PrintWriter var3 = new PrintWriter(var2);
         var1.printStackTrace(var3);
         var3.flush();
         this._thrownStackTrace = var2.toString();

         try {
            var3.close();
            var2.close();
         } catch (IOException var5) {
         }

         Object var7 = null;
         Object var6 = null;
      }
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append("LogRecord: [" + this._level + ", " + this._message + "]");
      return var1.toString();
   }

   public String getThreadDescription() {
      return this._thread;
   }

   public void setMillis(long var1) {
      this._millis = var1;
   }

   public String getMessage() {
      return this._message;
   }

   public void setThreadDescription(String var1) {
      this._thread = var1;
   }

   public String getLocation() {
      return this._location;
   }

   public LogLevel getLevel() {
      return this._level;
   }

   public String getCategory() {
      return this._category;
   }

   public void setThrownStackTrace(String var1) {
      this._thrownStackTrace = var1;
   }

   public Throwable getThrown() {
      return this._thrown;
   }

   public void setLevel(LogLevel var1) {
      this._level = var1;
   }

   public String getNDC() {
      return this._ndc;
   }

   public void setCategory(String var1) {
      this._category = var1;
   }

   public void setLocation(String var1) {
      this._location = var1;
   }

   public boolean isFatal() {
      return this.isSevereLevel() || this.hasThrown();
   }

   public long getSequenceNumber() {
      return this._sequenceNumber;
   }

   public void setMessage(String var1) {
      this._message = var1;
   }

   public long getMillis() {
      return this._millis;
   }

   public void setNDC(String var1) {
      this._ndc = var1;
   }

   public boolean hasThrown() {
      Throwable var1 = this.getThrown();
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.toString();
         return var2 != null && var2.trim().length() != 0;
      }
   }

   public LogRecord() {
      this._category = "Debug";
      this._message = "";
      this._level = LogLevel.INFO;
      this._sequenceNumber = getNextId();
      this._thread = Thread.currentThread().toString();
      this._ndc = "";
      this._location = "";
   }
}
