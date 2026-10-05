package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.Shaders;

public class LayerSpiderEyes implements LayerRenderer<EntitySpider> {
   public RenderSpider spiderRenderer;
   public static ResourceLocation SPIDER_EYES = new ResourceLocation("textures/entity/spider_eyes.png");

   public void doRenderLayer(EntitySpider var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.spiderRenderer.a(SPIDER_EYES);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(1, 1);
      if (var1.isInvisible()) {
         GlStateManager.depthMask(false);
      } else {
         GlStateManager.depthMask(true);
      }

      int var9 = 61680;
      int var10 = var9 % 65536;
      int var11 = var9 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (Config.isShaders()) {
         Shaders.beginSpiderEyes();
      }

      Config.getRenderGlobal().renderOverlayEyes = true;
      this.spiderRenderer.getMainModel().render(var1, var2, var3, var5, var6, var7, var8);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaders()) {
         Shaders.endSpiderEyes();
      }

      var9 = var1.b_(var4);
      var10 = var9 % 65536;
      var11 = var9 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
      this.spiderRenderer.setLightmap(var1, var4);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }

   public LayerSpiderEyes(RenderSpider var1) {
      this.spiderRenderer = var1;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }
}
