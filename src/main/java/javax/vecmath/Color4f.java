package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4f extends Tuple4f implements Serializable {
   public static final long recoveredField1682 = 8577680141580006740L;

   public Color4f(float var1, float var2, float var3, float var4) {
      super(var1, var2, var3, var4);
   }

   public Color get() {
      int var1 = Math.round(this.x * 255.0F);
      int var2 = Math.round(this.y * 255.0F);
      int var3 = Math.round(this.z * 255.0F);
      int var4 = Math.round(this.w * 255.0F);
      return new Color(var1, var2, var3, var4);
   }

   public Color4f(Tuple4f var1) {
      super(var1);
   }

   public void set(Color var1) {
      this.x = var1.getRed() / 255.0F;
      this.y = var1.getGreen() / 255.0F;
      this.z = var1.getBlue() / 255.0F;
      this.w = var1.getAlpha() / 255.0F;
   }

   public Color4f(Tuple4d var1) {
      super(var1);
   }

   public Color4f() {
   }

   public Color4f(float[] var1) {
      super(var1);
   }

   public Color4f(Color var1) {
      super(var1.getRed() / 255.0F, var1.getGreen() / 255.0F, var1.getBlue() / 255.0F, var1.getAlpha() / 255.0F);
   }

   public Color4f(Color4f var1) {
      super(var1);
   }
}
