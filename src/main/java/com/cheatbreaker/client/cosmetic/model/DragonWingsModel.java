package com.cheatbreaker.client.cosmetic.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DragonWingsModel extends ModelBase {
   public ModelRenderer recoveredField2543;
   public ResourceLocation recoveredField2544 = new ResourceLocation("textures/entity/enderdragon/dragon.png");
   public ModelRenderer recoveredField2545;

   public DragonWingsModel(float var1) {
      this.t = 256;
      this.u = 256;
      this.setTextureOffset("wing.skin", -56, 88);
      this.setTextureOffset("wingtip.skin", -56, 144);
      this.setTextureOffset("wing.bone", 112, 88);
      this.setTextureOffset("wingtip.bone", 112, 136);
      this.recoveredField2545 = new ModelRenderer(this, "wing");
      this.recoveredField2545.setRotationPoint(-12.0F, 5.0F, 2.0F);
      this.recoveredField2545.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.recoveredField2545.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.recoveredField2543 = new ModelRenderer(this, "wingtip");
      this.recoveredField2543.setRotationPoint(-56.0F, 0.0F, 0.0F);
      this.recoveredField2543.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.recoveredField2543.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.recoveredField2545.addChild(this.recoveredField2543);
   }

   public void method_20013(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, ResourceLocation var9) {
      if (var1 instanceof EntityPlayer) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(var9);
         GL11.glPushMatrix();
         GL11.glScaled(var8, var8, var8);
         GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.5F, 0.25F);
         float var10 = (float)(System.currentTimeMillis() % 2000L) / 2000.0F * (float) Math.PI * 2.0F;

         for (int var11 = 0; var11 < 2; var11++) {
            GL11.glEnable(2884);
            this.recoveredField2545.rotateAngleX = -0.125F - (float)Math.cos(var10) * 0.2F;
            this.recoveredField2545.rotateAngleY = 0.75F;
            this.recoveredField2545.rotateAngleZ = (float)(Math.sin(var10) + 0.125) * 0.8F;
            this.recoveredField2543.rotateAngleZ = (float)(Math.sin(var10 + 2.0F) + 0.5) * 0.75F;
            this.recoveredField2545.render(var7);
            GL11.glScalef(-1.0F, 1.0F, 1.0F);
            if (var11 == 0) {
               GL11.glCullFace(1028);
            }
         }

         GL11.glPopMatrix();
         GL11.glCullFace(1029);
         GL11.glDisable(2884);
      }
   }
}
