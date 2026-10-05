package net.minecraft.client.model;

import com.jagrosh.discordipc.entities.pipe.Pipe;
import junit.extensions.TestSetup;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.optifine.reflect.ReflectorField;

public class ModelDragon extends ModelBase {
   public ModelRenderer jaw;
   public ReflectorField field_0013;
   public ModelRenderer head;
   public ModelRenderer wing;
   public ModelRenderer frontLegTip;
   public float partialTicks;
   public ModelRenderer frontFoot;
   public ModelRenderer wingTip;
   public ModelRenderer body;
   public ModelRenderer spine;
   public ModelRenderer rearLeg;
   public ModelRenderer rearLegTip;
   public Pipe field_0008;
   public TestSetup field_0010;
   public ModelRenderer frontLeg;
   public ModelRenderer rearFoot;

   @Override
   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.partialTicks = var4;
   }

   public float updateRotations(double var1) {
      while (var1 >= 180.0) {
         var1 -= 360.0;
      }

      while (var1 < -180.0) {
         var1 += 360.0;
      }

      return (float)var1;
   }

   public ModelDragon(float var1) {
      this.t = 256;
      this.u = 256;
      this.setTextureOffset("body.body", 0, 0);
      this.setTextureOffset("wing.skin", -56, 88);
      this.setTextureOffset("wingtip.skin", -56, 144);
      this.setTextureOffset("rearleg.main", 0, 0);
      this.setTextureOffset("rearfoot.main", 112, 0);
      this.setTextureOffset("rearlegtip.main", 196, 0);
      this.setTextureOffset("head.upperhead", 112, 30);
      this.setTextureOffset("wing.bone", 112, 88);
      this.setTextureOffset("head.upperlip", 176, 44);
      this.setTextureOffset("jaw.jaw", 176, 65);
      this.setTextureOffset("frontleg.main", 112, 104);
      this.setTextureOffset("wingtip.bone", 112, 136);
      this.setTextureOffset("frontfoot.main", 144, 104);
      this.setTextureOffset("neck.box", 192, 104);
      this.setTextureOffset("frontlegtip.main", 226, 138);
      this.setTextureOffset("body.scale", 220, 53);
      this.setTextureOffset("head.scale", 0, 0);
      this.setTextureOffset("neck.scale", 48, 0);
      this.setTextureOffset("head.nostril", 112, 0);
      float var2 = -16.0F;
      this.head = new ModelRenderer(this, "head");
      this.head.addBox("upperlip", -6.0F, -1.0F, -8.0F + var2, 12, 5, 16);
      this.head.addBox("upperhead", -8.0F, -8.0F, 6.0F + var2, 16, 16, 16);
      this.head.mirror = true;
      this.head.addBox("scale", -5.0F, -12.0F, 12.0F + var2, 2, 4, 6);
      this.head.addBox("nostril", -5.0F, -3.0F, -6.0F + var2, 2, 2, 4);
      this.head.mirror = false;
      this.head.addBox("scale", 3.0F, -12.0F, 12.0F + var2, 2, 4, 6);
      this.head.addBox("nostril", 3.0F, -3.0F, -6.0F + var2, 2, 2, 4);
      this.jaw = new ModelRenderer(this, "jaw");
      this.jaw.setRotationPoint(0.0F, 4.0F, 8.0F + var2);
      this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
      this.head.addChild(this.jaw);
      this.spine = new ModelRenderer(this, "neck");
      this.spine.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.spine.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
      this.body = new ModelRenderer(this, "body");
      this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
      this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
      this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
      this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
      this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
      this.wing = new ModelRenderer(this, "wing");
      this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
      this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.wingTip = new ModelRenderer(this, "wingtip");
      this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
      this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.wing.addChild(this.wingTip);
      this.frontLeg = new ModelRenderer(this, "frontleg");
      this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
      this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
      this.frontLegTip = new ModelRenderer(this, "frontlegtip");
      this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
      this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
      this.frontLeg.addChild(this.frontLegTip);
      this.frontFoot = new ModelRenderer(this, "frontfoot");
      this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
      this.frontLegTip.addChild(this.frontFoot);
      this.rearLeg = new ModelRenderer(this, "rearleg");
      this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
      this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
      this.rearLegTip = new ModelRenderer(this, "rearlegtip");
      this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
      this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
      this.rearLeg.addChild(this.rearLegTip);
      this.rearFoot = new ModelRenderer(this, "rearfoot");
      this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
      this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
      this.rearLegTip.addChild(this.rearFoot);
   }

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      GlStateManager.pushMatrix();
      EntityDragon var8 = (EntityDragon)var1;
      float var9 = var8.field_0009 + (var8.field_0010 - var8.field_0009) * this.partialTicks;
      this.jaw.rotateAngleX = (float)(Math.sin(var9 * (float) Math.PI * 2.0F) + 1.0) * 0.2F;
      float var10 = (float)(Math.sin(var9 * (float) Math.PI * 2.0F - 1.0F) + 1.0);
      var10 = (var10 * var10 * 1.0F + var10 * 2.0F) * 0.05F;
      GlStateManager.translate(0.0F, var10 - 2.0F, -3.0F);
      GlStateManager.rotate(var10 * 2.0F, 1.0F, 0.0F, 0.0F);
      float var11 = -30.0F;
      float var12 = 0.0F;
      float var13 = 1.5F;
      double[] var14 = var8.getMovementOffsets(6, this.partialTicks);
      float var15 = this.updateRotations(var8.getMovementOffsets(5, this.partialTicks)[0] - var8.getMovementOffsets(10, this.partialTicks)[0]);
      float var16 = this.updateRotations(var8.getMovementOffsets(5, this.partialTicks)[0] + var15 / 2.0F);
      var11 += 2.0F;
      float var17 = var9 * (float) Math.PI * 2.0F;
      var11 = 20.0F;
      float var18 = -12.0F;

      for (int var19 = 0; var19 < 5; var19++) {
         double[] var20 = var8.getMovementOffsets(5 - var19, this.partialTicks);
         float var21 = (float)Math.cos(var19 * 0.45F + var17) * 0.15F;
         this.spine.rotateAngleY = this.updateRotations(var20[0] - var14[0]) * (float) Math.PI / 180.0F * var13;
         this.spine.rotateAngleX = var21 + (float)(var20[1] - var14[1]) * (float) Math.PI / 180.0F * var13 * 5.0F;
         this.spine.rotateAngleZ = -this.updateRotations(var20[0] - var16) * (float) Math.PI / 180.0F * var13;
         this.spine.rotationPointY = var11;
         this.spine.rotationPointZ = var18;
         this.spine.rotationPointX = var12;
         var11 = (float)(var11 + Math.sin(this.spine.rotateAngleX) * 10.0);
         var18 = (float)(var18 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0);
         var12 = (float)(var12 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0);
         this.spine.render(var7);
      }

      this.head.rotationPointY = var11;
      this.head.rotationPointZ = var18;
      this.head.rotationPointX = var12;
      double[] var30 = var8.getMovementOffsets(0, this.partialTicks);
      this.head.rotateAngleY = this.updateRotations(var30[0] - var14[0]) * (float) Math.PI / 180.0F * 1.0F;
      this.head.rotateAngleZ = -this.updateRotations(var30[0] - var16) * (float) Math.PI / 180.0F * 1.0F;
      this.head.render(var7);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-var15 * var13 * 1.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.0F, -1.0F, 0.0F);
      this.body.rotateAngleZ = 0.0F;
      this.body.render(var7);

      for (int var32 = 0; var32 < 2; var32++) {
         GlStateManager.enableCull();
         float var34 = var9 * (float) Math.PI * 2.0F;
         this.wing.rotateAngleX = 0.125F - (float)Math.cos(var34) * 0.2F;
         this.wing.rotateAngleY = 0.25F;
         this.wing.rotateAngleZ = (float)(Math.sin(var34) + 0.125) * 0.8F;
         this.wingTip.rotateAngleZ = -((float)(Math.sin(var34 + 2.0F) + 0.5)) * 0.75F;
         this.rearLeg.rotateAngleX = 1.0F + var10 * 0.1F;
         this.rearLegTip.rotateAngleX = 0.5F + var10 * 0.1F;
         this.rearFoot.rotateAngleX = 0.75F + var10 * 0.1F;
         this.frontLeg.rotateAngleX = 1.3F + var10 * 0.1F;
         this.frontLegTip.rotateAngleX = -0.5F - var10 * 0.1F;
         this.frontFoot.rotateAngleX = 0.75F + var10 * 0.1F;
         this.wing.render(var7);
         this.frontLeg.render(var7);
         this.rearLeg.render(var7);
         GlStateManager.scale(-1.0F, 1.0F, 1.0F);
         if (var32 == 0) {
            GlStateManager.cullFace(1028);
         }
      }

      GlStateManager.popMatrix();
      GlStateManager.cullFace(1029);
      GlStateManager.disableCull();
      float var33 = -((float)Math.sin(var9 * (float) Math.PI * 2.0F)) * 0.0F;
      var17 = var9 * (float) Math.PI * 2.0F;
      var11 = 10.0F;
      var18 = 60.0F;
      var12 = 0.0F;
      var14 = var8.getMovementOffsets(11, this.partialTicks);

      for (int var35 = 0; var35 < 12; var35++) {
         var30 = var8.getMovementOffsets(12 + var35, this.partialTicks);
         var33 = (float)(var33 + Math.sin(var35 * 0.45F + var17) * 0.05F);
         this.spine.rotateAngleY = (this.updateRotations(var30[0] - var14[0]) * var13 + 180.0F) * (float) Math.PI / 180.0F;
         this.spine.rotateAngleX = var33 + (float)(var30[1] - var14[1]) * (float) Math.PI / 180.0F * var13 * 5.0F;
         this.spine.rotateAngleZ = this.updateRotations(var30[0] - var16) * (float) Math.PI / 180.0F * var13;
         this.spine.rotationPointY = var11;
         this.spine.rotationPointZ = var18;
         this.spine.rotationPointX = var12;
         var11 = (float)(var11 + Math.sin(this.spine.rotateAngleX) * 10.0);
         var18 = (float)(var18 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0);
         var12 = (float)(var12 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0);
         this.spine.render(var7);
      }

      GlStateManager.popMatrix();
   }
}
