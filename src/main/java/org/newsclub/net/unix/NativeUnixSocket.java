package org.newsclub.net.unix;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import org.newsclub.net.unix.AFUNIXSocketException;

public class NativeUnixSocket {
   public static boolean loaded = false;

   public static native void setSocketOptionInt(FileDescriptor var0, int var1, int var2) throws java.io.IOException ;

   public static native void setConnected(AFUNIXSocket var0);

   public static native void setCreatedServer(AFUNIXServerSocket var0);

   static {
      try {
         Class.forName("org.newsclub.net.unix.NarSystem").getMethod("loadLibrary").invoke(null);
      } catch (ClassNotFoundException var1) {
         throw new IllegalStateException(
            "Could not find NarSystem class.\n\n*** ECLIPSE USERS ***\nIf you're running from within Eclipse, please try closing the \"junixsocket-native-common\" project\n",
            var1
         );
      } catch (Exception var2) {
         throw new IllegalStateException(var2);
      }

      loaded = true;
   }

   public static native int read(FileDescriptor var0, byte[] var1, int var2, int var3) throws java.io.IOException ;

   public static native void bind(String var0, FileDescriptor var1, int var2) throws java.io.IOException ;

   public static native void shutdown(FileDescriptor var0, int var1) throws java.io.IOException ;

   public static native void setCreated(AFUNIXSocket var0);

   public static native void unlink(String var0) throws java.io.IOException ;

   public static native void connect(String var0, FileDescriptor var1) throws java.io.IOException ;

   public static native void setBoundServer(AFUNIXServerSocket var0);

   public static native void listen(FileDescriptor var0, int var1) throws java.io.IOException ;

   public static native void setBound(AFUNIXSocket var0);

   public static native void initServerImpl(AFUNIXServerSocket var0, AFUNIXSocketImpl var1) ;

   public static native int available(FileDescriptor var0) throws java.io.IOException ;

   public static native void setPort(AFUNIXSocketAddress var0, int var1);

   public static boolean isLoaded() {
      return loaded;
   }

   public static native int getSocketOptionInt(FileDescriptor var0, int var1) throws java.io.IOException ;

   public static void checkSupported() {
   }

   public static void setPort1(AFUNIXSocketAddress var0, int var1) throws java.io.IOException, org.newsclub.net.unix.AFUNIXSocketException {
      if (var1 < 0) {
         throw new IllegalArgumentException("port out of range:" + var1);
      } else {
         boolean var2 = false;

         try {
            Field var3 = InetSocketAddress.class.getDeclaredField("holder");
            if (var3 != null) {
               var3.setAccessible(true);
               Object var4 = var3.get(var0);
               if (var4 != null) {
                  Field var5 = var4.getClass().getDeclaredField("port");
                  if (var5 != null) {
                     var5.setAccessible(true);
                     var5.set(var4, var1);
                     var2 = true;
                  }
               }
            } else {
               setPort(var0, var1);
            }
         } catch (RuntimeException var6) {
            throw var6;
         } catch (Exception var7) {
            if (var7 instanceof AFUNIXSocketException) {
               throw (AFUNIXSocketException)var7;
            }

            throw new AFUNIXSocketException("Could not set port", var7);
         }

         if (!var2) {
            throw new AFUNIXSocketException("Could not set port");
         }
      }
   }

   public static native void accept(String var0, FileDescriptor var1, FileDescriptor var2) throws java.io.IOException ;

   public static native void close(FileDescriptor var0) throws java.io.IOException ;

   public static native int write(FileDescriptor var0, byte[] var1, int var2, int var3) throws java.io.IOException ;
}
