package net.optifine.shaders.config;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.src.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;

public class MacroState {
   public static final String recoveredField3524 = "undef";
   public static final String recoveredField3525 = "if";
   public static final String recoveredField3526 = "endif";
   public static final String recoveredField3527 = "define";
   public static final String recoveredField3528 = "else";
   public Map<String, String> mapMacroValues;
   public Deque<Boolean> dequeResolved;
   public static final String recoveredField3529 = "ifdef";
   public static final String recoveredField3530 = "ifndef";
   public Deque<Boolean> dequeState;
   public boolean active = true;
   public static final String recoveredField3531 = "elif";
   public static Pattern PATTERN_DIRECTIVE = Pattern.compile("\\s*#\\s*(\\w+)\\s*(.*)");
   public static Pattern PATTERN_DEFINED = Pattern.compile("defined\\s+(\\w+)");
   public static Pattern PATTERN_DEFINED_FUNC = Pattern.compile("defined\\s*\\(\\s*(\\w+)\\s*\\)");
   public static Pattern PATTERN_MACRO = Pattern.compile("(\\w+)");
   public static List<String> MACRO_NAMES = Arrays.asList("define", "undef", "ifdef", "ifndef", "if", "else", "elif", "endif");

   public boolean processLine(String var1) {
      Matcher var2 = PATTERN_DIRECTIVE.matcher(var1);
      if (!var2.matches()) {
         return this.active;
      } else {
         String var3 = var2.group(1);
         String var4 = var2.group(2);
         int var5 = var4.indexOf("//");
         if (var5 >= 0) {
            var4 = var4.substring(0, var5);
         }

         boolean var6 = this.active;
         this.processMacro(var3, var4);
         this.active = !this.dequeState.contains(Boolean.FALSE);
         return this.active || var6;
      }
   }

   public static boolean isMacroLine(String var0) {
      Matcher var1 = PATTERN_DIRECTIVE.matcher(var0);
      if (!var1.matches()) {
         return false;
      } else {
         String var2 = var1.group(1);
         return MACRO_NAMES.contains(var2);
      }
   }

   public MacroState() {
      this.dequeState = new ArrayDeque<>();
      this.dequeResolved = new ArrayDeque<>();
      this.mapMacroValues = new HashMap<>();
   }

   public void processMacro(String var1, String var2) {
      StringTokenizer var3 = new StringTokenizer(var2, " \t");
      String var4 = var3.hasMoreTokens() ? var3.nextToken() : "";
      String var5 = var3.hasMoreTokens() ? var3.nextToken("").trim() : "";
      if (var1.equals("define")) {
         this.mapMacroValues.put(var4, var5);
      } else if (var1.equals("undef")) {
         this.mapMacroValues.remove(var4);
      } else if (var1.equals("ifdef")) {
         boolean var6 = this.mapMacroValues.containsKey(var4);
         this.dequeState.add(var6);
         this.dequeResolved.add(var6);
      } else if (var1.equals("ifndef")) {
         boolean var9 = !this.mapMacroValues.containsKey(var4);
         this.dequeState.add(var9);
         this.dequeResolved.add(var9);
      } else if (var1.equals("if")) {
         boolean var10 = this.eval(var2);
         this.dequeState.add(var10);
         this.dequeResolved.add(var10);
      } else if (!this.dequeState.isEmpty()) {
         if (var1.equals("elif")) {
            boolean var11 = this.dequeState.removeLast();
            boolean var7 = this.dequeResolved.removeLast();
            if (var7) {
               this.dequeState.add(false);
               this.dequeResolved.add(var7);
            } else {
               boolean var8 = this.eval(var2);
               this.dequeState.add(var8);
               this.dequeResolved.add(var8);
            }
         } else if (var1.equals("else")) {
            boolean var12 = this.dequeState.removeLast();
            boolean var13 = this.dequeResolved.removeLast();
            boolean var14 = !var13;
            this.dequeState.add(var14);
            this.dequeResolved.add(true);
         } else if (var1.equals("endif")) {
            this.dequeState.removeLast();
            this.dequeResolved.removeLast();
         }
      }
   }

   public boolean eval(String var1) {
      Matcher var2 = PATTERN_DEFINED.matcher(var1);
      var1 = var2.replaceAll("defined_$1");
      Matcher var3 = PATTERN_DEFINED_FUNC.matcher(var1);
      var1 = var3.replaceAll("defined_$1");
      boolean var4 = false;
      int var5 = 0;

      do {
         var4 = false;
         Matcher var6 = PATTERN_MACRO.matcher(var1);

         while (var6.find()) {
            String var7 = var6.group();
            if (var7.length() > 0) {
               char var8 = var7.charAt(0);
               if ((Character.isLetter(var8) || var8 == '_') && this.mapMacroValues.containsKey(var7)) {
                  String var9 = this.mapMacroValues.get(var7);
                  if (var9 == null) {
                     var9 = "1";
                  }

                  int var10 = var6.start();
                  int var11 = var6.end();
                  var1 = var1.substring(0, var10) + " " + var9 + " " + var1.substring(var11);
                  var4 = true;
                  var5++;
                  break;
               }
            }
         }
      } while (var4 && var5 < 100);

      if (var5 >= 100) {
         Config.warn("Too many iterations: " + var5 + ", when resolving: " + var1);
         return true;
      } else {
         try {
            MacroExpressionResolver var16 = new MacroExpressionResolver(this.mapMacroValues);
            ExpressionParser var17 = new ExpressionParser(var16);
            IExpression var18 = var17.parse(var1);
            if (var18.getExpressionType() == ExpressionType.BOOL) {
               IExpressionBool var20 = (IExpressionBool)var18;
               return var20.eval();
            } else if (var18.getExpressionType() == ExpressionType.FLOAT) {
               IExpressionFloat var19 = (IExpressionFloat)var18;
               float var21 = var19.eval();
               return var21 != 0.0F;
            } else {
               throw new ParseException("Not a boolean or float expression: " + var18.getExpressionType());
            }
         } catch (ParseException var12) {
            Config.warn("Invalid macro expression: " + var1);
            Config.warn("Error: " + var12.getMessage());
            return false;
         }
      }
   }
}
