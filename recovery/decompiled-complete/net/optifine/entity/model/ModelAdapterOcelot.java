package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.passive.EntityOcelot;
import net.optifine.reflect.Reflector;

public class ModelAdapterOcelot extends ModelAdapter {
   public static Map<String, Integer> mapPartFields = null;

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
   }

   public ModelAdapterOcelot() {
      super(EntityOcelot.class, "ocelot", 0.4F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderOcelot(var3, var1, var2);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelOcelot();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelOcelot)) {
         return null;
      } else {
         ModelOcelot var3 = (ModelOcelot)var1;
         Map var4 = getMapPartFields();
         if (var4.containsKey(var2)) {
            int var5 = (Integer)var4.get(var2);
            return (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelOcelot_ModelRenderers, var5);
         } else {
            return null;
         }
      }
   }

   public static Map<String, Integer> getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap<>();
         mapPartFields.put("back_left_leg", 0);
         mapPartFields.put("back_right_leg", 1);
         mapPartFields.put("front_left_leg", 2);
         mapPartFields.put("front_right_leg", 3);
         mapPartFields.put("tail", 4);
         mapPartFields.put("tail2", 5);
         mapPartFields.put("head", 6);
         mapPartFields.put("body", 7);
         return mapPartFields;
      }
   }
}
