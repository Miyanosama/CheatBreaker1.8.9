package org.davidmoten.text.utils;

import org.davidmoten.text.utils.CharSequenceConcatRightTrim;

public class CharSequenceConcatRightTrim$1 implements CharSequence {
   public int recoveredField3480;
   public CharSequenceConcatRightTrim recoveredField3481;
   public int recoveredField3482;

   @Override
   public char charAt(int var1) {
      return this.recoveredField3481.charAt(this.recoveredField3482 + var1);
   }

   public CharSequenceConcatRightTrim$1(CharSequenceConcatRightTrim var1, int var2, int var3) {
      this.recoveredField3481 = var1;
      this.recoveredField3480 = var2;
      this.recoveredField3482 = var3;
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      StringBuilder var3 = new StringBuilder(var2 - var1);

      for (int var4 = var1; var4 < var2; var4++) {
         var3.append(this.charAt(var4));
      }

      return var3;
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
   public int length() {
      return this.recoveredField3480 - this.recoveredField3482;
   }
}
