package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.stream.ChatController$ChatState;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass4377;

public class RenderBlaze extends RenderLiving<EntityBlaze> {
   public UnidentifiedClass4377 field_0000;
   public static ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
   public ChatController$ChatState field_0002;

   public ResourceLocation getEntityTexture(EntityBlaze var1) {
      return blazeTextures;
   }

   public RenderBlaze(RenderManager var1) {
      super(var1, new ModelBlaze(), 0.5F);
   }
}
