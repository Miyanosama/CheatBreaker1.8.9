package org.apache.log4j.spi;

public abstract class Filter implements OptionHandler {
   public static final int recoveredField3859 = 0;
   public static final int recoveredField3860 = -1;
   public Filter next;
   public static final int recoveredField3861 = 1;

   public void activateOptions() {
   }

   public void setNext(Filter var1) {
      this.next = var1;
   }

   public abstract int decide(LoggingEvent var1);

   public Filter getNext() {
      return this.next;
   }
}
