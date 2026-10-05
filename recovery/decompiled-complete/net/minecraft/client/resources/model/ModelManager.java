package net.minecraft.client.resources.model;

import io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.ResourcePackListEntry$1;
import net.minecraft.util.IRegistry;

public class ModelManager implements IResourceManagerReloadListener {
   public ServerBootstrap$ServerBootstrapAcceptor field_0003;
   public TextureMap texMap;
   public ResourcePackListEntry$1 field_0002;
   public IRegistry<ModelResourceLocation, IBakedModel> modelRegistry;
   public IBakedModel defaultModel;
   public BlockModelShapes modelProvider;

   public IBakedModel getModel(ModelResourceLocation var1) {
      if (var1 == null) {
         return this.defaultModel;
      } else {
         IBakedModel var2 = this.modelRegistry.getObject(var1);
         return var2 == null ? this.defaultModel : var2;
      }
   }

   public ModelManager(TextureMap var1) {
      this.texMap = var1;
      this.modelProvider = new BlockModelShapes(this);
   }

   public BlockModelShapes getBlockModelShapes() {
      return this.modelProvider;
   }

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      ModelBakery var2 = new ModelBakery(var1, this.texMap, this.modelProvider);
      this.modelRegistry = var2.setupModelRegistry();
      this.defaultModel = this.modelRegistry.getObject(ModelBakery.MODEL_MISSING);
      this.modelProvider.reloadModels();
   }

   public TextureMap getTextureMap() {
      return this.texMap;
   }

   public IBakedModel getMissingModel() {
      return this.defaultModel;
   }
}
