package net.minecraft.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder$State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.world.WorldProvider;
import recovered.unidentified.UnidentifiedClass4330;

public enum EnumChatFormatting {
   BOLD("BOLD", 'l', true),
   WHITE("WHITE", 'f', 15),
   DARK_BLUE("DARK_BLUE", '1', 1),
   UNDERLINE("UNDERLINE", 'n', true),
   AQUA("AQUA", 'b', 11),
   RESET("RESET", 'r', -1),
   DARK_RED("DARK_RED", '4', 4),
   BLACK("BLACK", '0', 0),
   DARK_GREEN("DARK_GREEN", '2', 2),
   BLUE("BLUE", '9', 9),
   RED("RED", 'c', 12),
   ITALIC("ITALIC", 'o', true),
   DARK_GRAY("DARK_GRAY", '8', 8),
   field_0000("OBFUSCATED", 'k', true),
   LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13),
   DARK_PURPLE("DARK_PURPLE", '5', 5),
   GREEN("GREEN", 'a', 10),
   DARK_AQUA("DARK_AQUA", '3', 3),
   GOLD("GOLD", '6', 6),
   GRAY("GRAY", '7', 7),
   YELLOW("YELLOW", 'e', 14),
   field_0010("STRIKETHROUGH", 'm', true);
   public boolean fancyStyling;
   public UnidentifiedClass4330 field_0017;
   public char formattingCode;
   public SocksAuthResponseDecoder$State field_0024;
   public static Pattern field_0034 = Pattern.compile("(?i)§[0-9A-FR]");
   public int colorIndex;
   public EnumPlayerModelParts field_0022;
   public String controlString;
   // $VF: synthetic field
   public static EnumChatFormatting[] field_0001 = new EnumChatFormatting[]{
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
      EnumChatFormatting.field_0000,
      BOLD,
      EnumChatFormatting.field_0010,
      UNDERLINE,
      ITALIC,
      RESET
   };
   public static Pattern formattingCodePattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   public static Map<String, EnumChatFormatting> field_0025 = Maps.newHashMap();
   public String name;
   public WorldProvider field_0007;

   public EnumChatFormatting(String var3, char var4, int var5) {
      this(var3, var4, false, var5);
   }

   public boolean isColor() {
      return !this.fancyStyling && this != RESET;
   }

   public static String func_175745_c(String var0) {
      return var0.toLowerCase().replaceAll("[^a-z]", "");
   }

   public static EnumChatFormatting getValueByName(String var0) {
      return var0 == null ? null : field_0025.get(func_175745_c(var0));
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

   public EnumChatFormatting(String var3, char var4, boolean var5, int var6) {
      this.name = var3;
      this.formattingCode = var4;
      this.fancyStyling = var5;
      this.colorIndex = var6;
      this.controlString = "§" + var4;
   }

   public static String method_09748(String var0) {
      Matcher var1 = field_0034.matcher(var0);
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
         field_0025.put(func_175745_c(var3.name), var3);
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

   public EnumChatFormatting(String var3, char var4, boolean var5) {
      this(var3, var4, var5, -1);
   }

   public String getFriendlyName() {
      return this.name().toLowerCase();
   }
}
