package org.json;

import io.netty.buffer.PooledByteBufAllocator;
import java.util.HashMap;
import net.minecraft.item.crafting.RecipesMapExtending;

public class XMLTokener extends JSONTokener {
   public RecipesMapExtending field_0001;
   public static HashMap<String, Character> entity = new HashMap<>(8);
   public PooledByteBufAllocator field_0000;

   public String nextCDATA() {
      StringBuilder var3 = new StringBuilder();

      while (this.more()) {
         char var1 = this.next();
         var3.append(var1);
         int var2 = var3.length() - 3;
         if (var2 >= 0 && var3.charAt(var2) == ']' && var3.charAt(var2 + 1) == ']' && var3.charAt(var2 + 2) == '>') {
            var3.setLength(var2);
            return var3.toString();
         }
      }

      throw this.syntaxError("Unclosed CDATA");
   }

   public XMLTokener(String var1) {
      super(var1);
   }

   public Object nextMeta() {
      char var1;
      do {
         var1 = this.next();
      } while (Character.isWhitespace(var1));

      switch (var1) {
         case '\u0000':
            throw this.syntaxError("Misshaped meta tag");
         case '!':
            return XML.BANG;
         case '"':
         case '\'':
            char var2 = var1;

            do {
               var1 = this.next();
               if (var1 == 0) {
                  throw this.syntaxError("Unterminated string");
               }
            } while (var1 != var2);

            return Boolean.TRUE;
         case '/':
            return XML.SLASH;
         case '<':
            return XML.LT;
         case '=':
            return XML.EQ;
         case '>':
            return XML.GT;
         case '?':
            return XML.QUEST;
         default:
            while (true) {
               var1 = this.next();
               if (Character.isWhitespace(var1)) {
                  return Boolean.TRUE;
               }

               switch (var1) {
                  case '\u0000':
                  case '!':
                  case '"':
                  case '\'':
                  case '/':
                  case '<':
                  case '=':
                  case '>':
                  case '?':
                     this.back();
                     return Boolean.TRUE;
               }
            }
      }
   }

   public Object nextEntity(char var1) {
      StringBuilder var2 = new StringBuilder();

      while (true) {
         char var3 = this.next();
         if (!Character.isLetterOrDigit(var3) && var3 != '#') {
            if (var3 == ';') {
               String var4 = var2.toString();
               return method_11368(var4);
            }

            throw this.syntaxError("Missing ';' in XML entity: &" + var2);
         }

         var2.append(Character.toLowerCase(var3));
      }
   }

   public Object nextToken() {
      char var1;
      do {
         var1 = this.next();
      } while (Character.isWhitespace(var1));

      switch (var1) {
         case '\u0000':
            throw this.syntaxError("Misshaped element");
         case '!':
            return XML.BANG;
         case '"':
         case '\'':
            char var2 = var1;
            StringBuilder var5 = new StringBuilder();

            while (true) {
               var1 = this.next();
               if (var1 == 0) {
                  throw this.syntaxError("Unterminated string");
               }

               if (var1 == var2) {
                  return var5.toString();
               }

               if (var1 == '&') {
                  var5.append(this.nextEntity(var1));
               } else {
                  var5.append(var1);
               }
            }
         case '/':
            return XML.SLASH;
         case '<':
            throw this.syntaxError("Misplaced '<'");
         case '=':
            return XML.EQ;
         case '>':
            return XML.GT;
         case '?':
            return XML.QUEST;
         default:
            StringBuilder var3 = new StringBuilder();

            while (true) {
               var3.append(var1);
               var1 = this.next();
               if (Character.isWhitespace(var1)) {
                  return var3.toString();
               }

               switch (var1) {
                  case '\u0000':
                     return var3.toString();
                  case '!':
                  case '/':
                  case '=':
                  case '>':
                  case '?':
                  case '[':
                  case ']':
                     this.back();
                     return var3.toString();
                  case '"':
                  case '\'':
                  case '<':
                     throw this.syntaxError("Bad character in a name");
               }
            }
      }
   }

   public boolean skipPast(String var1) {
      int var6 = 0;
      int var7 = var1.length();
      char[] var8 = new char[var7];

      for (int var4 = 0; var4 < var7; var4++) {
         char var3 = this.next();
         if (var3 == 0) {
            return false;
         }

         var8[var4] = var3;
      }

      while (true) {
         int var5 = var6;
         boolean var2 = true;

         for (int var10 = 0; var10 < var7; var10++) {
            if (var8[var5] != var1.charAt(var10)) {
               var2 = false;
               break;
            }

            if (++var5 >= var7) {
               var5 -= var7;
            }
         }

         if (var2) {
            return true;
         }

         char var9 = this.next();
         if (var9 == 0) {
            return false;
         }

         var8[var6] = var9;
         if (++var6 >= var7) {
            var6 -= var7;
         }
      }
   }

   public static String method_11368(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         if (var0.charAt(0) == '#') {
            int var2;
            if (var0.charAt(1) == 'x') {
               var2 = Integer.parseInt(var0.substring(2), 16);
            } else {
               var2 = Integer.parseInt(var0.substring(1));
            }

            return new String(new int[]{var2}, 0, 1);
         } else {
            Character var1 = entity.get(var0);
            return var1 == null ? '&' + var0 + ';' : var1.toString();
         }
      } else {
         return "";
      }
   }

   static {
      entity.put("amp", XML.AMP);
      entity.put("apos", XML.APOS);
      entity.put("gt", XML.GT);
      entity.put("lt", XML.LT);
      entity.put("quot", XML.QUOT);
   }

   public Object nextContent() {
      char var1;
      do {
         var1 = this.next();
      } while (Character.isWhitespace(var1));

      if (var1 == 0) {
         return null;
      } else if (var1 == '<') {
         return XML.LT;
      } else {
         StringBuilder var2;
         for (var2 = new StringBuilder(); var1 != 0; var1 = this.next()) {
            if (var1 == '<') {
               this.back();
               return var2.toString().trim();
            }

            if (var1 == '&') {
               var2.append(this.nextEntity(var1));
            } else {
               var2.append(var1);
            }
         }

         return var2.toString().trim();
      }
   }
}
