package org.davidmoten.text.utils;

import java.util.Arrays;

public class StringBuilder2 implements CharSequence {
   public char[] recoveredField1600;
   public int recoveredField1601;

   public StringBuilder2 method_27548() {
      int var1 = this.length();

      while (var1 > 0 && Character.isWhitespace(this.charAt(var1 - 1))) {
         var1--;
      }

      this.recoveredField1601 = var1;
      return this;
   }

   public int method_27550(int var1) {
      int var2 = this.recoveredField1600.length * 2;
      if (var2 < this.recoveredField1601 + var1) {
         var2 = this.recoveredField1601 + var1;
      }

      return var2;
   }

   @Override
   public String toString() {
      return new String(this.recoveredField1600, 0, this.recoveredField1601);
   }

   public String method_27551(int var1, int var2) {
      return new String(this.recoveredField1600, var1, var2 - var1);
   }

   public void method_27556(int var1, int var2) {
      System.arraycopy(this.recoveredField1600, var2, this.recoveredField1600, var1, this.recoveredField1601 - var2);
      this.recoveredField1601 -= var2 - var1;
   }

   @Override
   public int length() {
      return this.recoveredField1601;
   }

   public StringBuilder2(char[] var1, int var2) {
      this.recoveredField1600 = var1;
      this.recoveredField1601 = var2;
   }

   public char[] method_27554() {
      return this.recoveredField1600;
   }

   public StringBuilder2() {
      this(new char[16], 0);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      char[] var3 = new char[var2 - var1];
      System.arraycopy(this.recoveredField1600, var1, var3, 0, var2 - var1);
      return new StringBuilder2(var3, var3.length);
   }

   public void method_27555(int var1) {
      if (this.recoveredField1601 + var1 > this.recoveredField1600.length) {
         this.recoveredField1600 = Arrays.copyOf(this.recoveredField1600, this.method_27550(var1));
      }
   }

   public void method_27552(StringBuilder2 var1) {
      int var2 = var1.length();
      this.method_27555(var2);
      System.arraycopy(var1.recoveredField1600, 0, this.recoveredField1600, this.recoveredField1601, var2);
      this.recoveredField1601 += var2;
   }

   @Override
   public char charAt(int var1) {
      return this.recoveredField1600[var1];
   }

   public void method_27549(char var1) {
      this.method_27555(1);
      this.recoveredField1600[this.recoveredField1601] = var1;
      this.recoveredField1601++;
   }

   public void method_27546(int var1) {
      this.recoveredField1601 = var1;
   }

   public StringBuilder2(String var1) {
      this(var1.toCharArray(), var1.length());
   }
}
