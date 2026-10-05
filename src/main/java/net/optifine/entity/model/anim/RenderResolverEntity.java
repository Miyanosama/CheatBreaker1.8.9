package net.optifine.entity.model.anim;

import net.optifine.expr.IExpression;

public class RenderResolverEntity implements IRenderResolver {
   @Override
   public IExpression getParameter(String var1) {
      RenderEntityParameterBool var2 = RenderEntityParameterBool.parse(var1);
      if (var2 != null) {
         return var2;
      } else {
         RenderEntityParameterFloat var3 = RenderEntityParameterFloat.parse(var1);
         return var3 != null ? var3 : null;
      }
   }
}
