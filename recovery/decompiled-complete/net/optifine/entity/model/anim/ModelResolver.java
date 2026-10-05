package net.optifine.entity.model.anim;

import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.ModelBlock$1;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.expr.IExpression;
import net.optifine.shaders.ShaderProgramData;
import recovered.unidentified.UnidentifiedClass1222;

public class ModelResolver implements IModelResolver {
   public IRenderResolver renderResolver;
   public ShaderProgramData field_0011;
   public ParserEnchantmentId field_0005;
   public MessageToByteEncoder field_0010;
   public ModelBase model;
   public ModelRenderer partModelRenderer;
   public ModelRenderer thisModelRenderer;
   public UnidentifiedClass1222 field_0009;
   public EntityTrackerEntry field_0003;
   public NBTTagEnd field_0013;
   public CustomModelRenderer[] customModelRenderers;
   public StructureStrongholdPieces$PieceWeight field_0007;
   public ModelAdapter modelAdapter;
   public ModelBlock$1 field_0004;

   @Override
   public ModelVariableFloat getModelVariable(String var1) {
      String[] var2 = Config.tokenize(var1, ".");
      if (var2.length != 2) {
         return null;
      } else {
         String var3 = var2[0];
         String var4 = var2[1];
         ModelRenderer var5 = this.getModelRenderer(var3);
         if (var5 == null) {
            return null;
         } else {
            ModelVariableType var6 = ModelVariableType.parse(var4);
            return var6 == null ? null : new ModelVariableFloat(var1, var5, var6);
         }
      }
   }

   public ModelResolver(ModelAdapter var1, ModelBase var2, CustomModelRenderer[] var3) {
      this.modelAdapter = var1;
      this.model = var2;
      this.customModelRenderers = var3;
      Class var4 = var1.getEntityClass();
      if (TileEntity.class.isAssignableFrom(var4)) {
         this.renderResolver = new RenderResolverTileEntity();
      } else {
         this.renderResolver = new RenderResolverEntity();
      }
   }

   public void setPartModelRenderer(ModelRenderer var1) {
      this.partModelRenderer = var1;
   }

   @Override
   public ModelRenderer getModelRenderer(String var1) {
      if (var1 == null) {
         return null;
      } else if (var1.indexOf(":") >= 0) {
         String[] var7 = Config.tokenize(var1, ":");
         ModelRenderer var8 = this.getModelRenderer(var7[0]);

         for (int var9 = 1; var9 < var7.length; var9++) {
            String var10 = var7[var9];
            ModelRenderer var11 = var8.getChildDeep(var10);
            if (var11 == null) {
               return null;
            }

            var8 = var11;
         }

         return var8;
      } else if (this.thisModelRenderer != null && var1.equals("this")) {
         return this.thisModelRenderer;
      } else if (this.partModelRenderer != null && var1.equals("part")) {
         return this.partModelRenderer;
      } else {
         ModelRenderer var2 = this.modelAdapter.getModelRenderer(this.model, var1);
         if (var2 != null) {
            return var2;
         } else {
            for (int var3 = 0; var3 < this.customModelRenderers.length; var3++) {
               CustomModelRenderer var4 = this.customModelRenderers[var3];
               ModelRenderer var5 = var4.getModelRenderer();
               if (var1.equals(var5.getId())) {
                  return var5;
               }

               ModelRenderer var6 = var5.getChildDeep(var1);
               if (var6 != null) {
                  return var6;
               }
            }

            return null;
         }
      }
   }

   @Override
   public IExpression getExpression(String var1) {
      ModelVariableFloat var2 = this.getModelVariable(var1);
      if (var2 != null) {
         return var2;
      } else {
         IExpression var3 = this.renderResolver.getParameter(var1);
         return var3 != null ? var3 : null;
      }
   }

   public void setThisModelRenderer(ModelRenderer var1) {
      this.thisModelRenderer = var1;
   }
}
