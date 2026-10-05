package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterGhast extends ModelAdapter {
   public ModelAdapterGhast() {
      super(EntityGhast.class, "ghast", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelGhast();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelGhast)) {
         return null;
      } else {
         ModelGhast var3 = (ModelGhast)var1;
         if (var2.equals("body")) {
            return (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelGhast_body);
         } else {
            String var4 = "tentacle";
            if (var2.startsWith(var4)) {
               ModelRenderer[] var5 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelGhast_tentacles);
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
      RenderGhast var4 = new RenderGhast(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }
}
