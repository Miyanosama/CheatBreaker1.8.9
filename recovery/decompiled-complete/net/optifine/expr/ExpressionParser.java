package net.optifine.expr;

import io.netty.handler.codec.serialization.SoftReferenceMap;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.command.PlayerSelector$7;
import net.minecraft.src.Config;

public class ExpressionParser {
   public PlayerSelector$7 field_0001;
   public IExpressionResolver expressionResolver;
   public SoftReferenceMap field_0000;

   public IExpression makeVariable(Token var1) {
      if (this.expressionResolver == null) {
         throw new ParseException("Model variable not found: " + var1);
      } else {
         IExpression var2 = this.expressionResolver.getExpression(var1.getText());
         if (var2 == null) {
            throw new ParseException("Model variable not found: " + var1);
         } else {
            return var2;
         }
      }
   }

   public IExpression parse(String var1) {
      try {
         Token[] var2 = TokenParser.parse(var1);
         if (var2 == null) {
            return null;
         } else {
            ArrayDeque var3 = new ArrayDeque<>(Arrays.asList(var2));
            return this.parseInfix(var3);
         }
      } catch (IOException var4) {
         throw new ParseException(var4.getMessage(), var4);
      }
   }

   public IExpressionBool parseBool(String var1) {
      IExpression var2 = this.parse(var1);
      if (!(var2 instanceof IExpressionBool)) {
         throw new ParseException("Not a boolean expression: " + var2.getExpressionType());
      } else {
         return (IExpressionBool)var2;
      }
   }

   public IExpression parseExpression(Deque<Token> var1) {
      Token var2 = (Token)var1.poll();
      checkNull(var2, "Missing expression");
      switch (ExpressionParser$1.$SwitchMap$net$optifine$expr$TokenType[var2.getType().ordinal()]) {
         case 1:
            return makeConstantFloat(var2);
         case 2:
            FunctionType var3 = this.getFunctionType(var2, var1);
            if (var3 != null) {
               return this.makeFunction(var3, var1);
            }

            return this.makeVariable(var2);
         case 3:
            return this.makeBracketed(var2, var1);
         case 4:
            FunctionType var4 = FunctionType.parse(var2.getText());
            checkNull(var4, "Invalid operator: " + var2);
            if (var4 == FunctionType.PLUS) {
               return this.parseExpression(var1);
            } else if (var4 == FunctionType.MINUS) {
               IExpression var6 = this.parseExpression(var1);
               return makeFunction(FunctionType.NEG, new IExpression[]{var6});
            } else if (var4 == FunctionType.NOT) {
               IExpression var5 = this.parseExpression(var1);
               return makeFunction(FunctionType.NOT, new IExpression[]{var5});
            }
         default:
            throw new ParseException("Invalid expression: " + var2);
      }
   }

   public void mergeOperators(List<IExpression> var1, List<FunctionType> var2, int var3) {
      for (int var4 = 0; var4 < var2.size(); var4++) {
         FunctionType var5 = (FunctionType)var2.get(var4);
         if (var5.getPrecedence() == var3) {
            var2.remove(var4);
            IExpression var6 = (IExpression)var1.remove(var4);
            IExpression var7 = (IExpression)var1.remove(var4);
            IExpression var8 = makeFunction(var5, new IExpression[]{var6, var7});
            var1.add(var4, var8);
            var4--;
         }
      }
   }

   public IExpressionFloat parseFloat(String var1) {
      IExpression var2 = this.parse(var1);
      if (!(var2 instanceof IExpressionFloat)) {
         throw new ParseException("Not a float expression: " + var2.getExpressionType());
      } else {
         return (IExpressionFloat)var2;
      }
   }

   public IExpression makeInfixFunc(List<IExpression> var1, List<FunctionType> var2) {
      if (var1.size() != var2.size() + 1) {
         throw new ParseException("Invalid infix expression, expressions: " + var1.size() + ", operators: " + var2.size());
      } else if (var1.size() == 1) {
         return (IExpression)var1.get(0);
      } else {
         int var3 = Integer.MAX_VALUE;
         int var4 = Integer.MIN_VALUE;

         for (FunctionType var6 : var2) {
            var3 = Math.min(var6.getPrecedence(), var3);
            var4 = Math.max(var6.getPrecedence(), var4);
         }

         if (var4 >= var3 && var4 - var3 <= 10) {
            for (int var7 = var4; var7 >= var3; var7--) {
               this.mergeOperators(var1, var2, var7);
            }

            if (var1.size() == 1 && var2.size() == 0) {
               return (IExpression)var1.get(0);
            } else {
               throw new ParseException("Error merging operators, expressions: " + var1.size() + ", operators: " + var2.size());
            }
         } else {
            throw new ParseException("Invalid infix precedence, min: " + var3 + ", max: " + var4);
         }
      }
   }

   public IExpression makeBracketed(Token var1, Deque<Token> var2) {
      Deque var3 = getGroup(var2, TokenType.BRACKET_CLOSE, true);
      return this.parseInfix(var3);
   }

   public FunctionType getFunctionType(Token var1, Deque<Token> var2) {
      Token var3 = (Token)var2.peek();
      if (var3 != null && var3.getType() == TokenType.BRACKET_OPEN) {
         FunctionType var5 = FunctionType.parse(var1.getText());
         checkNull(var5, "Unknown function: " + var1);
         return var5;
      } else {
         FunctionType var4 = FunctionType.parse(var1.getText());
         if (var4 == null) {
            return null;
         } else if (var4.getParameterCount(new IExpression[0]) > 0) {
            throw new ParseException("Missing arguments: " + var4);
         } else {
            return var4;
         }
      }
   }

   public static Deque<Token> getGroup(Deque<Token> var0, TokenType var1, boolean var2) {
      ArrayDeque var3 = new ArrayDeque();
      int var4 = 0;
      Iterator var5 = var0.iterator();

      while (var5.hasNext()) {
         Token var6 = (Token)var5.next();
         var5.remove();
         if (var4 == 0 && var6.getType() == var1) {
            return var3;
         }

         var3.add(var6);
         if (var6.getType() == TokenType.BRACKET_OPEN) {
            var4++;
         }

         if (var6.getType() == TokenType.BRACKET_CLOSE) {
            var4--;
         }
      }

      if (var2) {
         throw new ParseException("Missing end token: " + var1);
      } else {
         return var3;
      }
   }

   public IExpression parseInfix(Deque<Token> var1) {
      if (var1.isEmpty()) {
         return null;
      } else {
         LinkedList var2 = new LinkedList();
         LinkedList var3 = new LinkedList();
         IExpression var4 = this.parseExpression(var1);
         checkNull(var4, "Missing expression");
         var2.add(var4);

         while (true) {
            Token var5 = (Token)var1.poll();
            if (var5 == null) {
               return this.makeInfix(var2, var3);
            }

            if (var5.getType() != TokenType.OPERATOR) {
               throw new ParseException("Invalid operator: " + var5);
            }

            IExpression var6 = this.parseExpression(var1);
            checkNull(var6, "Missing expression");
            var3.add(var5);
            var2.add(var6);
         }
      }
   }

   public IExpression makeFunction(FunctionType var1, Deque<Token> var2) {
      if (var1.getParameterCount(new IExpression[0]) == 0) {
         Token var3 = (Token)var2.peek();
         if (var3 == null || var3.getType() != TokenType.BRACKET_OPEN) {
            return makeFunction(var1, new IExpression[0]);
         }
      }

      Token var6 = (Token)var2.poll();
      Deque var4 = getGroup(var2, TokenType.BRACKET_CLOSE, true);
      IExpression[] var5 = this.parseExpressions(var4);
      return makeFunction(var1, var5);
   }

   public static IExpression makeFunction(FunctionType var0, IExpression[] var1) {
      ExpressionType[] var2 = var0.getParameterTypes(var1);
      if (var1.length != var2.length) {
         throw new ParseException(
            "Invalid number of arguments, function: \"" + var0.getName() + "\", count arguments: " + var1.length + ", should be: " + var2.length
         );
      } else {
         for (int var3 = 0; var3 < var1.length; var3++) {
            IExpression var4 = var1[var3];
            ExpressionType var5 = var4.getExpressionType();
            ExpressionType var6 = var2[var3];
            if (var5 != var6) {
               throw new ParseException(
                  "Invalid argument type, function: \"" + var0.getName() + "\", index: " + var3 + ", type: " + var5 + ", should be: " + var6
               );
            }
         }

         if (var0.getExpressionType() == ExpressionType.FLOAT) {
            return new FunctionFloat(var0, var1);
         } else if (var0.getExpressionType() == ExpressionType.BOOL) {
            return new FunctionBool(var0, var1);
         } else if (var0.getExpressionType() == ExpressionType.FLOAT_ARRAY) {
            return new FunctionFloatArray(var0, var1);
         } else {
            throw new ParseException("Unknown function type: " + var0.getExpressionType() + ", function: " + var0.getName());
         }
      }
   }

   public IExpression[] parseExpressions(Deque<Token> var1) {
      ArrayList var2 = new ArrayList();

      while (true) {
         Deque var3 = getGroup(var1, TokenType.COMMA, false);
         IExpression var4 = this.parseInfix(var3);
         if (var4 == null) {
            return var2.toArray(new IExpression[var2.size()]);
         }

         var2.add(var4);
      }
   }

   public ExpressionParser(IExpressionResolver var1) {
      this.expressionResolver = var1;
   }

   public IExpression makeInfix(List<IExpression> var1, List<Token> var2) {
      LinkedList var3 = new LinkedList();

      for (Token var5 : var2) {
         FunctionType var6 = FunctionType.parse(var5.getText());
         checkNull(var6, "Invalid operator: " + var5);
         var3.add(var6);
      }

      return this.makeInfixFunc(var1, var3);
   }

   public static void checkNull(Object var0, String var1) {
      if (var0 == null) {
         throw new ParseException(var1);
      }
   }

   public static IExpression makeConstantFloat(Token var0) {
      float var1 = Config.parseFloat(var0.getText(), Float.NaN);
      if (var1 == Float.NaN) {
         throw new ParseException("Invalid float value: " + var0);
      } else {
         return new ConstantFloat(var1);
      }
   }
}
