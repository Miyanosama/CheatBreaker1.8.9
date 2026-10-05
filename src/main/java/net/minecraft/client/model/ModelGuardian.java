package net.minecraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ModelGuardian extends ModelBase {
   public ModelRenderer[] guardianTail;
   public ModelRenderer[] guardianSpines;
   public ModelRenderer guardianBody;
   public ModelRenderer guardianEye;

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      this.guardianBody.render(var7);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      EntityGuardian var8 = (EntityGuardian)var7;
      float var9 = var3 - var8.W;
      this.guardianBody.rotateAngleY = var4 / (180.0F / (float)Math.PI);
      this.guardianBody.rotateAngleX = var5 / (180.0F / (float)Math.PI);
      float[] var10 = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
      float[] var11 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
      float[] var12 = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
      float[] var13 = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
      float[] var14 = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
      float[] var15 = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
      float var16 = (1.0F - var8.func_175469_o(var9)) * 0.55F;

      for (int var17 = 0; var17 < 12; var17++) {
         this.guardianSpines[var17].rotateAngleX = (float) Math.PI * var10[var17];
         this.guardianSpines[var17].rotateAngleY = (float) Math.PI * var11[var17];
         this.guardianSpines[var17].rotateAngleZ = (float) Math.PI * var12[var17];
         this.guardianSpines[var17].rotationPointX = var13[var17] * (1.0F + MathHelper.cos(var3 * 1.5F + var17) * 0.01F - var16);
         this.guardianSpines[var17].rotationPointY = 16.0F + var14[var17] * (1.0F + MathHelper.cos(var3 * 1.5F + var17) * 0.01F - var16);
         this.guardianSpines[var17].rotationPointZ = var15[var17] * (1.0F + MathHelper.cos(var3 * 1.5F + var17) * 0.01F - var16);
      }

      this.guardianEye.rotationPointZ = -8.25F;
      net.minecraft.entity.Entity var26 = Minecraft.getMinecraft().getRenderViewEntity();
      if (var8.hasTargetedEntity()) {
         var26 = var8.getTargetedEntity();
      }

      if (var26 != null) {
         Vec3 var18 = ((Entity)var26).getPositionEyes(0.0F);
         Vec3 var19 = var7.getPositionEyes(0.0F);
         double var20 = var18.yCoord - var19.yCoord;
         if (var20 > 0.0) {
            this.guardianEye.rotationPointY = 0.0F;
         } else {
            this.guardianEye.rotationPointY = 1.0F;
         }

         Vec3 var22 = var7.getLook(0.0F);
         var22 = new Vec3(var22.xCoord, 0.0, var22.zCoord);
         Vec3 var23 = new Vec3(var19.xCoord - var18.xCoord, 0.0, var19.zCoord - var18.zCoord).normalize().rotateYaw((float) (Math.PI / 2));
         double var24 = var22.dotProduct(var23);
         this.guardianEye.rotationPointX = MathHelper.sqrt_float((float)Math.abs(var24)) * 2.0F * (float)Math.signum(var24);
      }

      this.guardianEye.showModel = true;
      float var27 = var8.func_175471_a(var9);
      this.guardianTail[0].rotateAngleY = MathHelper.sin(var27) * (float) Math.PI * 0.05F;
      this.guardianTail[1].rotateAngleY = MathHelper.sin(var27) * (float) Math.PI * 0.1F;
      this.guardianTail[1].rotationPointX = -1.5F;
      this.guardianTail[1].rotationPointY = 0.5F;
      this.guardianTail[1].rotationPointZ = 14.0F;
      this.guardianTail[2].rotateAngleY = MathHelper.sin(var27) * (float) Math.PI * 0.15F;
      this.guardianTail[2].rotationPointX = 0.5F;
      this.guardianTail[2].rotationPointY = 0.5F;
      this.guardianTail[2].rotationPointZ = 6.0F;
   }

   public ModelGuardian() {
      this.t = 64;
      this.u = 64;
      this.guardianSpines = new ModelRenderer[12];
      this.guardianBody = new ModelRenderer(this);
      this.guardianBody.setTextureOffset(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
      this.guardianBody.setTextureOffset(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
      this.guardianBody.setTextureOffset(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
      this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
      this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);

      for (int var1 = 0; var1 < this.guardianSpines.length; var1++) {
         this.guardianSpines[var1] = new ModelRenderer(this, 0, 0);
         this.guardianSpines[var1].addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
         this.guardianBody.addChild(this.guardianSpines[var1]);
      }

      this.guardianEye = new ModelRenderer(this, 8, 0);
      this.guardianEye.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
      this.guardianBody.addChild(this.guardianEye);
      this.guardianTail = new ModelRenderer[3];
      this.guardianTail[0] = new ModelRenderer(this, 40, 0);
      this.guardianTail[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
      this.guardianTail[1] = new ModelRenderer(this, 0, 54);
      this.guardianTail[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
      this.guardianTail[2] = new ModelRenderer(this);
      this.guardianTail[2].setTextureOffset(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
      this.guardianTail[2].setTextureOffset(25, 19).addBox(1.0F, 10.5F, 3.0F, 1, 9, 9);
      this.guardianBody.addChild(this.guardianTail[0]);
      this.guardianTail[0].addChild(this.guardianTail[1]);
      this.guardianTail[1].addChild(this.guardianTail[2]);
   }

   public int func_178706_a() {
      return 54;
   }
}
