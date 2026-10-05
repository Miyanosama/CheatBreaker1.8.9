package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecart extends ModelAdapter {
   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelMinecart)) {
         return null;
      } else {
         ModelMinecart var3 = (ModelMinecart)var1;
         return var2.equals("bottom")
            ? var3.sideModels[0]
            : (
               var2.equals("back")
                  ? var3.sideModels[1]
                  : (
                     var2.equals("front")
                        ? var3.sideModels[2]
                        : (
                           var2.equals("right")
                              ? var3.sideModels[3]
                              : (var2.equals("left") ? var3.sideModels[4] : (var2.equals("dirt") ? var3.sideModels[5] : null))
                        )
                  )
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
   }

   public ModelAdapterMinecart(Class var1, String var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderMinecart var4 = new RenderMinecart(var3);
      if (!Reflector.RenderMinecart_modelMinecart.exists()) {
         Config.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         Reflector.setFieldValue(var4, Reflector.RenderMinecart_modelMinecart, var1);
         var4.c = var2;
         return var4;
      }
   }

   public ModelAdapterMinecart() {
      super(EntityMinecart.class, "minecart", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelMinecart();
   }
}
