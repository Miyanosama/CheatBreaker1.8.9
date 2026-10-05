package io.netty.util;

import java.util.Arrays;

public class Recycler$Stack<T> {
   public Recycler<T> parent;
   public Recycler$WeakOrderQueue cursor;
   public int maxCapacity;
   public Thread thread;
   public Recycler$WeakOrderQueue prev;
   public int size;
   public volatile Recycler$WeakOrderQueue head;
   public Recycler$DefaultHandle[] elements;

   public Recycler$DefaultHandle newHandle() {
      return new Recycler$DefaultHandle(this);
   }

   public boolean scavenge() {
      if (this.scavengeSome()) {
         return true;
      } else {
         this.prev = null;
         this.cursor = this.head;
         return false;
      }
   }

   public void push(Recycler$DefaultHandle var1) {
      if ((Recycler$DefaultHandle.access$1300(var1) | Recycler$DefaultHandle.access$700(var1)) != 0) {
         throw new IllegalStateException("recycled already");
      } else {
         Recycler$DefaultHandle.access$1302(var1, Recycler$DefaultHandle.access$702(var1, Recycler.access$1700()));
         int var2 = this.size;
         if (var2 == this.elements.length) {
            if (var2 == this.maxCapacity) {
               return;
            }

            this.elements = Arrays.copyOf(this.elements, var2 << 1);
         }

         this.elements[var2] = var1;
         this.size = var2 + 1;
      }
   }

   public Recycler$Stack(Recycler<T> var1, Thread var2, int var3) {
      this.parent = var1;
      this.thread = var2;
      this.maxCapacity = var3;
      this.elements = new Recycler$DefaultHandle[Recycler.access$1400()];
   }

   public Recycler$DefaultHandle pop() {
      int var1 = this.size;
      if (var1 == 0) {
         if (!this.scavenge()) {
            return null;
         }

         var1 = this.size;
      }

      Recycler$DefaultHandle var2 = this.elements[--var1];
      if (Recycler$DefaultHandle.access$700(var2) != Recycler$DefaultHandle.access$1300(var2)) {
         throw new IllegalStateException("recycled multiple times");
      } else {
         Recycler$DefaultHandle.access$1302(var2, 0);
         Recycler$DefaultHandle.access$702(var2, 0);
         this.size = var1;
         return var2;
      }
   }

   public boolean scavengeSome() {
      boolean var1 = false;
      Recycler$WeakOrderQueue var2 = this.cursor;
      Recycler$WeakOrderQueue var3 = this.prev;

      while (var2 != null) {
         if (var2.transfer(this)) {
            var1 = true;
            break;
         }

         Recycler$WeakOrderQueue var4 = Recycler$WeakOrderQueue.access$1500(var2);
         if (Recycler$WeakOrderQueue.access$1600(var2).get() == null) {
            if (var2.hasFinalData()) {
               while (var2.transfer(this)) {
               }
            }

            if (var3 != null) {
               Recycler$WeakOrderQueue.access$1502(var3, var4);
            }
         } else {
            var3 = var2;
         }

         var2 = var4;
      }

      this.prev = var3;
      this.cursor = var2;
      return var1;
   }
}
