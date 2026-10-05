package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;

public class LayerWolfCollar implements LayerRenderer<EntityWolf> {
   public static ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
   public RenderWolf wolfRenderer;

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   public LayerWolfCollar(RenderWolf var1) {
      this.wolfRenderer = var1;
   }

   public void doRenderLayer(EntityWolf var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (var1.isTamed() && !var1.isInvisible()) {
         this.wolfRenderer.a(WOLF_COLLAR);
         EnumDyeColor var9 = EnumDyeColor.byMetadata(var1.getCollarColor().getMetadata());
         float[] var10 = EntitySheep.getDyeRgb(var9);
         if (Config.isCustomColors()) {
            var10 = CustomColors.getWolfCollarColors(var9, var10);
         }

         GlStateManager.color(var10[0], var10[1], var10[2]);
         this.wolfRenderer.getMainModel().render(var1, var2, var3, var5, var6, var7, var8);
      }
   }
}
