package net.minecraft.client.renderer.entity;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder$State;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.optifine.EmissiveTextures;

public class RenderItem$8 implements ItemMeshDefinition {
   public EmissiveTextures field_0002;
   public PathNavigate field_0001;
   public HttpPostRequestEncoder field_0003;
   public SocksCmdRequestDecoder$State field_0000;

   public RenderItem$8(RenderItem var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelLocation(ItemStack var1) {
      return new ModelResourceLocation("enchanted_book", "inventory");
   }
}
