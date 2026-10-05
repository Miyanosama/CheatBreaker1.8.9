package net.optifine.entity.model;

import io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityChest;
import net.optifine.expr.ExpressionParser$1;
import net.optifine.reflect.Reflector;

public class ModelAdapterChest extends ModelAdapter {
   public ExpressionParser$1 field_0000;
   public DefaultSpdyHeadersFrame field_0001;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      Object var4 = var3.getSpecialRendererByClass(TileEntityChest.class);
      if (!(var4 instanceof TileEntityChestRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntityChestRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntityChestRenderer_simpleChest.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.simpleChest");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntityChestRenderer_simpleChest, var1);
            return (IEntityRenderer)var4;
         }
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelChest();
   }

   public ModelAdapterChest() {
      super(TileEntityChest.class, "chest", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"lid", "base", "knob"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelChest)) {
         return null;
      } else {
         ModelChest var3 = (ModelChest)var1;
         return var2.equals("lid") ? var3.a : (var2.equals("base") ? var3.b : (var2.equals("knob") ? var3.c : null));
      }
   }
}
