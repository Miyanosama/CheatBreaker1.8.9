package net.optifine.expr;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.scoreboard.IScoreObjectiveCriteria$EnumRenderType;
import net.minecraft.world.gen.feature.WorldGenCactus;

public class ParametersVariable implements IParameters {
   public static ExpressionType[] EMPTY = new ExpressionType[0];
   public int maxCount = Integer.MAX_VALUE;
   public ExpressionType[] last;
   public IScoreObjectiveCriteria$EnumRenderType field_0004;
   public ExpressionType[] repeat;
   public ExpressionType[] first;
   public WorldGenCactus field_0006;

   public ExpressionType[] getLast() {
      return this.last;
   }

   public ParametersVariable last(ExpressionType... var1) {
      return new ParametersVariable(this.first, this.repeat, var1);
   }

   public ParametersVariable maxCount(int var1) {
      return new ParametersVariable(this.first, this.repeat, this.last, var1);
   }

   public ExpressionType[] getFirst() {
      return this.first;
   }

   public ExpressionType[] getRepeat() {
      return this.repeat;
   }

   public int getCountRepeat() {
      return this.first == null ? 0 : this.first.length;
   }

   public ParametersVariable repeat(ExpressionType... var1) {
      return new ParametersVariable(this.first, var1, this.last);
   }

   public ParametersVariable(ExpressionType[] var1, ExpressionType[] var2, ExpressionType[] var3) {
      this(var1, var2, var3, Integer.MAX_VALUE);
   }

   @Override
   public ExpressionType[] getParameterTypes(IExpression[] var1) {
      int var2 = this.first.length + this.last.length;
      int var3 = var1.length - var2;
      int var4 = 0;

      for (int var5 = 0; var5 + this.repeat.length <= var3 && var2 + var5 + this.repeat.length <= this.maxCount; var5 += this.repeat.length) {
         var4++;
      }

      ArrayList var7 = new ArrayList();
      var7.addAll(Arrays.asList(this.first));

      for (int var6 = 0; var6 < var4; var6++) {
         var7.addAll(Arrays.asList(this.repeat));
      }

      var7.addAll(Arrays.asList(this.last));
      return var7.toArray(new ExpressionType[var7.size()]);
   }

   public ParametersVariable(ExpressionType[] var1, ExpressionType[] var2, ExpressionType[] var3, int var4) {
      this.first = normalize(var1);
      this.repeat = normalize(var2);
      this.last = normalize(var3);
      this.maxCount = var4;
   }

   public static ExpressionType[] normalize(ExpressionType[] var0) {
      return var0 == null ? EMPTY : var0;
   }

   public ParametersVariable() {
      this((ExpressionType[])null, (ExpressionType[])null, (ExpressionType[])null);
   }

   public ParametersVariable first(ExpressionType... var1) {
      return new ParametersVariable(var1, this.repeat, this.last);
   }
}
