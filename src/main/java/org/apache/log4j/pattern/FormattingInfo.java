package org.apache.log4j.pattern;

public class FormattingInfo {
   public static char[] SPACES = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
   public int maxLength;
   public static FormattingInfo DEFAULT = new FormattingInfo(false, 0, Integer.MAX_VALUE);
   public int minLength;
   public boolean leftAlign;

   public static FormattingInfo getDefault() {
      return DEFAULT;
   }

   public int getMaxLength() {
      return this.maxLength;
   }

   public void format(int var1, StringBuffer var2) {
      int var3 = var2.length() - var1;
      if (var3 > this.maxLength) {
         var2.delete(var1, var2.length() - this.maxLength);
      } else if (var3 < this.minLength) {
         if (this.leftAlign) {
            int var4 = var2.length();
            var2.setLength(var1 + this.minLength);

            for (int var5 = var4; var5 < var2.length(); var5++) {
               var2.setCharAt(var5, ' ');
            }
         } else {
            int var6;
            for (var6 = this.minLength - var3; var6 > 8; var6 -= 8) {
               var2.insert(var1, SPACES);
            }

            var2.insert(var1, SPACES, 0, var6);
         }
      }
   }

   public int getMinLength() {
      return this.minLength;
   }

   public boolean isLeftAligned() {
      return this.leftAlign;
   }

   public FormattingInfo(boolean var1, int var2, int var3) {
      this.leftAlign = var1;
      this.minLength = var2;
      this.maxLength = var3;
   }
}
