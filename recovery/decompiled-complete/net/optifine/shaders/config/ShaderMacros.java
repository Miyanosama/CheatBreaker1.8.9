package net.optifine.shaders.config;

import io.netty.channel.sctp.nio.NioSctpServerChannel$NioSctpServerChannelConfig;
import net.minecraft.src.Config;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import net.optifine.shaders.Shaders;

public class ShaderMacros {
   public static String field_0013;
   public static String field_0026;
   public static String field_0012;
   public static String field_0023;
   public static String field_0006;
   public static String field_0007;
   public static String field_0027;
   public static String field_0019;
   public static String PREFIX_MACRO = "MC_";
   public static String field_0029;
   public static String field_0005;
   public static String field_0014;
   public static String field_0017;
   public static ShaderMacro[] extensionMacros;
   public static String field_0021;
   public static String field_0025;
   public static String field_0003;
   public static String field_0009;
   public static String field_0015;
   public NioSctpServerChannel$NioSctpServerChannelConfig field_0024;
   public static String field_0002;
   public static String field_0001;
   public static String field_0004;
   public static String field_0000;
   public static String field_0022;
   public static String field_0016;
   public static String field_0020;
   public static String field_0011;
   public static String field_0028;
   public static String field_0018;

   public static String getRenderer() {
      String var0 = Config.openGlRenderer;
      if (var0 == null) {
         return "MC_GL_RENDERER_OTHER";
      } else {
         var0 = var0.toLowerCase();
         return var0.startsWith("amd")
            ? "MC_GL_RENDERER_RADEON"
            : (
               var0.startsWith("ati")
                  ? "MC_GL_RENDERER_RADEON"
                  : (
                     var0.startsWith("radeon")
                        ? "MC_GL_RENDERER_RADEON"
                        : (
                           var0.startsWith("gallium")
                              ? "MC_GL_RENDERER_GALLIUM"
                              : (
                                 var0.startsWith("intel")
                                    ? "MC_GL_RENDERER_INTEL"
                                    : (
                                       var0.startsWith("geforce")
                                          ? "MC_GL_RENDERER_GEFORCE"
                                          : (
                                             var0.startsWith("nvidia")
                                                ? "MC_GL_RENDERER_GEFORCE"
                                                : (
                                                   var0.startsWith("quadro")
                                                      ? "MC_GL_RENDERER_QUADRO"
                                                      : (
                                                         var0.startsWith("nvs")
                                                            ? "MC_GL_RENDERER_QUADRO"
                                                            : (var0.startsWith("mesa") ? "MC_GL_RENDERER_MESA" : "MC_GL_RENDERER_OTHER")
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      }
   }

   public static String getVendor() {
      String var0 = Config.openGlVendor;
      if (var0 == null) {
         return "MC_GL_VENDOR_OTHER";
      } else {
         var0 = var0.toLowerCase();
         return var0.startsWith("ati")
            ? "MC_GL_VENDOR_ATI"
            : (
               var0.startsWith("intel")
                  ? "MC_GL_VENDOR_INTEL"
                  : (var0.startsWith("nvidia") ? "MC_GL_VENDOR_NVIDIA" : (var0.startsWith("x.org") ? "MC_GL_VENDOR_XORG" : "MC_GL_VENDOR_OTHER"))
            );
      }
   }

   public static String getPrefixMacro() {
      return PREFIX_MACRO;
   }

   public static String getFixedMacroLines() {
      StringBuilder var0 = new StringBuilder();
      addMacroLine(var0, "MC_VERSION", Config.getMinecraftVersionInt());
      addMacroLine(var0, "MC_GL_VERSION " + Config.getGlVersion().toInt());
      addMacroLine(var0, "MC_GLSL_VERSION " + Config.getGlslVersion().toInt());
      addMacroLine(var0, getOs());
      addMacroLine(var0, getVendor());
      addMacroLine(var0, getRenderer());
      return var0.toString();
   }

   public static void addMacroLine(StringBuilder var0, String var1, float var2) {
      var0.append("#define ");
      var0.append(var1);
      var0.append(" ");
      var0.append(var2);
      var0.append("\n");
   }

   public static String getOptionMacroLines() {
      StringBuilder var0 = new StringBuilder();
      if (Shaders.configAntialiasingLevel > 0) {
         addMacroLine(var0, "MC_FXAA_LEVEL", Shaders.configAntialiasingLevel);
      }

      if (Shaders.configNormalMap) {
         addMacroLine(var0, "MC_NORMAL_MAP");
      }

      if (Shaders.configSpecularMap) {
         addMacroLine(var0, "MC_SPECULAR_MAP");
      }

      addMacroLine(var0, "MC_RENDER_QUALITY", Shaders.configRenderResMul);
      addMacroLine(var0, "MC_SHADOW_QUALITY", Shaders.configShadowResMul);
      addMacroLine(var0, "MC_HAND_DEPTH", Shaders.configHandDepthMul);
      if (Shaders.isOldHandLight()) {
         addMacroLine(var0, "MC_OLD_HAND_LIGHT");
      }

      if (Shaders.isOldLighting()) {
         addMacroLine(var0, "MC_OLD_LIGHTING");
      }

      return var0.toString();
   }

   public static void addMacroLine(StringBuilder var0, String var1) {
      var0.append("#define ");
      var0.append(var1);
      var0.append("\n");
   }

   public static ShaderMacro[] getExtensions() {
      if (extensionMacros == null) {
         String[] var0 = Config.getOpenGlExtensions();
         ShaderMacro[] var1 = new ShaderMacro[var0.length];

         for (int var2 = 0; var2 < var0.length; var2++) {
            var1[var2] = new ShaderMacro(PREFIX_MACRO + var0[var2], "");
         }

         extensionMacros = var1;
      }

      return extensionMacros;
   }

   public static void addMacroLine(StringBuilder var0, String var1, int var2) {
      var0.append("#define ");
      var0.append(var1);
      var0.append(" ");
      var0.append(var2);
      var0.append("\n");
   }

   public static String getOs() {
      Util$EnumOS var0 = Util.getOSType();
      switch (ShaderMacros$1.$SwitchMap$net$minecraft$util$Util$EnumOS[var0.ordinal()]) {
         case 1:
            return "MC_OS_WINDOWS";
         case 2:
            return "MC_OS_MAC";
         case 3:
            return "MC_OS_LINUX";
         default:
            return "MC_OS_OTHER";
      }
   }
}
