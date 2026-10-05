package org.java_websocket.exceptions;

public class InvalidFrameException extends InvalidDataException {
   public static long field_0000;

   public InvalidFrameException() {
      super(1002);
   }

   public InvalidFrameException(Throwable var1) {
      super(1002, var1);
   }

   public InvalidFrameException(String var1) {
      super(1002, var1);
   }

   public InvalidFrameException(String var1, Throwable var2) {
      super(1002, var1, var2);
   }
}
