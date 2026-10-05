package net.minecraft.client.resources.model;

import io.netty.util.concurrent.DefaultEventExecutor;
import java.util.List;
import net.minecraft.block.BlockNewLog$1;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.monster.EntityPigZombie$AIHurtByAggressor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;

public class WeightedBakedModel implements IBakedModel {
   public IBakedModel baseModel;
   public EntityPigZombie$AIHurtByAggressor field_0005;
   public int totalWeight;
   public DefaultEventExecutor field_0004;
   public List<WeightedBakedModel$MyWeighedRandomItem> models;
   public BlockNewLog$1 field_0001;

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.baseModel.getItemCameraTransforms();
   }

   @Override
   public boolean isGui3d() {
      return this.baseModel.isGui3d();
   }

   @Override
   public boolean isBuiltInRenderer() {
      return this.baseModel.isBuiltInRenderer();
   }

   public WeightedBakedModel(List<WeightedBakedModel$MyWeighedRandomItem> var1) {
      this.models = var1;
      this.totalWeight = WeightedRandom.getTotalWeight(var1);
      this.baseModel = ((WeightedBakedModel$MyWeighedRandomItem)var1.get(0)).model;
   }

   public IBakedModel getAlternativeModel(long var1) {
      return WeightedRandom.getRandomItem(this.models, Math.abs((int)var1 >> 16) % this.totalWeight).model;
   }

   @Override
   public boolean isAmbientOcclusion() {
      return this.baseModel.isAmbientOcclusion();
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return this.baseModel.getParticleTexture();
   }

   @Override
   public List<BakedQuad> getGeneralQuads() {
      return this.baseModel.getGeneralQuads();
   }

   @Override
   public List<BakedQuad> getFaceQuads(EnumFacing var1) {
      return this.baseModel.getFaceQuads(var1);
   }
}
