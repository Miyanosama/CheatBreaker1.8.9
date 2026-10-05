package net.minecraft.client.renderer.tileentity;

import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class TileEntityBeaconRenderer extends TileEntitySpecialRenderer<TileEntityBeacon> {
   public static ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

   @Override
   public boolean forceTileEntityRender() {
      return true;
   }

   public void renderTileEntityAt(TileEntityBeacon var1, double var2, double var4, double var6, float var8, int var9) {
      float var10 = var1.shouldBeamRender();
      if (var10 > 0.0) {
         if (Config.isShaders()) {
            Shaders.method_02193();
         }

         GlStateManager.alphaFunc(516, 0.1F);
         if (var10 > 0.0F) {
            Tessellator var11 = Tessellator.getInstance();
            WorldRenderer var12 = var11.getWorldRenderer();
            GlStateManager.disableFog();
            List var13 = var1.getBeamSegments();
            int var14 = 0;

            for (int var15 = 0; var15 < var13.size(); var15++) {
               TileEntityBeacon.BeamSegment var16 = (TileEntityBeacon.BeamSegment)var13.get(var15);
               int var17 = var14 + var16.getHeight();
               this.bindTexture(beaconBeam);
               GL11.glTexParameterf(3553, 10242, 10497.0F);
               GL11.glTexParameterf(3553, 10243, 10497.0F);
               GlStateManager.disableLighting();
               GlStateManager.disableCull();
               GlStateManager.disableBlend();
               GlStateManager.depthMask(true);
               GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
               double var18 = (double)var1.getWorld().K() + var8;
               double var20 = MathHelper.func_181162_h(-var18 * 0.2 - MathHelper.floor_double(-var18 * 0.1));
               float var22 = var16.method_28192()[0];
               float var23 = var16.method_28192()[1];
               float var24 = var16.method_28192()[2];
               double var25 = var18 * 0.025 * -1.5;
               double var27 = 0.2;
               double var29 = 0.5 + Math.cos(var25 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double var31 = 0.5 + Math.sin(var25 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double var33 = 0.5 + Math.cos(var25 + (Math.PI / 4)) * 0.2;
               double var35 = 0.5 + Math.sin(var25 + (Math.PI / 4)) * 0.2;
               double var37 = 0.5 + Math.cos(var25 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double var39 = 0.5 + Math.sin(var25 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double var41 = 0.5 + Math.cos(var25 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double var43 = 0.5 + Math.sin(var25 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double var45 = 0.0;
               double var47 = 1.0;
               double var49 = -1.0 + var20;
               double var51 = var16.getHeight() * var10 * 2.5 + var49;
               var12.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               var12.pos(var2 + var29, var4 + var17, var6 + var31).tex(1.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var29, var4 + var14, var6 + var31).tex(1.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var33, var4 + var14, var6 + var35).tex(0.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var33, var4 + var17, var6 + var35).tex(0.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var41, var4 + var17, var6 + var43).tex(1.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var41, var4 + var14, var6 + var43).tex(1.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var37, var4 + var14, var6 + var39).tex(0.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var37, var4 + var17, var6 + var39).tex(0.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var33, var4 + var17, var6 + var35).tex(1.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var33, var4 + var14, var6 + var35).tex(1.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var41, var4 + var14, var6 + var43).tex(0.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var41, var4 + var17, var6 + var43).tex(0.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var37, var4 + var17, var6 + var39).tex(1.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var37, var4 + var14, var6 + var39).tex(1.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var29, var4 + var14, var6 + var31).tex(0.0, var49).color(var22, var23, var24, 1.0F).endVertex();
               var12.pos(var2 + var29, var4 + var17, var6 + var31).tex(0.0, var51).color(var22, var23, var24, 1.0F).endVertex();
               var11.draw();
               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               GlStateManager.depthMask(false);
               var25 = 0.2;
               var27 = 0.2;
               var29 = 0.8;
               var31 = 0.2;
               var33 = 0.2;
               var35 = 0.8;
               var37 = 0.8;
               var39 = 0.8;
               var41 = 0.0;
               var43 = 1.0;
               var45 = -1.0 + var20;
               var47 = var16.getHeight() * var10 + var45;
               var12.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               var12.pos(var2 + 0.2, var4 + var17, var6 + 0.2).tex(1.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var14, var6 + 0.2).tex(1.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var14, var6 + 0.2).tex(0.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var17, var6 + 0.2).tex(0.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var17, var6 + 0.8).tex(1.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var14, var6 + 0.8).tex(1.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var14, var6 + 0.8).tex(0.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var17, var6 + 0.8).tex(0.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var17, var6 + 0.2).tex(1.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var14, var6 + 0.2).tex(1.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var14, var6 + 0.8).tex(0.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.8, var4 + var17, var6 + 0.8).tex(0.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var17, var6 + 0.8).tex(1.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var14, var6 + 0.8).tex(1.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var14, var6 + 0.2).tex(0.0, var45).color(var22, var23, var24, 0.125F).endVertex();
               var12.pos(var2 + 0.2, var4 + var17, var6 + 0.2).tex(0.0, var47).color(var22, var23, var24, 0.125F).endVertex();
               var11.draw();
               GlStateManager.enableLighting();
               GlStateManager.enableTexture2D();
               GlStateManager.depthMask(true);
               var14 = var17;
            }

            GlStateManager.enableFog();
         }

         if (Config.isShaders()) {
            Shaders.endBeacon();
         }
      }
   }
}
