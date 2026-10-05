package com.cheatbreaker.client.util.math;

public class Vec2f {
   public float recoveredField1800;
   public float recoveredField1801;

   public void method_28167(float var1, float var2) {
      this.recoveredField1801 = var1;
      this.recoveredField1800 = var2;
   }

   public static float method_28168(float var0, float var1, float var2, float var3) {
      var0 -= var2;
      var1 -= var3;
      return var0 * var0 + var1 * var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Vec2f)) {
         return false;
      } else {
         Vec2f var2 = (Vec2f)var1;
         return this.recoveredField1801 == var2.recoveredField1801 && this.recoveredField1800 == var2.recoveredField1800;
      }
   }

   public Vec2f(Vec2f var1) {
      this.recoveredField1801 = var1.recoveredField1801;
      this.recoveredField1800 = var1.recoveredField1800;
   }

   public Vec2f(float var1, float var2) {
      this.recoveredField1801 = var1;
      this.recoveredField1800 = var2;
   }

   public float method_28170(float var1, float var2) {
      var1 -= this.recoveredField1801;
      var2 -= this.recoveredField1800;
      return var1 * var1 + var2 * var2;
   }

   public static float method_28171(float var0, float var1, float var2, float var3) {
      var0 -= var2;
      var1 -= var3;
      return (float)Math.sqrt(var0 * var0 + var1 * var1);
   }

   public Vec2f() {
   }

   public float method_28169(Vec2f var1) {
      float var2 = var1.recoveredField1801 - this.recoveredField1801;
      float var3 = var1.recoveredField1800 - this.recoveredField1800;
      return var2 * var2 + var3 * var3;
   }

   @Override
   public String toString() {
      return "Vec2f[" + this.recoveredField1801 + ", " + this.recoveredField1800 + "]";
   }

   public void method_28172(Vec2f var1) {
      this.recoveredField1801 = var1.recoveredField1801;
      this.recoveredField1800 = var1.recoveredField1800;
   }

   public float method_28164(Vec2f var1) {
      float var2 = var1.recoveredField1801 - this.recoveredField1801;
      float var3 = var1.recoveredField1800 - this.recoveredField1800;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   @Override
   public int hashCode() {
      byte var1 = 7;
      int var2 = 31 * var1 + Float.floatToIntBits(this.recoveredField1801);
      return 31 * var2 + Float.floatToIntBits(this.recoveredField1800);
   }

   public float method_28163(float var1, float var2) {
      var1 -= this.recoveredField1801;
      var2 -= this.recoveredField1800;
      return (float)Math.sqrt(var1 * var1 + var2 * var2);
   }
}
