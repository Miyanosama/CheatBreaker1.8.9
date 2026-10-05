package org.scijava.nativelib;

import org.scijava.nativelib.DefaultJniExtractor;

import java.io.IOException;
import org.scijava.nativelib.JniExtractor;

public class NativeLoader {
   public static JniExtractor recoveredField3028 = null;

   public static JniExtractor method_23455() {
      return recoveredField3028;
   }

   public static void method_23458() throws java.io.IOException {
      recoveredField3028.extractRegistered();
   }

   static {
      try {
         if (NativeLoader.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
            recoveredField3028 = new DefaultJniExtractor();
         } else {
            recoveredField3028 = new WebappJniExtractor("Classloader");
         }
      } catch (IOException var1) {
         throw new ExceptionInInitializerError(var1);
      }
   }

   public static void method_23457(JniExtractor var0) {
      recoveredField3028 = var0;
   }

   public static void method_23456(String var0) throws java.io.IOException {
      System.load(recoveredField3028.extractJni("", var0).getAbsolutePath());
   }
}
