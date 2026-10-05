package net.minecraft.client.resources.model;

import io.netty.channel.AbstractChannel$AbstractUnsafe$6;
import java.util.List;
import javax.vecmath.Tuple4i;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.stream.IngestServerTester$1;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
   public NetHandlerHandshakeTCP field_0002;
   public IngestServerTester$1 field_0004;
   public ItemCameraTransforms cameraTransforms;
   public Tuple4i field_0003;
   public AbstractChannel$AbstractUnsafe$6 field_0000;

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
