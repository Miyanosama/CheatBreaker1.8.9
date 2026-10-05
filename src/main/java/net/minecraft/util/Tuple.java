package net.minecraft.util;

public class Tuple<A, B> {
   public B b;
   public A a;

   public A getFirst() {
      return this.a;
   }

   public Tuple(A var1, B var2) {
      this.a = (A)var1;
      this.b = (B)var2;
   }

   public B getSecond() {
      return this.b;
   }
}
