package org.apache.log4j.helpers;

import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class SyslogQuietWriter extends QuietWriter {
   public int syslogFacility;
   public int level;

   public void write(String var1) {
      super.write("<" + (this.syslogFacility | this.level) + ">" + var1);
   }

   public void setSyslogFacility(int var1) {
      this.syslogFacility = var1;
   }

   public SyslogQuietWriter(Writer var1, int var2, ErrorHandler var3) {
      super(var1, var3);
      this.syslogFacility = var2;
   }

   public void setLevel(int var1) {
      this.level = var1;
   }
}
