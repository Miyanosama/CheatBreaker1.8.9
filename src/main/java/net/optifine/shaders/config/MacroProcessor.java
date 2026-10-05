package net.optifine.shaders.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public class MacroProcessor {
   public static List<ShaderOption> getMacroOptions() {
      ArrayList var0 = new ArrayList();
      ShaderOption[] var1 = Shaders.getShaderPackOptions();

      for (int var2 = 0; var2 < var1.length; var2++) {
         ShaderOption var3 = var1[var2];
         String var4 = var3.getSourceLine();
         if (var4 != null && var4.startsWith("#")) {
            var0.add(var3);
         }
      }

      return var0;
   }

   public static String process(String var0) throws java.io.IOException {
      StringReader var1 = new StringReader(var0);
      BufferedReader var2 = new BufferedReader(var1);
      MacroState var3 = new MacroState();
      StringBuilder var4 = new StringBuilder();

      while (true) {
         String var5 = var2.readLine();
         if (var5 == null) {
            return var4.toString();
         }

         if (var3.processLine(var5) && !MacroState.isMacroLine(var5)) {
            var4.append(var5);
            var4.append("\n");
         }
      }
   }

   public static String getMacroHeader(String var0) throws java.io.IOException {
      StringBuilder var1 = new StringBuilder();
      Object var2 = null;
      ArrayList var3 = null;
      StringReader var4 = new StringReader(var0);
      BufferedReader var5 = new BufferedReader(var4);

      while (true) {
         String var6 = var5.readLine();
         if (var6 == null) {
            return var1.toString();
         }

         if (MacroState.isMacroLine(var6)) {
            if (var1.length() == 0) {
               var1.append(ShaderMacros.getFixedMacroLines());
            }

            if (var3 == null) {
               var3 = new ArrayList<>(Arrays.asList(ShaderMacros.getExtensions()));
            }

            Iterator var7 = var3.iterator();

            while (var7.hasNext()) {
               ShaderMacro var8 = (ShaderMacro)var7.next();
               if (var6.contains(var8.getName())) {
                  var1.append(var8.getSourceLine());
                  var1.append("\n");
                  var7.remove();
               }
            }
         }
      }
   }

   public static InputStream process(InputStream var0, String var1) throws java.io.IOException {
      String var2 = Config.readInputStream(var0, "ASCII");
      String var3 = getMacroHeader(var2);
      if (!var3.isEmpty()) {
         var2 = var3 + var2;
         if (Shaders.saveFinalShaders) {
            String var4 = var1.replace(':', '/') + ".pre";
            Shaders.saveShader(var4, var2);
         }

         var2 = process(var2);
      }

      if (Shaders.saveFinalShaders) {
         String var7 = var1.replace(':', '/');
         Shaders.saveShader(var7, var2);
      }

      byte[] var8 = var2.getBytes("ASCII");
      return new ByteArrayInputStream(var8);
   }
}
