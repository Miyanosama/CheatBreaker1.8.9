package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.command.CommandTime;
import net.minecraft.entity.monster.EntityCaveSpider;

public class ModelAdapterCaveSpider extends ModelAdapterSpider {
   public CommandTime field_0000;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderCaveSpider var4 = new RenderCaveSpider(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   public ModelAdapterCaveSpider() {
      super(EntityCaveSpider.class, "cave_spider", 0.7F);
   }
}
