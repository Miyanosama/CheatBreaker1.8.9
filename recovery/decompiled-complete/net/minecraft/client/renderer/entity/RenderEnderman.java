package net.minecraft.client.renderer.entity;

import io.netty.channel.ConnectTimeoutException;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving<EntityEnderman> {
   public ConnectTimeoutException field_0001;
   public Random rnd = new Random();
   public ModelEnderman endermanModel = (ModelEnderman)this.f;
   public static ResourceLocation endermanTextures = new ResourceLocation("textures/entity/enderman/enderman.png");

   public RenderEnderman(RenderManager var1) {
      super(var1, new ModelEnderman(0.0F), 0.5F);
      this.a(new LayerEndermanEyes(this));
      this.a(new LayerHeldBlock(this));
   }

   public ResourceLocation getEntityTexture(EntityEnderman var1) {
      return endermanTextures;
   }

   public void doRender(EntityEnderman var1, double var2, double var4, double var6, float var8, float var9) {
      this.endermanModel.isCarrying = var1.getHeldBlockState().getBlock().getMaterial() != Material.air;
      this.endermanModel.isAttacking = var1.isScreaming();
      if (var1.isScreaming()) {
         double var10 = 0.02;
         var2 += this.rnd.nextGaussian() * var10;
         var6 += this.rnd.nextGaussian() * var10;
      }

      super.doRender((EntityEnderman)var1, var2, var4, var6, var8, var9);
   }
}
