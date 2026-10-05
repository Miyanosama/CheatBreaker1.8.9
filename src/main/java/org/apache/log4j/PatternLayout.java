package org.apache.log4j;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class PatternLayout extends Layout {
   public StringBuffer sbuf;
   public String pattern;
   public int BUF_SIZE = 256;
   public PatternConverter head;
   public static final String recoveredField1432 = "%m%n";
   public static final String recoveredField1433 = "%r [%t] %p %c %x - %m%n";
   public int MAX_CAPACITY = 1024;

   public String format(LoggingEvent var1) {
      if (this.sbuf.capacity() > 1024) {
         this.sbuf = new StringBuffer(256);
      } else {
         this.sbuf.setLength(0);
      }

      for (PatternConverter var2 = this.head; var2 != null; var2 = var2.next) {
         var2.format(this.sbuf, var1);
      }

      return this.sbuf.toString();
   }

   public PatternLayout(String var1) {
      this.sbuf = new StringBuffer(256);
      this.pattern = var1;
      this.head = this.createPatternParser(var1 == null ? "%m%n" : var1).parse();
   }

   public boolean ignoresThrowable() {
      return true;
   }

   public PatternLayout() {
      this("%m%n");
   }

   public void activateOptions() {
   }

   public PatternParser createPatternParser(String var1) {
      return new PatternParser(var1);
   }

   public String getConversionPattern() {
      return this.pattern;
   }

   public void setConversionPattern(String var1) {
      this.pattern = var1;
      this.head = this.createPatternParser(var1).parse();
   }
}
