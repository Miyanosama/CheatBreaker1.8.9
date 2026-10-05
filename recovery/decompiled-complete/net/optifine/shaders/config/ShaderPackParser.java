package net.optifine.shaders.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.vecmath.Color4b;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityDropper;
import net.optifine.expr.ExpressionFloatArrayCached;
import net.optifine.expr.ExpressionFloatCached;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionFloatArray;
import net.optifine.expr.ParseException;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Program;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.uniform.CustomUniform;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderExpressionResolver;
import net.optifine.shaders.uniform.UniformType;
import net.optifine.util.StrUtils;
import recovered.unidentified.UnidentifiedClass1534;

public class ShaderPackParser {
   public Color4b field_0003;
   public static Map<String, Integer> mapAlphaFuncs = makeMapAlphaFuncs();
   public static Pattern PATTERN_VERSION = Pattern.compile("^\\s*#version\\s+.*$");
   public static Map<String, Integer> mapBlendFactors = makeMapBlendFactors();
   public static Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
   public TileEntityDropper field_0001;
   public ContainerEnchantment field_0007;
   public static Set<String> setConstNames = makeSetConstNames();

   public static boolean parseGuiScreen(String var0, Properties var1, Map<String, ScreenShaderOptions> var2, ShaderProfile[] var3, ShaderOption[] var4) {
      String var5 = var1.getProperty(var0);
      if (var5 == null) {
         return false;
      } else {
         ArrayList var6 = new ArrayList();
         HashSet var7 = new HashSet();
         String[] var8 = Config.tokenize(var5, " ");

         for (int var9 = 0; var9 < var8.length; var9++) {
            String var10 = var8[var9];
            if (var10.equals("<empty>")) {
               var6.add((ShaderOption)null);
            } else if (var7.contains(var10)) {
               Config.warn("[Shaders] Duplicate option: " + var10 + ", key: " + var0);
            } else {
               var7.add(var10);
               if (var10.equals("<profile>")) {
                  if (var3 == null) {
                     Config.warn("[Shaders] Option profile can not be used, no profiles defined: " + var10 + ", key: " + var0);
                  } else {
                     ShaderOptionProfile var11 = new ShaderOptionProfile(var3, var4);
                     var6.add(var11);
                  }
               } else if (var10.equals("*")) {
                  UnidentifiedClass1534 var15 = new UnidentifiedClass1534("<rest>");
                  var6.add(var15);
               } else if (var10.startsWith("[") && var10.endsWith("]")) {
                  String var17 = StrUtils.removePrefixSuffix(var10, "[", "]");
                  if (!var17.matches("^[a-zA-Z0-9_]+$")) {
                     Config.warn("[Shaders] Invalid screen: " + var10 + ", key: " + var0);
                  } else if (!parseGuiScreen("screen." + var17, var1, var2, var3, var4)) {
                     Config.warn("[Shaders] Invalid screen: " + var10 + ", key: " + var0);
                  } else {
                     ShaderOptionScreen var12 = new ShaderOptionScreen(var17);
                     var6.add(var12);
                  }
               } else {
                  ShaderOption var16 = ShaderUtils.getShaderOption(var10, var4);
                  if (var16 == null) {
                     Config.warn("[Shaders] Invalid option: " + var10 + ", key: " + var0);
                     var6.add((ShaderOption)null);
                  } else {
                     var16.setVisible(true);
                     var6.add(var16);
                  }
               }
            }
         }

         ShaderOption[] var13 = var6.toArray(new ShaderOption[var6.size()]);
         String var14 = var1.getProperty(var0 + ".columns");
         int var18 = Config.parseInt(var14, 2);
         ScreenShaderOptions var19 = new ScreenShaderOptions(var0, var13, var18);
         var2.put(var0, var19);
         return true;
      }
   }

   public static Map<String, IExpressionBool> parseProgramConditions(Properties var0, ShaderOption[] var1) {
      String var2 = "program.";
      Pattern var3 = Pattern.compile("program\\.([^.]+)\\.enabled");
      HashMap var4 = new HashMap();

      for (Object var6 : var0.keySet()) {
         String var7 = (String)var6;
         Matcher var8 = var3.matcher(var7);
         if (var8.matches()) {
            String var9 = var8.group(1);
            String var10 = var0.getProperty(var7).trim();
            IExpressionBool var11 = parseOptionExpression(var10, var1);
            if (var11 == null) {
               SMCLog.severe("Error parsing program condition: " + var7);
            } else {
               var4.put(var9, var11);
            }
         }
      }

      return var4;
   }

   public static CustomUniform parseCustomUniform(String var0, String var1, String var2, String var3, Map<String, IExpression> var4) {
      try {
         UniformType var5 = UniformType.parse(var2);
         if (var5 == null) {
            SMCLog.warning("Unknown " + var0 + " type: " + var5);
            return null;
         } else {
            ShaderExpressionResolver var6 = new ShaderExpressionResolver(var4);
            ExpressionParser var7 = new ExpressionParser(var6);
            IExpression var8 = var7.parse(var3);
            ExpressionType var9 = var8.getExpressionType();
            if (!var5.matchesExpressionType(var9)) {
               SMCLog.warning("Expression type does not match " + var0 + " type, expression: " + var9 + ", " + var0 + ": " + var5 + " " + var1);
               return null;
            } else {
               var8 = makeExpressionCached(var8);
               return new CustomUniform(var1, var5, var8);
            }
         }
      } catch (ParseException var11) {
         SMCLog.warning(var11.getClass().getName() + ": " + var11.getMessage());
         return null;
      }
   }

   public static GlAlphaState parseAlphaState(String var0) {
      String[] var1 = Config.tokenize(var0, " ");
      if (var1.length == 1) {
         String var2 = var1[0];
         if (var2.equals("off") || var2.equals("false")) {
            return new GlAlphaState(false);
         }
      } else if (var1.length == 2) {
         String var6 = var1[0];
         String var3 = var1[1];
         Integer var4 = mapAlphaFuncs.get(var6);
         float var5 = Config.parseFloat(var3, -1.0F);
         if (var4 != null && var5 >= 0.0F) {
            return new GlAlphaState(true, var4, var5);
         }
      }

      SMCLog.severe("Invalid alpha test: " + var0);
      return null;
   }

   public static Set<String> makeSetConstNames() {
      HashSet var0 = new HashSet();
      var0.add("shadowMapResolution");
      var0.add("shadowMapFov");
      var0.add("shadowDistance");
      var0.add("shadowDistanceRenderMul");
      var0.add("shadowIntervalSize");
      var0.add("generateShadowMipmap");
      var0.add("generateShadowColorMipmap");
      var0.add("shadowHardwareFiltering");
      var0.add("shadowHardwareFiltering0");
      var0.add("shadowHardwareFiltering1");
      var0.add("shadowtex0Mipmap");
      var0.add("shadowtexMipmap");
      var0.add("shadowtex1Mipmap");
      var0.add("shadowcolor0Mipmap");
      var0.add("shadowColor0Mipmap");
      var0.add("shadowcolor1Mipmap");
      var0.add("shadowColor1Mipmap");
      var0.add("shadowtex0Nearest");
      var0.add("shadowtexNearest");
      var0.add("shadow0MinMagNearest");
      var0.add("shadowtex1Nearest");
      var0.add("shadow1MinMagNearest");
      var0.add("shadowcolor0Nearest");
      var0.add("shadowColor0Nearest");
      var0.add("shadowColor0MinMagNearest");
      var0.add("shadowcolor1Nearest");
      var0.add("shadowColor1Nearest");
      var0.add("shadowColor1MinMagNearest");
      var0.add("wetnessHalflife");
      var0.add("drynessHalflife");
      var0.add("eyeBrightnessHalflife");
      var0.add("centerDepthHalflife");
      var0.add("sunPathRotation");
      var0.add("ambientOcclusionLevel");
      var0.add("superSamplingLevel");
      var0.add("noiseTextureResolution");
      return var0;
   }

   public static Map<String, ScreenShaderOptions> parseGuiScreens(Properties var0, ShaderProfile[] var1, ShaderOption[] var2) {
      HashMap var3 = new HashMap();
      parseGuiScreen("screen", var0, var3, var1, var2);
      return var3.isEmpty() ? null : var3;
   }

   public static Map<String, Integer> makeMapAlphaFuncs() {
      HashMap var0 = new HashMap();
      var0.put("NEVER", new Integer(512));
      var0.put("LESS", new Integer(513));
      var0.put("EQUAL", new Integer(514));
      var0.put("LEQUAL", new Integer(515));
      var0.put("GREATER", new Integer(516));
      var0.put("NOTEQUAL", new Integer(517));
      var0.put("GEQUAL", new Integer(518));
      var0.put("ALWAYS", new Integer(519));
      return Collections.unmodifiableMap(var0);
   }

   public static ShaderOption getShaderOption(String var0, String var1) {
      ShaderOption var2 = null;
      if (var2 == null) {
         var2 = ShaderOptionSwitch.parseOption(var0, var1);
      }

      if (var2 == null) {
         var2 = ShaderOptionVariable.parseOption(var0, var1);
      }

      if (var2 != null) {
         return var2;
      } else {
         if (var2 == null) {
            var2 = ShaderOptionSwitchConst.parseOption(var0, var1);
         }

         if (var2 == null) {
            var2 = ShaderOptionVariableConst.parseOption(var0, var1);
         }

         return var2 != null && setConstNames.contains(var2.getName()) ? var2 : null;
      }
   }

   public static void method_28407(Properties var0) {
      for (Object var2 : var0.keySet()) {
         String var3 = (String)var2;
         String[] var4 = Config.tokenize(var3, ".");
         if (var4.length == 2) {
            String var5 = var4[0];
            String var6 = var4[1];
            if (var5.equals("blend")) {
               Program var7 = Shaders.getProgram(var6);
               if (var7 == null) {
                  SMCLog.severe("Invalid program name: " + var6);
               } else {
                  String var8 = var0.getProperty(var3).trim();
                  GlBlendState var9 = parseBlendState(var8);
                  if (var9 != null) {
                     var7.setBlendState(var9);
                  }
               }
            }
         }
      }
   }

   public static String loadFile(String var0, IShaderPack var1, int var2, List<String> var3, int var4) {
      if (var4 >= 10) {
         throw new IOException("#include depth exceeded: " + var4 + ", file: " + var0);
      } else {
         var4++;
         InputStream var5 = var1.getResourceAsStream(var0);
         if (var5 == null) {
            return null;
         } else {
            InputStreamReader var6 = new InputStreamReader(var5, "ASCII");
            BufferedReader var7 = new BufferedReader(var6);
            var7 = resolveIncludes(var7, var0, var1, var2, var3, var4);
            CharArrayWriter var8 = new CharArrayWriter();

            while (true) {
               String var9 = var7.readLine();
               if (var9 == null) {
                  return var8.toString();
               }

               var8.write(var9);
               var8.write("\n");
            }
         }
      }
   }

   public static void method_28419(Properties var0) {
      for (Object var2 : var0.keySet()) {
         String var3 = (String)var2;
         String[] var4 = Config.tokenize(var3, ".");
         if (var4.length == 2) {
            String var5 = var4[0];
            String var6 = var4[1];
            if (var5.equals("alphaTest")) {
               Program var7 = Shaders.getProgram(var6);
               if (var7 == null) {
                  SMCLog.severe("Invalid program name: " + var6);
               } else {
                  String var8 = var0.getProperty(var3).trim();
                  GlAlphaState var9 = parseAlphaState(var8);
                  if (var9 != null) {
                     var7.setAlphaState(var9);
                  }
               }
            }
         }
      }
   }

   public static ShaderProfile[] parseProfiles(Properties var0, ShaderOption[] var1) {
      String var2 = "profile.";
      ArrayList var3 = new ArrayList();

      for (Object var5 : var0.keySet()) {
         String var6 = (String)var5;
         if (var6.startsWith(var2)) {
            String var7 = var6.substring(var2.length());
            var0.getProperty(var6);
            HashSet var8 = new HashSet();
            ShaderProfile var9 = parseProfile(var7, var0, var8, var1);
            if (var9 != null) {
               var3.add(var9);
            }
         }
      }

      return var3.size() <= 0 ? null : var3.toArray(new ShaderProfile[var3.size()]);
   }

   public static IExpression makeExpressionCached(IExpression var0) {
      return (IExpression)(var0 instanceof IExpressionFloat
         ? new ExpressionFloatCached((IExpressionFloat)var0)
         : (var0 instanceof IExpressionFloatArray ? new ExpressionFloatArrayCached((IExpressionFloatArray)var0) : var0));
   }

   public static Map<String, Integer> makeMapBlendFactors() {
      HashMap var0 = new HashMap();
      var0.put("ZERO", new Integer(0));
      var0.put("ONE", new Integer(1));
      var0.put("SRC_COLOR", new Integer(768));
      var0.put("ONE_MINUS_SRC_COLOR", new Integer(769));
      var0.put("DST_COLOR", new Integer(774));
      var0.put("ONE_MINUS_DST_COLOR", new Integer(775));
      var0.put("SRC_ALPHA", new Integer(770));
      var0.put("ONE_MINUS_SRC_ALPHA", new Integer(771));
      var0.put("DST_ALPHA", new Integer(772));
      var0.put("ONE_MINUS_DST_ALPHA", new Integer(773));
      var0.put("CONSTANT_COLOR", new Integer(32769));
      var0.put("ONE_MINUS_CONSTANT_COLOR", new Integer(32770));
      var0.put("CONSTANT_ALPHA", new Integer(32771));
      var0.put("ONE_MINUS_CONSTANT_ALPHA", new Integer(32772));
      var0.put("SRC_ALPHA_SATURATE", new Integer(776));
      return Collections.unmodifiableMap(var0);
   }

   public static void method_28395(Properties var0) {
      for (Object var2 : var0.keySet()) {
         String var3 = (String)var2;
         String[] var4 = Config.tokenize(var3, ".");
         if (var4.length == 2) {
            String var5 = var4[0];
            String var6 = var4[1];
            if (var5.equals("scale")) {
               Program var7 = Shaders.getProgram(var6);
               if (var7 == null) {
                  SMCLog.severe("Invalid program name: " + var6);
               } else {
                  String var8 = var0.getProperty(var3).trim();
                  RenderScale var9 = parseRenderScale(var8);
                  if (var9 != null) {
                     var7.setRenderScale(var9);
                  }
               }
            }
         }
      }
   }

   public static ShaderProfile parseProfile(String var0, Properties var1, Set<String> var2, ShaderOption[] var3) {
      String var4 = "profile.";
      String var5 = var4 + var0;
      if (var2.contains(var5)) {
         Config.warn("[Shaders] Profile already parsed: " + var0);
         return null;
      } else {
         var2.add(var0);
         ShaderProfile var6 = new ShaderProfile(var0);
         String var7 = var1.getProperty(var5);
         String[] var8 = Config.tokenize(var7, " ");

         for (int var9 = 0; var9 < var8.length; var9++) {
            String var10 = var8[var9];
            if (var10.startsWith(var4)) {
               String var11 = var10.substring(var4.length());
               ShaderProfile var12 = parseProfile(var11, var1, var2, var3);
               if (var6 != null) {
                  var6.addOptionValues(var12);
                  var6.addDisabledPrograms(var12.getDisabledPrograms());
               }
            } else {
               String[] var16 = Config.tokenize(var10, ":=");
               if (var16.length == 1) {
                  String var17 = var16[0];
                  boolean var13 = true;
                  if (var17.startsWith("!")) {
                     var13 = false;
                     var17 = var17.substring(1);
                  }

                  String var14 = "program.";
                  if (var17.startsWith(var14)) {
                     String var15 = var17.substring(var14.length());
                     if (!Shaders.isProgramPath(var15)) {
                        Config.warn("Invalid program: " + var15 + " in profile: " + var6.getName());
                     } else if (var13) {
                        var6.removeDisabledProgram(var15);
                     } else {
                        var6.addDisabledProgram(var15);
                     }
                  } else {
                     ShaderOption var21 = ShaderUtils.getShaderOption(var17, var3);
                     if (!(var21 instanceof ShaderOptionSwitch)) {
                        Config.warn("[Shaders] Invalid option: " + var17);
                     } else {
                        var6.addOptionValue(var17, String.valueOf(var13));
                        var21.setVisible(true);
                     }
                  }
               } else if (var16.length != 2) {
                  Config.warn("[Shaders] Invalid option value: " + var10);
               } else {
                  String var18 = var16[0];
                  String var19 = var16[1];
                  ShaderOption var20 = ShaderUtils.getShaderOption(var18, var3);
                  if (var20 == null) {
                     Config.warn("[Shaders] Invalid option: " + var10);
                  } else if (!var20.isValidValue(var19)) {
                     Config.warn("[Shaders] Invalid value: " + var10);
                  } else {
                     var20.setVisible(true);
                     var6.addOptionValue(var18, var19);
                  }
               }
            }
         }

         return var6;
      }
   }

   public static void collectShaderOptions(IShaderPack var0, String var1, String[] var2, Map<String, ShaderOption> var3) {
      for (int var4 = 0; var4 < var2.length; var4++) {
         String var5 = var2[var4];
         if (!var5.equals("")) {
            String var6 = var1 + "/" + var5 + ".vsh";
            String var7 = var1 + "/" + var5 + ".fsh";
            collectShaderOptions(var0, var6, var3);
            collectShaderOptions(var0, var7, var3);
         }
      }
   }

   public static CustomUniforms parseCustomUniforms(Properties var0) {
      String var1 = "uniform";
      String var2 = "variable";
      String var3 = var1 + ".";
      String var4 = var2 + ".";
      HashMap var5 = new HashMap();
      ArrayList var6 = new ArrayList();

      for (Object var8 : var0.keySet()) {
         String var9 = (String)var8;
         String[] var10 = Config.tokenize(var9, ".");
         if (var10.length == 3) {
            String var11 = var10[0];
            String var12 = var10[1];
            String var13 = var10[2];
            String var14 = var0.getProperty(var9).trim();
            if (var5.containsKey(var13)) {
               SMCLog.warning("Expression already defined: " + var13);
            } else if (var11.equals(var1) || var11.equals(var2)) {
               SMCLog.info("Custom " + var11 + ": " + var13);
               CustomUniform var15 = parseCustomUniform(var11, var13, var12, var14, var5);
               if (var15 != null) {
                  var5.put(var13, var15.getExpression());
                  if (!var11.equals(var2)) {
                     var6.add(var15);
                  }
               }
            }
         }
      }

      if (var6.size() <= 0) {
         return null;
      } else {
         CustomUniform[] var16 = var6.toArray(new CustomUniform[var6.size()]);
         return new CustomUniforms(var16, var5);
      }
   }

   public static IExpressionBool parseOptionExpression(String var0, ShaderOption[] var1) {
      try {
         ShaderOptionResolver var2 = new ShaderOptionResolver(var1);
         ExpressionParser var3 = new ExpressionParser(var2);
         return var3.parseBool(var0);
      } catch (ParseException var5) {
         SMCLog.warning(var5.getClass().getName() + ": " + var5.getMessage());
         return null;
      }
   }

   public static BufferedReader resolveIncludes(BufferedReader var0, String var1, IShaderPack var2, int var3, List<String> var4, int var5) {
      String var6 = "/";
      int var7 = var1.lastIndexOf("/");
      if (var7 >= 0) {
         var6 = var1.substring(0, var7);
      }

      CharArrayWriter var8 = new CharArrayWriter();
      int var9 = -1;
      LinkedHashSet var10 = new LinkedHashSet();
      int var11 = 1;

      while (true) {
         String var12 = var0.readLine();
         if (var12 == null) {
            char[] var21 = var8.toCharArray();
            if (var9 >= 0 && var10.size() > 0) {
               StringBuilder var24 = new StringBuilder();

               for (ShaderMacro var32 : var10) {
                  var24.append("#define ");
                  var24.append(var32.getName());
                  var24.append(" ");
                  var24.append(var32.getValue());
                  var24.append("\n");
               }

               String var29 = var24.toString();
               StringBuilder var33 = new StringBuilder(new String(var21));
               var33.insert(var9, var29);
               String var34 = var33.toString();
               var21 = var34.toCharArray();
            }

            CharArrayReader var25 = new CharArrayReader(var21);
            return new BufferedReader(var25);
         }

         if (var9 < 0) {
            Matcher var13 = PATTERN_VERSION.matcher(var12);
            if (var13.matches()) {
               String var14 = ShaderMacros.getFixedMacroLines() + ShaderMacros.getOptionMacroLines();
               String var15 = var12 + "\n" + var14;
               String var16 = "#line " + (var11 + 1) + " " + var3;
               var12 = var15 + var16;
               var9 = var8.size() + var15.length();
            }
         }

         Matcher var20 = PATTERN_INCLUDE.matcher(var12);
         if (var20.matches()) {
            String var22 = var20.group(1);
            boolean var26 = var22.startsWith("/");
            String var30 = var26 ? "/shaders" + var22 : var6 + "/" + var22;
            if (!var4.contains(var30)) {
               var4.add(var30);
            }

            int var17 = var4.indexOf(var30) + 1;
            var12 = loadFile(var30, var2, var17, var4, var5);
            if (var12 == null) {
               throw new IOException("Included file not found: " + var1);
            }

            if (var12.endsWith("\n")) {
               var12 = var12.substring(0, var12.length() - 1);
            }

            String var18 = "#line 1 " + var17 + "\n";
            if (var12.startsWith("#version ")) {
               var18 = "";
            }

            var12 = var18 + var12 + "\n#line " + (var11 + 1) + " " + var3;
         }

         if (var9 >= 0 && var12.contains(ShaderMacros.getPrefixMacro())) {
            ShaderMacro[] var23 = findMacros(var12, ShaderMacros.getExtensions());

            for (int var27 = 0; var27 < var23.length; var27++) {
               ShaderMacro var31 = var23[var27];
               var10.add(var31);
            }
         }

         var8.write(var12);
         var8.write("\n");
         var11++;
      }
   }

   public static void collectShaderOptions(IShaderPack var0, String var1, Map<String, ShaderOption> var2) {
      String[] var3 = getLines(var0, var1);

      for (int var4 = 0; var4 < var3.length; var4++) {
         String var5 = var3[var4];
         ShaderOption var6 = getShaderOption(var5, var1);
         if (var6 != null && !var6.getName().startsWith(ShaderMacros.getPrefixMacro()) && (!var6.checkUsed() || isOptionUsed(var6, var3))) {
            String var7 = var6.getName();
            ShaderOption var8 = (ShaderOption)var2.get(var7);
            if (var8 != null) {
               if (!Config.equals(var8.getValueDefault(), var6.getValueDefault())) {
                  Config.warn("Ambiguous shader option: " + var6.getName());
                  Config.warn(" - in " + Config.arrayToString(var8.getPaths()) + ": " + var8.getValueDefault());
                  Config.warn(" - in " + Config.arrayToString(var6.getPaths()) + ": " + var6.getValueDefault());
                  var8.setEnabled(false);
               }

               if (var8.getDescription() == null || var8.getDescription().length() <= 0) {
                  var8.setDescription(var6.getDescription());
               }

               var8.addPaths(var6.getPaths());
            } else {
               var2.put(var7, var6);
            }
         }
      }
   }

   public static RenderScale parseRenderScale(String var0) {
      String[] var1 = Config.tokenize(var0, " ");
      float var2 = Config.parseFloat(var1[0], -1.0F);
      float var3 = 0.0F;
      float var4 = 0.0F;
      if (var1.length > 1) {
         if (var1.length != 3) {
            SMCLog.severe("Invalid render scale: " + var0);
            return null;
         }

         var3 = Config.parseFloat(var1[1], -1.0F);
         var4 = Config.parseFloat(var1[2], -1.0F);
      }

      if (Config.between(var2, 0.0F, 1.0F) && Config.between(var3, 0.0F, 1.0F) && Config.between(var4, 0.0F, 1.0F)) {
         return new RenderScale(var2, var3, var4);
      } else {
         SMCLog.severe("Invalid render scale: " + var0);
         return null;
      }
   }

   public static void method_28392(Properties var0) {
      for (Object var2 : var0.keySet()) {
         String var3 = (String)var2;
         String[] var4 = Config.tokenize(var3, ".");
         if (var4.length == 3) {
            String var5 = var4[0];
            String var6 = var4[1];
            String var7 = var4[2];
            if (var5.equals("flip")) {
               Program var8 = Shaders.getProgram(var6);
               if (var8 == null) {
                  SMCLog.severe("Invalid program name: " + var6);
               } else {
                  Boolean[] var9 = var8.getBuffersFlip();
                  int var10 = Shaders.method_02329(var7);
                  if (var10 >= 0 && var10 < var9.length) {
                     String var11 = var0.getProperty(var3).trim();
                     Boolean var12 = Config.parseBoolean(var11, (Boolean)null);
                     if (var12 == null) {
                        SMCLog.severe("Invalid boolean value: " + var11);
                     } else {
                        var9[var10] = var12;
                     }
                  } else {
                     SMCLog.severe("Invalid buffer name: " + var7);
                  }
               }
            }
         }
      }
   }

   public static GlBlendState parseBlendState(String var0) {
      String[] var1 = Config.tokenize(var0, " ");
      if (var1.length == 1) {
         String var2 = var1[0];
         if (var2.equals("off") || var2.equals("false")) {
            return new GlBlendState(false);
         }
      } else if (var1.length == 2 || var1.length == 4) {
         String var10 = var1[0];
         String var3 = var1[1];
         String var4 = var10;
         String var5 = var3;
         if (var1.length == 4) {
            var4 = var1[2];
            var5 = var1[3];
         }

         Integer var6 = mapBlendFactors.get(var10);
         Integer var7 = mapBlendFactors.get(var3);
         Integer var8 = mapBlendFactors.get(var4);
         Integer var9 = mapBlendFactors.get(var5);
         if (var6 != null && var7 != null && var8 != null && var9 != null) {
            return new GlBlendState(true, var6, var7, var8, var9);
         }
      }

      SMCLog.severe("Invalid blend mode: " + var0);
      return null;
   }

   public static boolean isOptionUsed(ShaderOption var0, String[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         String var3 = var1[var2];
         if (var0.isUsedInLine(var3)) {
            return true;
         }
      }

      return false;
   }

   public static ShaderMacro[] findMacros(String var0, ShaderMacro[] var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1.length; var3++) {
         ShaderMacro var4 = var1[var3];
         if (var0.contains(var4.getName())) {
            var2.add(var4);
         }
      }

      return var2.toArray(new ShaderMacro[var2.size()]);
   }

   public static ShaderOption[] parseShaderPackOptions(IShaderPack var0, String[] var1, List<Integer> var2) {
      if (var0 == null) {
         return new ShaderOption[0];
      } else {
         HashMap var3 = new HashMap();
         collectShaderOptions(var0, "/shaders", var1, var3);

         for (int var5 : var2) {
            String var6 = "/shaders/world" + var5;
            collectShaderOptions(var0, var6, var1, var3);
         }

         Collection var8 = var3.values();
         ShaderOption[] var9 = var8.toArray(new ShaderOption[var8.size()]);
         ShaderPackParser$1 var7 = new ShaderPackParser$1();
         Arrays.sort(var9, var7);
         return var9;
      }
   }

   public static Set<String> parseOptionSliders(Properties var0, ShaderOption[] var1) {
      HashSet var2 = new HashSet();
      String var3 = var0.getProperty("sliders");
      if (var3 == null) {
         return var2;
      } else {
         String[] var4 = Config.tokenize(var3, " ");

         for (int var5 = 0; var5 < var4.length; var5++) {
            String var6 = var4[var5];
            ShaderOption var7 = ShaderUtils.getShaderOption(var6, var1);
            if (var7 == null) {
               Config.warn("Invalid shader option: " + var6);
            } else {
               var2.add(var6);
            }
         }

         return var2;
      }
   }

   public static String[] getLines(IShaderPack var0, String var1) {
      try {
         ArrayList var2 = new ArrayList();
         String var3 = loadFile(var1, var0, 0, var2, 0);
         if (var3 == null) {
            return new String[0];
         } else {
            ByteArrayInputStream var4 = new ByteArrayInputStream(var3.getBytes());
            return Config.readLines(var4);
         }
      } catch (IOException var6) {
         Config.dbg(var6.getClass().getName() + ": " + var6.getMessage());
         return new String[0];
      }
   }
}
