package net.optifine.expr;

import net.minecraft.stats.StatBase$2;

// $VF: synthetic class
public class ExpressionParser$1 {
   public StatBase$2 field_0000;

   static {
      try {
         $SwitchMap$net$optifine$expr$TokenType[TokenType.NUMBER.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$optifine$expr$TokenType[TokenType.IDENTIFIER.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$optifine$expr$TokenType[TokenType.BRACKET_OPEN.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$optifine$expr$TokenType[TokenType.OPERATOR.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
