package net.minecraft.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnumChatFormatting {
      BLACK("BLACK", '0', 0),
      DARK_BLUE("DARK_BLUE", '1', 1),
      DARK_GREEN("DARK_GREEN", '2', 2),
      DARK_AQUA("DARK_AQUA", '3', 3),
      DARK_RED("DARK_RED", '4', 4),
      DARK_PURPLE("DARK_PURPLE", '5', 5),
      GOLD("GOLD", '6', 6),
      GRAY("GRAY", '7', 7),
      DARK_GRAY("DARK_GRAY", '8', 8),
      BLUE("BLUE", '9', 9),
      GREEN("GREEN", 'a', 10),
      AQUA("AQUA", 'b', 11),
      RED("RED", 'c', 12),
      LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13),
      YELLOW("YELLOW", 'e', 14),
      WHITE("WHITE", 'f', 15),
      OBFUSCATED("OBFUSCATED", 'k', true),
      BOLD("BOLD", 'l', true),
      STRIKETHROUGH("STRIKETHROUGH", 'm', true),
      UNDERLINE("UNDERLINE", 'n', true),
      ITALIC("ITALIC", 'o', true),
      RESET("RESET", 'r', -1);
   public boolean fancyStyling;
   public char formattingCode;
   public static EnumChatFormatting[] recoveredField1921 = new EnumChatFormatting[]{
      BLACK,
      DARK_BLUE,
      DARK_GREEN,
      EnumChatFormatting.DARK_AQUA,
      DARK_RED,
      EnumChatFormatting.DARK_PURPLE,
      EnumChatFormatting.GOLD,
      EnumChatFormatting.GRAY,
      DARK_GRAY,
      BLUE,
      EnumChatFormatting.GREEN,
      AQUA,
      RED,
      EnumChatFormatting.LIGHT_PURPLE,
      EnumChatFormatting.YELLOW,
      WHITE,
      EnumChatFormatting.OBFUSCATED,
      BOLD,
      EnumChatFormatting.STRIKETHROUGH,
      UNDERLINE,
      ITALIC,
      RESET
   };
   public int colorIndex;
   public String controlString;
   public static Map<String, EnumChatFormatting> recoveredField1923 = Maps.newHashMap();
   public static Pattern formattingCodePattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   public static Pattern recoveredField1920 = Pattern.compile("(?i)§[0-9A-FR]");
   public String name;

   EnumChatFormatting(String var3, char var4, int var5) {
      this(var3, var4, false, var5);
   }

   public boolean isColor() {
      return !this.fancyStyling && this != RESET;
   }

   public static String func_175745_c(String var0) {
      return var0.toLowerCase().replaceAll("[^a-z]", "");
   }

   public static EnumChatFormatting getValueByName(String var0) {
      return var0 == null ? null : recoveredField1923.get(func_175745_c(var0));
   }

   @Override
   public String toString() {
      return this.controlString;
   }

   public static Collection<String> getValidValues(boolean var0, boolean var1) {
      ArrayList var2 = Lists.newArrayList();

      for (EnumChatFormatting var6 : values()) {
         if ((!var6.isColor() || var0) && (!var6.isFancyStyling() || var1)) {
            var2.add(var6.getFriendlyName());
         }
      }

      return var2;
   }

   public static String getTextWithoutFormattingCodes(String var0) {
      return var0 == null ? null : formattingCodePattern.matcher(var0).replaceAll("");
   }

   EnumChatFormatting(String var3, char var4, boolean var5, int var6) {
      this.name = var3;
      this.formattingCode = var4;
      this.fancyStyling = var5;
      this.colorIndex = var6;
      this.controlString = "§" + var4;
   }

   public static String method_09748(String var0) {
      Matcher var1 = recoveredField1920.matcher(var0);
      String var2 = "";

      while (var1.find()) {
         var2 = var1.group();
      }

      return var2;
   }

   public boolean isFancyStyling() {
      return this.fancyStyling;
   }

   static {
      for (EnumChatFormatting var3 : values()) {
         recoveredField1923.put(func_175745_c(var3.name), var3);
      }
   }

   public int getColorIndex() {
      return this.colorIndex;
   }

   public static EnumChatFormatting func_175744_a(int var0) {
      if (var0 < 0) {
         return RESET;
      } else {
         for (EnumChatFormatting var4 : values()) {
            if (var4.getColorIndex() == var0) {
               return var4;
            }
         }

         return null;
      }
   }

   EnumChatFormatting(String var3, char var4, boolean var5) {
      this(var3, var4, var5, -1);
   }

   public String getFriendlyName() {
      return this.name().toLowerCase();
   }
}
