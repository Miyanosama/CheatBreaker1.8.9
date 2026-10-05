package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
   public ItemCameraTransforms cameraTransforms;

   @Override
   public List<BakedQuad> getGeneralQuads() {
      return null;
   }

   @Override
   public boolean isBuiltInRenderer() {
      return true;
   }

   @Override
   public List<BakedQuad> getFaceQuads(EnumFacing var1) {
      return null;
   }

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   @Override
   public boolean isAmbientOcclusion() {
      return false;
   }

   @Override
   public boolean isGui3d() {
      return true;
   }

   public BuiltInModel(ItemCameraTransforms var1) {
      this.cameraTransforms = var1;
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return null;
   }
}
