package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class CountingQuietWriter extends QuietWriter {
   public long count;

   public void write(String var1) {
      try {
         this.out.write(var1);
         this.count = this.count + var1.length();
      } catch (IOException var3) {
         this.errorHandler.error("Write failure.", var3, 1);
      }
   }

   public long getCount() {
      return this.count;
   }

   public CountingQuietWriter(Writer var1, ErrorHandler var2) {
      super(var1, var2);
   }

   public void setCount(long var1) {
      this.count = var1;
   }
}
