package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterLeadKnot extends ModelAdapter {
   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelLeashKnot)) {
         return null;
      } else {
         ModelLeashKnot var3 = (ModelLeashKnot)var1;
         return var2.equals("knot") ? var3.field_110723_a : null;
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"knot"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderLeashKnot var4 = new RenderLeashKnot(var3);
      if (!Reflector.RenderLeashKnot_leashKnotModel.exists()) {
         Config.warn("Field not found: RenderLeashKnot.leashKnotModel");
         return null;
      } else {
         Reflector.setFieldValue(var4, Reflector.RenderLeashKnot_leashKnotModel, var1);
         var4.c = var2;
         return var4;
      }
   }

   public ModelAdapterLeadKnot() {
      super(EntityLeashKnot.class, "lead_knot", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelLeashKnot();
   }
}
