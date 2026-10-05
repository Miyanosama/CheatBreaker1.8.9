package net.minecraft.client.renderer.entity;

import io.netty.channel.AbstractChannel$AbstractUnsafe$1;
import net.minecraft.client.Minecraft$8;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderCow extends RenderLiving<EntityCow> {
   public Minecraft$8 field_0000;
   public static ResourceLocation cowTextures = new ResourceLocation("textures/entity/cow/cow.png");
   public AbstractChannel$AbstractUnsafe$1 field_0002;

   public ResourceLocation getEntityTexture(EntityCow var1) {
      return cowTextures;
   }

   public RenderCow(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
   }
}
