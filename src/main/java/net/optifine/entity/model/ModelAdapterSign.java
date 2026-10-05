package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntitySign;
import net.optifine.reflect.Reflector;

public class ModelAdapterSign extends ModelAdapter {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer var4 = var3.getSpecialRendererByClass(TileEntitySign.class);
      if (!(var4 instanceof TileEntitySignRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntitySignRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntitySignRenderer_model.exists()) {
            Config.warn("Field not found: TileEntitySignRenderer.model");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntitySignRenderer_model, var1);
            return (IEntityRenderer)var4;
         }
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"board", "stick"};
   }

   public ModelAdapterSign() {
      super(TileEntitySign.class, "sign", 0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSign)) {
         return null;
      } else {
         ModelSign var3 = (ModelSign)var1;
         return var2.equals("board") ? var3.signBoard : (var2.equals("stick") ? var3.signStick : null);
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSign();
   }
}
