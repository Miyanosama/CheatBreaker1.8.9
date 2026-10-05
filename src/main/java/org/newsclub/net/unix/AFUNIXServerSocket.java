package org.newsclub.net.unix;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import org.newsclub.net.unix.AFUNIXServerSocket$1;

public class AFUNIXServerSocket extends ServerSocket {
   public AFUNIXSocketImpl recoveredField3514;
   public Thread recoveredField3515;
   public AFUNIXSocketAddress boundEndpoint = null;

   @Override
   public boolean isBound() {
      return this.boundEndpoint != null;
   }

   // $VF: synthetic method
   public static AFUNIXSocketAddress method_05705(AFUNIXServerSocket var0) {
      return var0.boundEndpoint;
   }

   public static boolean isSupported() {
      return NativeUnixSocket.isLoaded();
   }

   public AFUNIXServerSocket() throws java.io.IOException {
      this.recoveredField3515 = new AFUNIXServerSocket$1(this);
      this.recoveredField3514 = new AFUNIXSocketImpl();
      NativeUnixSocket.initServerImpl(this, this.recoveredField3514);
      Runtime.getRuntime().addShutdownHook(this.recoveredField3515);
      NativeUnixSocket.setCreatedServer(this);
   }

   @Override
   public String toString() {
      return !this.isBound() ? "AFUNIXServerSocket[unbound]" : "AFUNIXServerSocket[" + this.boundEndpoint.method_01607() + "]";
   }

   public static AFUNIXServerSocket bindOn(AFUNIXSocketAddress var0) throws java.io.IOException {
      AFUNIXServerSocket var1 = newInstance();
      var1.bind(var0);
      return var1;
   }

   @Override
   public void bind(SocketAddress var1, int var2) throws java.io.IOException {
      if (this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if (this.isBound()) {
         throw new SocketException("Already bound");
      } else if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new IOException("Can only bind to endpoints of type " + AFUNIXSocketAddress.class.getName());
      } else {
         this.recoveredField3514.bind(var2, var1);
         this.boundEndpoint = (AFUNIXSocketAddress)var1;
      }
   }

   public static AFUNIXServerSocket newInstance() throws java.io.IOException {
      return new AFUNIXServerSocket();
   }

   @Override
   public void close() throws java.io.IOException {
      if (!this.isClosed()) {
         super.close();
         this.recoveredField3514.close();
         if (this.boundEndpoint != null) {
            NativeUnixSocket.unlink(this.boundEndpoint.method_01607());
         }

         try {
            Runtime.getRuntime().removeShutdownHook(this.recoveredField3515);
         } catch (IllegalStateException var2) {
         }
      }
   }
}
