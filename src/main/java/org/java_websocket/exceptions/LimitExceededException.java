package org.java_websocket.exceptions;

public class LimitExceededException extends InvalidDataException {
   public int limit;
   public static final long recoveredField3325 = 6908339749836826785L;

   public int getLimit() {
      return this.limit;
   }

   public LimitExceededException(String var1, int var2) {
      super(1009, var1);
      this.limit = var2;
   }

   public LimitExceededException(int var1) {
      super(1009);
      this.limit = var1;
   }

   public LimitExceededException(String var1) {
      this(var1, Integer.MAX_VALUE);
   }

   public LimitExceededException() {
      this(Integer.MAX_VALUE);
   }
}
