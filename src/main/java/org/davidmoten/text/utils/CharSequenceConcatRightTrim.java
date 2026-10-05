package org.davidmoten.text.utils;

public class CharSequenceConcatRightTrim implements CharSequence {
   public CharSequence recoveredField2766;
   public CharSequence recoveredField2767;

   @Override
   public char charAt(int var1) {
      return var1 < this.recoveredField2767.length()
         ? this.recoveredField2767.charAt(var1)
         : this.recoveredField2766.charAt(var1 - this.recoveredField2767.length());
   }

   public CharSequenceConcatRightTrim(CharSequence var1, CharSequence var2) {
      this.recoveredField2767 = var1;
      this.recoveredField2766 = var2;
   }

   @Override
   public int length() {
      int var1 = this.recoveredField2767.length() + this.recoveredField2766.length() - 1;

      while (var1 > 0 && Character.isWhitespace(this.charAt(var1))) {
         var1--;
      }

      return var1 + 1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = this.length();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.append(this.charAt(var3));
      }

      return var1.toString();
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return new CharSequenceConcatRightTrim$1(this, var2, var1);
   }
}
