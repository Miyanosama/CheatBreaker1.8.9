package io.netty.util.internal.logging;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.BlockBush;

public class MessageFormatter {
   public static final String DELIM_STR = "{}";
   public static final char DELIM_START = 123;
   public static final char DELIM_STOP = 125;
   public static final char ESCAPE_CHAR = 92;

   public static void objectArrayAppend(StringBuffer var0, Object[] var1, Map<Object[], Void> var2) {
      var0.append('[');
      if (!var2.containsKey(var1)) {
         var2.put(var1, null);
         int var3 = var1.length;

         for (int var4 = 0; var4 < var3; var4++) {
            deeplyAppendParameter(var0, var1[var4], var2);
            if (var4 != var3 - 1) {
               var0.append(", ");
            }
         }

         var2.remove(var1);
      } else {
         var0.append("...");
      }

      var0.append(']');
   }

   public static boolean isDoubleEscaped(String var0, int var1) {
      return var1 >= 2 && var0.charAt(var1 - 2) == '\\';
   }

   public static FormattingTuple arrayFormat(String var0, Object[] var1) {
      Throwable var2 = getThrowableCandidate(var1);
      if (var0 == null) {
         return new FormattingTuple(null, var1, var2);
      } else if (var1 == null) {
         return new FormattingTuple(var0);
      } else {
         int var3 = 0;
         StringBuffer var5 = new StringBuffer(var0.length() + 50);

         int var6;
         for (var6 = 0; var6 < var1.length; var6++) {
            int var4 = var0.indexOf("{}", var3);
            if (var4 == -1) {
               if (var3 == 0) {
                  return new FormattingTuple(var0, var1, var2);
               }

               var5.append(var0.substring(var3, var0.length()));
               return new FormattingTuple(var5.toString(), var1, var2);
            }

            if (isEscapedDelimeter(var0, var4)) {
               if (!isDoubleEscaped(var0, var4)) {
                  var6--;
                  var5.append(var0.substring(var3, var4 - 1));
                  var5.append('{');
                  var3 = var4 + 1;
               } else {
                  var5.append(var0.substring(var3, var4 - 1));
                  deeplyAppendParameter(var5, var1[var6], new HashMap<>());
                  var3 = var4 + 2;
               }
            } else {
               var5.append(var0.substring(var3, var4));
               deeplyAppendParameter(var5, var1[var6], new HashMap<>());
               var3 = var4 + 2;
            }
         }

         var5.append(var0.substring(var3, var0.length()));
         return var6 < var1.length - 1 ? new FormattingTuple(var5.toString(), var1, var2) : new FormattingTuple(var5.toString(), var1, null);
      }
   }

   public static void intArrayAppend(StringBuffer var0, int[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void doubleArrayAppend(StringBuffer var0, double[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void shortArrayAppend(StringBuffer var0, short[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void floatArrayAppend(StringBuffer var0, float[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void safeObjectAppend(StringBuffer var0, Object var1) {
      try {
         String var2 = var1.toString();
         var0.append(var2);
      } catch (Throwable var3) {
         System.err.println("SLF4J: Failed toString() invocation on an object of type [" + var1.getClass().getName() + ']');
         var3.printStackTrace();
         var0.append("[FAILED toString()]");
      }
   }

   public static void deeplyAppendParameter(StringBuffer var0, Object var1, Map<Object[], Void> var2) {
      if (var1 == null) {
         var0.append("null");
      } else {
         if (!var1.getClass().isArray()) {
            safeObjectAppend(var0, var1);
         } else if (var1 instanceof boolean[]) {
            booleanArrayAppend(var0, (boolean[])var1);
         } else if (var1 instanceof byte[]) {
            byteArrayAppend(var0, (byte[])var1);
         } else if (var1 instanceof char[]) {
            charArrayAppend(var0, (char[])var1);
         } else if (var1 instanceof short[]) {
            shortArrayAppend(var0, (short[])var1);
         } else if (var1 instanceof int[]) {
            intArrayAppend(var0, (int[])var1);
         } else if (var1 instanceof long[]) {
            longArrayAppend(var0, (long[])var1);
         } else if (var1 instanceof float[]) {
            floatArrayAppend(var0, (float[])var1);
         } else if (var1 instanceof double[]) {
            doubleArrayAppend(var0, (double[])var1);
         } else {
            objectArrayAppend(var0, (Object[])var1, var2);
         }
      }
   }

   public static void charArrayAppend(StringBuffer var0, char[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void longArrayAppend(StringBuffer var0, long[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static void booleanArrayAppend(StringBuffer var0, boolean[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static boolean isEscapedDelimeter(String var0, int var1) {
      return var1 == 0 ? false : var0.charAt(var1 - 1) == '\\';
   }

   public static Throwable getThrowableCandidate(Object[] var0) {
      if (var0 != null && var0.length != 0) {
         Object var1 = var0[var0.length - 1];
         return var1 instanceof Throwable ? (Throwable)var1 : null;
      } else {
         return null;
      }
   }

   public static void byteArrayAppend(StringBuffer var0, byte[] var1) {
      var0.append('[');
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var0.append(var1[var3]);
         if (var3 != var2 - 1) {
            var0.append(", ");
         }
      }

      var0.append(']');
   }

   public static FormattingTuple format(String var0, Object var1) {
      return arrayFormat(var0, new Object[]{var1});
   }

   public static FormattingTuple format(String var0, Object var1, Object var2) {
      return arrayFormat(var0, new Object[]{var1, var2});
   }
}
