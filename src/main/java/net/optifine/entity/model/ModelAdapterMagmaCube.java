package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterMagmaCube extends ModelAdapter {
   @Override
   public ModelBase makeModel() {
      return new ModelMagmaCube();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelMagmaCube)) {
         return null;
      } else {
         ModelMagmaCube var3 = (ModelMagmaCube)var1;
         if (var2.equals("core")) {
            return (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelMagmaCube_core);
         } else {
            String var4 = "segment";
            if (var2.startsWith(var4)) {
               ModelRenderer[] var5 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelMagmaCube_segments);
               if (var5 == null) {
                  return null;
               } else {
                  String var6 = var2.substring(var4.length());
                  int var7 = Config.parseInt(var6, -1);
                  var7--;
                  return var7 >= 0 && var7 < var5.length ? var5[var7] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderMagmaCube var4 = new RenderMagmaCube(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   public ModelAdapterMagmaCube() {
      super(EntityMagmaCube.class, "magma_cube", 0.5F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
   }
}
