package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scijava.nativelib.NativeLibraryUtil$EnumSwitch;
import org.scijava.nativelib.DefaultJniExtractor;

public class NativeLibraryUtil {
   public static final String recoveredField1732 = "java.io.tmpdir";
   public static final String recoveredField1734 = "/";
   public static NativeLibraryUtil.Architecture architecture = NativeLibraryUtil.Architecture.UNKNOWN;
   public static Logger recoveredField1733 = Logger.getLogger("org.scijava.nativelib.NativeLibraryUtil");

   public static String method_20323(String var0) {
      String var1 = null;
      switch (NativeLibraryUtil$EnumSwitch.recoveredField3306[getArchitecture().ordinal()]) {
         case 1:
         case 2:
            var1 = var0 + ".so";
            break;
         case 3:
         case 4:
            var1 = var0 + ".dll";
            break;
         case 5:
         case 6:
            var1 = "lib" + var0 + ".dylib";
      }

      recoveredField1733.log(Level.FINE, "native library name " + var1);
      return var1;
   }

   public static String method_20321() {
      String var0 = "META-INF/lib/";
      var0 = var0 + getArchitecture().name().toLowerCase() + "/";
      recoveredField1733.log(Level.FINE, "platform specific path is " + var0);
      return var0;
   }

   public static NativeLibraryUtil.Architecture getArchitecture() {
      if (NativeLibraryUtil.Architecture.UNKNOWN == architecture) {
         NativeLibraryUtil.Processor var0 = getProcessor();
         if (NativeLibraryUtil.Processor.UNKNOWN != var0) {
            String var1 = System.getProperty("os.name").toLowerCase();
            if (var1.indexOf("nix") < 0 && var1.indexOf("nux") < 0) {
               if (var1.indexOf("win") >= 0) {
                  if (NativeLibraryUtil.Processor.INTEL_32 == var0) {
                     architecture = NativeLibraryUtil.Architecture.WINDOWS_32;
                  } else if (NativeLibraryUtil.Processor.INTEL_64 == var0) {
                     architecture = NativeLibraryUtil.Architecture.WINDOWS_64;
                  }
               } else if (var1.indexOf("mac") >= 0) {
                  if (NativeLibraryUtil.Processor.INTEL_32 == var0) {
                     architecture = NativeLibraryUtil.Architecture.OSX_32;
                  } else if (NativeLibraryUtil.Processor.INTEL_64 == var0) {
                     architecture = NativeLibraryUtil.Architecture.OSX_64;
                  } else if (NativeLibraryUtil.Processor.PPC == var0) {
                     architecture = NativeLibraryUtil.Architecture.OSX_PPC;
                  }
               }
            } else if (NativeLibraryUtil.Processor.INTEL_32 == var0) {
               architecture = NativeLibraryUtil.Architecture.LINUX_32;
            } else if (NativeLibraryUtil.Processor.INTEL_64 == var0) {
               architecture = NativeLibraryUtil.Architecture.LINUX_64;
            }
         }
      }

      recoveredField1733.log(Level.FINE, "architecture is " + architecture + " os.name is " + System.getProperty("os.name").toLowerCase());
      return architecture;
   }

   public static NativeLibraryUtil.Processor getProcessor() {
      NativeLibraryUtil.Processor var0 = NativeLibraryUtil.Processor.UNKNOWN;
      String var2 = System.getProperty("os.arch").toLowerCase();
      if (var2.indexOf("ppc") >= 0) {
         var0 = NativeLibraryUtil.Processor.PPC;
      } else if (var2.indexOf("86") >= 0 || var2.indexOf("amd") >= 0) {
         byte var1 = 32;
         if (var2.indexOf("64") >= 0) {
            var1 = 64;
         }

         var0 = 32 == var1 ? NativeLibraryUtil.Processor.INTEL_32 : NativeLibraryUtil.Processor.INTEL_64;
      }

      recoveredField1733.log(Level.FINE, "processor is " + var0 + " os.arch is " + System.getProperty("os.arch").toLowerCase());
      return var0;
   }

   public static boolean loadVersionedNativeLibrary(Class var0, String var1) {
      var1 = getVersionedLibraryName(var0, var1);
      return loadNativeLibrary(var0, var1);
   }

   public static boolean loadNativeLibrary(Class var0, String var1) {
      boolean var2 = false;
      if (NativeLibraryUtil.Architecture.UNKNOWN == getArchitecture()) {
         recoveredField1733.log(Level.WARNING, "No native library available for this platform.");
      } else {
         try {
            String var3 = System.getProperty("java.io.tmpdir");
            DefaultJniExtractor var4 = new DefaultJniExtractor(var0, var3);
            File var5 = var4.extractJni(method_20321(), var1);
            System.load(var5.getPath());
            var2 = true;
         } catch (IOException var6) {
            recoveredField1733.log(Level.WARNING, "IOException creating DefaultJniExtractor", (Throwable)var6);
         } catch (SecurityException var7) {
            recoveredField1733.log(Level.WARNING, "Can't load dynamic library", (Throwable)var7);
         } catch (UnsatisfiedLinkError var8) {
            recoveredField1733.log(Level.WARNING, "Problem with library", (Throwable)var8);
         }
      }

      return var2;
   }

   public static String getVersionedLibraryName(Class var0, String var1) {
      String var2 = var0.getPackage().getImplementationVersion();
      if (null != var2 && var2.length() > 0) {
         var1 = var1 + "-" + var2;
      }

      return var1;
   }

   public static enum Architecture {
      UNKNOWN,
      LINUX_32,
      LINUX_64,
      WINDOWS_32,
      WINDOWS_64,
      OSX_32,
      OSX_64,
      OSX_PPC;
      // $VF: synthetic field
      public static NativeLibraryUtil.Architecture[] recoveredField881 = new NativeLibraryUtil.Architecture[]{
         UNKNOWN,
         LINUX_32,
         LINUX_64,
         NativeLibraryUtil.Architecture.WINDOWS_32,
         WINDOWS_64,
         NativeLibraryUtil.Architecture.OSX_32,
         OSX_64,
         NativeLibraryUtil.Architecture.OSX_PPC
      };
   }

   public static enum Processor {
      UNKNOWN,
      INTEL_32,
      INTEL_64,
      PPC;
   }
}
