package org.apache.log4j.lf5;

import org.apache.log4j.spi.ThrowableInformation;

public class Log4JLogRecord extends LogRecord {
   public void setThrownStackTrace(ThrowableInformation var1) {
      String[] var2 = var1.getThrowableStrRep();
      StringBuffer var3 = new StringBuffer();

      for (int var5 = 0; var5 < var2.length; var5++) {
         String var4 = var2[var5] + "\n";
         var3.append(var4);
      }

      this._thrownStackTrace = var3.toString();
   }

   public boolean isSevereLevel() {
      boolean var1 = false;
      if (LogLevel.ERROR.equals(this.getLevel()) || LogLevel.FATAL.equals(this.getLevel())) {
         var1 = true;
      }

      return var1;
   }
}
