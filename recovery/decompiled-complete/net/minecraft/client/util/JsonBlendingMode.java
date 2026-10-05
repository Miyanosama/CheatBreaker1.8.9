package net.minecraft.client.util;

import com.google.gson.JsonObject;
import io.netty.channel.AbstractChannel$AbstractUnsafe$5;
import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.JsonUtils;
import net.optifine.shaders.config.ShaderOptionResolver;
import org.lwjgl.opengl.GL14;

public class JsonBlendingMode {
   public MultithreadEventExecutorGroup field_0005;
   public static JsonBlendingMode field_148118_a = null;
   public int field_148117_c;
   public AbstractChannel$AbstractUnsafe$5 field_0007;
   public boolean field_148119_h;
   public boolean field_148113_g;
   public int field_148116_b;
   public int field_148114_d;
   public int field_148115_e;
   public int field_148112_f;
   public ShaderOptionResolver field_0000;

   public static int method_08051(String var0) {
      String var1 = var0.trim().toLowerCase();
      return var1.equals("add")
         ? 32774
         : (
            var1.equals("subtract")
               ? 32778
               : (
                  var1.equals("reversesubtract")
                     ? 32779
                     : (var1.equals("reverse_subtract") ? 32779 : (var1.equals("min") ? 32775 : (var1.equals("max") ? 32776 : 32774)))
               )
         );
   }

   public JsonBlendingMode(int var1, int var2, int var3, int var4, int var5) {
      this(true, false, var1, var2, var3, var4, var5);
   }

   public static int method_08053(String var0) {
      String var1 = var0.trim().toLowerCase();
      var1 = var1.replaceAll("_", "");
      var1 = var1.replaceAll("one", "1");
      var1 = var1.replaceAll("zero", "0");
      var1 = var1.replaceAll("minus", "-");
      return var1.equals("0")
         ? 0
         : (
            var1.equals("1")
               ? 1
               : (
                  var1.equals("srccolor")
                     ? 768
                     : (
                        var1.equals("1-srccolor")
                           ? 769
                           : (
                              var1.equals("dstcolor")
                                 ? 774
                                 : (
                                    var1.equals("1-dstcolor")
                                       ? 775
                                       : (
                                          var1.equals("srcalpha")
                                             ? 770
                                             : (var1.equals("1-srcalpha") ? 771 : (var1.equals("dstalpha") ? 772 : (var1.equals("1-dstalpha") ? 773 : -1)))
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public JsonBlendingMode() {
      this(false, true, 1, 0, 1, 0, 32774);
   }

   @Override
   public int hashCode() {
      int var1 = this.field_148116_b;
      var1 = 31 * var1 + this.field_148117_c;
      var1 = 31 * var1 + this.field_148114_d;
      var1 = 31 * var1 + this.field_148115_e;
      var1 = 31 * var1 + this.field_148112_f;
      var1 = 31 * var1 + (this.field_148113_g ? 1 : 0);
      return 31 * var1 + (this.field_148119_h ? 1 : 0);
   }

   public JsonBlendingMode(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7) {
      this.field_148113_g = var1;
      this.field_148116_b = var3;
      this.field_148114_d = var4;
      this.field_148117_c = var5;
      this.field_148115_e = var6;
      this.field_148119_h = var2;
      this.field_148112_f = var7;
   }

   public JsonBlendingMode(int var1, int var2, int var3) {
      this(false, false, var1, var2, var1, var2, var3);
   }

   public boolean func_148111_b() {
      return this.field_148119_h;
   }

   public static JsonBlendingMode func_148110_a(JsonObject var0) {
      if (var0 == null) {
         return new JsonBlendingMode();
      } else {
         int var1 = 32774;
         int var2 = 1;
         int var3 = 0;
         int var4 = 1;
         int var5 = 0;
         boolean var6 = true;
         boolean var7 = false;
         if (JsonUtils.method_05470(var0, "func")) {
            var1 = method_08051(var0.get("func").getAsString());
            if (var1 != 32774) {
               var6 = false;
            }
         }

         if (JsonUtils.method_05470(var0, "srcrgb")) {
            var2 = method_08053(var0.get("srcrgb").getAsString());
            if (var2 != 1) {
               var6 = false;
            }
         }

         if (JsonUtils.method_05470(var0, "dstrgb")) {
            var3 = method_08053(var0.get("dstrgb").getAsString());
            if (var3 != 0) {
               var6 = false;
            }
         }

         if (JsonUtils.method_05470(var0, "srcalpha")) {
            var4 = method_08053(var0.get("srcalpha").getAsString());
            if (var4 != 1) {
               var6 = false;
            }

            var7 = true;
         }

         if (JsonUtils.method_05470(var0, "dstalpha")) {
            var5 = method_08053(var0.get("dstalpha").getAsString());
            if (var5 != 0) {
               var6 = false;
            }

            var7 = true;
         }

         return var6 ? new JsonBlendingMode() : (var7 ? new JsonBlendingMode(var2, var3, var4, var5, var1) : new JsonBlendingMode(var2, var3, var1));
      }
   }

   public void func_148109_a() {
      if (!this.equals(field_148118_a)) {
         if (field_148118_a == null || this.field_148119_h != field_148118_a.func_148111_b()) {
            field_148118_a = this;
            if (this.field_148119_h) {
               GlStateManager.disableBlend();
               return;
            }

            GlStateManager.enableBlend();
         }

         GL14.glBlendEquation(this.field_148112_f);
         if (this.field_148113_g) {
            GlStateManager.tryBlendFuncSeparate(this.field_148116_b, this.field_148114_d, this.field_148117_c, this.field_148115_e);
         } else {
            GlStateManager.blendFunc(this.field_148116_b, this.field_148114_d);
         }
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof JsonBlendingMode)) {
         return false;
      } else {
         JsonBlendingMode var2 = (JsonBlendingMode)var1;
         return this.field_148112_f != var2.field_148112_f
            ? false
            : (
               this.field_148115_e != var2.field_148115_e
                  ? false
                  : (
                     this.field_148114_d != var2.field_148114_d
                        ? false
                        : (
                           this.field_148119_h != var2.field_148119_h
                              ? false
                              : (
                                 this.field_148113_g != var2.field_148113_g
                                    ? false
                                    : (this.field_148117_c != var2.field_148117_c ? false : this.field_148116_b == var2.field_148116_b)
                              )
                        )
                  )
            );
      }
   }
}
