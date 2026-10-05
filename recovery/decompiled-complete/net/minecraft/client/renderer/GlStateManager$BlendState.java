package net.minecraft.client.renderer;

public class GlStateManager$BlendState {
   public int dstFactorAlpha;
   public int srcFactorAlpha;
   public int dstFactor;
   public GlStateManager$BooleanState blend = new GlStateManager$BooleanState(3042);
   public int srcFactor = 1;

   public GlStateManager$BlendState() {
      this.dstFactor = 0;
      this.srcFactorAlpha = 1;
      this.dstFactorAlpha = 0;
   }
}
