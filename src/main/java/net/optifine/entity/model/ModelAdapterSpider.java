package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;

public class ModelAdapterSpider extends ModelAdapter {
   @Override
   public ModelBase makeModel() {
      return new ModelSpider();
   }

   public ModelAdapterSpider(Class var1, String var2, float var3) {
      super(var1, var2, var3);
   }

   public ModelAdapterSpider() {
      super(EntitySpider.class, "spider", 1.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSpider)) {
         return null;
      } else {
         ModelSpider var3 = (ModelSpider)var1;
         return var2.equals("head")
            ? var3.spiderHead
            : (
               var2.equals("neck")
                  ? var3.spiderNeck
                  : (
                     var2.equals("body")
                        ? var3.spiderBody
                        : (
                           var2.equals("leg1")
                              ? var3.spiderLeg1
                              : (
                                 var2.equals("leg2")
                                    ? var3.spiderLeg2
                                    : (
                                       var2.equals("leg3")
                                          ? var3.spiderLeg3
                                          : (
                                             var2.equals("leg4")
                                                ? var3.spiderLeg4
                                                : (
                                                   var2.equals("leg5")
                                                      ? var3.spiderLeg5
                                                      : (
                                                         var2.equals("leg6")
                                                            ? var3.spiderLeg6
                                                            : (var2.equals("leg7") ? var3.spiderLeg7 : (var2.equals("leg8") ? var3.spiderLeg8 : null))
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderSpider var4 = new RenderSpider(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }
}
