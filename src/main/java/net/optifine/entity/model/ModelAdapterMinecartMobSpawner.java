package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecartMobSpawner extends ModelAdapterMinecart {
   public ModelAdapterMinecartMobSpawner() {
      super(EntityMinecartMobSpawner.class, "spawner_minecart", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderMinecartMobSpawner var4 = new RenderMinecartMobSpawner(var3);
      if (!Reflector.RenderMinecart_modelMinecart.exists()) {
         Config.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         Reflector.setFieldValue(var4, Reflector.RenderMinecart_modelMinecart, var1);
         var4.c = var2;
         return var4;
      }
   }
}
