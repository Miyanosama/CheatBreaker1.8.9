package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;

public class ModelAdapterZombie extends ModelAdapterBiped {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderZombie var4 = new RenderZombie(var3);
      Render.setModelBipedMain(var4, (ModelBiped)var1);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   public ModelAdapterZombie() {
      super(EntityZombie.class, "zombie", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelZombie();
   }
}
