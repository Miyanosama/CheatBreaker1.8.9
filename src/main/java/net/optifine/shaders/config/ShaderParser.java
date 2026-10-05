package net.optifine.shaders.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderParser {
   public static Pattern PATTERN_UNIFORM = Pattern.compile("\\s*uniform\\s+\\w+\\s+(\\w+).*");
   public static Pattern PATTERN_ATTRIBUTE = Pattern.compile("\\s*attribute\\s+\\w+\\s+(\\w+).*");
   public static Pattern PATTERN_CONST_INT = Pattern.compile("\\s*const\\s+int\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
   public static Pattern PATTERN_CONST_FLOAT = Pattern.compile("\\s*const\\s+float\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
   public static Pattern PATTERN_CONST_VEC4 = Pattern.compile("\\s*const\\s+vec4\\s+(\\w+)\\s*=\\s*(.+)\\s*;.*");
   public static Pattern PATTERN_CONST_BOOL = Pattern.compile("\\s*const\\s+bool\\s+(\\w+)\\s*=\\s*(\\w+)\\s*;.*");
   public static Pattern PATTERN_PROPERTY = Pattern.compile("\\s*(/\\*|//)?\\s*([A-Z]+):\\s*(\\w+)\\s*(\\*/.*|\\s*)");
   public static Pattern PATTERN_EXTENSION = Pattern.compile("\\s*#\\s*extension\\s+(\\w+)\\s*:\\s*(\\w+).*");
   public static Pattern PATTERN_DEFERRED_FSH = Pattern.compile(".*deferred[0-9]*\\.fsh");
   public static Pattern PATTERN_COMPOSITE_FSH = Pattern.compile(".*composite[0-9]*\\.fsh");
   public static Pattern PATTERN_FINAL_FSH = Pattern.compile(".*final\\.fsh");
   public static Pattern PATTERN_DRAW_BUFFERS = Pattern.compile("[0-7N]*");

   public static boolean isValidDrawBuffers(String var0) {
      return PATTERN_DRAW_BUFFERS.matcher(var0).matches();
   }

   public static int method_00225(String var0) {
      int var1 = getIndex(var0, "gaux", 1, 4);
      return var1 > 0 ? var1 + 3 : getIndex(var0, "colortex", 4, 7);
   }

   public static boolean isFinal(String var0) {
      return PATTERN_FINAL_FSH.matcher(var0).matches();
   }

   public static boolean isComposite(String var0) {
      return PATTERN_COMPOSITE_FSH.matcher(var0).matches();
   }

   public static int method_00217(String var0) {
      return var0.equals("shadowcolor") ? 0 : getIndex(var0, "shadowcolor", 0, 1);
   }

   public static boolean isDeferred(String var0) {
      return PATTERN_DEFERRED_FSH.matcher(var0).matches();
   }

   public static int getDepthIndex(String var0) {
      return getIndex(var0, "depthtex", 0, 2);
   }

   public static int method_00223(String var0) {
      return var0.equals("shadow") ? 0 : (var0.equals("watershadow") ? 1 : getIndex(var0, "shadowtex", 0, 1));
   }

   public static ShaderLine parseLine(String var0) {
      Matcher var1 = PATTERN_UNIFORM.matcher(var0);
      if (var1.matches()) {
         return new ShaderLine(1, var1.group(1), "", var0);
      } else {
         Matcher var2 = PATTERN_ATTRIBUTE.matcher(var0);
         if (var2.matches()) {
            return new ShaderLine(2, var2.group(1), "", var0);
         } else {
            Matcher var3 = PATTERN_PROPERTY.matcher(var0);
            if (var3.matches()) {
               return new ShaderLine(6, var3.group(2), var3.group(3), var0);
            } else {
               Matcher var4 = PATTERN_CONST_INT.matcher(var0);
               if (var4.matches()) {
                  return new ShaderLine(3, var4.group(1), var4.group(2), var0);
               } else {
                  Matcher var5 = PATTERN_CONST_FLOAT.matcher(var0);
                  if (var5.matches()) {
                     return new ShaderLine(4, var5.group(1), var5.group(2), var0);
                  } else {
                     Matcher var6 = PATTERN_CONST_BOOL.matcher(var0);
                     if (var6.matches()) {
                        return new ShaderLine(5, var6.group(1), var6.group(2), var0);
                     } else {
                        Matcher var7 = PATTERN_EXTENSION.matcher(var0);
                        if (var7.matches()) {
                           return new ShaderLine(7, var7.group(1), var7.group(2), var0);
                        } else {
                           Matcher var8 = PATTERN_CONST_VEC4.matcher(var0);
                           return var8.matches() ? new ShaderLine(8, var8.group(1), var8.group(2), var0) : null;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static int getIndex(String var0, String var1, int var2, int var3) {
      if (var0.length() != var1.length() + 1) {
         return -1;
      } else if (!var0.startsWith(var1)) {
         return -1;
      } else {
         int var4 = var0.charAt(var1.length()) - '0';
         return var4 >= var2 && var4 <= var3 ? var4 : -1;
      }
   }
}
