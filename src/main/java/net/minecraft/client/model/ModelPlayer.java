package net.minecraft.client.model;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.ClientResourceManager;
import net.minecraft.client.Minecraft;
import com.cheatbreaker.client.cosmetic.model.DragonWingsModel;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import com.cheatbreaker.client.event.type.PlayerModelRenderEvent;
import com.cheatbreaker.client.event.EventPhase;

public class ModelPlayer extends ModelBiped {
   public boolean smallArms;
   public ModelRenderer bipedCape;
   public ModelRenderer bipedLeftArmwear;
   public ModelRenderer bipedRightLegwear;
   public ModelRenderer bipedDeadmau5Head;
   public ModelRenderer bipedLeftLegwear;
   public ModelRenderer bipedRightArmwear;
   public DragonWingsModel recoveredField3328;
   public ModelRenderer bipedBodyWear;

   public void renderRightArm() {
      this.h.render(0.0625F);
      this.bipedRightArmwear.render(0.0625F);
   }

   public void renderLeftArm() {
      this.i.render(0.0625F);
      this.bipedLeftArmwear.render(0.0625F);
   }

   @Override
   public void setInvisible(boolean var1) {
      super.setInvisible(var1);
      this.bipedLeftArmwear.showModel = var1;
      this.bipedRightArmwear.showModel = var1;
      this.bipedLeftLegwear.showModel = var1;
      this.bipedRightLegwear.showModel = var1;
      this.bipedBodyWear.showModel = var1;
      this.bipedCape.showModel = var1;
      this.bipedDeadmau5Head.showModel = var1;
   }

   public void renderDeadmau5Head(float var1) {
      copyModelAngles(this.e, this.bipedDeadmau5Head);
      this.bipedDeadmau5Head.rotationPointX = 0.0F;
      this.bipedDeadmau5Head.rotationPointY = 0.0F;
      this.bipedDeadmau5Head.render(var1);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      copyModelAngles(this.k, this.bipedLeftLegwear);
      copyModelAngles(this.j, this.bipedRightLegwear);
      copyModelAngles(this.i, this.bipedLeftArmwear);
      copyModelAngles(this.h, this.bipedRightArmwear);
      copyModelAngles(this.g, this.bipedBodyWear);
   }

   public ModelPlayer(float var1, boolean var2) {
      super(var1, 0.0F, 64, 64);
      this.smallArms = var2;
      this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
      this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, var1);
      this.bipedCape = new ModelRenderer(this, 0, 0);
      this.bipedCape.setTextureSize(64, 32);
      this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, var1);
      if (var2) {
         this.i = new ModelRenderer(this, 32, 48);
         this.i.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, var1);
         this.i.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.h = new ModelRenderer(this, 40, 16);
         this.h.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, var1);
         this.h.setRotationPoint(-5.0F, 2.5F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, var1 + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, var1 + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);
      } else {
         this.i = new ModelRenderer(this, 32, 48);
         this.i.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1);
         this.i.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1 + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, var1 + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);
      }

      this.k = new ModelRenderer(this, 16, 48);
      this.k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
      this.k.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
      this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1 + 0.25F);
      this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
      this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1 + 0.25F);
      this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.bipedBodyWear = new ModelRenderer(this, 16, 32);
      this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1 + 0.25F);
      this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.recoveredField3328 = new DragonWingsModel(0.0F);
   }

   public void renderCape(float var1) {
      this.bipedCape.render(var1);
   }

   @Override
   public void postRenderArm(float var1) {
      if (this.smallArms) {
         this.h.rotationPointX++;
         this.h.postRender(var1);
         this.h.rotationPointX--;
      } else {
         this.h.postRender(var1);
      }
   }

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      super.render(var1, var2, var3, var4, var5, var6, var7);
      GlStateManager.pushMatrix();
      if (this.r) {
         float var8 = 2.0F;
         GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GlStateManager.translate(0.0F, 24.0F * var7, 0.0F);
         this.bipedLeftLegwear.render(var7);
         this.bipedRightLegwear.render(var7);
         this.bipedLeftArmwear.render(var7);
         this.bipedRightArmwear.render(var7);
         this.bipedBodyWear.render(var7);
      } else {
         if (var1.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.bipedLeftLegwear.render(var7);
         this.bipedRightLegwear.render(var7);
         this.bipedLeftArmwear.render(var7);
         this.bipedRightArmwear.render(var7);
         this.bipedBodyWear.render(var7);
      }

      ClientResourceManager wings = var1 == Minecraft.getMinecraft().thePlayer
         ? CheatBreaker.getInstance().method_19791().getLocalCosmetics().getEquipped(CosmeticType.WINGS)
         : (var1 instanceof EntityPlayer ? ((EntityPlayer)var1).method_00329() : null);
      if (!this.o && wings != null && wings.method_20848() == CosmeticType.WINGS
         && CheatBreaker.getInstance().getGlobalSettings().recoveredField543.method_08908()) {
         this.recoveredField3328.method_20013(var1, var2, var3, var4, var5, var6, var7, wings.method_20846(), wings.method_20859());
      }

      if (var1 instanceof AbstractClientPlayer) {
         CheatBreaker.getInstance()
            .method_19817()
            .method_21935(new PlayerModelRenderEvent(EventPhase.END, (AbstractClientPlayer)var1, this, var7));
         this.j.rotateAngleZ = 0.0F;
         this.k.rotateAngleZ = 0.0F;
         this.k.offsetX = 0.0F;
         this.j.offsetX = 0.0F;
         this.g.rotateAngleZ = 0.0F;
         if (!CheatBreaker.getInstance().method_19783().method_01368().containsKey(var1.aK())) {
            this.bipedCape.rotateAngleZ = 0.0F;
            this.bipedCape.rotateAngleX = 0.0F;
            this.bipedCape.offsetX = 0.0F;
         }

         this.bipedRightArmwear.rotateAngleX = 0.0F;
         this.bipedRightArmwear.rotateAngleY = 0.0F;
         this.bipedRightArmwear.rotateAngleZ = 0.0F;
         this.bipedRightArmwear.offsetX = 0.0F;
         this.bipedLeftArmwear.rotateAngleX = 0.0F;
         this.bipedLeftArmwear.rotateAngleY = 0.0F;
         this.bipedLeftArmwear.rotateAngleZ = 0.0F;
         this.bipedLeftArmwear.offsetX = 0.0F;
         this.bipedRightLegwear.rotateAngleX = 0.0F;
         this.bipedRightLegwear.rotateAngleY = 0.0F;
         this.bipedRightLegwear.rotateAngleZ = 0.0F;
         this.bipedRightLegwear.offsetX = 0.0F;
         this.bipedLeftLegwear.rotateAngleX = 0.0F;
         this.bipedLeftLegwear.rotateAngleY = 0.0F;
         this.bipedLeftLegwear.rotateAngleZ = 0.0F;
         this.bipedLeftLegwear.offsetX = 0.0F;
      }

      GlStateManager.popMatrix();
   }
}
