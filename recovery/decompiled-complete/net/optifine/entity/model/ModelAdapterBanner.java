package net.optifine.entity.model;

import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityBanner;
import net.optifine.reflect.Reflector;

public class ModelAdapterBanner extends ModelAdapter {
   public ModelAdapterBanner() {
      super(TileEntityBanner.class, "banner", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBanner();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"slate", "stand", "top"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      Object var4 = var3.getSpecialRendererByClass(TileEntityBanner.class);
      if (!(var4 instanceof TileEntityBannerRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntityBannerRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntityBannerRenderer_bannerModel.exists()) {
            Config.warn("Field not found: TileEntityBannerRenderer.bannerModel");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntityBannerRenderer_bannerModel, var1);
            return (IEntityRenderer)var4;
         }
      }
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBanner)) {
         return null;
      } else {
         ModelBanner var3 = (ModelBanner)var1;
         return var2.equals("slate") ? var3.bannerSlate : (var2.equals("stand") ? var3.bannerStand : (var2.equals("top") ? var3.bannerTop : null));
      }
   }
}
