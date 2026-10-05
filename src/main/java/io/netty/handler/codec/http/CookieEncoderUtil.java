package io.netty.handler.codec.http;

import io.netty.util.internal.InternalThreadLocalMap;

public class CookieEncoderUtil {
   public static String stripTrailingSeparator(StringBuilder var0) {
      if (var0.length() > 0) {
         var0.setLength(var0.length() - 2);
      }

      return var0.toString();
   }

   public static StringBuilder stringBuilder() {
      return InternalThreadLocalMap.get().stringBuilder();
   }

   public static void addQuoted(StringBuilder var0, String var1, String var2) {
      if (var2 == null) {
         var2 = "";
      }

      var0.append(var1);
      var0.append('=');
      var0.append('"');
      var0.append(var2.replace("\\", "\\\\").replace("\"", "\\\""));
      var0.append('"');
      var0.append(';');
      var0.append(' ');
   }

   public static void add(StringBuilder var0, String var1, long var2) {
      var0.append(var1);
      var0.append('=');
      var0.append(var2);
      var0.append(';');
      var0.append(' ');
   }

   public static void add(StringBuilder var0, String var1, String var2) {
      if (var2 == null) {
         addQuoted(var0, var1, "");
      } else {
         for (int var3 = 0; var3 < var2.length(); var3++) {
            char var4 = var2.charAt(var3);
            switch (var4) {
               case '\t':
               case ' ':
               case '"':
               case '(':
               case ')':
               case ',':
               case '/':
               case ':':
               case ';':
               case '<':
               case '=':
               case '>':
               case '?':
               case '@':
               case '[':
               case '\\':
               case ']':
               case '{':
               case '}':
                  addQuoted(var0, var1, var2);
                  return;
            }
         }

         addUnquoted(var0, var1, var2);
      }
   }

   public static void addUnquoted(StringBuilder var0, String var1, String var2) {
      var0.append(var1);
      var0.append('=');
      var0.append(var2);
      var0.append(';');
      var0.append(' ');
   }
}
