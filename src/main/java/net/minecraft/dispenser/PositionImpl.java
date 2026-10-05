package net.minecraft.dispenser;

public class PositionImpl implements IPosition {
   public double y;
   public double x;
   public double z;

   @Override
   public double getZ() {
      return this.z;
   }

   @Override
   public double getY() {
      return this.y;
   }

   @Override
   public double getX() {
      return this.x;
   }

   public PositionImpl(double var1, double var3, double var5) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
   }
}
