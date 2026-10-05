package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelBoat extends ModelBase {
   public ModelRenderer[] boatSides = new ModelRenderer[5];

   public ModelBoat() {
      this.boatSides[0] = new ModelRenderer(this, 0, 8);
      this.boatSides[1] = new ModelRenderer(this, 0, 0);
      this.boatSides[2] = new ModelRenderer(this, 0, 0);
      this.boatSides[3] = new ModelRenderer(this, 0, 0);
      this.boatSides[4] = new ModelRenderer(this, 0, 0);
      byte var1 = 24;
      byte var2 = 6;
      byte var3 = 20;
      byte var4 = 4;
      this.boatSides[0].addBox(-var1 / 2, -var3 / 2 + 2, -3.0F, var1, var3 - 4, 4, 0.0F);
      this.boatSides[0].setRotationPoint(0.0F, var4, 0.0F);
      this.boatSides[1].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.boatSides[1].setRotationPoint(-var1 / 2 + 1, var4, 0.0F);
      this.boatSides[2].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.boatSides[2].setRotationPoint(var1 / 2 - 1, var4, 0.0F);
      this.boatSides[3].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.boatSides[3].setRotationPoint(0.0F, var4, -var3 / 2 + 1);
      this.boatSides[4].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.boatSides[4].setRotationPoint(0.0F, var4, var3 / 2 - 1);
      this.boatSides[0].rotateAngleX = (float) (Math.PI / 2);
      this.boatSides[1].rotateAngleY = (float) (Math.PI * 3.0 / 2.0);
      this.boatSides[2].rotateAngleY = (float) (Math.PI / 2);
      this.boatSides[3].rotateAngleY = (float) Math.PI;
   }

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      for (int var8 = 0; var8 < 5; var8++) {
         this.boatSides[var8].render(var7);
      }
   }
}
