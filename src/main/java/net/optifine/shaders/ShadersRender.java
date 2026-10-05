package net.optifine.shaders;

import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.ResourceLocation;
import net.optifine.reflect.Reflector;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ShadersRender {
   public static ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");

   public static void preRenderChunkLayer(EnumWorldBlockLayer var0) {
      if (Shaders.isRenderBackFace(var0)) {
         GlStateManager.disableCull();
      }

      if (OpenGlHelper.useVbo()) {
         GL11.glEnableClientState(32885);
         GL20.glEnableVertexAttribArray(Shaders.midTexCoordAttrib);
         GL20.glEnableVertexAttribArray(Shaders.tangentAttrib);
         GL20.glEnableVertexAttribArray(Shaders.entityAttrib);
      }
   }

   public static void beaconBeamDraw2() {
      GlStateManager.disableBlend();
   }

   public static void renderFPOverlay(EntityRenderer var0, float var1, int var2) {
      if (!Shaders.isShadowPass) {
         Shaders.beginFPOverlay();
         var0.renderHand(var1, var2, false, true, false);
         Shaders.endFPOverlay();
      }
   }

   public static void postRenderChunkLayer(EnumWorldBlockLayer var0) {
      if (OpenGlHelper.useVbo()) {
         GL11.glDisableClientState(32885);
         GL20.glDisableVertexAttribArray(Shaders.midTexCoordAttrib);
         GL20.glDisableVertexAttribArray(Shaders.tangentAttrib);
         GL20.glDisableVertexAttribArray(Shaders.entityAttrib);
      }

      if (Shaders.isRenderBackFace(var0)) {
         GlStateManager.enableCull();
      }
   }

   public static void method_06622() {
      Shaders.useProgram(Shaders.recoveredField2113);
   }

   public static void method_06606() {
   }

   public static void method_06620() {
   }

   public static void renderHand0(EntityRenderer var0, float var1, int var2) {
      if (!Shaders.isShadowPass) {
         boolean var3 = Shaders.isItemToRenderMainTranslucent();
         boolean var4 = Shaders.isItemToRenderOffTranslucent();
         if (!var3 || !var4) {
            Shaders.readCenterDepth();
            Shaders.beginHand(false);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Shaders.setSkipRenderHands(var3, var4);
            var0.renderHand(var1, var2, true, false, false);
            Shaders.endHand();
            Shaders.setHandsRendered(!var3, !var4);
            Shaders.setSkipRenderHands(false, false);
         }
      }
   }

   public static void setFrustrumPosition(ICamera var0, double var1, double var3, double var5) {
      var0.setPosition(var1, var3, var5);
   }

   public static void method_06602() {
      if (Shaders.isRenderingWorld) {
         Shaders.useProgram(Shaders.ProgramTexturedLit);
      }
   }

   public static void beginBlockDamage() {
      if (Shaders.isRenderingWorld) {
         Shaders.useProgram(Shaders.ProgramDamagedBlock);
         if (Shaders.ProgramDamagedBlock.getId() == Shaders.ProgramTerrain.getId()) {
            Shaders.setDrawBuffers(Shaders.drawBuffersColorAtt0);
            GlStateManager.depthMask(false);
         }
      }
   }

   public static void method_06625() {
      if (Shaders.isRenderingWorld) {
         Shaders.useProgram(Shaders.ProgramTexturedLit);
      }
   }

   public static void setupArrayPointersVbo() {
      byte var0 = 14;
      GL11.glVertexPointer(3, 5126, 56, 0L);
      GL11.glColorPointer(4, 5121, 56, 12L);
      GL11.glTexCoordPointer(2, 5126, 56, 16L);
      OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glTexCoordPointer(2, 5122, 56, 24L);
      OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glNormalPointer(5120, 56, 28L);
      GL20.glVertexAttribPointer(Shaders.midTexCoordAttrib, 2, 5126, false, 56, 32L);
      GL20.glVertexAttribPointer(Shaders.tangentAttrib, 4, 5122, false, 56, 40L);
      GL20.glVertexAttribPointer(Shaders.entityAttrib, 3, 5122, false, 56, 48L);
   }

   public static void renderEnchantedGlintEnd() {
      if (Shaders.isRenderingWorld) {
         if (Shaders.isRenderingFirstPersonHand() && Shaders.isRenderBothHands()) {
            Shaders.useProgram(Shaders.ProgramHand);
         } else {
            Shaders.useProgram(Shaders.ProgramEntities);
         }
      } else {
         Shaders.useProgram(Shaders.ProgramNone);
      }
   }

   public static void method_06626() {
   }

   public static void renderItemFP(ItemRenderer var0, float var1, boolean var2) {
      Shaders.setRenderingFirstPersonHand(true);
      GlStateManager.depthMask(true);
      if (var2) {
         GlStateManager.depthFunc(519);
         GL11.glPushMatrix();
         IntBuffer var3 = Shaders.activeDrawBuffers;
         Shaders.setDrawBuffers(Shaders.drawBuffersNone);
         Shaders.renderItemKeepDepthMask = true;
         var0.renderItemInFirstPerson(var1);
         Shaders.renderItemKeepDepthMask = false;
         Shaders.setDrawBuffers(var3);
         GL11.glPopMatrix();
      }

      GlStateManager.depthFunc(515);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var0.renderItemInFirstPerson(var1);
      Shaders.setRenderingFirstPersonHand(false);
   }

   public static void method_06601() {
      if (Shaders.isRenderingWorld) {
         Shaders.useProgram(Shaders.ProgramTerrain);
      }
   }

   public static void method_06616() {
      if (Shaders.isRenderingWorld) {
         Shaders.useProgram(Shaders.ProgramTerrain);
      }
   }

   public static void renderHand1(EntityRenderer var0, float var1, int var2) {
      if (!Shaders.isShadowPass && !Shaders.isBothHandsRendered()) {
         Shaders.readCenterDepth();
         GlStateManager.enableBlend();
         Shaders.beginHand(true);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         Shaders.setSkipRenderHands(Shaders.isHandRenderedMain(), Shaders.isHandRenderedOff());
         var0.renderHand(var1, var2, true, false, true);
         Shaders.endHand();
         Shaders.setHandsRendered(true, true);
         Shaders.setSkipRenderHands(false, false);
      }
   }

   public static void endBlockDamage() {
      if (Shaders.isRenderingWorld) {
         GlStateManager.depthMask(true);
         Shaders.useProgram(Shaders.ProgramTexturedLit);
      }
   }

   public static void beginTranslucent() {
      if (Shaders.isRenderingWorld) {
         if (Shaders.usedDepthBuffers >= 2) {
            GlStateManager.setActiveTexture(33995);
            Shaders.checkGLError("pre copy depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.renderWidth, Shaders.renderHeight);
            Shaders.checkGLError("copy depth");
            GlStateManager.setActiveTexture(33984);
         }

         Shaders.useProgram(Shaders.ProgramWater);
      }
   }

   public static void setupTerrain(RenderGlobal var0, Entity var1, double var2, ICamera var4, int var5, boolean var6) {
      var0.setupTerrain(var1, var2, var4, var5, var6);
   }

   public static void method_06619() {
      Shaders.useProgram(Shaders.recoveredField2176);
   }

   public static void beginTerrainSolid() {
      if (Shaders.isRenderingWorld) {
         Shaders.fogEnabled = true;
         Shaders.useProgram(Shaders.ProgramTerrain);
      }
   }

   public static boolean renderEndPortal(TileEntityEndPortal var0, double var1, double var3, double var5, float var7, int var8, float var9) {
      if (!Shaders.isShadowPass && Shaders.activeProgram.getId() == 0) {
         return false;
      } else {
         GlStateManager.disableLighting();
         Config.getTextureManager().bindTexture(END_PORTAL_TEXTURE);
         Tessellator var10 = Tessellator.getInstance();
         WorldRenderer var11 = var10.getWorldRenderer();
         var11.begin(7, DefaultVertexFormats.BLOCK);
         float var12 = 0.5F;
         float var13 = var12 * 0.15F;
         float var14 = var12 * 0.3F;
         float var15 = var12 * 0.4F;
         float var16 = 0.0F;
         float var17 = 0.2F;
         float var18 = (float)(System.currentTimeMillis() % 100000L) / 100000.0F;
         short var19 = 240;
         var11.pos(var1, var3 + var9, var5 + 1.0).color(var13, var14, var15, 1.0F).tex(var16 + var18, var16 + var18).lightmap(var19, var19).endVertex();
         var11.pos(var1 + 1.0, var3 + var9, var5 + 1.0).color(var13, var14, var15, 1.0F).tex(var16 + var18, var17 + var18).lightmap(var19, var19).endVertex();
         var11.pos(var1 + 1.0, var3 + var9, var5).color(var13, var14, var15, 1.0F).tex(var17 + var18, var17 + var18).lightmap(var19, var19).endVertex();
         var11.pos(var1, var3 + var9, var5).color(var13, var14, var15, 1.0F).tex(var17 + var18, var16 + var18).lightmap(var19, var19).endVertex();
         var10.draw();
         GlStateManager.enableLighting();
         return true;
      }
   }

   public static void renderShadowMap(EntityRenderer var0, int var1, float var2, long var3) {
      if (Shaders.usedShadowDepthBuffers > 0 && --Shaders.shadowPassCounter <= 0) {
         Minecraft var5 = Minecraft.getMinecraft();
         var5.mcProfiler.endStartSection("shadow pass");
         RenderGlobal var6 = var5.renderGlobal;
         Shaders.isShadowPass = true;
         Shaders.shadowPassCounter = Shaders.shadowPassInterval;
         Shaders.preShadowPassThirdPersonView = var5.gameSettings.thirdPersonView;
         var5.gameSettings.thirdPersonView = 1;
         Shaders.checkGLError("pre shadow");
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPushMatrix();
         var5.mcProfiler.endStartSection("shadow clear");
         EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.sfb);
         Shaders.checkGLError("shadow bind sfb");
         var5.mcProfiler.endStartSection("shadow camera");
         var0.setupCameraTransform(var2, 2);
         Shaders.setCameraShadow(var2);
         Shaders.checkGLError("shadow camera");
         Shaders.useProgram(Shaders.ProgramShadow);
         GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
         Shaders.checkGLError("shadow drawbuffers");
         GL11.glReadBuffer(0);
         Shaders.checkGLError("shadow readbuffer");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, Shaders.sfbDepthTextures.get(0), 0);
         if (Shaders.usedShadowColorBuffers != 0) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, Shaders.sfbColorTextures.get(0), 0);
         }

         Shaders.checkFramebufferStatus("shadow fb");
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glClear(Shaders.usedShadowColorBuffers != 0 ? 16640 : 256);
         Shaders.checkGLError("shadow clear");
         var5.mcProfiler.endStartSection("shadow frustum");
         ClippingHelper var7 = ClippingHelperShadow.getInstance();
         var5.mcProfiler.endStartSection("shadow culling");
         Frustum var8 = new Frustum(var7);
         Entity var9 = var5.getRenderViewEntity();
         double var10 = var9.P + (var9.s - var9.P) * var2;
         double var12 = var9.Q + (var9.t - var9.Q) * var2;
         double var14 = var9.R + (var9.u - var9.R) * var2;
         var8.setPosition(var10, var12, var14);
         GlStateManager.shadeModel(7425);
         GlStateManager.enableDepth();
         GlStateManager.depthFunc(515);
         GlStateManager.depthMask(true);
         GlStateManager.colorMask(true, true, true, true);
         GlStateManager.disableCull();
         var5.mcProfiler.endStartSection("shadow prepareterrain");
         var5.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         var5.mcProfiler.endStartSection("shadow setupterrain");
         int var16 = 0;
         var16 = var0.frameCount++;
         var6.setupTerrain(var9, var2, var8, var16, var5.thePlayer.isSpectator());
         var5.mcProfiler.endStartSection("shadow updatechunks");
         var5.mcProfiler.endStartSection("shadow terrain");
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         GlStateManager.disableAlpha();
         var6.renderBlockLayer(EnumWorldBlockLayer.SOLID, var2, 2, var9);
         Shaders.checkGLError("shadow terrain solid");
         GlStateManager.enableAlpha();
         var6.renderBlockLayer(EnumWorldBlockLayer.CUTOUT_MIPPED, var2, 2, var9);
         Shaders.checkGLError("shadow terrain cutoutmipped");
         var5.getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
         var6.renderBlockLayer(EnumWorldBlockLayer.CUTOUT, var2, 2, var9);
         Shaders.checkGLError("shadow terrain cutout");
         var5.getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
         GlStateManager.shadeModel(7424);
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         var5.mcProfiler.endStartSection("shadow entities");
         if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
            Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, 0);
         }

         var6.renderEntities(var9, var8, var2);
         Shaders.checkGLError("shadow entities");
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.enableCull();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.alphaFunc(516, 0.1F);
         if (Shaders.usedShadowDepthBuffers >= 2) {
            GlStateManager.setActiveTexture(33989);
            Shaders.checkGLError("pre copy shadow depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.shadowMapWidth, Shaders.shadowMapHeight);
            Shaders.checkGLError("copy shadow depth");
            GlStateManager.setActiveTexture(33984);
         }

         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         var5.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         GlStateManager.shadeModel(7425);
         Shaders.checkGLError("shadow pre-translucent");
         GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
         Shaders.checkGLError("shadow drawbuffers pre-translucent");
         Shaders.checkFramebufferStatus("shadow pre-translucent");
         if (Shaders.isRenderShadowTranslucent()) {
            var5.mcProfiler.endStartSection("shadow translucent");
            var6.renderBlockLayer(EnumWorldBlockLayer.TRANSLUCENT, var2, 2, var9);
            Shaders.checkGLError("shadow translucent");
         }

         if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
            RenderHelper.enableStandardItemLighting();
            Reflector.call(Reflector.ForgeHooksClient_setRenderPass, 1);
            var6.renderEntities(var9, var8, var2);
            Reflector.call(Reflector.ForgeHooksClient_setRenderPass, -1);
            RenderHelper.disableStandardItemLighting();
            Shaders.checkGLError("shadow entities 1");
         }

         GlStateManager.shadeModel(7424);
         GlStateManager.depthMask(true);
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GL11.glFlush();
         Shaders.checkGLError("shadow flush");
         Shaders.isShadowPass = false;
         var5.gameSettings.thirdPersonView = Shaders.preShadowPassThirdPersonView;
         var5.mcProfiler.endStartSection("shadow postprocess");
         if (Shaders.hasGlGenMipmap) {
            if (Shaders.usedShadowDepthBuffers >= 1) {
               if (Shaders.shadowMipmapEnabled[0]) {
                  GlStateManager.setActiveTexture(33988);
                  GlStateManager.bindTexture(Shaders.sfbDepthTextures.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[0] ? 9984 : 9987);
               }

               if (Shaders.usedShadowDepthBuffers >= 2 && Shaders.shadowMipmapEnabled[1]) {
                  GlStateManager.setActiveTexture(33989);
                  GlStateManager.bindTexture(Shaders.sfbDepthTextures.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[1] ? 9984 : 9987);
               }

               GlStateManager.setActiveTexture(33984);
            }

            if (Shaders.usedShadowColorBuffers >= 1) {
               if (Shaders.shadowColorMipmapEnabled[0]) {
                  GlStateManager.setActiveTexture(33997);
                  GlStateManager.bindTexture(Shaders.sfbColorTextures.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[0] ? 9984 : 9987);
               }

               if (Shaders.usedShadowColorBuffers >= 2 && Shaders.shadowColorMipmapEnabled[1]) {
                  GlStateManager.setActiveTexture(33998);
                  GlStateManager.bindTexture(Shaders.sfbColorTextures.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[1] ? 9984 : 9987);
               }

               GlStateManager.setActiveTexture(33984);
            }
         }

         Shaders.checkGLError("shadow postprocess");
         EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.dfb);
         GL11.glViewport(0, 0, Shaders.renderWidth, Shaders.renderHeight);
         Shaders.activeDrawBuffers = null;
         var5.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         Shaders.useProgram(Shaders.ProgramTerrain);
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5889);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         Shaders.checkGLError("shadow end");
      }
   }
}
