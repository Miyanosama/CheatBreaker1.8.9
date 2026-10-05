package org.json;

public class JSONException extends RuntimeException {
   public static final long recoveredField3000 = 0L;

   public JSONException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public JSONException(String var1) {
      super(var1);
   }

   public JSONException(Throwable var1) {
      super(var1.getMessage(), var1);
   }
}
