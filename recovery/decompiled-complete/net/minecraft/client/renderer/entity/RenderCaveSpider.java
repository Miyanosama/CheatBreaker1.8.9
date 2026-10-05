package net.minecraft.client.renderer.entity;

import io.netty.channel.group.DefaultChannelGroupFuture$1;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;
import org.json.JSONArray;

public class RenderCaveSpider extends RenderSpider<EntityCaveSpider> {
   public static ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");
   public JSONArray field_0002;
   public DefaultChannelGroupFuture$1 field_0003;
   public DefaultEventExecutorGroup field_0000;

   public void preRenderCallback(EntityCaveSpider var1, float var2) {
      GlStateManager.scale(0.7F, 0.7F, 0.7F);
   }

   public RenderCaveSpider(RenderManager var1) {
      super(var1);
      this.c *= 0.7F;
   }

   public ResourceLocation getEntityTexture(EntityCaveSpider var1) {
      return caveSpiderTextures;
   }
}
