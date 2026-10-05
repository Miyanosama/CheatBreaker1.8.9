package recovered.unidentified;

import io.netty.handler.codec.http.HttpObjectEncoder;
import java.io.IOException;
import org.scijava.nativelib.JniExtractor;

public class UnidentifiedClass3876 {
   public HttpObjectEncoder field_0000;
   public static JniExtractor field_0001 = null;

   public static JniExtractor method_23455() {
      return field_0001;
   }

   public static void method_23458() {
      field_0001.extractRegistered();
   }

   static {
      try {
         if (UnidentifiedClass3876.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
            field_0001 = new UnidentifiedClass4584();
         } else {
            field_0001 = new UnidentifiedClass1776("Classloader");
         }
      } catch (IOException var1) {
         throw new ExceptionInInitializerError(var1);
      }
   }

   public static void method_23457(JniExtractor var0) {
      field_0001 = var0;
   }

   public static void method_23456(String var0) {
      System.load(field_0001.extractJni("", var0).getAbsolutePath());
   }
}
