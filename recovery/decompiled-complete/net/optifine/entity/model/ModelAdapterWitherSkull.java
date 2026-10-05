package net.optifine.entity.model;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker$1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitherSkull extends ModelAdapter {
   public WebSocketServerHandshaker$1 field_0000;

   public ModelAdapterWitherSkull() {
      super(EntityWitherSkull.class, "wither_skull", 0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSkeletonHead)) {
         return null;
      } else {
         ModelSkeletonHead var3 = (ModelSkeletonHead)var1;
         return var2.equals("head") ? var3.skeletonHead : null;
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSkeletonHead();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderWitherSkull var4 = new RenderWitherSkull(var3);
      if (!Reflector.RenderWitherSkull_model.exists()) {
         Config.warn("Field not found: RenderWitherSkull_model");
         return null;
      } else {
         Reflector.setFieldValue(var4, Reflector.RenderWitherSkull_model, var1);
         var4.c = var2;
         return var4;
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }
}
