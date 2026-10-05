package net.optifine.expr;

import junit.swingui.TestRunner$11;
import net.minecraft.client.Minecraft$16;

public enum TokenType {
   COMMA(","),
   BRACKET_CLOSE(")"),
   IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
   BRACKET_OPEN("("),
   NUMBER("0123456789", "0123456789."),
   OPERATOR("+-*/%!&|<>=", "&|=");
   public static TokenType[] VALUES = values();
   // $VF: synthetic field
   public static TokenType[] $VALUES = new TokenType[]{
      TokenType.IDENTIFIER, TokenType.NUMBER, TokenType.OPERATOR, COMMA, TokenType.BRACKET_OPEN, TokenType.BRACKET_CLOSE
   };
   public Minecraft$16 field_0008;
   public String charsNext;
   public TestRunner$11 field_0010;
   public String charsFirst;

   public String getCharsNext() {
      return this.charsNext;
   }

   public TokenType(String var3, String var4) {
      this.charsFirst = var3;
      this.charsNext = var4;
   }

   public boolean hasCharNext(char var1) {
      return this.charsNext.indexOf(var1) >= 0;
   }

   public static TokenType getTypeByFirstChar(char var0) {
      for (int var1 = 0; var1 < VALUES.length; var1++) {
         TokenType var2 = VALUES[var1];
         if (var2.getCharsFirst().indexOf(var0) >= 0) {
            return var2;
         }
      }

      return null;
   }

   public TokenType(String var3) {
      this(var3, "");
   }

   public String getCharsFirst() {
      return this.charsFirst;
   }
}
