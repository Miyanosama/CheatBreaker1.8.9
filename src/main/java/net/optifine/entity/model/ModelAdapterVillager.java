package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.passive.EntityVillager;

public class ModelAdapterVillager extends ModelAdapter {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderVillager var4 = new RenderVillager(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public ModelBase makeModel() {
      return new ModelVillager(0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelVillager)) {
         return null;
      } else {
         ModelVillager var3 = (ModelVillager)var1;
         return var2.equals("head")
            ? var3.villagerHead
            : (
               var2.equals("body")
                  ? var3.villagerBody
                  : (
                     var2.equals("arms")
                        ? var3.villagerArms
                        : (
                           var2.equals("left_leg")
                              ? var3.leftVillagerLeg
                              : (var2.equals("right_leg") ? var3.rightVillagerLeg : (var2.equals("nose") ? var3.villagerNose : null))
                        )
                  )
            );
      }
   }

   public ModelAdapterVillager() {
      super(EntityVillager.class, "villager", 0.5F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose"};
   }
}
