package net.minecraft.client.resources.model;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;

public class WeightedBakedModel implements IBakedModel {
   public IBakedModel baseModel;
   public int totalWeight;
   public List<WeightedBakedModel.MyWeighedRandomItem> models;

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

   public WeightedBakedModel(List<WeightedBakedModel.MyWeighedRandomItem> var1) {
      this.models = var1;
      this.totalWeight = WeightedRandom.getTotalWeight(var1);
      this.baseModel = ((WeightedBakedModel.MyWeighedRandomItem)var1.get(0)).model;
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

   public static class Builder {
      public List<WeightedBakedModel.MyWeighedRandomItem> listItems = Lists.newArrayList();

      public WeightedBakedModel build() {
         Collections.sort(this.listItems);
         return new WeightedBakedModel(this.listItems);
      }

      public WeightedBakedModel.Builder add(IBakedModel var1, int var2) {
         this.listItems.add(new WeightedBakedModel.MyWeighedRandomItem(var1, var2));
         return this;
      }

      public IBakedModel first() {
         return this.listItems.get(0).model;
      }
   }

   public static class MyWeighedRandomItem extends WeightedRandom.Item implements Comparable<WeightedBakedModel.MyWeighedRandomItem> {
      public IBakedModel model;

      public int compareTo(WeightedBakedModel.MyWeighedRandomItem var1) {
         return ComparisonChain.start().compare(var1.a, this.a).compare(this.getCountQuads(), var1.getCountQuads()).result();
      }

      public MyWeighedRandomItem(IBakedModel var1, int var2) {
         super(var2);
         this.model = var1;
      }

      public int getCountQuads() {
         int var1 = this.model.getGeneralQuads().size();

         for (EnumFacing var5 : EnumFacing.values()) {
            var1 += this.model.getFaceQuads(var5).size();
         }

         return var1;
      }

      @Override
      public String toString() {
         return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.model + '}';
      }
   }
}
