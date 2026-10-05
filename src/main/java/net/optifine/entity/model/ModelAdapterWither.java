package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterWither extends ModelAdapter {
   public ModelAdapterWither() {
      super(EntityWither.class, "wither", 0.5F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelWither(0.0F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderWither var4 = new RenderWither(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelWither)) {
         return null;
      } else {
         ModelWither var3 = (ModelWither)var1;
         String var4 = "body";
         if (var2.startsWith(var4)) {
            ModelRenderer[] var9 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelWither_bodyParts);
            if (var9 == null) {
               return null;
            } else {
               String var10 = var2.substring(var4.length());
               int var11 = Config.parseInt(var10, -1);
               var11--;
               return var11 >= 0 && var11 < var9.length ? var9[var11] : null;
            }
         } else {
            String var5 = "head";
            if (var2.startsWith(var5)) {
               ModelRenderer[] var6 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelWither_heads);
               if (var6 == null) {
                  return null;
               } else {
                  String var7 = var2.substring(var5.length());
                  int var8 = Config.parseInt(var7, -1);
                  var8--;
                  return var8 >= 0 && var8 < var6.length ? var6[var8] : null;
               }
            } else {
               return null;
            }
         }
      }
   }
}
