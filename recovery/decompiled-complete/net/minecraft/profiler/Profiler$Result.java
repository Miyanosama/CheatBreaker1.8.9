package net.minecraft.profiler;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType;

public class Profiler$Result implements Comparable<Profiler$Result> {
   public String field_76331_c;
   public double field_76332_a;
   public ItemCameraTransforms$TransformType field_0000;
   public double field_76330_b;

   public int compareTo(Profiler$Result var1) {
      return var1.field_76332_a < this.field_76332_a ? -1 : (var1.field_76332_a > this.field_76332_a ? 1 : var1.field_76331_c.compareTo(this.field_76331_c));
   }

   public Profiler$Result(String var1, double var2, double var4) {
      this.field_76331_c = var1;
      this.field_76332_a = var2;
      this.field_76330_b = var4;
   }

   public int getColor() {
      return (this.field_76331_c.hashCode() & 11184810) + 4473924;
   }
}
