package net.optifine.entity.model;

import io.netty.util.internal.logging.Log4JLogger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntitySkull;
import net.optifine.reflect.Reflector;

public class ModelAdapterHeadSkeleton extends ModelAdapter {
   public Log4JLogger field_0000;

   @Override
   public ModelBase makeModel() {
      return new ModelSkeletonHead(0, 0, 64, 32);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSkeletonHead)) {
         return null;
      } else {
         ModelSkeletonHead var3 = (ModelSkeletonHead)var1;
         return var2.equals("head") ? var3.skeletonHead : null;
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      Object var4 = var3.getSpecialRendererByClass(TileEntitySkull.class);
      if (!(var4 instanceof TileEntitySkullRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntitySkullRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntitySkullRenderer_humanoidHead.exists()) {
            Config.warn("Field not found: TileEntitySkullRenderer.humanoidHead");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntitySkullRenderer_humanoidHead, var1);
            return (IEntityRenderer)var4;
         }
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   public ModelAdapterHeadSkeleton() {
      super(TileEntitySkull.class, "head_skeleton", 0.0F);
   }
}
