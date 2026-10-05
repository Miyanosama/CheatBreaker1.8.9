package org.newsclub.net.unix;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import recovered.unidentified.UnidentifiedClass1785;

public class AFUNIXServerSocket extends ServerSocket {
   public AFUNIXSocketImpl field_0001;
   public Thread field_0002;
   public AFUNIXSocketAddress boundEndpoint = null;

   @Override
   public boolean isBound() {
      return this.boundEndpoint != null;
   }

   @Override
   public Socket accept() {
      if (this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         AFUNIXSocket var1 = AFUNIXSocket.method_25943();
         this.field_0001.accept(var1.field_0002);
         var1.field_0005 = this.boundEndpoint;
         NativeUnixSocket.method_25802(var1);
         return var1;
      }
   }

   public static boolean isSupported() {
      return NativeUnixSocket.isLoaded();
   }

   public AFUNIXServerSocket() {
      this.field_0002 = new UnidentifiedClass1785(this);
      this.field_0001 = new AFUNIXSocketImpl();
      NativeUnixSocket.initServerImpl(this, this.field_0001);
      Runtime.getRuntime().addShutdownHook(this.field_0002);
      NativeUnixSocket.method_25792(this);
   }

   @Override
   public String toString() {
      return !this.isBound() ? "AFUNIXServerSocket[unbound]" : "AFUNIXServerSocket[" + this.boundEndpoint.method_01607() + "]";
   }

   public static AFUNIXServerSocket bindOn(AFUNIXSocketAddress var0) {
      AFUNIXServerSocket var1 = newInstance();
      var1.bind(var0);
      return var1;
   }

   @Override
   public void bind(SocketAddress var1, int var2) {
      if (this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if (this.isBound()) {
         throw new SocketException("Already bound");
      } else if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new IOException("Can only bind to endpoints of type " + AFUNIXSocketAddress.class.getName());
      } else {
         this.field_0001.method_06134(var2, var1);
         this.boundEndpoint = (AFUNIXSocketAddress)var1;
      }
   }

   public static AFUNIXServerSocket newInstance() {
      return new AFUNIXServerSocket();
   }

   @Override
   public void close() {
      if (!this.isClosed()) {
         super.close();
         this.field_0001.close();
         if (this.boundEndpoint != null) {
            NativeUnixSocket.method_25798(this.boundEndpoint.method_01607());
         }

         try {
            Runtime.getRuntime().removeShutdownHook(this.field_0002);
         } catch (IllegalStateException var2) {
         }
      }
   }
}
