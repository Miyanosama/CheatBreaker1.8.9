package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

public class RenderPigZombie extends RenderBiped<EntityPigZombie> {
   public static ResourceLocation ZOMBIE_PIGMAN_TEXTURE = new ResourceLocation("textures/entity/zombie_pigman.png");
   public Items field_0001;

   public ResourceLocation getEntityTexture(EntityPigZombie var1) {
      return ZOMBIE_PIGMAN_TEXTURE;
   }

   public RenderPigZombie(RenderManager var1) {
      super(var1, new ModelZombie(), 0.5F, 1.0F);
      this.a(new LayerHeldItem(this));
      this.a(new RenderPigZombie$1(this, this));
   }
}
