package net.optifine.entity.model.anim;

import net.minecraft.block.BlockStairs$EnumHalf;
import net.minecraft.client.gui.GuiLabel;
import net.optifine.expr.IExpression;
import recovered.unidentified.UnidentifiedClass3833;

public class RenderResolverEntity implements IRenderResolver {
   public UnidentifiedClass3833 field_0001;
   public GuiLabel field_0002;
   public BlockStairs$EnumHalf field_0000;

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
