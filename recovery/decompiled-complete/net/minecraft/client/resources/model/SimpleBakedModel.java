package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass3707;

public class SimpleBakedModel implements IBakedModel {
   public List<BakedQuad> generalQuads;
   public ItemCameraTransforms cameraTransforms;
   public boolean ambientOcclusion;
   public UnidentifiedClass3707 field_0004;
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
}
