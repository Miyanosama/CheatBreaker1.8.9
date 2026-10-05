package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityChest;
import net.optifine.reflect.Reflector;

public class ModelAdapterChestLarge extends ModelAdapter {
   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelChest)) {
         return null;
      } else {
         ModelChest var3 = (ModelChest)var1;
         return var2.equals("lid") ? var3.a : (var2.equals("base") ? var3.b : (var2.equals("knob") ? var3.c : null));
      }
   }

   public ModelAdapterChestLarge() {
      super(TileEntityChest.class, "chest_large", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"lid", "base", "knob"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelLargeChest();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer var4 = var3.getSpecialRendererByClass(TileEntityChest.class);
      if (!(var4 instanceof TileEntityChestRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntityChestRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntityChestRenderer_largeChest.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.largeChest");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntityChestRenderer_largeChest, var1);
            return (IEntityRenderer)var4;
         }
      }
   }
}
