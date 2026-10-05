package net.optifine.expr;

import com.cheatbreaker.client.module.AbstractModule$1;
import io.netty.util.internal.AppendableCharSequence;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase$1;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.util.MathUtils;

public enum FunctionType {
   DIV(11, ExpressionType.FLOAT, "/", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   POW(ExpressionType.FLOAT, "pow", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   BETWEEN(7, ExpressionType.BOOL, "between", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   IN(ExpressionType.BOOL, "in", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).last(ExpressionType.FLOAT)),
   MINUS(10, ExpressionType.FLOAT, "-", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   FLOOR(ExpressionType.FLOAT, "floor", new ExpressionType[]{ExpressionType.FLOAT}),
   ATAN2(ExpressionType.FLOAT, "atan2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   PI(ExpressionType.FLOAT, "pi", new ExpressionType[0]),
   MUL(11, ExpressionType.FLOAT, "*", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   ROUND(ExpressionType.FLOAT, "round", new ExpressionType[]{ExpressionType.FLOAT}),
   SIGNUM(ExpressionType.FLOAT, "signum", new ExpressionType[]{ExpressionType.FLOAT}),
   VEC3(ExpressionType.FLOAT_ARRAY, "vec3", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   LOG(ExpressionType.FLOAT, "log", new ExpressionType[]{ExpressionType.FLOAT}),
   TRUE(ExpressionType.BOOL, "true", new ExpressionType[0]),
   SMOOTH(ExpressionType.FLOAT, "smooth", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).maxCount(4)),
   NOT(12, ExpressionType.BOOL, "!", new ExpressionType[]{ExpressionType.BOOL}),
   MAX(ExpressionType.FLOAT, "max", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
   TIME(ExpressionType.FLOAT, "time", new ExpressionType[0]),
   SIN(ExpressionType.FLOAT, "sin", new ExpressionType[]{ExpressionType.FLOAT}),
   ABS(ExpressionType.FLOAT, "abs", new ExpressionType[]{ExpressionType.FLOAT}),
   EQUAL(7, ExpressionType.BOOL, "==", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   GREATER_OR_EQUAL(8, ExpressionType.BOOL, ">=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   MOD(11, ExpressionType.FLOAT, "%", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   IF(
      ExpressionType.FLOAT,
      "if",
      new ParametersVariable().first(ExpressionType.BOOL, ExpressionType.FLOAT).repeat(ExpressionType.BOOL, ExpressionType.FLOAT).last(ExpressionType.FLOAT)
   ),
   EXP(ExpressionType.FLOAT, "exp", new ExpressionType[]{ExpressionType.FLOAT}),
   RANDOM(ExpressionType.FLOAT, "random", new ExpressionType[0]),
   TAN(ExpressionType.FLOAT, "tan", new ExpressionType[]{ExpressionType.FLOAT}),
   SQRT(ExpressionType.FLOAT, "sqrt", new ExpressionType[]{ExpressionType.FLOAT}),
   COS(ExpressionType.FLOAT, "cos", new ExpressionType[]{ExpressionType.FLOAT}),
   SMALLER_OR_EQUAL(8, ExpressionType.BOOL, "<=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   VEC2(ExpressionType.FLOAT_ARRAY, "vec2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   TORAD(ExpressionType.FLOAT, "torad", new ExpressionType[]{ExpressionType.FLOAT}),
   PLUS(10, ExpressionType.FLOAT, "+", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   FRAC(ExpressionType.FLOAT, "frac", new ExpressionType[]{ExpressionType.FLOAT}),
   AND(3, ExpressionType.BOOL, "&&", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
   GREATER(8, ExpressionType.BOOL, ">", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   CLAMP(ExpressionType.FLOAT, "clamp", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   ACOS(ExpressionType.FLOAT, "acos", new ExpressionType[]{ExpressionType.FLOAT}),
   NEG(12, ExpressionType.FLOAT, "neg", new ExpressionType[]{ExpressionType.FLOAT}),
   FALSE(ExpressionType.BOOL, "false", new ExpressionType[0]),
   MIN(ExpressionType.FLOAT, "min", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
   VEC4(ExpressionType.FLOAT_ARRAY, "vec4", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   TODEG(ExpressionType.FLOAT, "todeg", new ExpressionType[]{ExpressionType.FLOAT}),
   OR(2, ExpressionType.BOOL, "||", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
   EQUALS(7, ExpressionType.BOOL, "equals", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   ASIN(ExpressionType.FLOAT, "asin", new ExpressionType[]{ExpressionType.FLOAT}),
   ATAN(ExpressionType.FLOAT, "atan", new ExpressionType[]{ExpressionType.FLOAT}),
   CEIL(ExpressionType.FLOAT, "ceil", new ExpressionType[]{ExpressionType.FLOAT}),
   SMALLER(8, ExpressionType.BOOL, "<", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   NOT_EQUAL(7, ExpressionType.BOOL, "!=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   FMOD(ExpressionType.FLOAT, "fmod", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT});
   public AbstractModule$1 field_0032;
   public BiomeGenBase$1 field_0055;
   public int precedence;
   // $VF: synthetic field
   public static FunctionType[] $VALUES = new FunctionType[]{
      FunctionType.PLUS,
      FunctionType.MINUS,
      FunctionType.MUL,
      DIV,
      FunctionType.MOD,
      FunctionType.NEG,
      FunctionType.PI,
      FunctionType.SIN,
      FunctionType.COS,
      FunctionType.ASIN,
      FunctionType.ACOS,
      FunctionType.TAN,
      FunctionType.ATAN,
      FunctionType.ATAN2,
      FunctionType.TORAD,
      FunctionType.TODEG,
      FunctionType.MIN,
      FunctionType.MAX,
      FunctionType.CLAMP,
      FunctionType.ABS,
      FunctionType.FLOOR,
      FunctionType.CEIL,
      FunctionType.EXP,
      FunctionType.FRAC,
      FunctionType.LOG,
      POW,
      FunctionType.RANDOM,
      FunctionType.ROUND,
      FunctionType.SIGNUM,
      FunctionType.SQRT,
      FunctionType.FMOD,
      FunctionType.TIME,
      FunctionType.IF,
      FunctionType.NOT,
      FunctionType.AND,
      FunctionType.OR,
      FunctionType.GREATER,
      FunctionType.GREATER_OR_EQUAL,
      FunctionType.SMALLER,
      FunctionType.SMALLER_OR_EQUAL,
      FunctionType.EQUAL,
      FunctionType.NOT_EQUAL,
      BETWEEN,
      FunctionType.EQUALS,
      IN,
      FunctionType.SMOOTH,
      FunctionType.TRUE,
      FunctionType.FALSE,
      FunctionType.VEC2,
      FunctionType.VEC3,
      FunctionType.VEC4
   };
   public ExpressionType expressionType;
   public static Map<Integer, Float> mapSmooth = new HashMap<>();
   public static FunctionType[] VALUES = values();
   public ItemFishingRod field_0041;
   public IParameters parameters;
   public AppendableCharSequence field_0013;
   public String name;

   public ExpressionType getExpressionType() {
      return this.expressionType;
   }

   public float getMin(IExpression[] var1) {
      if (var1.length == 2) {
         return Math.min(evalFloat(var1, 0), evalFloat(var1, 1));
      } else {
         float var2 = evalFloat(var1, 0);

         for (int var3 = 1; var3 < var1.length; var3++) {
            float var4 = evalFloat(var1, var3);
            if (var4 < var2) {
               var2 = var4;
            }
         }

         return var2;
      }
   }

   public IParameters getParameters() {
      return this.parameters;
   }

   public boolean evalBool(IExpression[] var1) {
      switch (FunctionType$1.$SwitchMap$net$optifine$expr$FunctionType[this.ordinal()]) {
         case 35:
            return true;
         case 36:
            return false;
         case 37:
            return !evalBool(var1, 0);
         case 38:
            return evalBool(var1, 0) && evalBool(var1, 1);
         case 39:
            return evalBool(var1, 0) || evalBool(var1, 1);
         case 40:
            return evalFloat(var1, 0) > evalFloat(var1, 1);
         case 41:
            return evalFloat(var1, 0) >= evalFloat(var1, 1);
         case 42:
            return evalFloat(var1, 0) < evalFloat(var1, 1);
         case 43:
            return evalFloat(var1, 0) <= evalFloat(var1, 1);
         case 44:
            return evalFloat(var1, 0) == evalFloat(var1, 1);
         case 45:
            return evalFloat(var1, 0) != evalFloat(var1, 1);
         case 46:
            float var2 = evalFloat(var1, 0);
            return var2 >= evalFloat(var1, 1) && var2 <= evalFloat(var1, 2);
         case 47:
            float var3 = evalFloat(var1, 0) - evalFloat(var1, 1);
            float var4 = evalFloat(var1, 2);
            return Math.abs(var3) <= var4;
         case 48:
            float var5 = evalFloat(var1, 0);

            for (int var6 = 1; var6 < var1.length; var6++) {
               float var7 = evalFloat(var1, var6);
               if (var5 == var7) {
                  return true;
               }
            }

            return false;
         default:
            Config.warn("Unknown function type: " + this);
            return false;
      }
   }

   public static FunctionType parse(String var0) {
      for (int var1 = 0; var1 < VALUES.length; var1++) {
         FunctionType var2 = VALUES[var1];
         if (var2.getName().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

   public FunctionType(ExpressionType var3, String var4, IParameters var5) {
      this(0, var3, var4, var5);
   }

   public static float evalFloat(IExpression[] var0, int var1) {
      IExpressionFloat var2 = (IExpressionFloat)var0[var1];
      return var2.eval();
   }

   public FunctionType(int var3, ExpressionType var4, String var5, ExpressionType[] var6) {
      this(var3, var4, var5, new Parameters(var6));
   }

   public float evalFloat(IExpression[] var1) {
      switch (FunctionType$1.$SwitchMap$net$optifine$expr$FunctionType[this.ordinal()]) {
         case 1:
            return evalFloat(var1, 0) + evalFloat(var1, 1);
         case 2:
            return evalFloat(var1, 0) - evalFloat(var1, 1);
         case 3:
            return evalFloat(var1, 0) * evalFloat(var1, 1);
         case 4:
            return evalFloat(var1, 0) / evalFloat(var1, 1);
         case 5:
            float var2 = evalFloat(var1, 0);
            float var3 = evalFloat(var1, 1);
            return var2 - var3 * (int)(var2 / var3);
         case 6:
            return -evalFloat(var1, 0);
         case 7:
            return MathHelper.PI;
         case 8:
            return MathHelper.sin(evalFloat(var1, 0));
         case 9:
            return MathHelper.cos(evalFloat(var1, 0));
         case 10:
            return MathUtils.asin(evalFloat(var1, 0));
         case 11:
            return MathUtils.acos(evalFloat(var1, 0));
         case 12:
            return (float)Math.tan(evalFloat(var1, 0));
         case 13:
            return (float)Math.atan(evalFloat(var1, 0));
         case 14:
            return (float)MathHelper.atan2(evalFloat(var1, 0), evalFloat(var1, 1));
         case 15:
            return MathUtils.toRad(evalFloat(var1, 0));
         case 16:
            return MathUtils.toDeg(evalFloat(var1, 0));
         case 17:
            return this.getMin(var1);
         case 18:
            return this.getMax(var1);
         case 19:
            return MathHelper.clamp_float(evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2));
         case 20:
            return MathHelper.abs(evalFloat(var1, 0));
         case 21:
            return (float)Math.exp(evalFloat(var1, 0));
         case 22:
            return MathHelper.floor_float(evalFloat(var1, 0));
         case 23:
            return MathHelper.ceiling_float_int(evalFloat(var1, 0));
         case 24:
            return (float)MathHelper.func_181162_h(evalFloat(var1, 0));
         case 25:
            return (float)Math.log(evalFloat(var1, 0));
         case 26:
            return (float)Math.pow(evalFloat(var1, 0), evalFloat(var1, 1));
         case 27:
            return (float)Math.random();
         case 28:
            return Math.round(evalFloat(var1, 0));
         case 29:
            return Math.signum(evalFloat(var1, 0));
         case 30:
            return MathHelper.sqrt_float(evalFloat(var1, 0));
         case 31:
            float var4 = evalFloat(var1, 0);
            float var5 = evalFloat(var1, 1);
            return var4 - var5 * MathHelper.floor_float(var4 / var5);
         case 32:
            Minecraft var6 = Minecraft.getMinecraft();
            WorldClient var7 = var6.theWorld;
            if (var7 == null) {
               return 0.0F;
            }

            return (float)(var7.K() % (-2966990590918296122L & 1360814073L)) + Config.renderPartialTicks;
         case 33:
            int var8 = (var1.length - 1) / 2;

            for (int var14 = 0; var14 < var8; var14++) {
               int var15 = var14 * 2;
               if (evalBool(var1, var15)) {
                  return evalFloat(var1, var15 + 1);
               }
            }

            return evalFloat(var1, var8 * 2);
         case 34:
            int var9 = (int)evalFloat(var1, 0);
            float var10 = evalFloat(var1, 1);
            float var11 = var1.length > 2 ? evalFloat(var1, 2) : 1.0F;
            float var12 = var1.length > 3 ? evalFloat(var1, 3) : var11;
            return Smoother.getSmoothValue(var9, var10, var11, var12);
         default:
            Config.warn("Unknown function type: " + this);
            return 0.0F;
      }
   }

   public FunctionType(ExpressionType var3, String var4, ExpressionType[] var5) {
      this(0, var3, var4, var5);
   }

   public static boolean evalBool(IExpression[] var0, int var1) {
      IExpressionBool var2 = (IExpressionBool)var0[var1];
      return var2.eval();
   }

   public int getParameterCount(IExpression[] var1) {
      return this.parameters.getParameterTypes(var1).length;
   }

   public float getMax(IExpression[] var1) {
      if (var1.length == 2) {
         return Math.max(evalFloat(var1, 0), evalFloat(var1, 1));
      } else {
         float var2 = evalFloat(var1, 0);

         for (int var3 = 1; var3 < var1.length; var3++) {
            float var4 = evalFloat(var1, var3);
            if (var4 > var2) {
               var2 = var4;
            }
         }

         return var2;
      }
   }

   public ExpressionType[] getParameterTypes(IExpression[] var1) {
      return this.parameters.getParameterTypes(var1);
   }

   public int getPrecedence() {
      return this.precedence;
   }

   public String getName() {
      return this.name;
   }

   public float[] evalFloatArray(IExpression[] var1) {
      switch (FunctionType$1.$SwitchMap$net$optifine$expr$FunctionType[this.ordinal()]) {
         case 49:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1)};
         case 50:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2)};
         case 51:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2), evalFloat(var1, 3)};
         default:
            Config.warn("Unknown function type: " + this);
            return null;
      }
   }

   public FunctionType(int var3, ExpressionType var4, String var5, IParameters var6) {
      this.precedence = var3;
      this.expressionType = var4;
      this.name = var5;
      this.parameters = var6;
   }
}
