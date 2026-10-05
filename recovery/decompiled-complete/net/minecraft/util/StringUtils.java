package net.minecraft.util;

import java.util.regex.Pattern;
import net.minecraft.item.Item$9;

public class StringUtils {
   public Item$9 field_0000;
   public static Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

   public static boolean isNullOrEmpty(String var0) {
      return org.apache.commons.lang3.StringUtils.isEmpty(var0);
   }

   public static String stripControlCodes(String var0) {
      return patternControlCode.matcher(var0).replaceAll("");
   }

   public static String ticksToElapsedTime(int var0) {
      int var1 = var0 / 20;
      int var2 = var1 / 60;
      var1 %= 60;
      return var1 < 10 ? var2 + ":0" + var1 : var2 + ":" + var1;
   }
}
