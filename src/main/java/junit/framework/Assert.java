package junit.framework;

public class Assert {
   public static void assertNull(Object var0) {
      assertNull(null, var0);
   }

   public static void failNotEquals(String var0, Object var1, Object var2) {
      fail(format(var0, var1, var2));
   }

   public static void assertEquals(double var0, double var2, double var4) {
      assertEquals(null, var0, var2, var4);
   }

   public static void assertEquals(long var0, long var2) {
      assertEquals(null, var0, var2);
   }

   public static void assertEquals(char var0, char var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(String var0, Object var1, Object var2) {
      if (var1 != null || var2 != null) {
         if (var1 == null || !var1.equals(var2)) {
            failNotEquals(var0, var1, var2);
         }
      }
   }

   public static void assertEquals(String var0, String var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(String var0, boolean var1, boolean var2) {
      assertEquals(var0, (Object)var1, (Object)Boolean.valueOf(var2));
   }

   public static void assertNotNull(String var0, Object var1) {
      assertTrue(var0, var1 != null);
   }

   public static void assertEquals(short var0, short var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(float var0, float var1, float var2) {
      assertEquals(null, var0, var1, var2);
   }

   public static void assertSame(Object var0, Object var1) {
      assertSame(null, var0, var1);
   }

   public static void assertNotNull(Object var0) {
      assertNotNull(null, var0);
   }

   public static void assertEquals(String var0, double var1, double var3, double var5) {
      if (Double.compare(var1, var3) != 0) {
         if (!(Math.abs(var1 - var3) <= var5)) {
            failNotEquals(var0, new Double(var1), new Double(var3));
         }
      }
   }

   public static void assertSame(String var0, Object var1, Object var2) {
      if (var1 != var2) {
         failNotSame(var0, var1, var2);
      }
   }

   public static void assertEquals(boolean var0, boolean var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(Object var0, Object var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertTrue(boolean var0) {
      assertTrue(null, var0);
   }

   public static void assertEquals(String var0, long var1, long var3) {
      assertEquals(var0, new Long(var1), new Long(var3));
   }

   public static void assertNotSame(Object var0, Object var1) {
      assertNotSame(null, var0, var1);
   }

   public static void assertFalse(boolean var0) {
      assertFalse(null, var0);
   }

   public static String format(String var0, Object var1, Object var2) {
      String var3 = "";
      if (var0 != null) {
         var3 = var0 + " ";
      }

      return var3 + "expected:<" + var1 + "> but was:<" + var2 + ">";
   }

   public static void assertEquals(String var0, char var1, char var2) {
      assertEquals(var0, new Character(var1), new Character(var2));
   }

   public static void fail(String var0) {
      throw new AssertionFailedError(var0);
   }

   public static void fail() {
      fail(null);
   }

   public static void assertTrue(String var0, boolean var1) {
      if (!var1) {
         fail(var0);
      }
   }

   public static void assertEquals(String var0, float var1, float var2, float var3) {
      if (Float.isInfinite(var1)) {
         if (var1 != var2) {
            failNotEquals(var0, new Float(var1), new Float(var2));
         }
      } else if (!(Math.abs(var1 - var2) <= var3)) {
         failNotEquals(var0, new Float(var1), new Float(var2));
      }
   }

   public static void assertEquals(String var0, int var1, int var2) {
      assertEquals(var0, new Integer(var1), new Integer(var2));
   }

   public static void failSame(String var0) {
      String var1 = "";
      if (var0 != null) {
         var1 = var0 + " ";
      }

      fail(var1 + "expected not same");
   }

   public static void assertEquals(int var0, int var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(String var0, String var1, String var2) {
      if (var1 != null || var2 != null) {
         if (var1 == null || !var1.equals(var2)) {
            throw new ComparisonFailure(var0, var1, var2);
         }
      }
   }

   public static void assertFalse(String var0, boolean var1) {
      assertTrue(var0, !var1);
   }

   public static void assertNull(String var0, Object var1) {
      assertTrue(var0, var1 == null);
   }

   public static void failNotSame(String var0, Object var1, Object var2) {
      String var3 = "";
      if (var0 != null) {
         var3 = var0 + " ";
      }

      fail(var3 + "expected same:<" + var1 + "> was not:<" + var2 + ">");
   }

   public static void assertEquals(String var0, short var1, short var2) {
      assertEquals(var0, new Short(var1), new Short(var2));
   }

   public static void assertEquals(byte var0, byte var1) {
      assertEquals(null, var0, var1);
   }

   public static void assertEquals(String var0, byte var1, byte var2) {
      assertEquals(var0, new Byte(var1), new Byte(var2));
   }

   public static void assertNotSame(String var0, Object var1, Object var2) {
      if (var1 == var2) {
         failSame(var0);
      }
   }
}
