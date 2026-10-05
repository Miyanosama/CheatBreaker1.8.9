package org.newsclub.net.unix;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class AFUNIXSocket extends Socket {
   public AFUNIXSocketImpl recoveredField3261;
   public AFUNIXSocketAddress recoveredField3262;

   @Override
   public void bind(SocketAddress var1) throws java.io.IOException {
      super.bind(var1);
      this.recoveredField3262 = (AFUNIXSocketAddress)var1;
   }

   @Override
   public String toString() {
      return this.isConnected()
         ? "AFUNIXSocket[fd=" + this.recoveredField3261.getFD() + ";path=" + this.recoveredField3262.method_01607() + "]"
         : "AFUNIXSocket[unconnected]";
   }

   @Override
   public void connect(SocketAddress var1) throws java.io.IOException {
      this.connect(var1, 0);
   }

   public static AFUNIXSocket connectTo(AFUNIXSocketAddress var0) throws java.io.IOException {
      AFUNIXSocket var1 = method_25943();
      var1.connect(var0);
      return var1;
   }

   public static boolean method_25947() {
      return NativeUnixSocket.isLoaded();
   }

   @Override
   public void connect(SocketAddress var1, int var2) throws java.io.IOException {
      if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new IOException("Can only connect to endpoints of type " + AFUNIXSocketAddress.class.getName());
      } else {
         this.recoveredField3261.connect(var1, var2);
         this.recoveredField3262 = (AFUNIXSocketAddress)var1;
         NativeUnixSocket.setConnected(this);
      }
   }

   public AFUNIXSocket(AFUNIXSocketImpl var1) throws java.net.SocketException, java.io.IOException {
      super(var1);

      try {
         NativeUnixSocket.setCreated(this);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   public static AFUNIXSocket method_25949() throws java.net.SocketException, java.io.IOException {
      AFUNIXSocketImpl var0 = new AFUNIXSocketImpl();
      AFUNIXSocket var1 = new AFUNIXSocket(var0);
      var1.recoveredField3261 = var0;
      return var1;
   }

   public static AFUNIXSocket method_25943() throws java.net.SocketException, java.io.IOException {
      AFUNIXSocketImpl.Lenient var0 = new AFUNIXSocketImpl.Lenient();
      AFUNIXSocket var1 = new AFUNIXSocket(var0);
      var1.recoveredField3261 = var0;
      return var1;
   }
}
