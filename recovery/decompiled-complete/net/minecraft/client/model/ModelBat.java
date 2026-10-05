package net.minecraft.client.model;

import net.minecraft.client.particle.EntityFirework$StarterFX;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.realms.RealmsSliderButton;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass0526;

public class ModelBat extends ModelBase {
   public ModelRenderer batRightWing;
   public SimpleResource field_0008;
   public ModelRenderer batOuterLeftWing;
   public UnidentifiedClass0526 field_0007;
   public EntityFirework$StarterFX field_0001;
   public ModelRenderer batHead;
   public ModelRenderer batOuterRightWing;
   public ModelRenderer batBody;
   public ModelRenderer batLeftWing;
   public RealmsSliderButton field_0000;

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      this.batHead.render(var7);
      this.batBody.render(var7);
   }

   public ModelBat() {
      this.t = 64;
      this.u = 64;
      this.batHead = new ModelRenderer(this, 0, 0);
      this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
      ModelRenderer var1 = new ModelRenderer(this, 24, 0);
      var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
      this.batHead.addChild(var1);
      ModelRenderer var2 = new ModelRenderer(this, 24, 0);
      var2.mirror = true;
      var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
      this.batHead.addChild(var2);
      this.batBody = new ModelRenderer(this, 0, 16);
      this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
      this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
      this.batRightWing = new ModelRenderer(this, 42, 0);
      this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
      this.batOuterRightWing = new ModelRenderer(this, 24, 16);
      this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
      this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
      this.batLeftWing = new ModelRenderer(this, 42, 0);
      this.batLeftWing.mirror = true;
      this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
      this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
      this.batOuterLeftWing.mirror = true;
      this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
      this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
      this.batBody.addChild(this.batRightWing);
      this.batBody.addChild(this.batLeftWing);
      this.batRightWing.addChild(this.batOuterRightWing);
      this.batLeftWing.addChild(this.batOuterLeftWing);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      if (((EntityBat)var7).getIsBatHanging()) {
         float var8 = 180.0F / (float)Math.PI;
         this.batHead.rotateAngleX = var5 / (180.0F / (float)Math.PI);
         this.batHead.rotateAngleY = (float) Math.PI - var4 / (180.0F / (float)Math.PI);
         this.batHead.rotateAngleZ = (float) Math.PI;
         this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
         this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
         this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
         this.batBody.rotateAngleX = (float) Math.PI;
         this.batRightWing.rotateAngleX = (float) (-Math.PI / 20);
         this.batRightWing.rotateAngleY = (float) (-Math.PI * 2.0 / 5.0);
         this.batOuterRightWing.rotateAngleY = -1.7278761F;
         this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
      } else {
         float var9 = 180.0F / (float)Math.PI;
         this.batHead.rotateAngleX = var5 / (180.0F / (float)Math.PI);
         this.batHead.rotateAngleY = var4 / (180.0F / (float)Math.PI);
         this.batHead.rotateAngleZ = 0.0F;
         this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batBody.rotateAngleX = (float) (Math.PI / 4) + MathHelper.cos(var3 * 0.1F) * 0.15F;
         this.batBody.rotateAngleY = 0.0F;
         this.batRightWing.rotateAngleY = MathHelper.cos(var3 * 1.3F) * (float) Math.PI * 0.25F;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
         this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
      }
   }
}
