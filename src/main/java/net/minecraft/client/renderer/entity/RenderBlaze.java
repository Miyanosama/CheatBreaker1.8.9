package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderBlaze extends RenderLiving<EntityBlaze> {
   public static ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");

   public ResourceLocation getEntityTexture(EntityBlaze var1) {
      return blazeTextures;
   }

   public RenderBlaze(RenderManager var1) {
      super(var1, new ModelBlaze(), 0.5F);
   }
}
