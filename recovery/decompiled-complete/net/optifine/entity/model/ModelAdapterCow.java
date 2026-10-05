package net.optifine.entity.model;

import net.minecraft.block.BlockBanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityCow;

public class ModelAdapterCow extends ModelAdapterQuadruped {
   public EntityAITarget field_0000;
   public BlockBanner field_0001;

   public ModelAdapterCow() {
      super(EntityCow.class, "cow", 0.7F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderCow(var3, var1, var2);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelCow();
   }
}
