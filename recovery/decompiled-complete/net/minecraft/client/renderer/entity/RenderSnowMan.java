package net.minecraft.client.renderer.entity;

import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.particle.EntityCloudFX$Factory;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.optifine.NaturalTextures;

public class RenderSnowMan extends RenderLiving<EntitySnowman> {
   public static ResourceLocation snowManTextures = new ResourceLocation("textures/entity/snowman.png");
   public ModelEnderCrystal field_0006;
   public JsonBlendingMode field_0007;
   public EntityCloudFX$Factory field_0000;
   public RenderGuardian field_0003;
   public WorldGenPumpkin field_0004;
   public NaturalTextures field_0001;
   public WebSocketHandshakeException field_0005;

   public ResourceLocation getEntityTexture(EntitySnowman var1) {
      return snowManTextures;
   }

   public ModelSnowMan getMainModel() {
      return (ModelSnowMan)super.getMainModel();
   }

   public RenderSnowMan(RenderManager var1) {
      super(var1, new ModelSnowMan(), 0.5F);
      this.a(new LayerSnowmanHead(this));
   }
}
