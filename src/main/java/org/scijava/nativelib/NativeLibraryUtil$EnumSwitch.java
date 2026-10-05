package org.scijava.nativelib;

import org.scijava.nativelib.NativeLibraryUtil;

// $VF: synthetic class
public class NativeLibraryUtil$EnumSwitch {
   public static int[] recoveredField3306 = new int[NativeLibraryUtil.Architecture.values().length];

   static {
      try {
         recoveredField3306[NativeLibraryUtil.Architecture.LINUX_32.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         recoveredField3306[NativeLibraryUtil.Architecture.LINUX_64.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         recoveredField3306[NativeLibraryUtil.Architecture.WINDOWS_32.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField3306[NativeLibraryUtil.Architecture.WINDOWS_64.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField3306[NativeLibraryUtil.Architecture.OSX_32.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField3306[NativeLibraryUtil.Architecture.OSX_64.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
