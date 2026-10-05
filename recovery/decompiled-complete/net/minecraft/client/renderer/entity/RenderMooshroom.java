package net.minecraft.client.renderer.entity;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$BaseIterator;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysTask;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityAuraFX$HappyVillagerFactory;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.ResourceLocation;

public class RenderMooshroom extends RenderLiving<EntityMooshroom> {
   public ConcurrentHashMapV8$MapReduceKeysTask field_0001;
   public ConcurrentHashMapV8$BaseIterator field_0002;
   public static ResourceLocation mooshroomTextures = new ResourceLocation("textures/entity/cow/mooshroom.png");
   public EntityAuraFX$HappyVillagerFactory field_0000;

   public ResourceLocation getEntityTexture(EntityMooshroom var1) {
      return mooshroomTextures;
   }

   public RenderMooshroom(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
      this.a(new LayerMooshroomMushroom(this));
   }
}
