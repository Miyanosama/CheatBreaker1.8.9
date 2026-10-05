package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.helpers.PatternConverter;
import recovered.unidentified.UnidentifiedClass3556;

public class RenderSpider<T extends EntitySpider> extends RenderLiving<T> {
   public PatternConverter field_0001;
   public EntityBlockDustFX field_0002;
   public UnidentifiedClass3556 field_0000;
   public static ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
   public EntityAITasks$EntityAITaskEntry field_0004;

   public float getDeathMaxRotation(T var1) {
      return 180.0F;
   }

   public RenderSpider(RenderManager var1) {
      super(var1, new ModelSpider(), 1.0F);
      this.a(new LayerSpiderEyes(this));
   }

   public ResourceLocation getEntityTexture(T var1) {
      return spiderTextures;
   }
}
