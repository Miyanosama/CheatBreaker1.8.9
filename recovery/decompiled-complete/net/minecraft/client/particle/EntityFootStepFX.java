package net.minecraft.client.particle;

import com.cheatbreaker.client.ui.fading.ExponentialFade;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.ConnectedTexturesCompact$1;
import net.optifine.reflect.ReflectorClass;

public class EntityFootStepFX extends EntityFX {
   public ExponentialFade field_0003;
   public TextureManager currentFootSteps;
   public int footstepMaxAge;
   public static ResourceLocation FOOTPRINT_TEXTURE = new ResourceLocation("textures/particle/footprint.png");
   public ReflectorClass field_0000;
   public ConnectedTexturesCompact$1 field_0001;
   public int footstepAge;

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.footstepAge + var3) / this.footstepMaxAge;
      var9 *= var9;
      float var10 = 2.0F - var9 * 2.0F;
      if (var10 > 1.0F) {
         var10 = 1.0F;
      }

      var10 *= 0.2F;
      GlStateManager.disableLighting();
      float var11 = 0.125F;
      float var12 = (float)(this.s - aw);
      float var13 = (float)(this.t - ax);
      float var14 = (float)(this.u - ay);
      float var15 = this.o.o(new BlockPos(this));
      this.currentFootSteps.bindTexture(FOOTPRINT_TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      var1.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var1.pos(var12 - 0.125F, var13, var14 + 0.125F).tex(0.0, 1.0).color(var15, var15, var15, var10).endVertex();
      var1.pos(var12 + 0.125F, var13, var14 + 0.125F).tex(1.0, 1.0).color(var15, var15, var15, var10).endVertex();
      var1.pos(var12 + 0.125F, var13, var14 - 0.125F).tex(1.0, 0.0).color(var15, var15, var15, var10).endVertex();
      var1.pos(var12 - 0.125F, var13, var14 - 0.125F).tex(0.0, 0.0).color(var15, var15, var15, var10).endVertex();
      Tessellator.getInstance().draw();
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
   }

   @Override
   public int getFXLayer() {
      return 3;
   }

   public EntityFootStepFX(TextureManager var1, World var2, double var3, double var5, double var7) {
      super(var2, var3, var5, var7, 0.0, 0.0, 0.0);
      this.currentFootSteps = var1;
      this.v = this.w = this.x = 0.0;
      this.footstepMaxAge = 200;
   }

   @Override
   public void onUpdate() {
      this.footstepAge++;
      if (this.footstepAge == this.footstepMaxAge) {
         this.setDead();
      }
   }
}
