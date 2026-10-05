package net.optifine.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class IteratorCache {
   public static Deque<IteratorCache.IteratorReusable<Object>> dequeIterators = new ArrayDeque<>();

   static {
      for (int var0 = 0; var0 < 1000; var0++) {
         IteratorCache.IteratorReadOnly var1 = new IteratorCache.IteratorReadOnly();
         dequeIterators.add(var1);
      }
   }

   public static void finished(IteratorCache.IteratorReusable<Object> var0) {
      synchronized (dequeIterators) {
         if (dequeIterators.size() <= 1000) {
            var0.setList(null);
            dequeIterators.addLast(var0);
         }
      }
   }

   public static Iterator<Object> getReadOnly(List var0) {
      synchronized (dequeIterators) {
         Object var2 = dequeIterators.pollFirst();
         if (var2 == null) {
            var2 = new IteratorCache.IteratorReadOnly();
         }

         ((IteratorCache.IteratorReusable)var2).setList(var0);
         return (Iterator<Object>)var2;
      }
   }

   public static class IteratorReadOnly implements IteratorCache.IteratorReusable<Object> {
      public List<Object> list;
      public boolean hasNext;
      public int index;

      @Override
      public void remove() {
         throw new UnsupportedOperationException("remove");
      }

      @Override
      public void setList(List<Object> var1) {
         if (this.hasNext) {
            throw new RuntimeException("Iterator still used, oldList: " + this.list + ", newList: " + var1);
         } else {
            this.list = var1;
            this.index = 0;
            this.hasNext = var1 != null && this.index < var1.size();
         }
      }

      @Override
      public boolean hasNext() {
         if (!this.hasNext) {
            IteratorCache.finished(this);
            return false;
         } else {
            return this.hasNext;
         }
      }

      @Override
      public Object next() {
         if (!this.hasNext) {
            return null;
         } else {
            Object var1 = this.list.get(this.index);
            this.index++;
            this.hasNext = this.index < this.list.size();
            return var1;
         }
      }
   }

   public interface IteratorReusable<E> extends Iterator<E> {
      void setList(List<E> var1);
   }
}
