package net.minecraft.util;

import net.minecraft.client.particle.Barrier$Factory;
import recovered.unidentified.UnidentifiedClass3786;

public class Tuple<A, B> {
   public UnidentifiedClass3786 field_0003;
   public Barrier$Factory field_0001;
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
