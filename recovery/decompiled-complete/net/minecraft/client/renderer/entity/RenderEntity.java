package net.minecraft.client.renderer.entity;

import io.netty.channel.DefaultChannelPipeline$4;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.stats.StatBase$3;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItemProperties;
import recovered.unidentified.UnidentifiedClass5100;

public class RenderEntity extends Render<Entity> {
   public CustomItemProperties field_0001;
   public StatBase$3 field_0002;
   public DefaultChannelPipeline$4 field_0003;
   public UnidentifiedClass5100 field_0000;

   @Override
   public ResourceLocation getEntityTexture(Entity var1) {
      return null;
   }

   public RenderEntity(RenderManager var1) {
      super(var1);
   }

   @Override
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      renderOffsetAABB(var1.getEntityBoundingBox(), var2 - var1.P, var4 - var1.Q, var6 - var1.R);
      GlStateManager.popMatrix();
      super.doRender(var1, var2, var4, var6, var8, var9);
   }
}
