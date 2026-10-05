package net.optifine.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class CloudRenderer {
   public boolean renderFancy;
   public int glListClouds;
   public Minecraft mc;
   public int cloudTickCounter;
   public Vec3 updateCloudColor;
   public int updateCloudTickCounter;
   public Vec3 cloudColor;
   public boolean updateRenderFancy;
   public float partialTicks;
   public double updatePlayerY;
   public double updatePlayerX;
   public boolean updated = false;
   public double updatePlayerZ;

   public void endUpdateGlList() {
      GL11.glEndList();
      this.updateRenderFancy = this.renderFancy;
      this.updateCloudTickCounter = this.cloudTickCounter;
      this.updateCloudColor = this.cloudColor;
      this.updatePlayerX = this.mc.getRenderViewEntity().p;
      this.updatePlayerY = this.mc.getRenderViewEntity().q;
      this.updatePlayerZ = this.mc.getRenderViewEntity().r;
      this.updated = true;
      GlStateManager.resetColor();
   }

   public void renderGlList() {
      Entity var1 = this.mc.getRenderViewEntity();
      double var2 = var1.p + (var1.s - var1.p) * this.partialTicks;
      double var4 = var1.q + (var1.t - var1.q) * this.partialTicks;
      double var6 = var1.r + (var1.u - var1.r) * this.partialTicks;
      double var8 = this.cloudTickCounter - this.updateCloudTickCounter + this.partialTicks;
      float var10 = (float)(var2 - this.updatePlayerX + var8 * 0.03);
      float var11 = (float)(var4 - this.updatePlayerY);
      float var12 = (float)(var6 - this.updatePlayerZ);
      GlStateManager.pushMatrix();
      if (this.renderFancy) {
         GlStateManager.translate(-var10 / 12.0F, -var11, -var12 / 12.0F);
      } else {
         GlStateManager.translate(-var10, -var11, -var12);
      }

      GlStateManager.callList(this.glListClouds);
      GlStateManager.popMatrix();
      GlStateManager.resetColor();
   }

   public void reset() {
      this.updated = false;
   }

   public boolean shouldUpdateGlList() {
      if (!this.updated) {
         return true;
      } else if (this.renderFancy != this.updateRenderFancy) {
         return true;
      } else if (this.cloudTickCounter >= this.updateCloudTickCounter + 20) {
         return true;
      } else if (Math.abs(this.cloudColor.xCoord - this.updateCloudColor.xCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.cloudColor.yCoord - this.updateCloudColor.yCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.cloudColor.zCoord - this.updateCloudColor.zCoord) > 0.003) {
         return true;
      } else {
         Entity var1 = this.mc.getRenderViewEntity();
         boolean var2 = this.updatePlayerY + var1.getEyeHeight() < 128.0 + this.mc.gameSettings.ofCloudsHeight * 128.0F;
         boolean var3 = var1.q + var1.getEyeHeight() < 128.0 + this.mc.gameSettings.ofCloudsHeight * 128.0F;
         return var3 != var2;
      }
   }

   public CloudRenderer(Minecraft var1) {
      this.renderFancy = false;
      this.updateRenderFancy = false;
      this.updateCloudTickCounter = 0;
      this.updateCloudColor = new Vec3(-1.0, -1.0, -1.0);
      this.updatePlayerX = 0.0;
      this.updatePlayerY = 0.0;
      this.updatePlayerZ = 0.0;
      this.glListClouds = -1;
      this.mc = var1;
      this.glListClouds = GLAllocation.generateDisplayLists(1);
   }

   public void prepareToRender(boolean var1, int var2, float var3, Vec3 var4) {
      this.renderFancy = var1;
      this.cloudTickCounter = var2;
      this.partialTicks = var3;
      this.cloudColor = var4;
   }

   public void startUpdateGlList() {
      GL11.glNewList(this.glListClouds, 4864);
   }
}
