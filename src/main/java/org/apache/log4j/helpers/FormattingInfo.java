package org.apache.log4j.helpers;

public class FormattingInfo {
   public int min = -1;
   public boolean leftAlign;
   public int max = Integer.MAX_VALUE;

   public FormattingInfo() {
      this.leftAlign = false;
   }

   public void reset() {
      this.min = -1;
      this.max = Integer.MAX_VALUE;
      this.leftAlign = false;
   }

   public void dump() {
      LogLog.debug("min=" + this.min + ", max=" + this.max + ", leftAlign=" + this.leftAlign);
   }
}
