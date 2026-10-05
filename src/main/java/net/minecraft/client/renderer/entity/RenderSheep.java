package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

public class RenderSheep extends RenderLiving<EntitySheep> {
   public static ResourceLocation shearedSheepTextures = new ResourceLocation("textures/entity/sheep/sheep.png");

   public ResourceLocation getEntityTexture(EntitySheep var1) {
      return shearedSheepTextures;
   }

   public RenderSheep(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
      this.a(new LayerSheepWool(this));
   }
}
