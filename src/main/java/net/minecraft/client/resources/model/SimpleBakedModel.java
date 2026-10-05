package net.minecraft.client.resources.model;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class SimpleBakedModel implements IBakedModel {
   public List<BakedQuad> generalQuads;
   public ItemCameraTransforms cameraTransforms;
   public boolean ambientOcclusion;
   public boolean gui3d;
   public List<List<BakedQuad>> faceQuads;
   public TextureAtlasSprite texture;

   @Override
   public List<BakedQuad> getGeneralQuads() {
      return this.generalQuads;
   }

   public SimpleBakedModel(List<BakedQuad> var1, List<List<BakedQuad>> var2, boolean var3, boolean var4, TextureAtlasSprite var5, ItemCameraTransforms var6) {
      this.generalQuads = var1;
      this.faceQuads = var2;
      this.ambientOcclusion = var3;
      this.gui3d = var4;
      this.texture = var5;
      this.cameraTransforms = var6;
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return this.texture;
   }

   @Override
   public List<BakedQuad> getFaceQuads(EnumFacing var1) {
      return this.faceQuads.get(var1.ordinal());
   }

   @Override
   public boolean isAmbientOcclusion() {
      return this.ambientOcclusion;
   }

   @Override
   public boolean isBuiltInRenderer() {
      return false;
   }

   @Override
   public boolean isGui3d() {
      return this.gui3d;
   }

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   public static class Builder {
      public boolean builderGui3d;
      public ItemCameraTransforms builderCameraTransforms;
      public List<List<BakedQuad>> builderFaceQuads;
      public boolean builderAmbientOcclusion;
      public TextureAtlasSprite builderTexture;
      public List<BakedQuad> builderGeneralQuads = Lists.newArrayList();

      public Builder(ModelBlock var1) {
         this(var1.isAmbientOcclusion(), var1.isGui3d(), var1.getAllTransforms());
      }

      public IBakedModel makeBakedModel() {
         if (this.builderTexture == null) {
            throw new RuntimeException("Missing particle!");
         } else {
            return new SimpleBakedModel(
               this.builderGeneralQuads,
               this.builderFaceQuads,
               this.builderAmbientOcclusion,
               this.builderGui3d,
               this.builderTexture,
               this.builderCameraTransforms
            );
         }
      }

      public SimpleBakedModel.Builder addGeneralQuad(BakedQuad var1) {
         this.builderGeneralQuads.add(var1);
         return this;
      }

      public void addGeneralBreakingFours(IBakedModel var1, TextureAtlasSprite var2) {
         for (BakedQuad var4 : var1.getGeneralQuads()) {
            this.addGeneralQuad(new BreakingFour(var4, var2));
         }
      }

      public Builder(IBakedModel var1, TextureAtlasSprite var2) {
         this(var1.isAmbientOcclusion(), var1.isGui3d(), var1.getItemCameraTransforms());
         this.builderTexture = var1.getParticleTexture();

         for (EnumFacing var6 : EnumFacing.values()) {
            this.addFaceBreakingFours(var1, var2, var6);
         }

         this.addGeneralBreakingFours(var1, var2);
      }

      public Builder(boolean var1, boolean var2, ItemCameraTransforms var3) {
         this.builderFaceQuads = Lists.newArrayListWithCapacity(6);

         for (EnumFacing var7 : EnumFacing.values()) {
            this.builderFaceQuads.add(Lists.newArrayList());
         }

         this.builderAmbientOcclusion = var1;
         this.builderGui3d = var2;
         this.builderCameraTransforms = var3;
      }

      public void addFaceBreakingFours(IBakedModel var1, TextureAtlasSprite var2, EnumFacing var3) {
         for (BakedQuad var5 : var1.getFaceQuads(var3)) {
            this.addFaceQuad(var3, new BreakingFour(var5, var2));
         }
      }

      public SimpleBakedModel.Builder addFaceQuad(EnumFacing var1, BakedQuad var2) {
         this.builderFaceQuads.get(var1.ordinal()).add(var2);
         return this;
      }

      public SimpleBakedModel.Builder setTexture(TextureAtlasSprite var1) {
         this.builderTexture = var1;
         return this;
      }
   }
}
