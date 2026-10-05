package org.newsclub.net.unix;

import java.io.IOException;
import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.NativeUnixSocket;

public class AFUNIXServerSocket$1 extends Thread {
   public AFUNIXServerSocket recoveredField359;

   @Override
   public void run() {
      try {
         if (AFUNIXServerSocket.method_05705(this.recoveredField359) != null) {
            NativeUnixSocket.unlink(AFUNIXServerSocket.method_05705(this.recoveredField359).method_01607());
         }
      } catch (IOException var2) {
      }
   }

   public AFUNIXServerSocket$1(AFUNIXServerSocket var1) {
      this.recoveredField359 = var1;
   }
}
