package net.optifine.render;

public class GlBlendState {
   public int dstFactor;
   public boolean enabled;
   public int srcFactorAlpha;
   public int dstFactorAlpha;
   public int srcFactor;

   public void setEnabled() {
      this.enabled = true;
   }

   public GlBlendState(boolean var1) {
      this(var1, 1, 0);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getDstFactorAlpha() {
      return this.dstFactorAlpha;
   }

   public int getDstFactor() {
      return this.dstFactor;
   }

   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   public void setFactors(int var1, int var2) {
      this.srcFactor = var1;
      this.dstFactor = var2;
      this.srcFactorAlpha = var1;
      this.dstFactorAlpha = var2;
   }

   public GlBlendState(boolean var1, int var2, int var3, int var4, int var5) {
      this.enabled = var1;
      this.srcFactor = var2;
      this.dstFactor = var3;
      this.srcFactorAlpha = var4;
      this.dstFactorAlpha = var5;
   }

   public void setDisabled() {
      this.enabled = false;
   }

   public int getSrcFactorAlpha() {
      return this.srcFactorAlpha;
   }

   public int getSrcFactor() {
      return this.srcFactor;
   }

   public void setState(GlBlendState var1) {
      this.enabled = var1.enabled;
      this.srcFactor = var1.srcFactor;
      this.dstFactor = var1.dstFactor;
      this.srcFactorAlpha = var1.srcFactorAlpha;
      this.dstFactorAlpha = var1.dstFactorAlpha;
   }

   public void setState(boolean var1, int var2, int var3, int var4, int var5) {
      this.enabled = var1;
      this.srcFactor = var2;
      this.dstFactor = var3;
      this.srcFactorAlpha = var4;
      this.dstFactorAlpha = var5;
   }

   public GlBlendState() {
      this(false, 1, 0);
   }

   @Override
   public String toString() {
      return "enabled: "
         + this.enabled
         + ", src: "
         + this.srcFactor
         + ", dst: "
         + this.dstFactor
         + ", srcAlpha: "
         + this.srcFactorAlpha
         + ", dstAlpha: "
         + this.dstFactorAlpha;
   }

   public GlBlendState(boolean var1, int var2, int var3) {
      this(var1, var2, var3, var2, var3);
   }

   public boolean isSeparate() {
      return this.srcFactor != this.srcFactorAlpha || this.dstFactor != this.dstFactorAlpha;
   }

   public void setFactors(int var1, int var2, int var3, int var4) {
      this.srcFactor = var1;
      this.dstFactor = var2;
      this.srcFactorAlpha = var3;
      this.dstFactorAlpha = var4;
   }
}
