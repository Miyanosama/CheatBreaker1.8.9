package net.optifine.entity.model;

import io.netty.buffer.CompositeByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.GameRules$Value;
import net.optifine.reflect.Reflector;

public class ModelAdapterDragon extends ModelAdapter {
   public CompositeByteBuf field_0000;
   public GameRules$Value field_0001;

   @Override
   public ModelBase makeModel() {
      return new ModelDragon(0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelDragon)) {
         return null;
      } else {
         ModelDragon var3 = (ModelDragon)var1;
         return var2.equals("head")
            ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 0)
            : (
               var2.equals("spine")
                  ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 1)
                  : (
                     var2.equals("jaw")
                        ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 2)
                        : (
                           var2.equals("body")
                              ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 3)
                              : (
                                 var2.equals("rear_leg")
                                    ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 4)
                                    : (
                                       var2.equals("front_leg")
                                          ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 5)
                                          : (
                                             var2.equals("rear_leg_tip")
                                                ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 6)
                                                : (
                                                   var2.equals("front_leg_tip")
                                                      ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 7)
                                                      : (
                                                         var2.equals("rear_foot")
                                                            ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 8)
                                                            : (
                                                               var2.equals("front_foot")
                                                                  ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 9)
                                                                  : (
                                                                     var2.equals("wing")
                                                                        ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelDragon_ModelRenderers, 10)
                                                                        : (
                                                                           var2.equals("wing_tip")
                                                                              ? (ModelRenderer)Reflector.getFieldValue(
                                                                                 var3, Reflector.ModelDragon_ModelRenderers, 11
                                                                              )
                                                                              : null
                                                                        )
                                                                  )
                                                            )
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

   public ModelAdapterDragon() {
      super(EntityDragon.class, "dragon", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderDragon var4 = new RenderDragon(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{
         "head", "spine", "jaw", "body", "rear_leg", "front_leg", "rear_leg_tip", "front_leg_tip", "rear_foot", "front_foot", "wing", "wing_tip"
      };
   }
}
