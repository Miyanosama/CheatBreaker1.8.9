package net.optifine.entity.model;

import net.minecraft.util.ResourceLocation;

public class CustomEntityRenderer {
   public String basePath;
   public CustomModelRenderer[] customModelRenderers;
   public float shadowSize;
   public String name = null;
   public ResourceLocation textureLocation;

   public CustomEntityRenderer(String var1, String var2, ResourceLocation var3, CustomModelRenderer[] var4, float var5) {
      this.basePath = null;
      this.textureLocation = null;
      this.customModelRenderers = null;
      this.shadowSize = 0.0F;
      this.name = var1;
      this.basePath = var2;
      this.textureLocation = var3;
      this.customModelRenderers = var4;
      this.shadowSize = var5;
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }

   public String getName() {
      return this.name;
   }

   public String getBasePath() {
      return this.basePath;
   }

   public CustomModelRenderer[] getCustomModelRenderers() {
      return this.customModelRenderers;
   }
}
