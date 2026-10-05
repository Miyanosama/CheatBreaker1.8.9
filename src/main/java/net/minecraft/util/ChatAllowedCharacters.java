package net.minecraft.util;

public class ChatAllowedCharacters {
   public static char[] allowedCharactersArray = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};

   public static String filterAllowedCharacters(String var0) {
      StringBuilder var1 = new StringBuilder();

      for (char var5 : var0.toCharArray()) {
         if (isAllowedCharacter(var5)) {
            var1.append(var5);
         }
      }

      return var1.toString();
   }

   public static boolean isAllowedCharacter(char var0) {
      return var0 != 167 && var0 >= ' ' && var0 != 127;
   }
}
