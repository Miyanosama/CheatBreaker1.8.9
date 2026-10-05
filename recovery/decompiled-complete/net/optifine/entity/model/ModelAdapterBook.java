package net.optifine.entity.model;

import net.minecraft.block.BlockNewLog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.optifine.reflect.Reflector;

public class ModelAdapterBook extends ModelAdapter {
   public S3DPacketDisplayScoreboard field_0000;
   public BlockNewLog field_0001;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      TileEntityRendererDispatcher var3 = TileEntityRendererDispatcher.instance;
      Object var4 = var3.getSpecialRendererByClass(TileEntityEnchantmentTable.class);
      if (!(var4 instanceof TileEntityEnchantmentTableRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)var4).getEntityClass() == null) {
            var4 = new TileEntityEnchantmentTableRenderer();
            ((TileEntitySpecialRenderer)var4).setRendererDispatcher(var3);
         }

         if (!Reflector.TileEntityEnchantmentTableRenderer_modelBook.exists()) {
            Config.warn("Field not found: TileEntityEnchantmentTableRenderer.modelBook");
            return null;
         } else {
            Reflector.setFieldValue(var4, Reflector.TileEntityEnchantmentTableRenderer_modelBook, var1);
            return (IEntityRenderer)var4;
         }
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBook();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBook)) {
         return null;
      } else {
         ModelBook var3 = (ModelBook)var1;
         return var2.equals("cover_right")
            ? var3.coverRight
            : (
               var2.equals("cover_left")
                  ? var3.coverLeft
                  : (
                     var2.equals("pages_right")
                        ? var3.pagesRight
                        : (
                           var2.equals("pages_left")
                              ? var3.pagesLeft
                              : (
                                 var2.equals("flipping_page_right")
                                    ? var3.flippingPageRight
                                    : (var2.equals("flipping_page_left") ? var3.flippingPageLeft : (var2.equals("book_spine") ? var3.bookSpine : null))
                              )
                        )
                  )
            );
      }
   }

   public ModelAdapterBook() {
      super(TileEntityEnchantmentTable.class, "book", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
   }
}
