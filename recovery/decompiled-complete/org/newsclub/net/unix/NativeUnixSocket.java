package org.newsclub.net.unix;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import org.apache.log4j.helpers.ISO8601DateFormat;
import recovered.unidentified.UnidentifiedClass1587;

public class NativeUnixSocket {
   public ISO8601DateFormat field_0001;
   public S01PacketEncryptionRequest field_0003;
   public static boolean loaded = false;
   public WorldGenHugeTrees field_0002;

   public static native void setSocketOptionInt(FileDescriptor var0, int var1, int var2);

   public static native void method_25802(AFUNIXSocket var0);

   public static native void method_25792(AFUNIXServerSocket var0);

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

   public static native int method_25797(FileDescriptor var0, byte[] var1, int var2, int var3);

   public static native void method_25800(String var0, FileDescriptor var1, int var2);

   public static native void method_25795(FileDescriptor var0, int var1);

   public static native void method_25809(AFUNIXSocket var0);

   public static native void method_25798(String var0);

   public static native void method_25799(String var0, FileDescriptor var1);

   public static native void method_25805(AFUNIXServerSocket var0);

   public static native void method_25807(FileDescriptor var0, int var1);

   public static native void method_25789(AFUNIXSocket var0);

   public static native void initServerImpl(AFUNIXServerSocket var0, AFUNIXSocketImpl var1);

   public static native int method_25794(FileDescriptor var0);

   public static native void setPort(AFUNIXSocketAddress var0, int var1);

   public static boolean isLoaded() {
      return loaded;
   }

   public static native int getSocketOptionInt(FileDescriptor var0, int var1);

   public static void checkSupported() {
   }

   public static void setPort1(AFUNIXSocketAddress var0, int var1) {
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
            if (var7 instanceof UnidentifiedClass1587) {
               throw (UnidentifiedClass1587)var7;
            }

            throw new UnidentifiedClass1587("Could not set port", var7);
         }

         if (!var2) {
            throw new UnidentifiedClass1587("Could not set port");
         }
      }
   }

   public static native void method_25801(String var0, FileDescriptor var1, FileDescriptor var2);

   public static native void close(FileDescriptor var0);

   public static native int method_25808(FileDescriptor var0, byte[] var1, int var2, int var3);
}
