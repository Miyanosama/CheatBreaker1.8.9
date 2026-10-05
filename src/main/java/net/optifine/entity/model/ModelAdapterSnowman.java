package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.monster.EntitySnowman;

public class ModelAdapterSnowman extends ModelAdapter {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderSnowMan var4 = new RenderSnowMan(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSnowMan();
   }

   public ModelAdapterSnowman() {
      super(EntitySnowman.class, "snow_golem", 0.5F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSnowMan)) {
         return null;
      } else {
         ModelSnowMan var3 = (ModelSnowMan)var1;
         return var2.equals("body")
            ? var3.body
            : (
               var2.equals("body_bottom")
                  ? var3.bottomBody
                  : (var2.equals("head") ? var3.head : (var2.equals("left_hand") ? var3.leftHand : (var2.equals("right_hand") ? var3.rightHand : null)))
            );
      }
   }
}
