package net.optifine.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StrUtils {
   public static int indexOfMaskSingle(String var0, String var1, int var2, char var3) {
      if (var0 != null && var1 != null) {
         if (var2 >= 0 && var2 <= var0.length()) {
            if (var0.length() < var2 + var1.length()) {
               return -1;
            } else {
               for (int var4 = var2; var4 + var1.length() <= var0.length(); var4++) {
                  String var5 = var0.substring(var4, var4 + var1.length());
                  if (method_10285(var5, var1, var3)) {
                     return var4;
                  }
               }

               return -1;
            }
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public static boolean equals(char var0, String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         if (var1.charAt(var2) == var0) {
            return true;
         }
      }

      return false;
   }

   public static String[] remove(String[] var0, int var1, int var2) {
      if (var0 == null) {
         return var0;
      } else if (var2 > 0 && var1 < var0.length) {
         if (var1 >= var2) {
            return var0;
         } else {
            ArrayList var3 = new ArrayList(var0.length);

            for (int var4 = 0; var4 < var0.length; var4++) {
               String var5 = var0[var4];
               if (var4 < var1 || var4 >= var2) {
                  var3.add(var5);
               }
            }

            return (java.lang.String[])var3.toArray(new String[var3.size()]);
         }
      } else {
         return var0;
      }
   }

   public static String method_10273(String var0, String var1) {
      if (var0 != null && var1 != null) {
         int var2 = var0.length();

         int var3;
         for (var3 = var2; var3 > 0; var3--) {
            char var4 = var0.charAt(var3 - 1);
            if (var1.indexOf(var4) < 0) {
               break;
            }
         }

         return var3 == var2 ? var0 : var0.substring(0, var3);
      } else {
         return var0;
      }
   }

   public static String trim(String var0, String var1) {
      if (var0 != null && var1 != null) {
         var0 = method_10262(var0, var1);
         return method_10273(var0, var1);
      } else {
         return var0;
      }
   }

   public static boolean isEmpty(String var0) {
      return var0 == null ? true : var0.trim().length() <= 0;
   }

   public static String[] split(String var0, String var1) {
      if (var0 != null && var0.length() > 0) {
         if (var1 == null) {
            return new String[]{var0};
         } else {
            ArrayList var2 = new ArrayList();
            int var3 = 0;

            for (int var4 = 0; var4 < var0.length(); var4++) {
               char var5 = var0.charAt(var4);
               if (equals(var5, var1)) {
                  var2.add(var0.substring(var3, var4));
                  var3 = var4 + 1;
               }
            }

            var2.add(var0.substring(var3, var0.length()));
            return (java.lang.String[])var2.toArray(new String[var2.size()]);
         }
      } else {
         return new String[0];
      }
   }

   public static String removePrefixSuffix(String var0, String[] var1, String[] var2) {
      var0 = removePrefix(var0, var1);
      return removeSuffix(var0, var2);
   }

   public static boolean method_10274(String var0, String var1, char var2) {
      if (var0 != null && var1 != null) {
         if (var0.length() < var1.length()) {
            return false;
         } else {
            String var3 = var0.substring(var0.length() - var1.length(), var0.length());
            return method_10285(var3, var1, var2);
         }
      } else {
         return var0 == var1;
      }
   }

   public static String removeSuffix(String var0, String[] var1) {
      if (var0 != null && var1 != null) {
         int var2 = var0.length();

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            var0 = removeSuffix(var0, var4);
            if (var0.length() != var2) {
               break;
            }
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static boolean method_10291(String var0, String var1, char var2) {
      if (var0 != null && var1 != null) {
         if (var0.length() < var1.length()) {
            return false;
         } else {
            String var3 = var0.substring(0, var1.length());
            return method_10285(var3, var1, var2);
         }
      } else {
         return var0 == var1;
      }
   }

   public static int method_10282(String[] var0, String var1) {
      if (var0 != null && var1 != null) {
         for (int var2 = 0; var2 < var0.length; var2++) {
            String var3 = var0[var2];
            if (var3.startsWith(var1)) {
               return var2;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static String addIfNotContains(String var0, String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         if (var0.indexOf(var1.charAt(var2)) < 0) {
            var0 = var0 + var1.charAt(var2);
         }
      }

      return var0;
   }

   public static String stringInc(String var0) {
      int var1 = parseInt(var0, -1);
      if (var1 == -1) {
         return "";
      } else {
         String var2 = "" + ++var1;
         return var2.length() > var0.length() ? "" : fillLeft("" + var1, var0.length(), '0');
      }
   }

   public static boolean equalsTrim(String var0, String var1) {
      if (var0 != null) {
         var0 = var0.trim();
      }

      if (var1 != null) {
         var1 = var1.trim();
      }

      return equals(var0, var1);
   }

   public static boolean equalsMask(String var0, String var1, char var2, char var3) {
      if (var1 != null && var0 != null) {
         if (var1.indexOf(var2) < 0) {
            return var1.indexOf(var3) < 0 ? var1.equals(var0) : method_10285(var0, var1, var3);
         } else {
            ArrayList var4 = new ArrayList();
            String var5 = "" + var2;
            if (var1.startsWith(var5)) {
               var4.add("");
            }

            StringTokenizer var6 = new StringTokenizer(var1, var5);

            while (var6.hasMoreElements()) {
               var4.add(var6.nextToken());
            }

            if (var1.endsWith(var5)) {
               var4.add("");
            }

            String var7 = (String)var4.get(0);
            if (!method_10291(var0, var7, var3)) {
               return false;
            } else {
               String var8 = (String)var4.get(var4.size() - 1);
               if (!method_10274(var0, var8, var3)) {
                  return false;
               } else {
                  int var9 = 0;

                  for (int var10 = 0; var10 < var4.size(); var10++) {
                     String var11 = (String)var4.get(var10);
                     if (var11.length() > 0) {
                        int var12 = indexOfMaskSingle(var0, var11, var9, var3);
                        if (var12 < 0) {
                           return false;
                        }

                        var9 = var12 + var11.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return var1 == var0;
      }
   }

   public static boolean isFilled(String var0) {
      return !isEmpty(var0);
   }

   public static String addSuffixCheck(String var0, String var1) {
      return var0 == null || var1 == null ? var0 : (var0.endsWith(var1) ? var0 : var0 + var1);
   }

   public static boolean startsWith(String var0, String[] var1) {
      if (var0 == null) {
         return false;
      } else if (var1 == null) {
         return false;
      } else {
         for (int var2 = 0; var2 < var1.length; var2++) {
            String var3 = var1[var2];
            if (var0.startsWith(var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public static String replacePrefix(String var0, String var1, String var2) {
      if (var0 == null || var1 == null) {
         return var0;
      } else if (!var0.startsWith(var1)) {
         return var0;
      } else {
         if (var2 == null) {
            var2 = "";
         }

         var0 = var0.substring(var1.length());
         return var2 + var0;
      }
   }

   public static String fillRight(String var0, int var1, char var2) {
      if (var0 == null) {
         var0 = "";
      }

      if (var0.length() >= var1) {
         return var0;
      } else {
         StringBuffer var3 = new StringBuffer(var0);

         while (var3.length() < var1) {
            var3.append(var2);
         }

         return var3.toString();
      }
   }

   public static String method_10295(String var0, String var1) {
      return var0 == null || var1 == null ? var0 : (var0.endsWith(var1) ? var0 : var1 + var0);
   }

   public static int parseInt(String var0, int var1) {
      if (var0 == null) {
         return var1;
      } else {
         try {
            return Integer.parseInt(var0);
         } catch (NumberFormatException var3) {
            return var1;
         }
      }
   }

   public static String method_10292(String var0, String var1, String var2) {
      if (var0 != null && var1 != null && var2 != null) {
         int var3 = var0.indexOf(var1);
         if (var3 < 0) {
            return null;
         } else {
            int var4 = var0.indexOf(var2, var3);
            return var4 < 0 ? null : var0.substring(var3, var4 + var2.length());
         }
      } else {
         return null;
      }
   }

   public static boolean equals(Object var0, Object var1) {
      return var0 == var1 ? true : (var0 != null && var0.equals(var1) ? true : var1 != null && var1.equals(var0));
   }

   public static boolean endsWith(String var0, String[] var1) {
      if (var0 == null) {
         return false;
      } else if (var1 == null) {
         return false;
      } else {
         for (int var2 = 0; var2 < var1.length; var2++) {
            String var3 = var1[var2];
            if (var0.endsWith(var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean equalsMask(String var0, String var1, char var2) {
      if (var1 != null && var0 != null) {
         if (var1.indexOf(var2) < 0) {
            return var1.equals(var0);
         } else {
            ArrayList var3 = new ArrayList();
            String var4 = "" + var2;
            if (var1.startsWith(var4)) {
               var3.add("");
            }

            StringTokenizer var5 = new StringTokenizer(var1, var4);

            while (var5.hasMoreElements()) {
               var3.add(var5.nextToken());
            }

            if (var1.endsWith(var4)) {
               var3.add("");
            }

            String var6 = (String)var3.get(0);
            if (!var0.startsWith(var6)) {
               return false;
            } else {
               String var7 = (String)var3.get(var3.size() - 1);
               if (!var0.endsWith(var7)) {
                  return false;
               } else {
                  int var8 = 0;

                  for (int var9 = 0; var9 < var3.size(); var9++) {
                     String var10 = (String)var3.get(var9);
                     if (var10.length() > 0) {
                        int var11 = var0.indexOf(var10, var8);
                        if (var11 < 0) {
                           return false;
                        }

                        var8 = var11 + var10.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return var1 == var0;
      }
   }

   public static String removeSuffix(String var0, String var1) {
      if (var0 != null && var1 != null) {
         if (var0.endsWith(var1)) {
            var0 = var0.substring(0, var0.length() - var1.length());
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static String replaceSuffix(String var0, String var1, String var2) {
      if (var0 == null || var1 == null) {
         return var0;
      } else if (!var0.endsWith(var1)) {
         return var0;
      } else {
         if (var2 == null) {
            var2 = "";
         }

         var0 = var0.substring(0, var0.length() - var1.length());
         return var0 + var2;
      }
   }

   public static boolean method_10285(String var0, String var1, char var2) {
      if (var0 != null && var1 != null) {
         if (var0.length() != var1.length()) {
            return false;
         } else {
            for (int var3 = 0; var3 < var1.length(); var3++) {
               char var4 = var1.charAt(var3);
               if (var4 != var2 && var0.charAt(var3) != var4) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return var0 == var1;
      }
   }

   public static String method_10262(String var0, String var1) {
      if (var0 != null && var1 != null) {
         int var2 = var0.length();

         for (int var3 = 0; var3 < var2; var3++) {
            char var4 = var0.charAt(var3);
            if (var1.indexOf(var4) < 0) {
               return var0.substring(var3);
            }
         }

         return "";
      } else {
         return var0;
      }
   }

   public static String fillLeft(String var0, int var1, char var2) {
      if (var0 == null) {
         var0 = "";
      }

      if (var0.length() >= var1) {
         return var0;
      } else {
         StringBuffer var3 = new StringBuffer();
         int var4 = var1 - var0.length();

         while (var3.length() < var4) {
            var3.append(var2);
         }

         return var3.toString() + var0;
      }
   }

   public static String removePrefixSuffix(String var0, String var1, String var2) {
      return removePrefixSuffix(var0, new String[]{var1}, new String[]{var2});
   }

   public static String removePrefix(String var0, String[] var1) {
      if (var0 != null && var1 != null) {
         int var2 = var0.length();

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            var0 = removePrefix(var0, var4);
            if (var0.length() != var2) {
               break;
            }
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static String removePrefix(String var0, String var1) {
      if (var0 != null && var1 != null) {
         if (var0.startsWith(var1)) {
            var0 = var0.substring(var1.length());
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static boolean equalsMask(String var0, String[] var1, char var2) {
      for (int var3 = 0; var3 < var1.length; var3++) {
         String var4 = var1[var3];
         if (equalsMask(var0, var4, var2)) {
            return true;
         }
      }

      return false;
   }

   public static int method_10294(String[] var0, String var1) {
      if (var0 != null && var1 != null) {
         for (int var2 = 0; var2 < var0.length; var2++) {
            String var3 = var0[var2];
            if (var3.endsWith(var1)) {
               return var2;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }
}
