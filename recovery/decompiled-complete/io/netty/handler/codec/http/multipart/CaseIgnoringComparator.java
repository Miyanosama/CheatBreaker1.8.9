package io.netty.handler.codec.http.multipart;

import io.netty.channel.udt.UdtMessage;
import io.netty.handler.timeout.WriteTimeoutHandler$1;
import io.netty.util.internal.chmv8.ForkJoinPool$WorkQueue;
import java.io.Serializable;
import java.util.Comparator;

public class CaseIgnoringComparator implements Serializable, Comparator<CharSequence> {
   public WriteTimeoutHandler$1 __junk2771074304248453421;
   public ForkJoinPool$WorkQueue __junk3343506821989544849;
   public static long serialVersionUID;
   public UdtMessage __junk5987554991202339772;
   public static CaseIgnoringComparator INSTANCE = new CaseIgnoringComparator();

   public Object readResolve() {
      return INSTANCE;
   }

   public int compare(CharSequence var1, CharSequence var2) {
      int var3 = var1.length();
      int var4 = var2.length();
      int var5 = Math.min(var3, var4);

      for (int var6 = 0; var6 < var5; var6++) {
         char var7 = var1.charAt(var6);
         char var8 = var2.charAt(var6);
         if (var7 != var8) {
            var7 = Character.toUpperCase(var7);
            var8 = Character.toUpperCase(var8);
            if (var7 != var8) {
               var7 = Character.toLowerCase(var7);
               var8 = Character.toLowerCase(var8);
               if (var7 != var8) {
                  return var7 - var8;
               }
            }
         }
      }

      return var3 - var4;
   }
}
