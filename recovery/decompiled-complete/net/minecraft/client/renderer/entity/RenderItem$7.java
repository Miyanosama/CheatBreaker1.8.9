package net.minecraft.client.renderer.entity;

import javazoom.jl.decoder.LayerIIIDecoder$SBI;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.command.CommandExecuteAt$1;
import net.minecraft.item.ItemStack;
import net.optifine.util.TextureUtils;

public class RenderItem$7 implements ItemMeshDefinition {
   public LayerIIIDecoder$SBI field_0001;
   public CommandExecuteAt$1 field_0003;
   public TextureUtils field_0002;

   @Override
   public ModelResourceLocation getModelLocation(ItemStack var1) {
      return new ModelResourceLocation("banner", "inventory");
   }

   public RenderItem$7(RenderItem var1) {
      this.this$0 = var1;
      super();
   }
}
