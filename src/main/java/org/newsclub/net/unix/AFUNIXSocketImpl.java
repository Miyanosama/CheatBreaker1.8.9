package org.newsclub.net.unix;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import org.newsclub.net.unix.AFUNIXSocketImpl$AFUNIXInputStream;
import org.newsclub.net.unix.AFUNIXSocketException;

public class AFUNIXSocketImpl extends SocketImpl {
   public boolean recoveredField3878;
   public static final int recoveredField3879 = 1;
   public AFUNIXSocketImpl.AFUNIXOutputStream recoveredField3880;
   public boolean recoveredField3881;
   public boolean recoveredField3882;
   public boolean recoveredField3883 = false;
   public AFUNIXSocketImpl$AFUNIXInputStream recoveredField3884;
   public static final int recoveredField3885 = 0;
   public String recoveredField3886;
   public static final int recoveredField3887 = 2;
   public boolean recoveredField3888;

   @Override
   public void create(boolean var1) throws java.io.IOException {
   }

   @Override
   public InputStream getInputStream() throws java.io.IOException {
      if (!this.recoveredField3881 && !this.recoveredField3878) {
         throw new IOException("Not connected/not bound");
      } else {
         return this.recoveredField3884;
      }
   }

   public static int expectBoolean(Object var0) throws org.newsclub.net.unix.AFUNIXSocketException {
      try {
         return (Boolean)var0 ? 1 : 0;
      } catch (ClassCastException var2) {
         throw new AFUNIXSocketException("Unsupported value: " + var0, var2);
      } catch (NullPointerException var3) {
         throw new AFUNIXSocketException("Value must not be null", var3);
      }
   }

   @Override
   public void accept(SocketImpl var1) throws java.io.IOException {
      AFUNIXSocketImpl var2 = (AFUNIXSocketImpl)var1;
      NativeUnixSocket.accept(this.recoveredField3886, this.fd, var2.fd);
      var2.recoveredField3886 = this.recoveredField3886;
      var2.recoveredField3881 = true;
   }

   public static int expectInteger(Object var0) throws org.newsclub.net.unix.AFUNIXSocketException {
      try {
         return (Integer)var0;
      } catch (ClassCastException var2) {
         throw new AFUNIXSocketException("Unsupported value: " + var0, var2);
      } catch (NullPointerException var3) {
         throw new AFUNIXSocketException("Value must not be null", var3);
      }
   }

   public AFUNIXSocketImpl() {
      this.recoveredField3878 = false;
      this.recoveredField3881 = false;
      this.recoveredField3882 = false;
      this.recoveredField3888 = false;
      this.recoveredField3884 = new AFUNIXSocketImpl$AFUNIXInputStream(this);
      this.recoveredField3880 = new AFUNIXSocketImpl.AFUNIXOutputStream();
      this.fd = new FileDescriptor();
   }

   @Override
   public synchronized void close() throws java.io.IOException {
      if (!this.recoveredField3883) {
         this.recoveredField3883 = true;
         if (this.fd.valid()) {
            NativeUnixSocket.shutdown(this.fd, 2);
            NativeUnixSocket.close(this.fd);
         }

         if (this.recoveredField3878) {
            NativeUnixSocket.unlink(this.recoveredField3886);
         }

         this.recoveredField3881 = false;
      }
   }

   @Override
   public void shutdownOutput() throws java.io.IOException {
      if (!this.recoveredField3883 && this.fd.valid()) {
         NativeUnixSocket.shutdown(this.fd, 1);
      }
   }

   // $VF: synthetic method
   public static FileDescriptor method_06143(AFUNIXSocketImpl var0) {
      return var0.fd;
   }

   @Override
   public void shutdownInput() throws java.io.IOException {
      if (!this.recoveredField3883 && this.fd.valid()) {
         NativeUnixSocket.shutdown(this.fd, 0);
      }
   }

   @Override
   public int available() throws java.io.IOException {
      return NativeUnixSocket.available(this.fd);
   }

   public FileDescriptor getFD() {
      return this.fd;
   }

   // $VF: synthetic method
   public static boolean method_06144(AFUNIXSocketImpl var0, boolean var1) {
      return var0.recoveredField3882 = var1;
   }

   // $VF: synthetic method
   public static FileDescriptor method_06122(AFUNIXSocketImpl var0) {
      return var0.fd;
   }

   @Override
   public void connect(SocketAddress var1, int var2) throws java.io.IOException {
      if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new SocketException("Cannot bind to this type of address: " + var1.getClass());
      } else {
         AFUNIXSocketAddress var3 = (AFUNIXSocketAddress)var1;
         this.recoveredField3886 = var3.method_01607();
         NativeUnixSocket.connect(this.recoveredField3886, this.fd);
         this.address = var3.getAddress();
         this.port = var3.getPort();
         this.localport = 0;
         this.recoveredField3881 = true;
      }
   }

   @Override
   public void sendUrgentData(int var1) throws java.io.IOException {
      NativeUnixSocket.write(this.fd, new byte[]{(byte)(var1 & 0xFF)}, 0, 1);
   }

   public void bind(int var1, SocketAddress var2) throws java.net.SocketException, java.io.IOException {
      if (!(var2 instanceof AFUNIXSocketAddress)) {
         throw new SocketException("Cannot bind to this type of address: " + var2.getClass());
      } else {
         AFUNIXSocketAddress var3 = (AFUNIXSocketAddress)var2;
         this.recoveredField3886 = var3.method_01607();
         NativeUnixSocket.bind(this.recoveredField3886, this.fd, var1);
         this.recoveredField3878 = true;
         this.localport = var3.getPort();
      }
   }

   @Override
   public void setOption(int var1, Object var2) throws java.net.SocketException {
      try {
         switch (var1) {
            case 1:
            case 8:
               NativeUnixSocket.setSocketOptionInt(this.fd, var1, expectBoolean(var2));
               return;
            case 128:
               if (var2 instanceof Boolean) {
                  boolean var3 = (Boolean)var2;
                  if (var3) {
                     throw new SocketException("Only accepting Boolean.FALSE here");
                  }

                  NativeUnixSocket.setSocketOptionInt(this.fd, var1, -1);
                  return;
               }

               NativeUnixSocket.setSocketOptionInt(this.fd, var1, expectInteger(var2));
               return;
            case 4097:
            case 4098:
            case 4102:
               NativeUnixSocket.setSocketOptionInt(this.fd, var1, expectInteger(var2));
               return;
            default:
               throw new AFUNIXSocketException("Unsupported option: " + var1);
         }
      } catch (AFUNIXSocketException var4) {
         throw var4;
      } catch (Exception var5) {
         throw new AFUNIXSocketException("Error while setting option", var5);
      }
   }

   public void checkClose() throws java.io.IOException {
      if (this.recoveredField3882 && this.recoveredField3888) {
      }
   }

   @Override
   public void listen(int var1) throws java.io.IOException {
      NativeUnixSocket.listen(this.fd, var1);
   }

   @Override
   public Object getOption(int var1) throws java.net.SocketException {
      try {
         switch (var1) {
            case 1:
            case 8:
               return NativeUnixSocket.getSocketOptionInt(this.fd, var1) != 0;
            case 128:
            case 4097:
            case 4098:
            case 4102:
               return NativeUnixSocket.getSocketOptionInt(this.fd, var1);
            default:
               throw new AFUNIXSocketException("Unsupported option: " + var1);
         }
      } catch (AFUNIXSocketException var3) {
         throw var3;
      } catch (Exception var4) {
         throw new AFUNIXSocketException("Error while getting option", var4);
      }
   }

   @Override
   public void connect(InetAddress var1, int var2) throws java.io.IOException {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }

   public void bind(SocketAddress var1) throws java.io.IOException {
      this.bind(0, var1);
   }

   @Override
   public OutputStream getOutputStream() throws java.io.IOException {
      if (!this.recoveredField3881 && !this.recoveredField3878) {
         throw new IOException("Not connected/not bound");
      } else {
         return this.recoveredField3880;
      }
   }

   @Override
   public String toString() {
      return super.toString()
         + "[fd="
         + this.fd
         + "; file="
         + this.recoveredField3886
         + "; connected="
         + this.recoveredField3881
         + "; bound="
         + this.recoveredField3878
         + "]";
   }

   @Override
   public void connect(String var1, int var2) throws java.io.IOException {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }

   @Override
   public void bind(InetAddress var1, int var2) throws java.io.IOException {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }

   // $VF: synthetic method
   public static FileDescriptor method_06146(AFUNIXSocketImpl var0) {
      return var0.fd;
   }

   // $VF: synthetic method
   public static FileDescriptor method_06139(AFUNIXSocketImpl var0) {
      return var0.fd;
   }

   public class AFUNIXOutputStream extends OutputStream {
      public boolean streamClosed = false;

      @Override
      public void close() throws java.io.IOException {
         if (!this.streamClosed) {
            this.streamClosed = true;
            if (AFUNIXSocketImpl.this.fd.valid()) {
               NativeUnixSocket.shutdown(AFUNIXSocketImpl.this.fd, 1);
            }

            AFUNIXSocketImpl.this.recoveredField3888 = true;
            AFUNIXSocketImpl.this.checkClose();
         }
      }

      @Override
      public void write(byte[] var1, int var2, int var3) throws java.io.IOException {
         if (this.streamClosed) {
            throw new AFUNIXSocketException("This OutputStream has already been closed.");
         } else if (var3 > var1.length - var2) {
            throw new IndexOutOfBoundsException();
         } else {
            try {
               while (var3 > 0 && !Thread.interrupted()) {
                  int var4 = NativeUnixSocket.write(AFUNIXSocketImpl.this.fd, var1, var2, var3);
                  if (var4 == -1) {
                     throw new IOException("Unspecific error while writing");
                  }

                  var3 -= var4;
                  var2 += var4;
               }
            } catch (IOException var5) {
               throw (IOException)new IOException(var5.getMessage() + " at " + AFUNIXSocketImpl.this.toString()).initCause(var5);
            }
         }
      }

      public AFUNIXOutputStream() {
      }

      @Override
      public void write(int var1) throws java.io.IOException {
         byte[] var2 = new byte[]{(byte)var1};
         this.write(var2, 0, 1);
      }
   }

   public static final class Lenient extends AFUNIXSocketImpl {
      @Override
      public Object getOption(int var1) throws java.net.SocketException {
         try {
            return super.getOption(var1);
         } catch (SocketException var3) {
            switch (var1) {
               case 1:
               case 8:
                  return false;
               default:
                  throw var3;
            }
         }
      }

      @Override
      public void setOption(int var1, Object var2) throws java.net.SocketException {
         try {
            super.setOption(var1, var2);
         } catch (SocketException var4) {
            switch (var1) {
               case 1:
                  return;
               default:
                  throw var4;
            }
         }
      }
   }
}
