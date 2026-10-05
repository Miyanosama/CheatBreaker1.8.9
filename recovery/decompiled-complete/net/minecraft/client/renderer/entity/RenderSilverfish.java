package net.minecraft.client.renderer.entity;

import io.netty.util.UniqueName;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.resources.model.SimpleBakedModel$Builder;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.Cartesian$Product$ProductIterator;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.rewrite.ReflectionRewritePolicy;

public class RenderSilverfish extends RenderLiving<EntitySilverfish> {
   public UniqueName field_0001;
   public SimpleBakedModel$Builder field_0003;
   public static ResourceLocation silverfishTextures = new ResourceLocation("textures/entity/silverfish.png");
   public Cartesian$Product$ProductIterator field_0000;
   public ReflectionRewritePolicy field_0002;

   public RenderSilverfish(RenderManager var1) {
      super(var1, new ModelSilverfish(), 0.3F);
   }

   public ResourceLocation getEntityTexture(EntitySilverfish var1) {
      return silverfishTextures;
   }

   public float getDeathMaxRotation(EntitySilverfish var1) {
      return 180.0F;
   }
}
