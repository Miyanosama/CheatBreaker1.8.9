package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;

public class ModelAdapterPig extends ModelAdapterQuadruped {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderPig(var3, var1, var2);
   }

   public ModelAdapterPig() {
      super(EntityPig.class, "pig", 0.7F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelPig();
   }
}
