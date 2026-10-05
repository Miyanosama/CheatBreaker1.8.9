package net.minecraft.client.renderer;

import net.minecraft.client.renderer.chunk.VisGraph$1;
import org.apache.log4j.lf5.util.LogMonitorAdapter;

public class GlStateManager$Color {
   public LogMonitorAdapter field_0003;
   public float red = 1.0F;
   public float blue;
   public float green = 1.0F;
   public VisGraph$1 field_0000;
   public float alpha;

   public GlStateManager$Color() {
      this.blue = 1.0F;
      this.alpha = 1.0F;
   }

   public GlStateManager$Color(float var1, float var2, float var3, float var4) {
      this.blue = 1.0F;
      this.alpha = 1.0F;
      this.red = var1;
      this.green = var2;
      this.blue = var3;
      this.alpha = var4;
   }
}
