package net.minecraft.client.model;

public class ModelLargeChest extends ModelChest {
   public ModelLargeChest() {
      this.a = new ModelRenderer(this, 0, 0).setTextureSize(128, 64);
      this.a.addBox(0.0F, -5.0F, -14.0F, 30, 5, 14, 0.0F);
      this.a.rotationPointX = 1.0F;
      this.a.rotationPointY = 7.0F;
      this.a.rotationPointZ = 15.0F;
      this.c = new ModelRenderer(this, 0, 0).setTextureSize(128, 64);
      this.c.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
      this.c.rotationPointX = 16.0F;
      this.c.rotationPointY = 7.0F;
      this.c.rotationPointZ = 15.0F;
      this.b = new ModelRenderer(this, 0, 19).setTextureSize(128, 64);
      this.b.addBox(0.0F, 0.0F, 0.0F, 30, 10, 14, 0.0F);
      this.b.rotationPointX = 1.0F;
      this.b.rotationPointY = 6.0F;
      this.b.rotationPointZ = 1.0F;
   }
}
