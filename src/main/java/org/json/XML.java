package org.json;

import java.util.Map.Entry;
import org.json.UnicodeCodePoints;

public class XML {
   public static Character AMP = '&';
   public static Character APOS = '\'';
   public static Character BANG = '!';
   public static Character EQ = '=';
   public static Character GT = '>';
   public static Character LT = '<';
   public static Character QUEST = '?';
   public static Character QUOT = '"';
   public static Character SLASH = '/';

   public static boolean method_04260(int var0) {
      return Character.isISOControl(var0) && var0 != 9 && var0 != 10 && var0 != 13
         || (var0 < 32 || var0 > 55295) && (var0 < 57344 || var0 > 65533) && (var0 < 65536 || var0 > 1114111);
   }

   public static String method_04264(String var0) {
      StringBuilder var1 = new StringBuilder(var0.length());
      int var2 = 0;

      for (int var3 = var0.length(); var2 < var3; var2++) {
         char var4 = var0.charAt(var2);
         if (var4 == '&') {
            int var5 = var0.indexOf(59, var2);
            if (var5 > var2) {
               String var6 = var0.substring(var2 + 1, var5);
               var1.append(XMLTokener.method_11368(var6));
               var2 += var6.length() + 1;
            } else {
               var1.append(var4);
            }
         } else {
            var1.append(var4);
         }
      }

      return var1.toString();
   }

   public static String method_04267(String var0) {
      StringBuilder var1 = new StringBuilder(var0.length());

      for (int var3 : method_04257(var0)) {
         switch (var3) {
            case 34:
               var1.append("&quot;");
               break;
            case 38:
               var1.append("&amp;");
               break;
            case 39:
               var1.append("&apos;");
               break;
            case 60:
               var1.append("&lt;");
               break;
            case 62:
               var1.append("&gt;");
               break;
            default:
               if (method_04260(var3)) {
                  var1.append("&#x");
                  var1.append(Integer.toHexString(var3));
                  var1.append(';');
               } else {
                  var1.appendCodePoint(var3);
               }
         }
      }

      return var1.toString();
   }

   public static JSONObject toJSONObject(String var0) throws org.json.JSONException {
      return toJSONObject(var0, false);
   }

   public static Object stringToValue(String var0) {
      return JSONObject.stringToValue(var0);
   }

   public static JSONObject toJSONObject(String var0, boolean var1) throws org.json.JSONException {
      JSONObject var2 = new JSONObject();
      XMLTokener var3 = new XMLTokener(var0);

      while (var3.more() && var3.skipPast("<")) {
         parse(var3, var2, null, var1);
      }

      return var2;
   }

   public static Iterable<Integer> method_04257(String var0) {
      return new UnicodeCodePoints(var0);
   }

   public static String toString(Object var0, String var1) throws org.json.JSONException {
      StringBuilder var2 = new StringBuilder();
      if (var0 instanceof JSONObject) {
         if (var1 != null) {
            var2.append('<');
            var2.append(var1);
            var2.append('>');
         }

         JSONObject var4 = (JSONObject)var0;

         for (Entry var16 : var4.entrySet()) {
            String var8 = (String)var16.getKey();
            Object var9 = var16.getValue();
            if (var9 == null) {
               var9 = "";
            } else if (var9.getClass().isArray()) {
               var9 = new JSONArray(var9);
            }

            if ("content".equals(var8)) {
               if (var9 instanceof JSONArray) {
                  JSONArray var14 = (JSONArray)var9;
                  int var17 = 0;

                  for (Object var12 : var14) {
                     if (var17 > 0) {
                        var2.append('\n');
                     }

                     var2.append(method_04267(var12.toString()));
                     var17++;
                  }
               } else {
                  var2.append(method_04267(var9.toString()));
               }
            } else if (var9 instanceof JSONArray) {
               for (Object var11 : (JSONArray)var9) {
                  if (var11 instanceof JSONArray) {
                     var2.append('<');
                     var2.append(var8);
                     var2.append('>');
                     var2.append(toString(var11));
                     var2.append("</");
                     var2.append(var8);
                     var2.append('>');
                  } else {
                     var2.append(toString(var11, var8));
                  }
               }
            } else if ("".equals(var9)) {
               var2.append('<');
               var2.append(var8);
               var2.append("/>");
            } else {
               var2.append(toString(var9, var8));
            }
         }

         if (var1 != null) {
            var2.append("</");
            var2.append(var1);
            var2.append('>');
         }

         return var2.toString();
      } else if (var0 == null || !(var0 instanceof JSONArray) && !var0.getClass().isArray()) {
         String var5 = var0 == null ? "null" : method_04267(var0.toString());
         return var1 == null ? "\"" + var5 + "\"" : (var5.length() == 0 ? "<" + var1 + "/>" : "<" + var1 + ">" + var5 + "</" + var1 + ">");
      } else {
         JSONArray var3;
         if (var0.getClass().isArray()) {
            var3 = new JSONArray(var0);
         } else {
            var3 = (JSONArray)var0;
         }

         for (Object var7 : var3) {
            var2.append(toString(var7, var1 == null ? "array" : var1));
         }

         return var2.toString();
      }
   }

   public static boolean parse(XMLTokener var0, JSONObject var1, String var2, boolean var3) throws org.json.JSONException {
      JSONObject var6 = null;
      Object var9 = var0.nextToken();
      if (var9 == BANG) {
         char var4 = var0.next();
         if (var4 == '-') {
            if (var0.next() == '-') {
               var0.skipPast("-->");
               return false;
            }

            var0.back();
         } else if (var4 == '[') {
            var9 = var0.nextToken();
            if ("CDATA".equals(var9) && var0.next() == '[') {
               String var12 = var0.nextCDATA();
               if (var12.length() > 0) {
                  var1.accumulate("content", var12);
               }

               return false;
            }

            throw var0.syntaxError("Expected 'CDATA['");
         }

         int var5 = 1;

         do {
            var9 = var0.nextMeta();
            if (var9 == null) {
               throw var0.syntaxError("Missing '>' after '<!'.");
            }

            if (var9 == LT) {
               var5++;
            } else if (var9 == GT) {
               var5--;
            }
         } while (var5 > 0);

         return false;
      } else if (var9 == QUEST) {
         var0.skipPast("?>");
         return false;
      } else if (var9 == SLASH) {
         var9 = var0.nextToken();
         if (var2 == null) {
            throw var0.syntaxError("Mismatched close tag " + var9);
         } else if (!var9.equals(var2)) {
            throw var0.syntaxError("Mismatched " + var2 + " and " + var9);
         } else if (var0.nextToken() != GT) {
            throw var0.syntaxError("Misshaped close tag");
         } else {
            return true;
         }
      } else if (var9 instanceof Character) {
         throw var0.syntaxError("Misshaped tag");
      } else {
         String var8 = (String)var9;
         var9 = null;
         var6 = new JSONObject();

         while (true) {
            if (var9 == null) {
               var9 = var0.nextToken();
            }

            if (!(var9 instanceof String)) {
               if (var9 == SLASH) {
                  if (var0.nextToken() != GT) {
                     throw var0.syntaxError("Misshaped tag");
                  }

                  if (var6.length() > 0) {
                     var1.accumulate(var8, var6);
                  } else {
                     var1.accumulate(var8, "");
                  }

                  return false;
               }

               if (var9 != GT) {
                  throw var0.syntaxError("Misshaped tag");
               }

               while (true) {
                  var9 = var0.nextContent();
                  if (var9 == null) {
                     if (var8 != null) {
                        throw var0.syntaxError("Unclosed tag " + var8);
                     }

                     return false;
                  }

                  if (var9 instanceof String) {
                     String var11 = (String)var9;
                     if (var11.length() > 0) {
                        var6.accumulate("content", var3 ? var11 : stringToValue(var11));
                     }
                  } else if (var9 == LT && parse(var0, var6, var8, var3)) {
                     if (var6.length() == 0) {
                        var1.accumulate(var8, "");
                     } else if (var6.length() == 1 && var6.opt("content") != null) {
                        var1.accumulate(var8, var6.opt("content"));
                     } else {
                        var1.accumulate(var8, var6);
                     }

                     return false;
                  }
               }
            }

            String var7 = (String)var9;
            var9 = var0.nextToken();
            if (var9 == EQ) {
               var9 = var0.nextToken();
               if (!(var9 instanceof String)) {
                  throw var0.syntaxError("Missing value");
               }

               var6.accumulate(var7, var3 ? (String)var9 : stringToValue((String)var9));
               var9 = null;
            } else {
               var6.accumulate(var7, "");
            }
         }
      }
   }

   public static void noSpace(String var0) throws org.json.JSONException {
      int var2 = var0.length();
      if (var2 == 0) {
         throw new JSONException("Empty string.");
      } else {
         for (int var1 = 0; var1 < var2; var1++) {
            if (Character.isWhitespace(var0.charAt(var1))) {
               throw new JSONException("'" + var0 + "' contains a space character.");
            }
         }
      }
   }

   public static String toString(Object var0) throws org.json.JSONException {
      return toString(var0, null);
   }
}
