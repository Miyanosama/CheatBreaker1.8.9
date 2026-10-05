package net.minecraft.client.renderer.entity;

import io.netty.util.internal.ThreadLocalRandom$1;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.ai.EntityAIFollowOwner;

public class RenderSkeleton$1 extends LayerBipedArmor {
   public ThreadLocalRandom$1 field_0001;
   public EntityAIFollowOwner field_0002;

   @Override
   public void initArmor() {
      this.c = new ModelSkeleton(0.5F, true);
      this.d = new ModelSkeleton(1.0F, true);
   }

   public RenderSkeleton$1(RenderSkeleton var1, RendererLivingEntity var2) {
      this.field_177199_a = var1;
      super(var2);
   }
}
