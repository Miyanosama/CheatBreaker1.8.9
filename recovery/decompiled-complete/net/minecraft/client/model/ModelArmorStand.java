package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import recovered.unidentified.UnidentifiedClass3599;

public class ModelArmorStand extends ModelArmorStandArmor {
   public ModelRenderer standLeftSide;
   public ModelRenderer standWaist;
   public UnidentifiedClass3599 field_0001;
   public ModelRenderer standRightSide;
   public ModelRenderer standBase;

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      super.render(var1, var2, var3, var4, var5, var6, var7);
      GlStateManager.pushMatrix();
      if (this.r) {
         float var8 = 2.0F;
         GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GlStateManager.translate(0.0F, 24.0F * var7, 0.0F);
         this.standRightSide.render(var7);
         this.standLeftSide.render(var7);
         this.standWaist.render(var7);
         this.standBase.render(var7);
      } else {
         if (var1.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.standRightSide.render(var7);
         this.standLeftSide.render(var7);
         this.standWaist.render(var7);
         this.standBase.render(var7);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      if (var7 instanceof EntityArmorStand) {
         EntityArmorStand var8 = (EntityArmorStand)var7;
         this.i.showModel = var8.getShowArms();
         this.h.showModel = var8.getShowArms();
         this.standBase.showModel = !var8.hasNoBasePlate();
         this.k.setRotationPoint(1.9F, 12.0F, 0.0F);
         this.j.setRotationPoint(-1.9F, 12.0F, 0.0F);
         this.standRightSide.rotateAngleX = (float) (Math.PI / 180.0) * var8.getBodyRotation().getX();
         this.standRightSide.rotateAngleY = (float) (Math.PI / 180.0) * var8.getBodyRotation().getY();
         this.standRightSide.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getBodyRotation().getZ();
         this.standLeftSide.rotateAngleX = (float) (Math.PI / 180.0) * var8.getBodyRotation().getX();
         this.standLeftSide.rotateAngleY = (float) (Math.PI / 180.0) * var8.getBodyRotation().getY();
         this.standLeftSide.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getBodyRotation().getZ();
         this.standWaist.rotateAngleX = (float) (Math.PI / 180.0) * var8.getBodyRotation().getX();
         this.standWaist.rotateAngleY = (float) (Math.PI / 180.0) * var8.getBodyRotation().getY();
         this.standWaist.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getBodyRotation().getZ();
         float var9 = (var8.getLeftLegRotation().getX() + var8.getRightLegRotation().getX()) / 2.0F;
         float var10 = (var8.getLeftLegRotation().getY() + var8.getRightLegRotation().getY()) / 2.0F;
         float var11 = (var8.getLeftLegRotation().getZ() + var8.getRightLegRotation().getZ()) / 2.0F;
         this.standBase.rotateAngleX = 0.0F;
         this.standBase.rotateAngleY = (float) (Math.PI / 180.0) * -var7.y;
         this.standBase.rotateAngleZ = 0.0F;
      }
   }

   public ModelArmorStand(float var1) {
      super(var1, 64, 64);
      this.e = new ModelRenderer(this, 0, 0);
      this.e.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, var1);
      this.e.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.g = new ModelRenderer(this, 0, 26);
      this.g.addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, var1);
      this.g.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.h = new ModelRenderer(this, 24, 0);
      this.h.addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, var1);
      this.h.setRotationPoint(-5.0F, 2.0F, 0.0F);
      this.i = new ModelRenderer(this, 32, 16);
      this.i.mirror = true;
      this.i.addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, var1);
      this.i.setRotationPoint(5.0F, 2.0F, 0.0F);
      this.j = new ModelRenderer(this, 8, 0);
      this.j.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, var1);
      this.j.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.k = new ModelRenderer(this, 40, 16);
      this.k.mirror = true;
      this.k.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, var1);
      this.k.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.standRightSide = new ModelRenderer(this, 16, 0);
      this.standRightSide.addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, var1);
      this.standRightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standRightSide.showModel = true;
      this.standLeftSide = new ModelRenderer(this, 48, 16);
      this.standLeftSide.addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, var1);
      this.standLeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standWaist = new ModelRenderer(this, 0, 48);
      this.standWaist.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, var1);
      this.standWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standBase = new ModelRenderer(this, 0, 32);
      this.standBase.addBox(-6.0F, 11.0F, -6.0F, 12, 1, 12, var1);
      this.standBase.setRotationPoint(0.0F, 12.0F, 0.0F);
   }

   public ModelArmorStand() {
      this(0.0F);
   }

   @Override
   public void postRenderArm(float var1) {
      boolean var2 = this.h.showModel;
      this.h.showModel = true;
      super.postRenderArm(var1);
      this.h.showModel = var2;
   }
}
