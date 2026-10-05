package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.nethandler.server.PacketVoice;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderChicken extends RenderLiving<EntityChicken> {
   public EntityAIOwnerHurtByTarget field_0001;
   public RenderBiped field_0003;
   public static ResourceLocation chickenTextures = new ResourceLocation("textures/entity/chicken.png");
   public BlockPartRotation field_0000;
   public PacketVoice field_0002;

   public float handleRotationFloat(EntityChicken var1, float var2) {
      float var3 = var1.field_70888_h + (var1.wingRotation - var1.field_70888_h) * var2;
      float var4 = var1.field_70884_g + (var1.destPos - var1.field_70884_g) * var2;
      return (MathHelper.sin(var3) + 1.0F) * var4;
   }

   public ResourceLocation getEntityTexture(EntityChicken var1) {
      return chickenTextures;
   }

   public RenderChicken(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
   }
}
