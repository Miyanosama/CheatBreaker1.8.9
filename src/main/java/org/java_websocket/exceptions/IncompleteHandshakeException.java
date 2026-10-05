package org.java_websocket.exceptions;

public class IncompleteHandshakeException extends RuntimeException {
   public int preferredSize;
   public static final long recoveredField1916 = 7906596804233893092L;

   public int getPreferredSize() {
      return this.preferredSize;
   }

   public IncompleteHandshakeException(int var1) {
      this.preferredSize = var1;
   }

   public IncompleteHandshakeException() {
      this.preferredSize = 0;
   }
}
