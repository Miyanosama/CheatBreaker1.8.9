package net.optifine.entity.model.anim;

import net.minecraft.src.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;

public class ModelVariableUpdater {
   public IExpressionFloat expression;
   public String expressionText;
   public ModelVariableFloat modelVariable;
   public String modelVariableName;

   public void update() {
      float var1 = this.expression.eval();
      this.modelVariable.setValue(var1);
   }

   public ModelVariableUpdater(String var1, String var2) {
      this.modelVariableName = var1;
      this.expressionText = var2;
   }

   public boolean initialize(IModelResolver var1) {
      this.modelVariable = var1.getModelVariable(this.modelVariableName);
      if (this.modelVariable == null) {
         Config.warn("Model variable not found: " + this.modelVariableName);
         return false;
      } else {
         try {
            ExpressionParser var2 = new ExpressionParser(var1);
            this.expression = var2.parseFloat(this.expressionText);
            return true;
         } catch (ParseException var3) {
            Config.warn("Error parsing expression: " + this.expressionText);
            Config.warn(var3.getClass().getName() + ": " + var3.getMessage());
            return false;
         }
      }
   }
}
