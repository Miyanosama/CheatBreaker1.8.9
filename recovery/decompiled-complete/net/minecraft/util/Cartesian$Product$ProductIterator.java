package net.minecraft.util;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.server.integrated.IntegratedServer$2;
import org.java_websocket.exceptions.IncompleteException;
import recovered.unidentified.UnidentifiedClass1222;

public class Cartesian$Product$ProductIterator<T> extends UnmodifiableIterator<T[]> {
   public int index = -2;
   public Iterator<? extends T>[] iterators;
   public IntegratedServer$2 field_0002;
   public IncompleteException field_0004;
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

   public Cartesian$Product$ProductIterator(Class<T> var1, Iterable<? extends T>[] var2) {
      this.iterables = var2;
      this.iterators = (Iterator<? extends T>[])UnidentifiedClass1222.method_08277(Iterator.class, this.iterables.length);

      for (int var3 = 0; var3 < this.iterables.length; var3++) {
         this.iterators[var3] = var2[var3].iterator();
      }

      this.results = (T[])UnidentifiedClass1222.method_08277(var1, this.iterators.length);
   }
}
