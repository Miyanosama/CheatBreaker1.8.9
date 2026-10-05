package javax.vecmath;

import io.netty.buffer.PooledDirectByteBuf$1;
import java.awt.Color;
import java.io.Serializable;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.optifine.shaders.config.RenderScale;

public class Color3f extends Tuple3f implements Serializable {
   public static long field_0001;
   public PooledDirectByteBuf$1 field_0003;
   public WeightedBakedModel field_0000;
   public RenderScale field_0002;

   public Color get() {
      int var1 = Math.round(this.x * 255.0F);
      int var2 = Math.round(this.y * 255.0F);
      int var3 = Math.round(this.z * 255.0F);
      return new Color(var1, var2, var3);
   }

   public Color3f(Tuple3f var1) {
      super(var1);
   }

   public void set(Color var1) {
      this.x = var1.getRed() / 255.0F;
      this.y = var1.getGreen() / 255.0F;
      this.z = var1.getBlue() / 255.0F;
   }

   public Color3f() {
   }

   public Color3f(Tuple3d var1) {
      super(var1);
   }

   public Color3f(float var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public Color3f(float[] var1) {
      super(var1);
   }

   public Color3f(Color var1) {
      super(var1.getRed() / 255.0F, var1.getGreen() / 255.0F, var1.getBlue() / 255.0F);
   }

   public Color3f(Color3f var1) {
      super(var1);
   }
}
