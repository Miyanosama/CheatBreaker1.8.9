package net.optifine.expr;

public enum TokenType {
      IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
      NUMBER("0123456789", "0123456789."),
      OPERATOR("+-*/%!&|<>=", "&|="),
      COMMA(","),
      BRACKET_OPEN("("),
      BRACKET_CLOSE(")");
   public static TokenType[] $VALUES = new TokenType[]{
      TokenType.IDENTIFIER, TokenType.NUMBER, TokenType.OPERATOR, COMMA, TokenType.BRACKET_OPEN, TokenType.BRACKET_CLOSE
   };
   public static TokenType[] VALUES = values();
   public String charsNext;
   public String charsFirst;

   public String getCharsNext() {
      return this.charsNext;
   }

   TokenType(String var3, String var4) {
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

   TokenType(String var3) {
      this(var3, "");
   }

   public String getCharsFirst() {
      return this.charsFirst;
   }
}
