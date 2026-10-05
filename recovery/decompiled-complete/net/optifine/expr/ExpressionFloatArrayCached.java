package net.optifine.expr;

import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.entity.passive.EntityRabbit$AIRaidFarm;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;
import net.minecraft.world.gen.MapGenBase;

public class ExpressionFloatArrayCached implements IExpressionFloatArray, IExpressionCached {
   public BiomeGenBase$TempCategory field_0003;
   public boolean cached;
   public float[] value;
   public EntityRabbit$AIRaidFarm field_0004;
   public GuiSleepMP field_0000;
   public IExpressionFloatArray expression;
   public MapGenBase field_0006;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   @Override
   public String toString() {
      return "cached(" + this.expression + ")";
   }

   @Override
   public float[] eval() {
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

   public ExpressionFloatArrayCached(IExpressionFloatArray var1) {
      this.expression = var1;
   }
}
