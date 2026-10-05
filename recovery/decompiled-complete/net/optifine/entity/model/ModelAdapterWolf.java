package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.storage.SaveFormatOld;
import net.optifine.reflect.Reflector;
import org.slf4j.helpers.BasicMarkerFactory;

public class ModelAdapterWolf extends ModelAdapter {
   public BasicMarkerFactory field_0000;
   public SaveFormatOld field_0001;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderWolf(var3, var1, var2);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelWolf)) {
         return null;
      } else {
         ModelWolf var3 = (ModelWolf)var1;
         return var2.equals("head")
            ? var3.wolfHeadMain
            : (
               var2.equals("body")
                  ? var3.wolfBody
                  : (
                     var2.equals("leg1")
                        ? var3.wolfLeg1
                        : (
                           var2.equals("leg2")
                              ? var3.wolfLeg2
                              : (
                                 var2.equals("leg3")
                                    ? var3.wolfLeg3
                                    : (
                                       var2.equals("leg4")
                                          ? var3.wolfLeg4
                                          : (
                                             var2.equals("tail")
                                                ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelWolf_tail)
                                                : (var2.equals("mane") ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelWolf_mane) : null)
                                          )
                                    )
                              )
                        )
                  )
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
   }

   public ModelAdapterWolf() {
      super(EntityWolf.class, "wolf", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelWolf();
   }
}
