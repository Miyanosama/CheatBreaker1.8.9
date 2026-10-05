package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;
import org.apache.log4j.NDC;

public class ModelAdapterEndermite extends ModelAdapter {
   public ModelAdapterBanner field_0000;
   public NDC field_0001;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderEndermite var4 = new RenderEndermite(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public ModelBase makeModel() {
      return new ModelEnderMite();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4"};
   }

   public ModelAdapterEndermite() {
      super(EntityEndermite.class, "endermite", 0.3F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelEnderMite)) {
         return null;
      } else {
         ModelEnderMite var3 = (ModelEnderMite)var1;
         String var4 = "body";
         if (var2.startsWith(var4)) {
            ModelRenderer[] var5 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelEnderMite_bodyParts);
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
