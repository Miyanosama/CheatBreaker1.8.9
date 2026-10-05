package net.minecraft.client.model;

public class ModelChest extends ModelBase {
   public ModelRenderer a = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
   public ModelRenderer c;
   public ModelRenderer b;

   public void renderAll() {
      this.c.rotateAngleX = this.a.rotateAngleX;
      this.a.render(0.0625F);
      this.c.render(0.0625F);
      this.b.render(0.0625F);
   }

   public ModelChest() {
      this.a.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
      this.a.rotationPointX = 1.0F;
      this.a.rotationPointY = 7.0F;
      this.a.rotationPointZ = 15.0F;
      this.c = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
      this.c.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
      this.c.rotationPointX = 8.0F;
      this.c.rotationPointY = 7.0F;
      this.c.rotationPointZ = 15.0F;
      this.b = new ModelRenderer(this, 0, 19).setTextureSize(64, 64);
      this.b.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
      this.b.rotationPointX = 1.0F;
      this.b.rotationPointY = 6.0F;
      this.b.rotationPointZ = 1.0F;
   }
}
