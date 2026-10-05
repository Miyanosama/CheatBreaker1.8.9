package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

public class RenderWitch extends RenderLiving<EntityWitch> {
   public static ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");

   public RenderWitch(RenderManager var1) {
      super(var1, new ModelWitch(0.0F), 0.5F);
      this.a(new LayerHeldItemWitch(this));
   }

   public ResourceLocation getEntityTexture(EntityWitch var1) {
      return witchTextures;
   }

   @Override
   public void y_() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public void preRenderCallback(EntityWitch var1, float var2) {
      float var3 = 0.9375F;
      GlStateManager.scale(var3, var3, var3);
   }

   public void doRender(EntityWitch var1, double var2, double var4, double var6, float var8, float var9) {
      ((ModelWitch)this.f).field_82900_g = var1.getHeldItem() != null;
      super.doRender((EntityWitch)var1, var2, var4, var6, var8, var9);
   }
}
