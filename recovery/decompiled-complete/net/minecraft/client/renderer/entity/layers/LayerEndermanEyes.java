package net.minecraft.client.renderer.entity.layers;

import com.jagrosh.discordipc.entities.User;
import io.netty.handler.ssl.JdkSslClientContext;
import javax.vecmath.MismatchedSizeException;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.Shaders;

public class LayerEndermanEyes implements LayerRenderer<EntityEnderman> {
   public MismatchedSizeException field_0002;
   public JdkSslClientContext field_0004;
   public RenderEnderman endermanRenderer;
   public static ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
   public User field_0000;

   public void doRenderLayer(EntityEnderman var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.endermanRenderer.a(RES_ENDERMAN_EYES);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(1, 1);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(!var1.isInvisible());
      char var9 = '\uf0f0';
      int var10 = var9 % 65536;
      int var11 = var9 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
      GlStateManager.enableLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (Config.isShaders()) {
         Shaders.beginSpiderEyes();
      }

      Config.getRenderGlobal().renderOverlayEyes = true;
      this.endermanRenderer.getMainModel().render(var1, var2, var3, var5, var6, var7, var8);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaders()) {
         Shaders.endSpiderEyes();
      }

      this.endermanRenderer.setLightmap(var1, var4);
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }

   public LayerEndermanEyes(RenderEnderman var1) {
      this.endermanRenderer = var1;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }
}
