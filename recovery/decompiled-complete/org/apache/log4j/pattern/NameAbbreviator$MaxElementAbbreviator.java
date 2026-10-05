package org.apache.log4j.pattern;

import io.netty.channel.sctp.SctpMessage;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$21;

public class NameAbbreviator$MaxElementAbbreviator extends NameAbbreviator {
   public SctpMessage field_0002;
   public int count;
   public LogBrokerMonitor$21 field_0000;

   public NameAbbreviator$MaxElementAbbreviator(int var1) {
      this.count = var1;
   }

   public void abbreviate(int var1, StringBuffer var2) {
      int var3 = var2.length() - 1;
      String var4 = var2.toString();

      for (int var5 = this.count; var5 > 0; var5--) {
         var3 = var4.lastIndexOf(".", var3 - 1);
         if (var3 == -1 || var3 < var1) {
            return;
         }
      }

      var2.delete(var1, var3 + 1);
   }
}
