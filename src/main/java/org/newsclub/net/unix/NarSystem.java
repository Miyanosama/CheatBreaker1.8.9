package org.newsclub.net.unix;

import java.io.File;
import java.net.URL;
import org.scijava.nativelib.DefaultJniExtractor;

public class NarSystem {
   public static int method_11126() {
      return new NarSystem().method_11125();
   }

   public static File method_11128(ClassLoader var0, String[] var1, String var2, String var3) {
      String var4 = NarSystem.class.getName().replace('.', '/') + ".class";
      URL var5 = var0.getResource(var4);
      if (var5 != null && "file".equals(var5.getProtocol())) {
         String var6 = var5.getPath();
         String var7 = var6.substring(0, var6.length() - var4.length()) + "../nar/" + var2 + "-";

         for (String var11 : var1) {
            File var12 = new File(var7 + var11 + "-jni/lib/" + var11 + "/jni/" + var3);
            if (var12.isFile()) {
               return var12;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static String[] method_11130() {
      String var0 = System.getProperty("os.arch") + "-" + System.getProperty("os.name").replaceAll(" ", "");
      if (var0.startsWith("i386-Linux")) {
         return new String[]{"i386-Linux-ecpc", "i386-Linux-gpp", "i386-Linux-icc", "i386-Linux-ecc", "i386-Linux-icpc", "i386-Linux-linker", "i386-Linux-gcc"};
      } else if (var0.startsWith("x86-Windows")) {
         return new String[]{"x86-Windows-linker", "x86-Windows-gpp", "x86-Windows-msvc", "x86-Windows-icl", "x86-Windows-gcc"};
      } else if (var0.startsWith("amd64-Linux")) {
         return new String[]{"amd64-Linux-gpp", "amd64-Linux-icpc", "amd64-Linux-gcc", "amd64-Linux-linker"};
      } else if (var0.startsWith("amd64-Windows")) {
         return new String[]{"amd64-Windows-gpp", "amd64-Windows-msvc", "amd64-Windows-icl", "amd64-Windows-linker", "amd64-Windows-gcc"};
      } else if (var0.startsWith("amd64-FreeBSD")) {
         return new String[]{"amd64-FreeBSD-gpp", "amd64-FreeBSD-gcc", "amd64-FreeBSD-linker"};
      } else if (var0.startsWith("ppc-MacOSX")) {
         return new String[]{"ppc-MacOSX-gpp", "ppc-MacOSX-linker", "ppc-MacOSX-gcc"};
      } else if (var0.startsWith("x86_64-MacOSX")) {
         return new String[]{"x86_64-MacOSX-icc", "x86_64-MacOSX-icpc", "x86_64-MacOSX-gpp", "x86_64-MacOSX-linker", "x86_64-MacOSX-gcc"};
      } else if (var0.startsWith("ppc-AIX")) {
         return new String[]{"ppc-AIX-gpp", "ppc-AIX-xlC", "ppc-AIX-gcc", "ppc-AIX-linker"};
      } else if (var0.startsWith("i386-FreeBSD")) {
         return new String[]{"i386-FreeBSD-gpp", "i386-FreeBSD-gcc", "i386-FreeBSD-linker"};
      } else if (var0.startsWith("sparc-SunOS")) {
         return new String[]{"sparc-SunOS-cc", "sparc-SunOS-CC", "sparc-SunOS-linker"};
      } else if (var0.startsWith("arm-Linux")) {
         return new String[]{"arm-Linux-gpp", "arm-Linux-linker", "arm-Linux-gcc"};
      } else if (var0.startsWith("x86-SunOS")) {
         return new String[]{"x86-SunOS-g++", "x86-SunOS-linker"};
      } else if (var0.startsWith("i386-MacOSX")) {
         return new String[]{"i386-MacOSX-gpp", "i386-MacOSX-gcc", "i386-MacOSX-linker"};
      } else {
         throw new RuntimeException("Unhandled architecture/OS: " + var0);
      }
   }

   public native int method_11125();

   public static String method_11127(ClassLoader var0, String[] var1, String var2) {
      for (String var6 : var1) {
         String var7 = "lib/" + var6 + "/jni/";
         if (var0.getResource(var7 + var2) != null) {
            return var7;
         }
      }

      throw new RuntimeException("Library '" + var2 + "' not found!");
   }

   public static void method_11129() {
      String var0 = "junixsocket-native-2.0.4";
      String var1 = System.mapLibraryName("junixsocket-native-2.0.4");
      String[] var2 = method_11130();
      ClassLoader var3 = NarSystem.class.getClassLoader();
      File var4 = method_11128(var3, var2, "junixsocket-native-2.0.4", var1);
      if (var4 != null) {
         System.load(var4.getPath());
      } else {
         try {
            String var5 = method_11127(var3, var2, var1);
            DefaultJniExtractor var6 = new DefaultJniExtractor(NarSystem.class, System.getProperty("java.io.tmpdir"));
            File var7 = var6.extractJni(var5, "junixsocket-native-2.0.4");
            System.load(var7.getPath());
         } catch (Exception var8) {
            var8.printStackTrace();
            throw var8 instanceof RuntimeException ? (RuntimeException)var8 : new RuntimeException(var8);
         }
      }
   }
}
