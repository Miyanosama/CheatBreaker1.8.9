package net.optifine.config;

public class RangeInt {
   public int max;
   public int min;

   @Override
   public String toString() {
      return "min: " + this.min + ", max: " + this.max;
   }

   public RangeInt(int var1, int var2) {
      this.min = Math.min(var1, var2);
      this.max = Math.max(var1, var2);
   }

   public int getMin() {
      return this.min;
   }

   public boolean isInRange(int var1) {
      return var1 < this.min ? false : var1 <= this.max;
   }

   public int getMax() {
      return this.max;
   }
}
