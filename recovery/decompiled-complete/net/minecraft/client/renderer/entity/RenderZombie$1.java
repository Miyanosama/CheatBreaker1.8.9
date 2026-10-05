package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.ai.attributes.BaseAttribute;

public class RenderZombie$1 extends LayerBipedArmor {
   public BaseAttribute field_0001;

   public RenderZombie$1(RenderZombie var1, RendererLivingEntity var2) {
      this.field_177200_a = var1;
      super(var2);
   }

   @Override
   public void initArmor() {
      this.c = new ModelZombie(0.5F, true);
      this.d = new ModelZombie(1.0F, true);
   }
}
