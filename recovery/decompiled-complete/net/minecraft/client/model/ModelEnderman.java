package net.minecraft.client.model;

import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import io.netty.handler.codec.serialization.WeakReferenceMap;
import io.netty.util.Recycler$WeakOrderQueue$Link;
import net.minecraft.entity.Entity;
import net.minecraft.util.HttpUtil$1;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

public class ModelEnderman extends ModelBiped {
   public boolean isAttacking;
   public Recycler$WeakOrderQueue$Link field_0005;
   public WeakReferenceMap field_0002;
   public boolean isCarrying;
   public CBAnchorHelper field_0000;
   public WorldGenHugeTrees field_0001;
   public HttpUtil$1 field_0006;

   public ModelEnderman(float var1) {
      super(0.0F, -14.0F, 64, 32);
      float var2 = -14.0F;
      this.f = new ModelRenderer(this, 0, 16);
      this.f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1 - 0.5F);
      this.f.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      this.g = new ModelRenderer(this, 32, 16);
      this.g.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1);
      this.g.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      this.h = new ModelRenderer(this, 56, 0);
      this.h.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var1);
      this.h.setRotationPoint(-3.0F, 2.0F + var2, 0.0F);
      this.i = new ModelRenderer(this, 56, 0);
      this.i.mirror = true;
      this.i.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var1);
      this.i.setRotationPoint(5.0F, 2.0F + var2, 0.0F);
      this.j = new ModelRenderer(this, 56, 0);
      this.j.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var1);
      this.j.setRotationPoint(-2.0F, 12.0F + var2, 0.0F);
      this.k = new ModelRenderer(this, 56, 0);
      this.k.mirror = true;
      this.k.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var1);
      this.k.setRotationPoint(2.0F, 12.0F + var2, 0.0F);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      this.e.showModel = true;
      float var8 = -14.0F;
      this.g.rotateAngleX = 0.0F;
      this.g.rotationPointY = var8;
      this.g.rotationPointZ = -0.0F;
      this.j.rotateAngleX -= 0.0F;
      this.k.rotateAngleX -= 0.0F;
      this.h.rotateAngleX = (float)(this.h.rotateAngleX * 0.5);
      this.i.rotateAngleX = (float)(this.i.rotateAngleX * 0.5);
      this.j.rotateAngleX = (float)(this.j.rotateAngleX * 0.5);
      this.k.rotateAngleX = (float)(this.k.rotateAngleX * 0.5);
      float var9 = 0.4F;
      if (this.h.rotateAngleX > var9) {
         this.h.rotateAngleX = var9;
      }

      if (this.i.rotateAngleX > var9) {
         this.i.rotateAngleX = var9;
      }

      if (this.h.rotateAngleX < -var9) {
         this.h.rotateAngleX = -var9;
      }

      if (this.i.rotateAngleX < -var9) {
         this.i.rotateAngleX = -var9;
      }

      if (this.j.rotateAngleX > var9) {
         this.j.rotateAngleX = var9;
      }

      if (this.k.rotateAngleX > var9) {
         this.k.rotateAngleX = var9;
      }

      if (this.j.rotateAngleX < -var9) {
         this.j.rotateAngleX = -var9;
      }

      if (this.k.rotateAngleX < -var9) {
         this.k.rotateAngleX = -var9;
      }

      if (this.isCarrying) {
         this.h.rotateAngleX = -0.5F;
         this.i.rotateAngleX = -0.5F;
         this.h.rotateAngleZ = 0.05F;
         this.i.rotateAngleZ = -0.05F;
      }

      this.h.rotationPointZ = 0.0F;
      this.i.rotationPointZ = 0.0F;
      this.j.rotationPointZ = 0.0F;
      this.k.rotationPointZ = 0.0F;
      this.j.rotationPointY = 9.0F + var8;
      this.k.rotationPointY = 9.0F + var8;
      this.e.rotationPointZ = -0.0F;
      this.e.rotationPointY = var8 + 1.0F;
      this.f.rotationPointX = this.e.rotationPointX;
      this.f.rotationPointY = this.e.rotationPointY;
      this.f.rotationPointZ = this.e.rotationPointZ;
      this.f.rotateAngleX = this.e.rotateAngleX;
      this.f.rotateAngleY = this.e.rotateAngleY;
      this.f.rotateAngleZ = this.e.rotateAngleZ;
      if (this.isAttacking) {
         float var10 = 1.0F;
         this.e.rotationPointY -= var10 * 5.0F;
      }
   }
}
