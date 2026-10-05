package com.cheatbreaker.client.util.render;

import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class LegacyGlStateManager {
   public static LegacyGlColorState recoveredField2583 = new LegacyGlColorState();
   public static LegacyGlBlendState recoveredField2584 = new LegacyGlBlendState();
   public static int recoveredField2582 = 7425;

   public static void method_28203(int var0, int var1, int var2, int var3) {
      if (var0 != recoveredField2584.recoveredField892
         || var1 != recoveredField2584.recoveredField896
         || var2 != recoveredField2584.recoveredField895
         || var3 != recoveredField2584.recoveredField893) {
         recoveredField2584.recoveredField892 = var0;
         recoveredField2584.recoveredField896 = var1;
         recoveredField2584.recoveredField895 = var2;
         recoveredField2584.recoveredField893 = var3;
         OpenGlHelper.glBlendFunc(var0, var1, var2, var3);
      }
   }

   public static void method_28200(float var0, float var1, float var2, float var3) {
      if (var0 != recoveredField2583.recoveredField772
         || var1 != recoveredField2583.recoveredField774
         || var2 != recoveredField2583.recoveredField773
         || var3 != recoveredField2583.recoveredField771) {
         recoveredField2583.recoveredField772 = var0;
         recoveredField2583.recoveredField774 = var1;
         recoveredField2583.recoveredField773 = var2;
         recoveredField2583.recoveredField771 = var3;
         GL11.glColor4f(var0, var1, var2, var3);
      }
   }

   public static void method_28199() {
      recoveredField2584.recoveredField894.method_03839();
   }

   public static void method_28204() {
      recoveredField2584.recoveredField894.method_03841();
   }

   public static void method_28201(int var0) {
      if (var0 != recoveredField2582) {
         recoveredField2582 = var0;
         GL11.glShadeModel(var0);
      }
   }

   public static void method_28202(int var0, int var1) {
      if (var0 != recoveredField2584.recoveredField892 || var1 != recoveredField2584.recoveredField896) {
         recoveredField2584.recoveredField892 = var0;
         recoveredField2584.recoveredField896 = var1;
         GL11.glBlendFunc(var0, var1);
      }
   }
}
