package net.minecraft.util;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.util.Cartesian;

public class Cartesian_Product_ProductIterator<T> extends UnmodifiableIterator<T[]> {
   public int index = -2;
   public Iterator<? extends T>[] iterators;
   public Iterable<? extends T>[] iterables;
   public T[] results;

   public T[] next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         while (this.index < this.iterators.length) {
            this.results[this.index] = (T)this.iterators[this.index].next();
            this.index++;
         }

         return (T[])((Object[])this.results.clone());
      }
   }

   public void endOfData() {
      this.index = -1;
      Arrays.fill(this.iterators, null);
      Arrays.fill(this.results, null);
   }

   @Override
   public boolean hasNext() {
      if (this.index == -2) {
         this.index = 0;

         for (Iterator var4 : this.iterators) {
            if (!var4.hasNext()) {
               this.endOfData();
               break;
            }
         }

         return true;
      } else {
         if (this.index >= this.iterators.length) {
            for (this.index = this.iterators.length - 1; this.index >= 0; this.index--) {
               Iterator var1 = this.iterators[this.index];
               if (var1.hasNext()) {
                  break;
               }

               if (this.index == 0) {
                  this.endOfData();
                  break;
               }

               var1 = this.iterables[this.index].iterator();
               this.iterators[this.index] = var1;
               if (!var1.hasNext()) {
                  this.endOfData();
                  break;
               }
            }
         }

         return this.index >= 0;
      }
   }

   public Cartesian_Product_ProductIterator(Class<T> var1, Iterable<? extends T>[] var2) {
      this.iterables = var2;
      this.iterators = (Iterator<? extends T>[])Cartesian.method_08277(Iterator.class, this.iterables.length);

      for (int var3 = 0; var3 < this.iterables.length; var3++) {
         this.iterators[var3] = var2[var3].iterator();
      }

      this.results = (T[])Cartesian.method_08277(var1, this.iterators.length);
   }
}
