package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.util.ResourceLocation;

public class RenderFireball extends Render<EntityFireball> {
   public S41PacketServerDifficulty field_0000;
   public float scale;
   public EntitySplashFX field_0002;

   public ResourceLocation getEntityTexture(EntityFireball var1) {
      return TextureMap.locationBlocksTexture;
   }

   public void doRender(EntityFireball var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      this.bindEntityTexture(var1);
      GlStateManager.translate((float)var2, (float)var4, (float)var6);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(this.scale, this.scale, this.scale);
      TextureAtlasSprite var10 = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.fire_charge);
      Tessellator var11 = Tessellator.getInstance();
      WorldRenderer var12 = var11.getWorldRenderer();
      float var13 = var10.getMinU();
      float var14 = var10.getMaxU();
      float var15 = var10.getMinV();
      float var16 = var10.getMaxV();
      float var17 = 1.0F;
      float var18 = 0.5F;
      float var19 = 0.25F;
      GlStateManager.rotate(180.0F - this.b.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.b.playerViewX, 1.0F, 0.0F, 0.0F);
      var12.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      var12.pos(-0.5, -0.25, 0.0).tex(var13, var16).normal(0.0F, 1.0F, 0.0F).endVertex();
      var12.pos(0.5, -0.25, 0.0).tex(var14, var16).normal(0.0F, 1.0F, 0.0F).endVertex();
      var12.pos(0.5, 0.75, 0.0).tex(var14, var15).normal(0.0F, 1.0F, 0.0F).endVertex();
      var12.pos(-0.5, 0.75, 0.0).tex(var13, var15).normal(0.0F, 1.0F, 0.0F).endVertex();
      var11.draw();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(var1, var2, var4, var6, var8, var9);
   }

   public RenderFireball(RenderManager var1, float var2) {
      super(var1);
      this.scale = var2;
   }
}
