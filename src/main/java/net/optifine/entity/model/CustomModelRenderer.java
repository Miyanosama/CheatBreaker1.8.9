package net.optifine.entity.model;

import net.minecraft.client.model.ModelRenderer;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomModelRenderer {
   public ModelRenderer modelRenderer;
   public ModelUpdater modelUpdater;
   public String modelPart;
   public boolean attach;

   public boolean isAttach() {
      return this.attach;
   }

   public CustomModelRenderer(String var1, boolean var2, ModelRenderer var3, ModelUpdater var4) {
      this.modelPart = var1;
      this.attach = var2;
      this.modelRenderer = var3;
      this.modelUpdater = var4;
   }

   public ModelUpdater getModelUpdater() {
      return this.modelUpdater;
   }

   public ModelRenderer getModelRenderer() {
      return this.modelRenderer;
   }

   public String getModelPart() {
      return this.modelPart;
   }
}
