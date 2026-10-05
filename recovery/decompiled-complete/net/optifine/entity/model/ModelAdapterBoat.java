package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.command.PlayerSelector$4;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterBoat extends ModelAdapter {
   public PlayerSelector$4 field_0000;

   public ModelAdapterBoat() {
      super(EntityBoat.class, "boat", 0.5F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBoat)) {
         return null;
      } else {
         ModelBoat var3 = (ModelBoat)var1;
         return var2.equals("bottom")
            ? var3.boatSides[0]
            : (
               var2.equals("back")
                  ? var3.boatSides[1]
                  : (var2.equals("front") ? var3.boatSides[2] : (var2.equals("right") ? var3.boatSides[3] : (var2.equals("left") ? var3.boatSides[4] : null)))
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBoat();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderBoat var4 = new RenderBoat(var3);
      if (!Reflector.RenderBoat_modelBoat.exists()) {
         Config.warn("Field not found: RenderBoat.modelBoat");
         return null;
      } else {
         Reflector.setFieldValue(var4, Reflector.RenderBoat_modelBoat, var1);
         var4.c = var2;
         return var4;
      }
   }
}
