package net.minecraft.client.resources.data;

public class FontMetadataSection implements IMetadataSection {
   public float[] charLefts;
   public float[] charSpacings;
   public float[] charWidths;

   public FontMetadataSection(float[] var1, float[] var2, float[] var3) {
      this.charWidths = var1;
      this.charLefts = var2;
      this.charSpacings = var3;
   }
}
