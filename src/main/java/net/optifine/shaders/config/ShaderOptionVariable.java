package net.optifine.shaders.config;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;
import net.optifine.util.StrUtils;

public class ShaderOptionVariable extends ShaderOption {
   public static Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+(\\w+)\\s+(-?[0-9\\.Ff]+|\\w+)\\s*(//.*)?$");

   @Override
   public String getValueColor(String var1) {
      String var2 = var1.toLowerCase();
      return !var2.equals("false") && !var2.equals("off") ? "§a" : "§c";
   }

   @Override
   public String getSourceLine() {
      return "#define " + this.getName() + " " + this.getValue() + " // Shader option " + this.getValue();
   }

   @Override
   public String getValueText(String var1) {
      String var2 = Shaders.translate("prefix." + this.getName(), "");
      String var3 = super.getValueText(var1);
      String var4 = Shaders.translate("suffix." + this.getName(), "");
      return var2 + var3 + var4;
   }

   public static String[] parseValues(String var0, String var1) {
      String[] var2 = new String[]{var0};
      if (var1 == null) {
         return var2;
      } else {
         var1 = var1.trim();
         var1 = StrUtils.removePrefix(var1, "[");
         var1 = StrUtils.removeSuffix(var1, "]");
         var1 = var1.trim();
         if (var1.length() <= 0) {
            return var2;
         } else {
            String[] var3 = Config.tokenize(var1, " ");
            if (var3.length <= 0) {
               return var2;
            } else {
               if (!Arrays.asList(var3).contains(var0)) {
                  var3 = (String[])Config.addObjectToArray(var3, var0, 0);
               }

               return var3;
            }
         }
      }
   }

   public static ShaderOption parseOption(String var0, String var1) {
      Matcher var2 = PATTERN_VARIABLE.matcher(var0);
      if (!var2.matches()) {
         return null;
      } else {
         String var3 = var2.group(1);
         String var4 = var2.group(2);
         String var5 = var2.group(3);
         String var6 = StrUtils.method_10292(var5, "[", "]");
         if (var6 != null && var6.length() > 0) {
            var5 = var5.replace(var6, "").trim();
         }

         String[] var7 = parseValues(var4, var6);
         if (var3 != null && var3.length() > 0) {
            var1 = StrUtils.removePrefix(var1, "/shaders/");
            return new ShaderOptionVariable(var3, var5, var4, var7, var1);
         } else {
            return null;
         }
      }
   }

   @Override
   public boolean matchesLine(String var1) {
      Matcher var2 = PATTERN_VARIABLE.matcher(var1);
      if (!var2.matches()) {
         return false;
      } else {
         String var3 = var2.group(1);
         return var3.matches(this.getName());
      }
   }

   public ShaderOptionVariable(String var1, String var2, String var3, String[] var4, String var5) {
      super(var1, var2, var3, var4, var3, var5);
      this.setVisible(this.getValues().length > 1);
   }
}
