package net.optifine.expr;

public class Token {
   public TokenType type;
   public String text;

   public String getText() {
      return this.text;
   }

   public TokenType getType() {
      return this.type;
   }

   public Token(TokenType var1, String var2) {
      this.type = var1;
      this.text = var2;
   }

   @Override
   public String toString() {
      return this.text;
   }
}
