package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variants;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public class ModelVariableFloat implements IExpressionFloat {
   public ModelRenderer modelRenderer;
   public ModelVariableType enumModelVariable;
   public ModelBlockDefinition$Variants field_0000;
   public String name;

   @Override
   public float eval() {
      return this.getValue();
   }

   public float getValue() {
      return this.enumModelVariable.getFloat(this.modelRenderer);
   }

   public void setValue(float var1) {
      this.enumModelVariable.setFloat(this.modelRenderer, var1);
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ModelVariableFloat(String var1, ModelRenderer var2, ModelVariableType var3) {
      this.name = var1;
      this.modelRenderer = var2;
      this.enumModelVariable = var3;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
