package org.apache.log4j.lf5.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;

public abstract class StreamUtils {
   public static int field_0000;
   public AnvilSaveConverter field_0001;

   public static byte[] getBytes(InputStream var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      copy(var0, var1);
      var1.close();
      return var1.toByteArray();
   }

   public static void copy(InputStream var0, OutputStream var1, int var2) {
      byte[] var3 = new byte[var2];

      for (int var4 = var0.read(var3); var4 != -1; var4 = var0.read(var3)) {
         var1.write(var3, 0, var4);
      }

      var1.flush();
   }

   public static void copy(InputStream var0, OutputStream var1) {
      copy(var0, var1, 2048);
   }

   public static void copyThenClose(InputStream var0, OutputStream var1) {
      copy(var0, var1);
      var0.close();
      var1.close();
   }
}
