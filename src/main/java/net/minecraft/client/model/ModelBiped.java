package net.minecraft.client.model;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import com.cheatbreaker.client.emote.EmoteManager;
import com.cheatbreaker.client.emote.Emote;
import com.cheatbreaker.client.event.type.PlayerModelRenderEvent;
import com.cheatbreaker.client.event.EventPhase;

public class ModelBiped extends ModelBase {
   public ModelRenderer f;
   public ModelRenderer j;
   public int m;
   public boolean o;
   public boolean n;
   public ModelRenderer h;
   public ModelRenderer e;
   public ModelRenderer k;
   public ModelRenderer i;
   public int l;
   public ModelRenderer g;

   public void postRenderArm(float var1) {
      this.h.postRender(var1);
   }

   public ModelBiped(float var1, float var2, int var3, int var4) {
      this.t = var3;
      this.u = var4;
      this.e = new ModelRenderer(this, 0, 0);
      this.e.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1);
      this.e.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      this.f = new ModelRenderer(this, 32, 0);
      this.f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1 + 0.5F);
      this.f.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      this.g = new ModelRenderer(this, 16, 16);
      this.g.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1);
      this.g.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      this.h = new ModelRenderer(this, 40, 16);
      this.h.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, var1);
      this.h.setRotationPoint(-5.0F, 2.0F + var2, 0.0F);
      this.i = new ModelRenderer(this, 40, 16);
      this.i.mirror = true;
      this.i.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1);
      this.i.setRotationPoint(5.0F, 2.0F + var2, 0.0F);
      this.j = new ModelRenderer(this, 0, 16);
      this.j.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
      this.j.setRotationPoint(-1.9F, 12.0F + var2, 0.0F);
      this.k = new ModelRenderer(this, 0, 16);
      this.k.mirror = true;
      this.k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
      this.k.setRotationPoint(1.9F, 12.0F + var2, 0.0F);
   }

   public ModelBiped() {
      this(0.0F);
   }

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      if (var1 instanceof AbstractClientPlayer && this instanceof ModelPlayer) {
         CheatBreaker.getInstance()
            .method_19817()
            .method_21935(new PlayerModelRenderEvent(EventPhase.START, (AbstractClientPlayer)var1, (ModelPlayer)this, var7));
      }

      GlStateManager.pushMatrix();
      if (this.r) {
         float var8 = 2.0F;
         GlStateManager.scale(1.5F / var8, 1.5F / var8, 1.5F / var8);
         GlStateManager.translate(0.0F, 16.0F * var7, 0.0F);
         this.e.render(var7);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GlStateManager.translate(0.0F, 24.0F * var7, 0.0F);
         this.g.render(var7);
         this.h.render(var7);
         this.i.render(var7);
         this.j.render(var7);
         this.k.render(var7);
         this.f.render(var7);
      } else {
         if (var1.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.e.render(var7);
         this.g.render(var7);
         this.h.render(var7);
         this.i.render(var7);
         this.j.render(var7);
         this.k.render(var7);
         this.f.render(var7);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void a(ModelBase var1) {
      super.a(var1);
      if (var1 instanceof ModelBiped) {
         ModelBiped var2 = (ModelBiped)var1;
         this.l = var2.l;
         this.m = var2.m;
         this.n = var2.n;
         this.o = var2.o;
      }
   }

   public void setInvisible(boolean var1) {
      this.e.showModel = var1;
      this.f.showModel = var1;
      this.g.showModel = var1;
      this.h.showModel = var1;
      this.i.showModel = var1;
      this.j.showModel = var1;
      this.k.showModel = var1;
   }

   public ModelBiped(float var1) {
      this(var1, 0.0F, 64, 32);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      this.e.rotateAngleY = var4 / (180.0F / (float)Math.PI);
      this.e.rotateAngleX = var5 / (180.0F / (float)Math.PI);
      this.h.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float) Math.PI) * 2.0F * var2 * 0.5F;
      this.i.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 2.0F * var2 * 0.5F;
      this.i.rotateAngleZ = 0.0F;
      this.j.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
      this.k.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float) Math.PI) * 1.4F * var2;
      this.j.rotateAngleY = 0.0F;
      this.k.rotateAngleY = 0.0F;
      if (this.isRiding) {
         this.h.rotateAngleX += (float) (-Math.PI / 5);
         this.i.rotateAngleX += (float) (-Math.PI / 5);
         this.j.rotateAngleX = (float) (-Math.PI * 2.0 / 5.0);
         this.k.rotateAngleX = (float) (-Math.PI * 2.0 / 5.0);
         this.j.rotateAngleY = (float) (Math.PI / 10);
         this.k.rotateAngleY = (float) (-Math.PI / 10);
      }

      if (this.l != 0) {
         this.i.rotateAngleX = this.i.rotateAngleX * 0.5F - (float) (Math.PI / 10) * this.l;
      }

      this.h.rotateAngleY = 0.0F;
      this.h.rotateAngleZ = 0.0F;
      switch (this.m) {
         case 0:
         case 2:
         default:
            break;
         case 1:
            this.h.rotateAngleX = this.h.rotateAngleX * 0.5F - (float) (Math.PI / 10) * this.m;
            break;
         case 3:
            this.h.rotateAngleX = this.h.rotateAngleX * 0.5F - (float) (Math.PI / 10) * this.m;
            this.h.rotateAngleY = CheatBreaker.getInstance().getModuleManager().recoveredField1717.recoveredField3723.method_08908()
               ? 0.0F
               : (float) (-Math.PI / 6);
      }

      this.i.rotateAngleY = 0.0F;
      if (this.p > -9990.0F) {
         float var8 = this.p;
         this.g.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var8) * (float) Math.PI * 2.0F) * 0.2F;
         this.h.rotationPointZ = MathHelper.sin(this.g.rotateAngleY) * 5.0F;
         this.h.rotationPointX = -MathHelper.cos(this.g.rotateAngleY) * 5.0F;
         this.i.rotationPointZ = -MathHelper.sin(this.g.rotateAngleY) * 5.0F;
         this.i.rotationPointX = MathHelper.cos(this.g.rotateAngleY) * 5.0F;
         this.h.rotateAngleY = this.h.rotateAngleY + this.g.rotateAngleY;
         this.i.rotateAngleY = this.i.rotateAngleY + this.g.rotateAngleY;
         this.i.rotateAngleX = this.i.rotateAngleX + this.g.rotateAngleY;
         var8 = 1.0F - this.p;
         var8 *= var8;
         var8 *= var8;
         var8 = 1.0F - var8;
         float var9 = MathHelper.sin(var8 * (float) Math.PI);
         float var10 = MathHelper.sin(this.p * (float) Math.PI) * -(this.e.rotateAngleX - 0.7F) * 0.75F;
         this.h.rotateAngleX = (float)(this.h.rotateAngleX - (var9 * 1.2 + var10));
         this.h.rotateAngleY = this.h.rotateAngleY + this.g.rotateAngleY * 2.0F;
         this.h.rotateAngleZ = this.h.rotateAngleZ + MathHelper.sin(this.p * (float) Math.PI) * -0.4F;
      }

      EmoteManager var16 = CheatBreaker.getInstance().method_19783();
      Emote var17 = var16.method_01371();
      if (!this.n && (var17 == null || !var17.method_02056().equalsIgnoreCase("Naruto Run"))) {
         this.g.rotateAngleX = 0.0F;
         this.j.rotationPointZ = 0.1F;
         this.k.rotationPointZ = 0.1F;
         this.j.rotationPointY = 12.0F;
         this.k.rotationPointY = 12.0F;
         this.e.rotationPointY = 0.0F;
      } else {
         this.g.rotateAngleX = 0.5F;
         this.h.rotateAngleX += 0.4F;
         this.i.rotateAngleX += 0.4F;
         this.j.rotationPointZ = 4.0F;
         this.k.rotationPointZ = 4.0F;
         this.j.rotationPointY = 9.0F;
         this.k.rotationPointY = 9.0F;
         this.e.rotationPointY = 1.0F;
      }

      this.h.rotateAngleZ = this.h.rotateAngleZ + (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
      this.i.rotateAngleZ = this.i.rotateAngleZ - (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
      this.h.rotateAngleX = this.h.rotateAngleX + MathHelper.sin(var3 * 0.067F) * 0.05F;
      this.i.rotateAngleX = this.i.rotateAngleX - MathHelper.sin(var3 * 0.067F) * 0.05F;
      if (this.o) {
         float var18 = 0.0F;
         float var11 = 0.0F;
         this.h.rotateAngleZ = 0.0F;
         this.i.rotateAngleZ = 0.0F;
         this.h.rotateAngleY = -(0.1F - var18 * 0.6F) + this.e.rotateAngleY;
         this.i.rotateAngleY = 0.1F - var18 * 0.6F + this.e.rotateAngleY + 0.4F;
         this.h.rotateAngleX = (float) (-Math.PI / 2) + this.e.rotateAngleX;
         this.i.rotateAngleX = (float) (-Math.PI / 2) + this.e.rotateAngleX;
         this.h.rotateAngleX -= var18 * 1.2F - var11 * 0.4F;
         this.i.rotateAngleX -= var18 * 1.2F - var11 * 0.4F;
         this.h.rotateAngleZ = this.h.rotateAngleZ + (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
         this.i.rotateAngleZ = this.i.rotateAngleZ - (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
         this.h.rotateAngleX = this.h.rotateAngleX + MathHelper.sin(var3 * 0.067F) * 0.05F;
         this.i.rotateAngleX = this.i.rotateAngleX - MathHelper.sin(var3 * 0.067F) * 0.05F;
      }

      copyModelAngles(this.e, this.f);
   }
}
