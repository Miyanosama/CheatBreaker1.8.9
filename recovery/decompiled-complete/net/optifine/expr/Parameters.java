package net.optifine.expr;

import net.minecraft.client.gui.inventory.GuiContainerCreative$ContainerCreative;
import net.minecraft.client.particle.EntityLavaFX$Factory;
import net.minecraft.client.renderer.entity.RenderWolf;

public class Parameters implements IParameters {
   public EntityLavaFX$Factory field_0001;
   public GuiContainerCreative$ContainerCreative field_0003;
   public RenderWolf field_0000;
   public ExpressionType[] parameterTypes;

   @Override
   public ExpressionType[] getParameterTypes(IExpression[] var1) {
      return this.parameterTypes;
   }

   public Parameters(ExpressionType[] var1) {
      this.parameterTypes = var1;
   }
}
