package net.optifine.shaders.config;

import net.minecraft.src.Config;
import net.minecraft.util.Util;
import net.optifine.shaders.Shaders;

public class ShaderMacros {
   public static final String recoveredField3564 = "MC_GL_VENDOR_OTHER";
   public static final String recoveredField3565 = "MC_SHADOW_QUALITY";
   public static final String recoveredField3566 = "MC_OS_OTHER";
   public static final String recoveredField3567 = "MC_GL_RENDERER_GEFORCE";
   public static final String recoveredField3568 = "MC_GL_RENDERER_OTHER";
   public static final String recoveredField3569 = "MC_FXAA_LEVEL";
   public static final String recoveredField3570 = "MC_SPECULAR_MAP";
   public static final String recoveredField3571 = "MC_GL_VENDOR_XORG";
   public static final String recoveredField3572 = "MC_GLSL_VERSION";
   public static final String recoveredField3573 = "MC_HAND_DEPTH";
   public static final String recoveredField3574 = "MC_GL_VENDOR_NVIDIA";
   public static final String recoveredField3575 = "MC_OS_LINUX";
   public static final String recoveredField3576 = "MC_GL_RENDERER_INTEL";
   public static ShaderMacro[] extensionMacros;
   public static final String recoveredField3577 = "MC_RENDER_QUALITY";
   public static final String recoveredField3578 = "MC_GL_VENDOR_INTEL";
   public static final String recoveredField3579 = "MC_GL_RENDERER_GALLIUM";
   public static final String recoveredField3580 = "MC_VERSION";
   public static final String recoveredField3581 = "MC_GL_RENDERER_QUADRO";
   public static final String recoveredField3582 = "MC_GL_RENDERER_MESA";
   public static final String recoveredField3583 = "MC_GL_VENDOR_ATI";
   public static final String recoveredField3584 = "MC_OLD_LIGHTING";
   public static final String recoveredField3585 = "MC_GL_VERSION";
   public static final String recoveredField3586 = "MC_NORMAL_MAP";
   public static final String recoveredField3587 = "MC_OS_WINDOWS";
   public static final String recoveredField3588 = "MC_GL_RENDERER_RADEON";
   public static final String recoveredField3589 = "MC_OLD_HAND_LIGHT";
   public static final String recoveredField3590 = "MC_OS_MAC";
   public static String PREFIX_MACRO = "MC_";

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
      Util.EnumOS var0 = Util.getOSType();
      switch (var0) {
         case WINDOWS:
            return "MC_OS_WINDOWS";
         case OSX:
            return "MC_OS_MAC";
         case LINUX:
            return "MC_OS_LINUX";
         default:
            return "MC_OS_OTHER";
      }
   }
}
