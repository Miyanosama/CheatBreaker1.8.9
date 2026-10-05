package net.optifine.shaders.config;

public class RenderScale {
   public float scale = 1.0F;
   public float offsetX = 0.0F;
   public float offsetY = 0.0F;

   @Override
   public String toString() {
      return "" + this.scale + ", " + this.offsetX + ", " + this.offsetY;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   public float getOffsetY() {
      return this.offsetY;
   }

   public RenderScale(float var1, float var2, float var3) {
      this.scale = var1;
      this.offsetX = var2;
      this.offsetY = var3;
   }

   public float getScale() {
      return this.scale;
   }
}
