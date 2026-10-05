package net.optifine.shaders.uniform;

import java.util.ArrayList;
import java.util.Map;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionCached;

public class CustomUniforms {
   public CustomUniform[] uniforms;
   public IExpressionCached[] expressionsCached;

   public void update() {
      this.resetCache();

      for (int var1 = 0; var1 < this.uniforms.length; var1++) {
         CustomUniform var2 = this.uniforms[var1];
         var2.update();
      }
   }

   public void setProgram(int var1) {
      for (int var2 = 0; var2 < this.uniforms.length; var2++) {
         CustomUniform var3 = this.uniforms[var2];
         var3.setProgram(var1);
      }
   }

   public CustomUniforms(CustomUniform[] var1, Map<String, IExpression> var2) {
      this.uniforms = var1;
      ArrayList var3 = new ArrayList();

      for (String var5 : var2.keySet()) {
         IExpression var6 = (IExpression)var2.get(var5);
         if (var6 instanceof IExpressionCached) {
            IExpressionCached var7 = (IExpressionCached)var6;
            var3.add(var7);
         }
      }

      this.expressionsCached = (net.optifine.expr.IExpressionCached[])var3.toArray(new IExpressionCached[var3.size()]);
   }

   public void resetCache() {
      for (int var1 = 0; var1 < this.expressionsCached.length; var1++) {
         IExpressionCached var2 = this.expressionsCached[var1];
         var2.reset();
      }
   }

   public void reset() {
      for (int var1 = 0; var1 < this.uniforms.length; var1++) {
         CustomUniform var2 = this.uniforms[var1];
         var2.reset();
      }
   }
}
