package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.inventory.InventoryCrafting;
import recovered.unidentified.UnidentifiedClass3199;

public class ModelAdapterCreeper extends ModelAdapter {
   public UnidentifiedClass3199 field_0000;
   public InventoryCrafting field_0001;

   @Override
   public ModelBase makeModel() {
      return new ModelCreeper();
   }

   public ModelAdapterCreeper() {
      super(EntityCreeper.class, "creeper", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderCreeper var4 = new RenderCreeper(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelCreeper)) {
         return null;
      } else {
         ModelCreeper var3 = (ModelCreeper)var1;
         return var2.equals("head")
            ? var3.head
            : (
               var2.equals("armor")
                  ? var3.creeperArmor
                  : (
                     var2.equals("body")
                        ? var3.body
                        : (
                           var2.equals("leg1")
                              ? var3.leg1
                              : (var2.equals("leg2") ? var3.leg2 : (var2.equals("leg3") ? var3.leg3 : (var2.equals("leg4") ? var3.leg4 : null)))
                        )
                  )
            );
      }
   }
}
