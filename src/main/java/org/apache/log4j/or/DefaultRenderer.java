package org.apache.log4j.or;

public class DefaultRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      try {
         return var1.toString();
      } catch (Exception var3) {
         return var3.toString();
      }
   }
}
