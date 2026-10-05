package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.world.gen.feature.WorldGenMinable;
import org.apache.log4j.chainsaw.ControlPanel$1;
import recovered.unidentified.UnidentifiedClass1472;

public class ModelMinecart extends ModelBase {
   public ModelRenderer[] sideModels = new ModelRenderer[7];
   public ControlPanel$1 field_0003;
   public UnidentifiedClass1472 field_0000;
   public WorldGenMinable field_0002;

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.sideModels[5].rotationPointY = 4.0F - var4;

      for (int var8 = 0; var8 < 6; var8++) {
         this.sideModels[var8].render(var7);
      }
   }

   public ModelMinecart() {
      this.sideModels[0] = new ModelRenderer(this, 0, 10);
      this.sideModels[1] = new ModelRenderer(this, 0, 0);
      this.sideModels[2] = new ModelRenderer(this, 0, 0);
      this.sideModels[3] = new ModelRenderer(this, 0, 0);
      this.sideModels[4] = new ModelRenderer(this, 0, 0);
      this.sideModels[5] = new ModelRenderer(this, 44, 10);
      byte var1 = 20;
      byte var2 = 8;
      byte var3 = 16;
      byte var4 = 4;
      this.sideModels[0].addBox(-var1 / 2, -var3 / 2, -1.0F, var1, var3, 2, 0.0F);
      this.sideModels[0].setRotationPoint(0.0F, var4, 0.0F);
      this.sideModels[5].addBox(-var1 / 2 + 1, -var3 / 2 + 1, -1.0F, var1 - 2, var3 - 2, 1, 0.0F);
      this.sideModels[5].setRotationPoint(0.0F, var4, 0.0F);
      this.sideModels[1].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.sideModels[1].setRotationPoint(-var1 / 2 + 1, var4, 0.0F);
      this.sideModels[2].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.sideModels[2].setRotationPoint(var1 / 2 - 1, var4, 0.0F);
      this.sideModels[3].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.sideModels[3].setRotationPoint(0.0F, var4, -var3 / 2 + 1);
      this.sideModels[4].addBox(-var1 / 2 + 2, -var2 - 1, -1.0F, var1 - 4, var2, 2, 0.0F);
      this.sideModels[4].setRotationPoint(0.0F, var4, var3 / 2 - 1);
      this.sideModels[0].rotateAngleX = (float) (Math.PI / 2);
      this.sideModels[1].rotateAngleY = (float) (Math.PI * 3.0 / 2.0);
      this.sideModels[2].rotateAngleY = (float) (Math.PI / 2);
      this.sideModels[3].rotateAngleY = (float) Math.PI;
      this.sideModels[5].rotateAngleX = (float) (-Math.PI / 2);
   }
}
