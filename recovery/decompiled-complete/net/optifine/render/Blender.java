package net.optifine.render;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.src.Config;
import net.optifine.shaders.uniform.ShaderUniform1i;

public class Blender {
   public static int field_0005;
   public static int field_0010;
   public GuiMultiplayer field_0004;
   public static int field_0009;
   public ShaderUniform1i field_0001;
   public static int field_0002;
   public static int field_0011;
   public static int field_0008;
   public static int field_0003;
   public EntityBreakingFX field_0012;
   public static int field_0000;
   public static int field_0006;
   public static int field_0007;

   public static int parseBlend(String var0) {
      if (var0 == null) {
         return 1;
      } else {
         var0 = var0.toLowerCase().trim();
         if (var0.equals("alpha")) {
            return 0;
         } else if (var0.equals("add")) {
            return 1;
         } else if (var0.equals("subtract")) {
            return 2;
         } else if (var0.equals("multiply")) {
            return 3;
         } else if (var0.equals("dodge")) {
            return 4;
         } else if (var0.equals("burn")) {
            return 5;
         } else if (var0.equals("screen")) {
            return 6;
         } else if (var0.equals("overlay")) {
            return 7;
         } else if (var0.equals("replace")) {
            return 8;
         } else {
            Config.warn("Unknown blend: " + var0);
            return 1;
         }
      }
   }

   public static void clearBlend(float var0) {
      GlStateManager.disableAlpha();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 1);
      GlStateManager.color(1.0F, 1.0F, 1.0F, var0);
   }

   public static void setupBlend(int var0, float var1) {
      switch (var0) {
         case 0:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(1.0F, 1.0F, 1.0F, var1);
            break;
         case 1:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 1);
            GlStateManager.color(1.0F, 1.0F, 1.0F, var1);
            break;
         case 2:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(775, 0);
            GlStateManager.color(var1, var1, var1, 1.0F);
            break;
         case 3:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(774, 771);
            GlStateManager.color(var1, var1, var1, var1);
            break;
         case 4:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(1, 1);
            GlStateManager.color(var1, var1, var1, 1.0F);
            break;
         case 5:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(0, 769);
            GlStateManager.color(var1, var1, var1, 1.0F);
            break;
         case 6:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(1, 769);
            GlStateManager.color(var1, var1, var1, 1.0F);
            break;
         case 7:
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(774, 768);
            GlStateManager.color(var1, var1, var1, 1.0F);
            break;
         case 8:
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, var1);
      }

      GlStateManager.enableTexture2D();
   }
}
