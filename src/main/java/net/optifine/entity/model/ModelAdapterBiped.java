package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapterBiped extends ModelAdapter {
   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBiped)) {
         return null;
      } else {
         ModelBiped var3 = (ModelBiped)var1;
         return var2.equals("head")
            ? var3.e
            : (
               var2.equals("headwear")
                  ? var3.f
                  : (
                     var2.equals("body")
                        ? var3.g
                        : (
                           var2.equals("left_arm")
                              ? var3.i
                              : (var2.equals("right_arm") ? var3.h : (var2.equals("left_leg") ? var3.k : (var2.equals("right_leg") ? var3.j : null)))
                        )
                  )
            );
      }
   }

   public ModelAdapterBiped(Class var1, String var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
   }
}
