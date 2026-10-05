package net.optifine.entity.model;

import com.cheatbreaker.client.module.type.TabListModule;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntityBanner$EnumBannerPattern;
import recovered.unidentified.UnidentifiedClass1748;

public abstract class ModelAdapter {
   public String name;
   public TabListModule field_0006;
   public String[] aliases;
   public TileEntityBanner$EnumBannerPattern field_0005;
   public float shadowSize;
   public Class entityClass;
   public GuiScreenAddServer field_0007;
   public UnidentifiedClass1748 field_0004;

   public ModelAdapter(Class var1, String var2, float var3) {
      this.entityClass = var1;
      this.name = var2;
      this.shadowSize = var3;
   }

   public ModelRenderer[] getModelRenderers(ModelBase var1) {
      String[] var2 = this.getModelRendererNames();
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < var2.length; var4++) {
         String var5 = var2[var4];
         ModelRenderer var6 = this.getModelRenderer(var1, var5);
         if (var6 != null) {
            var3.add(var6);
         }
      }

      return var3.toArray(new ModelRenderer[var3.size()]);
   }

   public Class getEntityClass() {
      return this.entityClass;
   }

   public abstract ModelRenderer getModelRenderer(ModelBase var1, String var2);

   public String[] getAliases() {
      return this.aliases;
   }

   public abstract String[] getModelRendererNames();

   public abstract ModelBase makeModel();

   public abstract IEntityRenderer makeEntityRender(ModelBase var1, float var2);

   public String getName() {
      return this.name;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }

   public ModelAdapter(Class var1, String var2, float var3, String[] var4) {
      this.entityClass = var1;
      this.name = var2;
      this.shadowSize = var3;
      this.aliases = var4;
   }
}
