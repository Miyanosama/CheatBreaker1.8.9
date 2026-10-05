package org.apache.log4j.chainsaw;

import java.util.Comparator;

public class MyTableModel$1 implements Comparator {
   public int compare(Object var1, Object var2) {
      if (var1 == null && var2 == null) {
         return 0;
      } else if (var1 == null) {
         return -1;
      } else if (var2 == null) {
         return 1;
      } else {
         EventDetails var3 = (EventDetails)var1;
         EventDetails var4 = (EventDetails)var2;
         return var3.getTimeStamp() < var4.getTimeStamp() ? 1 : -1;
      }
   }
}
