package org.java_websocket.exceptions;

import io.netty.handler.stream.ChunkedWriteHandler$2;
import junit.swingui.TestSuitePanel;

public class InvalidDataException extends Exception {
   public TestSuitePanel field_0001;
   public static long field_0003;
   public int closecode;
   public ChunkedWriteHandler$2 field_0002;

   public InvalidDataException(int var1, String var2, Throwable var3) {
      super(var2, var3);
      this.closecode = var1;
   }

   public InvalidDataException(int var1, Throwable var2) {
      super(var2);
      this.closecode = var1;
   }

   public InvalidDataException(int var1, String var2) {
      super(var2);
      this.closecode = var1;
   }

   public int getCloseCode() {
      return this.closecode;
   }

   public InvalidDataException(int var1) {
      this.closecode = var1;
   }
}
