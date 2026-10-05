package org.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONPointer {
   public static final String recoveredField2024 = "utf-8";
   public List<String> refTokens;

   public String escape(String var1) {
      return var1.replace("~", "~0").replace("/", "~1").replace("\\", "\\\\").replace("\"", "\\\"");
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("");

      for (String var3 : this.refTokens) {
         var1.append('/').append(this.escape(var3));
      }

      return var1.toString();
   }

   public Object readByIndexToken(Object var1, String var2) {
      try {
         int var3 = Integer.parseInt(var2);
         JSONArray var4 = (JSONArray)var1;
         if (var3 >= var4.length()) {
            throw new JSONPointerException(String.format("index %d is out of bounds - the array has %d elements", var3, var4.length()));
         } else {
            return var4.get(var3);
         }
      } catch (NumberFormatException var5) {
         throw new JSONPointerException(String.format("%s is not an array index", var2), var5);
      }
   }

   public String unescape(String var1) {
      return var1.replace("~1", "/").replace("~0", "~").replace("\\\"", "\"").replace("\\\\", "\\");
   }

   public static JSONPointer.Builder builder() {
      return new JSONPointer.Builder();
   }

   public String toURIFragment() {
      try {
         StringBuilder var1 = new StringBuilder("#");

         for (String var3 : this.refTokens) {
            var1.append('/').append(URLEncoder.encode(var3, "utf-8"));
         }

         return var1.toString();
      } catch (UnsupportedEncodingException var4) {
         throw new RuntimeException(var4);
      }
   }

   public JSONPointer(List<String> var1) {
      this.refTokens = new ArrayList<>(var1);
   }

   public Object queryFrom(Object var1) {
      if (this.refTokens.isEmpty()) {
         return var1;
      } else {
         Object var2 = var1;

         for (String var4 : this.refTokens) {
            if (var2 instanceof JSONObject) {
               var2 = ((JSONObject)var2).opt(this.unescape(var4));
            } else {
               if (!(var2 instanceof JSONArray)) {
                  throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", var2, var4));
               }

               var2 = this.readByIndexToken(var2, var4);
            }
         }

         return var2;
      }
   }

   public JSONPointer(String var1) {
      if (var1 == null) {
         throw new NullPointerException("pointer cannot be null");
      } else if (var1.isEmpty() || var1.equals("#")) {
         this.refTokens = Collections.emptyList();
      } else {
         String var8;
         if (var1.startsWith("#/")) {
            var8 = var1.substring(2);

            try {
               var8 = URLDecoder.decode(var8, "utf-8");
            } catch (UnsupportedEncodingException var7) {
               throw new RuntimeException(var7);
            }
         } else {
            if (!var1.startsWith("/")) {
               throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
            }

            var8 = var1.substring(1);
         }

         this.refTokens = new ArrayList<>();

         for (String var6 : var8.split("/")) {
            this.refTokens.add(this.unescape(var6));
         }
      }
   }

   public static class Builder {
      public List<String> refTokens = new ArrayList<>();

      public JSONPointer.Builder append(int var1) {
         this.refTokens.add(String.valueOf(var1));
         return this;
      }

      public JSONPointer.Builder append(String var1) {
         if (var1 == null) {
            throw new NullPointerException("token cannot be null");
         } else {
            this.refTokens.add(var1);
            return this;
         }
      }

      public JSONPointer build() {
         return new JSONPointer(this.refTokens);
      }
   }
}
