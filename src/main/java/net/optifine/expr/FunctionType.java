package net.optifine.expr;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.util.MathUtils;

public enum FunctionType {
      PLUS(10, ExpressionType.FLOAT, "+", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      MINUS(10, ExpressionType.FLOAT, "-", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      MUL(11, ExpressionType.FLOAT, "*", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      DIV(11, ExpressionType.FLOAT, "/", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      MOD(11, ExpressionType.FLOAT, "%", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      NEG(12, ExpressionType.FLOAT, "neg", new ExpressionType[]{ExpressionType.FLOAT}),
      PI(ExpressionType.FLOAT, "pi", new ExpressionType[0]),
      SIN(ExpressionType.FLOAT, "sin", new ExpressionType[]{ExpressionType.FLOAT}),
      COS(ExpressionType.FLOAT, "cos", new ExpressionType[]{ExpressionType.FLOAT}),
      ASIN(ExpressionType.FLOAT, "asin", new ExpressionType[]{ExpressionType.FLOAT}),
      ACOS(ExpressionType.FLOAT, "acos", new ExpressionType[]{ExpressionType.FLOAT}),
      TAN(ExpressionType.FLOAT, "tan", new ExpressionType[]{ExpressionType.FLOAT}),
      ATAN(ExpressionType.FLOAT, "atan", new ExpressionType[]{ExpressionType.FLOAT}),
      ATAN2(ExpressionType.FLOAT, "atan2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      TORAD(ExpressionType.FLOAT, "torad", new ExpressionType[]{ExpressionType.FLOAT}),
      TODEG(ExpressionType.FLOAT, "todeg", new ExpressionType[]{ExpressionType.FLOAT}),
      MIN(ExpressionType.FLOAT, "min", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
      MAX(ExpressionType.FLOAT, "max", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
      CLAMP(ExpressionType.FLOAT, "clamp", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
      ABS(ExpressionType.FLOAT, "abs", new ExpressionType[]{ExpressionType.FLOAT}),
      FLOOR(ExpressionType.FLOAT, "floor", new ExpressionType[]{ExpressionType.FLOAT}),
      CEIL(ExpressionType.FLOAT, "ceil", new ExpressionType[]{ExpressionType.FLOAT}),
      EXP(ExpressionType.FLOAT, "exp", new ExpressionType[]{ExpressionType.FLOAT}),
      FRAC(ExpressionType.FLOAT, "frac", new ExpressionType[]{ExpressionType.FLOAT}),
      LOG(ExpressionType.FLOAT, "log", new ExpressionType[]{ExpressionType.FLOAT}),
      POW(ExpressionType.FLOAT, "pow", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      RANDOM(ExpressionType.FLOAT, "random", new ExpressionType[0]),
      ROUND(ExpressionType.FLOAT, "round", new ExpressionType[]{ExpressionType.FLOAT}),
      SIGNUM(ExpressionType.FLOAT, "signum", new ExpressionType[]{ExpressionType.FLOAT}),
      SQRT(ExpressionType.FLOAT, "sqrt", new ExpressionType[]{ExpressionType.FLOAT}),
      FMOD(ExpressionType.FLOAT, "fmod", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      TIME(ExpressionType.FLOAT, "time", new ExpressionType[0]),
      IF(
      ExpressionType.FLOAT,
      "if",
      new ParametersVariable().first(ExpressionType.BOOL, ExpressionType.FLOAT).repeat(ExpressionType.BOOL, ExpressionType.FLOAT).last(ExpressionType.FLOAT)
   ),
      NOT(12, ExpressionType.BOOL, "!", new ExpressionType[]{ExpressionType.BOOL}),
      AND(3, ExpressionType.BOOL, "&&", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
      OR(2, ExpressionType.BOOL, "||", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
      GREATER(8, ExpressionType.BOOL, ">", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      GREATER_OR_EQUAL(8, ExpressionType.BOOL, ">=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      SMALLER(8, ExpressionType.BOOL, "<", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      SMALLER_OR_EQUAL(8, ExpressionType.BOOL, "<=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      EQUAL(7, ExpressionType.BOOL, "==", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      NOT_EQUAL(7, ExpressionType.BOOL, "!=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      BETWEEN(7, ExpressionType.BOOL, "between", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
      EQUALS(7, ExpressionType.BOOL, "equals", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
      IN(ExpressionType.BOOL, "in", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).last(ExpressionType.FLOAT)),
      SMOOTH(ExpressionType.FLOAT, "smooth", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).maxCount(4)),
      TRUE(ExpressionType.BOOL, "true", new ExpressionType[0]),
      FALSE(ExpressionType.BOOL, "false", new ExpressionType[0]),
      VEC2(ExpressionType.FLOAT_ARRAY, "vec2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
      VEC3(ExpressionType.FLOAT_ARRAY, "vec3", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
      VEC4(ExpressionType.FLOAT_ARRAY, "vec4", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT});
   public int precedence;
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
   public static FunctionType[] VALUES = values();
   public static Map<Integer, Float> mapSmooth = new HashMap<>();
   public IParameters parameters;
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
      switch (this) {
         case TRUE:
            return true;
         case FALSE:
            return false;
         case NOT:
            return !evalBool(var1, 0);
         case AND:
            return evalBool(var1, 0) && evalBool(var1, 1);
         case OR:
            return evalBool(var1, 0) || evalBool(var1, 1);
         case GREATER:
            return evalFloat(var1, 0) > evalFloat(var1, 1);
         case GREATER_OR_EQUAL:
            return evalFloat(var1, 0) >= evalFloat(var1, 1);
         case SMALLER:
            return evalFloat(var1, 0) < evalFloat(var1, 1);
         case SMALLER_OR_EQUAL:
            return evalFloat(var1, 0) <= evalFloat(var1, 1);
         case EQUAL:
            return evalFloat(var1, 0) == evalFloat(var1, 1);
         case NOT_EQUAL:
            return evalFloat(var1, 0) != evalFloat(var1, 1);
         case BETWEEN:
            float var2 = evalFloat(var1, 0);
            return var2 >= evalFloat(var1, 1) && var2 <= evalFloat(var1, 2);
         case EQUALS:
            float var3 = evalFloat(var1, 0) - evalFloat(var1, 1);
            float var4 = evalFloat(var1, 2);
            return Math.abs(var3) <= var4;
         case IN:
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

   FunctionType(ExpressionType var3, String var4, IParameters var5) {
      this(0, var3, var4, var5);
   }

   public static float evalFloat(IExpression[] var0, int var1) {
      IExpressionFloat var2 = (IExpressionFloat)var0[var1];
      return var2.eval();
   }

   FunctionType(int var3, ExpressionType var4, String var5, ExpressionType[] var6) {
      this(var3, var4, var5, new Parameters(var6));
   }

   public float evalFloat(IExpression[] var1) {
      switch (this) {
         case PLUS:
            return evalFloat(var1, 0) + evalFloat(var1, 1);
         case MINUS:
            return evalFloat(var1, 0) - evalFloat(var1, 1);
         case MUL:
            return evalFloat(var1, 0) * evalFloat(var1, 1);
         case DIV:
            return evalFloat(var1, 0) / evalFloat(var1, 1);
         case MOD:
            float var2 = evalFloat(var1, 0);
            float var3 = evalFloat(var1, 1);
            return var2 - var3 * (int)(var2 / var3);
         case NEG:
            return -evalFloat(var1, 0);
         case PI:
            return MathHelper.PI;
         case SIN:
            return MathHelper.sin(evalFloat(var1, 0));
         case COS:
            return MathHelper.cos(evalFloat(var1, 0));
         case ASIN:
            return MathUtils.asin(evalFloat(var1, 0));
         case ACOS:
            return MathUtils.acos(evalFloat(var1, 0));
         case TAN:
            return (float)Math.tan(evalFloat(var1, 0));
         case ATAN:
            return (float)Math.atan(evalFloat(var1, 0));
         case ATAN2:
            return (float)MathHelper.atan2(evalFloat(var1, 0), evalFloat(var1, 1));
         case TORAD:
            return MathUtils.toRad(evalFloat(var1, 0));
         case TODEG:
            return MathUtils.toDeg(evalFloat(var1, 0));
         case MIN:
            return this.getMin(var1);
         case MAX:
            return this.getMax(var1);
         case CLAMP:
            return MathHelper.clamp_float(evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2));
         case ABS:
            return MathHelper.abs(evalFloat(var1, 0));
         case EXP:
            return (float)Math.exp(evalFloat(var1, 0));
         case FLOOR:
            return MathHelper.floor_float(evalFloat(var1, 0));
         case CEIL:
            return MathHelper.ceiling_float_int(evalFloat(var1, 0));
         case FRAC:
            return (float)MathHelper.func_181162_h(evalFloat(var1, 0));
         case LOG:
            return (float)Math.log(evalFloat(var1, 0));
         case POW:
            return (float)Math.pow(evalFloat(var1, 0), evalFloat(var1, 1));
         case RANDOM:
            return (float)Math.random();
         case ROUND:
            return Math.round(evalFloat(var1, 0));
         case SIGNUM:
            return Math.signum(evalFloat(var1, 0));
         case SQRT:
            return MathHelper.sqrt_float(evalFloat(var1, 0));
         case FMOD:
            float var4 = evalFloat(var1, 0);
            float var5 = evalFloat(var1, 1);
            return var4 - var5 * MathHelper.floor_float(var4 / var5);
         case TIME:
            Minecraft var6 = Minecraft.getMinecraft();
            WorldClient var7 = var6.theWorld;
            if (var7 == null) {
               return 0.0F;
            }

            return (float)(var7.K() % 24000L) + Config.renderPartialTicks;
         case IF:
            int var8 = (var1.length - 1) / 2;

            for (int var14 = 0; var14 < var8; var14++) {
               int var15 = var14 * 2;
               if (evalBool(var1, var15)) {
                  return evalFloat(var1, var15 + 1);
               }
            }

            return evalFloat(var1, var8 * 2);
         case SMOOTH:
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

   FunctionType(ExpressionType var3, String var4, ExpressionType[] var5) {
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
      switch (this) {
         case VEC2:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1)};
         case VEC3:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2)};
         case VEC4:
            return new float[]{evalFloat(var1, 0), evalFloat(var1, 1), evalFloat(var1, 2), evalFloat(var1, 3)};
         default:
            Config.warn("Unknown function type: " + this);
            return null;
      }
   }

   FunctionType(int var3, ExpressionType var4, String var5, IParameters var6) {
      this.precedence = var3;
      this.expressionType = var4;
      this.name = var5;
      this.parameters = var6;
   }
}
