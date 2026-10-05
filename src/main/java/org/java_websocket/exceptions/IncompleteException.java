package org.java_websocket.exceptions;

public class IncompleteException extends Exception {
   public int preferredSize;
   public static final long recoveredField909 = 7330519489840500997L;

   public int getPreferredSize() {
      return this.preferredSize;
   }

   public IncompleteException(int var1) {
      this.preferredSize = var1;
   }
}
