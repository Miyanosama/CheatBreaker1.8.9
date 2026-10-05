package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.passive.EntityRabbit;
import net.optifine.reflect.Reflector;

public class ModelAdapterRabbit extends ModelAdapter {
   public static Map<String, Integer> mapPartFields = null;
   public RendererLivingEntity field_0001;

   @Override
   public ModelBase makeModel() {
      return new ModelRabbit();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderRabbit(var3, var1, var2);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{
         "left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"
      };
   }

   public static Map<String, Integer> getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap<>();
         mapPartFields.put("left_foot", 0);
         mapPartFields.put("right_foot", 1);
         mapPartFields.put("left_thigh", 2);
         mapPartFields.put("right_thigh", 3);
         mapPartFields.put("body", 4);
         mapPartFields.put("left_arm", 5);
         mapPartFields.put("right_arm", 6);
         mapPartFields.put("head", 7);
         mapPartFields.put("right_ear", 8);
         mapPartFields.put("left_ear", 9);
         mapPartFields.put("tail", 10);
         mapPartFields.put("nose", 11);
         return mapPartFields;
      }
   }

   public ModelAdapterRabbit() {
      super(EntityRabbit.class, "rabbit", 0.3F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelRabbit)) {
         return null;
      } else {
         ModelRabbit var3 = (ModelRabbit)var1;
         Map var4 = getMapPartFields();
         if (var4.containsKey(var2)) {
            int var5 = (Integer)var4.get(var2);
            return (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelRabbit_renderers, var5);
         } else {
            return null;
         }
      }
   }
}
