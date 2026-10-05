package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.src.Config;

public class ModelAdapterArmorStand extends ModelAdapterBiped {
   public ModelAdapterArmorStand() {
      super(EntityArmorStand.class, "armor_stand", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      String[] var1 = super.getModelRendererNames();
      return (String[])Config.addObjectsToArray(var1, new String[]{"right", "left", "waist", "base"});
   }

   @Override
   public ModelBase makeModel() {
      return new ModelArmorStand();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      ArmorStandRenderer var4 = new ArmorStandRenderer(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelArmorStand)) {
         return null;
      } else {
         ModelArmorStand var3 = (ModelArmorStand)var1;
         return var2.equals("right")
            ? var3.standRightSide
            : (
               var2.equals("left")
                  ? var3.standLeftSide
                  : (var2.equals("waist") ? var3.standWaist : (var2.equals("base") ? var3.standBase : super.getModelRenderer(var3, var2)))
            );
      }
   }
}
