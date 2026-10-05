package org.apache.log4j.or;

import org.apache.log4j.Layout;

public class ThreadGroupRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      if (!(var1 instanceof ThreadGroup)) {
         try {
            return var1.toString();
         } catch (Exception var6) {
            return var6.toString();
         }
      } else {
         StringBuffer var2 = new StringBuffer();
         ThreadGroup var3 = (ThreadGroup)var1;
         var2.append("java.lang.ThreadGroup[name=");
         var2.append(var3.getName());
         var2.append(", maxpri=");
         var2.append(var3.getMaxPriority());
         var2.append("]");
         Thread[] var4 = new Thread[var3.activeCount()];
         var3.enumerate(var4);

         for (int var5 = 0; var5 < var4.length; var5++) {
            var2.append(Layout.LINE_SEP);
            var2.append("   Thread=[");
            var2.append(var4[var5].getName());
            var2.append(",");
            var2.append(var4[var5].getPriority());
            var2.append(",");
            var2.append(var4[var5].isDaemon());
            var2.append("]");
         }

         return var2.toString();
      }
   }
}
