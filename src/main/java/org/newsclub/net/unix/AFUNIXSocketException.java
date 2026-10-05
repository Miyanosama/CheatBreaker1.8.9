package org.newsclub.net.unix;

import java.net.SocketException;

public class AFUNIXSocketException extends SocketException {
   public String recoveredField1237;
   public static final long recoveredField1238 = 1L;

   public AFUNIXSocketException(String var1) {
      this(var1, (String)null);
   }

   public AFUNIXSocketException(String var1, String var2) {
      super(var1);
      this.recoveredField1237 = var2;
   }

   public AFUNIXSocketException(String var1, Throwable var2) {
      this(var1, (String)null);
      this.initCause(var2);
   }

   @Override
   public String toString() {
      return this.recoveredField1237 == null ? super.toString() : super.toString() + " (socket: " + this.recoveredField1237 + ")";
   }
}
