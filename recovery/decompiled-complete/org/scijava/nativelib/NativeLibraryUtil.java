package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.server.management.UserListWhitelistEntry;
import recovered.unidentified.UnidentifiedClass3988;
import recovered.unidentified.UnidentifiedClass4584;

public class NativeLibraryUtil {
   public static String field_0003;
   public EntityWeatherEffect field_0005;
   public static Logger field_0002 = Logger.getLogger("org.scijava.nativelib.NativeLibraryUtil");
   public UserListWhitelistEntry field_0004;
   public static NativeLibraryUtil$Architecture architecture = NativeLibraryUtil$Architecture.field_0010;
   public static String field_0001;

   public static String method_20323(String var0) {
      String var1 = null;
      switch (UnidentifiedClass3988.field_0003[getArchitecture().ordinal()]) {
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

      field_0002.log(Level.FINE, "native library name " + var1);
      return var1;
   }

   public static String method_20321() {
      String var0 = "META-INF/lib/";
      var0 = var0 + getArchitecture().name().toLowerCase() + "/";
      field_0002.log(Level.FINE, "platform specific path is " + var0);
      return var0;
   }

   public static NativeLibraryUtil$Architecture getArchitecture() {
      if (NativeLibraryUtil$Architecture.field_0010 == architecture) {
         NativeLibraryUtil$Processor var0 = getProcessor();
         if (NativeLibraryUtil$Processor.field_0003 != var0) {
            String var1 = System.getProperty("os.name").toLowerCase();
            if (var1.indexOf("nix") < 0 && var1.indexOf("nux") < 0) {
               if (var1.indexOf("win") >= 0) {
                  if (NativeLibraryUtil$Processor.field_0000 == var0) {
                     architecture = NativeLibraryUtil$Architecture.field_0007;
                  } else if (NativeLibraryUtil$Processor.field_0005 == var0) {
                     architecture = NativeLibraryUtil$Architecture.field_0005;
                  }
               } else if (var1.indexOf("mac") >= 0) {
                  if (NativeLibraryUtil$Processor.field_0000 == var0) {
                     architecture = NativeLibraryUtil$Architecture.field_0013;
                  } else if (NativeLibraryUtil$Processor.field_0005 == var0) {
                     architecture = NativeLibraryUtil$Architecture.field_0002;
                  } else if (NativeLibraryUtil$Processor.field_0004 == var0) {
                     architecture = NativeLibraryUtil$Architecture.field_0003;
                  }
               }
            } else if (NativeLibraryUtil$Processor.field_0000 == var0) {
               architecture = NativeLibraryUtil$Architecture.field_0012;
            } else if (NativeLibraryUtil$Processor.field_0005 == var0) {
               architecture = NativeLibraryUtil$Architecture.field_0006;
            }
         }
      }

      field_0002.log(Level.FINE, "architecture is " + architecture + " os.name is " + System.getProperty("os.name").toLowerCase());
      return architecture;
   }

   public static NativeLibraryUtil$Processor getProcessor() {
      NativeLibraryUtil$Processor var0 = NativeLibraryUtil$Processor.field_0003;
      String var2 = System.getProperty("os.arch").toLowerCase();
      if (var2.indexOf("ppc") >= 0) {
         var0 = NativeLibraryUtil$Processor.field_0004;
      } else if (var2.indexOf("86") >= 0 || var2.indexOf("amd") >= 0) {
         byte var1 = 32;
         if (var2.indexOf("64") >= 0) {
            var1 = 64;
         }

         var0 = 32 == var1 ? NativeLibraryUtil$Processor.field_0000 : NativeLibraryUtil$Processor.field_0005;
      }

      field_0002.log(Level.FINE, "processor is " + var0 + " os.arch is " + System.getProperty("os.arch").toLowerCase());
      return var0;
   }

   public static boolean loadVersionedNativeLibrary(Class var0, String var1) {
      var1 = getVersionedLibraryName(var0, var1);
      return loadNativeLibrary(var0, var1);
   }

   public static boolean loadNativeLibrary(Class var0, String var1) {
      boolean var2 = false;
      if (NativeLibraryUtil$Architecture.field_0010 == getArchitecture()) {
         field_0002.log(Level.WARNING, "No native library available for this platform.");
      } else {
         try {
            String var3 = System.getProperty("java.io.tmpdir");
            UnidentifiedClass4584 var4 = new UnidentifiedClass4584(var0, var3);
            File var5 = var4.extractJni(method_20321(), var1);
            System.load(var5.getPath());
            var2 = true;
         } catch (IOException var6) {
            field_0002.log(Level.WARNING, "IOException creating DefaultJniExtractor", (Throwable)var6);
         } catch (SecurityException var7) {
            field_0002.log(Level.WARNING, "Can't load dynamic library", (Throwable)var7);
         } catch (UnsatisfiedLinkError var8) {
            field_0002.log(Level.WARNING, "Problem with library", (Throwable)var8);
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
}
