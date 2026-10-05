package net.minecraft.util;

import net.minecraft.util.Cartesian;

import java.util.Collections;
import java.util.Iterator;
import net.minecraft.util.Cartesian_Product_ProductIterator;

public class Cartesian$Product<T> implements Iterable<T[]> {
   public Class<T> recoveredField3932;
   public Iterable<? extends T>[] recoveredField3933;

   public Cartesian$Product(Class<T> var1, Iterable<? extends T>[] var2) {
      this.recoveredField3932 = var1;
      this.recoveredField3933 = var2;
   }

   @Override
   public Iterator<T[]> iterator() {
      return (Iterator<T[]>)(Iterator<?>)(this.recoveredField3933.length <= 0
         ? Collections.singletonList(Cartesian.method_08277(this.recoveredField3932, 0)).iterator()
         : new Cartesian_Product_ProductIterator<>(this.recoveredField3932, this.recoveredField3933));
   }
}
