package net.minecraft.client.renderer.entity;

import io.netty.channel.local.LocalChannel;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReservationNode;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import org.slf4j.helpers.NOPLoggerFactory;

public class RenderWolf extends RenderLiving<EntityWolf> {
   public EntityAILeapAtTarget field_0002;
   public ConcurrentHashMapV8$ReservationNode field_0005;
   public static ResourceLocation tamedWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   public static ResourceLocation wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
   public NOPLoggerFactory field_0003;
   public LocalChannel field_0004;
   public static ResourceLocation anrgyWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   public void doRender(EntityWolf var1, double var2, double var4, double var6, float var8, float var9) {
      if (var1.isWolfWet()) {
         float var10 = var1.a_(var9) * var1.getShadingWhileWet(var9);
         GlStateManager.color(var10, var10, var10);
      }

      super.doRender((EntityWolf)var1, var2, var4, var6, var8, var9);
   }

   public ResourceLocation getEntityTexture(EntityWolf var1) {
      return var1.isTamed() ? tamedWolfTextures : (var1.isAngry() ? anrgyWolfTextures : wolfTextures);
   }

   public RenderWolf(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
      this.a(new LayerWolfCollar(this));
   }

   public float handleRotationFloat(EntityWolf var1, float var2) {
      return var1.getTailRotation();
   }
}
