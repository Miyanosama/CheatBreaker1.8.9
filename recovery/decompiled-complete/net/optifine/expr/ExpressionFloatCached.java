package net.optifine.expr;

import net.minecraft.client.gui.achievement.GuiStats$StatsMobsList;
import net.minecraft.world.gen.feature.WorldGenPumpkin;

public class ExpressionFloatCached implements IExpressionCached, IExpressionFloat {
   public WorldGenPumpkin field_0002;
   public boolean cached;
   public GuiStats$StatsMobsList field_0001;
   public float value;
   public IExpressionFloat expression;

   @Override
   public float eval() {
      if (!this.cached) {
         this.value = this.expression.eval();
         this.cached = true;
      }

      return this.value;
   }

   @Override
   public void reset() {
      this.cached = false;
   }

   @Override
   public String toString() {
      return "cached(" + this.expression + ")";
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ExpressionFloatCached(IExpressionFloat var1) {
      this.expression = var1;
   }
}
