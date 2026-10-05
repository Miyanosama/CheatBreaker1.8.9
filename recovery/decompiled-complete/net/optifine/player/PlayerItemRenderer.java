package net.optifine.player;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.gui.GuiResourcePackAvailable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class PlayerItemRenderer {
   public GuiResourcePackAvailable field_0001;
   public ModelRenderer modelRenderer;
   public BlockPistonBase field_0000;
   public int attachTo = 0;

   public PlayerItemRenderer(int var1, ModelRenderer var2) {
      this.modelRenderer = null;
      this.attachTo = var1;
      this.modelRenderer = var2;
   }

   public ModelRenderer getModelRenderer() {
      return this.modelRenderer;
   }

   public void render(ModelBiped var1, float var2) {
      ModelRenderer var3 = PlayerItemModel.getAttachModel(var1, this.attachTo);
      if (var3 != null) {
         var3.postRender(var2);
      }

      this.modelRenderer.render(var2);
   }
}
