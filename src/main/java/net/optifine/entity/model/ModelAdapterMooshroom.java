package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.entity.passive.EntityMooshroom;

public class ModelAdapterMooshroom extends ModelAdapterQuadruped {
   @Override
   public ModelBase makeModel() {
      return new ModelCow();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderMooshroom(var3, var1, var2);
   }

   public ModelAdapterMooshroom() {
      super(EntityMooshroom.class, "mooshroom", 0.7F);
   }
}
