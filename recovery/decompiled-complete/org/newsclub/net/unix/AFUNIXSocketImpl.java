package org.newsclub.net.unix;

import io.netty.buffer.PooledUnsafeDirectByteBuf$1;
import io.netty.handler.ssl.SslContext;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1;
import net.minecraft.client.model.ModelGuardian;
import recovered.unidentified.UnidentifiedClass1561;
import recovered.unidentified.UnidentifiedClass1587;
import recovered.unidentified.UnidentifiedClass4013;

public class AFUNIXSocketImpl extends SocketImpl {
   public SslContext field_0006;
   public ModelGuardian field_0013;
   public boolean field_0005;
   public static int field_0011;
   public PooledUnsafeDirectByteBuf$1 field_0001;
   public AFUNIXSocketImpl$AFUNIXOutputStream field_0002;
   public boolean field_0014;
   public boolean field_0009;
   public LayerIDecoder$SubbandLayer1 field_0003;
   public UnidentifiedClass4013 field_0015;
   public boolean field_0000 = false;
   public UnidentifiedClass1561 field_0007;
   public static int field_0008;
   public String field_0004;
   public static int field_0010;
   public boolean field_0012;

   @Override
   public void create(boolean var1) {
   }

   @Override
   public InputStream getInputStream() {
      if (!this.field_0014 && !this.field_0005) {
         throw new IOException("Not connected/not bound");
      } else {
         return this.field_0007;
      }
   }

   public static int method_06137(Object var0) {
      try {
         return (Boolean)var0 ? 1 : 0;
      } catch (ClassCastException var2) {
         throw new UnidentifiedClass1587("Unsupported value: " + var0, var2);
      } catch (NullPointerException var3) {
         throw new UnidentifiedClass1587("Value must not be null", var3);
      }
   }

   @Override
   public void accept(SocketImpl var1) {
      AFUNIXSocketImpl var2 = (AFUNIXSocketImpl)var1;
      NativeUnixSocket.method_25801(this.field_0004, this.fd, var2.fd);
      var2.field_0004 = this.field_0004;
      var2.field_0014 = true;
   }

   public static int method_06145(Object var0) {
      try {
         return (Integer)var0;
      } catch (ClassCastException var2) {
         throw new UnidentifiedClass1587("Unsupported value: " + var0, var2);
      } catch (NullPointerException var3) {
         throw new UnidentifiedClass1587("Value must not be null", var3);
      }
   }

   public AFUNIXSocketImpl() {
      this.field_0005 = false;
      this.field_0014 = false;
      this.field_0009 = false;
      this.field_0012 = false;
      this.field_0007 = new UnidentifiedClass1561(this, null);
      this.field_0002 = new AFUNIXSocketImpl$AFUNIXOutputStream(this, null);
      this.fd = new FileDescriptor();
   }

   @Override
   public synchronized void close() {
      if (!this.field_0000) {
         this.field_0000 = true;
         if (this.fd.valid()) {
            NativeUnixSocket.method_25795(this.fd, 2);
            NativeUnixSocket.close(this.fd);
         }

         if (this.field_0005) {
            NativeUnixSocket.method_25798(this.field_0004);
         }

         this.field_0014 = false;
      }
   }

   @Override
   public void shutdownOutput() {
      if (!this.field_0000 && this.fd.valid()) {
         NativeUnixSocket.method_25795(this.fd, 1);
      }
   }

   @Override
   public void shutdownInput() {
      if (!this.field_0000 && this.fd.valid()) {
         NativeUnixSocket.method_25795(this.fd, 0);
      }
   }

   @Override
   public int available() {
      return NativeUnixSocket.method_25794(this.fd);
   }

   public FileDescriptor getFD() {
      return this.fd;
   }

   @Override
   public void connect(SocketAddress var1, int var2) {
      if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new SocketException("Cannot bind to this type of address: " + var1.getClass());
      } else {
         AFUNIXSocketAddress var3 = (AFUNIXSocketAddress)var1;
         this.field_0004 = var3.method_01607();
         NativeUnixSocket.method_25799(this.field_0004, this.fd);
         this.address = var3.getAddress();
         this.port = var3.getPort();
         this.localport = 0;
         this.field_0014 = true;
      }
   }

   @Override
   public void sendUrgentData(int var1) {
      NativeUnixSocket.method_25808(this.fd, new byte[]{(byte)(var1 & 0xFF)}, 0, 1);
   }

   public void method_06134(int var1, SocketAddress var2) {
      if (!(var2 instanceof AFUNIXSocketAddress)) {
         throw new SocketException("Cannot bind to this type of address: " + var2.getClass());
      } else {
         AFUNIXSocketAddress var3 = (AFUNIXSocketAddress)var2;
         this.field_0004 = var3.method_01607();
         NativeUnixSocket.method_25800(this.field_0004, this.fd, var1);
         this.field_0005 = true;
         this.localport = var3.getPort();
      }
   }

   @Override
   public void setOption(int var1, Object var2) {
      try {
         switch (var1) {
            case 1:
            case 8:
               NativeUnixSocket.setSocketOptionInt(this.fd, var1, method_06137(var2));
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

               NativeUnixSocket.setSocketOptionInt(this.fd, var1, method_06145(var2));
               return;
            case 4097:
            case 4098:
            case 4102:
               NativeUnixSocket.setSocketOptionInt(this.fd, var1, method_06145(var2));
               return;
            default:
               throw new UnidentifiedClass1587("Unsupported option: " + var1);
         }
      } catch (UnidentifiedClass1587 var4) {
         throw var4;
      } catch (Exception var5) {
         throw new UnidentifiedClass1587("Error while setting option", var5);
      }
   }

   public void checkClose() {
      if (this.field_0009 && this.field_0012) {
      }
   }

   @Override
   public void listen(int var1) {
      NativeUnixSocket.method_25807(this.fd, var1);
   }

   @Override
   public Object getOption(int var1) {
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
               throw new UnidentifiedClass1587("Unsupported option: " + var1);
         }
      } catch (UnidentifiedClass1587 var3) {
         throw var3;
      } catch (Exception var4) {
         throw new UnidentifiedClass1587("Error while getting option", var4);
      }
   }

   @Override
   public void connect(InetAddress var1, int var2) {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }

   public void bind(SocketAddress var1) {
      this.method_06134(0, var1);
   }

   @Override
   public OutputStream getOutputStream() {
      if (!this.field_0014 && !this.field_0005) {
         throw new IOException("Not connected/not bound");
      } else {
         return this.field_0002;
      }
   }

   @Override
   public String toString() {
      return super.toString() + "[fd=" + this.fd + "; file=" + this.field_0004 + "; connected=" + this.field_0014 + "; bound=" + this.field_0005 + "]";
   }

   @Override
   public void connect(String var1, int var2) {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }

   @Override
   public void bind(InetAddress var1, int var2) {
      throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
   }
}
