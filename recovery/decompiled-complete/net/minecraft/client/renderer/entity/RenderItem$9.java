package net.minecraft.client.renderer.entity;

import io.netty.channel.AbstractChannelHandlerContext$WriteAndFlushTask;
import io.netty.handler.codec.http.QueryStringEncoder;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MessageDeserializer;

public class RenderItem$9 implements ItemMeshDefinition {
   public QueryStringEncoder field_0001;
   public AbstractChannelHandlerContext$WriteAndFlushTask field_0003;
   public MessageDeserializer field_0002;

   public RenderItem$9(RenderItem var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelLocation(ItemStack var1) {
      return new ModelResourceLocation("filled_map", "inventory");
   }
}
