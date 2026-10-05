package org.newsclub.net.unix;

import java.io.File;
import java.net.InetSocketAddress;

public class AFUNIXSocketAddress extends InetSocketAddress {
   public static final long recoveredField16 = 1L;
   public String recoveredField17;

   @Override
   public String toString() {
      return this.getClass().getName() + "[host=" + this.getHostName() + ";port=" + this.getPort() + ";file=" + this.recoveredField17 + "]";
   }

   public String method_01607() {
      return this.recoveredField17;
   }

   public AFUNIXSocketAddress(File var1, int var2) throws java.io.IOException {
      super(0);
      if (var2 != 0) {
         NativeUnixSocket.setPort1(this, var2);
      }

      this.recoveredField17 = var1.getCanonicalPath();
   }

   public AFUNIXSocketAddress(File var1) throws java.io.IOException {
      this(var1, 0);
   }
}
