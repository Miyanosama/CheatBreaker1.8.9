package net.minecraft.client.resources;

import io.netty.channel.SimpleChannelInboundHandler;
import java.io.IOException;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;

public class GrassColorReloadListener implements IResourceManagerReloadListener {
   public StructureNetherBridgePieces$Piece field_0001;
   public SimpleChannelInboundHandler field_0002;
   public static ResourceLocation LOC_GRASS_PNG = new ResourceLocation("textures/colormap/grass.png");

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      try {
         ColorizerGrass.setGrassBiomeColorizer(TextureUtil.readImageData(var1, LOC_GRASS_PNG));
      } catch (IOException var3) {
      }
   }
}
