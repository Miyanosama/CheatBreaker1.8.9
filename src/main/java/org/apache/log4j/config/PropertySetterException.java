package org.apache.log4j.config;

public class PropertySetterException extends Exception {
   public static final long recoveredField1925 = -1352613734254235861L;
   public Throwable rootCause;

   public PropertySetterException(String var1) {
      super(var1);
   }

   public String getMessage() {
      String var1 = super.getMessage();
      if (var1 == null && this.rootCause != null) {
         var1 = this.rootCause.getMessage();
      }

      return var1;
   }

   public PropertySetterException(Throwable var1) {
      this.rootCause = var1;
   }
}
