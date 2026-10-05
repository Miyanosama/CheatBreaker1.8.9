package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderPig extends RenderLiving<EntityPig> {
   public static ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");

   public ResourceLocation getEntityTexture(EntityPig var1) {
      return pigTextures;
   }

   public RenderPig(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
      this.a(new LayerSaddle(this));
   }
}
