package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityIronGolem;

public class ModelAdapterIronGolem extends ModelAdapter {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderIronGolem var4 = new RenderIronGolem(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
   }

   public ModelAdapterIronGolem() {
      super(EntityIronGolem.class, "iron_golem", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelIronGolem();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelIronGolem)) {
         return null;
      } else {
         ModelIronGolem var3 = (ModelIronGolem)var1;
         return var2.equals("head")
            ? var3.ironGolemHead
            : (
               var2.equals("body")
                  ? var3.ironGolemBody
                  : (
                     var2.equals("left_arm")
                        ? var3.ironGolemLeftArm
                        : (
                           var2.equals("right_arm")
                              ? var3.ironGolemRightArm
                              : (var2.equals("left_leg") ? var3.ironGolemLeftLeg : (var2.equals("right_leg") ? var3.ironGolemRightLeg : null))
                        )
                  )
            );
      }
   }
}
