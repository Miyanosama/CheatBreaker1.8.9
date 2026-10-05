package net.optifine.entity.model;

import io.netty.handler.ssl.OpenSslEngine;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomModelRenderer {
   public LayerWitherAura field_0003;
   public ModelRenderer modelRenderer;
   public RenderOcelot field_0002;
   public ModelUpdater modelUpdater;
   public String modelPart;
   public boolean attach;
   public OpenSslEngine field_0007;
   public DefaultPlayerSkin field_0004;

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
