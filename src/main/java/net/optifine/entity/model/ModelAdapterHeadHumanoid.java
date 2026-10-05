package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntitySkull;
import net.optifine.reflect.Reflector;

public class ModelAdapterHeadHumanoid extends ModelAdapter {
   public ModelAdapterHeadHumanoid() {
      super(TileEntitySkull.class, "head_humanoid", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer var4 = var3.getSpecialRendererByClass(TileEntitySkull.class);
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
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelHumanoidHead)) {
         return null;
      } else {
         ModelHumanoidHead var3 = (ModelHumanoidHead)var1;
         return var2.equals("head")
            ? var3.skeletonHead
            : (
               var2.equals("head2")
                  ? (!Reflector.ModelHumanoidHead_head.exists() ? null : (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelHumanoidHead_head))
                  : null
            );
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelHumanoidHead();
   }
}
