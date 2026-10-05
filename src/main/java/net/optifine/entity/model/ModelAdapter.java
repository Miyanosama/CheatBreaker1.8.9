package net.optifine.entity.model;

import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapter {
   public String name;
   public String[] aliases;
   public float shadowSize;
   public Class entityClass;

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

      return (net.minecraft.client.model.ModelRenderer[])var3.toArray(new ModelRenderer[var3.size()]);
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
