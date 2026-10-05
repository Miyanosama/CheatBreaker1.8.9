package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;

public class SimpleLayout extends Layout {
   public StringBuffer sbuf = new StringBuffer(128);

   public void activateOptions() {
   }

   public String format(LoggingEvent var1) {
      this.sbuf.setLength(0);
      this.sbuf.append(var1.getLevel().toString());
      this.sbuf.append(" - ");
      this.sbuf.append(var1.getRenderedMessage());
      this.sbuf.append(LINE_SEP);
      return this.sbuf.toString();
   }

   public boolean ignoresThrowable() {
      return true;
   }
}
